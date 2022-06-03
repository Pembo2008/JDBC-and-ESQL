import java.sql.*;
import java.util.Scanner;

public class BD_class{
	
static void createDatabase_1(Connection conn1, String db_name,String tab_name) throws Exception {
		
		Statement stat1 = conn1.createStatement();
		
	    stat1.executeUpdate("create database " + db_name +" owner postgres");
		Connection conn2 = null;
		//ÔÓÚÓÍÓÎ:ÔÓ‰ÔÓÚÓÍÓÎ(—”¡ƒ)://ıÓÒÚ:ÔÓÚ/·‰
		String url = "jdbc:postgresql://localhost:5432/"+db_name,
				userName = "postgres", userPassd = "ABCabc123";
		Class.forName("org.postgresql.Driver");
		
		conn2 = DriverManager.getConnection(url, userName, userPassd);
		Statement stat2 = conn2.createStatement();
		stat2.executeUpdate("CREATE TABLE IF NOT EXISTS "+ tab_name +
				" (»ƒ≈Õ“»‘» ¿“Œ– INT PRIMARY KEY check(»ƒ≈Õ“»‘» ¿“Œ–>0),"
				+ "Ã≈ƒ_œ≈–—ŒÕ¿À_1 INT NOT NULL,"
				+ " ¬Œ«–¿—“ INT NOT NULL);");
		System.out.println("Tab "+tab_name+" created");
		
		stat2.close();
		System.out.println("DB "+db_name+" created");
		
		stat1.close();
		conn2.close();
	  }


static void dropDatabase_2(Connection conn1, String db_name) throws Exception {
	
	Statement stat1 = conn1.createStatement();
	
	stat1.executeUpdate("drop database IF EXISTS " + db_name);
	System.out.println("DB "+db_name+" dropped");
	
	stat1.close();
  }


static void clearTab_3(Connection conn1, String tab_name) throws Exception {
	
	PreparedStatement prep1 = conn1.prepareStatement("select clearTab(?)");
	prep1.setString(1, tab_name);
	ResultSet rs1 = prep1.executeQuery();
	System.out.println("Tab "+tab_name+" cleared");

	rs1.close();
	
	prep1.close();
  }

static void addToTab_4(Connection conn1, String tab_name) throws Exception {
	
	
	Scanner in = new Scanner(System.in);
	switch(tab_name) {
	case "Ã≈ƒœ≈–—ŒÕ¿À":
		System.out.print("Input id: ");
	    int id = Integer.parseInt(in.nextLine());
	    System.out.print("Input surname: ");
	    String surn = in.nextLine();
	    System.out.print("Input adress: ");
	    String adr = in.nextLine();
	    System.out.print("Input tax amount: ");
	    int tax = Integer.parseInt(in.nextLine());
	    in.close();
	    PreparedStatement prep1 = conn1.prepareStatement("select addToTableStaff(?,?,?,?)");
		prep1.setInt(1, id);
		prep1.setString(2, surn);
		prep1.setString(3, adr);
		prep1.setInt(4, tax);
		ResultSet rs1 = prep1.executeQuery();
		System.out.println("Tab "+tab_name+" updated");
		rs1.close();
		prep1.close();
	    break;
	case "new_tb":
		System.out.print("Input id: ");
	    int id_n = Integer.parseInt(in.nextLine());
	    System.out.print("Input med_pers_id: ");
	    int med_id = Integer.parseInt(in.nextLine());
	    System.out.print("Input age: ");
	    int age = Integer.parseInt(in.nextLine());
	    in.close();
	    PreparedStatement prep2 = conn1.prepareStatement("select addToTableNew(?,?,?)");
		prep2.setInt(1, id_n);
		prep2.setInt(2, med_id);
		prep2.setInt(3, age);
		ResultSet rs2 = prep2.executeQuery();
		System.out.println("Tab "+tab_name+" updated");
		rs2.close();
		prep2.close();
	    break;
	case "Ã≈—“Œ_–¿¡Œ“€":
		System.out.print("Input id: ");
	    int p_id = Integer.parseInt(in.nextLine());
	    System.out.print("Input hospital name: ");
	    String place_name = in.nextLine();
	    System.out.print("Input adress: ");
	    String place_adr = in.nextLine();
	    System.out.print("Input tax percent: ");
	    int tax_percent = Integer.parseInt(in.nextLine());
	    in.close();
	    PreparedStatement prep3 = conn1.prepareStatement("select addToTablePlace(?,?,?,?)");
		prep3.setInt(1, p_id);
		prep3.setString(2, place_name);
		prep3.setString(3, place_adr);
		prep3.setInt(4, tax_percent);
		ResultSet rs3 = prep3.executeQuery();
		System.out.println("Tab "+tab_name+" updated");
		rs3.close();
		prep3.close();
	    break;
	case "“»œ€_Œœ≈–¿÷»…":
		System.out.print("Input id: ");
	    int op_id = in.nextInt();
	    System.out.print("Input name of operation: ");
	    String op_name = in.nextLine();
	    System.out.print("Input place of operation: ");
	    String op_place = in.nextLine();
	    System.out.print("Input savings: ");
	    int savings = Integer.parseInt(in.nextLine());
	    System.out.print("Input price: ");
	    int price = Integer.parseInt(in.nextLine());
	    System.out.print("Input sum of tax: ");
	    int tax_sum = Integer.parseInt(in.nextLine());
	    in.close();
	    PreparedStatement prep4 = conn1.prepareStatement("select addToTableOper(?,?,?,?,?,?)");
		prep4.setInt(1, op_id);
		prep4.setString(2, op_name);
		prep4.setString(3, op_place);
		prep4.setInt(4, savings);
		prep4.setInt(5, price);
		prep4.setInt(6, tax_sum);
		ResultSet rs4 = prep4.executeQuery();
		System.out.println("Tab "+tab_name+" updated");
		rs4.close();
		prep4.close();
	    break;
	case "“–”ƒŒ¬¿ﬂ_ƒ≈ﬂ“≈À‹ÕŒ—“‹":
		System.out.print("Input contract id: ");
	    int contract_id = Integer.parseInt(in.nextLine());
	    System.out.print("Input date: ");
	    String date = in.nextLine();
	    System.out.print("Input med id: ");
	    int med_id_td = Integer.parseInt(in.nextLine());
	    System.out.print("Input place_id: ");
	    int p_id_td = Integer.parseInt(in.nextLine());
	    System.out.print("Input operation id: ");
	    int op_id_td = Integer.parseInt(in.nextLine());
	    System.out.print("Input amount: ");
	    int amount = Integer.parseInt(in.nextLine());
	    System.out.print("Input payment: ");
	    int payment = Integer.parseInt(in.nextLine());
	    in.close();
	    PreparedStatement prep5 = conn1.prepareStatement("select addToTableAct(?,?,?,?,?,?,?)");
		prep5.setInt(1, contract_id);
		prep5.setString(2, date);
		prep5.setInt(3, med_id_td);
		prep5.setInt(4, p_id_td);
		prep5.setInt(5, op_id_td);
		prep5.setInt(6, amount);
		prep5.setInt(7, payment);
		ResultSet rs5 = prep5.executeQuery();
		System.out.println("Tab "+tab_name+" updated");
		rs5.close();
		prep5.close();
	    break;
	}

  }

static void searchTab_5(Connection conn1) throws Exception {
	
	Scanner in = new Scanner(System.in);
	System.out.print("Input table to search: ");
	String tab_name = in.nextLine();
	System.out.print("Input field to search: ");
	String field = in.nextLine();
	System.out.print("Input value: ");
	String value = in.nextLine();
	in.close();
	switch(tab_name) {
	case "Ã≈ƒœ≈–—ŒÕ¿À":
		PreparedStatement prep1 = conn1.prepareStatement("select searchMed(?,?)");
		prep1.setString(1, field);
		prep1.setString(2, value);
		ResultSet rs1 = prep1.executeQuery();
		while (rs1.next()) { 
			 System.out.println(rs1.getString(1));
			 }
		rs1.close();
		prep1.close();
		break;
	case "Ã≈—“Œ_–¿¡Œ“€": 
		PreparedStatement prep2 = conn1.prepareStatement("select searchPlace(?,?)");
		prep2.setString(1, field);
		prep2.setString(2, value);
		ResultSet rs2 = prep2.executeQuery();
		while (rs2.next()) { 
			 System.out.println(rs2.getString(1));
			 }
		rs2.close();
		prep2.close();
		break;
	case "“»œ€_Œœ≈–¿÷»…":
		PreparedStatement prep3 = conn1.prepareStatement("select searchOp(?,?)");
		prep3.setString(1, field);
		prep3.setString(2, value);
		ResultSet rs3 = prep3.executeQuery();
		while (rs3.next()) { 
			 System.out.println(rs3.getString(1));
			 }
		rs3.close();
		prep3.close();
		break;
	case "“–”ƒŒ¬¿ﬂ_ƒ≈ﬂ“≈À‹ÕŒ—“‹":
		PreparedStatement prep4 = conn1.prepareStatement("select searchTd(?,?)");
		prep4.setString(1, field);
		prep4.setString(2, value);
		ResultSet rs4 = prep4.executeQuery();
		while (rs4.next()) { 
			 System.out.println(rs4.getString(1));
			 }
		rs4.close();
		prep4.close();
		break;
	}
  }

static void updateTab_6(Connection conn1, String tab_name) throws Exception {
	Scanner in = new Scanner(System.in);
	switch(tab_name) {
	case "Ã≈ƒœ≈–—ŒÕ¿À":
		System.out.print("Input id: ");
	    int id = Integer.parseInt(in.nextLine());
	    System.out.print("Input surname: ");
	    String surn = in.nextLine();
	    System.out.print("Input adress: ");
	    String adr = in.nextLine();
	    System.out.print("Input tax amount: ");
	    int tax = in.nextInt();
	    in.close();
	    PreparedStatement prep1 = conn1.prepareStatement("select update_staff(?,?,?,?)");
		prep1.setInt(1, id);
		prep1.setString(2, surn);
		prep1.setString(3, adr);
		prep1.setInt(4, tax);
		ResultSet rs1 = prep1.executeQuery();
		System.out.println("Tab "+tab_name+" updated");
		rs1.close();
		prep1.close();
	    break;
	case "Ã≈—“Œ_–¿¡Œ“€":
		System.out.print("Input id: ");
	    int p_id = Integer.parseInt(in.nextLine());
	    System.out.print("Input hospital name: ");
	    String place_name = in.next();
	    System.out.print("Input adress: ");
	    String place_adr = in.next();
	    System.out.print("Input tax percent: ");
	    int tax_percent = in.nextInt();
	    in.close();
	    PreparedStatement prep3 = conn1.prepareStatement("select update_place(?,?,?,?)");
		prep3.setInt(1, p_id);
		prep3.setString(2, place_name);
		prep3.setString(3, place_adr);
		prep3.setInt(4, tax_percent);
		ResultSet rs3 = prep3.executeQuery();
		System.out.println("Tab "+tab_name+" updated");
		rs3.close();
		prep3.close();
	    break;
	case "“»œ€_Œœ≈–¿÷»…":
		System.out.print("Input id: ");
	    int op_id = Integer.parseInt(in.nextLine());
	    System.out.print("Input name of operation: ");
	    String op_name = in.nextLine();
	    System.out.print("Input place of operation: ");
	    String op_place = in.nextLine();
	    System.out.print("Input savings: ");
	    int savings = Integer.parseInt(in.nextLine());
	    System.out.print("Input price: ");
	    int price = Integer.parseInt(in.nextLine());
	    System.out.print("Input sum of tax: ");
	    int tax_sum = in.nextInt();
	    in.close();
	    PreparedStatement prep4 = conn1.prepareStatement("select update_op(?,?,?,?,?,?)");
		prep4.setInt(1, op_id);
		prep4.setString(2, op_name);
		prep4.setString(3, op_place);
		prep4.setInt(4, savings);
		prep4.setInt(5, price);
		prep4.setInt(6, tax_sum);
		ResultSet rs4 = prep4.executeQuery();
		System.out.println("Tab "+tab_name+" updated");
		rs4.close();
		prep4.close();
	    break;
	case "“–”ƒŒ¬¿ﬂ_ƒ≈ﬂ“≈À‹ÕŒ—“‹":
		System.out.print("Input contract id: ");
	    int contract_id = Integer.parseInt(in.nextLine());
	    System.out.print("Input date: ");
	    String date = in.nextLine();
	    System.out.print("Input med id: ");
	    int med_id_td = Integer.parseInt(in.nextLine());
	    System.out.print("Input place_id: ");
	    int p_id_td = Integer.parseInt(in.nextLine());
	    System.out.print("Input operation id: ");
	    int op_id_td = Integer.parseInt(in.nextLine());
	    System.out.print("Input amount: ");
	    int amount = Integer.parseInt(in.nextLine());
	    System.out.print("Input payment: ");
	    int payment = Integer.parseInt(in.nextLine());
	    in.close();
	    PreparedStatement prep5 = conn1.prepareStatement("select update_td(?,?,?,?,?,?,?)");
		prep5.setInt(1, contract_id);
		prep5.setString(2, date);
		prep5.setInt(3, med_id_td);
		prep5.setInt(4, p_id_td);
		prep5.setInt(5, op_id_td);
		prep5.setInt(6, amount);
		prep5.setInt(7, payment);
		ResultSet rs5 = prep5.executeQuery();
		System.out.println("Tab "+tab_name+" updated");
		rs5.close();
		prep5.close();
	    break;
	}

  }

static void dropTab_7(Connection conn1, String tab_name) throws Exception {
	
	Scanner in = new Scanner(System.in);
	System.out.print("Input field to drop: ");
	String field = in.nextLine();
    System.out.print("Input value: ");
    String value = in.nextLine();
    in.close();
	PreparedStatement prep1 = conn1.prepareStatement("select dropFunc(?,?,?)");
	prep1.setString(1, tab_name);
	prep1.setString(2, field);
	prep1.setString(3, value);
	ResultSet rs1 = prep1.executeQuery();
	System.out.println("Tab "+tab_name+" cleared");

	rs1.close();
	
	prep1.close();
  }

static void add_role(Connection conn1) throws Exception {
	
	int role = 2;
	Scanner input = new Scanner(System.in);
	while(true){
		System.out.println("Input int for the role to create: 1 - for admin, 0 - for the guest: ");
	        role = Integer.parseInt(input.nextLine());
	        if((role == 0)||(role==1)) {
	            break;
	            }
	        else {
	            System.out.println(role + " is not 0 or 1");
	        }
	    }
	input.close();
	if(role == 0) {
		Statement stat1 = conn1.createStatement();
		ResultSet rs1 = stat1.executeQuery("select createGuest();");
		System.out.println("Role guest created");
		rs1.close();
		stat1.close();
	}
	else {
		Statement stat1 = conn1.createStatement();
		ResultSet rs1 = stat1.executeQuery("select createAdmin();");
		System.out.println("Role admin created");
		rs1.close();
		stat1.close();
	}
  }



