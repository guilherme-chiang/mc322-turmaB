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

	@Override
	protected double calculaScore() {

		double valorBase;
		double fatorIdade;
		double quantidadeCarros;

		// valor base do seguro
		valorBase = CalcSeguro.VALOR_BASE.getValor();

		// Calcula a idade da pessoa em anos
		Date dataAtual = new Date();
		long diferencaMillis = dataAtual.getTime() - dataNascimento.getTime();
		long idade = diferencaMillis / (1000L * 60 * 60 * 24 * 365);

		if (idade >= 18 && idade < 30)
			fatorIdade = CalcSeguro.FATOR_18_30.getValor();

		else if (idade >= 30 && idade < 60)
			fatorIdade = CalcSeguro.FATOR_30_60.getValor();

		else if (idade >= 60 && idade < 90)
			fatorIdade = CalcSeguro.FATOR_60_90.getValor();

		else
			fatorIdade = 0.0;

		// calcula quantidade de carros
		quantidadeCarros = listaVeiculos.size() * 1.0;

		// retorna o Score
		return valorBase * fatorIdade * quantidadeCarros;
	}

	@Override
	public String toString() {

		String saida;

		saida = "CPF: " + cpf + "\n";
		saida += "	Nome: " + nome + "\n";
		saida += "	Endereço: " + endereco + "\n";
		saida += "	Gênero: " + genero + "\n";
		saida += "	Data de nascimento: " + simpleDateFormat.format(dataNascimento) + "\n";
		saida += "	Educação: " + educacao + "\n";
		saida += "	Classe econômica: " + classeEconomica + "\n";
		saida += "	Data da licença: " + simpleDateFormat.format(dataLicenca) + "\n";

		saida += "	Veiculo(s):\n";

		if (listaVeiculos != null) {
			for (int i = 0; i < listaVeiculos.size(); i++)
				saida += "		" + listaVeiculos.get(i).toString();
		} else
			saida += "		Nenhum\n";
		
		return saida;
	}
}
