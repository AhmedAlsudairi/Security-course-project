

//import java.sql.*;
import java.util.Scanner;

public class Banking {
	
	private static String customerId;
	private static String customerPwd;

	public static String getCustomerId() {
		return customerId;
	}
	public static void setCustomerId(String customerId) {		// enforce least privilege (hide id,pwd from user)
		Banking.customerId = customerId;
	}
	public static String getCustomerPwd() {
		return customerPwd;
	}
	public static void setCustomerPwd(String customerPwd) {
		Banking.customerPwd = customerPwd;
	}
	
	
	public static void success(){
		System.out.println("Success!");
	}
	
	public static void failure(){
		System.out.println("Invalid Input");
		
	}
	
	
	
	public static void main(String[] args) {
		try{
		Scanner scanner = new Scanner(System.in);
		
		int tries =0;
		boolean flag = true;
		
		while(flag && tries < 3){				// prevent from broken authentication
		System.out.println("Enter your customer ID");
		String temp =scanner.nextLine();
		char t[] = temp.toCharArray();
		
		System.out.println("Enter your password");
		 String temp2 = scanner.nextLine();
		 char t2[] = temp2.toCharArray();	// input should be decoded before trying to sanitize it
		 
		 
		 boolean flag2 = true;
			for (int i=0; i<t.length; i++){	 
				char c = t[i];
				if(!Character.isDigit(c)){	
					flag2 =false;

					break;
				}
				
			
			}
			
			boolean flag3 = true;
			for (int i=0; i < temp2.length(); i++){
				String c = temp2.charAt(i)+"";
				if(c.equals("'") || c.equals(" ") || c.equals("@") || c.equals("!") || c.equals("#") || 
						c.equals("$") || c.equals("%") || c.equals("^") || c.equals("&") || c.equals("*") ||
						c.equals("(") || c.equals(")") || c.equals("-") || c.equals("+") || c.equals("=") 
						|| c.equals("/") || c.equals("`") || c.equals("") || 			// // input validation
						c.equals("\"") || c.equals("\'") || c.equals("\b") || c.equals("\r") || 
						c.equals("\t") ||c.equals("\n") || c.equals("\f") || c.equals("\\") ){ 	// avoid simple escaping
					
					flag3 =false;

					break;
				}
			}
				if(flag && flag2 && flag3&&t.length!=0&&t2.length!=0){
				setCustomerId(temp);
				setCustomerPwd(temp2);
				flag = false;
				success();
				}
				else {
					failure();
					tries++;
				}
			}
		 
		
		String sql = "select"
				+ "customer_id, acc_number,branch_id,balance "
				+ "from Accounts where customer_id = '"
				+ customerId
				+ "and customer_pwd = '"
				+ customerPwd
				+ "'";
		 Statement dataSource;
		Connection c = dataSource.getConnection();
		 ResultSet rs = c.createStatement().executeQuery(sql);
		
		
		success();
		
		} catch (Exception e){
			System.out.println("Error invalid input ");	// avoid detailed error messages
			failure();
		}
	}

}
