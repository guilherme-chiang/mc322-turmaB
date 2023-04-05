import java.util.Random;

public class Sinistro {

	private int id;
	private String data;
	private String endereco;
	
	// Construtor
	public Sinistro(String data, String endereco) {
		this.data = data;
		this.endereco = endereco;
		
		novoID();
	}

	// Getters e Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	// Metodos
	private void novoID() {
		Random gerador = new Random();
		
		this.id = gerador.nextInt();
		System.out.print("ID do Sinistro: ");
		System.out.println(this.id);
	}
	
}
