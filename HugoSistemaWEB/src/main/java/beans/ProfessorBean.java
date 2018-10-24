package beans;

import java.io.IOException;
import java.io.Serializable;
import java.security.Principal;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
	private String confirmarSenha;	
		
	@PostConstruct
	public void init() {
		setProfessores(professorService.getAll());
	}
	
	public String getUserLogin() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		Principal userPrincipal = externalContext.getUserPrincipal();
		if (userPrincipal == null) {
			return "Login necessário para usar o sistema";
		}
		return "Bem vindo, "+userPrincipal.getName();
	}

	public void efetuarLogout() throws IOException, ServletException {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpSession session = (HttpSession) ec.getSession(false);
		session.invalidate();
		HttpServletRequest request = (HttpServletRequest) ec.getRequest();
		request.logout();
		ec.redirect(ec.getApplicationContextPath());
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
		if (!professor.getSenha().equals(confirmarSenha)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("ERROR", "Senhas diferentes!"));
		} else {
			boolean sameLogin = false;
			for (Professor p : professores) {
				if (professor.getLogin().equals(p.getLogin())) {
					sameLogin = true;
				}
			}
			if (sameLogin) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("ERROR", "Professor já está cadastrado"));
			} else {
				professor.setNome(professor.getNome());
				FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Professor " + professor.getNome() + "adicionado"));
				professorService.save(professor);
				professores = professorService.getAll();
				professor = new Professor();		}
		}
	}
	
	public boolean isUserInRole(String role) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		return externalContext.isUserInRole(role);
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

	public String getConfirmarSenha() {
		return confirmarSenha;
	}

	public void setConfirmarSenha(String confirmarSenha) {
		this.confirmarSenha = confirmarSenha;
	}
}
