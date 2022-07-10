package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Data_Content {
	
	StringProperty id = new SimpleStringProperty(); 
	StringProperty name  =new SimpleStringProperty();  
	StringProperty date = new SimpleStringProperty(); 
	StringProperty content = new SimpleStringProperty() ; 
	StringProperty set_price = new SimpleStringProperty(); 
	StringProperty send_order = new SimpleStringProperty(); 
	StringProperty get_price = new SimpleStringProperty(); 
	
	
	
	Data_Content(String id ,String name,String date , String content , String set_price, String send_order , String get_price){
		
		setId(id);
		setName(name);
		setDate(date);
		setContent(content);
		setSet_price(set_price);
		setSend_order(send_order);
		setGet_price(get_price);
		
		
	}
	
	Data_Content(String id , String name, String date, String send_order){
		setDate(date);
		setId(id);
		setName(name);
		setSend_order(send_order);
	}
	
	
	
	Data_Content(String name , String content){
		setName(name);
		setContent(content);
	}
	
	
	
	
    public void setId(String id) {
    	
    	this.id.set(id);
    	
    }
    
    public String getId() {
    	return id.get();
    }
	
    public final StringProperty idProperty() {
		   return id;
	}
    
    public void setName(String name) {
    	this.name.set(name);
    	
    }
    
    public String getName() {
    	return this.name.get() ;
    }
    
    public final StringProperty nameProperty() {
		   return name;
	}
    
    public void setDate(String date) {
    	this.date.set(date);
    }
    
    public String getDate() {
    	return this.date.get();
    }
    
    public final StringProperty dateProperty() {
		   return date;
	}
    
    public void setGet_price(String price) {
    	this.get_price.set(price);
    }
    
    public String getGet_price() {
    	return this.get_price.get();
    }
    
    public final StringProperty get_priceProperty() {
		   return get_price;
	}
    
    public void setSend_order(String order) {
    	
    	this.send_order.set(order);
    }
    
    public String getSend_order() {
    	return this.send_order.get();
    }
    
    public final StringProperty send_orderProperty() {
		   return send_order;
	}
    
    public void setContent(String content) {
    	
    	this.content.set(content);
    	
    }
    
    public String getContent() {
    	return this.content.get(); 
    }
    
    public final StringProperty contentProperty() {
		   return content;
	}
    
    public void setSet_price(String price) {
    	
    	this.set_price.set(price);
    	
    }
    
    public String getSet_price() {
    	return this.set_price.get();
    }
    
    public final StringProperty set_priceProperty() {
		   return set_price;
	}
	
	
}
