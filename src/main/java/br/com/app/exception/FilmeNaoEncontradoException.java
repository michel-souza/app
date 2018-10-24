package br.com.app.exception;

public class FilmeNaoEncontradoException extends Exception {

	private static final long serialVersionUID = 1L;

	public FilmeNaoEncontradoException() {
		super("Filme não encontrado!");
	}
	
	public FilmeNaoEncontradoException(Throwable cause) {
		super("Filme não encontrado!", cause);
	}
}
