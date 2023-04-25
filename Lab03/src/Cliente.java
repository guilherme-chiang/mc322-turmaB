import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Cliente {

	protected String nome;

	protected String endereco;

	protected List<Veiculo> listaVeiculos = new LinkedList<Veiculo>();

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
		return Collections.unmodifiableList(listaVeiculos);
	}

	public void setListaVeiculos(List<Veiculo> listaVeiculos) {
		this.listaVeiculos = listaVeiculos;
	}

	// Metodos

	public void cadastrarVeiculo(Veiculo veiculo) {
		this.listaVeiculos.add(veiculo);
	}

	public Veiculo procurarVeiculo(String placaAlvo) {

		for (int i = 0; i < listaVeiculos.size(); i++) {

			String placaCarro = listaVeiculos.get(i).getPlaca();

			if (Objects.equals(placaAlvo, placaCarro))
				return listaVeiculos.get(i);

		}

		return null;
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
