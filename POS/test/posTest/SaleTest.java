package posTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.ui.model.PurchaseInfoTableModel;


//No idea what this should be called, so it has the simplest name possible
public class SaleTest {

	
	private SoldItem sold;
	private StockItem stock;
	private PurchaseInfoTableModel tableModel;
	
	
	@Before
	public void setUp(){
		// TODO Vaja gettida see data, mitte hardcodeda stockitemit imo
		stock = new StockItem(0L,"Ivan", "x", 1.0);
		sold = new SoldItem(stock, 10);
		tableModel = new PurchaseInfoTableModel();
		tableModel.addItem(sold);
		
	}
	
	@Test
	public void testAddSoldItem(){
		boolean thrown = false;
		try{
			tableModel.addItem(new SoldItem(new StockItem(1L, "Vani", "y", 10), 1));
		}catch(IllegalArgumentException e){
			thrown=true;
		}
		assertFalse(thrown);
		assertEquals(tableModel.getRowCount(), 2);
	}
	
	@Test
	public void testGetSumWithNoItems(){
		tableModel=new PurchaseInfoTableModel();
		assertEquals(tableModel.getRowCount(),0);
	}
	
	
	@Test
	public void testGetSumWithOneItem(){
		assertEquals(tableModel.getRowCount(),1);
	}
	
	@Test
	public void testGetSumWithMultipleItems(){
		tableModel.addItem(new SoldItem(new StockItem(1L, "Vasja", "xy", 2.0),20));
		assertTrue(tableModel.getRowCount()>=2);
		
	}
	
}
