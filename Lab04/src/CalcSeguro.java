/*
 * Define as constantes para o cálculo do seguro
 */
public enum CalcSeguro {

	VALOR_BASE(100.0),
	
	FATOR_18_30(1.2),
	FATOR_30_60(1.0),
	FATOR_60_90(1.5);

	//atributo
	private final double valor;
	
	//Construtor
	CalcSeguro(double valor){
		this.valor = valor;
	}
	
	//getter
	public double getValor() {
		return valor;
	}

}
