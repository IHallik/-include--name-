package ee.ut.math.tvt.salessystem.ui.panels;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import java.util.NoSuchElementException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;

/**
 * Purchase pane + shopping cart tabel UI.
 */
public class PurchaseItemPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	// Text field on the dialogPane
	private JTextField barCodeField;
	private JTextField quantityField;
	private JTextField nameField;
	private JTextField priceField;

	private JComboBox<StockItem> productSelectionField;

	private JButton addItemButton;

	// Warehouse model
	private SalesSystemModel model;

	/**
	 * Constructs new purchase item panel.
	 * 
	 * @param model
	 *            composite model of the warehouse and the shopping cart.
	 */
	public PurchaseItemPanel(SalesSystemModel model) {
		this.model = model;

		setLayout(new GridBagLayout());

		add(drawDialogPane(), getDialogPaneConstraints());
		add(drawBasketPane(), getBasketPaneConstraints());

		setEnabled(false);
	}

	// shopping cart pane
	private JComponent drawBasketPane() {

		// Create the basketPane
		JPanel basketPane = new JPanel();
		basketPane.setLayout(new GridBagLayout());
		basketPane.setBorder(BorderFactory.createTitledBorder("Shopping cart"));

		// Create the table, put it inside a scollPane,
		// and add the scrollPane to the basketPanel.
		JTable table = new JTable(model.getCurrentPurchaseTableModel());
		JScrollPane scrollPane = new JScrollPane(table);

		basketPane.add(scrollPane, getBacketScrollPaneConstraints());

		return basketPane;
	}

	// purchase dialog
	private JComponent drawDialogPane() {
		// Create the panel
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		panel.setBorder(BorderFactory.createTitledBorder("Product"));

		StockItem[] items = new StockItem[model.getWarehouseTableModel().getRowCount()];
		model.getWarehouseTableModel().getTableRows().toArray(items);

		// Initialize the textfields
		productSelectionField = new JComboBox<StockItem>(items);
		barCodeField = new JTextField();
		quantityField = new JTextField("1");
		nameField = new JTextField();
		priceField = new JTextField();

		// Fill the dialog fields if the bar code text field loses focus
		productSelectionField.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent a) {
				if(a.getStateChange() == ItemEvent.SELECTED) {
					fillDialogFields((StockItem)a.getItem());
				}
			}
		});

		barCodeField.setEditable(false);
		nameField.setEditable(false);
		priceField.setEditable(false);
		
		nameField.setMinimumSize(new Dimension(100,20));

		// == Add components to the panel
		panel.add(productSelectionField, getItemConstraints(0,0,2));

		// - bar code
		panel.add(new JLabel("Bar code:"), getItemConstraints(0,1,1));
		panel.add(barCodeField, getItemConstraints(1,1,1));

		// - amount
		panel.add(new JLabel("Amount:"), getItemConstraints(0,2,1));
		panel.add(quantityField, getItemConstraints(1,2,1));

		// - name
		panel.add(new JLabel("Name:"), getItemConstraints(0,3,1));
		panel.add(nameField, getItemConstraints(1,3,1));

		// - price
		panel.add(new JLabel("Price:"), getItemConstraints(0,4,1));
		panel.add(priceField, getItemConstraints(1,4,1));

		// Create and add the button
		addItemButton = new JButton("Add to cart");
		addItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addItemEventHandler();
			}
		});

		panel.add(addItemButton, getItemConstraints(0,5,2));

		return panel;
	}

	// Fill dialog with data from the "database".
	public void fillDialogFields(StockItem stockItem) {
		if (stockItem != null) {
			barCodeField.setText(stockItem.getId() + "");
			nameField.setText(stockItem.getName());
			String priceString = String.valueOf(stockItem.getPrice());
			priceField.setText(priceString);
		} else {
			reset();
		}
	}

	// Search the warehouse for a StockItem with the bar code entered
	// to the barCode textfield.
	// Unused functionality
	private StockItem getStockItemByBarcode() {
		try {
			int code = Integer.parseInt(barCodeField.getText());
			return model.getWarehouseTableModel().getItemById(code);
		} catch (NumberFormatException ex) {
			return null;
		} catch (NoSuchElementException ex) {
			return null;
		}
	}

	/**
	 * Add new item to the cart.
	 */
	public void addItemEventHandler() {
		// add chosen item to the shopping cart.
		StockItem stockItem = (StockItem)productSelectionField.getSelectedItem();
		
		if (stockItem != null) {
			int quantity;
			try {
				quantity = Integer.parseInt(quantityField.getText());
			} catch (NumberFormatException ex) {
				quantity = 1;
			}

			if(quantity > 0) {
				int qtyInUse = 0;
				int itemIndex = 0;
				List<SoldItem> rows = model.getCurrentPurchaseTableModel().getTableRows();
				for(; itemIndex < rows.size(); itemIndex++) {
					if(rows.get(itemIndex).getId() == stockItem.getId()) {
						qtyInUse += rows.get(itemIndex).getQuantity();
						break;
					}
				}
				
				if(quantity + qtyInUse <= stockItem.getQuantity()) {
					model.getCurrentPurchaseTableModel().addItem(new SoldItem(stockItem, quantity));
				} else {
					JOptionPane.showMessageDialog(null,
            		    "Warehouse has less than the requested quantity of that item.",
            		    "Error",
            		    JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null,
            		    "Quantity must be 1 or greater.",
            		    "Error",
            		    JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/**
	 * Sets whether or not this component is enabled.
	 */
	@Override
	public void setEnabled(boolean enabled) {
		this.addItemButton.setEnabled(enabled);
		this.barCodeField.setEnabled(enabled);
		this.quantityField.setEnabled(enabled);
		this.productSelectionField.setEnabled(enabled);
	}

	/**
	 * Reset dialog fields.
	 */
	public void reset() {
		productSelectionField.setSelectedItem(null);
		barCodeField.setText("");
		quantityField.setText("1");
		nameField.setText("");
		priceField.setText("");
	}

	/*
	 * === Ideally, UI's layout and behavior should be kept as separated as
	 * possible. If you work on the behavior of the application, you don't want
	 * the layout details to get on your way all the time, and vice versa. This
	 * separation leads to cleaner, more readable and better maintainable code.
	 * 
	 * In a Swing application, the layout is also defined as Java code and this
	 * separation is more difficult to make. One thing that can still be done is
	 * moving the layout-defining code out into separate methods, leaving the
	 * more important methods unburdened of the messy layout code. This is done
	 * in the following methods.
	 */
	
	// Formatting constraints for dialogPane items
	private GridBagConstraints getItemConstraints(int gridx, int gridy, int gridwidth) {
		GridBagConstraints gc = new GridBagConstraints();

		gc.insets = new Insets(0, 4, 4, 0);
		gc.gridwidth = gridwidth;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.gridx = gridx;
		gc.gridy = gridy;
		gc.gridwidth = gridwidth;

		return gc;
	}

	// Formatting constraints for the dialogPane
	private GridBagConstraints getDialogPaneConstraints() {
		GridBagConstraints gc = new GridBagConstraints();

		gc.anchor = GridBagConstraints.WEST;
		gc.weightx = 1.0;
		gc.weighty = 0d;
		gc.gridwidth = GridBagConstraints.REMAINDER;
		gc.fill = GridBagConstraints.NONE;

		return gc;
	}

	// Formatting constraints for the basketPane
	private GridBagConstraints getBasketPaneConstraints() {
		GridBagConstraints gc = new GridBagConstraints();

		gc.anchor = GridBagConstraints.WEST;
		gc.weightx = 0.2;
		gc.weighty = 1.0;
		gc.gridwidth = GridBagConstraints.REMAINDER;
		gc.fill = GridBagConstraints.BOTH;

		return gc;
	}

	private GridBagConstraints getBacketScrollPaneConstraints() {
		GridBagConstraints gc = new GridBagConstraints();

		gc.fill = GridBagConstraints.BOTH;
		gc.weightx = 1.0;
		gc.weighty = 1.0;

		return gc;
	}

}
