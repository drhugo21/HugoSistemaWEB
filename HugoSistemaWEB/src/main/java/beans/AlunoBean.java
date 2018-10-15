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

import entities.Aluno;
import services.AlunoService;

@SessionScoped
@Named
public class AlunoBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private Aluno aluno = new Aluno();
	private Set<Aluno> alunos;
	@Inject
	private AlunoService alunoService;

	
	@PostConstruct
	public void init() {
		setAlunoService(new AlunoService());
		alunos = (Set<Aluno>) alunoService.getAll();
	}

	public void createAluno() {
		alunoService.save(aluno);
		aluno = new Aluno();
	}

	public void editarAluno(Aluno id) throws IOException {
		alunoService.update(id);
		aluno = new Aluno();
		setAlunos(getAlunoService().getAll());
	}

	public Aluno buscarAluno(Long id) {
		return alunoService.getByID(id);
	} 

	public void removerAluno(Long id) {
		alunoService.remove(alunoService.getByID(id));
		alunos = (Set<Aluno>) alunoService.getAll();
		alunoService = new AlunoService();
//		setRenderPanelGridAlunoBuscado(false);
	}

	public void salvarEdicao(Long id) throws IOException {
		alunoService.update(alunoService.getByID(id));
		alunos = (Set<Aluno>) alunoService.getAll();
		setAlunoService(new AlunoService());
		FacesContext.getCurrentInstance().getExternalContext().redirect("cadastrados.xhtml");
	}
	
	public AlunoService getAlunoService() {
		return alunoService;
	}
	public void setAlunoService(AlunoService alunoService) {
		this.alunoService = alunoService;
	}
	public Aluno getAluno() {
		return aluno;
	}
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	public Set<Aluno> getAlunos() {
		return alunos;
	}
	public void setAlunos(List<Aluno> alunos) {
		this.alunos = (Set<Aluno>) alunos;
	}
	public AlunoBean() {
		super();
	}
}
