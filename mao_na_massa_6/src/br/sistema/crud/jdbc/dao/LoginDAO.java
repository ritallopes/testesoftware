package br.sistema.crud.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import br.sistema.crud.jdbc.dto.LoginDTO;
import br.sistema.crud.jdbc.dto.PessoaDTO;
import br.sistema.crud.jdbc.exception.PersistenciaExcpetion;

public class LoginDAO {

	public boolean logar(LoginDTO loginDTO) throws PersistenciaExcpetion {
		boolean resultado = false;
		try {
			
			if(loginDTO.getNome().equals("adm") && loginDTO.getSenha().equals("adm")) {
				resultado = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistenciaExcpetion(e.getMessage(), e);
		}
		return resultado;
		
	}
	
	
	
	
	
	
	/*
	@Override
	public void inserir(LoginDTO objc) throws PersistenciaExcpetion {
	
	}

	@Override
	public void atualizar(LoginDTO objc) throws PersistenciaExcpetion {
	
	}

	@Override
	public void deletar(Integer id) throws PersistenciaExcpetion {
	
	}

	@Override
	public List<LoginDTO> listarTodos() throws PersistenciaExcpetion {
		return null;
	}

	@Override
	public LoginDTO buscarPorId(Integer id) throws PersistenciaExcpetion {
		// TODO Auto-generated method stub
		return null;
	} */

}
