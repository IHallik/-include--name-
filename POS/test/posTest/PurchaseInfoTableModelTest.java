package posTest;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.ui.model.PurchaseInfoTableModel;

public class PurchaseInfoTableModelTest {
	
	private PurchaseInfoTableModel pm;	
	
	@Before
	public void setUp(){
		SoldItem x = new SoldItem(new StockItem(1L,"g","2",10,7),2);
		SoldItem y = new SoldItem(new StockItem(2L,"b","7",8,3),1);
		pm.addItem(x);
		pm.addItem(y);
	}
	
	
	@Test
	public void noErrorColumn(){
		boolean bool = false;
		try{
			pm.getItemById(2L);
		}catch(IllegalArgumentException e){
			bool= true;
		}
		assertTrue(bool);
	}
	
	@Test
	public void errorColumn(){
		boolean bool = false;
		try{
			pm.getItemById(7L);
		}catch(IllegalArgumentException e){
			bool= true;
		}
		assertTrue(bool);
	}

}

