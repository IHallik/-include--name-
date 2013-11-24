package posTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.data.StockItem;

public class StockItemTest {
	
	
	private StockItem stock;
	private int columnIndex;
	
	@Before
	public void setUp(){
		columnIndex=2;
		stock = new StockItem(69L,"Aivan","x", 1.0);
	}
	
	@Test
	public void testClone(){
		StockItem x = (StockItem) stock.clone();
		assertEquals(x.getName(), x.getName());
	}
	
	@Test
	public void testGetColumn(){
		boolean bool = false;
		try{
			stock.getColumn(columnIndex);
		}catch(RuntimeException e){
			bool= true;
		}
		assertFalse(bool);
	}

}
