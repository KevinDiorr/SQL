import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.NoRouteToHostException;
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

public class manage_Item extends JFrame implements ActionListener {
	
	
	JPanel p1,p2,p3,p4,p5,p6,p7;
	JLabel lbl1,lbl2,lbl3,lbl4,lbl5,lbl6;
	JTextField txt1,txt2,txt3,txt4;
	JButton btn1,btn2,btn3,btn4;
	JTable table;
	JScrollPane scroll;
	DefaultTableModel tableModel;
	Vector<Object>Row,col;
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
	}

	public manage_Item(){
		
		col = new Vector<Object>();
		col.add("ID");
		col.add("Name");
		col.add("Price");
		col.add("Category");
		
		
		p1 = new JPanel();
		p2 = new JPanel();
		p3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		p4 = new JPanel(new GridLayout(5,2));
		p5 = new JPanel(new GridLayout(1,2));
		p6 = new JPanel(new GridLayout(1,2));
		p7 = new JPanel(new GridLayout(2,1,4,5));
		
		lbl1 = new JLabel("ID");
		lbl2 = new JLabel("Name");
		lbl3 = new JLabel("Price");
		lbl4 = new JLabel("Category");
		lbl5 = new JLabel("Manage Item");
		lbl6 = new JLabel("Detail");
		
		txt1 = new JTextField();
		txt2 = new JTextField();
		txt3 = new JTextField();
		txt4 = new JTextField();
		
		btn1 = new JButton("Reset");
		btn2 = new JButton("Insert");
		btn3 = new JButton("Update");
		btn4 = new JButton("Delete");
		
		tableModel = new DefaultTableModel(Row, col);
		table = new JTable(tableModel);
		scroll = new JScrollPane(table);
		
		p1.add(lbl5);
		
		p2.add(scroll);
		
		p3.add(lbl6);
		
		p4.add(lbl1);
		p4.add(txt1);
		p4.add(lbl2);
		p4.add(txt2);
		p4.add(lbl3);
		p4.add(txt3);
		p4.add(lbl4);
		p4.add(txt4);
		
		p5.add(btn1);
		p5.add(btn2);
		p6.add(btn3);
		p6.add(btn4);
		
		p4.add(p5);
		p4.add(p6);
		
		p7.add(p3);
		p7.add(p4);
		
		add(p1, BorderLayout.NORTH);
		add(p2, BorderLayout.CENTER);
		add(p7, BorderLayout.SOUTH);
		
		
		setTitle("Manage Item");
		setSize(900,500);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
	}

	public static void main(String[] args) {
		new manage_Item();

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
