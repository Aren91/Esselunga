package esselunga.jpa.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import esselunga.jpa.connection.EntityManagerProvider;
import esselunga.jpa.models.Utente;

public class UtenteDao extends BaseDao<Utente> {

	@Override
	public Utente insert(Utente model) {
		try {
			
			EntityManagerProvider.beginTransaction();
			
			if(!getEntityManager().contains(model)) {
				
				model = getEntityManager().merge(model);
				
			}
			
			getEntityManager().persist(model);
			EntityManagerProvider.commitTransaction();
			
			return model;
			
		} catch (Exception e) {
			
			e.printStackTrace();
			EntityManagerProvider.rollbackTransaction();
			
		} finally {
			
			getEntityManager().close();
		}
		return null;
	}

	@Override
	public Utente update(Utente model) {
		
		try {
			
			EntityManagerProvider.beginTransaction();
			getEntityManager().merge(model);
			EntityManagerProvider.commitTransaction();
			
			return model;
			
		} catch (Exception e) {
			
			e.printStackTrace();
			EntityManagerProvider.rollbackTransaction();
			
		} finally {
			
			getEntityManager().close();
		}
		return null;
	}

	@Override
	public void delete(Utente model) {

		try {
			
			EntityManagerProvider.beginTransaction();

			if(getEntityManager().contains(model)) {
				
				model = getEntityManager().merge(model);
			}
			
			getEntityManager().remove(model);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			EntityManagerProvider.rollbackTransaction();
			
		} finally {
			
			getEntityManager().flush();
			getEntityManager().close();
			
		}
		
	}

	@Override
	public Utente findById(Integer id) {

		try {
			
			EntityManagerProvider.beginTransaction();
			Utente utenteTrovato = getEntityManager().find(Utente.class, id);
			EntityManagerProvider.commitTransaction();
			
			return utenteTrovato;
			
		} catch (Exception e) {
			
			e.printStackTrace();
			EntityManagerProvider.rollbackTransaction();
			
		} finally {
			
			getEntityManager().close();
		}
		
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Utente> findAll() {

		try {
			
			EntityManagerProvider.beginTransaction();
			List<Utente> listaUtenti = new ArrayList<Utente>();
			
			//native query - SQL
			Query query = EntityManagerProvider.getEntityManager().createNativeQuery("SELECT * FROM utente", Utente.class);
			
			listaUtenti = query.getResultList();
			
			EntityManagerProvider.commitTransaction();
			
			return listaUtenti;
			
		} catch (Exception e) {
			
			e.printStackTrace();
			EntityManagerProvider.rollbackTransaction();
			
		} finally {
			
			getEntityManager().close();
			
		}
		return null;
	}

}
