package beans;

import java.io.IOException;
import java.io.Serializable;
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
@Named
public class DisciplinaBean implements Serializable {
	private Disciplina disciplina = new Disciplina();
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
	private Disciplina disciplinaMatriculaAluno;
	private Disciplina disciplinaBuscada;
	
	@PostConstruct
	public void init() {
		setDisciplinaService(new DisciplinaService());
		disciplinas = (Set<Disciplina>) disciplinaService.getAll();
	}

	public void createDisciplina() {
		disciplinaService.save(disciplina);
		disciplina = new Disciplina();
	}

	public void editarDisciplina() throws IOException {
		setDisciplinaBuscada(getDisciplinaBuscada());
		setDisciplinaBuscada(new Disciplina());
//		setRenderPanelGridAlunoBuscado(false);
		FacesContext.getCurrentInstance().getExternalContext().redirect("editarAluno.xhtml");
	}

	public Disciplina buscarDisciplina(Long id) {
		return disciplinaService.getByID(id);
		}

	public void removerDisciplina() {
		disciplinaService.remove(alunoBuscado);
		alunos = (Set<Aluno>) alunoService.getAll();
		alunoBuscado = new Aluno();
//		setRenderPanelGridAlunoBuscado(false);
	}

	public void salvarEdicao() throws IOException {
		alunoService.update(alunoBuscado);
		alunos = (Set<Aluno>) alunoService.getAll();
		setAlunoBuscado(new Aluno());
		FacesContext.getCurrentInstance().getExternalContext().redirect("cadastrados.xhtml");
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

	public Disciplina getDisciplinaMatriculaAluno() {
		return disciplinaMatriculaAluno;
	}

	public void setDisciplinaMatriculaAluno(Disciplina disciplinaMatriculaAluno) {
		this.disciplinaMatriculaAluno = disciplinaMatriculaAluno;
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
	public void setDisciplinas(Set<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}
	public Disciplina getDisciplinaBuscada() {
		return disciplinaBuscada;
	}

	public void setDisciplinaBuscada(Disciplina disciplinaBuscada) {
		this.disciplinaBuscada = disciplinaBuscada;
	}

	public DisciplinaBean() {
		super();
	}
}
