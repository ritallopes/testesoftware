package teste;

public class RH {
	public String recomendacaoCandidatoEmprego(int idade) {
	
		if((idade >= 0 && idade < 16) || (idade >= 55 && idade < 99)) {
			return "Não pode empregar";
		}else if(idade >= 16 && idade <18) {
			return "Pode ser empregado tempo parcial";			
		}else if(idade >= 18 && idade < 55) {
			return "Pode ser empregado tempo integral";
		}else {
			return "Inválida";
		}
	}

}
