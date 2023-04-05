
public class Cliente {

	private String nome;
	private String cpf;
	private String dataNascimento;
	private int idade;
	private String endereco;
	
	// Construtor
	public Cliente(String nome, String cpf, String dataNascimento, int idade, String endereco) {
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.idade = idade;
		this.endereco = endereco;
	}

	// Getters e Setters
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	

	
	// Metodos
	public boolean validarCPF(){
				
		String subCpf = this.cpf;
		
		subCpf = subCpf.replace(".","");
		subCpf = subCpf.replace("-","");
				
		
		if ((subCpf.length() != 11) == true) {
			System.out.println("diferente de 11");
			return false;
		}
		
		else if (todosIguais(subCpf) == true) {
			System.out.println("todos os numeros sao iguais");
			return false;
		}
		
		else if (codigoVerificador(subCpf) == false) {
			
			return false;
		}
		
		else {
			this.cpf = subCpf;

			return true;
		}
			
	}	
	
	
	private boolean todosIguais(String numero){
		
	    int n = numero.length();
	    
	    for (int i = 1; i < n; i++)
	        if (numero.charAt(0) != numero.charAt(i))
	            return false;
	 
	    return true;
	}
	
	private boolean codigoVerificador(String numero) {
		
		// variavel para calcular os digitos da posicao 10 e 11
		int dig10, dig11;
		
		// variaveis auxiliares para o calculo do codigo verificador
	    int num, sm, peso;
	    
	    // Numero verificador do digito 10
	    sm = 0;
	    peso = 10;
	    for (int i = 0; i < 9; i++) {
	    	
	    	// (48 e a posicao de '0' na tabela ASCII)
	    	num = (int)(numero.charAt(i) - 48);
	    	sm = sm + (num * peso);
	    	peso--;
	    }
	    
	    
	    int resto = 11 - (sm % 11);
	    if (resto >= 10)
	    	dig10 = 0;
	    else
	    	dig10 = resto;
	    
	    
	    
	    // Numero verificador do digito 11
	    sm = 0;
	    peso = 11;
	    for (int i = 0; i < 10; i++) {
	    	
	    	// (48 e a posicao de '0' na tabela ASCII)
	    	num = (int)(numero.charAt(i) - 48);
	    	sm = sm + (num * peso);
	    	peso = peso - 1;
	    }
	    
	    resto = 11 - (sm % 11);
	    if (resto >= 10)
	    	dig11 = 0;
	    else
	    	dig11 = resto;
	    
	   
	    
	    // Comparando com o codigo verificador do input
	    if (dig10 == (int)(numero.charAt(9) - 48) && dig11 == (int)(numero.charAt(10) - 48))
	    	return true;
	    
	    else {
			System.out.print("Codigo verificador calculado: ");
			System.out.print(dig10);
			System.out.println(dig11);
			

			System.out.print("Codigo verificador do input: ");
			System.out.print(numero.charAt(9));
			System.out.println(numero.charAt(10));
			
	    	return false;
	    }
	    	
	}
}
