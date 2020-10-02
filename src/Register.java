import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;

public class Register extends JFrame implements ActionListener{
	
	JPanel p1,p2,p3,p4,p5,p6,p7;
	JLabel lbl1,lbl2,lbl3,lbl4,lbl5,lbl6,lbl7,lbl8;
	JTextField txt1,txt2;
	JPasswordField pass1,pass2;
	JRadioButton male,female;
	JSpinner spn1,spn2,spn3;
	JButton btn1,btn2;
	ButtonGroup btnGender;
	JCheckBox check;
	
	Connect con = new Connect();
	ResultSet rs;
	private boolean String; 
	Vector<Object> role;
	
	
	public Register(){
		
		p1 = new JPanel();
		p2 = new JPanel(new GridLayout(8,1,4,5));
		p3 = new JPanel(new GridLayout(8,1,4,5));
		p4 = new JPanel(new GridLayout(1,2,4,5));
		p5 = new JPanel(new GridLayout(1,3));
		p6 = new JPanel(new GridLayout(1,2,4,5));
		p7 = new JPanel();
		
		lbl1 = new JLabel("CREATE ACCOUNT");
		lbl2 = new JLabel("Name");
		lbl3 = new JLabel("Email");
		lbl4 = new JLabel("Password");
		lbl5 = new JLabel("Confirm Password");
		lbl6 = new JLabel("Gender");
		lbl7 = new JLabel("Birthady");
		
		
		txt1 = new JTextField();
		txt2 = new JTextField();
		
		pass1 = new JPasswordField();
		pass2 = new JPasswordField();
		
		spn1 = new JSpinner();
		spn2 = new JSpinner();
		spn3 = new JSpinner();
		
		male = new JRadioButton("Male");
		female = new JRadioButton("Female");
		
		btnGender = new ButtonGroup();
		
		btnGender.add(male);
		btnGender.add(female);
		
		btn1 = new JButton("Back");
		btn1.addActionListener(this);
		btn2 = new JButton("Submit");
		btn2.addActionListener(this);
		
		check = new JCheckBox("Terms and Condition");
		
		
		
		
		
		p1.add(lbl1);
		
		p2.add(lbl2);
		p2.add(lbl3);
		p2.add(lbl4);
		p2.add(lbl5);
		p2.add(lbl6);
		p2.add(lbl7);
		p2.add(check);
		
		p3.add(txt1);
		p3.add(txt2);
		p3.add(pass1);
		p3.add(pass2);
		p3.add(p4);
		p4.add(male);
		p4.add(female);
		p3.add(p5);
		p5.add(spn1);
		p5.add(spn2);
		p5.add(spn3);
		
		p6.add(btn1);
		p6.add(btn2);
		p7.add(p6);
		
		add(p1, BorderLayout.NORTH );
		add(p2, BorderLayout.WEST);
		add(p3, BorderLayout.CENTER);
		add(p7, BorderLayout.SOUTH);
		
		setTitle("Register");
		setSize(300,400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setLayout(new BorderLayout());
		
		
		
	}
	

	public static void main(String[] args) {
		new Register();
		
	
	}

	private int generateIncrement() {
		int hasil = 0;
		ResultSet rs = con.executeQuery("SELECT * FROM user ORDER BY UserID DESC");
		try {
			rs.next();
			hasil = (int)rs.getInt("ID");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
		return hasil+1;
	}
	
	private boolean usernamePernahDigunakan(String username) {
		boolean hasil = false;
			ResultSet rs = con.executeQuery("SELECT * FROM user WHERE Name = '"+ username +"'");
			try {
				if (rs.next()) {
					hasil = true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		return hasil;
	}
	
	private void insertToDatabase() {

		String name = txt1.getText().trim();
		String password = pass1.getText().toString();
		
		String gender = "";
			if (male.isSelected() == true) {
				gender = "male";
			} else if (female.isSelected() == true) {
				gender = "female";
			}
			
		java.lang.String email = null;
		java.lang.String birthday = null;
		java.lang.String id = null;
		String query = "INSERT INTO user(UserID, Name,Email, Password, Birthday, Gender, Status) VALUES('"+ id +"','"+ getName() +"','"+ email +"','"+ password +"', '"+ birthday +"','user')";
		System.out.println(query);
		con.executeUpdate(query);
	}
	
	private boolean checkNumeric(String input) {
		boolean ketemuAngka = false;
		for (int i=0; i<input.length(); i++) {
	        char ceking = input.charAt(i);
	        
	        ketemuAngka = Character.isDigit(ceking);
	    }
		
			return ketemuAngka;
	}
	
	private void registrationValidation() {

		if (txt1.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Please fill the Username","Opps !",JOptionPane.WARNING_MESSAGE);
		} else if (txt1.getText().trim().toString().length() < 5 || txt1.getText().trim().toString().length() > 25) {
			JOptionPane.showMessageDialog(this, "Username must between 5 and 25 characters","Opps !",JOptionPane.WARNING_MESSAGE);
		} else if (usernamePernahDigunakan(txt1.getText().trim().toString()) == true) {
			JOptionPane.showMessageDialog(this, "Username already used","Opps !",JOptionPane.WARNING_MESSAGE);
		}else if(txt2.getText().startsWith("@") && !txt2.getText().endsWith(".com") ){
			JOptionPane.showMessageDialog(this, "Email can't starts with @ and must end with .com ");
		} else if (pass1.getText().equals("") || pass2.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Please fill the \nPassword and Password Confirmation","Opps !",JOptionPane.WARNING_MESSAGE);
		} else if (!pass1.getText().equals(pass2.getText())) {
			JOptionPane.showMessageDialog(this, "Password Confirmation not match","Opps !",JOptionPane.WARNING_MESSAGE);
		} else if (!male.isSelected() && !female.isSelected()) {
			JOptionPane.showMessageDialog(this, "Please select Gender","Opps !",JOptionPane.WARNING_MESSAGE);
		} else if (!check.isSelected()) {
			JOptionPane.showMessageDialog(this, "You must agree with the agreement","Opps !",JOptionPane.WARNING_MESSAGE);
		} else {
			insertToDatabase();
			JOptionPane.showMessageDialog(this, "Registration Success !", "Registration Status", JOptionPane.INFORMATION_MESSAGE);
			new signIn();
			dispose();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == btn1){
			new signIn().setVisible(true);
			this.dispose();
		
		}else if(e.getSource() == btn2){
			
			registrationValidation();
		
		
	}

}
}

