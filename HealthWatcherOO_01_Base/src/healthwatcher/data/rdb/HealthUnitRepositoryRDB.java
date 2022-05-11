package healthwatcher.data.rdb;

import healthwatcher.data.IHealthUnitRepository;
import healthwatcher.model.healthguide.HealthUnit;
import healthwatcher.model.healthguide.MedicalSpeciality;

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


public class HealthUnitRepositoryRDB implements IHealthUnitRepository {

	private IPersistenceMechanism mp;

	private ResultSet resultSet;

	private SpecialityRepositoryRDB specialityRep;

	public HealthUnitRepositoryRDB(IPersistenceMechanism mp) {
		this.mp = mp;
		specialityRep = new SpecialityRepositoryRDB(mp);
	}

	public void update(HealthUnit us) throws RepositoryException, ObjectNotFoundException,
			ObjectNotValidException {
		if (us != null) {
			String sql = null;
			try {
				Statement stmt = (Statement) this.mp.getCommunicationChannel();
				sql = "update SCBS_unidadesaude set " + "descricao='" + us.getDescription() + "'"
						+ " where codigo = '" + us.getCode() + "'";
				stmt.executeUpdate(sql);
				stmt.close();
			} catch (SQLException sqlException) {
				System.out.println(sql);
				throw new RepositoryException(ExceptionMessages.EXC_FALHA_BD);
			} catch (PersistenceMechanismException mpException) {
				throw new RepositoryException(ExceptionMessages.EXC_FALHA_ATUALIZACAO);
			} finally {
				try {
					mp.releaseCommunicationChannel();
				} catch (PersistenceMechanismException e) {
					throw new PersistenceSoftException(e);
				}
			}
		} else {
			throw new ObjectNotValidException(ExceptionMessages.EXC_NULO);
		}
	}

	public boolean exists(int num) throws RepositoryException {
		return false;
	}

	public IteratorDsk getHealthUnitList() throws RepositoryException, ObjectNotFoundException {
		List listaUs = new ArrayList();

		// Query para selecionar os c�digos de todas unidades de sa�de
		// existentes no sistema
		String sql = "SELECT codigo FROM SCBS_unidadesaude";
		ResultSet rs = null;

		try {
			Statement stmt = (Statement) this.mp.getCommunicationChannel();
			rs = stmt.executeQuery(sql);

			// O resultado da query � testado para saber
			// da exist�ncia de unidades de sa�de cadastradas.
			// Caso n�o existam uma exce��o � lan�ada.
			if (rs.next()) {
				HealthUnit us = search((new Integer(rs.getString("codigo"))).intValue());
				listaUs.add(us);
			} else {
				throw new ObjectNotFoundException(ExceptionMessages.EXC_FALHA_PROCURA);
			}

			// O resultado da query � navegado, e cada
			// c�digo � informado � um m�todo (procura) que
			// monta uma unidade de s�ude a partir do c�digo.
			while (rs.next()) {
				HealthUnit us = new HealthUnit();
				us = search((new Integer(rs.getString("codigo"))).intValue());
				listaUs.add(us);
			}
			rs.close();
			stmt.close();
		} catch (PersistenceMechanismException e) {
			throw new RepositoryException(ExceptionMessages.EXC_FALHA_PROCURA);
		} catch (SQLException e) {
			throw new RepositoryException(ExceptionMessages.EXC_FALHA_PROCURA);
		} finally {
			try {
				mp.releaseCommunicationChannel();
			} catch (PersistenceMechanismException e) {
				throw new PersistenceSoftException(e);
			}
		}
		// O retorno desse m�todo � uma estrutura que permite a
		// itera��o nos elementos
		return new ConcreteIterator(listaUs);
	}

