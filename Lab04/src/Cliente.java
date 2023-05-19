import java.util.LinkedList;
import java.util.List;

public class Cliente {

	protected String nome;
	protected String endereco;
	protected List<Veiculo> listaVeiculos = new LinkedList<Veiculo>();
	protected double valorSeguro;

	// Construtor
	protected Cliente(String nome, String endereco) {
		this.nome = nome;
		this.endereco = endereco;
	}

	// Getters e Setters
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public List<Veiculo> getListaVeiculos() {
		return listaVeiculos;
	}

	public void setListaVeiculos(List<Veiculo> listaVeiculos) {
		this.listaVeiculos = listaVeiculos;
	}

	public double getValorSeguro() {
		return valorSeguro;
	}

	public void setValorSeguro(double valorSeguro) {
		this.valorSeguro = valorSeguro;
	}

	// Metodos
	protected double calculaScore() {
		return 0.0;
	}

	/*
	 * Verifica se o veiculo já existe na lista de veiculos, considerando a placa do
	 * carro
	 */
	public boolean cadastrarVeiculo(Veiculo veiculo) {

		for (Veiculo veiculo1 : listaVeiculos) {

			String PlacaEncontrado = veiculo1.getPlaca();

			if (PlacaEncontrado.equals(veiculo.getPlaca()))
				return false;
		}

		this.listaVeiculos.add(veiculo);

		return true;
	}

	
	/*
	 * Imprime a lista de veiculos completa
	 */
	public void listarVeiculos() {

		// imprime descrição do que está sendo listado
		System.out.println("\nLista de Veiculos registrados no cliente " + nome + "\n");

		if (listaVeiculos.size() > 0) {
			for (Veiculo veiculo : listaVeiculos)
				System.out.print(veiculo.toString());
		}

		else
			System.out.println("\nNão há veículo registrado\n");
	}
	
	
	@Override
	public String toString() {

		String saida;

		saida = "Nome: " + nome + "\n";
		saida += "Endereço: " + endereco + "\n";
		saida += "Veiculo(s):\n" + listaVeiculos + "\n";

		return saida;
	}

}
