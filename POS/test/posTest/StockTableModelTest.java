package posTest;

import static org.junit.Assert.assertEquals;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.ui.model.StockTableModel;

public class StockTableModelTest {
	
	private StockTableModel stm;
	private StockItem stockitem;
	private SoldItem solditem;
	
	@Before
	public void setUp(){
		stockitem = new StockItem(88L, "Ussripik", "just nii ongi", 42.0, 12);
		solditem = new SoldItem(stockitem, 20);
		
		stm = new StockTableModel();
		stm.addItem(stockitem);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testValidateNameUniqueness(){
		StockItem item = new StockItem(72L, "Ussripik", "just nii ongi", 42.0, 12);
		stm.addItem(item);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testHasEnoughInStock(){
		stm.removeItem(solditem);
	}
	
	@Test
	public void testGetItemByIdWhenItemExists(){
		assertEquals(stm.getItemById(88L).getId(), new Long(88L));
	}
	
	@Test(expected = NoSuchElementException.class)
	public void testGetItemByIdWhenThrowsException(){
		stm.getItemById(70L);
	}
}
