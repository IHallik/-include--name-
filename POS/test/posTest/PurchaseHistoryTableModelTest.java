package posTest;

import java.util.ArrayList;
import java.util.HashSet;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.data.HistoryItem;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.ui.model.PurchaseHistoryTableModel;

public class PurchaseHistoryTableModelTest extends TestCase {
	
	private PurchaseHistoryTableModel phm;
	private HistoryItem hm;	
	
	@Before
	public void setUp(){
		phm = new PurchaseHistoryTableModel();
		SoldItem i1= new SoldItem(new StockItem(0L,"Burks","b",2.0,10),3);
		SoldItem i2 = new SoldItem(new StockItem(1L, "bruksKallim","b2",4.0,20),5);
		HashSet<SoldItem> ls = new HashSet<SoldItem>();
		ls.add(i1);
		ls.add(i2);
		hm = new HistoryItem(new ArrayList<SoldItem>(ls));
		phm.addItem(hm);
	}
	
	@Test
	public void testGetColumnValue(){
		boolean b = false;
		try{
			//phm.getColumnValue(hm,0);			This can't be used due to protected
			hm.getColumn(0);
		}catch(IllegalArgumentException e){
			b= true;
		}
		assertFalse(b);
	}
	
	@Test
	public void testGetColumnValueException(){
		boolean b = false;
		try{
			hm.getColumn(88);
		}catch(RuntimeException e){
			b= true;
		}
		assertTrue(b);
	}
	
	@Test
	public void testAddItem(){
		
		boolean b = false;
		try{
			SoldItem i1= new SoldItem(new StockItem(0L,"Burks","b",2.0,10),32);
			SoldItem i2 = new SoldItem(new StockItem(1L, "bruksKallim","b2",4.0,20),65);
			HashSet<SoldItem> ls = new HashSet<SoldItem>();
			ls.add(i1);
			ls.add(i2);
			hm = new HistoryItem(new ArrayList<SoldItem>(ls));
			phm.addItem(hm);
		}catch(IllegalArgumentException e){
			b=true;
		}
		assertFalse(b);
	}
}
