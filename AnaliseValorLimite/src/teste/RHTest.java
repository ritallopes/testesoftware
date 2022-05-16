package teste;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class RHTest {
	private RH rh = new RH();;
	
	@Parameter(0)
	public int entrada;
	@Parameter(1)
	public String esperado;
	
	@Parameters
	public static Iterable<Object[]> data(){
		return Arrays.asList(new Object[][] {{0,"Inválida"}, 
			{14,"Não pode empregar"},
			{16,"Pode ser empregado tempo parcial"},
			{18,"Pode ser empregado tempo integral"}});
	}
	@Test
	public void test() {
		System.out.println(esperado);
		System.out.println(rh.recomendacaoCandidatoEmprego(entrada));
		assertEquals(esperado, rh.recomendacaoCandidatoEmprego(entrada), 0);
	}

}
