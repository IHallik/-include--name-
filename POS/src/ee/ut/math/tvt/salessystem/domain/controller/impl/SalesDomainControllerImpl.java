package ee.ut.math.tvt.salessystem.domain.controller.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import org.hibernate.Session;

import ee.ut.math.tvt.salessystem.domain.controller.ConfirmationStatusEvent;
import ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController;
import ee.ut.math.tvt.salessystem.domain.data.HistoryItem;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.domain.exception.VerificationFailedException;
import ee.ut.math.tvt.salessystem.ui.tabs.ConfirmationTab;
import ee.ut.math.tvt.salessystem.util.HibernateUtil;

/**
 * Implementation of the sales domain controller.
 */
public class SalesDomainControllerImpl implements SalesDomainController {	
    protected Vector<ConfirmationStatusEvent> _listeners;    
    ConfirmationTab confirmationPopup;
    private Session session = HibernateUtil.currentSession();
    
    public void addConfirmationStatusListener(ConfirmationStatusEvent listener) {
		if (_listeners == null) {
			_listeners = new Vector<ConfirmationStatusEvent>();
		}
			
		_listeners.addElement(listener);
	}
	
	protected void confirmTransaction(HistoryItem sale, boolean success) {
		if(success) {
			session.beginTransaction();
			
			session.save(sale);
			Set<SoldItem> items = sale.getSoldItems();
			for(SoldItem item : items) {
				item.setHistoryItem(sale);
				session.save(item);
			}
			
			session.getTransaction().commit();
		}
		
		if (_listeners != null) {
			for(ConfirmationStatusEvent e : _listeners) {
				e.SaleConfirmed(success);
			}
		}
	}
    
    public SalesDomainControllerImpl() {
    	confirmationPopup = new ConfirmationTab();
    }
	
	public void submitCurrentPurchase(List<SoldItem> goods) throws VerificationFailedException {
		double sum = 0;
		for(SoldItem item : goods) {
			sum += item.getSum();
		}
		
		final HistoryItem sale = new HistoryItem(goods);
		
		confirmationPopup.setCost(sum);
		confirmationPopup.addConfirmationStatusListener(new ConfirmationStatusEvent() {
			public void SaleConfirmed(boolean success) {
				confirmTransaction(sale, success);
			}
		});
		
		try {
			confirmationPopup.draw().setVisible(true);
		} catch (ParseException e) {}
	}
	
	private void saleConfirmed(boolean success) {
		
	}

	public void cancelCurrentPurchase() throws VerificationFailedException {				
		// XXX - Cancel current purchase
	}

	public void startNewPurchase() throws VerificationFailedException {
		// XXX - Start new purchase
	}

	@SuppressWarnings("unchecked")
	public List<StockItem> loadWarehouseState() {
		System.out.println("StockItem returned "+session.createQuery("from StockItem").list().size() + " rows.");
		return session.createQuery("from StockItem").list();
	}
	
	@SuppressWarnings("unchecked")
	public List<HistoryItem> getSales() {
		System.out.println("HistoryItem returned "+session.createQuery("from HistoryItem").list().size() + " rows.");
		return session.createQuery("from HistoryItem").list();
	}
	
	@SuppressWarnings("unchecked")
	public List<SoldItem> getSoldItems() {
		System.out.println("SoldItem returned "+session.createQuery("from SoldItem").list().size() + " rows.");
		return session.createQuery("from SoldItem").list();
	}
	
	public void endSession() {
		//HibernateUtil.closeSession();
	}
}
