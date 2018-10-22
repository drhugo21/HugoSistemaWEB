package beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import entities.Aluno;
import entities.Disciplina;
import services.AlunoService;
import services.DisciplinaService;
import services.ProfessorService;

@SessionScoped
@Named(value = "disciplinaBean")
public class DisciplinaBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long matriculaDisciplina;
	private Disciplina disciplina = new Disciplina();
	private Disciplina disciplinaBuscado = new Disciplina();
	private Disciplina disciplinaEditado;
	private Set<Disciplina> disciplinas;
	@Inject
	private DisciplinaService disciplinaService;
	@Inject
	private ProfessorService professorService;
	@Inject
	@ManagedProperty(value = "{professorBean}")
	private ProfessorBean professorBean;
	@Inject
	private AlunoService alunoService;
	private Disciplina disciplinaBuscada;
	private boolean renderPanelGridDisciplinaBuscado;
	
	@PostConstruct
	public void init() {
		setDisciplinas(disciplinaService.getAll());
	}

	public void criarDisciplina() {
		disciplinaService.save(disciplina);
		disciplina = new Disciplina();
		setDisciplinas(getDisciplinaService().getAll());
	}

	public void editarDisciplina() throws IOException {
		setDisciplinaBuscada(getDisciplinaBuscada());
		setDisciplinaBuscada(new Disciplina());
		setRenderPanelGridDisciplinaBuscado(false);
		FacesContext.getCurrentInstance().getExternalContext().redirect("editarAluno.xhtml");
	}

	public void buscarDisciplina() {
		boolean encontrado = false;
		for (Disciplina buscaDisciplina : disciplinas) {
			if (buscaDisciplina.getMatricula() == getMatriculaDisciplina()) {
				setDisciplinaBuscada(buscaDisciplina);
				encontrado = true;
				setRenderPanelGridDisciplinaBuscado(true);
				setMatriculaDisciplina(0L);
			}
		}
		if (!encontrado) {
			FacesContext.getCurrentInstance().addMessage("ERROR",
					new FacesMessage("Disciplina com matricula " + getMatriculaDisciplina() + " nao foi encontrado"));
			setRenderPanelGridDisciplinaBuscado(false);
		}			
	}

	public void removerDisciplina() {
		disciplinaService.remove(disciplinaBuscado);
		disciplinas = (Set<Disciplina>) disciplinaService.getAll();
		disciplinaBuscado = new Disciplina();
		setRenderPanelGridDisciplinaBuscado(false);
	}
	
	public void salvarEdicao() throws IOException {
		disciplinaService.update(disciplinaEditado);
		disciplinas = (Set<Disciplina>) disciplinaService.getAll();
		setDisciplinaEditado(new Disciplina());
		FacesContext.getCurrentInstance().getExternalContext().redirect("disciplinasCadastrados.xhtml");
	}
	
	public void salvarPc() {
		disciplina.setNomeD(disciplina.getNomeD());
		disciplina.setProfessorD(disciplina.getProfessorD());
		disciplina.setAlunos(disciplina.getAlunos());
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Disciplina " + disciplina.getNomeD() + "adicionado"));
		disciplinaService.save(disciplina);
		disciplinas = (Set<Disciplina>) disciplinaService.getAll();
		disciplina = new Disciplina();
	}
	
	public DisciplinaService getDisciplinaService() {
		return disciplinaService;
	}

	public void setDisciplinaService(DisciplinaService disciplinaService) {
		this.disciplinaService = disciplinaService;
	}

	public ProfessorService getProfessorService() {
		return professorService;
	}

	public void setProfessorService(ProfessorService professorService) {
		this.professorService = professorService;
	}

	public ProfessorBean getProfessorBean() {
		return professorBean;
	}

	public void setProfessorBean(ProfessorBean professorBean) {
		this.professorBean = professorBean;
	}

	public AlunoService getAlunoService() {
		return alunoService;
	}

	public void setAlunoService(AlunoService alunoService) {
		this.alunoService = alunoService;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}
	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
	public Set<Disciplina> getDisciplinas() {
		return disciplinas;
	}
	public void setDisciplinas(List<Disciplina> list) {
		this.disciplinas = (Set<Disciplina>) list;
	}
	public Disciplina getDisciplinaBuscada() {
		return disciplinaBuscada;
	}

	public void setDisciplinaBuscada(Disciplina disciplinaBuscada) {
		this.disciplinaBuscada = disciplinaBuscada;
	}

	public boolean isRenderPanelGridDisciplinaBuscado() {
		return renderPanelGridDisciplinaBuscado;
	}

	public void setRenderPanelGridDisciplinaBuscado(boolean renderPanelGridDisciplinaBuscado) {
		this.renderPanelGridDisciplinaBuscado = renderPanelGridDisciplinaBuscado;
	}

	public Long getMatriculaDisciplina() {
		return matriculaDisciplina;
	}

	public void setMatriculaDisciplina(Long matriculaDisciplina) {
		this.matriculaDisciplina = matriculaDisciplina;
	}

	public Disciplina getDisciplinaEditado() {
		return disciplinaEditado;
	}

	public void setDisciplinaEditado(Disciplina disciplinaEditado) {
		this.disciplinaEditado = disciplinaEditado;
	}

	public DisciplinaBean() {
		super();
	}
}
