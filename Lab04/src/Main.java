import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Main {

	// ------------------------------------------------------------------------------------------
	// ----------------------------------------Selecionar----------------------------------------
	// ------------------------------------------------------------------------------------------

	// Seleciona a seguradora, sendo escolhida pelo input de teclado.
	static Seguradora selecionaSeguradora(List<Seguradora> listaSeguradora) {

		Seguradora seguradora = null;

		if (listaSeguradora.size() > 0) {

			System.out.println("Seguradoras disponíveis:");

			for (int i = 0; i < listaSeguradora.size(); i++)
				System.out.println(i + " - " + listaSeguradora.get(i).getNome());

			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);

			int selecionado;

			do {
				System.out.println("\nDigite uma opcao: ");
				selecionado = scanner.nextInt();
			} while (selecionado < 0 || selecionado > listaSeguradora.size() - 1);

			seguradora = listaSeguradora.get(selecionado);
		}

		else
			System.out.println("Nenhum Seguradora registrada");

		return seguradora;
	}

	// Seleciona o cliente, sendo escolhida pelo input de teclado

	static Cliente selecionaCliente(Seguradora seguradora) throws ParseException {

		List<Cliente> listaCliente = seguradora.getListaCliente();
		Cliente cliente = null;

		if (listaCliente.size() > 0) {

			System.out.println("Clientes registrados:");

			for (int i = 0; i < listaCliente.size(); i++)
				System.out.println(i + " - " + listaCliente.get(i).getNome());

			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);

			int selecionado;

			do {
				System.out.println("\nDigite uma opcao: ");
				selecionado = scanner.nextInt();
			} while (selecionado < 0 || selecionado > listaCliente.size() - 1);

			cliente = listaCliente.get(selecionado);
		}

		else
			System.out.println("Nenhum Cliente registrado");

		return cliente;

	}

	// Seleciona o Veículo, sendo escolhida pelo input de teclado.
	static Veiculo selecionaVeiculo(Cliente cliente) throws ParseException {

		List<Veiculo> listaVeiculo = cliente.getListaVeiculos();
		Veiculo veiculo = null;

		if (listaVeiculo.size() > 0) {

			System.out.println("Veiculos registrados:");

			for (int i = 0; i < listaVeiculo.size(); i++)
				System.out.print(i + " - " + listaVeiculo.get(i).toString());

			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);

			int selecionado;

			do {
				System.out.println("\nDigite uma opcao: ");
				selecionado = scanner.nextInt();
			} while (selecionado < 0 || selecionado > listaVeiculo.size() - 1);

			veiculo = listaVeiculo.get(selecionado);
		}

		else
			System.out.println("Nenhum veículo registrado");

		return veiculo;
	}

	// Seleciona o Sinistro, sendo escolhida pelo input de teclado
	static Sinistro selecionaSinistro(Seguradora seguradora) throws ParseException {

		List<Sinistro> listaSinistro = seguradora.getListaSinistro();
		Sinistro sinistro = null;

		if (listaSinistro.size() > 0) {

			System.out.println("Sinistros registrados:");

			for (int i = 0; i < listaSinistro.size(); i++)
				System.out.println(i + " - " + listaSinistro.get(i).imprimirSimplificado());

			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);

			int selecionado;

			do {
				System.out.println("\nDigite uma opcao: ");
				selecionado = scanner.nextInt();
			} while (selecionado < 0 || selecionado > listaSinistro.size() - 1);

			sinistro = listaSinistro.get(selecionado);
		}

		else
			System.out.println("Nenhum sinistros registrado");

		return sinistro;
	}

	// ------------------------------------------------------------------------------------------
	// ----------------------------------------Cadastrar-----------------------------------------
	// ------------------------------------------------------------------------------------------

	// Faz leitura do teclado e passa os atributos para cadastrar Seguradora
	static void cadastrarSeguradora(List<Seguradora> listaSeguradora) {

		@SuppressWarnings("resource")
		Scanner ler = new Scanner(System.in);

		String nome;
		String telefone;
		String email;
		String endereco;

		System.out.print("\n------------ Criar Seguradora ------------\n");

		System.out.print("Digite o nome da seguradora: ");
		nome = ler.nextLine();

		System.out.print("Digite o telefone: ");
		telefone = ler.nextLine();

		System.out.print("Digite o Email: ");
		email = ler.nextLine();

		System.out.print("Digite o endereco: ");
		endereco = ler.nextLine();

		// passa os atributos lidos para criar Seguradora
		cadastrarSeguradora(listaSeguradora, nome, telefone, email, endereco);
	}

	// Adiciona Seguro na listaSeguro atraves de parâmetros
	static void cadastrarSeguradora(List<Seguradora> listaSeguradora, String nome, String telefone, String email,
			String endereco) {

		Seguradora seguradora = new Seguradora(nome, telefone, email, endereco);

		listaSeguradora.add(seguradora);

		System.out.println("\nSeguradora cadastrado com sucesso!\n");
		System.out.println(seguradora.toString());
		System.out.println("------------------------------------------\n");
	}

	/*
	 * Faz leitura do teclado e passa os atributos para cadastrar Cliente na
	 * seguradora selecionada
	 */
	static void cadastrarCliente(List<Seguradora> listaSeguradora) throws ParseException {

		@SuppressWarnings("resource")
		Scanner ler = new Scanner(System.in);

		Seguradora seguradora = selecionaSeguradora(listaSeguradora);

		String nome;
		String endereco;

		System.out.print("\n------------ Criar Cliente ------------\n");

		System.out.print("Digite o nome: ");
		nome = ler.nextLine();

		System.out.print("Digite o endereço: ");
		endereco = ler.nextLine();

		System.out.print("Identificação do cliente: [ CPF / CNPJ ]");
		String identificacao;

		while (true) {
			identificacao = ler.nextLine();

			if (Objects.equals("CPF", identificacao) || Objects.equals("CNPJ", identificacao))
				break;
			else
				System.out.print("Tipo de identificação inválida, digite novamente: [ CPF / CNPJ ]");
		}

		if (Objects.equals("CPF", identificacao)) {
			// para cliente fisico
			String cpf;
			String genero;
			String dataLicenca;
			String educacao;
			String dataNascimento;
			String classeEconomica;

			System.out.print("Digite o CPF: [ xxx.xxx.xxx-xx ]");
			while (true) {
				cpf = ler.nextLine();

				if (Validacao.validaCPF(cpf))
					break;
				else
					System.out.print("CPF inválido, digite novamente: [ xxx.xxx.xxx-xx ]");
			}

			System.out.print("Digite o gênero: ");
			genero = ler.nextLine();

			System.out.print("Digite a data da licença [ dd/mm/yyyy ]: ");
			while (true) {
				dataLicenca = ler.nextLine();

				if (Validacao.validaData(dataLicenca))
					break;
				else
					System.out.print("Data inválida, digite novamente: [ dd/mm/yyyy ]");
			}

			System.out.print("Digite o grau de formação: ");
			educacao = ler.nextLine();

			System.out.print("Digite a data de nascimento [ dd/mm/yyyy ]: ");
			while (true) {
				dataNascimento = ler.nextLine();

				if (Validacao.validaData(dataNascimento))
					break;
				else
					System.out.print("Data inválida, digite novamente: [ dd/mm/yyyy ]");
			}

			System.out.print("Digite a classe econômica: ");
			classeEconomica = ler.nextLine();

			//
			cadastrarCliente(seguradora, nome, endereco, cpf, genero, dataLicenca, educacao, dataNascimento,
					classeEconomica);
		}

		else if (Objects.equals("CNPJ", identificacao)) {
			// para cliente juridico
			String cnpj;
			String dataFundacao;
			int qtdeFuncionarios;

			System.out.print("Digite o CNPJ: [ xx.xxx.xxx/xxxx-xx ]");
			while (true) {
				cnpj = ler.nextLine();

				if (Validacao.validaCNPJ(cnpj))
					break;
				else
					System.out.print("CNPJ inválido, digite novamente: [ xx.xxx.xxx/xxxx-xx ]");
			}

			System.out.print("Digite a data de fundação [ dd/mm/yyyy ]: ");
			while (true) {
				dataFundacao = ler.nextLine();

				if (Validacao.validaData(dataFundacao))
					break;
				else
					System.out.print("Data inválida, digite novamente: [ dd/mm/yyyy ]");
			}

			System.out.print("Digite a quantidade de fincionários: ");
			qtdeFuncionarios = ler.nextInt();
			ler.nextLine();

			//
			cadastrarCliente(seguradora, nome, endereco, cnpj, dataFundacao, qtdeFuncionarios);
		}
	}

	// cadastra cliente (PF) na seguradora
	static void cadastrarCliente(Seguradora seguradora, String nome, String endereco, String cpf, String genero,
			String dataLicenca, String educacao, String dataNascimento, String classeEconomica) throws ParseException {

		// cria novo cliente
		ClientePF clientePF = new ClientePF(nome, endereco, cpf, genero, dataLicenca, educacao, dataNascimento,
				classeEconomica);

		seguradora.cadastrarCliente(clientePF);

		System.out.println("\nCliente cadastrado com sucesso!\n");
		System.out.println(clientePF.toString());
		System.out.println("------------------------------------------\n");
	}

	// cadastra cliente (PJ) na seguradora
	static void cadastrarCliente(Seguradora seguradora, String nome, String endereco, String cnpj, String dataFundacao,
			int qtdeFuncionarios) throws ParseException {

		// cria novo cliente
		ClientePJ clientePJ = new ClientePJ(nome, endereco, cnpj, dataFundacao, qtdeFuncionarios);

		seguradora.cadastrarCliente(clientePJ);

		System.out.println("\nCliente cadastrado com sucesso!\n");
		System.out.println(clientePJ.toString());
		System.out.println("------------------------------------------\n");
	}

	// Adiciona Veiculo no cliente selecionado dentro do seguro selecionado
	static void cadastrarVeiculo(List<Seguradora> listaSeguradora) throws ParseException {

		@SuppressWarnings("resource")
		Scanner ler = new Scanner(System.in);

		Seguradora seguradora = selecionaSeguradora(listaSeguradora);
		Cliente cliente = selecionaCliente(seguradora);

		String placa;
		String marca;
		String modelo;
		int anoFabricacao;

		System.out.print("\n------------ Criar Veiculo ------------\n");

		System.out.print("Digite a placa: ");
		placa = ler.nextLine();

		System.out.print("Digite a marca: ");
		marca = ler.nextLine();

		System.out.print("Digite o modelo: ");
		modelo = ler.nextLine();

		System.out.print("Digite o ano de fabricação: ");
		anoFabricacao = Integer.parseInt(ler.nextLine());

		cadastrarVeiculo(cliente, placa, marca, modelo, anoFabricacao);
	}

	// cadastra Veiculo no cliente
	static void cadastrarVeiculo(Cliente cliente, String placa, String marca, String modelo, int anoFabricacao)
			throws ParseException {

		Veiculo veiculo = new Veiculo(placa, marca, modelo, anoFabricacao);

		cliente.cadastrarVeiculo(veiculo);

		System.out.println("\nVeiculo cadastrado com sucesso!\n");
		System.out.println(veiculo.toString());	
		System.out.println("------------------------------------------\n");
	}

	// cadastra Sinistro na seguradora
	static void cadastrarSinistro(Seguradora seguradora, Cliente cliente, Veiculo veiculo, String endereco) {
		seguradora.gerarSinistro();

		List<Sinistro> listaSinistro = seguradora.getListaSinistro();
		Sinistro sinistro = listaSinistro.get(listaSinistro.size() - 1);

		sinistro.setSeguradora(seguradora);
		sinistro.setEndereco(endereco);
		sinistro.setCliente(cliente);
		sinistro.setVeiculo(veiculo);

		System.out.println("\nSinistro cadastrado com sucesso!\n");
		System.out.println(sinistro.toString());	
		System.out.println("------------------------------------------\n");
	}

	// ------------------------------------------------------------------------------------------
	// ------------------------------------------Listar------------------------------------------
	// ------------------------------------------------------------------------------------------

	static void listarCliente(List<Seguradora> listaSeguradora) {

		Seguradora seguradora = selecionaSeguradora(listaSeguradora);

		if (seguradora != null)
			seguradora.listarClientes();
	}

	static void listarSinistroSeguradora(List<Seguradora> listaSeguradora) {

		Seguradora seguradora = selecionaSeguradora(listaSeguradora);

		if (seguradora != null)
			seguradora.listarSinistro();
	}

	static void listarSinistroCliente(List<Seguradora> listaSeguradora) throws ParseException {

		Seguradora seguradora = selecionaSeguradora(listaSeguradora);
		Cliente cliente = null;

		if (seguradora != null)
			cliente = selecionaCliente(seguradora);

		if (cliente != null) {
			if (cliente instanceof ClientePF)
				seguradora.visualizarSinistro(((ClientePF) cliente).getCpf());

			else if (cliente instanceof ClientePJ)
				seguradora.visualizarSinistro(((ClientePJ) cliente).getCnpj());
		}
	}

	static void listarVeiculoSeguradora(List<Seguradora> listaSeguradora) {

		Seguradora seguradora = selecionaSeguradora(listaSeguradora);

		if (seguradora != null)
			seguradora.listarVeiculos();
	}

	static void listarVeiculoCliente(List<Seguradora> listaSeguradora) throws ParseException {

		Seguradora seguradora = selecionaSeguradora(listaSeguradora);
		Cliente cliente = null;

		if (seguradora != null)
			cliente = selecionaCliente(seguradora);

		if (cliente != null)
			cliente.listarVeiculos();
	}

	// ------------------------------------------------------------------------------------------
	// -----------------------------------------Excluir------------------------------------------
	// ------------------------------------------------------------------------------------------

	static void excluirCliente(List<Seguradora> listaSeguradora) throws ParseException {
		System.out.println("\n------------ Excluir Cliente ------------\n");

		Seguradora seguradora = selecionaSeguradora(listaSeguradora);
		Cliente cliente = null;

		if (seguradora != null)
			cliente = selecionaCliente(seguradora);

		if (cliente != null) {

			List<Sinistro> listaSinistro = seguradora.getListaSinistro();

			if (listaSinistro.size() > 0) {
				for (Sinistro sinistro : listaSinistro) {
					if (sinistro.getCliente().equals(cliente))
						seguradora.getListaSinistro().remove(sinistro);
				}
			}

			if (cliente instanceof ClientePF)
				seguradora.removerCliente(((ClientePF) cliente).getCpf());

			else
				seguradora.removerCliente(((ClientePJ) cliente).getCnpj());

			System.out.println("\nCliente e sinistros excluidos com sucesso!");
			System.out.println("------------------------------------------\n");
		}
	}

	static void excluirVeiculo(List<Seguradora> listaSeguradora) throws ParseException {
		System.out.println("\n------------ Excluir Veiculo ------------\n");

		Seguradora seguradora = selecionaSeguradora(listaSeguradora);
		Cliente cliente = null;
		Veiculo veiculo = null;

		if (seguradora != null)
			cliente = selecionaCliente(seguradora);

		if (cliente != null)
			veiculo = selecionaVeiculo(cliente);

		if (veiculo != null) {

			List<Sinistro> listaSinistro = seguradora.procurarSinistro(cliente, veiculo);

			if (listaSinistro.size() > 0) {
				for (Sinistro sinistro : listaSinistro) {
					if (sinistro.getCliente().equals(cliente))
						seguradora.getListaSinistro().remove(sinistro);
				}
			}

			cliente.getListaVeiculos().remove(veiculo);

			System.out.println("\nVeiculo e sinistros excluidos com sucesso!");
			System.out.println("------------------------------------------\n");
		}
	}

	static void excluirSinistro(List<Seguradora> listaSeguradora) throws ParseException {
		System.out.println("\n------------ Excluir sinistro ------------\n");

		Seguradora seguradora = selecionaSeguradora(listaSeguradora);
		Sinistro sinistro = null;

		if (seguradora != null)
			sinistro = selecionaSinistro(seguradora);

		if (sinistro != null) {
			seguradora.getListaSinistro().remove(sinistro);

			System.out.println("\nSinistro excluido com sucesso!");
			System.out.println("------------------------------------------\n");
		}
	}

	// ------------------------------------------------------------------------------------------
	// ------------------------------------------Outros------------------------------------------
	// ------------------------------------------------------------------------------------------

	static void gerarSinistro(List<Seguradora> listaSeguradora) throws ParseException {

		@SuppressWarnings("resource")
		Scanner ler = new Scanner(System.in);

		System.out.println("\n------------ Gerar sinistro ------------\n");

		Seguradora seguradora = selecionaSeguradora(listaSeguradora);
		Cliente cliente = null;
		Veiculo veiculo = null;
		String endereco;

		if (seguradora != null)
			cliente = selecionaCliente(seguradora);

		if (cliente != null)
			veiculo = selecionaVeiculo(cliente);

		if (veiculo != null) {
			System.out.print("Digite o endereco: ");
			endereco = ler.nextLine();

			cadastrarSinistro(seguradora, cliente, veiculo, endereco);
		}
	}

	/*
	 * Transfere Carro e sinistros de um cliente para o outro. Caso o cliente que
	 * transfere ficar sem carro, o cliente é removido da seguradora
	 */
	static void transferirSeguradora(List<Seguradora> listaSeguradora) throws ParseException {

		System.out.println("\n------------ Transferir seguradora ------------\n");

		Seguradora seguradora = selecionaSeguradora(listaSeguradora);
		Cliente clienteTransferir = null;
		Cliente clienteReceber = null;
		Veiculo veiculoTransferir = null;

		if (seguradora != null && seguradora.getListaCliente().size() >= 2) {
			clienteTransferir = selecionaCliente(seguradora);
			clienteReceber = selecionaCliente(seguradora);

			while (clienteReceber.equals(clienteTransferir)) {
				System.out.println("Selecione um cliente diferente!");
				clienteReceber = selecionaCliente(seguradora);
			}
		}

		if (clienteTransferir != null)
			veiculoTransferir = selecionaVeiculo(clienteTransferir);

		if (veiculoTransferir != null) {

			List<Sinistro> sinistroTransferir = seguradora.procurarSinistro(clienteTransferir, veiculoTransferir);

			if (sinistroTransferir.size() > 0) {

				clienteReceber.cadastrarVeiculo(veiculoTransferir);

				for (Sinistro sinistro : sinistroTransferir) {
					sinistro.setCliente(clienteReceber);
				}

				clienteTransferir.getListaVeiculos().remove(veiculoTransferir);

				if (clienteTransferir.getListaVeiculos().size() == 0)
					seguradora.getListaCliente().remove(clienteTransferir);
			}

			seguradora.calcularPrecoSeguroCliente();

			System.out.println("\nVeiculo e sinistros transferidos com sucesso!");
			System.out.println("------------------------------------------\n");
		}
	}

	static void calcularReceitaSeguradora(List<Seguradora> listaSeguradora) {

		Seguradora seguradora = selecionaSeguradora(listaSeguradora);

		if (seguradora != null) {
			System.out.println("\n------------ Receita ------------\n");

			seguradora.calcularPrecoSeguroCliente();
			seguradora.calcularReceita();

		}
	}

	// ------------------------------------------------------------------------------------------
	// ------------------------------------------Menu--------------------------------------------
	// ------------------------------------------------------------------------------------------

	// exibir menu externo
	private static void exibirMenuExterno() {

		MenuOpcoes menuOpcoes[] = MenuOpcoes.values();

		System.out.println("Menu principal");

		for (MenuOpcoes op : menuOpcoes)
			System.out.println(op.ordinal() + " - " + op.getDescricao());

	}

	/*
	 * exibir submenus se a lista de constantes do submenu for percorrida da mesma
	 * forma que o meu externo, a opção Voltar é printada com a posição que está na
	 * lista do enum (9 - Voltar). Por isso, a lista é percorrida de forma
	 * diferente, tendo i como o inteiro correspondente. Assim, para listar o
	 * submenu de cadastros, por exemplo, vai ser printado "3 - Voltar".
	 */
	private static void exibirSubmenu(MenuOpcoes op) {

		SubmenuOpcoes[] submenu = op.getSubmenu();

		System.out.println(op.getDescricao());

		for (int i = 0; i < submenu.length; i++)
			System.out.println(i + " - " + submenu[i].getDescricao());
	}

	// ler opções do menu externo
	private static MenuOpcoes lerOpcaoMenuExterno() {

		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

		int opUsuario;
		MenuOpcoes opUsuarioConst;

		do {
			System.out.println("\nDigite uma opcao: ");
			opUsuario = scanner.nextInt();
		} while (opUsuario < 0 || opUsuario > MenuOpcoes.values().length - 1);

		opUsuarioConst = MenuOpcoes.values()[opUsuario];

		return opUsuarioConst;
	}

	// ler opção dos submenus
	private static SubmenuOpcoes lerOpcaoSubmenu(MenuOpcoes op) {

		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

		int opUsuario;

		SubmenuOpcoes opUsuarioConst;

		do {
			System.out.println("\nDigite uma opcao: ");
			opUsuario = scanner.nextInt();
		} while (opUsuario < 0 || opUsuario > op.getSubmenu().length - 1);

		opUsuarioConst = op.getSubmenu()[opUsuario];

		return opUsuarioConst;
	}

	// executar opções do menu externo
	@SuppressWarnings("incomplete-switch")
	private static void executarOpcaoMenuExterno(MenuOpcoes op, List<Seguradora> listaSeguradora)
			throws ParseException {
		switch (op) {
		case CADASTROS:
		case LISTAR:
		case EXCLUIR:
			executarSubmenu(op, listaSeguradora);
			break;

		case GERAR_SINISTRO:
			gerarSinistro(listaSeguradora);
			break;

		case TRANSFERIR_SEGURO:
			transferirSeguradora(listaSeguradora);
			break;

		case CALCULAR_RECEITA:
			calcularReceitaSeguradora(listaSeguradora);
			break;
		// case SAIR:
		}
	}

	@SuppressWarnings("incomplete-switch")
	public static void executarOpcaoSubMenu(SubmenuOpcoes opSubmenu, List<Seguradora> listaSeguradora)
			throws ParseException {
		switch (opSubmenu) {
		case CADASTRAR_CLIENTE:
			cadastrarCliente(listaSeguradora);
			break;

		case CADASTRAR_VEICULO:
			cadastrarVeiculo(listaSeguradora);
			break;

		case CADASTRAR_SEGURADORA:
			cadastrarSeguradora(listaSeguradora);
			break;

		case LISTAR_CLIENTES:
			listarCliente(listaSeguradora);
			break;

		case LISTAR_SINISTROS_SEGURADORA:
			listarSinistroSeguradora(listaSeguradora);
			break;

		case LISTAR_SINISTROS_CLIENTE:
			listarSinistroCliente(listaSeguradora);
			break;

		case LISTAR_VEICULOS_SEGURADORA:
			listarVeiculoSeguradora(listaSeguradora);
			break;

		case LISTAR_VEICULOS_CLIENTE:
			listarVeiculoCliente(listaSeguradora);
			break;

		case EXCLUIR_CLIENTE:
			excluirCliente(listaSeguradora);
			break;

		case EXCLUIR_VEICULO:
			excluirVeiculo(listaSeguradora);
			break;

		case EXCLUIR_SINISTRO:
			excluirSinistro(listaSeguradora);
			break;

		// case VOLTAR:
		// break;
		}
	}

	// executa os submenus: exibição do menu, leitura da opção e execução dos
	// métodos
	private static void executarSubmenu(MenuOpcoes op, List<Seguradora> listaSeguradora) throws ParseException {

		SubmenuOpcoes opSubmenu;

		do {
			exibirSubmenu(op);
			opSubmenu = lerOpcaoSubmenu(op);
			executarOpcaoSubMenu(opSubmenu, listaSeguradora);
		} while (opSubmenu != SubmenuOpcoes.VOLTAR);
	}

	// ------------------------------------------------------------------------------------------
	// -------------------------------------------Main-------------------------------------------
	// ------------------------------------------------------------------------------------------

	// executa o menu externo: exibição do menu, leitura da opção e execução da
	// opção
	public static void main(String[] args) throws ParseException {

		List<Seguradora> listaSeguradora = new LinkedList<Seguradora>();
		Seguradora testeSeguradora = null;
		Cliente testeCliente = null;
		Veiculo testeVeiculo = null;

		/*
		 * Instanciar pelo menos 2 objetos da classe Veiculo, 1 objeto da classe
		 * ClientePF, 1 objeto da classe ClientePJ, 1 objeto da classe Seguradora
		 */

		// Seguradora
		cadastrarSeguradora(listaSeguradora, "Seguradora de teste", "(11) 91234-5678", "teste@gmail.com",
				"Rua teste, 123");

		testeSeguradora = listaSeguradora.get(0);
		// Cliente PF
		cadastrarCliente(testeSeguradora, "Guilherme Chiang", "Rua teste, 123", "389.271.286-72", "Masculino",
				"20/03/2010", "Ensino Médio", "21/06/1999", "Média");

		// Cliente PJ
		cadastrarCliente(testeSeguradora, "Unicamp", "Rua teste, 123", "64.987.146/0001-55", "15/04/1987", 321);

		testeCliente = testeSeguradora.getListaCliente().get(0);
		cadastrarVeiculo(testeCliente, "ABC1B34", "Toyota", "Etios", 2019);

		/*
		 * Adicionar pelo menos 1 Veiculo em cada Cliente instanciado
		 */
		testeCliente = testeSeguradora.getListaCliente().get(0);
		cadastrarVeiculo(testeCliente, "BSNU62", "Fiat", "Argo", 2023);

		testeCliente = testeSeguradora.getListaCliente().get(1);
		cadastrarVeiculo(testeCliente, "APO510", "Ford", "Fiesta", 2020);

		/*
		 * Cadastrar pelo menos 1 ClientePF e 1 ClientePJ na Seguradora
		 */

		// Cliente PF
		cadastrarCliente(testeSeguradora, "Cesar Augusto", "R. Luverci Pereira de Souza, 1611", "654.123.098-60",
				"Masculino", "01/02/2003", "Ensino médio", "01/09/2010", "Média");

		// Cliente PJ
		cadastrarCliente(testeSeguradora, "Casa verde", "Av. Professor Atílio Martini, 469", "22.432.654/0001-27",
				"02/08/2016", 32);

		testeCliente = testeSeguradora.getListaCliente().get(2);
		cadastrarVeiculo(testeCliente, "SHY5N17", "Nissan", "Sentra", 2013);

		testeCliente = testeSeguradora.getListaCliente().get(3);
		cadastrarVeiculo(testeCliente, "STFE5N2", "Fiat", "Pulse", 2015);

		/*
		 * Gerar pelo menos 2 objetos Sinistro
		 */

		testeSeguradora = listaSeguradora.get(0);
		testeCliente = testeSeguradora.getListaCliente().get(2);
		testeVeiculo = testeCliente.getListaVeiculos().get(0);
		cadastrarSinistro(testeSeguradora, testeCliente, testeVeiculo, "Av. Dra. julieta Tortima, 414");

		testeSeguradora = listaSeguradora.get(0);
		testeCliente = testeSeguradora.getListaCliente().get(3);
		testeVeiculo = testeCliente.getListaVeiculos().get(0);
		cadastrarSinistro(testeSeguradora, testeCliente, testeVeiculo, "Av. Professor Atílio Martini, 469");

		/*
		 * Chamar os métodos da classe Seguradora: listarClientes();
		 * visualizarSinistro(); listarSinistros(); e calcularReceita() (veja a seçao 4)
		 */

		// Objetos selecionados para testar os métodos
		testeSeguradora = listaSeguradora.get(0);
		testeCliente = testeSeguradora.getListaCliente().get(2);
		testeVeiculo = testeCliente.getListaVeiculos().get(0);

		testeSeguradora.listarClientes("CPF");
		testeSeguradora.listarClientes("CNPJ");
		testeSeguradora.listarSinistro();
		testeSeguradora.visualizarSinistro(((ClientePF) testeCliente).getCpf());
		testeSeguradora.calcularReceita();

		/*
		 * Atualizar o atributo valorSeguro de cada cliente cadastrado na seguradora
		 * utilizando o método calcularPrecoSeguroCliente() da classe Segurador
		 */
		testeSeguradora.calcularPrecoSeguroCliente();

		/*
		 * Mostrar na tela a receita total da seguradora utilizando o m´etodo
		 * calcularReceita()
		 */
		testeSeguradora.calcularReceita();

		/*
		 * Implementar uma funç˜ao para criar o menu de operaçoes usando o enum
		 * MenuOperacoes
		 */
		// Métodos da seção menus, destacados acima

		/*
		 * chamar o menu de operaç˜oes
		 */
		MenuOpcoes op;

		do {
			exibirMenuExterno();
			op = lerOpcaoMenuExterno();
			executarOpcaoMenuExterno(op, listaSeguradora);
		} while (op != MenuOpcoes.SAIR);

		System.out.println("Saiu do sistema");
	}

}
