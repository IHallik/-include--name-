package posTest;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.ui.model.StockTableModel;

public class StockTableModelTest {
	
	private StockTableModel stock;
	private int quantity = 10;
	private StockItem item;
	private Long id;
	
	
	@Before
	public void setUp(){
		
		item = new StockItem(88L, "Ussripik", "just nii ongi", 42.0, 17);
		stock = new StockTableModel();
		stock.addItem(item);
		id=item.getId();
	}
	
	@Test
	public void testValidateNameUniqueness(){
		boolean bool = false;
		for(long i=0; i<stock.getRowCount();i++){
			for(long j=i+1;j<stock.getRowCount();j++){
				if(stock.getItemById(i).getName().equals(stock.getItemById(j).getName())){
					bool=true;
					break;
				}
			}
		}
		assertTrue(bool);
	}
	
	@Test
	public void testHasEnoughInStock(){
		assertTrue(stock.getItemById(item.getId()).getQuantity()>=quantity);
	}
	
	@Test
	public void testGetItemByIdWhenItemExists(){
		boolean bool = false;
		try{
			stock.getItemById(id);
		}catch(NoSuchElementException e){
			bool = true;
		}
		assertTrue(bool);
	}
	
	@Test
	public void testGetItemByIdWhenThrowsException(){
		id =72L;
		boolean bool = false;
		try{
			stock.getItemById(id);
		}catch(NoSuchElementException e){
			bool = true;
		}
		assertTrue(bool);
	}
	
	

}
