package Views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Containers.Product;
import Containers.Transactions;
import Handlers.ProductHandler;
import Handlers.TransactionHandler;

public class ShowTransactionsDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable transactionsTable;

	public ShowTransactionsDialog(TransactionHandler transactions) {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JLabel lblTransactions = new JLabel("Transactions");
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTransactions)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(12)
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addComponent(lblTransactions)
					.addGap(15)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
					.addContainerGap())
		);
		{
			transactionsTable = new JTable();
			transactionsTable.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Id", "Sell/Buy", "Total"
				}
			) {
				Class[] columnTypes = new Class[] {
					String.class, String.class, String.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
				boolean[] columnEditables = new boolean[] {
					false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
			scrollPane.setViewportView(transactionsTable);
		}
		contentPanel.setLayout(gl_contentPanel);
		
		fillTable(transactions);
	}

	private void fillTable(TransactionHandler transactions) {
		DefaultTableModel model = (DefaultTableModel) transactionsTable.getModel();
		Transactions aux;		
		for(int i = 0, j = transactions.getTotal(); i < j; i++) {
			aux = transactions.get(i);
			model.addRow(new String[] {
					String.valueOf(aux.getId()),
					(aux.isSell())?"Sell": "Buy",
					String.valueOf(aux.getTotal())
			});
		}
	}
	
}
