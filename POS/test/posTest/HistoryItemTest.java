package posTest;

import java.util.ArrayList;
import java.util.HashSet;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.data.HistoryItem;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;

public class HistoryItemTest extends TestCase {
	
	private HistoryItem hm;
	
	@Before
	public void setUp(){
		hm = new HistoryItem();
	}

	@Test
	public void testGetSumWithNoItems(){
		hm = new HistoryItem(new ArrayList<SoldItem>());
		assertEquals(hm.getOrderPrice(),0.0, 0.001);
	}
	

	@Test
	public void testGetSumWithOneItem(){
		SoldItem i1= new SoldItem(new StockItem(0L,"Burks","b",2.0,10),3);
		HashSet<SoldItem> ls = new HashSet<SoldItem>();
		ls.add(i1);
		hm = new HistoryItem(new ArrayList<SoldItem>(ls));
		assertEquals(hm.getOrderPrice(),6.0, 0.001);
	}
	
	@Test
	public void testEmptyHistoryTab(){
		assertEquals(hm.getUniqueItemCount(),new Integer(0));
	}
	
	@Test
	public void testGetSumWithMultipleItems(){
		SoldItem i1= new SoldItem(new StockItem(0L,"Burks","b",2.0,10),3);
		SoldItem i2 = new SoldItem(new StockItem(1L, "bruksKallim","b2",4.0,20),5);
		HashSet<SoldItem> ls = new HashSet<SoldItem>();
		ls.add(i1);
		ls.add(i2);
		hm = new HistoryItem(new ArrayList<SoldItem>(ls));
		assertEquals(hm.getOrderPrice(),26.0, 0.001);
	}
	
	// We don't have an add method, we'll test the constructor instead
	@Test
	public void testAddSoldItem(){
		SoldItem i1= new SoldItem(new StockItem(0L,"Burks","b",2.0,10),3);
		SoldItem i2 = new SoldItem(new StockItem(1L, "bruksKallim","b2",4.0,20),5);
		SoldItem i3 = new SoldItem(new StockItem(2L, "Fortune Cookie","tells your fortune",1.0,133),7);
		HashSet<SoldItem> ls = new HashSet<SoldItem>();
		ls.add(i1);
		ls.add(i2);
		ls.add(i3);
		hm = new HistoryItem(new ArrayList<SoldItem>(ls));
		assertEquals(hm.getUniqueItemCount(), new Integer(3));
	}
}