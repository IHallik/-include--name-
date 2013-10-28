package ee.ut.math.tvt.salessystem.ui.tabs;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.MaskFormatter;

import ee.ut.math.tvt.salessystem.domain.exception.VerificationFailedException;

public class ConfirmationTab {

	public static JFrame popUpWindow(final double totalCost) throws ParseException, VerificationFailedException {
		final JFrame item = new JFrame();
		item.setAlwaysOnTop(true);
		item.setSize(new Dimension(200, 200));
		item.setTitle("Confirm Order");
		item.setResizable(false);
		JPanel popUp = new JPanel();
		popUp.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		popUp.setLayout(new GridBagLayout());
		popUp.setSize(200, 200);
		GridBagConstraints c = new GridBagConstraints();
		final DoubleField moneyBack = new DoubleField();
		//MaskFormatter dateMask = new MaskFormatter("####.##");
		//dateMask.install(moneyBack);
		moneyBack.setEditable(false);
		JLabel costDisplay = new JLabel("Your order cost is : " + totalCost);
		final DoubleField payMoney = new DoubleField();
		//dateMask.install(payMoney);
		
		item.add(popUp);
		payMoney.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent arg0) {
				data();

			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				data();

			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				data();

			}

			private void data() {
				try {
					if (!payMoney.getText().isEmpty()) {
						double changeBack = Double.parseDouble(payMoney
								.getText()) - totalCost;
						changeBack = Math.round(changeBack * 100.0) / 100.0;
						moneyBack.setText(changeBack + "");
					}
				} catch (NumberFormatException e) {

				}
			}
		});

		costDisplay.setSize(300, 75);
		c.gridx = 0;
		c.gridy = 0;
		popUp.add(costDisplay, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		popUp.add(payMoney, c);

		c.gridx = 0;
		c.gridy = 2;
		JLabel dp = new JLabel("Payment Amount");
		popUp.add(dp, c);
		
		c.gridx = 1;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		popUp.add(moneyBack, c);

		c.gridx = 0;
		c.gridy = 3;
		JButton confirm = new JButton("Confirm");
		confirm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (Double.parseDouble(moneyBack.getText()) >= 0.0) {
						//This is where it should fire fireConfirmationStatus(boolean)
						item.dispose();
					}
				} catch (NumberFormatException e) {

				}
			}

		});
		popUp.add(confirm, c);

		c.gridx = 1;
		c.gridy = 3;
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				PurchaseTab.purchasePane.setEnabled(true);
				PurchaseTab.submitPurchase.setEnabled(true);
				PurchaseTab.cancelPurchase.setEnabled(true);
				PurchaseTab.newPurchase.setEnabled(false);
				item.dispose();

			}

		});
		popUp.add(cancel, c);

		return item;
	}

}
