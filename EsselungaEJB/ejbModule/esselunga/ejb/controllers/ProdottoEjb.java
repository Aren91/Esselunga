package esselunga.ejb.controllers;

import java.io.Serializable;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import esselunga.ejb.interfaces.ProdottoEjbInterface;
import esselunga.jpa.dao.ProdottoDao;
import esselunga.jpa.models.Prodotto;

@Stateless(name="ProdottoEjbInterface")
@LocalBean
public class ProdottoEjb implements ProdottoEjbInterface, Serializable{

	private static final long serialVersionUID = -1777842390135702217L;

	@Override
	public Prodotto insert(Prodotto prodotto) {
		
		ProdottoDao prodottoDao = new ProdottoDao();
		
		return prodottoDao.insert(prodotto);
	}

	@Override
	public Prodotto update(Prodotto prodotto) {
		
		ProdottoDao prodottoDao = new ProdottoDao();
		
		return prodottoDao.update(prodotto);
	}

	@Override
	public void delete(Prodotto prodotto) {
		
		ProdottoDao prodottoDao = new ProdottoDao();
		prodottoDao.delete(prodotto);
	}

	@Override
	public Prodotto findById(Integer id) {
		
		ProdottoDao prodottoDao = new ProdottoDao();
		
		return prodottoDao.findById(id);
	}

	@Override
	public List<Prodotto> findAll() {
		
		ProdottoDao prodottoDao = new ProdottoDao();
		
		return prodottoDao.findAll();
	}

	@Override
	public List<Prodotto> findAllByUtenti() {
		
		ProdottoDao prodottoDao = new ProdottoDao();
		
		return prodottoDao.findAllByUtenti();
	}

	@Override
	public List<Prodotto> findAllByIdUtente(Integer idUtente) {
		
		ProdottoDao prodottoDao = new ProdottoDao();
		
		return prodottoDao.findAllByIdUtente(idUtente);
	}

	@Override
	public List<Prodotto> findAllDati() {

		ProdottoDao prodottoDao = new ProdottoDao();
		
		return prodottoDao.findAllDati();

	}

}
