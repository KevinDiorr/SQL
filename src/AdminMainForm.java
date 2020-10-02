import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class AdminMainForm extends JFrame implements ActionListener{

	JPanel p1;
	JMenuBar menubar;
	JMenu manage; 
	JMenuItem user,item,logout;
	JLabel lbl1; 
	ImageIcon gambar;
	
public AdminMainForm(){

	menubar = new JMenuBar();
	manage = new JMenu("Manage");
	user = new JMenuItem("User");
	item = new JMenuItem("Item");
	logout = new JMenuItem("Logout");
	
	gambar = new ImageIcon("cadbuly.PNG");
	lbl1 = new JLabel(gambar);
	
	p1 = new JPanel();
	
	
	p1.add(lbl1);
	
	add(menubar);
	this.setJMenuBar(menubar);
			
	menubar.add(manage);

	
	manage.add(user);
	user.addActionListener(this);
	manage.add(item);
	item.addActionListener(this);
	manage.add(logout);
	logout.addActionListener(this);
	
	
	add(p1, BorderLayout.CENTER);
	
	setTitle("MAIN FORM");
	setSize(1000,700);
	setLocationRelativeTo(null);
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	setResizable(false);
	setVisible(true);
	
	
}
		
	

	protected AdminMainForm(java.lang.String loginSessionID) {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		
		new AdminMainForm();

	}



	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(user)){
			manageUser user = new manageUser();
		}else if(e.getSource().equals(item)){
			manage_Item item = new manage_Item();
		}else if(e.getSource().equals(logout)){
			this.dispose();
		}
		
	}

}
