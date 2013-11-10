package ee.ut.math.tvt.salessystem.domain.data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Container of SoldItems. Holds price total, datetime of sale and item list
 */
@Entity
@Table(name = "SALE")
public class HistoryItem implements DisplayableItem {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "sale_date")
	private Calendar orderDateTime;
	
	@Column(name = "price_total")
	private double priceTotal;
	
	@OneToMany(mappedBy = "sale")
	private Set<SoldItem> cart;
	
	//XXX - Deprecated with db
	static long counter = 0;
	
	public HistoryItem() {
		cart = new HashSet<SoldItem>();
		priceTotal = 0;
	}

	public HistoryItem(List<SoldItem> cart) {
		this.cart = new HashSet<SoldItem>();
		this.cart.addAll(cart);
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

	public Set<SoldItem> getSoldItems() {
		return cart;
	}
	
	public Object getColumn(int columnIndex) {
        switch(columnIndex) {
            case 0: return id;
            case 1: return getOrderDateString();
            case 2: return getOrderTimeString();
            case 3: return String.format(Locale.ENGLISH, "%.2f", new Double(priceTotal));
            default: throw new RuntimeException("invalid column!");
        }
    }
}
