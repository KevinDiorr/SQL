import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;


public class changePassword extends JFrame implements ActionListener{
	
	JPanel pnl1,pnl2,pnl3;
	JLabel lbl1,lbl2,lbl3,lbl4;
	
	JPasswordField pass1, pass2, pass3;
	JButton btn1;

	Connect con = new Connect();
	ResultSet rs; 
	protected String LoginSessionID = "";
public changePassword(){
	
	pnl1 = new JPanel();
	pnl2 = new JPanel(new GridLayout(3,2,7,8));
	pnl3 = new JPanel();
	
	lbl1 = new JLabel("Enter old password");
	lbl2 = new JLabel("Enter new password");
	lbl3 = new JLabel("Confirm Password");
	lbl4 = new JLabel("Change Password");
	
	pass1 = new JPasswordField();
	pass2 = new JPasswordField();
	pass3 = new JPasswordField();
	
	btn1 = new JButton("Update");
	btn1.addActionListener(this);
	
	pnl1.add(lbl4);
	
	pnl2.add(lbl1);
	pnl2.add(pass1);
	pnl2.add(lbl2);
	pnl2.add(pass2);
	pnl2.add(lbl3);
	pnl2.add(pass3);
	
	pnl3.add(btn1);
	
	add(pnl1, BorderLayout.NORTH);
	add(pnl2, BorderLayout.CENTER);
	add(pnl3, BorderLayout.SOUTH);
	
	setTitle("Setting");
	setSize(500,200);
	setLocationRelativeTo(null);
	setVisible(true);
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	
	
}
	public static void main(String[] args) {
		
		new changePassword();

	}
	
	
private void updateValidation() {
		
		 if (pass1.getText().toString().equals("") || pass2.getText().toString().equals("") || pass3.getText().toString().equals("")) {
			JOptionPane.showMessageDialog(this, "Please fill the \nPassword and Password Confirmation","Opps !",JOptionPane.WARNING_MESSAGE);
		}else if (!pass2.getText().toString().equals(pass3.getText().toString())) {
			JOptionPane.showMessageDialog(this, "Password Confirmation not match","Opps !",JOptionPane.WARNING_MESSAGE);
		}else {
			
			String password = null;
			updateDatabase(password);
			JOptionPane.showMessageDialog(this, "Success !", "Change Password", JOptionPane.INFORMATION_MESSAGE);
			dispose();
		}
	}
	
	
	private void getPreviousPassword() {
		ResultSet rs = con.executeQuery("SELECT Password FROM user WHERE ID='"+ LoginSessionID + "'");
		try {
			while (rs.next()) {
				pass1.setText(rs.getString("Username"));
				pass1.setEnabled(false);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void updateDatabase(String password) {
		String query = "UPDATE user SET Password ='"+ password +LoginSessionID +"'";
		System.out.println(query);
		con.executeUpdate(query);
	}
	

	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if (e.getSource() == btn1) {
			updateValidation();
		
	}

}

}