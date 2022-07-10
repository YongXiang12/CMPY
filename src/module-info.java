module javaFx {
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.fxml;
	requires javafx.base;
	requires java.sql;
	requires poi;
	requires java.desktop;
	requires org.apache.commons.compress;
	requires poi.ooxml; 
	//requires org.apache.poi.ooxml ;
	
	opens application to javafx.graphics, javafx.fxml ;
}
