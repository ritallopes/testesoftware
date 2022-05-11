package healthwatcher.data.rdb;

import healthwatcher.data.IDiseaseRepository;
import healthwatcher.model.complaint.DiseaseType;
import healthwatcher.model.complaint.Symptom;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import lib.exceptions.ExceptionMessages;
import lib.exceptions.ObjectAlreadyInsertedException;
import lib.exceptions.ObjectNotFoundException;
import lib.exceptions.ObjectNotValidException;
import lib.exceptions.PersistenceMechanismException;
import lib.exceptions.PersistenceSoftException;
import lib.exceptions.RepositoryException;
import lib.persistence.IPersistenceMechanism;
import lib.util.ConcreteIterator;
import lib.util.IteratorDsk;





public class DiseaseTypeRepositoryRDB implements IDiseaseRepository {

	private IPersistenceMechanism mp;

	private ResultSet resultSet;

	public DiseaseTypeRepositoryRDB(IPersistenceMechanism mp) {
		this.mp = mp;
	}

	public void update(DiseaseType td) throws RepositoryException, ObjectNotFoundException,
			ObjectNotValidException {
	}

	public void filter(int codigo) throws RepositoryException, ObjectNotFoundException {
	}

	public boolean exists(int code) throws RepositoryException {
		return false;
	}

	public IteratorDsk getDiseaseTypeList() throws RepositoryException, ObjectNotFoundException {
		List listatd = new ArrayList();
		String sql = "SELECT * FROM SCBS_tipodoenca";
		ResultSet rs = null;

		try {
			Statement stmt = (Statement) this.mp.getCommunicationChannel();
			rs = stmt.executeQuery(sql);

			if (!rs.next()) {
				throw new ObjectNotFoundException(ExceptionMessages.EXC_FALHA_PROCURA);
			}

			do {
				DiseaseType td = partialSearch((new Integer(rs.getString("codigo"))).intValue());
				listatd.add(td);
			} while (rs.next());

			rs.close();
			stmt.close();
		} catch (PersistenceMechanismException e) {
			e.printStackTrace();
			throw new RepositoryException(ExceptionMessages.EXC_FALHA_BD);
		} catch (SQLException e) {
			System.out.println(sql);
			e.printStackTrace();
			throw new RepositoryException(ExceptionMessages.EXC_FALHA_BD);
		}
		return new ConcreteIterator(listatd);
	}

	public void insert(DiseaseType td) throws RepositoryException, ObjectAlreadyInsertedException,
			ObjectNotValidException {

	}

	/**
	 * M�todo para recuperar um tipo de doen�a do banco de dados.
	 *
	 * @param codigo c�digo do tipo de doen�a a ser procurado
	 * @return um objeto tipo doen�a montado a partir dos dados
	 *           do banco de dados
	 */
	public DiseaseType partialSearch(int codigo) throws ObjectNotFoundException {

		//$System.out.println("RepositorioTipoDoenca::procuraParcial()->begin");

		DiseaseType td = null;
		String nome, descricao, manifestacao, duracao;
		String sql = null;
		// Tentativa de recuperar os dados do bd usando o c�digo 
		// informado
		try {
			sql = "select * from SCBS_tipodoenca where " + "codigo = '" + codigo + "'";

			Statement stmt = (Statement) this.mp.getCommunicationChannel();
			resultSet = stmt.executeQuery(sql);

			if (resultSet.next()) {
				codigo = (new Integer(resultSet.getString("codigo"))).intValue();
				nome = resultSet.getString("nome");
				descricao = resultSet.getString("descricao");
				manifestacao = resultSet.getString("manifestacao");
				duracao = resultSet.getString("duracao");

				//preparar para buscar em outra tabela os sintomas desta doenca
				//depois vai chamar deepAccess() de SymptomRepositoryArray
			} else {
				throw new ObjectNotFoundException(ExceptionMessages.EXC_FALHA_PROCURA);
			}
			resultSet.close();
			stmt.close();

			td = new DiseaseType();
			td.setName(nome);
			td.setDescription(descricao);
			td.setManifestation(manifestacao);
			td.setDuration(duracao);
			td.setCode(codigo);

		} catch (PersistenceMechanismException e) {
			throw new PersistenceSoftException(e);
		} catch (SQLException e) {
			System.out.println(sql);
			throw new PersistenceSoftException(e);
		} finally {
			try {
				mp.releaseCommunicationChannel();
			} catch (PersistenceMechanismException e) {
				throw new PersistenceSoftException(e);
			}
		}

		return td;
	}

