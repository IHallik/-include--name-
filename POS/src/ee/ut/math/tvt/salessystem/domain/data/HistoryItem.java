package ee.ut.math.tvt.salessystem.domain.data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Container of SoldItems. Holds price total, datetime of sale and item list
 */
public class HistoryItem implements DisplayableItem {
	private Long id;

	private Calendar orderDateTime;
	private double priceTotal;
	private List<SoldItem> cart;
	
	static long counter = 0;

	public HistoryItem(List<SoldItem> cart) {
		this.cart = cart;
		id = counter++;
		orderDateTime = new GregorianCalendar();
		priceTotal = 0;

		for(SoldItem item : cart) {
			priceTotal += item.getSum();
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getOrderPrice() {
		return priceTotal;
	}
	
	public String getOrderDateString() {
		DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		return df.format(orderDateTime.getTime());
	}
	
	public String getOrderTimeString() {
		DateFormat df = new SimpleDateFormat("HH:mm:ss");
		return df.format(orderDateTime.getTime());
	}

	public Integer getUniqueItemCount() {
		return cart.size();
	}

	public List<SoldItem> getStockItemList() {
		return cart;
	}
	
	public Object getColumn(int columnIndex) {
        switch(columnIndex) {
            case 0: return id;
            case 1: return getOrderDateString();
            case 2: return getOrderTimeString();
            case 3: return new Double(priceTotal);
            default: throw new RuntimeException("invalid column!");
        }
    }
}
