package esselunga.jpa.dao;

import java.util.List;

import javax.persistence.Query;

import esselunga.jpa.models.Prodotto;

public class ProdottoDao extends BaseDao<Prodotto>{

	@Override
	public Prodotto insert(Prodotto prodotto){
		
		logInit("insert", prodotto);
		try {
			beginTransaction();
			if(!getEntityManager().contains(prodotto)) {
				prodotto = getEntityManager().merge(prodotto);
			}
			getEntityManager().persist(prodotto);
			commitTransaction();
			
			return prodotto;
		} catch (Exception e) {
			e.printStackTrace();
			rollbackTransaction();
			
			return null;
		} finally {
			getEntityManager().close();
		}
	}

	@Override
	public Prodotto update(Prodotto prodotto) {
		
		logInit("update", prodotto);
		try {
			beginTransaction();
			if(!getEntityManager().contains(prodotto)) {
				prodotto = getEntityManager().merge(prodotto);
			}
			getEntityManager().persist(prodotto);
			commitTransaction();
			
			return prodotto;
		} catch(Exception e) {
			e.printStackTrace();
			rollbackTransaction();
			
			return null;
		} finally {
			getEntityManager().close();
		}
	}

	@Override
	public void delete(Prodotto prodotto) {
		
		logInit("delete", prodotto);
		try {
			beginTransaction();
			if(!getEntityManager().contains(prodotto)) {
				prodotto = getEntityManager().merge(prodotto);
			}
			getEntityManager().remove(prodotto);
			commitTransaction();
		} catch(Exception e) {
			e.printStackTrace();
			rollbackTransaction();
		} finally {
			getEntityManager().flush();
			getEntityManager().close();
		}
	}

	@Override
	public Prodotto findById(Integer id) {
		
		logInit("findById", id);
		
		try {
			beginTransaction();
			Prodotto prodottoTrovato = getEntityManager().find(Prodotto.class, id);
			
			return prodottoTrovato;
		} catch(Exception e) {
			e.printStackTrace();
			
			return null;
		} finally {
			getEntityManager().close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Prodotto> findAll() {
		
		logInit("findAll", null);
		
		try {
			beginTransaction();
			Query query = getEntityManager().createNativeQuery("SELECT * FROM PRODOTTO", Prodotto.class);
			List<Prodotto> prodottiTrovati = query.getResultList();
			
			return prodottiTrovati;
		} catch(Exception e) {
			e.printStackTrace();
			
			return null;
		} finally {
			getEntityManager().close();
		}
	}

}
