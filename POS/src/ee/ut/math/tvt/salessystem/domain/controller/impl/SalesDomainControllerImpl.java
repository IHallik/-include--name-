package ee.ut.math.tvt.salessystem.domain.controller.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import ee.ut.math.tvt.salessystem.domain.exception.VerificationFailedException;
import ee.ut.math.tvt.salessystem.domain.controller.ConfirmationStatusEvent;
import ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController;
import ee.ut.math.tvt.salessystem.domain.data.HistoryItem;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;
import ee.ut.math.tvt.salessystem.ui.tabs.ConfirmationTab;

/**
 * Implementation of the sales domain controller.
 */
public class SalesDomainControllerImpl implements SalesDomainController {
	
    protected Vector<ConfirmationStatusEvent> _listeners;
	
	public void addConfirmationStatusListener(ConfirmationStatusEvent listener) {
		if (_listeners == null) {
			_listeners = new Vector<ConfirmationStatusEvent>();
		}
			
		_listeners.addElement(listener);
	}
	
	//The popup confirmation box should fire this event
	protected void fireConfirmationStatus(boolean success) {
		if (_listeners != null) {
			for(ConfirmationStatusEvent e : _listeners) {
				e.SaleConfirmed(success);
			}
		}
	}
	
	public void submitCurrentPurchase(List<SoldItem> goods) throws VerificationFailedException {
		
		double sum = 0;
		for(SoldItem item : goods) {
			sum += item.getSum();
		}
		
		try {
			ConfirmationTab.popUpWindow(sum).setVisible(true);
		} catch (ParseException e) {}
		
		//Block the program flow somehow? Lock main display or the purchase tab only???
	}

	public void cancelCurrentPurchase() throws VerificationFailedException {				
		// XXX - Cancel current purchase
	}
	

	public void startNewPurchase() throws VerificationFailedException {
		// XXX - Start new purchase
	}

	public List<StockItem> loadWarehouseState() {
		// XXX mock implementation
		List<StockItem> dataset = new ArrayList<StockItem>();

		StockItem chips = new StockItem(1l, "Lays chips", "Potato chips", 11.0, 5);
		StockItem chupaChups = new StockItem(2l, "Chupa-chups", "Sweets", 8.0, 8);
	    StockItem frankfurters = new StockItem(3l, "Frankfurters", "Beer sauseges", 15.0, 12);
	    StockItem beer = new StockItem(4l, "Free Beer", "Student's delight", 0.0, 100);

		dataset.add(chips);
		dataset.add(chupaChups);
		dataset.add(frankfurters);
		dataset.add(beer);
		
		return dataset;
	}
}
