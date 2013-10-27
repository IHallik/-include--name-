package ee.ut.math.tvt.salessystem.ui.tabs;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;

/**
 * Encapsulates everything that has to do with the purchase tab (the tab
 * labelled "History" in the menu).
 */
public class HistoryTab {

	SalesSystemModel model;
	
    public HistoryTab(SalesSystemModel model) {
        this.model = model;
    }
    
    public Component draw() {
        JPanel panel = new JPanel();
        
        panel.setLayout(new GridBagLayout());

		JTable purchasesTable = new JTable(model.getPurchaseHistoryTableModel());
		JTable soldItemTable = new JTable();
		
		JScrollPane purchasesScrollPane = new JScrollPane(purchasesTable);
		purchasesScrollPane.setBorder(BorderFactory.createTitledBorder("Order History"));
		
		JScrollPane soldItemScrollPane = new JScrollPane(soldItemTable);
		soldItemScrollPane.setBorder(BorderFactory.createTitledBorder("Order details"));

		panel.add(purchasesScrollPane, getPurchasesScrollPaneConstraints1());
		panel.add(soldItemScrollPane, getItemScrollPaneConstraints());
        
        return panel;
    }
    
    private GridBagConstraints getPurchasesScrollPaneConstraints1() {
		GridBagConstraints gc = new GridBagConstraints();

		gc.fill = GridBagConstraints.BOTH;
		gc.weightx = 1.0;
		gc.weighty = 0.5;

		return gc;
	}
    
    private GridBagConstraints getItemScrollPaneConstraints() {
		GridBagConstraints gc = new GridBagConstraints();

		gc.fill = GridBagConstraints.BOTH;
		gc.gridy = 1;
		gc.weightx = 1.0;
		gc.weighty = 0.5;

		return gc;
	}
}