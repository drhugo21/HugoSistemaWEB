package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Professor implements Identificavel{
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prof_seq_gen")
	@SequenceGenerator(name = "prof_seq_gen", sequenceName = "prof_matricula_seq")
	@Id
	private Long matricula;
	private String nome;
	private String senha;
	private String login;	
	
	public Long getMatricula() {
		return matricula;
	}
	public void setMatricula(Long matricula) {
		this.matricula = matricula;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public Professor() {
		super();
		// TODO Auto-generated constructor stub
	}
}