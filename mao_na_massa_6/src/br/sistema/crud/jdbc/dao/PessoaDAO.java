package br.sistema.crud.jdbc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.sistema.crud.jdbc.dto.EnderecoDTO;
import br.sistema.crud.jdbc.dto.PessoaDTO;
import br.sistema.crud.jdbc.dto.UfDTO;
import br.sistema.crud.jdbc.exception.PersistenciaExcpetion;

public class PessoaDAO implements GenericoDAO<PessoaDTO> {
	
	ArrayList<PessoaDTO> banco;
	private static int id_pessoa = 0;
	
	public PessoaDAO() {
		banco = new ArrayList<PessoaDTO>();
	}

	@Override
	public void inserir(PessoaDTO pessoaDTO) throws PersistenciaExcpetion {
		try {
			pessoaDTO.setIdPessoa(id_pessoa);
			this.id_pessoa++;
			banco.add(pessoaDTO);
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistenciaExcpetion(e.getMessage(), e);
		}
	}

	@Override
	public void atualizar(PessoaDTO pessoaDTO) throws PersistenciaExcpetion {
		try {
			int n_size = banco.size();
			for(int i = 0; i < n_size; i++) {
				if(banco.get(i).getIdPessoa().equals(pessoaDTO.getIdPessoa())) {
					banco.set(i, pessoaDTO);
				}
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			throw new PersistenciaExcpetion(e.getMessage(), e);
		}
	}

	@Override
	public void deletar(Integer idPessoa) throws PersistenciaExcpetion {
		try {
			
			int n_size = banco.size();
			int posicao = -1;
			for(int i = 0; i < n_size; i++) {
				if(banco.get(i).getIdPessoa().equals(idPessoa)) {
					posicao = i;
					break;
				}
			}
			
			banco.remove(posicao);
			
		} catch(Exception e) {
			e.printStackTrace();
			throw new PersistenciaExcpetion(e.getMessage(), e);
		}
	}
	
	public void deletarTudo() throws PersistenciaExcpetion {
		try {
			banco.clear();
		} catch(Exception e) {
			e.printStackTrace();
			throw new PersistenciaExcpetion(e.getMessage(), e);
		}
		
	}

	@Override
	public List<PessoaDTO> listarTodos() throws PersistenciaExcpetion {
		List<PessoaDTO> listaPessoas = new ArrayList<PessoaDTO>();
		try {
			listaPessoas = (List<PessoaDTO>) banco.clone();
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistenciaExcpetion(e.getMessage(), e);
		}
		return listaPessoas;
	}

	@Override
	public PessoaDTO buscarPorId(Integer idPessoa) throws PersistenciaExcpetion {
		PessoaDTO pessoa = null;
		try {
			
			int n_size = banco.size();
			int posicao = -1;
			for(int i = 0; i < n_size; i++) {
				if(banco.get(i).getIdPessoa().equals(idPessoa)) {
					posicao = i;
					break;
				}
			}
			
			pessoa = banco.get(posicao);
			
		} catch(Exception e) {
			e.printStackTrace();
			throw new PersistenciaExcpetion(e.getMessage(), e);
		}
		
		return pessoa;
	}
	
}
