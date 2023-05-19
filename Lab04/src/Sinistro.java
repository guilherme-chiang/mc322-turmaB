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
	private Cliente cliente;

	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

	// Construtor
	public Sinistro() {
		this.id = novoId();
		this.data = new Date();
	}

	// Getters e Setters
	public int getId() {
		return id;
	}
	
	public String getData() {
		return simpleDateFormat.format(data);
	}

	public void setData(String data) throws ParseException {
		this.data = simpleDateFormat.parse(data);;
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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public SimpleDateFormat getSimpleDateFormat() {
		return simpleDateFormat;
	}

	public void setSimpleDateFormat(SimpleDateFormat simpleDateFormat) {
		this.simpleDateFormat = simpleDateFormat;
	}


	// Métodos
	private int novoId() {
		Random gerador = new Random();
		return gerador.nextInt();
	}

	public String imprimirSimplificado() {

		String saida;

		saida = "ID: " + id + ", ";
		saida += " Seguradora: " + seguradora.getNome() + ", ";
		saida += " Placa do veículo: ";
		saida += veiculo.getPlaca() + ", ";
		saida += " Cliente " + cliente.getNome() + "\n";

		return saida;
	}	
	
	@Override
	public String toString() {

		String saida;

		saida = "ID: " + id + "\n";
		saida += "	Data: " + simpleDateFormat.format(data) + "\n";
		saida += "	Endereço: " + endereco + "\n";
		saida += "	Seguradora: " + seguradora.getNome() + "\n";

		saida += "	Placa do veículo: ";
		
		if (veiculo != null)
			saida += veiculo.getPlaca() + "\n";
		else
			saida += "vazio\n";
		
		
		saida += "	Cliente ";
		if (cliente != null) {
			
			if (cliente instanceof ClientePF) {
				ClientePF clientePf = (ClientePF) cliente;

				saida += "CPF: " + clientePf.getCpf() + "\n";
			} 
			else {
				ClientePJ clientePj = (ClientePJ) cliente;

				saida += "CNPJ: " + clientePj.getCnpj() + "\n";
			}
		}
		else
			saida += "vazio\n";
		
		saida += "\n";

		return saida;
	}
}


