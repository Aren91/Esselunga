package esselunga.jpa.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
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

			if(!getEntityManager().contains(model)) {
				
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
	
	
	@SuppressWarnings("unchecked")
	public List<Utente> findAllByProdotti() {
		
		try {
			
			EntityManagerProvider.beginTransaction();
			List<Utente> listaUtenti = new ArrayList<Utente>();
			
			Query query = EntityManagerProvider.getEntityManager().createNativeQuery("select * from utente u " + 
																					"inner join carrello c on u.ID = c.ID_UTENTE" + 
																					" inner join prodotto p on p.ID = c.ID_PRODOTTO",
																					Utente.class);
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
	
	@SuppressWarnings("unchecked")
	public List<Utente> findUtentiByProdottoId(Integer id) {
		
		try {
			
			EntityManagerProvider.beginTransaction();
			List<Utente> listaUtenti = new ArrayList<Utente>();
			
			Query query = EntityManagerProvider.getEntityManager().createNativeQuery("select * from utente u " + 
																					"inner join carrello c on u.ID = c.ID_UTENTE" + 
																					" inner join prodotto p on p.ID = c.ID_PRODOTTO" + 
																					" where p.ID = " + id, Utente.class);
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
	
	public Utente login (String email, String password) throws NoResultException {
		
		System.out.println("login");
		
		try {
			
			EntityManagerProvider.beginTransaction();
			Utente utente = new Utente();
			
			Query query = EntityManagerProvider.getEntityManager().createNativeQuery("select * from utente u "
																					+ "where u.EMAIL = ? "
																					+ "and PASSWORD = ?;", Utente.class);
			query.setParameter(1, email);
			query.setParameter(2, password);
			
			utente = (Utente) query.getSingleResult();
			
			EntityManagerProvider.commitTransaction();
			
			return utente;
			
		} catch (Exception e) {

			e.printStackTrace();
			EntityManagerProvider.rollbackTransaction();
			
		} finally {
			
			getEntityManager().close();
		}
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<Utente> findAllDatiUtenteProdotto(){
		
		System.out.println("findAllDatiUtenteProdotto di UtenteDao");
		try {
			beginTransaction();
			Query query = getEntityManager().createNativeQuery("SELECT U.*, P.* FROM CARRELLO C "
					+ "INNER JOIN UTENTE U ON U.ID = C.ID_UTENTE "
					+ "INNER JOIN PRODOTTO P ON P.ID = C.ID_PRODOTTO", Utente.class);
			List<Utente> datiTrovati = query.getResultList();
			
			return datiTrovati;
		} catch(Exception e) {
			e.printStackTrace();
			
			return null;
		} finally {
			getEntityManager().close();
		}
	}
	
	public String findEmail(String email){
		
		System.out.println("findEmail di UtenteDao");
		String emailTrovata = null;
		try {
			beginTransaction();
			Query query = getEntityManager().createNativeQuery("SELECT EMAIL FROM UTENTE WHERE EMAIL = ?", Utente.class);
			query.setParameter(1, email);
			emailTrovata = (String)query.getSingleResult();
			
			return emailTrovata;
		} catch(Exception e) {
			e.printStackTrace();
			
			return null;
		} finally {
			getEntityManager().close();
		}
	}

}
