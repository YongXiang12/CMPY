package application;

import java.sql.Connection;
import java.util.* ;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class DB_Controller {
	
	private static Connection connection ; 
	private static PreparedStatement statement ; 
	
	DB_Controller(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
		}catch(Exception e ) {
			
			e.printStackTrace();
			final Alert alert = new Alert(AlertType.CONFIRMATION); // 實體化Alert對話框物件，並直接在建構子設定對話框的訊息類型
			alert.setTitle("錯誤"); //設定對話框視窗的標題列文字
			alert.setHeaderText("內容: 資料庫連線錯誤 "+e.toString()); //設定對話框視窗裡的標頭文字。若設為空字串，則表示無標頭
			alert.showAndWait();
		}
		
	}
	
	
	void  init_Connection() throws SQLException {
		connection = get_Connect();
	}
	

	// change DB_name 
	static void insert(Data_Content db)  {
		try {
			
			System.out.println("insert");
			statement = connection.prepareStatement("insert into content (name, contents, date , set_price , send_order , get_price) values (?,?,?, ?, ?,?);");
			
			// set_value ;
			// e.g : stat.setString(1, "Wang");
			String main_year = db.getDate();
			String sub_year = main_year.substring(0,3);
			String new_year = Integer.toString(Integer.parseInt(sub_year)+1911);
			main_year = main_year.replace(sub_year, new_year);
			
			statement.setString(1, db.getName());
			statement.setString(2, db.getContent());
			
			statement.setString(3, main_year);
			statement.setString(4, db.getSet_price());
			statement.setString(5, db.getSend_order());
			
			if(db.getSend_order().equals("FALSE")) {
				statement.setString(6, "0");
			}else {
				statement.setString(6,  db.getGet_price());
			}
			
			
			statement.executeUpdate() ;
			statement.clearParameters();
			
		}catch(SQLException sql_e) {
			sql_e.printStackTrace();
		}catch(Exception e) {
			final Alert alert = new Alert(AlertType.CONFIRMATION); // 實體化Alert對話框物件，並直接在建構子設定對話框的訊息類型
			alert.setTitle("錯誤"); //設定對話框視窗的標題列文字
			alert.setHeaderText("內容: 資料庫連線錯誤 "+e.toString()); //設定對話框視窗裡的標頭文字。若設為空字串，則表示無標頭
			alert.showAndWait();
		}
	}
	
	
	
	static void delete(String id ) {
		
		
		try {
			// fix the content 
			statement = connection.prepareStatement("DELETE FROM content WHERE id = ?");
			//e.g : statement.setInt(1 , 100) ;
			statement.setString(1, id);
			statement.executeUpdate() ;
			statement.clearParameters();
			
		}catch(SQLException e ) {
			final Alert alert = new Alert(AlertType.CONFIRMATION); // 實體化Alert對話框物件，並直接在建構子設定對話框的訊息類型
			alert.setTitle("錯誤"); //設定對話框視窗的標題列文字
			alert.setHeaderText("內容: 資料庫連線錯誤 "+e.toString()); //設定對話框視窗裡的標頭文字。若設為空字串，則表示無標頭
			alert.showAndWait();
		}
		
	}
	
	static void delete_user_info(String name ) {
		
		
		try {
			// fix the content 
			statement = connection.prepareStatement("DELETE FROM user_info WHERE name = ?");
			//e.g : statement.setInt(1 , 100) ;
			statement.setString(1, name);
			statement.executeUpdate() ;
			statement.clearParameters();
			
		}catch(SQLException e ) {
			final Alert alert = new Alert(AlertType.CONFIRMATION); // 實體化Alert對話框物件，並直接在建構子設定對話框的訊息類型
			alert.setTitle("錯誤"); //設定對話框視窗的標題列文字
			alert.setHeaderText("內容: 資料庫連線錯誤 "+e.toString()); //設定對話框視窗裡的標頭文字。若設為空字串，則表示無標頭
			alert.showAndWait();
		}
		
	}
	
	static Data_Content delete_select_id(String id ) throws SQLException {
		
		statement = connection.prepareStatement("SELECT * FROM content WHERE id = "+id);
		
		ResultSet result = statement.executeQuery() ;
		String name = "" ;
		String content = "" ;
		while(result.next()) {
			name = result.getString("name");
			
			content = result.getString("contents");
		}
		
		Data_Content data = new Data_Content(name , content) ;
		return data ;
	}
	
	static ObservableList<Data_Content> non_premutation_selection(String name , String year){
		boolean All_year = false ;
		
		if(year.equals("all")) {
			All_year = true ;
		}
		
	
		ObservableList<Data_Content> user = FXCollections.observableArrayList(); ;
		try {
			
			// fix content 
			statement = connection.prepareStatement("SELECT * FROM content WHERE name =\""+name+"\"");
			
			// statement.setString(1,"leo")
			String new_date ="" ; 
			ResultSet result = statement.executeQuery() ;
			
			while(result.next()) {
				String date = result.getString("date");
				
				
				
				if(!All_year && date.length() != 0) {
					new_date = date.substring(0,4);
					if((Integer.parseInt(new_date)-1911) != Integer.parseInt(year)){
						continue ;
					}
				}
				
				String id = result.getString("id");
				
				String n = result.getString("name");


				String content = result.getString("contents");
				String send_order = result.getString("send_order");
				String get_price = result.getString("get_price");
				String set_price = result.getString("set_price");
				
				date = date.replace(new_date , year);
				
				user.add(new Data_Content(id,n,date,content,set_price,send_order,get_price));
			}
			statement.clearParameters();
			
			
		}catch(SQLException e) {
			final Alert alert = new Alert(AlertType.CONFIRMATION); // 實體化Alert對話框物件，並直接在建構子設定對話框的訊息類型
			alert.setTitle("錯誤"); //設定對話框視窗的標題列文字
			alert.setHeaderText("內容: 資料庫連線錯誤 "+e.toString()); //設定對話框視窗裡的標頭文字。若設為空字串，則表示無標頭
			alert.showAndWait();
		}
		
		
		
		user.add(new Data_Content("-1",name,year+"/","0"));
		user.add(new Data_Content("-2",name,year+"/","0"));
		user.add(new Data_Content("-3",name,year+"/","0"));

		return user ; 
		
	}
	
	
	static ObservableList<Data_Content> select(String name , String year) {
		boolean All_year = false ;
		if(year.equals("all")) {
			All_year = true ;
		}
		
		
		
		
		ObservableList<Data_Content> user = FXCollections.observableArrayList();
		try {
			
			// fix content 
			statement = connection.prepareStatement("SELECT * FROM content WHERE name =\""+name+"\"");
			
			// statement.setString(1,"leo")
			String new_date ="" ; 
			ResultSet result = statement.executeQuery() ;
			
			while(result.next()) {
				String date = result.getString("date");
				
				
				
				if(!All_year && date.length() != 0) {
					new_date = date.substring(0,4);
					if((Integer.parseInt(new_date)-1911) != Integer.parseInt(year)){
						continue ;
					}
				}
				
				int index1 = date.indexOf("/");
				int index2 = date.indexOf("/",index1+1);
				int index3 = date.length() - index2;
				
			
				
				
				if(index2 - index1 == 2) {
					StringBuilder str =new StringBuilder(date);
					str.insert(index1+1, '0');
					date = str.toString();
				}
				
				if(index3==2 ) {
					StringBuilder str =new StringBuilder(date);
					str.insert(index2+2, '0');
					date = str.toString();
				}
				
				String id = result.getString("id");
				
				String n = result.getString("name");


				String content = result.getString("contents");
				String send_order = result.getString("send_order");
				String get_price = result.getString("get_price");
				String set_price = result.getString("set_price");
				
				date = date.replace(new_date , year);
				
				user.add(new Data_Content(id,n,date,content,set_price,send_order,get_price));
			}
			statement.clearParameters();
			
			
		}catch(SQLException e) {
			e.printStackTrace();
			final Alert alert = new Alert(AlertType.CONFIRMATION); // 實體化Alert對話框物件，並直接在建構子設定對話框的訊息類型
			alert.setTitle("錯誤"); //設定對話框視窗的標題列文字
			alert.setHeaderText("內容: 資料庫連線錯誤 "+e.toString()); //設定對話框視窗裡的標頭文字。若設為空字串，則表示無標頭
			alert.showAndWait();
		}
		

		
		if(user.size() != 0) {
			DataComparator comparator = new DataComparator();
			
			Collections.sort(user , comparator);
		}
		
		user.add(new Data_Content("-1",name,year+"/" ,"0"));
		user.add(new Data_Content("-2",name,year+"/","0"));
		user.add(new Data_Content("-3",name,year+"/","0"));
		
		return user ; 
		
	}
	
	
	static ObservableList<Data_Content> things_search_select(String name , String thing){
		
		ObservableList<Data_Content> user = FXCollections.observableArrayList();
		
		try {
		
		statement = connection.prepareStatement("SELECT * FROM content WHERE name =\""+name+"\"");
		
		// statement.setString(1,"leo")
		
		ResultSet result = statement.executeQuery() ;
		
		while(result.next()) {
			
			String things = result.getString("contents");
			
			
			if(!things.contains(thing)) {
				continue; 
			}
			
			String date = result.getString("date");
			String new_date  = date.substring(0,4);
			
			String year = Integer.toString(Integer.parseInt(new_date)-1911);
			
			int index1 = date.indexOf("/");
			int index2 = date.indexOf("/",index1+1);
			int index3 = date.length() - index2;
			
		
			
			
			if(index2 - index1 == 2) {
				StringBuilder str =new StringBuilder(date);
				str.insert(index1+1, '0');
				date = str.toString();
			}
			
			if(index3==2 ) {
				StringBuilder str =new StringBuilder(date);
				str.insert(index2+2, '0');
				date = str.toString();
			}
			
			String id = result.getString("id");
			
			String n = result.getString("name");


			String content = result.getString("contents");
			String send_order = result.getString("send_order");
			String get_price = result.getString("get_price");
			String set_price = result.getString("set_price");
			
			date = date.replace(new_date , year);
			
			user.add(new Data_Content(id,n,date,content,set_price,send_order,get_price));
		}
		statement.clearParameters();
		
		}catch(Exception e) {
			e.printStackTrace();
			final Alert alert = new Alert(AlertType.CONFIRMATION); // 實體化Alert對話框物件，並直接在建構子設定對話框的訊息類型
			alert.setTitle("錯誤"); //設定對話框視窗的標題列文字
			alert.setHeaderText("內容: 資料庫連線錯誤 "+e.toString()); //設定對話框視窗裡的標頭文字。若設為空字串，則表示無標頭
			alert.showAndWait();
		}
		
		if(user.size() != 0) {
			thingsComparator comparator = new thingsComparator();
			
			Collections.sort(user , comparator);
		}
		
		return user;
	}
	
	static ArrayList<No_Pay_Data> no_pay_data(String name , String month , String year ){
		
		Boolean Has_month = true ;
		Boolean Has_Year = true; 
		
		ArrayList<No_Pay_Data> data = new ArrayList<>();
		
		if(month.equals("0") ) {
			Has_month = false ;
		}
		
		if(year.equals("0")) {
			Has_Year = false ;
		}
		
		try {
			
			statement = connection.prepareStatement("SELECT date , contents , set_price FROM content WHERE name =\""+name+"\""+" AND send_order =\""+"FALSE"+"\"");

			ResultSet result = statement.executeQuery() ;
			
			while(result.next()) {
				String date = result.getString("date");
				

				
				String pay = result.getString("set_price");
				String content = result.getString("contents");
				
				
				
				if(pay == null || pay.length() == 0) {
					continue ;
				}
				
				if(date.length() < 5) {
					continue ;
				}
				
				if(Has_Year) {
					int y = Integer.parseInt(year)+1911 ;
					if(!(date.substring(0,4).equals(Integer.toString(y))))
						continue ;
					
					if(Has_month) {
						// 7 - 10 
						if(month.length() >= 3 ) {
							
							String during[] = month.split("-");
							
							int first = Integer.parseInt(during[0]) ;
							int last =Integer.parseInt(during[1]);
							int m ; 

							
							if(date.substring(6,7).equals("/")) {
								m = Integer.parseInt(date.substring(5,6));
							}else {
								m = Integer.parseInt(date.substring(5,7));
							}
							
							if(!(m >= first && m <= last)) {
								continue ;
							}
							
						}else {
							int m = Integer.parseInt(month);
							
							if(m >=10) {
								
								if(date.substring(6,7).equals("/")) {
									continue ;
								}else {
									int temp = Integer.parseInt(date.substring(5,7));
									
									if(m != temp)
										continue ;
								}
								
								
							}else {
								
								if(!date.substring(6,7).equals("/")) {
									continue ;
								}else {
										int temp = Integer.parseInt(date.substring(5,6));					
										if(m != temp)
											continue ;
								}
								
								
							}
						}
							
					}
				}
				
				
				date = date.replace(date.substring(0,4),Integer.toString(Integer.parseInt(date.substring(0,4))-1911));
				
				
				int index1 = date.indexOf("/");
				int index2 = date.indexOf("/",index1+1);
				int index3 = date.length() - index2;
				

				
				
				if(index2 - index1 == 2) {
					StringBuilder str =new StringBuilder(date);
					str.insert(index1+1, '0');
					date = str.toString();
				}
				
				if(index3==2 ) {
					StringBuilder str =new StringBuilder(date);
					str.insert(index2+2, '0');
					date = str.toString();
				}
				
				No_Pay_Data no_pay = new No_Pay_Data(date , content , pay);
				data.add(no_pay);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			final Alert alert = new Alert(AlertType.CONFIRMATION); // 實體化Alert對話框物件，並直接在建構子設定對話框的訊息類型
			alert.setTitle("錯誤"); //設定對話框視窗的標題列文字
			alert.setHeaderText("內容: 資料庫連線錯誤 "+e.toString()); //設定對話框視窗裡的標頭文字。若設為空字串，則表示無標頭
			alert.showAndWait();
		}
		
		if(data.size() != 0) {
			NoPayComparator comparator = new NoPayComparator();
			
			Collections.sort(data , comparator);
		}
		
		
		return data ;
	}
	
	
	static int no_pay_data_total_price(String name , String year){
		
	
		
		int total_price = 0 ; 
		
		try {
			
			statement = connection.prepareStatement("SELECT date , contents , set_price FROM content WHERE name =\""+name+"\""+" AND send_order =\""+"FALSE"+"\"");
			ResultSet result = statement.executeQuery() ;
			
			while(result.next()) {
				String date = result.getString("date");
				
				int y = Integer.parseInt(year)+1911 ;
				
				if(date == null ||!(date.substring(0,4).equals(Integer.toString(y))))
					continue ;
				
				String price  =result.getString("set_price");
				if(price != null )
					total_price += Integer.parseInt(price);
	
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			final Alert alert = new Alert(AlertType.CONFIRMATION); // 實體化Alert對話框物件，並直接在建構子設定對話框的訊息類型
			alert.setTitle("錯誤"); //設定對話框視窗的標題列文字
			alert.setHeaderText("內容: 資料庫連線錯誤 "+e.toString()); //設定對話框視窗裡的標頭文字。若設為空字串，則表示無標頭
			alert.show();
		}
		
		
		return total_price;
		
	}
	
	
	
	
	
	static Set<String> no_pay_list_search() throws SQLException{
		
		Set<String> set = new HashSet<String>();
		
		statement = connection.prepareStatement("SELECT name ,set_price FROM content WHERE send_order =\""+"FALSE"+"\"");
		
		
		ResultSet result = statement.executeQuery() ;
		
		while(result.next()) {
			
			String name = result.getString("name");
			String set_price = result.getString("set_price");
	
			
			if(set_price == null || set_price.length() ==0 || set_price.equals("0"))
					continue ;
				
		
			set.add(name);
		}
	
		return set ; 
		
	}
	
	
	String[] user_info_select(String name) {
		
		String[] str = new String[7];
		
		try {
			
			statement = connection.prepareStatement("SELECT * FROM user_info WHERE name =\""+name+"\"");
		// statement.setString(1,"leo")
		
			ResultSet result = statement.executeQuery() ;
			while(result.next()) {
				str[0] = result.getString("id");
				//System.out.println(str[0]);
				str[1] = result.getString("name");
				str[2] = result.getString("address");
				str[3] = result.getString("phone_number");
				str[4] = result.getString("telephone");
				str[5] = result.getString("id_2nd");
				str[6] = result.getString("remark");
			}
			
		}catch(Exception e ) {
			e.printStackTrace();
			final Alert alert = new Alert(AlertType.CONFIRMATION); // 實體化Alert對話框物件，並直接在建構子設定對話框的訊息類型
			alert.setTitle("錯誤"); //設定對話框視窗的標題列文字
			alert.setHeaderText("內容: 資料庫連線錯誤 "+e.toString()); //設定對話框視窗裡的標頭文字。若設為空字串，則表示無標頭
			alert.showAndWait();
		}
		
		
		
		return str ; 
	}

	static void user_info_insert(user_info user)  {
		try {
			statement = connection.prepareStatement("insert into user_info (name, address, phone_number ,telephone , id_2nd) values (?,?,?,?,?);");
			
			// set_value ;
			// e.g : stat.setString(1, "Wang");
			
			
			statement.setString(1, user.getname());
			statement.setString(2, user.getaddress());
			statement.setString(3, user.getphone_number());
			statement.setString(4, user.gettelephone());
			statement.setString(5, user.getid_2nd());
			
		
			statement.executeUpdate() ;
			statement.clearParameters();

		}catch(SQLException sql_e) {
			sql_e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
			final Alert alert = new Alert(AlertType.CONFIRMATION); // 實體化Alert對話框物件，並直接在建構子設定對話框的訊息類型
			alert.setTitle("錯誤"); //設定對話框視窗的標題列文字
			alert.setHeaderText("內容: 資料庫連線錯誤 "+e.toString()); //設定對話框視窗裡的標頭文字。若設為空字串，則表示無標頭
			alert.showAndWait();
		}
	}
	
	
	
	
static void user_info_update(user_info user) {
		
		try {
			
			statement = connection.prepareStatement("UPDATE user_info SET name = ?, address = ? , phone_number = ? , telephone = ? ,id_2nd =? , remark=? WHERE id = ? ");

			
			statement.setString(1, user.getname());
			statement.setString(2, user.getaddress());
			statement.setString(3, user.getphone_number());
			statement.setString(4, user.gettelephone());
			statement.setString(5, user.getid_2nd());
			statement.setString(6, user.getremark());
			// modify to put id 
			statement.setString(7, user.getID());

			
			statement.executeUpdate();
			statement.clearParameters();
			
		}catch(SQLException e) {
			e.printStackTrace() ;
		}
		
		
	}
	
	
	
	ArrayList<String> search_combox_select() {
		ArrayList<String> list = new ArrayList<>();
		try {
			
		
			statement = connection.prepareStatement("SELECT name FROM user_info");
		
		// statement.setString(1,"leo")
		
			ResultSet result = statement.executeQuery() ;
			
			
			while(result.next()) {
				String name = result.getString("name");
				list.add(name);
			}
			
		}catch(Exception e ) {
			e.printStackTrace();
			final Alert alert = new Alert(AlertType.CONFIRMATION); // 實體化Alert對話框物件，並直接在建構子設定對話框的訊息類型
			alert.setTitle("錯誤"); //設定對話框視窗的標題列文字
			alert.setHeaderText("內容: 資料庫連線錯誤 "+e.toString()); //設定對話框視窗裡的標頭文字。若設為空字串，則表示無標頭
			alert.showAndWait();
		}
		
		return list ;
	}
	
	 static void update(Data_Content db) {
		
		try {
			//fix 
			statement = connection.prepareStatement("UPDATE content SET name = ?, contents = ? , date = ? , get_price = ?, send_order =?, set_price =? WHERE id = ? ");
			
			//statement.setString(1,Leo) 
			String sub_year = (db.getDate()).substring(0,3) ;
			int year = Integer.parseInt(sub_year)+1911;
			String main_year = db.getDate().replace(sub_year, Integer.toString(year));
			
			statement.setString(1, db.getName());
			statement.setString(2, db.getContent());
			statement.setString(3, main_year);
			statement.setString(4, db.getGet_price());
			statement.setString(5, db.getSend_order());
			statement.setString(6, db.getSet_price());
			statement.setString(7, db.getId());
			
			statement.executeUpdate();
			statement.clearParameters();
			
		}catch(SQLException e) {
			e.printStackTrace() ;
		}
		
	
	}
	 
	 
	 static void name_update(String new_name , String old_name) {
			
			try {
				//fix 
				statement = connection.prepareStatement("UPDATE content SET name = ? WHERE name = ? ");
					
				//statement.setString(1,Leo) 

				
				statement.setString(1, new_name);
				statement.setString(2, old_name );
				
				statement.executeUpdate();
				statement.clearParameters();
				
			}catch(SQLException e) {
				e.printStackTrace();
				final Alert alert = new Alert(AlertType.CONFIRMATION); 
				alert.setTitle("錯誤"); 
				alert.setHeaderText("內容: 資料庫連線錯誤 "+e.toString()); 
				alert.showAndWait();
			}
			
			
			
			
		}

	
	private static Connection get_Connect() throws SQLException{
		//company_data
		// first_test 
		String Server_Name = "localhost";
		String Database_name = "company_data";
		String url = "jdbc:mysql://" + Server_Name + "/" + Database_name ;
		String user = "root";
		String password = "jk660716";
		
		return DriverManager.getConnection(url , user , password);
		
	}
	
	
	
	
}


class DataComparator implements Comparator<Data_Content> {

	@Override
	public int compare(Data_Content content1, Data_Content content2) {
		
		
		String date1 = content1.getDate();
		String date2 = content2.getDate();
		
		return date1.compareTo(date2);
		
	}
}

class thingsComparator implements Comparator<Data_Content> {

	@Override
	public int compare(Data_Content content1, Data_Content content2) {
		
		
		String date1 = content1.getDate();
		String date2 = content2.getDate();
		
		return date2.compareTo(date1);
		
	}
}



class NoPayComparator implements Comparator<No_Pay_Data> {

	@Override
	public int compare(No_Pay_Data content1, No_Pay_Data content2) {
		
		
		String date1 = content1.Get_day();
		String date2 = content2.Get_day();
		
		return date1.compareTo(date2);
		
	}
}
