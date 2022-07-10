package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class user_info {
	StringProperty id  = new SimpleStringProperty();
	StringProperty name = new SimpleStringProperty();
	StringProperty address  = new SimpleStringProperty();
	StringProperty phone_number= new SimpleStringProperty(); 
	StringProperty telephone = new SimpleStringProperty();
	StringProperty id_2nd = new SimpleStringProperty();
	StringProperty remark = new SimpleStringProperty();
	
	
	user_info(){
		
	}
	
	
	user_info(String id , String name , String address , String phone_number , String telephone , String id_2nd){
		setID(id);
		setname(name);
		setaddress(address);
		setphone_number(phone_number);
		settelephone(telephone);
		setid_2nd(id_2nd);
	}
	
	public final StringProperty idProperty() {
		   return id;
	}
	
	public final StringProperty nameProperty() {
		   return name;
	}
	
	public final StringProperty addressProperty() {
		   return address;
	}
	public final StringProperty phone_numberProperty() {
		   return phone_number;
	}
	public final StringProperty telephoneProperty() {
		   return telephone;
	}
	public final StringProperty id_2ndProperty() {
			return id_2nd;
	}
	
	public final StringProperty remarkProperty() {
		   return remark;
	}
	

	void setID(String id) {
		this.id.set(id);
	}
	
	String getID() {
		return id.get();
	}
	
	// name ;
	void setname(String name ) {
		this.name.set(name);
	}
	
	String getname() {

		return name.get() ;
	}
	
	// Address 
	void setaddress(String address ) {
		this.address.set(address); ;
	}
	
	String getaddress() {

		return this.address.get() ;
	}
	//phone_number 
	
	void setphone_number(String phone_number ) {
		this.phone_number.set(phone_number);;
	}
	
	String getphone_number() {

		return this.phone_number.get();
	}
	
	// telephone 
	void settelephone(String telephone ) {
		this.telephone.set(telephone); ;
	}
	
	String gettelephone() {

		return this.telephone.get() ;
	}
	
	void setid_2nd(String id_2nd) {
		this.id_2nd.set(id_2nd);;
	}
	
	String getid_2nd() {
		return this.id_2nd.get();
	}

	
	void setremark(String remark) {
		this.remark.set(remark);
	}
	
	String getremark() {
		return remark.get();
	}
	
	
}
