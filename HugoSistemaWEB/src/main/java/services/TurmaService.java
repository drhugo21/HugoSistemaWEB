package services;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import dao.AlunoDAO;
import dao.TurmaDAO;
import entities.Aluno;
import entities.Turma;
import util.TransacionalCdi;

public class TurmaService implements Serializable, Service<Turma>{

	private static final long serialVersionUID = -7803325791425670859L;

	@Inject
	private TurmaDAO turmaDAO;

	@Override
	@TransacionalCdi
	public void save(Turma turma) {
		turmaDAO.save(turma);
	}

	@Override
	@TransacionalCdi
	public void update(Turma turma) {
		turmaDAO.update(turma);
	}

	@Override
	@TransacionalCdi
	public void remove(Turma turma) {
		turmaDAO.remove(turma);
	}

	@Override
	public Turma getByID(long objId) {
		return (Turma) turmaDAO.getByID(objId);
	}

	@Override
	public List<Turma> getAll() {
		return turmaDAO.getAll();
	}

}
