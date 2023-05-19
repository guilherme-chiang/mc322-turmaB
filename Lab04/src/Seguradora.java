import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Seguradora {

	private String nome;
	private String telefone;
	private String email;
	private String endereco;

	private List<Sinistro> listaSinistro = new LinkedList<Sinistro>();
	private List<Cliente> listaCliente = new LinkedList<Cliente>();

	// Construtor
	public Seguradora(String nome, String telefone, String email, String endereco) {
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.endereco = endereco;
	}

	// Setters e Getters
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public List<Sinistro> getListaSinistro() {
		return listaSinistro;
	}

	public void setListaSinistro(List<Sinistro> listaSinistro) {
		this.listaSinistro = listaSinistro;
	}

	public List<Cliente> getListaCliente() {
		return listaCliente;
	}

	public void setListaCliente(List<Cliente> listaCliente) {
		this.listaCliente = listaCliente;
	}

	// Métodos

	/*
	 * Verifica se o cliente a ser cadastrado já está registrado, sendo considerado
	 * o CPF ou CNPJ
	 * 
	 * retorna true caso tenha sido adicionado, false caso contrário
	 */
	public boolean cadastrarCliente(Cliente cliente) {

		if (cliente instanceof ClientePF) {
			if (procurarCliente(((ClientePF) cliente).getCpf()) != null)
				return false;
		}

		else if (cliente instanceof ClientePJ) {
			if (procurarCliente(((ClientePJ) cliente).getCnpj()) != null)
				return false;
		}

		this.listaCliente.add(cliente);

		return true;
	}

	/*
	 * Remove os sinistros relacionados ao liente, e o próprio cliente também, sendo
	 * considerado o CPF ou CNPJ para a procura
	 * 
	 * retorna true caso tenha sido removido, false caso contrário
	 */
	public boolean removerCliente(String cliente) {

		if (listaCliente.remove(procurarCliente(cliente)) == false)
			return false;

		for (int i = listaSinistro.size() - 1; i >= 0; i--) {

			Cliente clienteEncontrado = listaSinistro.get(i).getCliente();

			if (clienteEncontrado instanceof ClientePF) {
				if (((ClientePF) clienteEncontrado).getCpf().equals(cliente))
					listaSinistro.remove(i);
			}

			else if (clienteEncontrado instanceof ClientePJ) {
				if (((ClientePJ) clienteEncontrado).getCnpj().equals(cliente))
					listaSinistro.remove(i);
			}
		}

		return true;
	}

	// Imprime a lista de clientes de cliente CPF ou CNPJ
	public void listarClientes() {

		if (listaCliente.size() > 0) {
			@SuppressWarnings("resource")
			Scanner ler = new Scanner(System.in);

			System.out.print("Identificação do cliente: [ CPF / CNPJ ]");
			String identificacao;

			while (true) {
				identificacao = ler.nextLine();

				if (Objects.equals("CPF", identificacao) || Objects.equals("CNPJ", identificacao))
					break;

				else
					System.out.print("Tipo de identificação inválida, digite novamente: [ CPF / CNPJ ]");
			}

			// imprime descrição do que está sendo listado
			System.out.print("\nLista de clientes ");

			if (Objects.equals("CPF", identificacao))
				System.out.print("(pessoa física) ");
			else
				System.out.print("(pessoa jurídica) ");

			System.out.println("da seguradora " + nome);

			// lista de clientes
			for (Cliente cliente : listaCliente) {
				if (Objects.equals("CPF", identificacao) && cliente instanceof ClientePF)
					System.out.println(cliente.toString());

				else if (Objects.equals("CNPJ", identificacao) && cliente instanceof ClientePJ)
					System.out.println(cliente.toString());
			}
		}

		else
			System.out.println("\nNão há Cliente registrado\n");

		System.out.println("------------------------------------------\n");
	}

	// Imprime a lista de clientes de cliente CPF ou CNPJ
	public void listarClientes(String identificacao) {

		if (listaCliente.size() > 0) {

			// imprime descrição do que está sendo listado
			System.out.print("\nLista de clientes ");

			if (Objects.equals("CPF", identificacao))
				System.out.print("(pessoa física) ");
			else
				System.out.print("(pessoa jurídica) ");

			System.out.println("da seguradora " + nome);

			// lista de clientes
			for (Cliente cliente : listaCliente) {
				if (Objects.equals("CPF", identificacao) && cliente instanceof ClientePF)
					System.out.println(cliente.toString());

				else if (Objects.equals("CNPJ", identificacao) && cliente instanceof ClientePJ)
					System.out.println(cliente.toString());
			}
		}

		else
			System.out.println("\nNão há Cliente registrado\n");
		
		System.out.println("------------------------------------------\n");
	}
	
	/*
	 * gera sinistro para o cliente
	 * 
	 * retorna true caso tenha sido adicionado, false caso contrário
	 */
	public boolean gerarSinistro() {

		Sinistro sinistro = new Sinistro();

		if (procurarID(sinistro.getId()))
			return false;

		this.listaSinistro.add(new Sinistro());

		return true;
	}

	/*
	 * Imprime a lista de veiculos completa
	 */
	public void listarVeiculos() {

		// imprime descrição do que está sendo listado
		System.out.println("\nLista de Veiculos registrados na seguradora " + nome + "\n");

		if (listaCliente.size() > 0) {

			for (Cliente cliente : listaCliente) {
				
				List<Veiculo> listaVeiculos = cliente.getListaVeiculos();
				
				if (listaVeiculos.size() > 0) {
					for (Veiculo veiculo : listaVeiculos)
						System.out.println(veiculo.toString());
				}
			}
		}
		
		else
			System.out.println("\nNão há cliente registrado\n");
		
		System.out.println("------------------------------------------\n");
	}

	/*
	 * Imprime uma lista de sinistros relacionadas ao cliente
	 */
	public void visualizarSinistro(String cliente) {

		Cliente clienteEncontrado = procurarCliente(cliente);
		List<Sinistro> sinistroEncontrados = procurarSinistro(clienteEncontrado);

		// imprime descrição do que está sendo listado
		System.out.print("\nLista de sinistros do cliente " + clienteEncontrado.getNome());
		System.out.println(" registrados na seguradora " + nome + "\n");

		if (sinistroEncontrados == null) {
			System.out.println("Nenhum sinistro encontrado\n");
		}

		else {
			System.out.println("Sinistro do cliente " + cliente + ":");

			for (Sinistro sinistro : sinistroEncontrados)
				System.out.println(sinistro.toString());
		}
	}

	/*
	 * Imprime a lista de Sinistro completa
	 */
	public void listarSinistro() {

		// imprime descrição do que está sendo listado
		System.out.println("\nLista de sinistros registrados da seguradora " + nome + "\n");

		if (listaSinistro.size() > 0) {
			for (Sinistro sinistro : listaSinistro)
				System.out.println(sinistro.toString());
		}

		else
			System.out.println("\nNão há sinistro registrado\n");
		
		System.out.println("------------------------------------------\n");
	}

	// Atualiza o preço do seguro de todos os clientes
	public void calcularPrecoSeguroCliente() {

		for (Cliente clienteEncontrado : listaCliente) {

			int qtdeSinistro = procurarSinistro(clienteEncontrado).size();

			clienteEncontrado.setValorSeguro(clienteEncontrado.calculaScore() * (1 + qtdeSinistro));
		}
	}

	// Calcula e imprime a receita da seguradora
	public void calcularReceita() {

		calcularPrecoSeguroCliente();
		
		double receita = 0;

		for (Cliente cliente : listaCliente) {
			receita += cliente.getValorSeguro();
		}

		System.out.println("\nReceita da seguradora: " + nome + " = R$" + receita + "\n");
		System.out.println("------------------------------------------\n");
	}

	/*
	 * função procura por clientes a partir do parâmetro, que pode ser um CPF ou
	 * CNPJ
	 */
	private Cliente procurarCliente(String cliente) {

		for (Cliente clienteEncontrado : listaCliente) {

			if (clienteEncontrado instanceof ClientePF) {
				if (((ClientePF) clienteEncontrado).getCpf().equals(cliente))
					return clienteEncontrado;
			}

			else if (clienteEncontrado instanceof ClientePJ) {
				if (((ClientePJ) clienteEncontrado).getCnpj().equals(cliente))
					return clienteEncontrado;
			}
		}

		return null;
	}

	/*
	 * função procura por sinistros do cliente a partir de um dado cliente
	 */
	private List<Sinistro> procurarSinistro(Cliente cliente) {

		List<Sinistro> listaEncontrados = new LinkedList<Sinistro>();

		for (Sinistro sinistro : listaSinistro) {

			Cliente clienteEncontrado = sinistro.getCliente();

			if (clienteEncontrado.equals(cliente))
				listaEncontrados.add(sinistro);
		}

		return listaEncontrados;
	}

	/*
	 * função procura por sinistros do cliente a partir de um dado cliente e veiculo
	 */
	public List<Sinistro> procurarSinistro(Cliente cliente, Veiculo veiculo) {

		List<Sinistro> listaEncontrados = new LinkedList<Sinistro>();

		for (Sinistro sinistro : listaSinistro) {

			Cliente clienteEncontrado = sinistro.getCliente();
			Veiculo veiculoEncontrado = sinistro.getVeiculo();
			

			if (clienteEncontrado.equals(cliente) && veiculoEncontrado.equals(veiculo))
				listaEncontrados.add(sinistro);
		}

		return listaEncontrados;
	}
	
	/*
	 * Verifica se um ID do sinistro já existe
	 * 
	 * retorna true caso tenha encontrado, false caso contrário
	 */
	private boolean procurarID(int id) {

		for (Sinistro sinistro : listaSinistro) {

			int idEncontrado = sinistro.getId();

			if (idEncontrado == id)
				return true;
		}

		return false;
	}

	
	@Override
	public String toString() {
		String saida;
		
		saida = "Seguradora: " + nome + "\n";
		saida += "	Telefone: " + telefone + "\n";
		saida += "	E-mail: " + email + "\n";
		saida += "	Endereco: " + endereco + "\n";
		
		
		return saida;
	}

	
}
