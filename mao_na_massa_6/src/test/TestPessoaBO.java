package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.Parameter;

import br.sistema.crud.jdbc.bo.PessoaBO;
@RunWith(Parameterized.class)
public class TestPessoaBO {
	PessoaBO pessoa;	
	@Parameter(0);
	private int entrada1;
	@Parameter(1);
	private int esperado1;

	@Before
	public void setUp() throws Exception {
		pessoa = new PessoaBO();
	}


}
