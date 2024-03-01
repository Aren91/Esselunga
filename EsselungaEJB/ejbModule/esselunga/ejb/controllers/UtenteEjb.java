package esselunga.ejb.controllers;

import java.io.Serializable;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

import esselunga.ejb.interfaces.UtenteEjbInterface;
import esselunga.jpa.dao.UtenteDao;
import esselunga.jpa.models.Utente;

@Stateless(name="UtenteEjbInterface")
@LocalBean
public class UtenteEjb implements Serializable, UtenteEjbInterface{

	private static final long serialVersionUID = -1449442866644241177L;

	@Override
	public List<Utente> findAll() {
		
		UtenteDao utenteDao = new UtenteDao();
		
		List<Utente> listaUtenti = utenteDao.findAll();
		
		return listaUtenti;
	}

	@Override
	public Utente findById(Integer id) {
		
		UtenteDao utenteDao = new UtenteDao();
		
		Utente utente = utenteDao.findById(id);
		
		return utente;
	}

	@Override
	public Utente insert(Utente model) {

		UtenteDao utenteDao = new UtenteDao();
		
		Utente utente = utenteDao.insert(model);
		
		return utente;
	}

	@Override
	public Utente update(Utente model) {

		UtenteDao utenteDao = new UtenteDao();
		
		Utente utente = utenteDao.update(model);
		
		return utente;
	}

	@Override
	public void delete(Utente model) {

		UtenteDao utenteDao = new UtenteDao();
		utenteDao.delete(model);
	}

	@Override
	public List<Utente> findAllByProdotti() {
		
		UtenteDao utenteDao = new UtenteDao();
		List<Utente> listaUtenti = utenteDao.findAllByProdotti();
		return listaUtenti;
	}

	@Override
	public List<Utente> findUtentiByProdottoId(Integer id) {

		UtenteDao utenteDao = new UtenteDao();
		List<Utente> listaUtenti = utenteDao.findUtentiByProdottoId(id);
		return listaUtenti;
	}

	@Override
	public Utente login(String email, String password) throws Exception {

		UtenteDao utenteDao = new UtenteDao();
		try {
			Utente utente = utenteDao.login(email, password);
			
			return utente;
		} catch(NoResultException nre) {
			throw new NoResultException(nre.getMessage());
		}
		catch (Exception e) {
			throw new Exception("errore generico");
		}
		
		
	}

	@Override
	public List<Utente> findAllDatiUtenteProdotto() {
		
		UtenteDao utenteDao = new UtenteDao();
		
		return utenteDao.findAllDatiUtenteProdotto();
	}

	@Override
	public String findEmail(String email) {
		
		UtenteDao utenteDao = new UtenteDao();
		
		return utenteDao.findEmail(email);
	}

}
