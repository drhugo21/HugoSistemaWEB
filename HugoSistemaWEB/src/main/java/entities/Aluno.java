package entities;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class Aluno implements Identificavel {
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "aluno_seq_gen")
	@SequenceGenerator(name = "aluno_seq_gen", sequenceName = "aluno_matricula_seq")
	@Id
	private Long matricula;
	private String nome;
	private String data;
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "ALUNO_DISCIPLINA", joinColumns = { @JoinColumn(name = "DISCIPLINA_MATRICULA") }, inverseJoinColumns = {
			@JoinColumn(name= "ALUNO_MATRICULA") })
	private Set<Disciplina> disciplinas;

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

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	Set<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(Set<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

	public Aluno() {
		super();
		// TODO Auto-generated constructor stub
	}
}
