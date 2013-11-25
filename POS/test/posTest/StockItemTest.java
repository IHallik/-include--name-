package posTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.data.StockItem;

public class StockItemTest {
	
	private StockItem stock;
	
	@Before
	public void setUp() {
		stock = new StockItem(69L,"Aivan","x", 1.0);
	}
	
	@Test
	public void testClone() {
		StockItem x = (StockItem) stock.clone();
		assertTrue(stock.equals(x));
	}
	
	@Test
	public void testGetColumn() {
		assertEquals((Double)(stock.getColumn(2)), 1.0, 0.001);
	}
	
	@Test(expected = RuntimeException.class)
	public void testGetInvalidColumn() {
		stock.getColumn(27);
	}
}
