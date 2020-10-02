import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class Cart extends JFrame implements ActionListener  {
	
	private static final String LoginSessionID = null;
	JPanel p1,p2,p3,p4,p5;
	JLabel lbl1,lbl2,lbl3,lbl4,lbl5, lbl6, lbl7,lbl8,lbl9;
	
	JSpinner spn1;
	JButton btn1, btn2;
	ImageIcon gambar;
	JTable table, table2;
	JScrollPane scroll;
	DefaultTableModel tableModel;
	Vector<Object> Row,col;
	Vector<Object> Row2, col2;
	JTextField txt1,txt2; 
	Connect con = new Connect();
	ResultSet rs; 
	
	
	public Cart(){
		
		col = new Vector<Object>();
		col.add("Product ID");
		col.add("Product Name");
		col.add("Product Type");
		col.add("Product Price");
		
		p1 = new JPanel();
		p2 = new JPanel();
		p3 = new JPanel(new GridLayout(3,2,4,5));
		p4 = new JPanel(new GridLayout(2,1));
		p5 = new JPanel(new GridLayout(1,2,4,5));
		
		lbl1 = new JLabel("Name");
		lbl2 = new JLabel("Quantity");
		lbl3 = new JLabel("Price");
		lbl4 = new JLabel();
		lbl5 = new JLabel();
		lbl6 = new JLabel();
		lbl7 = new JLabel();
		lbl8 = new JLabel();
		lbl9 = new JLabel("Cart");
		
		txt1 = new JTextField();
		txt2 = new JTextField();
		
		spn1 = new JSpinner();
		
		btn1 = new JButton("Delete");
		btn2 = new JButton("Check Out");
		
		scroll = new JScrollPane();
		
		tableModel = new DefaultTableModel(Row,col);
		table = new JTable(tableModel);
		scroll = new JScrollPane(table);
		
		p1.add(lbl9);
		
		p2.add(scroll);
		
		p3.add(lbl1);
		p3.add(txt1);
		p3.add(lbl2);
		p3.add(spn1);
		p3.add(lbl3);
		p3.add(txt2);
		
		p4.add(btn1);
		p4.add(btn2);
		
		p5.add(p3);
		p5.add(p4);
		
		add(p1, BorderLayout.NORTH);
		add(p2, BorderLayout.CENTER);
		add(p5, BorderLayout.SOUTH);
		
		setTitle("My Cart");
		setSize(800,500);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		
				
		
	}
	
	
	
	private void tableRefreshProductList() {
		DefaultTableModel dtm = new DefaultTableModel(col, 0);
		ResultSet rs = con.executeQuery("SELECT ProductID, UserId, ProductQuantity FROM cart");
		int counter = 0;
		try {
			while (rs.next()) {
				Vector<Object> tableRow = new Vector<>();
				tableRow.add(rs.getObject(1));
				tableRow.add(rs.getObject(2));
				tableRow.add(rs.getObject(3));
				dtm.addRow(tableRow);
				counter++;
			}
			System.out.println(counter + " data selected from headphone table");
			table.setModel(dtm);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//vector yang nampung vector pilihan
	Vector<Object> saveTempCartData = new Vector<>();
	private void tableRefreshCartList() {
		DefaultTableModel dtmCart = new DefaultTableModel(col2, 0);
		//vector pilihan
		Vector<Object> cartTableRow = new Vector<>();
		cartTableRow.add(txt1.getText());
		cartTableRow.add(txt2.getText());
		cartTableRow.add(spn1.getValue().toString());
		
		
		if (saveTempCartData.size() >= 1) {
			System.out.println("Modify Existing Data");
			boolean ketemuSama = false;
			
			for (int i = 0; i < saveTempCartData.size(); i++) {
				
				if (  ( (Vector) saveTempCartData.get(i) ).get(0).toString().equals( cartTableRow.get(0).toString() )  ) {
					int existingValue = Integer.parseInt(  ( (Vector) saveTempCartData.get(i) ).get(3).toString()  );
					int newValue = Integer.parseInt(cartTableRow.get(3).toString());
					int jumlahLamaTambahJmlahBaru = existingValue + newValue;
					System.out.println("ketemu vector yang samam add existing data, new qty: " + jumlahLamaTambahJmlahBaru + "to vector:" + ((Vector) saveTempCartData.get(i) ).get(0).toString());
					((Vector) saveTempCartData.get(i)).set(3, jumlahLamaTambahJmlahBaru);
					
					i=saveTempCartData.size();
					ketemuSama = true;
				} 
			}
			
			
			if (ketemuSama == false) {
				System.out.println("add new data");
				saveTempCartData.add(cartTableRow);
				
			}
			
		} else {
			System.out.println("Data Pertama Masuk");
			saveTempCartData.add(cartTableRow);
		
		}
		
		
		DefaultTableModel dtmLoop = new DefaultTableModel(col2, 0);
		table.setModel(dtmLoop);
		for (int i = 0; i < saveTempCartData.size(); i++) {	

			dtmCart.addRow((Vector) saveTempCartData.get(i));
		}		
		table2.setModel(dtmCart);
	}
	
		private void addCartToDatabase() {
		if (saveTempCartData.size() == 0) {
			JOptionPane.showMessageDialog(this, "Please order at least 1 product", "Oops !", JOptionPane.WARNING_MESSAGE);
		} else {
			
			int TransactionID = generateTransactionID();
			String ProductID = "";
			int Quantity = 0; 
			
			String UserID = LoginSessionID;
			
			
			con.insertDetailTransaction(TransactionID, ProductID, Quantity);
			for (int i = 0; i < saveTempCartData.size(); i++) {
				ProductID = ((Vector)saveTempCartData.get(i)).get(0).toString();
				int Qty = Integer.parseInt(((Vector)saveTempCartData.get(i)).get(3).toString());
				con.insertDetailTransaction(TransactionID, ProductID, Qty);
			}
			
			for (int i = 0; i < saveTempCartData.size(); i++) {
				saveTempCartData.remove(i);
			}
			DefaultTableModel dtm0 = new DefaultTableModel(col2, 10);
			table2.setModel(dtm0);
			JOptionPane.showMessageDialog(this, "Success Check Out !", "Success", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private int generateTransactionID() {
			// TODO Auto-generated method stub
			return 0;
		}



	public static void main(String[] args) {
		
		new Cart();

	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}

