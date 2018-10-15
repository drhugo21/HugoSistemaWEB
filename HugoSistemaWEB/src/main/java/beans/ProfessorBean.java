package beans;

import java.io.Serializable;
import java.util.Set;

import javax.faces.bean.SessionScoped;
import javax.inject.Named;

import entities.Aluno;
import entities.Professor;

@SessionScoped
@Named
public class ProfessorBean implements Serializable {
	private Professor professor = new Professor();
	private Set<Professor> professores;
	public Professor getProfessor() {
		return professor;
	}
	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	public Set<Professor> getProfessores() {
		return professores;
	}
	public void setProfessores(Set<Professor> professores) {
		this.professores = professores;
	}
	public ProfessorBean() {
		super();
	}
}
