
public class Main {

	public static void main(String[] args) {
		
		Seguradora testeSeguradora = new Seguradora( "seguradora",  "(11)91234-5678",  "seguradora@gmail.com",  "teste, 123");
		
		Sinistro testeSinistro = new Sinistro( "01/01/2023", "teste, 456");
		
		Cliente testeCliente = new Cliente( "nome",  "123.456.789-09",  "01/01/2023", 1, "teste, 012");
		//Cliente testeCliente = new Cliente( "nome",  "111.111.111-11",  "01/01/2023", 1, "teste, 012");

		Veiculo testeVeiculo = new Veiculo( "placa",  "fusca",  "fusca");
		
		
		if(testeCliente.validarCPF() == false) 
			System.out.println("CPF invalido");
		
		else
			System.out.println("CPF valido");
		
		//System.out.println(teste_cliente.getCpf());
		
	}

}
