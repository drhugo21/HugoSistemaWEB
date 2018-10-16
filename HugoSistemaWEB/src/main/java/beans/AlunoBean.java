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
@Named(value = "alunoBean")
public class AlunoBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private int matriculaAluno;
	private Aluno aluno = new Aluno();
	private Aluno alunoBuscado = new Aluno();
	private Aluno alunoEditado;
	private List<Aluno> alunos;
	@Inject
	private AlunoService alunoService;
	private boolean renderPanelGridPcBuscado;
	
	@PostConstruct
	public void init() {
		setAlunos(alunoService.getAll());
	}

	public void criarAluno() {
		alunoService.save(aluno);
		aluno = new Aluno();
		setAlunos(getAlunoService().getAll());
	}

	public void editarAluno() throws IOException {
		setAlunoEditado(getAlunoBuscado());
		setAlunoBuscado(new Aluno());
		setRenderPanelGridPcBuscado(false);
		FacesContext.getCurrentInstance().getExternalContext().redirect("editarAluno.xhtml");
	}

	public void buscarAluno() {
		boolean encontrado = false;
		for (Aluno buscaAluno : alunos) {
			if (buscaAluno.getMatricula() == getMatriculaAluno()) {
				setAlunoBuscado(buscaAluno);
				encontrado = true;
				setRenderPanelGridPcBuscado(true);
				setMatriculaAluno(0);
			}
		}
		if (!encontrado) {
			FacesContext.getCurrentInstance().addMessage("ERROR",
					new FacesMessage("Aluno com matricula " + getMatriculaAluno() + " nao foi encontrado"));
			setRenderPanelGridPcBuscado(false);
		}	
	} 

	public void removerAluno() {
		alunoService.remove(alunoBuscado);
		alunos = alunoService.getAll();
		alunoBuscado = new Aluno();
		setRenderPanelGridPcBuscado(false);
	}
	
	public void salvarEdicao() throws IOException {
		alunoService.update(alunoEditado);
		alunos = alunoService.getAll();
		setAlunoEditado(new Aluno());
		FacesContext.getCurrentInstance().getExternalContext().redirect("alunosCadastrados.xhtml");
	}

	public void salvarPc() {
		aluno.setNome(aluno.getNome());
		aluno.setData(aluno.getData());
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Aluno " + aluno.getNome() + "adicionado"));
		alunoService.save(aluno);
		alunos = alunoService.getAll();
		aluno = new Aluno();
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
		return (Set<Aluno>) alunos;
	}
	public void setAlunos(List<Aluno> alunos) {
		this.alunos = (List<Aluno>) alunos;
	}
	public Aluno getAlunoBuscado() {
		return alunoBuscado;
	}

	public void setAlunoBuscado(Aluno alunoBuscado) {
		this.alunoBuscado = alunoBuscado;
	}

	public Aluno getAlunoEditado() {
		return alunoEditado;
	}

	public void setAlunoEditado(Aluno alunoEditado) {
		this.alunoEditado = alunoEditado;
	}

	public boolean isRenderPanelGridPcBuscado() {
		return renderPanelGridPcBuscado;
	}

	public void setRenderPanelGridPcBuscado(boolean renderPanelGridPcBuscado) {
		this.renderPanelGridPcBuscado = renderPanelGridPcBuscado;
	}

	public int getMatriculaAluno() {
		return matriculaAluno;
	}

	public void setMatriculaAluno(int i) {
		this.matriculaAluno = i;
	}

	public AlunoBean() {
		super();
	}
}
