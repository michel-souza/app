package br.com.app.domain;

public class FilmeVencedorException extends Exception {

	private static final long serialVersionUID = 1L;

	public FilmeVencedorException() {
		super("Filmes vencedores não podem ser excluidos!");
	}
	
	public FilmeVencedorException(Throwable cause) {
		super("Filmes vencedores não podem ser excluidos!", cause);
	}
}
