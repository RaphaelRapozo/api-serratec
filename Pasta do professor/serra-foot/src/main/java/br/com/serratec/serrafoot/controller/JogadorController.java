package br.com.serratec.serrafoot.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.serrafoot.exception.JogadorNaoEncontratoException;
import br.com.serratec.serrafoot.model.Jogador;
import br.com.serratec.serrafoot.repository.JogadorRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@Api(value = "API REST Jogadores")
@RestController
@RequestMapping("/api/jogadores")
public class JogadorController {
	
	//Aqui estamos falando para o spring instaciar o nosso repositorio automagicamente.
	@Autowired
	private JogadorRepository _repositorioJogador;
	
	@ApiOperation(value = "Retorna uma lista de jogadores")
	@GetMapping //OK
	public List<Jogador> obter(){
		//Retorna a lista de jogadores de dentro do repositorio.
		return _repositorioJogador.obterTodos();
	}
	
	@ApiOperation(value = "Retorna um jogador filtrando por Id")
	@GetMapping("/{id}") //OK
	public Optional<Jogador> obter(@PathVariable(value = "id") Integer id){
		//Recebe o id da URI ou URL e pesquisa o jogador por id na lista e retorna.
		return _repositorioJogador.obterPorId(id);
	}
	
	@ApiOperation(value = "Adiciona um jogador")
	@PostMapping //OK
	public ResponseEntity<Jogador> adicionar(@RequestBody Jogador jogador) {
		
		//Aqui estamos pedindo ao repositorio para add  o jogador na lista.
		var adicionado = this._repositorioJogador.adicionar(jogador);
		
		//Aqui estamos retornando o jogador adicionado com o statusCode 201 - Created
		return new ResponseEntity<>(adicionado, HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Atualiza um jogador")
	@PutMapping("/{id}") //OK
	public ResponseEntity<Jogador> atualizar(@PathVariable(value = "id") Integer id, @RequestBody Jogador jogador) {
		
		try {
			//Pegando o id que vem na URL e passando para o objeto jogador.
			jogador.setId(id);
			
			// Aqui estamos pedindo ao repositorio para atualizar o jogador.
			var atualizado = this._repositorioJogador.atualizar(jogador);
			
			//Devolve o jogador atualizado com o status correto.
			return new ResponseEntity<>(atualizado, HttpStatus.OK);
			
		} catch (JogadorNaoEncontratoException e) {
			//Printando uma mensagem no console, não envio ao usuário ainda.
			System.out.println(e.getMessage()); 
			
			//Eu envio um StatusCode 404-NotDound para o usuario informando que o jogador não foi encontrado.
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	@ApiOperation(value = "Deleta um jogador")
	@DeleteMapping("/{id}") //OK
	public ResponseEntity<Optional<Jogador>> deletar(@PathVariable(value = "id") Integer id){
		
		try {
			// Tento deletar o jogador por ID.
			var deletado = this._repositorioJogador.deletar(id);
			
			//Retorno para o usuário o objeto que foi deletado.
			return new ResponseEntity<Optional<Jogador>>(deletado, HttpStatus.OK);
			
		} catch (JogadorNaoEncontratoException e) {
			
			//Printar o erro no log da API, não vai para o cliente. (Opicional)
			System.out.println(e.getMessage());
			
			// Retorno um response com o status 404.
			return new ResponseEntity<Optional<Jogador>>(HttpStatus.NOT_FOUND);
		}
	}
	
}