	/**
	 * M�todo para recuperar um tipo de doen�a do banco de dados.
	 *
	 * @param codigo c�digo do tipo de doen�a a ser procurado
	 * @return um objeto tipo doen�a montado a partir dos dados
	 *		   do banco de dados
	 */
	public DiseaseType search(int code) throws RepositoryException, ObjectNotFoundException {

		DiseaseType td = null;
		String nome, descricao, manifestacao, duracao;
		List sintomas;
		String sql = null;
		// Tentativa de recuperar os dados do bd usando o c�digo 
		// informado
		try {
			sql = "select * from SCBS_tipodoenca where " + "codigo = '" + code + "'";

			Statement stmt = (Statement) this.mp.getCommunicationChannel();
			resultSet = stmt.executeQuery(sql);

			if (resultSet.next()) {
				code = (new Integer(resultSet.getString("codigo"))).intValue();
				nome = resultSet.getString("nome");
				descricao = resultSet.getString("descricao");
				manifestacao = resultSet.getString("manifestacao");
				duracao = resultSet.getString("duracao");

				//preparar para buscar em outra tabela os sintomas desta doenca
				//depois vai chamar deepAccess() de RepositorioSintomaArray
			} else {
				throw new ObjectNotFoundException(ExceptionMessages.EXC_FALHA_PROCURA);
			}
			resultSet.close();
			stmt.close();

			// Query para recuperar os sintomas relacionados com o tipo
			// de doen�a encontrado a partir do c�digo
			sql = "select * from SCBS_tipodoencasintoma where codigotipodoenca = '" + code + "'";

			stmt = (Statement) this.mp.getCommunicationChannel();
			resultSet = stmt.executeQuery(sql);

			sintomas = new ArrayList();
			while (resultSet.next()) {
				int codeSymptom = (new Integer(resultSet.getString("codigosintoma"))).intValue();

				// Query para encontrar os dados de um sintoma usando o
				// c�digo encontrado na tabela de relacionamentos.
				sql = "select * from SCBS_sintoma where " + "codigo = '" + codeSymptom + "'";

				Statement stmt2 = (Statement) this.mp.getCommunicationChannel();
				ResultSet resultSet2 = stmt2.executeQuery(sql);
				Symptom sintoma;

				if (resultSet2.next()) {
					sintoma = new Symptom(resultSet2.getString("descricao"));
					sintoma.setCode((new Integer(resultSet2.getString("codigo"))).intValue());
				} else {
					// Caso esse trecho de c�digo seja executado,
					// a tabela de relacinoamentos n�o est� consistente
					// com a tabela de sintomas
					throw new ObjectNotFoundException(ExceptionMessages.EXC_FALHA_PROCURA);
				}
				resultSet2.close();
				stmt2.close();

				sintomas.add(sintoma);

			}
			resultSet.close();
			stmt.close();

			td = new DiseaseType(nome, descricao, manifestacao, duracao, sintomas);
			td.setCode(code);

		} catch (PersistenceMechanismException e) {
			throw new RepositoryException(ExceptionMessages.EXC_FALHA_BD);
		} catch (SQLException e) {
			System.out.println(sql);
			e.printStackTrace();
			throw new RepositoryException(ExceptionMessages.EXC_FALHA_BD);
		}
		
		return td;
	}
}