	public IteratorDsk getPartialHealthUnitList() throws RepositoryException,
			ObjectNotFoundException {
		List listaUs = new ArrayList();

		// Query para selecionar os c�digos de todas unidades de sa�de
		//existentes no sistema
		String sql = "SELECT codigo FROM SCBS_unidadesaude";
		ResultSet rs = null;

		try {
			Statement stmt = (Statement) this.mp.getCommunicationChannel();
			rs = stmt.executeQuery(sql);

			// O resultado da query � testado para saber
			//da exist�ncia de unidades de sa�de cadastradas.
			// Caso n�o existam uma exce��o � lan�ada.
			if (rs.next()) {
				HealthUnit us = partialSearch((new Integer(rs.getString("codigo"))).intValue());
				listaUs.add(us);
			} else {
				throw new ObjectNotFoundException(ExceptionMessages.EXC_FALHA_PROCURA);
			}

			// 		O resultado da query � navegado, e cada
			// c�digo � informado � um m�todo (procura) que
			// monta uma unidade de s�ude a partir do c�digo.
			while (rs.next()) {
				HealthUnit us = new HealthUnit();
				us = search((new Integer(rs.getString("codigo"))).intValue());
				listaUs.add(us);
			}
			rs.close();
			stmt.close();
		} catch (PersistenceMechanismException e) {
			e.printStackTrace();
			throw new RepositoryException(ExceptionMessages.EXC_FALHA_PROCURA);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RepositoryException(ExceptionMessages.EXC_FALHA_PROCURA);
		}

		// 	O retorno desse m�todo � uma estrutura que permite a
		// itera��o nos elementos
		return new ConcreteIterator(listaUs);
	}

	public IteratorDsk getHealthUnitListBySpeciality(int code) throws RepositoryException,
			ObjectNotFoundException {
		List listaUS = new ArrayList();

		// Query para selecionar os c�digos das unidades associadas
		// a especialidade informada como par�metro.
		String sql = "select U.codigo from "
				+ "SCBS_unidadeespecialidade R, SCBS_especialidade E, SCBS_unidadesaude U where "
				+ "E.codigo=R.codigoespecialidade AND U.codigo=R.codigounidadesaude AND "
				+ "E.codigo = '" + code + "'";

		ResultSet rs = null;

		try {
			Statement stmt = (Statement) this.mp.getCommunicationChannel();
			rs = stmt.executeQuery(sql);

			// O resultado da query � testado para saber
			// da exist�ncia de unidades de sa�de relacionadas.
			// Caso n�o existam uma exce��o � lan�ada.
			if (rs.next()) {
				HealthUnit us = new HealthUnit();
				us = partialSearch((new Integer(rs.getString("codigo"))).intValue());
				listaUS.add(us);
			} else {
				throw new ObjectNotFoundException(ExceptionMessages.EXC_FALHA_PROCURA);
			}

			// O resultado da query � navegado, e cada
			// c�digo � informado � um m�todo (procura) que
			// monta uma unidade de s�ude a partir do c�digo.
			while (rs.next()) {
				HealthUnit us = new HealthUnit();
				us = search((new Integer(rs.getString("codigo"))).intValue());
				listaUS.add(us);
			}
			rs.close();
			stmt.close();
		} catch (PersistenceMechanismException e) {
			throw new RepositoryException(ExceptionMessages.EXC_FALHA_PROCURA);
		} catch (SQLException e) {
			throw new RepositoryException(ExceptionMessages.EXC_FALHA_PROCURA);
		} finally {
			try {
				mp.releaseCommunicationChannel();
			} catch (PersistenceMechanismException e) {
				throw new PersistenceSoftException(e);
			}
		}
		// O retorno desse m�todo � uma estrutura que permite a
		// itera��o nos elementos
		return new ConcreteIterator(listaUS);
	}

	public void insert(HealthUnit hu) throws RepositoryException, ObjectAlreadyInsertedException,
			ObjectNotValidException {

		if (hu != null) {
			String sql = null;
			try {
				Statement stmt = (Statement) this.mp.getCommunicationChannel();
				sql = "insert into SCBS_unidadesaude (codigo,DESCRICAO) values (";
				sql += hu.getCode() + ",'";
				sql += hu.getDescription() + "')";
				stmt.executeUpdate(sql);
				stmt.close();
			} catch (SQLException e) {
				System.out.println(sql);
				throw new PersistenceSoftException(e);
			} catch (PersistenceMechanismException e) {
				throw new PersistenceSoftException(e);
			} finally {
				try {
					mp.releaseCommunicationChannel();
				} catch (PersistenceMechanismException e) {
					throw new PersistenceSoftException(e);
				}
			}
		} else {
			throw new ObjectNotValidException(ExceptionMessages.EXC_NULO);
		}
	}

