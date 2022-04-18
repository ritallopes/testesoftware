package br.sistema.crud.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.sistema.crud.jdbc.dto.UfDTO;
import br.sistema.crud.jdbc.exception.NegocioException;
import br.sistema.crud.jdbc.exception.PersistenciaExcpetion;

public class UfDAO {
	
	
	public List<UfDTO> listaEstado() throws  PersistenciaExcpetion{
	
	List<UfDTO> lista = new ArrayList<>();
	try{
		
		UfDTO ufDTO1 = new UfDTO();
		ufDTO1.setIdUF(1);
		ufDTO1.setSiglaUF("RN");
		ufDTO1.setDescrcao("Rio Grande do Norte");
		lista.add(ufDTO1);
		
		UfDTO ufDTO2 = new UfDTO();
		ufDTO2.setIdUF(2);
		ufDTO2.setSiglaUF("SP");
		ufDTO2.setDescrcao("São Paulo");
		lista.add(ufDTO2);
		
	}catch(Exception e){
		e.printStackTrace();
		throw new PersistenciaExcpetion(e.getMessage(), e);
	}
	
		return lista;
	}

}
