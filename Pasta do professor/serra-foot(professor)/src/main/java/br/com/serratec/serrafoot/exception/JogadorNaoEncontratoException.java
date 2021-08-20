package br.com.serratec.serrafoot.exception;

public class JogadorNaoEncontratoException extends Exception {

	public JogadorNaoEncontratoException() {
		super("Jogador não encontrado.");
	}

	public JogadorNaoEncontratoException(String message) {
		super(message);
	}	
}
