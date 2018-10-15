package dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import entities.Aluno;
import entities.Identificavel;

public class AlunoDAO <E extends Identificavel> {
	@Inject
	private EntityManager em;

	private Class<E> classe;

	public AlunoDAO(Class<E> classe) {
		this.classe = classe;
	}

	public void save(E obj) {
		if(obj.getMatricula() == null) {
			em.persist(obj);
		} else {
			update(obj);
		}
	}

	public E update(E obj) {
		E resultado = obj;
		resultado = em.merge(obj);
		return resultado;
	}

	public void remove(E obj) {
		obj = getByID(obj.getMatricula());
		em.remove(obj);
	}

	public E getByID(Long objId) {
		return em.find(classe, objId);
	}

	public List<E> getAll() {
		Query query = em.createQuery("from " + classe.getSimpleName());
		return query.getResultList();
	}
}
