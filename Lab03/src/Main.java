import java.text.ParseException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws ParseException {

		Seguradora seguradora = null;

		// loop das opcoes de visualizacao e input
		menuLoop(seguradora);

	}

	// metodo para simular um menu
	static void menuLoop(Seguradora seguradora) throws ParseException {

		@SuppressWarnings("resource")
		Scanner ler = new Scanner(System.in);

		if (seguradora == null) {

			seguradora = inputSeguradora();

			// perfis criados para popular a seguradora
			setup(seguradora);
		}

		else {
			String saida = "";

			saida += "Selecione uma das opções" + "\n\n";

			saida += "	[1] Cadastrar cliente" + "\n";
			saida += "	[2] Remover cliente" + "\n";
			saida += "	[3] Registrar sinistro" + "\n";
			saida += "	[4] Listar cliente (pessoa física)" + "\n";
			saida += "	[5] Listar cliente (pessoa jurídica)" + "\n";
			saida += "	[6] Listar sinistro" + "\n";
			saida += "	[7] Visualizar sinistro selecionado" + "\n\n";

			System.out.print(saida);

			System.out.print("Input: ");
			String input = ler.nextLine();

			switch (input) {

			case "1":
				inputCliente(seguradora);

				break;

			case "2":
				inputRemoverCliente(seguradora);

				break;

			case "3":
				inputSinistro(seguradora);

				break;

			case "4":
				seguradora.listarCliente("CPF");

				break;

			case "5":
				seguradora.listarCliente("CNPJ");

				break;

			case "6":
				seguradora.listarSinistros();

				break;

			case "7":
				System.out.print("\n");
				seguradora.listarClienteSimples("CPF");
				seguradora.listarClienteSimples("CNPJ");

				System.out.print("Digite o CPF ou CNPJ do cliente: ");
				String lerSinistro = ler.nextLine();
				System.out.print("\n");

				seguradora.visualizarSinistro(lerSinistro);

				break;

			}
		}

		menuLoop(seguradora);
	}

	// Cadastrar Seguradora com input do teclado
	static Seguradora inputSeguradora() {
		String nome;
		String telefone;
		String email;
		String endereco;

		@SuppressWarnings("resource")
		Scanner ler = new Scanner(System.in);

		System.out.print("Digite o nome da seguradora: ");
		nome = ler.nextLine();

		System.out.print("Digite o telefone: ");
		telefone = ler.nextLine();

		System.out.print("Digite o Email: ");
		email = ler.nextLine();

		System.out.print("Digite o endereco: ");
		endereco = ler.nextLine();

		System.out.println("-----------------------------------------\n");

		Seguradora saidaSeguradora = new Seguradora(nome, telefone, email, endereco);

		return saidaSeguradora;
	}

	// Cadastrar Cliente com input do teclado
	static void inputCliente(Seguradora seguradora) throws ParseException {
		String nome;
		String endereco;

		@SuppressWarnings("resource")
		Scanner ler = new Scanner(System.in);

		System.out.print("Digite o nome: ");
		nome = ler.nextLine();

		System.out.print("Digite o endereço: ");
		endereco = ler.nextLine();

		System.out.print("Identificação do cliente [ CPF / CNPJ ]: ");
		String identificacao = ler.nextLine();

		if (Objects.equals("CPF", identificacao)) {
			// para cliente fisico
			String cpf;
			String genero;
			String dataLicenca;
			String educacao;
			String dataNascimento;
			String classeEconomica;

			System.out.print("Digite o CPF: ");
			cpf = ler.nextLine();

			System.out.print("Digite o gênero: ");
			genero = ler.nextLine();

			System.out.print("Digite a data da licença [ dd/mm/yyyy ]: ");
			dataLicenca = ler.nextLine();

			System.out.print("Digite o grau de formação: ");
			educacao = ler.nextLine();

			System.out.print("Digite a data de nascimento [ dd/mm/yyyy ]: ");
			dataNascimento = ler.nextLine();

			System.out.print("Digite a classe econômica: ");
			classeEconomica = ler.nextLine();

			// cria novo cliente
			ClientePF clientePF = new ClientePF(nome, endereco, cpf, genero, dataLicenca, educacao, dataNascimento,
					classeEconomica);

			// Adiciona veiculo no cliente
			clientePF.cadastrarVeiculo(inputVeiculo());

			// Verifica se CNPJ é valido
			if (clientePF.validarCPF())
				System.out.println("CPF valido");

			else
				System.out.println("CPF invalido");

			// info sobre o cliente
			System.out.println(clientePF.toString());

			// Adiciona cliente para a seguradora
			seguradora.cadastrarCliente(clientePF);

		}

		else if (Objects.equals("CNPJ", identificacao)) {
			// para cliente juridico
			String cnpj;
			String dataFundacao;

			System.out.print("Digite o CNPJ: ");
			cnpj = ler.nextLine();

			System.out.print("Digite a data de fundação [ dd/mm/yyyy ]: ");
			dataFundacao = ler.nextLine();

			// cria novo cliente
			ClientePJ clientePJ = new ClientePJ(nome, endereco, cnpj, dataFundacao);

			// Adiciona veiculo no cliente
			clientePJ.cadastrarVeiculo(inputVeiculo());

			// Verifica se CNPJ é valido
			if (clientePJ.validarCNPJ())
				System.out.println("CNPJ valido");

			else
				System.out.println("CNPJ invalido");

			// info sobre o cliente
			System.out.println(clientePJ.toString());

			// Adiciona cliente para a seguradora
			seguradora.cadastrarCliente(clientePJ);

		}

	}

	// Cadastrar Veiculo com input do teclado
	static Veiculo inputVeiculo() {

		String placa;
		String marca;
		String modelo;
		int anoFabricacao;

		@SuppressWarnings("resource")
		Scanner ler = new Scanner(System.in);

		System.out.print("Digite a placa: ");
		placa = ler.nextLine();

		System.out.print("Digite a marca: ");
		marca = ler.nextLine();

		System.out.print("Digite o modelo: ");
		modelo = ler.nextLine();

		System.out.print("Digite o ano de fabricação: ");
		anoFabricacao = Integer.parseInt(ler.nextLine());

		Veiculo veiculo = new Veiculo(placa, marca, modelo, anoFabricacao);

		return veiculo;
	}

	// Remove cliente com input do teclado
	static void inputRemoverCliente(Seguradora seguradora) {

		@SuppressWarnings("resource")
		Scanner ler = new Scanner(System.in);

		seguradora.listarClienteSimples("CNPJ");
		seguradora.listarClienteSimples("CPF");

		System.out.print("Digite o CPF ou CNPJ do cliente: ");
		String identificacao = ler.nextLine();

		seguradora.removerCliente(identificacao);

	}

	// Input de teclado para cadastrar sinistro

	static void inputSinistro(Seguradora seguradora) {

		String identificacao;

		ClientePJ clientePJ = null;
		ClientePF clientePF = null;

		String endereco = null;
		Veiculo veiculo = null;

		@SuppressWarnings("resource")
		Scanner ler = new Scanner(System.in);

		System.out.print("Digite o endereço: ");
		endereco = ler.nextLine();

		System.out.print("Identificação do cliente [ CPF / CNPJ ]: ");
		identificacao = ler.nextLine();

		if (Objects.equals("CPF", identificacao)) {

			seguradora.listarCliente("CPF");

			System.out.print("Digite o CPF: ");
			String cpfInput = ler.nextLine();

			clientePF = seguradora.procurarClientePF(cpfInput);

			if (clientePF == null)
				System.out.println("Cliente não encontrado");

			else {
				System.out.print(clientePF.toString());

				System.out.print("Digite a placa do veículo: ");
				String placa = ler.nextLine();

				veiculo = clientePF.procurarVeiculo(placa);

				if (veiculo == null)
					System.out.println("Placa não encontrada");

				else
					veiculo.toString();
			}

			cadastrarSinistro(seguradora, endereco, clientePF, veiculo);

			List<Sinistro> listaSinistro = seguradora.getListaSinistro();

			System.out.print(listaSinistro.get(listaSinistro.size() - 1).toString());

		}

		else if (Objects.equals("CNPJ", identificacao)) {

			seguradora.listarCliente("CNPJ");

			System.out.print("Digite o CNPJ: ");
			String cnpjInput = ler.nextLine();

			clientePJ = seguradora.procurarClientePJ(cnpjInput);

			if (clientePJ == null)
				System.out.println("Cliente não encontrado");

			else {
				System.out.print(clientePJ.toString());

				System.out.print("Digite a placa do veículo: ");
				String placa = ler.nextLine();

				veiculo = clientePJ.procurarVeiculo(placa);

				if (veiculo == null)
					System.out.println("Placa não encontrada");

				else
					veiculo.toString();
			}

			cadastrarSinistro(seguradora, endereco, clientePJ, veiculo);

			List<Sinistro> listaSinistro = seguradora.getListaSinistro();

			System.out.print(listaSinistro.get(listaSinistro.size() - 1).toString());

		}
	}

	// Cadastrar sinistro com pessoa juridica

	static void cadastrarSinistro(Seguradora seguradora, String endereco, ClientePJ clientePJ, Veiculo veiculo) {

		seguradora.gerarSinistro();

		List<Sinistro> listaSinistro = seguradora.getListaSinistro();

		Sinistro sinistro = listaSinistro.get(listaSinistro.size() - 1);

		sinistro.setSeguradora(seguradora);
		sinistro.setEndereco(endereco);
		sinistro.setClientePJ(clientePJ);
		sinistro.setVeiculo(veiculo);

	}

	// Cadastrar sinistro com pessoa fisica

	static void cadastrarSinistro(Seguradora seguradora, String endereco, ClientePF clientePF, Veiculo veiculo) {

		seguradora.gerarSinistro();

		List<Sinistro> listaSinistro = seguradora.getListaSinistro();

		Sinistro sinistro = listaSinistro.get(listaSinistro.size() - 1);

		sinistro.setSeguradora(seguradora);
		sinistro.setEndereco(endereco);
		sinistro.setClientePF(clientePF);
		sinistro.setVeiculo(veiculo);

	}

	// Adiciona perfis de clientes e sinistros

	static void setup(Seguradora seguradora) throws ParseException {

		// -------------------------------------------------------------------------------------------------------
		// Cliente PJ 1
		// -------------------------------------------------------------------------------------------------------

		ClientePJ clienteOJ1 = new ClientePJ("zezinho do pneu", "Av. Dr. Romeu Tortima, 413", "11.444.777/0001-61",
				"17/04/2015");

		clienteOJ1.cadastrarVeiculo(new Veiculo("SLO5N12", "Toyota", "Yaris", 2010));
		clienteOJ1.cadastrarVeiculo(new Veiculo("SKI5N16", "Honda", "Civic", 2011));

		seguradora.cadastrarCliente(clienteOJ1);

		cadastrarSinistro(seguradora, "Av. Sr. Romeu Tortima, 413", clienteOJ1, clienteOJ1.procurarVeiculo("SLO5N12"));
		cadastrarSinistro(seguradora, "Av. Dra. julieta Tortima, 414", clienteOJ1,
				clienteOJ1.procurarVeiculo("SKI5N16"));

		// -------------------------------------------------------------------------------------------------------
		// Cliente PJ 2
		// -------------------------------------------------------------------------------------------------------

		ClientePJ clienteOJ2 = new ClientePJ("Casa verde", "Av. Professor Atílio Martini, 469", "22.432.654/0001-27",
				"02/08/2016");

		clienteOJ2.cadastrarVeiculo(new Veiculo("SJU5N13", "Ferrari", "Roma", 2012));

		seguradora.cadastrarCliente(clienteOJ2);

		cadastrarSinistro(seguradora, "R. Dr. Romeu Tortima, 415", clienteOJ2, clienteOJ2.procurarVeiculo("SJU5N13"));

		// -------------------------------------------------------------------------------------------------------
		// Cliente PJ 3
		// -------------------------------------------------------------------------------------------------------

		ClientePJ clienteOJ3 = new ClientePJ("Oxxinho", "R. Euríco Vanderlei Morais Carva, 08", "64.987.146/0001-55",
				"25/11/2010");

		clienteOJ3.cadastrarVeiculo(new Veiculo("SHY5N17", "Nissan", "Sentra", 2013));
		clienteOJ3.cadastrarVeiculo(new Veiculo("SGT6N16", "Audi", "A3", 2014));
		clienteOJ3.cadastrarVeiculo(new Veiculo("STFE5N2", "Fiat", "Pulse", 2015));

		seguradora.cadastrarCliente(clienteOJ3);

		// -------------------------------------------------------------------------------------------------------
		// Cliente PF 1
		// -------------------------------------------------------------------------------------------------------

		ClientePF clienteOF1 = new ClientePF("Cesar Augusto", "R. Luverci Pereira de Souza, 1611", "654.123.098-60",
				"Masculino", "01/02/2003", "Ensino médio", "01/09/2010", "Média");

		clienteOF1.cadastrarVeiculo(new Veiculo("SLO5N12", "Toyota", "Yaris", 2010));

		seguradora.cadastrarCliente(clienteOF1);

		cadastrarSinistro(seguradora, "Av. Dr. Romeu Bolima, 416", clienteOF1, clienteOF1.procurarVeiculo("SLO5N12"));
		cadastrarSinistro(seguradora, "Av. Dr. Romeu Retiima, 417", clienteOF1, clienteOF1.procurarVeiculo("SLO5N12"));
		cadastrarSinistro(seguradora, "Av. Dr. Romeu Tortima, 418", clienteOF1, clienteOF1.procurarVeiculo("SLO5N12"));

		// -------------------------------------------------------------------------------------------------------
		// Cliente PF 2
		// -------------------------------------------------------------------------------------------------------

		ClientePF clienteOF2 = new ClientePF("Armando Ferreira", "Rua Dr. Ruy Vicente de Mello, 596", "389.271.286-72",
				"Feminino", "03/06/1987", "Ensino médio", "06/06/2012", "Alta");

		clienteOF2.cadastrarVeiculo(new Veiculo("SKI5N16", "Honda", "Civic", 2011));
		clienteOF2.cadastrarVeiculo(new Veiculo("SJU5N13", "Ferrari", "Roma", 2012));
		clienteOF2.cadastrarVeiculo(new Veiculo("SHY5N17", "Nissan", "Sentra", 2013));

		seguradora.cadastrarCliente(clienteOF2);

		cadastrarSinistro(seguradora, "Av. Dr. Romeu Retiima, 417", clienteOF2, clienteOF2.procurarVeiculo("SKI5N16"));
		cadastrarSinistro(seguradora, "Av. Dr. Romeu Tortima, 418", clienteOF2, clienteOF2.procurarVeiculo("SHY5N17"));

		// -------------------------------------------------------------------------------------------------------
		// Cliente PF 3
		// -------------------------------------------------------------------------------------------------------

		ClientePF clienteOF3 = new ClientePF("Peter Mckinnon", "R. Luverci Pereira de Souza, 1100", "257.109.347-98",
				"Masculino", "24/09/2001", "Ensino médio", "21/10/2010", "Baixa");

		clienteOF3.cadastrarVeiculo(new Veiculo("SGT6N16", "Audi", "A3", 2014));
		clienteOF3.cadastrarVeiculo(new Veiculo("STFE5N2", "Fiat", "Pulse", 2015));

		seguradora.cadastrarCliente(clienteOF3);

	}
}
