import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Vector;

import javax.swing.AbstractButton;
import javax.swing.ButtonModel;
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
import javax.swing.table.DefaultTableModel;

public class ProductForm extends JFrame implements ActionListener {
	
	JPanel p1,p2,p3,p4,p5;
	JLabel lbl1,lbl2,lbl3,lbl4,lbl5, lbl6, lbl7,lbl8,lbl9;
	
	JSpinner spn1;
	JButton btn1, btn2;
	JTextField txt1,txt2,txt3;
	ImageIcon gambar;
	JTable table, tableCart;
	JScrollPane scroll;
	DefaultTableModel tableModel;
	Vector<Object> Row,col;
	Vector<Object> Row2,col2;
	
	Connect con = new Connect();
	ResultSet rs;
	
	
	public ProductForm(){
		
		col = new Vector<Object>();
		col.add("Product ID");
		col.add("Product Name");
		col.add("Product Type");
		col.add("Product Price");

		
		
		p1 = new JPanel();
		p2 = new JPanel();
		p3 = new JPanel(new GridLayout(5,2,4,5));
		p4 = new JPanel(new GridLayout(1,2));
		p5 = new JPanel();
		
		lbl1 = new JLabel("ID");
		lbl2 = new JLabel("Name");
		lbl3 = new JLabel("Price");
		lbl4 = new JLabel("Quantity");
		lbl5 = new JLabel();
		lbl6 = new JLabel();
		lbl7 = new JLabel();
		lbl8 = new JLabel();
		lbl9 = new JLabel();
		
		txt1= new JTextField();
		txt2= new JTextField();
		txt3= new JTextField();
		
		spn1 = new JSpinner();
		
		btn1 = new JButton("Reset");
		btn2 = new JButton("Add to Cart");
		btn2.addActionListener(this);
		
		scroll = new JScrollPane();
		
		tableModel = new DefaultTableModel(Row,col);
		table = new JTable(tableModel);
		scroll = new JScrollPane(table);
		
		p1.add(lbl9);
		
		p2.add(scroll);
		
		p3.add(lbl1);
		p3.add(txt2);
		p3.add(lbl2);
		p3.add(txt2);
		p3.add(lbl3);
		p3.add(txt3);
		p3.add(lbl4);
		p3.add(spn1);
		p3.add(btn1);
		p3.add(btn2);
		
		p4.add(lbl5);
		p4.add(p3);
		
		add(p1, BorderLayout.NORTH);
		add(p2, BorderLayout.CENTER);
		add(p4, BorderLayout.SOUTH);
		
		setTitle("Products");
		setSize(800,500);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	private void tableRefreshProductList() {
		DefaultTableModel dtm = new DefaultTableModel(col, 0);
		ResultSet rs = con.executeQuery("SELECT ProductID, ProductName,ProductPrice, ProductCategory FROM product");
		int counter = 0;
		try {
			while (rs.next()) {
				Vector<Object> tableRow = new Vector<>();
				tableRow.add(rs.getObject(1));
				tableRow.add(rs.getObject(2));
				tableRow.add(rs.getObject(3));
				tableRow.add(rs.getObject(4));
				tableRow.add(rs.getObject(5));
				tableRow.add(rs.getObject(6));
				dtm.addRow(tableRow);
				counter++;
			}
			System.out.println(counter + " data selected from headphone table");
			table.setModel(dtm);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	Vector<Object> saveTempCartData = new Vector<>();
	private void tableRefreshCartList() {
		DefaultTableModel dtmCart = new DefaultTableModel(col2, 0);
		
		Vector<Object> cartTableRow = new Vector<>();
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
					textBoxAction(3);
					i=saveTempCartData.size();
					ketemuSama = true;
				} 
			}
			
			
			if (ketemuSama == false) {
				System.out.println("add new data");
				saveTempCartData.add(cartTableRow);
				textBoxAction(3);
			}
			
		} else {
			System.out.println("Data Pertama Masuk");
			saveTempCartData.add(cartTableRow);
			textBoxAction(3);
		}
		
		
		DefaultTableModel dtmLoop = new DefaultTableModel(col2, 0);
		
		tableCart.setModel(dtmLoop);
		for (int i = 0; i < saveTempCartData.size(); i++) {	

			dtmCart.addRow((Vector) saveTempCartData.get(i));
		}

		tableCart.setModel( dtmCart);
	}
	
	private void textBoxAction(int i) {
		// TODO Auto-generated method stub
		
	}

	private void addToCartTable() {
		if (txt1.getText().equals("") || txt2.getText().equals("") || txt3.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Please select a product", "Oops !", JOptionPane.WARNING_MESSAGE);
		} else if (Integer.parseInt(spn1.getValue().toString()) < 1) {
			JOptionPane.showMessageDialog(this, "Please order at least 1 product", "Oops !", JOptionPane.WARNING_MESSAGE);
		} else {
			tableRefreshCartList();
			System.out.println("Menjalankan fungsi add to cart");
		}
	}
	
	private int generateTransactionID() {
		int hasil = 0;
		ResultSet rs = con.executeQuery("SELECT * FROM headertransaction ORDER BY TransactionID DESC");
		try {
			if (rs.next()) {
				hasil = (int)rs.getInt("TransactionID");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
		return hasil+1;
	}
	
	private void addCartToDatabase() {
		if (saveTempCartData.size() == 0) {
			JOptionPane.showMessageDialog(this, "Please order at least 1 product", "Oops !", JOptionPane.WARNING_MESSAGE);
		} else {
			
			int TransactionID = generateTransactionID();
			String LoginSessionID = null;
			String UserID = LoginSessionID;
			
			
			
			con.insertHeaderTransaction(TransactionID, UserID);
			for (int i = 0; i < saveTempCartData.size(); i++) {
				String ProductID = ((Vector)saveTempCartData.get(i)).get(0).toString();
				int Qty = Integer.parseInt(((Vector)saveTempCartData.get(i)).get(3).toString());
				con.insertDetailTransaction(TransactionID, ProductID, Qty);
			}
			
			for (int i = 0; i < saveTempCartData.size(); i++) {
				saveTempCartData.remove(i);
			}
			DefaultTableModel dtm0 = new DefaultTableModel(col2, 10);
			tableCart.setModel(dtm0);
			JOptionPane.showMessageDialog(this, "Success Add To Chart !", "Success", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	
	

	public static void main(String[] args) {
		
		new ProductForm();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btn2)){
			Cart cart = new Cart();
		}
		
	}

}
