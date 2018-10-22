package beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import entities.Professor;
import entities.Professor;
import services.ProfessorService;
import services.ProfessorService;

@SessionScoped
@Named
public class ProfessorBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private int matriculaProfessor;
	private Professor professor = new Professor();
	private Professor professorBuscado = new Professor();
	private Professor professorEditado;
	private List<Professor> professores;
	@Inject
	private ProfessorService professorService;
	private boolean renderPanelGridProfessorBuscado;
		
	@PostConstruct
	public void init() {
		setProfessores(professorService.getAll());
	}

	public void criarProfessor() {
		professorService.save(professor);
		professor = new Professor();
		setProfessores(getProfessorService().getAll());
	}

	public void editarProfessor() throws IOException {
		setProfessorEditado(getProfessorBuscado());
		setProfessorBuscado(new Professor());
		setRenderPanelGridProfessorBuscado(false);
		FacesContext.getCurrentInstance().getExternalContext().redirect("editarProfessor.xhtml");
	}

	public void buscarProfessor() {
		boolean encontrado = false;
		for (Professor buscaProfessor : professores) {
			if (buscaProfessor.getMatricula() == getMatriculaProfessor()) {
				setProfessorBuscado(buscaProfessor);
				encontrado = true;
				setRenderPanelGridProfessorBuscado(true);
				setMatriculaProfessor(0);
			}
		}
		if (!encontrado) {
			FacesContext.getCurrentInstance().addMessage("ERROR",
					new FacesMessage("Professor com matricula " + getMatriculaProfessor() + " nao foi encontrado"));
			setRenderPanelGridProfessorBuscado(false);
		}	
	} 

	public void removerProfessor() {
		professorService.remove(professorBuscado);
		professores = professorService.getAll();
		professorBuscado = new Professor();
		setRenderPanelGridProfessorBuscado(false);
	}
	
	public void salvarEdicao() throws IOException {
		professorService.update(professorEditado);
		professores = professorService.getAll();
		setProfessorEditado(new Professor());
		FacesContext.getCurrentInstance().getExternalContext().redirect("professorsCadastrados.xhtml");
	}

	public void salvarProfessor() {
		professor.setNome(professor.getNome());
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Professor " + professor.getNome() + "adicionado"));
		professorService.save(professor);
		professores = professorService.getAll();
		professor = new Professor();
	}

	public int getMatriculaProfessor() {
		return matriculaProfessor;
	}

	public void setMatriculaProfessor(int matriculaProfessor) {
		this.matriculaProfessor = matriculaProfessor;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public Professor getProfessorBuscado() {
		return professorBuscado;
	}

	public void setProfessorBuscado(Professor professorBuscado) {
		this.professorBuscado = professorBuscado;
	}

	public Professor getProfessorEditado() {
		return professorEditado;
	}

	public void setProfessorEditado(Professor professorEditado) {
		this.professorEditado = professorEditado;
	}

	public List<Professor> getProfessores() {
		return professores;
	}

	public void setProfessores(List<Professor> professores) {
		this.professores = professores;
	}

	public ProfessorService getProfessorService() {
		return professorService;
	}

	public void setProfessorService(ProfessorService professorService) {
		this.professorService = professorService;
	}

	public boolean isRenderPanelGridProfessorBuscado() {
		return renderPanelGridProfessorBuscado;
	}

	public void setRenderPanelGridProfessorBuscado(boolean renderPanelGridProfessorBuscado) {
		this.renderPanelGridProfessorBuscado = renderPanelGridProfessorBuscado;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
