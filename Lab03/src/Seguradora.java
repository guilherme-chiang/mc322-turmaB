import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Seguradora {

	private String nome;
	private String telefone;
	private String email;
	private String endereco;

	private List<Sinistro> listaSinistro = new LinkedList<Sinistro>();
	private List<ClientePF> listaClientePF = new LinkedList<ClientePF>();
	private List<ClientePJ> listaClientePJ = new LinkedList<ClientePJ>();

	// Construtor
	public Seguradora(String nome, String telefone, String email, String endereco) {
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.endereco = endereco;
	}

	// Getters e Setters
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
		return Collections.unmodifiableList(listaSinistro);
	}

	public void setListaSinistro(List<Sinistro> listaSinistro) {
		this.listaSinistro = listaSinistro;
	}

	public List<ClientePF> getListaClientePF() {
		return Collections.unmodifiableList(listaClientePF);
	}

	public void setListaClientePF(List<ClientePF> listaClientePF) {
		this.listaClientePF = listaClientePF;
	}

	public List<ClientePJ> getListaClientePJ() {
		return Collections.unmodifiableList(listaClientePJ);
	}

	public void setListaClientePJ(List<ClientePJ> listaClientePJ) {
		this.listaClientePJ = listaClientePJ;
	}

	// Metodos

	public boolean cadastrarCliente(ClientePF clientePF) {

		this.listaClientePF.add(clientePF);

		return true;
	}

	public boolean cadastrarCliente(ClientePJ clientePJ) {

		this.listaClientePJ.add(clientePJ);

		return true;
	}

	public boolean removerCliente(String cliente) {

		String saida, tipoCliente;
		boolean achou = false;
		int i = 0;

		// Verifica se e CPF ou CNPJ
		tipoCliente = cpfOuCnpj(cliente);

		if (Objects.equals("CPF", tipoCliente)) {

			// Procura por CPF na listaClientePF
			for (i = 0; i < listaClientePF.size(); i++) {
				String cpf = listaClientePF.get(i).getCpf();

				if (Objects.equals(cliente, cpf)) {
					achou = true;
					break;
				}

			}

			// Caso encontre o CPF na lista, remove o sinistro associado e o cliente
			if (achou) {

				// Remover sinistros relacionados ao cliente
				int listaSinistroPF[] = acharSinistroPF(cliente);

				saida = "Sinistro do cliente " + cliente + " removido(s):\n";

				if (listaSinistroPF != null) {

					for (int j = listaSinistroPF.length - 1; j >= 0; j--) {
						saida += "	" + listaSinistro.get(listaSinistroPF[j]).getId() + "\n";
						listaSinistro.remove(listaSinistroPF[j]);
					}
				}

				else
					saida += "	Nenhum\n";

				
				// Remover cliente
				listaClientePF.remove(i);

				saida += "\nCliente " + cliente + " removido\n";
			}

			else
				saida = "Cliente com CPF " + cliente + " não foi encontrado";

		}

		else if (Objects.equals("CNPJ", tipoCliente)) {

			// Procura por CNPJ na listaClientePJ
			for (i = 0; i < listaClientePJ.size(); i++) {

				String cnpj = listaClientePJ.get(i).getCnpj();

				if (Objects.equals(cliente, cnpj)) {
					achou = true;
					break;
				}
			}

			// Caso encontre o CNPJ na lista, remove o sinistro associado e o cliente
			if (achou) {

				// Remover sinistros relacionados ao cliente
				int listaSinistroPJ[] = acharSinistroPJ(cliente);

				saida = "Sinistro do cliente " + cliente + " removido(s):\n";

				if (listaSinistroPJ != null) {
					
					for (int j = listaSinistroPJ.length - 1; j >= 0; j--) {
						saida += "	" + listaSinistro.get(listaSinistroPJ[j]).getId() + "\n";
						listaSinistro.remove(listaSinistroPJ[j]);
					}
				}

				else
					saida += "	Nenhum\n";

				// Remover cliente
				listaClientePJ.remove(i);

				saida += "\nCliente " + cliente + " removido\n";
			}

			else
				saida = "Cliente com CNPJ " + cliente + " não foi encontrado";

		}

		else
			saida = "CPF ou CNPJ inválido";

		System.out.println(saida);

		if (achou == true)
			return true;

		else
			return false;

	}

	public void listarClienteSimples(String tipoCliente) {

		String saida;
		int i = 0;

		if (Objects.equals("CPF", tipoCliente)) {

			saida = "Clientes (pessoa física) registrados:\n";

			if (listaClientePF != null) {
				for (i = 0; i < listaClientePF.size(); i++) {
					saida += "	" + listaClientePF.get(i).getNome();
					saida += ", CPF: " + listaClientePF.get(i).getCpf() + "\n";
				}
			}

			if (i == 0)
				saida += "	Nenhum\n";
		}

		else if (Objects.equals("CNPJ", tipoCliente)) {

			saida = "Clientes (pessoa jurídica) registrados:\n";

			if (listaClientePJ != null) {
				for (i = 0; i < listaClientePJ.size(); i++) {
					saida += "	" + listaClientePJ.get(i).getNome();
					saida += ", CNPJ: " + listaClientePJ.get(i).getCnpj() + "\n";
				}
			}

			if (i == 0)
				saida += "	Nenhum\n";
		}

		else
			saida = "tipo de cliente inválido\n";

		System.out.println(saida);
	}

	public void listarCliente(String tipoCliente) {

		String saida;
		int i = 0;

		if (Objects.equals("CPF", tipoCliente)) {

			saida = "Clientes (pessoa física) registrados:\n";

			if (listaClientePF != null) {
				for (i = 0; i < listaClientePF.size(); i++) 
					saida += "	" + listaClientePF.get(i).toString() + "\n";
				
			}

			if (i == 0)
				saida += "	Nenhum\n";
		}

		else if (Objects.equals("CNPJ", tipoCliente)) {

			saida = "Clientes (pessoa jurídica) registrados:\n";

			if (listaClientePJ != null) {
				for (i = 0; i < listaClientePJ.size(); i++) 
					saida += "	" + listaClientePJ.get(i).toString() + "\n";
				
			}

			if (i == 0)
				saida += "	Nenhum\n";
		}

		else
			saida = "tipo de cliente inválido\n";

		System.out.println(saida);
	}
	
	public ClientePF procurarClientePF(String cliente) {

		// Procura por CPF na listaClientePF
		for (int i = 0; i < listaClientePF.size(); i++) {
			String cpf = listaClientePF.get(i).getCpf();

			if (Objects.equals(cliente, cpf))
				return listaClientePF.get(i);

		}

		return null;
	}

	public ClientePJ procurarClientePJ(String cliente) {

		// Procura por CNPJ na listaClientePJ
		for (int i = 0; i < listaClientePJ.size(); i++) {
			String cnpj = listaClientePJ.get(i).getCnpj();

			if (Objects.equals(cliente, cnpj))
				return listaClientePJ.get(i);

		}

		return null;
	}

	public boolean gerarSinistro() {

		this.listaSinistro.add(new Sinistro());

		return false;
	}

	public boolean visualizarSinistro(String cliente) {

		String saida, tipoCliente;
		boolean achou = false;
		int i = 0;

		tipoCliente = cpfOuCnpj(cliente);

		if (Objects.equals("CPF", tipoCliente)) {

			int listaSinistroPF[] = acharSinistroPF(cliente);

			saida = "Sinistro do cliente" + cliente + ":\n";

			if (listaSinistroPF != null) {
				for (i = listaSinistroPF.length - 1; i >= 0; i--) {
					saida += "	" + listaSinistro.get(listaSinistroPF[i]).getId() + "\n";
				}

				achou = true;
			}

			if (i == 0)
				saida += "	Nenhum\n";

		}

		else if (Objects.equals("CNPJ", tipoCliente)) {

			int listaSinistroPJ[] = acharSinistroPJ(cliente);

			saida = "Sinistro do cliente" + cliente + ":\n";

			if (listaSinistroPJ != null) {
				for (i = listaSinistroPJ.length - 1; i >= 0; i--) {
					saida += listaSinistro.get(listaSinistroPJ[i]).toString();
				}

				achou = true;
			}

			if (i == 0)
				saida += "	Nenhum\n";

		}

		else
			saida = "CPF ou CNPJ inválidos";

		System.out.println(saida);

		if (achou == true)
			return true;

		else
			return false;

	}

	public void listarSinistros() {

		String saida = "ID dos sinistros registrados:\n";

		int i = 0;

		if (listaSinistro != null) {
			for (i = 0; i < listaSinistro.size(); i++) {
				saida += listaSinistro.get(i).toString();
			}

		}

		if (i == 0)
			saida += "nenhum\n";

		System.out.println(saida);
	}

	private String cpfOuCnpj(String cliente) {

		// Funcao pra identificar se o cliente é pessoa fisica ou juridica

		cliente = cliente.replace(".", "");
		cliente = cliente.replace("-", "");
		cliente = cliente.replace("/", "");

		if (cliente.length() == 11)
			return "CPF";

		else if (cliente.length() == 14)
			return "CNPJ";

		else
			return "invalido";
	}

	private static int[] add_int(int array[], int ele) {

		int i, novoArray[];

		if (array != null)
			novoArray = new int[array.length + 1];
		else
			novoArray = new int[1];

		
		// copia array para o novo array
		for (i = 0; i < novoArray.length - 1; i++)
			novoArray[i] = array[i];

		// adiciona o elemento novo no final
		novoArray[i] = ele;

		return novoArray;
	}

	private int[] acharSinistroPF(String cpfAlvo) {

		int listaSinistroPF[] = null;

		if (listaSinistro != null) {

			for (int i = 0; i < listaSinistro.size(); i++) {

				if (listaSinistro.get(i).getClientePF() != null) {
					String cpf = listaSinistro.get(i).getClientePF().getCpf();

					if (Objects.equals(cpfAlvo, cpf)) 
						listaSinistroPF = add_int(listaSinistroPF, i);

					
				}
			}
		}

		return listaSinistroPF;
	}

	private int[] acharSinistroPJ(String cnpjAlvo) {

		int listaSinistroPJ[] = null;

		if (listaSinistro != null) {

			for (int i = 0; i < listaSinistro.size(); i++) {

				if (listaSinistro.get(i).getClientePJ() != null) {
					String cnpj = listaSinistro.get(i).getClientePJ().getCnpj();

					if (Objects.equals(cnpjAlvo, cnpj)) 
						listaSinistroPJ = add_int(listaSinistroPJ, i);
					
				}
			}
		}

		return listaSinistroPJ;
	}

}
