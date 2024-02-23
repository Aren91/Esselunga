package esselunga.ejb.interfaces;

import java.util.List;

import javax.ejb.Local;

import esselunga.jpa.models.Utente;

@Local
public interface UtenteEjbInterface {

	List<Utente> findAll();
	
	Utente findById(Integer id);
	
	Utente insert(Utente model);
	
	Utente update(Utente model);
	
	void delete(Utente model);
}
