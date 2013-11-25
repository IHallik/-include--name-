package posTest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.data.HistoryItem;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;

public class HistoryItemTest {
	
	private HistoryItem hm;
	
	@Before
	public void setUp(){
	}

	@Test
	public void testGetSumWithNoItems(){
		hm = new HistoryItem(new ArrayList<SoldItem>());
		assertEquals(hm.getOrderPrice(),0.0, 0.001);
	}

	@Test
	public void testGetSumWithOneItem(){
		SoldItem i1= new SoldItem(new StockItem(0L,"Burks","b",2.0,10),3);
		List<SoldItem> ls = new ArrayList<SoldItem>();
		ls.add(i1);
		hm = new HistoryItem(ls);
		assertEquals(hm.getOrderPrice(),6.0, 0.001);
	}
	
	@Test
	public void testEmptyHistoryTab(){
		hm = new HistoryItem(new ArrayList<SoldItem>());
		assertEquals(hm.getUniqueItemCount(), new Integer(0));
	}
	
	@Test
	public void testGetSumWithMultipleItems(){
		SoldItem i1= new SoldItem(new StockItem(0L,"Burks","b",2.0,10),3);
		SoldItem i2 = new SoldItem(new StockItem(1L, "bruksKallim","b2",4.0,20),5);
		List<SoldItem> ls = new ArrayList<SoldItem>();
		ls.add(i1);
		ls.add(i2);
		hm = new HistoryItem(ls);
		assertEquals(hm.getOrderPrice(),26.0, 0.001);
	}
	
	//Solditems are added all at once in the constructor
	@Test
	public void testAddSoldItem(){
		SoldItem i1= new SoldItem(new StockItem(0L,"Burks","b",2.0,10),3);
		SoldItem i2 = new SoldItem(new StockItem(1L, "bruksKallim","b2",4.0,20),5);
		SoldItem i3 = new SoldItem(new StockItem(2L, "Fortune Cookie","tells your fortune",1.0,133),7);
		List<SoldItem> ls = new ArrayList<SoldItem>();
		ls.add(i1);
		ls.add(i2);
		ls.add(i3);
		hm = new HistoryItem(ls);
		assertEquals(hm.getUniqueItemCount(), new Integer(3));
	}
}