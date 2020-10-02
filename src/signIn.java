import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class signIn extends JFrame implements ActionListener{
	
	JPanel p1,p2,p3,p4;
	
	JTextField txtEmail; 
	JLabel lblTitle, lblEmail, lblPass, lbl1;
	JPasswordField pass; 
	JButton btnRegis, btnLogin;
	
	Connect con = new Connect();
	ResultSet rs; 
	private boolean String; 
	Vector<Object> role;
	
	
public signIn() {
	
		p1 = new JPanel();
		p2 = new JPanel(new GridLayout(2,2,3,10));
		p3 = new JPanel(new GridLayout(1,2,10,4));
		p4 = new JPanel();
		
	
		txtEmail = new JTextField();
		lblTitle = new JLabel("SIGN IN"); 
		lblEmail = new JLabel("Email");
		lblPass = new JLabel("Password");
		lbl1 = new JLabel();
		
		pass = new JPasswordField();
		
		btnRegis = new JButton("Register");
		btnRegis.addActionListener(this);
		btnLogin = new JButton ("Login");
		btnLogin.addActionListener(this);
		
		p1.add(lblTitle);
		p2.add(lblEmail);
		p2.add(txtEmail);
		p2.add(lblPass);
		p2.add(pass);
		p3.add(btnRegis);
		p3.add(btnLogin);
		p4.add(p3);
		
		
		add(p1, BorderLayout.NORTH);
		add(p2, BorderLayout.CENTER);
		add(p4, BorderLayout.SOUTH);
		
		
		
		setTitle("SIGN IN");
		setSize(400, 170);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setBackground(getBackground());
		
		
		
}

public static void main (String[]args){
	
	new signIn();
	
}
private void databaseCheckLogin() {
	
	String tempDB_Email = "";
	String tempDB_Password = "";
	String tempDB_role = "";
	
	
	boolean valid = false;
	
	
	ResultSet rs = con.executeQuery("SELECT UserID,Email,Password,Role FROM user");
	java.lang.String LoginSessionID = null;
	try {
		
		while (rs.next()) {
			tempDB_Email =  rs.getString("Email");
			tempDB_Password = rs.getString("Password");

			
			System.out.println(rs.getString("ID") + " " + rs.getString("Name")+" "+rs.getString("Password")+" "+rs.getString("Role"));
			
			
			if (txtEmail.getText().trim().equals(tempDB_Email) && pass.getText().equals(tempDB_Password)) {
				valid = true;
				tempDB_role = rs.getString("Role");
				LoginSessionID = rs.getString("ID");
				break;
			}

		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	if (tempDB_role == null) {
		tempDB_role = "null";
	}
	
	if (valid == false) {
		JOptionPane.showMessageDialog(this, "Username or Password Wrong !", "Auth Failed", JOptionPane.ERROR_MESSAGE);
	} else if (valid == true && tempDB_role.equals("user")) {
		new MemberMainForm(LoginSessionID);
		dispose();
	} else if (valid == true && tempDB_role.equals("admin")) {
		new AdminMainForm(LoginSessionID);
		dispose();
	} else if (valid == true && tempDB_role.equals("null")) {
		JOptionPane.showMessageDialog(this, "Error: Role not Found \n\nPlease contact kel4-lf11-BAD@gmail.com","Whoops, Something Wrong !", JOptionPane.ERROR_MESSAGE);
	}
}

private void loginValidation() {
	if (txtEmail.getText().trim().equals("") || pass.getText().toString().equals("")) {
		JOptionPane.showMessageDialog(this, "Please input Username / Password","Opps !",JOptionPane.WARNING_MESSAGE);
	} else {
		databaseCheckLogin();
	}
}



@Override
public void actionPerformed(ActionEvent e) {
	
	if(e.getSource() == btnRegis){
		
		new Register().setVisible(true);
		this.dispose();
		
	}else if(e.getSource() == btnLogin){
		
		loginValidation();
		
		
	}
	
	
}
	
	
}
