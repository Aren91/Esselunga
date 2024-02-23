package esselunga.ejb.interfaces;

import java.util.List;

import javax.ejb.Local;

import esselunga.jpa.models.Prodotto;

@Local
public interface ProdottoEjbInterface {

	Prodotto insert(Prodotto prodotto);
	
	Prodotto update(Prodotto prodotto);
	
	void delete(Prodotto prodotto);
	
	Prodotto findById(Integer id);
	
	List<Prodotto> findAll();
	
}
