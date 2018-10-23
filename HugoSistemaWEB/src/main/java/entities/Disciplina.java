package entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class Disciplina implements Identificavel{
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "disc_seq_gen")
	@SequenceGenerator(name = "disc_seq_gen", sequenceName = "disc_id_seq")
	@Id
	private Long matricula;
	private String nome;
	@ManyToOne
	@JoinColumn(name = "PROF_ID_DISCIPLINA")
	private Professor professorD;
	@ManyToMany(mappedBy = "disciplinas", cascade = CascadeType.ALL)
	private Set<Aluno> alunos;
	
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


	public Professor getProfessorD() {
		return professorD;
	}


	public void setProfessorD(Professor professorD) {
		this.professorD = professorD;
	}


	public Set<Aluno> getAlunos() {
		return alunos;
	}


	public void setAlunos(Set<Aluno> alunos) {
		this.alunos = alunos;
	}


	public Disciplina() {
		super();
		// TODO Auto-generated constructor stub
	}	
}
