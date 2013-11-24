package posTest;

import java.util.NoSuchElementException;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.ui.model.PurchaseInfoTableModel;

public class PurchaseInfoTableModelTest extends TestCase {
	
	private PurchaseInfoTableModel pm;	
	
	@Before
	public void setUp(){
		pm = new PurchaseInfoTableModel();
		SoldItem x = new SoldItem(new StockItem(1L,"g","2",10,7),2);
		SoldItem y = new SoldItem(new StockItem(2L,"b","7",8,3),1);
		pm.addItem(x);
		pm.addItem(y);
	}
	
	
	@Test
	public void testGetItem(){
		boolean bool = false;
		try{
			pm.getItemById(2L);
		}catch(IllegalArgumentException e){
			bool= true;
		}
		assertFalse(bool);
	}
	
	@Test
	public void testGetItemException(){
		boolean bool = false;
		try{
			pm.getItemById(7L);
		}catch(NoSuchElementException e){
			bool= true;
		}
		assertTrue(bool);
	}

}

