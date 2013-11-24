package posTest;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;

public class SoldItemTest extends TestCase {
	
	
	private SoldItem sold;
	private StockItem stock;
	
	
	@Before
	public void setUp(){
		stock = new StockItem(0L,"Ivan", "x", 1.0);
	}
	
	
	@Test
	public void testGetSum(){
		sold = new SoldItem(stock, 10);
		assertEquals(sold.getSum(),10.0,0.001);
		
	}
	
	@Test
	public void testGetSumWithZeroQuantity(){
		sold = new SoldItem(stock, 0);
		assertEquals(sold.getQuantity(),0.0,0.001);
	}

}
