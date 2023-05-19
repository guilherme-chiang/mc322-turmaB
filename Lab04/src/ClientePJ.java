import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClientePJ extends Cliente {

	private final String cnpj;
	private Date dataFundacao;
	private int qtdeFuncionarios;

	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

	// Construtor
	public ClientePJ(String nome, String endereco, String cnpj, String dataFundacao, int qtdeFuncionarios)
			throws ParseException {

		super(nome, endereco);

		this.cnpj = cnpj;
		this.dataFundacao = simpleDateFormat.parse(dataFundacao);
		this.qtdeFuncionarios = qtdeFuncionarios;
	}

	// Getters e Setters
	public String getCnpj() {
		return cnpj;
	}

	public int getQtdeFuncionarios() {
		return qtdeFuncionarios;
	}

	public void setQtdeFuncionarios(int qtdeFuncionarios) {
		this.qtdeFuncionarios = qtdeFuncionarios;
	}

	public String getDataFundacao() {
		return simpleDateFormat.format(dataFundacao);
	}

	public void setDataFundacao(String dataFundacao) throws ParseException {
		this.dataFundacao = simpleDateFormat.parse(dataFundacao);
	}

	// Metodos

	@Override
	protected double calculaScore() {

		double valorBase;
		double quantidadeCarros;

		// valor base do seguro
		valorBase = CalcSeguro.VALOR_BASE.getValor();

		// calcula quantidade de carros
		quantidadeCarros = listaVeiculos.size() * 1.0;

		return valorBase * (1.0 + qtdeFuncionarios / 100.0) * quantidadeCarros;
	}

	@Override
	public String toString() {

		String saida;

		saida = "CNPJ: " + cnpj + "\n";
		saida += "	Nome: " + nome + "\n";
		saida += "	Endereço: " + endereco + "\n";
		saida += "	Data de fundação: " + simpleDateFormat.format(dataFundacao) + "\n";
		saida += "	Quantidade de funcionários: " + qtdeFuncionarios + "\n";

		saida += "	Veiculo(s):\n";

		if (listaVeiculos != null) {
			for (int i = 0; i < listaVeiculos.size(); i++)
				saida += "		" + listaVeiculos.get(i).toString();
		} else
			saida += "		Nenhum\n";
		
		return saida;
	}
}
