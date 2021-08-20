package br.com.serratec.serrafoot.repository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import br.com.serratec.serrafoot.exception.JogadorNaoEncontratoException;
import br.com.serratec.serrafoot.model.Jogador;

@Repository
public class JogadorRepository {
	//Lista glogal de jogadores que será utilizada pelos metodos.
	//Aqui essa lista simula um banco de dados.
	private LinkedList<Jogador> jogadores = new LinkedList<Jogador>();
	//Aqui criamos o nosso serial rsrsrs
	//O id que será que incremental
	private Integer idAtual = 0;
	
	public List<Jogador> obterTodos(){
		return this.jogadores;
		// Aqui vamos ir no banco e pegar todos os jogadores.
	}
	
	public Optional<Jogador> obterPorId(Integer idDoJogador) {
		//Aqui estamos filtrando uma lista para encontrar o jogador com base no id.
		//Após encontrado retornamos o primeiro resultado.
		return this.jogadores.stream()
				.filter(jogador -> jogador.getId() == idDoJogador)
				.findFirst();
	}
	
	public Jogador adicionar(Jogador jogador) {
		//Aqui estamos incrementando o ID, estamos simulando o serial.
		this.idAtual++;
		//	Adicionando o id no jogador			
		jogador.setId(idAtual);		
		// Aqui to simulando o insert no banco
		this.jogadores.add(jogador);
		//Retornando  o jogador
		return jogador;		
	}
	
	public Jogador atualizar(Jogador jogadorAtualizado) throws JogadorNaoEncontratoException {
		
		//Deleta o jogador da lista para poder atualizar.
		this.deletarJogadorNaLista(jogadorAtualizado.getId());
		
		//Estamos adicionando o novo jogador na lista atualizado.
		this.jogadores.add(jogadorAtualizado);		
		
		//Retornar o novo jogador atualizado.
		return jogadorAtualizado;
	}
	
	public Optional<Jogador> deletar(Integer idDoJogador) throws JogadorNaoEncontratoException {
		//Aqui eu pego o jogador na lista;
		//Caso ele não exista, vai lançar exception.
		Optional<Jogador> jogador = this.obterPorId(idDoJogador);
		
		//Deleto o jogador da lista.
		this.deletarJogadorNaLista(idDoJogador);
		
		//Retorno o jogador que foi deletado.
		return jogador;
	}
	
	//Criei este metodo que só vai funcionar dentro desta classe.
	//Esse metodo serve para deletar um jogador na lista.
	//Foi criado para não duplicar código, pois seu código pode estar no metodo Atualizar e Deletar.
	private void deletarJogadorNaLista(Integer idDoJogador) throws JogadorNaoEncontratoException {
		//Estamos procurando o jogador na listra para saber se existe.
		var jogador = this.obterPorId(idDoJogador);
		
		//Caso o jogador não exista, lançamos uma excpetion.
		if(jogador.isEmpty()) {
			throw new JogadorNaoEncontratoException();
		}
		
		//Aqui estamos removendo da lista com base no filtro interno de id.
		this.jogadores.removeIf(j -> j.getId() == idDoJogador);
	}
}
