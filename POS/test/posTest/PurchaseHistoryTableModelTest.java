package posTest;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.data.HistoryItem;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.ui.model.PurchaseHistoryTableModel;
import ee.ut.math.tvt.salessystem.ui.model.PurchaseInfoTableModel;

public class PurchaseHistoryTableModelTest {
	
	private PurchaseHistoryTableModel phm;
	private PurchaseInfoTableModel pim;
	private HistoryItem hm;	
	
	@Before
	public void setUp(){
		pim = new PurchaseInfoTableModel();
		phm = new PurchaseHistoryTableModel();
		
		SoldItem i1= new SoldItem(new StockItem(0L,"Burks","b",2.0,10),3);
		SoldItem i2 = new SoldItem(new StockItem(1L, "bruksKallim","b2",4.0,20),5);
		
		pim.addItem(i1);
		pim.addItem(i2);
		
		phm.addItem(new HistoryItem(pim.getTableRows()));
		
		hm = new HistoryItem(pim.getTableRows());
	}
	
	@Test
	public void testGetColumnValue(){
		assertEquals(hm.getOrderDateString(), phm.getValueAt(0, 1));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetColumnValueException(){
		phm.getValueAt(0, 100);
	}
	
	@Test
	public void testAddItem() {
		phm.addItem(hm);
	}
}
