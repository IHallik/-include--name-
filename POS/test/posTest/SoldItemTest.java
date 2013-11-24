package posTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;

public class SoldItemTest {
	
	
	private SoldItem sold;
	private StockItem stock;
	
	
	@Before
	public void setUp(){
		// TODO Vaja gettida see data, mitte hardcodeda stockitemit imo
		stock = new StockItem(0L,"Ivan", "x", 1.0);
		sold = new SoldItem(stock, 10);
	}
	
	
	@Test
	public void testGetSum(){
		assertEquals(sold.getSum(),1,0*10);
		
	}
	
	@Test
	public void testGetSumWithZeroQuantity(){
		assertEquals((int)sold.getQuantity(),0);
	}

}
