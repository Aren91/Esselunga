package esselunga.ejb.interfaces;

import java.util.List;

import javax.ejb.Local;
import javax.persistence.NoResultException;

import esselunga.jpa.models.Utente;

@Local
public interface UtenteEjbInterface {

	List<Utente> findAll();
	
	Utente findById(Integer id);
	
	Utente insert(Utente model);
	
	Utente update(Utente model);
	
	void delete(Utente model);
	
	List<Utente> findAllByProdotti();
	
	List<Utente> findUtentiByProdottoId(Integer id);
	
	Utente login(String email, String password) throws NoResultException;
	
	List<Utente> findAllDatiUtenteProdotto();
	
	String findEmail(String email);
}
