package posTest;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;

public class SoldItemTest {
	
	private SoldItem sold;
	private SoldItem soldZero;
	private StockItem stock;
	
	@Before
	public void setUp(){
		stock = new StockItem(0L,"Ivan", "x", 1.0);
		sold = new SoldItem(stock, 10);
		soldZero = new SoldItem(stock, 0);
	}
	
	@Test
	public void testGetSum(){
		assertEquals(sold.getSum(), 10.0, 0.001);
		
	}
	
	@Test
	public void testGetSumWithZeroQuantity(){
		assertEquals(soldZero.getQuantity(), 0.0, 0.001);
	}
}
