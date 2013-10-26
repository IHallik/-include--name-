package ee.ut.math.tvt.salessystem.ui.model;

import org.apache.log4j.Logger;

import ee.ut.math.tvt.salessystem.domain.data.HistoryItem;

/**
 * Purchase history details model.
 */
public class PurchaseHistoryTableModel extends
		SalesSystemTableModel<HistoryItem> {
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger
			.getLogger(PurchaseInfoTableModel.class);

	public PurchaseHistoryTableModel() {
		super(new String[] { "Id", "Order date", "Order time", "Order price" });
	}

	@Override
	protected Object getColumnValue(HistoryItem item, int columnIndex) {
		try {
			return item.getColumn(columnIndex);
		} catch (RuntimeException e) {
			throw new IllegalArgumentException("Column index out of range:" + columnIndex +
					" Column count:" + getColumnCount() + "\n" +
					"Error:" + e);
		}
	}

	@Override
	public String toString() {
		final StringBuffer buffer = new StringBuffer();

		for (int i = 0; i < headers.length; i++)
			buffer.append(headers[i] + "\t");
		buffer.append("\n");

		for (final HistoryItem item : rows) {
			buffer.append(item.getId() + "\t");
			buffer.append(item.getOrderDateString() + "\t");
			buffer.append(item.getOrderTimeString() + "\t");
			buffer.append(item.getOrderPrice() + "\t");
			buffer.append("\n");
		}

		return buffer.toString();
	}

	/**
	 * Add new StockItem to table.
	 * 
	 * @throws IllegalArgumentException
	 */
	public void addItem(final HistoryItem item) throws IllegalArgumentException {
		rows.add(item);
		log.debug("Purchase at " + item.getOrderTimeString() + " recorded with total=" + item.getOrderPrice());
		fireTableDataChanged();
	}
}
