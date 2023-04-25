import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Sinistro {

	private final int id;
	private Date data;
	private String endereco;
	private Seguradora seguradora;
	private Veiculo veiculo;
	private ClientePF clientePF;
	private ClientePJ clientePJ;

	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

	// Construtor
	public Sinistro() {
		Random gerador = new Random();

		this.id = gerador.nextInt();

		this.data = new Date();

		this.endereco = "";
		this.seguradora = null;
		this.veiculo = null;
		this.clientePF = null;
		this.clientePJ = null;
	}

	// Getters e Setters
	public int getId() {
		return id;
	}

	public String getData() {
		return simpleDateFormat.format(data);
	}

	public void setData(String data) throws ParseException {
		this.data = simpleDateFormat.parse(data);
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Seguradora getSeguradora() {
		return seguradora;
	}

	public void setSeguradora(Seguradora seguradora) {
		this.seguradora = seguradora;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public ClientePF getClientePF() {
		return clientePF;
	}

	public void setClientePF(ClientePF clientePF) {
		if (clientePJ == null)
			this.clientePF = clientePF;

		else
			System.out.println("Não é possível associar CPF, pois já existe um CNPJ associado");
	}

	public ClientePJ getClientePJ() {
		return clientePJ;
	}

	public void setClientePJ(ClientePJ clientePJ) {
		if (clientePF == null)
			this.clientePJ = clientePJ;

		else
			System.out.println("Não é possível associar CNPJ, pois já existe um CPF associado");
	}

	// Metodos

	@Override
	public String toString() {

		String saida;

		saida = "ID: " + id + "\n";
		saida += "	Data: " + simpleDateFormat.format(data) + "\n";
		saida += "	Endereço: " + endereco + "\n";
		saida += "	Seguradora: " + seguradora.getNome() + "\n";

		if (veiculo != null)
			saida += "	Placa do veículo: " + veiculo.getPlaca() + "\n";

		if (clientePF == null && clientePJ != null)
			saida += "	CNPJ do cliente: " + clientePJ.getCnpj() + "\n";

		else if (clientePF != null && clientePJ == null)
			saida += "	CPF do cliente: " + clientePF.getCpf() + "\n";

		saida += "\n";

		return saida;
	}

}
