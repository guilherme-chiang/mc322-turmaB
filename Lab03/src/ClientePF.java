import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClientePF extends Cliente {

	private final String cpf;
	private String genero;
	private Date dataLicenca;
	private String educacao;
	private Date dataNascimento;
	private String classeEconomica;

	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

	// Construtor
	public ClientePF(String nome, String endereco, String cpf, String genero, String dataLicenca, String educacao,
			String dataNascimento, String classeEconomica) throws ParseException {

		super(nome, endereco);

		this.cpf = cpf;
		this.genero = genero;
		this.dataLicenca = simpleDateFormat.parse(dataLicenca);
		this.educacao = educacao;
		this.dataNascimento = simpleDateFormat.parse(dataNascimento);
		this.classeEconomica = classeEconomica;
	}

	// Getters e Setters
	public String getCpf() {
		return cpf;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getDataLicenca() {
		return simpleDateFormat.format(dataLicenca);
	}

	public void setDataLicenca(String dataLicenca) throws ParseException {
		this.dataLicenca = simpleDateFormat.parse(dataLicenca);
	}

	public String getEducacao() {
		return educacao;
	}

	public void setEducacao(String educacao) {
		this.educacao = educacao;
	}

	public String getDataNascimento() {
		return simpleDateFormat.format(dataNascimento);
	}

	public void setDataNascimento(String dataNascimento) throws ParseException {
		this.dataNascimento = simpleDateFormat.parse(dataNascimento);
	}

	public String getClasseEconomica() {
		return classeEconomica;
	}

	public void setClasseEconomica(String classeEconomica) {
		this.classeEconomica = classeEconomica;
	}

	// Metodos

	// Verifica se CPF e valido
	public boolean validarCPF() {

		String subCpf = this.cpf;

		subCpf = subCpf.replace(".", "");
		subCpf = subCpf.replace("-", "");

		// Caso não tenha 11 digitos
		if (subCpf.length() != 11)
			return false;

		// Caso todos os numeros sejam iguais
		else if (todosIguais(subCpf))
			return false;

		// Caso o codigo verificador esteje errado
		else if (!codigoVerificador(subCpf))
			return false;

		// Retorne verdadeiro caso CPF esteje certo
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

		// variavel para calcular os digitos da posicao 10 e 11
		int dig10, dig11;

		// variaveis auxiliares para o calculo do codigo verificador
		int num, sm, peso;

		// Numero verificador do digito 10
		sm = 0;
		peso = 10;

		for (int i = 0; i < 9; i++) {

			// (48 e a posicao de '0' na tabela ASCII)
			num = (int) (numero.charAt(i) - 48);
			sm = sm + (num * peso);
			peso--;
		}

		int resto = sm % 11;

		if (resto < 2)
			dig10 = 0;
		else
			dig10 = 11 - resto;

		// Numero verificador do digito 11
		sm = 0;
		peso = 11;

		for (int i = 0; i < 10; i++) {

			// (48 e a posicao de '0' na tabela ASCII)
			if (i != 9)
				num = (int) (numero.charAt(i) - 48);

			else
				num = dig10;

			sm = sm + (num * peso);
			peso = peso - 1;
		}

		resto = sm % 11;

		if (resto < 2)
			dig11 = 0;
		else
			dig11 = 11 - resto;

		// Comparando com o codigo verificador do input

		if (dig10 == (int) (numero.charAt(9) - 48) && dig11 == (int) (numero.charAt(10) - 48))
			return true;

		else
			return false;

	}

	
	@Override
	public String toString() {

		String saida;

		saida = "Nome: " + nome + "\n";
		saida += "	CPF: " + cpf + "\n";
		saida += "	Gênero: " + genero + "\n";
		saida += "	Data de nascimento: " + simpleDateFormat.format(dataNascimento) + "\n";
		saida += "	Educação: " + educacao + "\n";
		saida += "	Classe econômica: " + classeEconomica + "\n";
		saida += "	Endereço: " + endereco + "\n";
		saida += "	Data da licença: " + simpleDateFormat.format(dataLicenca) + "\n";

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
