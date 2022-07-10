package application;

import java.io.*;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFPrintSetup;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Excel_File {
	
	File file ; 
	FileInputStream fip ;
	HSSFWorkbook read_wb ; 
	HSSFSheet read_sheet ; 
	int total_count_row ; 
	int total_pay_row ;
	
	public void init_File() throws IOException{
		
		
	}
	
	
	public void Write_Into_File(String name , String month , String address , String phone ,ArrayList<No_Pay_Data> data) throws Exception {
		
		try {
		file = new File("C:\\Users\\Administrator\\Desktop\\請款單檔案存放\\請款單(空白_長).xls");
		}catch(Exception e) {
			final Alert alert = new Alert(AlertType.CONFIRMATION); // 實體化Alert對話框物件，並直接在建構子設定對話框的訊息類型
			alert.setTitle("錯誤"); //設定對話框視窗的標題列文字
			alert.setHeaderText("內容: 檔案請先關閉再開啟"); //設定對話框視窗裡的標頭文字。若設為空字串，則表示無標頭
			alert.showAndWait();
		}
		total_count_row = 42 ;
		total_pay_row = 44 ;
		
		
		fip = new FileInputStream(file);
		
		read_wb = new HSSFWorkbook(fip);
		int total = 0 ;
		
		read_sheet = read_wb.cloneSheet(0);
		int start_row = 6 ;

		//int last_row = 16 ; 
		
		
		Cell cell = read_sheet.getRow(1).getCell(6);
		cell.setCellValue(month);
		cell = read_sheet.getRow(3).getCell(1);
		cell.setCellValue(name);
		cell = read_sheet.getRow(3).getCell(2);
		cell.setCellValue(address);
		cell = read_sheet.getRow(3).getCell(7);
		cell.setCellValue(phone);
		
		
		
		
		for(int i = 0 ; i < data.size() ; i++) {
			No_Pay_Data temp = data.get(i);
			cell = read_sheet.getRow(start_row).getCell(0);
			
			cell.setCellValue(temp.Get_day());
			cell = read_sheet.getRow(start_row).getCell(1);
			cell.setCellValue(temp.Get_content());
			cell = read_sheet.getRow(start_row).getCell(6);
			int temp_pay = Integer.parseInt(temp.Get_pay());
			total +=temp_pay ;
			
			cell.setCellValue(temp.Get_pay());
			cell = read_sheet.getRow(start_row).getCell(7);
			cell.setCellValue("0");
			cell = read_sheet.getRow(start_row).getCell(8);
			cell.setCellValue(temp.Get_pay());
			//cell.setCellValue(num);
			
			start_row++ ;
		}
		
		int shift_start_row = 40 ; 
		int shift_last_row = 46;
		
		
		
		cell = read_sheet.getRow(total_count_row).getCell(7);
		cell.setCellValue(total);
		cell = read_sheet.getRow(total_pay_row).getCell(7);
		cell.setCellValue(total);
		int last_row = shift_row(shift_start_row , shift_last_row , shift_start_row - (data.size() + 6) );
		// Store File
		
		read_wb.setPrintArea(0,0,8,0,last_row);
		read_sheet.getPrintSetup().setPaperSize(
			      XSSFPrintSetup.A4_PAPERSIZE);
		
		
		
		String n = "C:\\Users\\Administrator\\Desktop\\請款單檔案存放\\"+name+" "+month+".xls";
		fip.close() ;
		FileOutputStream output = null;
		try {
		output = new FileOutputStream(n);
		
		}catch(Exception e) {
			
				final Alert alert = new Alert(AlertType.CONFIRMATION); // 實體化Alert對話框物件，並直接在建構子設定對話框的訊息類型
				alert.setTitle("錯誤"); //設定對話框視窗的標題列文字
				alert.setHeaderText("內容: 檔案請先關閉再開啟"); //設定對話框視窗裡的標頭文字。若設為空字串，則表示無標頭
				alert.showAndWait();
				//alert.setContentText("確定要刪除"); //設定對話框的訊息文字
			
		}
		read_wb.removeSheetAt(0) ;
		read_wb.write(output);
		output.flush();
		output.close();
		read_wb.close();
		
		
		Printer printer = new Printer();
		printer.File_printer(name , month);
		
	}
	//java.io.FileNotFoundException
	
	public int shift_row(int start_row , int last_row , int shift) {
		

		read_sheet.shiftRows(start_row, last_row, -shift);
		
		return last_row - shift ;
		
	}
	
}
