package posTest;

import static org.junit.Assert.*;

import java.util.HashSet;
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
		hm = new HistoryItem();
	}
	
	@Test
	public void emptyHistoryTab(){
		assertEquals((int)hm.getUniqueItemCount(),0);
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Test
	public void multipleItemsPrice(){
		SoldItem i1= new SoldItem(new StockItem(0L,"Burks","b",2.0,10),3);
		SoldItem i2 = new SoldItem(new StockItem(1L, "bruksKallim","b2",4.0,20),5);
		HashSet<SoldItem> ls = new HashSet<SoldItem>();
		ls.add(i1);
		ls.add(i2);
		hm = new HistoryItem((List<SoldItem>) ls);
		assertEquals(hm.getOrderPrice(),26.0);
	}

}
