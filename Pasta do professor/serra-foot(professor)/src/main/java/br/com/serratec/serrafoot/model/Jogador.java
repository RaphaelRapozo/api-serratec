package br.com.serratec.serrafoot.model;

public class Jogador {
	private Integer id;
	private String nome;
	private Integer idade;
	private Double salario;
		
	public Jogador() {}
		
	public Jogador(Integer id, String nome, Integer idade, Double salario) {
		this.id = id;
		this.nome = nome;
		this.idade = idade;
		this.salario = salario;
	}
	
	public Jogador(String nome, Integer idade, Double salario) {
		this.nome = nome;
		this.idade = idade;
		this.salario = salario;
	}


	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Integer getIdade() {
		return idade;
	}
	
	public void setIdade(Integer idade) {
		this.idade = idade;
	}
	
	public Double getSalario() {
		return salario;
	}
	
	public void setSalario(Double salario) {
		this.salario = salario;
	}

	@Override
	public String toString() {
		return "Jogador [id=" + id + ", nome=" + nome + ", idade=" + idade + ", salario=" + salario + ", getId()="
				+ getId() + ", getNome()=" + getNome() + ", getIdade()=" + getIdade() + ", getSalario()=" + getSalario()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
	
	
	
}
