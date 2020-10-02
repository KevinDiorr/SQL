import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class manageUser extends JFrame implements ActionListener {
	
	JPanel p1,p2,p3,p4,p5,p6,p7;
	JLabel lbl1,lbl2,lbl3,lbl4,lbl5, lbl6, lbl7,lbl8,lbl9,lbl10,lbl11,lbl12,lbl13,lbl14, lbl15, lbl16;
	JButton btn1, btn2;
	JTable table;
	JScrollPane scroll;
	DefaultTableModel tableModel;
	JTextField txt1, txt2, txt3,txt4,txt5,txt6;
Vector<Object> Row,col;

Connect con = new Connect();
ResultSet rs; 

public void fillTable(){
	tableModel = new DefaultTableModel(col, 0);
	con.rs = con.executeQuery("SELECT UserID,Name,Email,Password,Birthday,Gender, Status FROM user");
	try {
		while (con.rs.next()) {
			Row = new Vector<Object>();
			for (int i = 1; i <= ((DefaultTableModel) con.rs).getColumnCount(); i++)
				Row.add(con.rs.getObject(i) + "");
			tableModel.addRow(Row);
		}
		table.setModel(tableModel);
	}catch(SQLException e) {
		
		e.printStackTrace();
	}
	System.out.println("Updating table view...");
	RowSorter sort = new TableRowSorter(tableModel);
	table.setRowSorter(sort);
	
	
	
}

public void modifState(boolean modif){
	
	txt1.setEnabled(modif);
	txt2.setEnabled(modif);
	txt3.setEnabled(modif);
	txt4.setEnabled(modif);
	txt5.setEnabled(!modif);
	txt6.setEnabled(!modif);
}


	
	public manageUser(){
		
		col = new Vector<Object>();
		col.add("ID");
		col.add("Name");
		col.add("Email");
		col.add("Bithday");
		col.add("Gender");
		col.add("Status");
		
		p1 = new JPanel();
		p2 = new JPanel();
		p3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		p4 = new JPanel(new GridLayout(4,2));
		p5 = new JPanel(new GridLayout(4,2));
		p6 = new JPanel(new GridLayout(1,2));
		p7 = new JPanel(new GridLayout(2,1,4,5));
		
		lbl1 = new JLabel("ID");
		lbl2 = new JLabel("Name");
		lbl3 = new JLabel("Email");
		lbl4 = new JLabel("Status");
		lbl5 = new JLabel("Transaction");
		lbl6 = new JLabel("Cart");
		lbl7 = new JLabel("Manage User");
		
		lbl8 = new JLabel();
		lbl9 = new JLabel();
		lbl10 = new JLabel();
		lbl11 = new JLabel();
		lbl12 = new JLabel();
		lbl13 = new JLabel();
		
		lbl14 = new JLabel();
		
		lbl15 = new JLabel();
		lbl16 = new JLabel();
		
		txt1 = new JTextField();
		txt2 = new JTextField();
		txt3 = new JTextField();
		txt4 = new JTextField();
		txt5 = new JTextField();
		txt6 = new JTextField();
		
		
		btn1 = new JButton("Ban User");
		btn2 = new JButton("Un-Ban User");
		
		
		scroll = new JScrollPane();
		
		tableModel = new DefaultTableModel(Row,col);
		table = new JTable(tableModel);
		scroll = new JScrollPane(table);
		
		p1.add(lbl7);
		
		p2.add(scroll);
		
		p3.add(lbl14);
		
		p4.add(lbl1);
		p4.add(txt1);
		p4.add(lbl2);
		p4.add(txt2);
		p4.add(lbl3);
		p4.add(txt3);
		
		p4.add(lbl15);
		p4.add(btn1);
		
		p5.add(lbl4);
		p5.add(txt4);
		p5.add(lbl5);
		p5.add(txt5);
		p5.add(lbl6);
		p5.add(txt6);
		
		p5.add(btn2);
		p5.add(lbl16);
		
		p6.add(p4);
		p6.add(p5);
		
		p7.add(p3);
		p7.add(p6);
		
		
		add(p1, BorderLayout.NORTH);
		add(p2, BorderLayout.CENTER);
		add(p7, BorderLayout.SOUTH);
		
		setTitle("Products");
		setSize(900,500);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		
				
		
	}
	

	public static void main(String[] args) {
		
		new manageUser();

	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
