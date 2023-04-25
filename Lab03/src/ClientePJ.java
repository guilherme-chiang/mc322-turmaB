import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClientePJ extends Cliente {

	private final String cnpj;
	private Date dataFundacao;

	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

	// Construtor
	public ClientePJ(String nome, String endereco, String cnpj, String dataFundacao) throws ParseException {

		super(nome, endereco);

		this.cnpj = cnpj;
		this.dataFundacao = simpleDateFormat.parse(dataFundacao);
	}

	// Getters e Setters
	public String getCnpj() {
		return cnpj;
	}

	public String getDataFundacao() {
		return simpleDateFormat.format(dataFundacao);
	}

	public void setDataFundacao(String dataFundacao) throws ParseException {
		this.dataFundacao = simpleDateFormat.parse(dataFundacao);
	}

	// Metodos

	// Verifica se CNPJ e valido
	public boolean validarCNPJ() {

		String subCnpj = this.cnpj;

		subCnpj = subCnpj.replace(".", "");
		subCnpj = subCnpj.replace("-", "");
		subCnpj = subCnpj.replace("/", "");

		// Caso não tenha 14 digitos
		if (subCnpj.length() != 14)
			return false;

		// Caso todos os numeros sejam iguais
		else if (todosIguais(subCnpj))
			return false;

		// Caso o codigo verificador esteje errado
		else if (!codigoVerificador(subCnpj))
			return false;

		// Retorne verdadeiro caso CNPJ esteje certo
		else
			return true;

	}

	// Confere se os digitos sao todos iguais
	private boolean todosIguais(String numero) {

		int n = numero.length();

		for (int i = 1; i < n; i++)
			if (numero.charAt(0) != numero.charAt(i))
				return false;

		return true;
	}

	// calculo o codigo verificador
	private boolean codigoVerificador(String numero) {

		// variavel para calcular os digitos da posicao 13 e 14
		int dig13, dig14;

		// variaveis auxiliares para o calculo do codigo verificador
		int num, sm, resto;

		// Numero verificador do digito 13
		sm = 0;
		int peso1[] = { 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };

		for (int i = 0; i < 12; i++) {
			num = (int) (numero.charAt(i) - 48);
			sm = sm + (num * peso1[i]);
		}

		resto = sm % 11;

		if (resto < 2)
			dig13 = 0;
		else
			dig13 = 11 - resto;

		// Numero verificador do digito 14
		sm = 0;
		int peso2[] = { 6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };

		for (int i = 0; i < 13; i++) {

			// (48 e a posicao de '0' na tabela ASCII)
			if (i != 12)
				num = (int) (numero.charAt(i) - 48);
			
			else
				num = dig13;

			sm = sm + (num * peso2[i]);
		}

		resto = sm % 11;

		if (resto < 2)
			dig14 = 0;
		else
			dig14 = 11 - resto;

		// Comparando com o codigo verificador do input
		if (dig13 == (int) (numero.charAt(12) - 48) && dig14 == (int) (numero.charAt(13) - 48))
			return true;

		else
			return false;

	}

	@Override
	public String toString() {

		String saida;

		saida = "Nome: " + nome + "\n";
		saida += "	CNPJ: " + cnpj + "\n";
		saida += "	Data de fundação: " + simpleDateFormat.format(dataFundacao) + "\n";
		saida += "	Endereço: " + endereco + "\n";

		saida += "	Veiculo(s):\n";

		int i = 0;

		if (listaVeiculos != null) {
			for (i = 0; i < listaVeiculos.size(); i++)
				saida += "	" + listaVeiculos.get(i).toString();
		}

		if (i == 0)
			saida += "		Nenhum\n";

		return saida;
	}
}
