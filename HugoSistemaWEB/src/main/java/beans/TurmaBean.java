package beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import entities.Aluno;
import entities.Turma;
import services.TurmaService;

@SessionScoped
@Named
public class TurmaBean implements Serializable {
	private Turma turma = new Turma();
	private Turma turmaBuscado = new Turma();
	private TurmaService turmaService;
	private Turma turmaEditado;
	private Long matriculaTurma;
	private Set<Turma> turmas;
	private boolean renderPanelGridTurmaBuscado;
	
	@PostConstruct
	public void init() {
		setTurmas(turmaService.getAll());
	}
	
	public void criarTurma() {
		turmaService.save(turma);
		turma = new Turma();
		setTurmas(getTurmaService().getAll());
	}
	
	public void editarTurma() throws IOException {
		setTurmaEditado(getTurmaBuscado());
		setTurmaBuscado(new Turma());
		setRenderPanelGridTurmaBuscado(false);
		FacesContext.getCurrentInstance().getExternalContext().redirect("editarTurma.xhtml");
	}

	public void buscarTurma() {
		boolean encontrado = false;
		for (Turma buscaTurma: turmas) {
			if (buscaTurma.getMatricula() == getMatriculaTurma()) {
				setTurmaBuscado(buscaTurma);
				encontrado = true;
				setRenderPanelGridTurmaBuscado(true);
				setMatriculaTurma(0L);
			}
		}
		if (!encontrado) {
			FacesContext.getCurrentInstance().addMessage("ERROR",
					new FacesMessage("Turma com matricula " + getMatriculaTurma() + " nao foi encontrado"));
			setRenderPanelGridTurmaBuscado(false);
		}	
	} 

	public void removerTurma() {
		turmaService.remove(turmaBuscado);
		turmas = (Set<Turma>) turmaService.getAll();
		turmaBuscado = new Turma();
		setRenderPanelGridTurmaBuscado(false);
	}
	
	public void salvarEdicao() throws IOException {
		turmaService.update(turmaEditado);
		turmas = (Set<Turma>) turmaService.getAll();
		setTurmaEditado(new Turma());
		FacesContext.getCurrentInstance().getExternalContext().redirect("turmasCadastrados.xhtml");
	}

	public void salvarTurma() {
		turma.setNome(turma.getNome());
		turma.setAlunos(turma.getAlunos());
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Turma " + turma.getNome() + "adicionado"));
		turmaService.save(turma);
		turmas = (Set<Turma>) turmaService.getAll();
		turma = new Turma();
	}
	
	public Turma getTurma() {
		return turma;
	}
	public void setTurma(Turma turma) {
		this.turma = turma;
	}
	public Set<Turma> getTurmas() {
		return turmas;
	}
	public void setTurmas(List<Turma> list) {
		this.turmas = (Set<Turma>) list;
	}
	public TurmaService getTurmaService() {
		return turmaService;
	}

	public void setTurmaService(TurmaService turmaService) {
		this.turmaService = turmaService;
	}

	public Turma getTurmaEditado() {
		return turmaEditado;
	}

	public void setTurmaEditado(Turma turmaEditado) {
		this.turmaEditado = turmaEditado;
	}

	public Turma getTurmaBuscado() {
		return turmaBuscado;
	}

	public void setTurmaBuscado(Turma turmaBuscado) {
		this.turmaBuscado = turmaBuscado;
	}

	public Long getMatriculaTurma() {
		return matriculaTurma;
	}

	public void setMatriculaTurma(Long matriculaTurma) {
		this.matriculaTurma = matriculaTurma;
	}

	public boolean isRenderPanelGridTurmaBuscado() {
		return renderPanelGridTurmaBuscado;
	}

	public void setRenderPanelGridTurmaBuscado(boolean renderPanelGridTurmaBuscado) {
		this.renderPanelGridTurmaBuscado = renderPanelGridTurmaBuscado;
	}

	public TurmaBean() {
		super();
	}
}
