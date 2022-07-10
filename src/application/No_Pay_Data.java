package application;

public class No_Pay_Data {

	String day  = "" ; 
	String content = "";
	String pay = ""; 
	
	
	
	No_Pay_Data(String day , String content , String pay){
		this.day = day ; 
		this.content = content ;
		this.pay = pay ;
	}
	
	void Set_day(String day) {
		this.day = day ;
	}
	
	String Get_day() {
		return this.day ;
	}
	
	
	void Set_content(String content) {
		this.content = content ;
	}
	
	String Get_content() {
		return this.content ;
	}
	
	void Set_pay(String pay) {
		this.pay = pay ;
	}
	
	String Get_pay() {
		return this.pay ;
	}
	
}
