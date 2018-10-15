package beans;

import java.io.Serializable;
import java.util.Set;

import javax.faces.bean.SessionScoped;
import javax.inject.Named;

import entities.Aluno;
import entities.Turma;

@SessionScoped
@Named
public class TurmaBean implements Serializable {
	private Turma turma = new Turma();
	private Set<Turma> turmas;
	public Turma getTurma() {
		return turma;
	}
	public void setTurma(Turma turma) {
		this.turma = turma;
	}
	public Set<Turma> getTurmas() {
		return turmas;
	}
	public void setTurmas(Set<Turma> turmas) {
		this.turmas = turmas;
	}
	public TurmaBean() {
		super();
	}
}
