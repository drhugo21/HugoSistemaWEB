package entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class Turma implements Identificavel{
	@GeneratedValue(generator = "turma_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "turma_seq")
	@Id
	private Long matricula;
	@OneToMany
	@JoinColumn(name = "matricula_aluno")
	private Set<Aluno> alunos;
	private String nome;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Long getMatricula() {
		return matricula;
	}
	public void setMatricula(Long matricula) {
		this.matricula = matricula;
	}
	public Set<Aluno> getAlunos() {
		return alunos;
	}
	public void setAlunos(Set<Aluno> alunos) {
		this.alunos = alunos;
	}
	public Turma() {
		super();
		// TODO Auto-generated constructor stub
	}
}