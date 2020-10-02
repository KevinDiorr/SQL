import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class MemberMainForm extends JFrame implements ActionListener {

	JPanel p1;
	JMenuBar menubar;
	JMenu store, profil; 
	JMenuItem product, cart, hampers, about, pass,logout;
	JLabel lbl1; 
	ImageIcon gambar;
	
	Connect con = new Connect();
	ResultSet rs;
	
	
	protected String LoginSessionID = "";
	
public MemberMainForm(){
	
	

	menubar = new JMenuBar();
	store = new JMenu("Store");
	profil = new JMenu("Profile");
	product = new JMenuItem("Product");
	cart = new JMenuItem("Cart");
	hampers = new JMenuItem("Hampers");
	about = new JMenuItem("About");
	pass = new JMenuItem("Change Password");
	logout = new JMenuItem("Logout");
	
	gambar = new ImageIcon("cadbuly.PNG");
	lbl1 = new JLabel(gambar);
	
	p1 = new JPanel();
	
	
	p1.add(lbl1);
	
	add(menubar);
	this.setJMenuBar(menubar);
			
	menubar.add(store);
	menubar.add(profil);
	
	store.add(product);
	product.addActionListener(this);
	store.add(cart);
	cart.addActionListener(this);
	store.add(hampers);
	hampers.addActionListener(this);
	store.add(about);
	about.addActionListener(this);
	
	profil.add(pass);
	pass.addActionListener(this);
	profil.add(logout);
	logout.addActionListener(this);
	
	add(p1, BorderLayout.CENTER);
	
	setTitle("MAIN FORM");
	setSize(1000,700);
	setLocationRelativeTo(null);
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	setResizable(false);
	setVisible(true);
	
	
}
		

	

 protected MemberMainForm(java.lang.String loginSessionID2) {
	// TODO Auto-generated constructor stub
}




	public static void main(String[] args) {
		
		new MemberMainForm();

	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(product)){
			ProductForm product = new ProductForm();
		}else if(e.getSource().equals(cart)){
			Cart cart = new Cart();
			
		}else if(e.getSource().equals(pass) ){
			changePassword password = new changePassword();
		}else if(e.getSource().equals(logout)){
			this.dispose();
		}
		
	}

}