	public HealthUnit search(int code) throws RepositoryException, ObjectNotFoundException {

		HealthUnit us = null;
		String sql = null;
		try {

			// Query montada para recuperar os relacionamentos de
			// unidades de sa�de com especialidades
			// filtrando pelo identificador da unidade.
			sql = "select * from SCBS_unidadeespecialidade where " + "codigounidadesaude = '"
					+ code + "'";

			Statement stmt = (Statement) this.mp.getCommunicationChannel();
			resultSet = stmt.executeQuery(sql);
			List specialities = new ArrayList();

			// Iterar nos resultados da query para recuperar as
			// especialidades e inserir em um conjunto
			// (RepositorioEspecialidadeArray)
			while (resultSet.next()) {
				try {
					MedicalSpeciality esp = specialityRep.search((new Integer(resultSet
							.getString("codigoespecialidade"))).intValue());
					System.out.println("medicalspeciality: " + esp.getCodigo() + " "
							+ esp.getDescricao());
					specialities.add(esp);
				} catch (ObjectNotFoundException ex) {
				}
			}
			resultSet.close();
			stmt.close();

			// Query montada para recuperar a unidade de sa�de
			// usando o identificador da unidade informado como
			// par�metro do m�todo
			sql = "select * from SCBS_unidadesaude where " + "codigo = '" + code + "'";

			stmt = (Statement) this.mp.getCommunicationChannel();
			resultSet = stmt.executeQuery(sql);

			if (resultSet.next()) {
				us = new HealthUnit(resultSet.getString("descricao"), specialities);

				//us.setId(resultSet.getLong("ID"));
				us.setCode((new Integer(resultSet.getString("codigo"))).intValue());

				//preparar para buscar em outra tabela as especialidades desta unidade de saude
				//depois vai chamar deepAccess() de RepositorioEspecialidadeBDR
			} else {
				throw new ObjectNotFoundException(ExceptionMessages.EXC_FALHA_PROCURA);
			}
			resultSet.close();
			stmt.close();

		} catch (PersistenceMechanismException e) {
			throw new RepositoryException(ExceptionMessages.EXC_FALHA_BD);
		} catch (SQLException e) {
			System.out.println(sql);
			e.printStackTrace();
			throw new RepositoryException(ExceptionMessages.EXC_FALHA_BD);
		} finally {
			try {
				mp.releaseCommunicationChannel();
			} catch (PersistenceMechanismException e) {
				throw new PersistenceSoftException(e);
			}
		}

		return us;
	}

	public void remove(int codigo) throws RepositoryException, ObjectNotFoundException {
	}

	public HealthUnit partialSearch(int codigo) throws RepositoryException, ObjectNotFoundException {
		HealthUnit hu = null;
		String sql = null;
		try {
			// Query montada para recuperar a unidade de sa�de
			// usando o identificador da unidade informado como
			// par�metro do m�todo
			sql = "select * from SCBS_unidadesaude where " + "codigo = '" + codigo + "'";

			Statement stmt = (Statement) this.mp.getCommunicationChannel();
			resultSet = stmt.executeQuery(sql);

			if (resultSet.next()) {
				hu = new HealthUnit();
				hu.setCode((new Integer(resultSet.getString("codigo"))).intValue());
				hu.setDescription(resultSet.getString("descricao"));
			} else {
				throw new ObjectNotFoundException(ExceptionMessages.EXC_FALHA_PROCURA);
			}
			resultSet.close();
			stmt.close();

		} catch (PersistenceMechanismException e) {
			throw new RepositoryException("PersistenceMechanismException: " + e.getMessage());
		} catch (SQLException e) {
			System.out.println(sql);
			throw new RepositoryException("SQLException: " + e.getMessage());
		} finally {
			try {
				mp.releaseCommunicationChannel();
			} catch (PersistenceMechanismException e) {
				throw new RepositoryException("PersistenceMechanismException: " + e.getMessage());
			}
		}
		return hu;
	}
}
