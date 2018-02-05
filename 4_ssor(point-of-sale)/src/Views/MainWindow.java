package Views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Containers.Product;
import Handlers.ProductHandler;
import Handlers.TransactionHandler;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class MainWindow {

	private JFrame frame;
	private JTextField productTF;
	private JTable productsTable;
	private JTextField totalTf;

	private ProductHandler products;
	private TransactionHandler transactions;
	private JTextField productFindedTF;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainWindow() {
		initialize();
	}


	private void initialize() {
		products = new ProductHandler();
		transactions = new TransactionHandler();

		frame = new JFrame();
		frame.setBounds(100, 100, 709, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel lblProducto = new JLabel("Producto");

		productTF = new JTextField();
		productTF.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();

		JLabel lblTotal = new JLabel("Total");

		totalTf = new JTextField();
		totalTf.setText("0");
		totalTf.setEditable(false);
		totalTf.setColumns(10);

		JButton btnSell = new JButton("Sell");
		btnSell.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				transactions.add(transactions.getTotal(), Float.parseFloat(totalTf.getText()), 
						true);
				clearElements();
			}
		});

		JButton btnAdd = new JButton("Add product");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) productsTable.getModel();
				Product aux = products.search(Integer.parseInt(productTF.getText()));
				float total = Float.parseFloat(totalTf.getText());
				model.addRow(new String[] {
						String.valueOf(aux.getId()),
						aux.getName(),
						String.valueOf(aux.getPrice())
				});
				total += aux.getPrice();
				totalTf.setText(String.valueOf(total));
			}
		});
		
		productFindedTF = new JTextField();
		productFindedTF.setEditable(false);
		productFindedTF.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Product aux = products.search(Integer.parseInt(productTF.getText()));
				if(aux != null) {
					productFindedTF.setText(aux.toString());
				}
				else {
					productTF.setText("");
					productFindedTF.setText("");
				}
			}
		});
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearElements();
			}
		});
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 685, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblProducto)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(productTF, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnSearch)
							.addGap(18)
							.addComponent(productFindedTF, GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(btnAdd))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblTotal)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(totalTf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 364, Short.MAX_VALUE)
							.addComponent(btnCancel)
							.addGap(18)
							.addComponent(btnSell)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblProducto)
						.addComponent(productTF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAdd)
						.addComponent(productFindedTF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSearch))
					.addGap(9)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 341, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblTotal)
							.addComponent(totalTf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnSell))
						.addComponent(btnCancel))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);

		productsTable = new JTable();
		productsTable.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Id", "Name", "Price"
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
		scrollPane.setViewportView(productsTable);
		frame.getContentPane().setLayout(groupLayout);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnActions = new JMenu("Products");
		menuBar.add(mnActions);

		JMenuItem mntmBuyProducts = new JMenuItem("Buy");
		mntmBuyProducts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuyProductDialog dialog = new BuyProductDialog();
				dialog.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent we) {
						if(dialog.accepted()) {
							products.add(products.getTotal(), dialog.getName(), dialog.getPrice(), dialog.getTotalProduct());
							transactions.add(transactions.getTotal(), dialog.getTotal(), false);
						}
					}
				});
				dialog.setVisible(true);
			}
		});
		mnActions.add(mntmBuyProducts);

		JMenuItem mntmShowProducts = new JMenuItem("Show");
		mntmShowProducts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShowProductDialog dialog = new ShowProductDialog(products);
				dialog.setVisible(true);
			}
		});
		mnActions.add(mntmShowProducts);

		JMenu mnTransactions = new JMenu("Transactions");
		menuBar.add(mnTransactions);

		JMenuItem mntmShow = new JMenuItem("Show");
		mntmShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShowTransactionsDialog dialog = new ShowTransactionsDialog(transactions);
				dialog.setVisible(true);
			}
		});
		mnTransactions.add(mntmShow);
	}
	
	private void clearElements() {
		DefaultTableModel model = (DefaultTableModel)productsTable.getModel();
		for(int i = model.getRowCount()-1; i >= 0; i--)
			model.removeRow(i);
		totalTf.setText("0");
		productFindedTF.setText("");
		productTF.setText("");
		
	}
}
