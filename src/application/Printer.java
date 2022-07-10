package application;


import java.awt.Desktop;

import java.io.File;

import javax.print.PrintService;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

//import com.spire.xls.Workbook;

public class Printer {
	
	PrintService printerService = null ; 
	
	HSSFWorkbook workbook = new HSSFWorkbook(); 
	HSSFSheet sheet = workbook.createSheet("Sheet");
	
	
	public void File_printer(String name , String month){
	
		try {
			
			Desktop desktop = Desktop.getDesktop();
			try {
				desktop.open(new File("C:\\Users\\Administrator\\Desktop\\請款單檔案存放\\"+name+" "+month+".xls"));
				} catch (Exception e) {           
				e.printStackTrace();
				}
			
		}catch(Exception e) {
			final Alert alert = new Alert(AlertType.CONFIRMATION); // 實體化Alert對話框物件，並直接在建構子設定對話框的訊息類型
			alert.setTitle("錯誤"); //設定對話框視窗的標題列文字
			alert.setHeaderText("內容: "+e.toString()); //設定對話框視窗裡的標頭文字。若設為空字串，則表示無標頭
			alert.showAndWait();
			;
		}
		
	}
	
	
}

