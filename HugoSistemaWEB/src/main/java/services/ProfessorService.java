package services;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import dao.DisciplinaDAO;
import dao.ProfessorDAO;
import entities.Disciplina;
import entities.Identificavel;
import entities.Professor;
import util.TransacionalCdi;

public class ProfessorService implements Serializable, Service<Professor>{

	private static final long serialVersionUID = -7803325791425670859L;

	@Inject
	private ProfessorDAO<Identificavel> professorDAO;

	@Override
	@TransacionalCdi
	public void save(Professor professor) {
		professorDAO.save(professor);
	}

	@Override
	@TransacionalCdi
	public void update(Professor professor) {
		professorDAO.update(professor);
	}

	@Override
	@TransacionalCdi
	public void remove(Professor professor) {
		professorDAO.remove(professor);
	}

	@Override
	public Professor getByID(long objId) {
		return (Professor) professorDAO.getByID(objId);
	}

	@Override
	public List<Professor> getAll() {
		return professorDAO.getAll();
	}

}
