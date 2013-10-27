package ee.ut.math.tvt.salessystem.ui.tabs;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.JTableHeader;

import org.apache.log4j.Logger;

import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;


public class StockTab {
	
  private static final Logger log = Logger.getLogger(PurchaseTab.class);

  private JButton addItem;

  private SalesSystemModel model;

  public StockTab(SalesSystemModel model) {
    this.model = model;
  }

  // warehouse stock tab - consists of a menu and a table
  public Component draw() {
    JPanel panel = new JPanel();
    panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

    GridBagLayout gb = new GridBagLayout();
    GridBagConstraints gc = new GridBagConstraints();
    panel.setLayout(gb);

    gc.fill = GridBagConstraints.HORIZONTAL;
    gc.anchor = GridBagConstraints.NORTH;
    gc.gridwidth = GridBagConstraints.REMAINDER;
    gc.weightx = 1.0d;
    gc.weighty = 0d;

    panel.add(drawStockMenuPane(), gc);

    gc.weighty = 1.0;
    gc.fill = GridBagConstraints.BOTH;
    panel.add(drawStockMainPane(), gc);
    return panel;
  }

  // warehouse menu
  private Component drawStockMenuPane() {
    JPanel panel = new JPanel();

    GridBagConstraints gc = new GridBagConstraints();
    GridBagLayout gb = new GridBagLayout();

    panel.setLayout(gb);

    gc.anchor = GridBagConstraints.NORTHWEST;
    gc.weightx = 0;

    addItem = createAddButton();
    gc.gridwidth = GridBagConstraints.RELATIVE;
    gc.weightx = 1.0;
    panel.add(addItem, gc);

    panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    return panel;
  }

  // Creates the button "Add"
  private JButton createAddButton() {
    JButton b = new JButton("Add");
    b.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
    	  AddButtonClicked();
      }
    });

    return b;
  }

  /* === Event handlers for the menu buttons
   *     (get executed when the buttons are clicked)
   */


  /** Event handler for the <code>Add</code> event. */
  protected void AddButtonClicked() {
    log.info("New sale process started");
        
    JFrame panel = new JFrame("Add new product");
    
    GridBagLayout gb = new GridBagLayout();    
    panel.setLayout(gb);    
    
    GridBagConstraints gc_L = new GridBagConstraints();
    gc_L.fill = GridBagConstraints.HORIZONTAL;
    gc_L.anchor = GridBagConstraints.WEST;
    gc_L.gridwidth = GridBagConstraints.REMAINDER;
    gc_L.fill = GridBagConstraints.BOTH;
    
    GridBagConstraints gc_R = new GridBagConstraints();
    gc_R.fill = GridBagConstraints.HORIZONTAL;
    gc_R.anchor = GridBagConstraints.EAST;
    gc_R.gridwidth = GridBagConstraints.REMAINDER;
    gc_R.fill = GridBagConstraints.BOTH;
    
    
    JButton addNewProductButton = new JButton("Add new Product");
    JLabel idLabel = new JLabel("Id:");
    JLabel nameLabel = new JLabel("Name:");
    JLabel descriptionLabel = new JLabel("Description:");
    JLabel priceLabel = new JLabel("Price:");
    JLabel quantityLabel = new JLabel("Quantity:");
    
    JTextField idTextField = new IntegerField();
    JTextField nameTextField = new JTextField();
    JTextField descriptionTextField = new JTextField();
    JTextField priceTextField = new DoubleField();
    JTextField quantityTextField = new IntegerField();
    
    panel.add(idLabel,gc_L);
    panel.add(idTextField,gc_R);
    
    panel.add(nameLabel,gc_L);
    panel.add(nameTextField,gc_R);
    
    panel.add(descriptionLabel,gc_L);
    panel.add(descriptionTextField,gc_R);
    
    panel.add(priceLabel,gc_L);
    panel.add(priceTextField,gc_R);
    
    panel.add(quantityLabel,gc_L); 
    panel.add(quantityTextField,gc_R);
    
    panel.add(addNewProductButton,gc_R);
    
    panel.setSize(160, 250);
    panel.setResizable(false);
    panel.setVisible(true);
    
    
    
    StockItem newProduct = new StockItem(Long.parseLong(idTextField.getText()),
    									nameTextField.getText(), 
    									descriptionLabel.getText(), 
    									Double.parseDouble(priceTextField.getText()),
    									Integer.parseInt(quantityTextField.getText())
    									);
    
    
  }
  
  // table of the wareshouse stock
  private Component drawStockMainPane() {
    JPanel panel = new JPanel();

    JTable table = new JTable(model.getWarehouseTableModel());
    JTableHeader header = table.getTableHeader();
    header.setReorderingAllowed(false);

    JScrollPane scrollPane = new JScrollPane(table);

    GridBagConstraints gc = new GridBagConstraints();
    GridBagLayout gb = new GridBagLayout();
    gc.fill = GridBagConstraints.BOTH;
    gc.weightx = 1.0;
    gc.weighty = 1.0;

    panel.setLayout(gb);
    panel.add(scrollPane, gc);

    panel.setBorder(BorderFactory.createTitledBorder("Warehouse status"));
    return panel;
  }

}
