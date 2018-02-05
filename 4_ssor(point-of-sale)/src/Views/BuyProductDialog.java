package Views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BuyProductDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField nameTf;
	private JTextField totalTf;
	private JSpinner totalS;
	private JSpinner priceS;
	boolean accept;

	/**
	 * Create the dialog.
	 */
	public BuyProductDialog() {
		accept = false;
		setBounds(100, 100, 212, 273);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JLabel lblBuyProduct = new JLabel("Buy product");
		lblBuyProduct.setFont(new Font("Dialog", Font.BOLD, 14));
		JLabel lblName = new JLabel("Name");
		nameTf = new JTextField();
		nameTf.setColumns(10);
		JLabel lblPrice = new JLabel("Price");
		
		priceS = new JSpinner();
		priceS.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent arg0) {
				totalTf.setText(String.valueOf(calculateTotal()));			
			}
		});
		priceS.setModel(new SpinnerNumberModel(0, null, null, 1));
		
		JLabel lblTotalProduct = new JLabel("Total product");
		
		totalS = new JSpinner();
		totalS.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent arg0) {
				totalTf.setText(String.valueOf(calculateTotal()));				
			}
		});
		totalS.setModel(new SpinnerNumberModel(1, 1, null, 1));
		
		JLabel lblTotal = new JLabel("Total");
		
		totalTf = new JTextField();
		totalTf.setText("0");
		totalTf.setEditable(false);
		totalTf.setColumns(10);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
							.addComponent(lblBuyProduct)
							.addGroup(gl_contentPanel.createSequentialGroup()
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
									.addComponent(lblName)
									.addComponent(lblPrice))
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
									.addComponent(priceS)
									.addComponent(nameTf)))
							.addGroup(gl_contentPanel.createSequentialGroup()
								.addComponent(lblTotalProduct)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(totalS)))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblTotal)
							.addGap(31)
							.addComponent(totalTf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(202, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblBuyProduct)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblName)
						.addComponent(nameTf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPrice)
						.addComponent(priceS, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTotalProduct)
						.addComponent(totalS, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTotal)
						.addComponent(totalTf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(184, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						close(true);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						close(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	private float calculateTotal() {
		return Float.parseFloat(priceS.getValue().toString()) * 
				Integer.parseInt(totalS.getValue().toString());
	}
	
	private void close(boolean accept) {
		this.accept = accept;
		if(!accept || nameTf.getText().compareTo("") != 0)
			dispose();
	}
	
	public boolean accepted() {
		return accept;
	}
	
	public String getName() {
		return nameTf.getText();
	}
	
	public int getTotalProduct() {
		return Integer.parseInt(totalS.getValue().toString());
	}
	
	public float getPrice() {
		return Float.parseFloat(priceS.getValue().toString());
	}
	
	public float getTotal() {
		return Float.parseFloat(totalTf.getText());
	}
}
