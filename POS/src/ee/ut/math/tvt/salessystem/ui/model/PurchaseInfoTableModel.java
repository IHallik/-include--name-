package ee.ut.math.tvt.salessystem.ui.model;

import java.util.Locale;

import org.apache.log4j.Logger;

import ee.ut.math.tvt.salessystem.domain.data.SoldItem;

/**
 * Purchase history details model.
 */
public class PurchaseInfoTableModel extends SalesSystemTableModel<SoldItem> {
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(PurchaseInfoTableModel.class);

	public PurchaseInfoTableModel() {
		super(new String[] { "Id", "Name", "Price", "Quantity", "Sum"});
	}

	@Override
	protected Object getColumnValue(SoldItem item, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return item.getId();
		case 1:
			return item.getName();
		case 2:
			return String.format(Locale.ENGLISH, "%.2f", item.getPrice());
		case 3:
			return item.getQuantity();
		case 4:
			return String.format(Locale.ENGLISH, "%.2f", item.getSum());
		}
		throw new IllegalArgumentException("Column index out of range");
	}

	@Override
	public String toString() {
		final StringBuffer buffer = new StringBuffer();

		for (int i = 0; i < headers.length; i++)
			buffer.append(headers[i] + "\t");
		buffer.append("\n");

		for (final SoldItem item : rows) {
			buffer.append(item.getId() + "\t");
			buffer.append(item.getName() + "\t");
			buffer.append(String.format(Locale.ENGLISH, "%.2f", item.getPrice()) + "\t");
			buffer.append(item.getQuantity() + "\t");
			buffer.append(String.format(Locale.ENGLISH, "%.2f", item.getSum()) + "\t");
			buffer.append("\n");
		}

		return buffer.toString();
	}

	/**
	 * Add new StockItem to table.
	 * @throws IllegalArgumentException 
	 */
	public void addItem(final SoldItem item) throws IllegalArgumentException {
		// Count how many of the items are already in order
		int qtyInUse = 0;
		int itemIndex = 0;
		for(; itemIndex < rows.size(); itemIndex++) {
			if(rows.get(itemIndex).getId() == item.getId()) {
				qtyInUse += rows.get(itemIndex).getQuantity();
				break;
			}
		}

		if(qtyInUse == 0) {
			rows.add(item);
			fireTableDataChanged();
		} else {
			rows.get(itemIndex).setQuantity(qtyInUse + item.getQuantity());
			log.debug("Added " + item.getName() + " quantity of " + item.getQuantity());
			fireTableDataChanged();
		}
	}
}
