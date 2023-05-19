import java.text.SimpleDateFormat;

/*
 * Define as funções matemáticas para a validação do CPF, CNPJ e do nome
 */
public class Validacao {

	// Construtor privado para evitar a criação de instâncias da classe
	private Validacao() {
	}

	// Métodos

	/*
	 * Verifica se um CPF é válido, verificando se é umposto apenas por números, se
	 * não são todos iguais, e se o código verificador é válido.
	 * 
	 * retorna verdadeiro se o cpf for válido. retorna falso caso contrário.
	 */
	public static boolean validaCPF(String cpf) {

		int digito1, digito2;

		// Remove caracteres não numéricos
		cpf = cpf.replaceAll("[^\\d]", "");

		/*
		 * explicação sobre o cpf.matches("(\\d)\\1{10}")
		 * 
		 * - (\\d) é um grupo de captura que corresponde a qualquer dígito de 0 a 9.
		 * 
		 * - \\1 é uma referência para o primeiro grupo de captura.
		 * 
		 * - {10} significa que o padrão anterior deve aparecer exatamente 10 vezes.
		 */
		if (cpf.length() != 11 || cpf.matches("(\\d)\\1{10}"))
			return false;

		// Copia os 9 primeiros dígitos do cpf
		String cpfSemDigitos = cpf.substring(0, 9);

		// Cálculo do primeiro dígito verificador
		int soma = 0;
		for (int i = 0; i < 9; i++)
			soma += Integer.parseInt(cpfSemDigitos.substring(i, i + 1)) * (10 - i);

		int resto = soma % 11;
		digito1 = resto < 2 ? 0 : 11 - resto;

		// adiciona o digito calculado na sequencia
		cpfSemDigitos += digito1;

		// Cálculo do segundo dígito verificador
		soma = 0;
		for (int i = 0; i < 10; i++)
			soma += Integer.parseInt(cpfSemDigitos.substring(i, i + 1)) * (11 - i);

		resto = soma % 11;
		digito2 = resto < 2 ? 0 : 11 - resto;

		// adiciona o digito calculado na sequencia
		cpfSemDigitos += digito2;

		return cpf.equals(cpfSemDigitos);
	}

	/*
	 * Verifica se um CNPJ é válido, verificando se é umposto apenas por números, se
	 * não são todos iguais, e se o código verificador é válido.
	 * 
	 * retorna verdadeiro se o cnpj for válido. retorna falso caso contrário.
	 */
	public static boolean validaCNPJ(String cnpj) {

		int digito1, digito2;

		// Remove caracteres não numéricos
		cnpj = cnpj.replaceAll("[^\\d]", "");

		/*
		 * explicação sobre o cpf.matches("(\\d)\\1{10}")
		 * 
		 * - (\\d) é um grupo de captura que corresponde a qualquer dígito de 0 a 9.
		 * 
		 * - \\1 é uma referência para o primeiro grupo de captura.
		 * 
		 * - {10} significa que o padrão anterior deve aparecer exatamente 10 vezes.
		 */
		if (cnpj.length() != 14 || cnpj.matches("(\\d)\\1{13}"))
			return false;

		// Copia os 12 primeiros dígitos do cnpj
		String cnpjSemDigitos = cnpj.substring(0, 12);

		// Cálculo do primeiro dígito verificador
		int soma = 0;
		for (int i = 0; i < 12; i++)
			soma += Integer.parseInt(cnpjSemDigitos.substring(i, i + 1)) * (i < 4 ? 5 - i : 13 - i);

		int resto = 11 - soma % 11;
		digito1 = resto >= 10 ? 0 : resto;

		// adiciona o digito calculado na sequencia
		cnpjSemDigitos += digito1;

		// Cálculo do segundo dígito verificador
		soma = 0;
		for (int i = 0; i < 13; i++)
			soma += Integer.parseInt(cnpjSemDigitos.substring(i, i + 1)) * (i < 5 ? 6 - i : 14 - i);

		resto = 11 - soma % 11;
		digito2 = resto >= 10 ? 0 : resto;

		// adiciona o digito calculado na sequencia
		cnpjSemDigitos += digito2;

		return cnpj.equals(cnpjSemDigitos);
	}

	/*
	 * verifica se um nome é válido, Retorna verdadeiro caso o nome seje válido,
	 * falso caso contrário
	 */
	public static boolean validaNome(String nome) {
		return !nome.matches(".*\\d+.*");
	}

	// Verifica se a data digitada é válida
	public static boolean validaData(String data) {
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			simpleDateFormat.parse(data);
			return true;
		} 
		
		catch (Exception e) {
			return false;
		}
	}
}
