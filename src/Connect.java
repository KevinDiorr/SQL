
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Connect {

	Connection con; 
	Statement st;
	ResultSet rs;
	
	public Connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cadbuly", "root", "");
			System.out.println("Connected to BlueJack Database");
			st = con.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void executeUpdate(String query) {
		try {
			st.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public ResultSet executeQuery(String query) {
		ResultSet rs = null;
		
		try {
			rs = st.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rs;
	}
	
	
	
	public void insertToMember(String id, String name, String email, String password, String birthday, String gender, String status){
		try {
			PreparedStatement pstat = con.prepareStatement("INSERT INTO user (UserID, Name, Email, Password, Birthday,Gender,Status,) ?,?,?,?,?,?,?");
			pstat.setString(1, id);
			pstat.setString(2, name);
			pstat.setString(3, email);
			pstat.setString(4, password);
			pstat.setString(5, birthday);
			pstat.setString(6, gender);
			pstat.setString(7, status);
			pstat.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public void insertToProduct(String prodid, String prodname, int prodprice, String prodcategory){
		try {
			PreparedStatement pstat = con.prepareStatement("INSERT INTO product (ProductID, ProductName, ProductPrice, ProductCategory) ?,?,?,?");
			pstat.setString(1, prodid);
			pstat.setString(2, prodname);
			pstat.setInt(3, prodprice);
			pstat.setString(4, prodcategory);
	
			pstat.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public void insertToCart(String userid, String proid, int qty){
		try {
			PreparedStatement pstat = con.prepareStatement("INSERT INTO mycart (UserID, ProductID, ProductQuantity) ?,?,?");
			pstat.setString(1, userid);
			pstat.setString(2, proid);
			pstat.setInt(3, qty);
			pstat.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	public void insertHeaderTransaction(int TransactionID, String UserID) {
		try {
			PreparedStatement pstat = con.prepareStatement("INSERT INTO headertransaction (TransactionID, UserID) VALUES(?,?)");
			pstat.setInt(1, TransactionID);
			pstat.setString(2, UserID);
			pstat.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	public void insertDetailTransaction(int TransactionID, String productID, int Qty) {
		try {
			PreparedStatement pstat = con.prepareStatement("INSERT INTO detailtransaction (TransactionID, ProductID, Qty) VALUES(?,?,?)");
			pstat.setInt(1, TransactionID);
			pstat.setString(2, productID);
			pstat.setInt(3, Qty);
			pstat.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public void updateProduct(String prodid, String prodname, int prodprice, String prodcategory) {
		try {
			PreparedStatement pstat = con.prepareStatement("UPDATE product SET `ProductName` = ?,  `ProductPrice` = ?, `Product Category` =? WHERE ProductID = ?");
			pstat.setString(1, prodid);
			pstat.setString(2, prodname);
			pstat.setInt(3, prodprice);
			pstat.setString(4, prodcategory);
			
			pstat.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updatePassword(String password) {
		try {
			PreparedStatement pstat = con.prepareStatement("UPDATE user SET `Password` = ? WHERE ProductID = ?");
			pstat.setString(1, password);
			
			
			pstat.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteProduct(String id) {
		try {
			PreparedStatement pstat = con.prepareStatement("DELETE FROM product WHERE Product = ?");
			pstat.setString(1, id);
			pstat.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