	public static void main(String[] args) throws Exception {
		System.out.print("Choose role - 1 for admin, 0 for guest:");
		Scanner sc = new Scanner(System.in);
		int rl = Integer.parseInt(sc.nextLine());
		sc.close();
		Connection conn = null;
		//ÔÓÚÓÍÓÎ:ÔÓ‰ÔÓÚÓÍÓÎ(—”¡ƒ)://ıÓÒÚ:ÔÓÚ/·‰
		String url,userName,userPassd;
		if (rl == 1) {
			url = "jdbc:postgresql://localhost:5432/postgres";
			userName = "postgres";
			userPassd = "ABCabc123";
		}
		else {
			url = "jdbc:postgresql://localhost:5432/postgres";
			userName = "guest";
			userPassd = "guestpwd";
		}
		Class.forName("org.postgresql.Driver");
		
		conn = DriverManager.getConnection(url, userName, userPassd);
		System.out.println("Connection ready");
		System.out.println("Choose 1 for creating database:");
		System.out.println("Choose 2 to drop database:");
		System.out.println("Choose 3 for clear table:");
		System.out.println("Choose 4 for adding new row to table:");
		System.out.println("Choose 5 for searching table by string:");
		System.out.println("Choose 6 to update table by string:");
		System.out.println("Choose 7 to delete table:");
		System.out.println("Choose 8 to add role:");
		System.out.println("Choose 0 to leave program:");
		String command = "9";
		while(command != "0") {
			Scanner in1 = new Scanner(System.in);
			command = in1.nextLine();
			switch(command) {
			case "1":
				try {
					String db,tab;
					System.out.println("Input database:");
					db = in1.nextLine();
					System.out.println("Input table:");
					tab = in1.nextLine();
					createDatabase_1(conn, db,tab);
				}
				catch (SQLException exception) {
					System.err.println("SQLException : " + exception.toString());
					}
				break;
			case "2":
				try {
					System.out.println("Input name of database:");
					String db_drop;
					db_drop = in1.nextLine();
					dropDatabase_2(conn,db_drop);
				}
				catch (SQLException exception) {
					System.err.println("SQLException : " + exception.toString());
					}
				break;
			case "3":
				try {
					System.out.println("Input name of table:");
					String tab_clear;
					tab_clear = in1.nextLine();
					clearTab_3(conn,tab_clear);
				}
				catch (SQLException exception) {
					System.err.println("SQLException : " + exception.toString());
					}
				break;
			case "4":
				try {
					String tab_add;
					System.out.println("Input table:");
					tab_add = in1.nextLine();
					addToTab_4(conn, tab_add);
				}
				catch (SQLException exception) {
					System.err.println("SQLException : " + exception.toString());
					}
				break;
			case "5":
				try {
					searchTab_5(conn);
				}
				catch (SQLException exception) {
					System.err.println("SQLException : " + exception.toString());
					}
				break;
			case "6":
				try {
					String tab_upd;
					System.out.println("Input table:");
					tab_upd = in1.nextLine();
					updateTab_6(conn, tab_upd);
				}
				catch (SQLException exception) {
					System.err.println("SQLException : " + exception.toString());
					}
				break;
			case "7":
				try {
					String tab_drop;
					System.out.println("Input table:");
					tab_drop = in1.nextLine();
					dropTab_7(conn, tab_drop);
				}
				catch (SQLException exception) {
					System.err.println("SQLException : " + exception.toString());
					}
				break;
			case "8":
				try {
					add_role(conn);
				}
				catch (SQLException exception) {
					System.err.println("SQLException : " + exception.toString());
					}
				break;
			case "0":
				System.out.println("Leaving program");
				break;
			default:
				System.out.println("Wrong input, try again");
				break;
			}
		in1.close();
			}
		conn.close();
	}
}
