package services;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import dao.AlunoDAO;
import dao.DisciplinaDAO;
import entities.Aluno;
import entities.Disciplina;
import entities.Identificavel;
import util.TransacionalCdi;

public class DisciplinaService  implements Serializable, Service<Disciplina>{

	private static final long serialVersionUID = -7803325791425670859L;

	@Inject
	private DisciplinaDAO<Identificavel> disciplinaDAO;

	@Override
	@TransacionalCdi
	public void save(Disciplina disciplina) {
		disciplinaDAO.save(disciplina);
	}

	@Override
	@TransacionalCdi
	public void update(Disciplina disciplina) {
		disciplinaDAO.update(disciplina);
	}

	@Override
	@TransacionalCdi
	public void remove(Disciplina disciplina) {
		disciplinaDAO.remove(disciplina);
	}

	@Override
	public Disciplina getByID(long objId) {
		return (Disciplina) disciplinaDAO.getByID(objId);
	}

	@Override
	public List<Disciplina> getAll() {
		return disciplinaDAO.getAll();
	}

}
