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
				desktop.open(new File("C:\\Users\\Administrator\\Desktop\\�дڳ��ɮצs��\\"+name+" "+month+".xls"));
				} catch (Exception e) {           
				e.printStackTrace();
				}
			
		}catch(Exception e) {
			final Alert alert = new Alert(AlertType.CONFIRMATION); // �����Alert��ܮت���A�ê����b�غc�l�]�w��ܮت��T������
			alert.setTitle("���~"); //�]�w��ܮص��������D�C��r
			alert.setHeaderText("���e: "+e.toString()); //�]�w��ܮص����̪����Y��r�C�Y�]���Ŧr��A�h��ܵL���Y
			alert.showAndWait();
			;
		}
		
	}
	
	
}

