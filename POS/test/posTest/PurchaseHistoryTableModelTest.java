package posTest;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.data.HistoryItem;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.ui.model.PurchaseHistoryTableModel;

public class PurchaseHistoryTableModelTest {
	
	private PurchaseHistoryTableModel phm;
	private HistoryItem hm;	
	
	@SuppressWarnings("unchecked")
	@Before
	public void setUp(){
		SoldItem i1= new SoldItem(new StockItem(0L,"Burks","b",2.0,10),3);
		SoldItem i2 = new SoldItem(new StockItem(1L, "bruksKallim","b2",4.0,20),5);
		HashSet<SoldItem> ls = new HashSet<SoldItem>();
		ls.add(i1);
		ls.add(i2);
		hm = new HistoryItem((List<SoldItem>) ls);
		phm.addItem(hm);
	}
	
	
	@Test
	public void getColumnValueException(){
		boolean b = false;
		try{
			hm.getColumn(88);
		}catch(IllegalArgumentException e){
			b= true;
		}
		assertTrue(b);
	}
	
	@Test
	public void noColumnValueException(){
		boolean b = false;
		try{
			//phm.getColumnValue(hm,0);			This cant be used due to protected
			hm.getColumn(0);
		}catch(IllegalArgumentException e){
			b= true;
		}
		assertTrue(b);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void addItemTest(){
		
		boolean b = false;
		try{
			SoldItem i1= new SoldItem(new StockItem(0L,"Burks","b",2.0,10),32);
			SoldItem i2 = new SoldItem(new StockItem(1L, "bruksKallim","b2",4.0,20),65);
			HashSet<SoldItem> ls = new HashSet<SoldItem>();
			ls.add(i1);
			ls.add(i2);
			hm = new HistoryItem((List<SoldItem>) ls);
			phm.addItem(hm);
		}catch(IllegalArgumentException e){
			b=true;
		}
		assertTrue(b);
	}
}
