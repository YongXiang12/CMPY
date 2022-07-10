package application;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.Date;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.scene.input.KeyCode;
import java.sql.*;

public class controller implements Initializable {
	
	
	DB_Controller db_controller ; 
	Data_Content current_user ; 
	String user_id  ;
	//String User_name ;
	//private Boolean 
	private ArrayList<String> list = new ArrayList<>();
	private ArrayList<String> keyword_list  = new ArrayList<String>();
	boolean isFirst = false ;
	
	@FXML
	private AnchorPane top_anchorpane;
	
	@FXML
	private ListView<String> comsumer_listview ;
	
	
    @FXML
    private TableView<Data_Content> table_view;
    
    @FXML
    private TableColumn<Data_Content, String> id;


    @FXML
    private TableColumn<Data_Content, String> date;

    @FXML
    private TableColumn<Data_Content, String> content;

    @FXML
    private TableColumn<Data_Content, String> set_price;

    @FXML
    private TableColumn<Data_Content, String> send_order;

    @FXML
    private TableColumn<Data_Content, String> get_price;
    
    @FXML
    private ComboBox<String> search_combox;
    
    @FXML
    private ComboBox<String> things_search;
    
    @FXML
    private Label name_lable;

    @FXML
    private Text name_txt;

    @FXML
    private Label address_lable;

    @FXML
    private Text address_txt;

    @FXML
    private Label phone_number_label;

    @FXML
    private Text phone_number_txt;

    @FXML
    private Label telephone_label;

    @FXML
    private Text telephone_txt;
    
    @FXML
    private Text id2_txt ;
    
    @FXML
    private Text user_name ; 
    
    
    @FXML
    private Text year_text ;

    @FXML
    private Text total_prices ;
    
    @FXML
    private Button modify_list ;
   
    @FXML 
    private Button add_new_list ;
    
    @FXML 
    private Button no_pay_button ;
    
    @FXML
    private Button modify_year ;
    
    @FXML 
    private Button Compara_button;
    
    @FXML
    private Button printer_button ; 
    
    @FXML 
    private Button delete_button ;
    
    @FXML
    private Text remark_txt ;
    
    
    Stage no_pay_search_stage  ;
    
   
    
    ListView<String> Fast_view = new ListView();
    TablePosition<Data_Content, String> position ;
    private void Set_Table() {
    	
    	table_view.setEditable(true);
    	table_view.getSelectionModel().setCellSelectionEnabled(true);
        
		table_view.setOnKeyReleased(new EventHandler<KeyEvent>() {
		//table_view.set
			@Override
		    public void handle(KeyEvent t) {
				
				
				
		        if (t.getCode() == KeyCode.RIGHT) {
		        	position = table_view.getFocusModel().getFocusedCell() ;
		            //table_view.getSelectionModel().selectNext();
		            
		            table_view.edit(position.getRow(), position.getTableColumn());

		            table_view.getSelectionModel().getSelectedItem().getId();
		         }
		        
		        
		     }
		});
			
		
		
		//ObservableList<Data_Content> user = FXCollections.observableArrayList(new Data_content("leo" , "asdas" , "123456" , "554564"));
		//user.add(new user_info("jhon" , "qwer" ,"123123","123123"));
		//user.add(new user_info());
		//user.add(new user_info());
		
		id.setCellValueFactory(new PropertyValueFactory<Data_Content, String>("Id"));
		id.setCellValueFactory(data-> data.getValue().idProperty());
		id.setCellFactory(c -> new TableCellController() );
		//name.setEditable(true);
		
		id.setStyle("-fx-alignment: CENTER;");
		id.prefWidthProperty().bind(table_view.widthProperty().multiply(0.05));
		/*
		name.setCellValueFactory(new PropertyValueFactory<Data_Content, String>("Name"));
		name.setCellValueFactory(data-> data.getValue().nameProperty());
		name.setCellFactory(TextFieldTableCell.forTableColumn());
		//name.setEditable(true);
		
		name.setOnEditCommit((CellEditEvent<Data_Content , String> n)->{
			n.getRowValue().setName(n.getNewValue());
			
			// get the edited class 
			current_user = n.getRowValue() ;
			alto_update(current_user);
		});
		
		name.setStyle("-fx-alignment: CENTER;");
		*/

		date.setCellValueFactory(data-> data.getValue().dateProperty());
		date.setCellFactory(c -> new TableCellController() );
		//address.setEditable(true);
		
		date.setOnEditCommit((CellEditEvent<Data_Content , String> n)->{
			n.getRowValue().setDate(n.getNewValue());
			
			// get the edited class 
			current_user = n.getRowValue() ;
			alto_update(current_user);
		});
		
		date.setStyle("-fx-alignment: CENTER;");
		date.prefWidthProperty().bind(table_view.widthProperty().multiply(0.1));
		
		content.setCellValueFactory(data-> data.getValue().contentProperty());
		content.setCellFactory(c -> new TableCellController() );
		
	
		content.setOnEditCommit((CellEditEvent<Data_Content , String> n)->{
			n.getRowValue().setContent(n.getNewValue());
			
			// get the edited class 
			current_user = n.getRowValue() ;
		
			//table_view.getSelectionModel().selectNext();
			alto_update(current_user);
		});
		content.setStyle("-fx-alignment: CENTER;");
		content.prefWidthProperty().bind(table_view.widthProperty().multiply(0.42));
		
		
		set_price.setCellValueFactory(data-> data.getValue().set_priceProperty());
		set_price.setCellFactory(c -> new TableCellController() );
		//telephone.setEditable(true);
		set_price.setOnEditCommit((CellEditEvent<Data_Content , String> n)->{
			n.getRowValue().setSet_price(n.getNewValue());
			
			// get the edited class 
			current_user = n.getRowValue() ;
			alto_update(current_user);
		});
		
		
		set_price.setCellValueFactory(data-> data.getValue().set_priceProperty());
		set_price.setCellFactory(c -> new TableCellController() );
		//telephone.setEditable(true);
		set_price.setOnEditCommit((CellEditEvent<Data_Content , String> n)->{
			n.getRowValue().setSet_price(n.getNewValue());
			
			// get the edited class 
			current_user = n.getRowValue() ;
			alto_update(current_user);
		});
		set_price.setStyle("-fx-alignment: CENTER;");
		set_price.prefWidthProperty().bind(table_view.widthProperty().multiply(0.15));
		
		send_order.setCellValueFactory(data-> data.getValue().send_orderProperty());
		send_order.setStyle("-fx-alignment: CENTER;");
		send_order.prefWidthProperty().bind(table_view.widthProperty().multiply(0.1));
		
		Callback<TableColumn<Data_Content, String>, TableCell<Data_Content, String>> cellFactory = new Callback<TableColumn<Data_Content, String>, TableCell<Data_Content, String>>() {

			@Override
			public TableCell<Data_Content, String> call(TableColumn<Data_Content, String> arg0) {
			
				
				ToggleButton  tb1 = new ToggleButton();
				final TableCell<Data_Content ,String> cell = new TableCell<Data_Content , String>(){
					
					@Override
					public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                    
                        
                        if (empty) {
                            setGraphic(null);
                        } else {
                        	
	                         if(item != null) {
	                        	
	                        	if(item.equals("TRUE")) {
	                        		
	                        		tb1.setSelected(true);
	                        		tb1.setText("Yes");
	                        		tb1.setStyle("-fx-background-color: lightgreen;");
	                        	}else {
	                        		tb1.setText("No");
	                        		tb1.setStyle("-fx-background-color: pink;");
	                        	}
	                        	
	                        	//total_prices.setText(Integer.toString(DB_Controller.no_pay_data_total_price(user_name.getText() ,year_text.getText() )));
	                 
	                            setGraphic(tb1);
	                        }
                        }
                    }
					
					
				};
				
			
				// 筆記!!
				
				tb1.setOnAction((ActionEvent event) -> {
					// 先取得當前位置
					int index = cell.getIndex();
   
					
					// 之後取tableview
                    Data_Content d =table_view.getItems().get(index);
                    
                 
                    
                    // change table button 
					if(tb1.isSelected()) {
						
						tb1.setText("Yes");
						tb1.setStyle("-fx-background-color: lightgreen;");
						d.setGet_price(d.getSet_price());
						d.setSend_order("TRUE");
			
					}else {
						
						tb1.setText("NO");
						tb1.setStyle("-fx-background-color: pink;");
						d.setGet_price("0");
						d.setSend_order("FALSE");
					}
					
					DB_Controller.update(d);
					total_prices.setText(Integer.toString(DB_Controller.no_pay_data_total_price(user_name.getText() ,year_text.getText() )));
					table_view.refresh();
                });
				
		
				
				return cell;
			}
			
			
		};
		
		
		
		send_order.setCellFactory(cellFactory);
		//telephone.setEditable(true);
		//send_order.setOnEditCommit(){
			
		
				
				
				
				
				
				/*(CellEditEvent<Data_Content , String> n)->{
			n.getRowValue().setSend_order(n.getNewValue());
			
			// get the edited class 
			current_user = n.getRowValue() ;
			
		}
		*/
			
				
		//);
		
		
		get_price.setCellValueFactory(data-> data.getValue().get_priceProperty());
		
		get_price.setCellFactory(TextFieldTableCell.forTableColumn());
		//telephone.setEditable(true);
		get_price.setOnEditCommit((CellEditEvent<Data_Content , String> n)->{
			n.getRowValue().setGet_price(n.getNewValue());
			
			// get the edited class 
			current_user = n.getRowValue() ;
			alto_update(current_user);
		});
		get_price.setStyle("-fx-alignment: CENTER;");
    	get_price.prefWidthProperty().bind(table_view.widthProperty().multiply(0.15));
    }
    
    
    
    private void alto_update(Data_Content db) {
    		
    		ObservableList<Data_Content> user = null ;
    		
    		if(db.getId().charAt(0) != '-') {
    			DB_Controller.update(current_user);
    			total_prices.setText(Integer.toString(DB_Controller.no_pay_data_total_price(current_user.getName() ,year_text.getText() )));
    		}else {
    			if(db.getDate().length() > 4) {
	    			position = table_view.getFocusModel().getFocusedCell() ;
		           
	    			
	    			if(current_user.getGet_price() == null) {
	    				System.out.println("set");
	    				current_user.setSend_order("FALSE");
	    				current_user.setGet_price("0");
	    				
	    			}
	    			
	    			DB_Controller.insert(current_user);
					user = DB_Controller.non_premutation_selection(current_user.getName() , year_text.getText());
					table_view.getItems().clear();
					table_view.getItems().addAll(user);
					
					table_view.getFocusModel().focus(position);
					total_prices.setText(Integer.toString(DB_Controller.no_pay_data_total_price(current_user.getName() ,year_text.getText() )));
    			}
				
				
    		}
    		
    	
    		
    			
    	
    	
    }
    

    
 
    private void init_things_search_table(String name ,String thing) {
    	
    	if(thing != null) {
    		ObservableList<Data_Content> user = FXCollections.observableArrayList();
			user = DB_Controller.things_search_select(name , thing);
			table_view.setItems(user);
    	}else {
    		final Alert alert = new Alert(AlertType.CONFIRMATION); // 實體化Alert對話框物件，並直接在建構子設定對話框的訊息類型
			alert.setTitle("錯誤"); //設定對話框視窗的標題列文字
			alert.setHeaderText("內容: 請輸入比對項目"); //設定對話框視窗裡的標頭文字。若設為空字串，則表示無標頭
			alert.showAndWait();
    	}

    }
    
    
    private void init_table(String name ) {
    	ObservableList<Data_Content> user = FXCollections.observableArrayList();
		user = DB_Controller.select(name ,year_text.getText());
		table_view.setItems(user);
		total_prices.setText(Integer.toString(DB_Controller.no_pay_data_total_price(user_name.getText() ,year_text.getText() )));
    }
    
    
    
    
    private void Set_Userinfo_UI(String name) {
    	
    	String[] str = db_controller.user_info_select(name);
    	name_txt.setUnderline(true);
    	user_id = str[0];
    	name_txt.setText(str[1]);
    	address_txt.setUnderline(true);
    	address_txt.setText(str[2]);
    	phone_number_txt.setUnderline(true);
    	phone_number_txt.setText(str[3]);
    	telephone_txt.setUnderline(true);
    	telephone_txt.setText(str[4]);
    	id2_txt.setUnderline(true);
    	id2_txt.setText(str[5]);
    	remark_txt.setUnderline(true);
    	remark_txt.setText(str[6]);
    	
    }
    
    
    
    // here to update the data
    public void List_search(String str){
    	
    	if(keyword_list.size() != 0) 
    		keyword_list.clear();
    	
    	for(int i = 0 ; i < list.size(); i++) {
    		
    		if(list.get(i).contains(str)) {
    			
    			keyword_list.add(list.get(i));
    		}
    		
    	}
    	
    	
    	if(keyword_list.size() != 0) {
    		
    		search_combox.getItems().clear();
    		
    	}
    	
    	for(int i = 0 ; i < keyword_list.size() ; i++) {
    		
    		search_combox.getItems().add(keyword_list.get(i));
    	}
    	
    }
    
    
    public void modify_window() {
    	
    	Label name = new Label("客戶名稱");
    	Label address = new Label("地址");
    	Label telephone = new Label("行動電話");
    	Label phone = new Label("家用電話");
    	Label id2 = new Label("編碼");
    	Label remark = new Label("備註");
    	Button correct = new Button("確認");
    	Button cancel = new Button("取消");
    	
    	
    	TextField name_field = new TextField();
    	name_field.setPrefColumnCount(15);
    	name_field.setText(name_txt.getText());
    	name_field.setAlignment(Pos.CENTER);
    	name_field.selectAll();
    	
    	TextField address_field = new TextField();
    	address_field.setPrefColumnCount(15);
    	address_field.setText(address_txt.getText());
    	address_field.setAlignment(Pos.CENTER);
    	address_field.selectAll();
    	
    	TextField telephone_field = new TextField();
    	telephone_field.setPrefColumnCount(15);
    	telephone_field.setText(telephone_txt.getText());
    	telephone_field.setAlignment(Pos.CENTER);
    	telephone_field.selectAll();
    	
    	
    	TextField phone_field = new TextField();
    	phone_field.setPrefColumnCount(15);
    	phone_field.setText(phone_number_txt.getText());
    	phone_field.setAlignment(Pos.CENTER);
    	phone_field.selectAll();
    	
    	TextField id2_field = new TextField();
    	id2_field.setPrefColumnCount(15);
    	id2_field.setText(id2_txt.getText());
    	id2_field.setAlignment(Pos.CENTER);
    	id2_field.selectAll();
    	
    	TextField remark_field = new TextField();
    	remark_field.setPrefColumnCount(15);
    	remark_field.setText(remark_txt.getText());
    	remark_field.setAlignment(Pos.CENTER);
    	remark_field.selectAll();
    	
    	// buttons -> correct , cancel 
    	HBox hbox = new HBox();
    	//hbox.setMargin(hbox, new Insets(0,0,0,5));
    	hbox.setPrefWidth(100);
    	hbox.setPrefHeight(20);
    	hbox.setSpacing(10);
    	correct.setMaxWidth(hbox.getPrefWidth());
    	correct.setMaxHeight(hbox.getPrefHeight());
    	
    	cancel.setMaxWidth(hbox.getPrefWidth());
    	cancel.setMaxHeight(hbox.getPrefHeight());
    	//cancel.setPadding(new Insets(0,0,0,10));
    	hbox.getChildren().addAll(correct , cancel);
    	hbox.setAlignment(Pos.CENTER_RIGHT);
    	// create GridPanel 
    	GridPane gridpane = new GridPane();
		gridpane.setAlignment(Pos.CENTER);
		gridpane.setHgap(10);
		gridpane.setVgap(10);
		gridpane.setPadding(new Insets(5, 5, 5, 5));
		
		
		gridpane.add(name , 0 ,0);
		gridpane.add(address , 0 ,1 );
		gridpane.add(telephone, 0, 2);
		gridpane.add(phone, 0, 3);
    	gridpane.add(id2, 0, 4);
    	gridpane.add(remark,0,5);
		// textfield add 
		gridpane.add(name_field , 1 ,0);
		gridpane.add(address_field , 1 ,1 );
		gridpane.add(telephone_field, 1, 2);
		gridpane.add(phone_field, 1, 3);
		gridpane.add(id2_field, 1, 4);
		gridpane.add(remark_field,1,5);
		// button add 
		gridpane.add(hbox, 1, 6);
		
		
		// create new stage 
		Scene scene = new Scene(gridpane);
		
		Stage stage = new Stage();
		
		stage.setWidth(300);
		stage.setHeight(350);
		stage.setScene(scene);
		stage.show();
    	
		correct.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {

				
				if(!name_field.getText().equals(user_name.getText())) {
					DB_Controller.name_update(name_field.getText() , user_name.getText());
				}
				String[] str = db_controller.user_info_select(user_name.getText());
				user_info user = new user_info();
				user.setID(str[0]);
				user.setname(name_field.getText());
				user.setaddress(address_field.getText());
				user.settelephone(telephone_field.getText());
				user.setphone_number(phone_field.getText());
				user.setid_2nd(id2_field.getText());
				user.setremark(remark_field.getText());
				
				
				DB_Controller.user_info_update(user);
				//Set_Userinfo_UI(name_field.getText());
				
				
				list = db_controller.search_combox_select();

				user_name.setText(name_field.getText());
				Set_Userinfo_UI(name_field.getText());
				
				
				stage.close();
			}
			
			
		});
		
		cancel.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				stage.close();
			}
			
			
		});
		
		
		
    }
    
    
    public void add_new_list() {
    	
    	
    	Label name = new Label("客戶名稱");
    	Label address = new Label("地址");
    	Label telephone = new Label("行動電話");
    	Label phone = new Label("家用電話");
    	Label id2 = new Label("統編");
    	Label remark = new Label("備註");
    	Button correct = new Button("確認");
    	Button cancel = new Button("取消");
    	
    	
    	TextField name_field = new TextField();
    	name_field.setPrefColumnCount(15);
    	name_field.setAlignment(Pos.CENTER);
    	name_field.selectAll();
    	
    	TextField address_field = new TextField();
    	address_field.setPrefColumnCount(15);
    	address_field.setAlignment(Pos.CENTER);
    	address_field.selectAll();
    	
    	TextField telephone_field = new TextField();
    	telephone_field.setPrefColumnCount(15);
    	telephone_field.setAlignment(Pos.CENTER);
    	telephone_field.selectAll();
    	
    	
    	TextField phone_field = new TextField();
    	phone_field.setPrefColumnCount(15);
    	phone_field.setAlignment(Pos.CENTER);
    	phone_field.selectAll();

    	TextField id2_field = new TextField();
    	id2_field.setPrefColumnCount(15);
    	id2_field.setAlignment(Pos.CENTER);
    	id2_field.selectAll();
    	
    	TextField remark_field = new TextField();
    	remark_field.setPrefColumnCount(15);
    	remark_field.setAlignment(Pos.CENTER);
    	remark_field.selectAll();
    	
    	// buttons -> correct , cancel 
    	HBox hbox = new HBox();
    	//hbox.setMargin(hbox, new Insets(0,0,0,5));
    	hbox.setPrefWidth(100);
    	hbox.setPrefHeight(20);
    	hbox.setSpacing(10);
    	correct.setMaxWidth(hbox.getPrefWidth());
    	correct.setMaxHeight(hbox.getPrefHeight());
    	
    	cancel.setMaxWidth(hbox.getPrefWidth());
    	cancel.setMaxHeight(hbox.getPrefHeight());
    	//cancel.setPadding(new Insets(0,0,0,10));
    	hbox.getChildren().addAll(correct , cancel);
    	hbox.setAlignment(Pos.CENTER_RIGHT);
    	// create GridPanel 
    	GridPane gridpane = new GridPane();
		gridpane.setAlignment(Pos.CENTER);
		gridpane.setHgap(10);
		gridpane.setVgap(10);
		gridpane.setPadding(new Insets(5, 5, 5, 5));
		
		
		gridpane.add(name , 0 ,0);
		gridpane.add(address , 0 ,1 );
		gridpane.add(telephone, 0, 2);
		gridpane.add(phone, 0, 3);
		gridpane.add(id2, 0, 4);
		gridpane.add(remark, 0, 5);
		// textfield add 
		gridpane.add(name_field , 1 ,0);
		gridpane.add(address_field , 1 ,1 );
		gridpane.add(telephone_field, 1, 2);
		gridpane.add(phone_field, 1, 3);
		gridpane.add(id2_field, 1, 4);
		gridpane.add(remark_field, 1, 5);
		// button add 
		gridpane.add(hbox, 1, 6);
		
		// create new stage 
		Scene scene = new Scene(gridpane);
		
		Stage stage = new Stage();
		
		stage.setWidth(300);
		stage.setHeight(350);
		stage.setScene(scene);
		stage.show();
 
		correct.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				
			
				user_info user = new user_info(); 
				
				user.setname(name_field.getText());

				user.setaddress(address_field.getText());
				
				user.settelephone(telephone_field.getText());

				user.setphone_number(phone_field.getText());
				
				user.setid_2nd(id2_field.getText());
				
				user.setremark(remark_field.getText());
				
				DB_Controller.user_info_insert(user);
				ObservableList<Data_Content> users = DB_Controller.select(name_field.getText() , year_text.getText());
				table_view.getItems().clear();
				table_view.getItems().addAll(users);
				Set_Userinfo_UI(name_field.getText());
				user_name.setText(name_field.getText());
				
				search_combox.getItems().clear();
				list = db_controller.search_combox_select();
				
				stage.close();
				
			}
			
			
		});
		
		cancel.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				stage.close();
			}
			
			
		});
    	
    }
    
    
    void no_pay_list() throws SQLException{
    	Fast_view.getItems().clear();
    	Set<String> set = new HashSet<String>();
    	Accordion accordion = new Accordion();
    
    	set = DB_Controller.no_pay_list_search();

    	Iterator iterator = set.iterator();
    	
    	TitledPane[] panel = new TitledPane[set.size()];
    	TitledPane Fast_pane ;
    	ListView[] view = new ListView[set.size()];
    	
    	
    	
    	
    	int count = 0 ; 
    	GridPane gridpane  = new GridPane();
    	while(iterator.hasNext()) {
    		
    		Object obj = iterator.next();
    		gridpane = new GridPane();
    		Fast_view.getItems().add(obj.toString());
    		
    		ArrayList<No_Pay_Data> data = DB_Controller.no_pay_data(obj.toString(),"0","0");
    		
    		view[count] = new ListView<String>();
    		
    		
    		//view[count].getItems().add("日期\t\t\t內容\t\t\t\t\t應收取");
    		int total = 0 ; 
    		
    		for(int i = 0 ; i < data.size() ; i++) {
    			No_Pay_Data temp_data = data.get(i);
    			//String String_list = temp_data.Get_day()+"\t\t"+temp_data.Get_content()+"\t\t"+temp_data.Get_pay();
    			
    			
    			if(temp_data.Get_content() == null || temp_data.Get_pay()==null) {
    				continue ;
    			}
    			
    	    	
    			gridpane.setAlignment(Pos.CENTER);
    			gridpane.setHgap(10);
    			gridpane.setVgap(10);
    			gridpane.setPadding(new Insets(5, 5, 5, 5));

    			Label date = new Label(temp_data.Get_day()) ;
    			Label content = new Label(temp_data.Get_content());
    			Label pay = new Label(temp_data.Get_pay());
    			gridpane.add(date, 0,  i);
    			gridpane.add(content, 1, i);
    			gridpane.add(pay, 2, i);
    			
    			total += Integer.parseInt(temp_data.Get_pay());
    			//String String_list ;
    			/*
    			
    			if(temp_data.Get_content().length() < 8) {
    				String_list = temp_data.Get_day()+"\t\t"+temp_data.Get_content()+"\t\t\t\t\t"+temp_data.Get_pay();
    			}else if(temp_data.Get_content().length() < 16) {
    				String_list = temp_data.Get_day()+"\t\t"+temp_data.Get_content()+"\t\t\t"+temp_data.Get_pay();
    			}
    			else {
    				String_list = temp_data.Get_day()+"\t\t"+temp_data.Get_content()+"\t"+temp_data.Get_pay();
    			}*/
    			
    			//view[count].getItems().add(String_list);
    		}
    		Label str = new Label("總額 : ");
			Label total_label = new Label(Integer.toString(total));
			gridpane.add(str, 0,  data.size());
			gridpane.add(total_label, 1,data.size());
			
    		ScrollPane scroll=new ScrollPane();
    		
        	//scroll.setOrientation(Orientation.VERTICAL);
      
        	scroll.setPrefHeight(500);
        	scroll.setPrefWidth(400);
        	scroll.setContent(gridpane);
    		
    		
    		panel[count] = new TitledPane(obj.toString() , scroll);
    	
    		count ++ ;
    	}
    	
    	
    	Fast_pane = new TitledPane("快速收尋" , Fast_view);
    	//Fast_pane.lookup(".title").setStyle("-fx-background-color: #bbeeff;");
    	Button Advanced_Button = new Button("查詢按鈕");
    	Advanced_Button.setMaxWidth(300);
    	Advanced_Button.setMaxHeight(150);
    	Advanced_Button.setPadding(new Insets(5, 5, 5, 5));
    	Advanced_Button.setStyle("-fx-background-color:linear-gradient(#768aa5 0%, #849cbb 5%, #5877a2 50%, #486a9a 51%, #4a6c9b 100%) ; -fx-text-fill:white;");
    	Advanced_Button.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				Advanced_Button_Input();
			}
		});
    	
    	
    	TitledPane Advanced_Pane = new TitledPane("進階查詢" , Advanced_Button);
    	ScrollPane scroll=new ScrollPane();
    		
    	//scroll.setOrientation(Orientation.VERTICAL);
    	
    	accordion.getPanes().add(Fast_pane);
    	accordion.getPanes().add(Advanced_Pane);
    	accordion.getPanes().addAll(panel);
    	accordion.setPrefWidth(500);
    	scroll.setPrefHeight(600);
    	scroll.setPrefWidth(500);
    	scroll.setContent(accordion);
    	
    	VBox vbox = new VBox(2);
    	vbox.getChildren().addAll(scroll);
    	vbox.setPadding(new Insets(2));

    	no_pay_search_stage = new Stage();
    	
    	String css = this.getClass().getResource("application.css").toExternalForm();
    	Scene scene = new Scene(vbox);
		scene.getStylesheets().add(css);
		no_pay_search_stage.setWidth(500);
		no_pay_search_stage.setHeight(600);
		no_pay_search_stage.setScene(scene);
		no_pay_search_stage.show();
    }
    
    
    public void Advanced_Button_Input() {
    	
    	
    	Label year_label = new Label("年度 :"+year_text.getText());
    	Label name_label = new Label("客戶 :"+user_name.getText());
    	
    	Label month_label = new Label("月份");
    	TextField Month = new TextField();
    	Button correct = new Button("確認");
    	Button cancel = new Button("取消");
    	Month.setPrefColumnCount(15);
    	Month.setAlignment(Pos.CENTER);
    	Month.selectAll();
    	
    	HBox hbox = new HBox();
    	//hbox.setMargin(hbox, new Insets(0,0,0,5));
    	hbox.setPrefWidth(100);
    	hbox.setPrefHeight(20);
    	hbox.setSpacing(10);
    	correct.setMaxWidth(hbox.getPrefWidth());
    	correct.setMaxHeight(hbox.getPrefHeight());
    	
    	cancel.setMaxWidth(hbox.getPrefWidth());
    	cancel.setMaxHeight(hbox.getPrefHeight());
    	//cancel.setPadding(new Insets(0,0,0,10));
    	hbox.getChildren().addAll(correct , cancel);
    	hbox.setAlignment(Pos.CENTER_RIGHT);
    	
    	GridPane gridpane = new GridPane();
		gridpane.setAlignment(Pos.CENTER);
		gridpane.setHgap(10);
		gridpane.setVgap(10);
		gridpane.setPadding(new Insets(5, 5, 5, 5));
		
		gridpane.add(year_label, 0, 0);
		gridpane.add(name_label, 0, 1);
		gridpane.add(month_label , 0 ,2);
    	
		// textfield add 

		
		gridpane.add( Month, 1 ,2);
		gridpane.add(hbox, 1, 3);
		
		correct.setOnAction(new EventHandler<ActionEvent>() {


			@Override
			public void handle(ActionEvent arg0) {
				Get_the_Advanced_Data(Month.getText());
			}
		});
		
		Stage stage = new Stage();
		cancel.setOnAction(new EventHandler<ActionEvent>() {


			@Override
			public void handle(ActionEvent arg0) {
			
				stage.close();
			}
		});

		
    	
    	String css = this.getClass().getResource("application.css").toExternalForm();
    	Scene scene = new Scene(gridpane);
    	scene.getStylesheets().add(css);
    	stage.setWidth(350);
    	stage.setHeight(300);
    	stage.setScene(scene);
    	stage.show();
    }
    
    public void Get_the_Advanced_Data(String month) {
    	
    	int Total_Pay = 0 ;
    	GridPane gridpane = new GridPane();
		gridpane.setAlignment(Pos.CENTER);
		gridpane.setHgap(10);
		gridpane.setVgap(10);
    	
		
		ArrayList<No_Pay_Data> data = DB_Controller.no_pay_data(user_name.getText(), month,year_text.getText());
		
		for(int i = 0 ; i < data.size() ; i++) {
			//temp_data.Get_day()+"\t\t"+temp_data.Get_content()+"\t\t\t\t\t"+temp_data.Get_pay();
			
			No_Pay_Data temp = data.get(i);
			Label date = new Label(temp.Get_day()) ;
			Label content = new Label(temp.Get_content());
			Label pay = new Label(temp.Get_pay());
			gridpane.add(date, 0,  i);
			gridpane.add(content, 1, i);
			gridpane.add(pay, 2, i);
			Total_Pay += Integer.parseInt(temp.Get_pay());
			
		}
		Label Total = new Label("總額");
		Label total_pay = new Label(Integer.toString(Total_Pay));
		gridpane.add(Total, 1,  data.size());
		gridpane.add(total_pay, 2,  data.size());
		
		ScrollPane scroll=new ScrollPane();
		
    	//scroll.setOrientation(Orientation.VERTICAL);
  
    	scroll.setPrefHeight(600);
    	scroll.setPrefWidth(500);
    	scroll.setContent(gridpane);
		
		
		Stage stage = new Stage();
    	String css = this.getClass().getResource("application.css").toExternalForm();
    	Scene scene = new Scene(scroll);
    	scene.getStylesheets().add(css);
    	stage.setWidth(500);
    	stage.setHeight(600);
    	stage.setScene(scene);
    	stage.show();
    	
    }
    
	public void choose_printer_file(String month ) {
		Button correct = new Button("確認");
		Button cancel = new Button("取消");
		ArrayList<No_Pay_Data> data = DB_Controller.no_pay_data(user_name.getText(),"0","0");
		
		CheckBox[]  checkbox = new CheckBox[data.size()];
		
    	GridPane gridpane = new GridPane();
		gridpane.setAlignment(Pos.CENTER);
		gridpane.setHgap(10);
		gridpane.setVgap(10);
		
		for(int i = 0 ; i < data.size(); i++) {
			No_Pay_Data temp = data.get(i);
			Label date = new Label(temp.Get_day()) ;
			Label content = new Label(temp.Get_content());
			Label pay = new Label(temp.Get_pay());
			checkbox[i] = new CheckBox();
			checkbox[i].setSelected(true);
			gridpane.add(checkbox[i],0,i);
			gridpane.add(date, 1,  i);
			gridpane.add(content, 2, i);
			gridpane.add(pay, 3, i);
		}
		HBox hbox = new HBox();
    	//hbox.setMargin(hbox, new Insets(0,0,0,5));
    	hbox.setPrefWidth(100);
    	hbox.setPrefHeight(20);
    	hbox.setSpacing(10);
    	correct.setMaxWidth(hbox.getPrefWidth());
    	correct.setMaxHeight(hbox.getPrefHeight());
    	
    	cancel.setMaxWidth(hbox.getPrefWidth());
    	cancel.setMaxHeight(hbox.getPrefHeight());
    	//cancel.setPadding(new Insets(0,0,0,10));
    	hbox.getChildren().addAll(correct , cancel);
    	hbox.setAlignment(Pos.CENTER_RIGHT);
    	
    	gridpane.add(hbox,3,data.size());
		
		ScrollPane scroll=new ScrollPane();
		scroll.setPrefHeight(600);
    	scroll.setPrefWidth(500);
    	scroll.setContent(gridpane);
		
    	Stage stage = new Stage();
    	correct.setOnAction(new EventHandler<ActionEvent>() {


			@Override
			public void handle(ActionEvent arg0) {
				ArrayList<No_Pay_Data> temp_data = new ArrayList<>();
				
				for(int i = 0 ; i < data.size(); i++) {
					if(checkbox[i].isSelected()) {
						
						temp_data.add(data.get(i));
						
					}
				}
				
				
				Excel_File excel = new Excel_File();
				try {
					excel.init_File();
					excel.Write_Into_File(user_name.getText() , month , address_txt.getText(), phone_number_txt.getText(),  temp_data);
					stage.close();
				
				} catch (Exception e) {
					final Alert alert = new Alert(AlertType.CONFIRMATION); // 實體化Alert對話框物件，並直接在建構子設定對話框的訊息類型
					alert.setTitle("錯誤"); //設定對話框視窗的標題列文字
					alert.setHeaderText("內容: 檔案請先關閉再開啟 "+e.toString()); //設定對話框視窗裡的標頭文字。若設為空字串，則表示無標頭
					alert.showAndWait();
				}
				
			}
		});
    	
    	cancel.setOnAction(new EventHandler<ActionEvent>() {


			@Override
			public void handle(ActionEvent arg0) {
				
				stage.close();
			}
		});
    	
    	
		
    	String css = this.getClass().getResource("application.css").toExternalForm();
    	Scene scene = new Scene(scroll);
    	scene.getStylesheets().add(css);
    	stage.setWidth(500);
    	stage.setHeight(600);
    	stage.setScene(scene);
    	stage.show();
		
		
	}
    
    public void init_listview() {
    	comsumer_listview.getItems().clear();
    	
    	for(int i = 0 ; i < keyword_list.size() ; i++) {
    		comsumer_listview.getItems().add(keyword_list.get(i));
    	}
    	
    	
    	comsumer_listview.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				// TODO Auto-generated method stub
				String name = comsumer_listview.getSelectionModel().getSelectedItem();
				ObservableList<Data_Content> user = DB_Controller.select(name , year_text.getText());
				table_view.getItems().clear();
				table_view.getItems().addAll(user);
				Set_Userinfo_UI(name);
				user_name.setText(name);
				total_prices.setText(Integer.toString(DB_Controller.no_pay_data_total_price(name ,year_text.getText() )));
			}
    		
    	});
    	
    	
    	
    }
    
    
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        
        int dateYear = calendar.get(calendar.YEAR);
        dateYear -= 1911 ;
		year_text.setText(Integer.toString(dateYear));
		
		db_controller = new DB_Controller();
		
		try {
			db_controller.init_Connection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		Set_Table() ;
		list = db_controller.search_combox_select();
		
		for(int i = 0 ; i < list.size() ; i++) {
			search_combox.getItems().add(list.get(i));
			
		}

		
		search_combox.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int selectedIndex = search_combox.getSelectionModel().getSelectedIndex();
			    String selectedItem = search_combox.getSelectionModel().getSelectedItem();
			    
			    if(selectedIndex < 0  ) {
				    if((selectedItem != null)) {
				    	if(!selectedItem.contains(" ")) {
				    		
				    		List_search(selectedItem);
				    		init_listview();
				    	}
				    }
			    }
			    // not found 
			    if(selectedIndex < 0) {
			    	return ; 
			    }
			    
			    
			    
			  

			    user_name.setText(selectedItem);
			    init_table(selectedItem);
			    Set_Userinfo_UI(selectedItem);
			
			}
			
		});
		
		
		
		things_search.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				
				int selectedIndex = things_search.getSelectionModel().getSelectedIndex();
			    String selectedItem = things_search.getSelectionModel().getSelectedItem();
			    
			    /*
			    if(selectedIndex < 0  ) {
				    if((selectedItem != null)) {
				    	if(!selectedItem.contains(" ")) {
				    		
				    		List_search(selectedItem);
				    		init_listview();
				    	}
				    }
			    }
			    // not found 
			    if(selectedIndex < 0) {
			    	return ; 
			    }
				*/
			    
			    
			    if(user_name.getText() == null) {
			    	final Alert alert = new Alert(AlertType.CONFIRMATION); // 實體化Alert對話框物件，並直接在建構子設定對話框的訊息類型
					alert.setTitle("錯誤"); //設定對話框視窗的標題列文字
					alert.setHeaderText("內容: 請先選擇客戶"); //設定對話框視窗裡的標頭文字。若設為空字串，則表示無標頭
					alert.showAndWait();
			    }else {
			    	init_things_search_table(user_name.getText(),selectedItem);
			    }
			    
			    
				
				
			}
			
			
			
			
			
		});
			
		
		
		
		
		
		// titlepane initial 
		
		// modify_button 
		modify_list.setStyle("-fx-background-color:linear-gradient(#768aa5 0%, #849cbb 5%, #5877a2 50%, #486a9a 51%, #4a6c9b 100%) ; -fx-text-fill:white;");
		modify_list.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				
				if(user_name.getText().length() == 0) {
				    final Alert alert = new Alert(AlertType.INFORMATION); // 實體化Alert對話框物件，並直接在建構子設定對話框的訊息類型
				    alert.setTitle("錯誤"); //設定對話框視窗的標題列文字
				    alert.setHeaderText("請先選擇客戶"); //設定對話框視窗裡的標頭文字。若設為空字串，則表示無標頭
				   // alert.setContentText("請先選擇客戶"); //設定對話框的訊息文字
				    alert.showAndWait();
				    return ; 
				}
				
				modify_window();
			}
			
			
			
		});
		
		// add_new_list button 
		
		add_new_list.setStyle("-fx-background-color:linear-gradient(#768aa5 0%, #849cbb 5%, #5877a2 50%, #486a9a 51%, #4a6c9b 100%) ; -fx-text-fill:white;");
		add_new_list.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				add_new_list();
			}
			
			
		});
		no_pay_button.setStyle("-fx-background-color:linear-gradient(#768aa5 0%, #849cbb 5%, #5877a2 50%, #486a9a 51%, #4a6c9b 100%) ; -fx-text-fill:white;");
		//no_pay_button.setStyle("");
		no_pay_button.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				Fast_view.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

					@Override
					public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
						// TODO Auto-generated method stub
						String name = Fast_view.getSelectionModel().getSelectedItem();
						ObservableList<Data_Content> user = DB_Controller.select(name , year_text.getText());
						table_view.getItems().clear();
						table_view.getItems().addAll(user);
						total_prices.setText(Integer.toString(DB_Controller.no_pay_data_total_price(name ,year_text.getText() )));
						Set_Userinfo_UI(name);
						user_name.setText(name);
					}
		    		
		    	});
				
				try {
					no_pay_list();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		
		
		
		
		Compara_button.setStyle("-fx-background-color:linear-gradient(#768aa5 0%, #849cbb 5%, #5877a2 50%, #486a9a 51%, #4a6c9b 100%) ; -fx-text-fill:white;");
		Compara_button.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				init_table(user_name.getText());
			}
			
		});
		
		
		
		
		modify_year.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				Label year = new Label("年度");
				Button correct = new Button("確認");
		    	Button cancel = new Button("取消");
		    	
		    	
		    	TextField year_field = new TextField();
		    	year_field.setPrefColumnCount(15);
		    	year_field.setAlignment(Pos.CENTER);
		    	year_field.selectAll();
		    	
		    	HBox hbox = new HBox();
		    	hbox.setPrefWidth(100);
		    	hbox.setPrefHeight(20);
		    	hbox.setSpacing(10);
		    	correct.setMaxWidth(hbox.getPrefWidth());
		    	correct.setMaxHeight(hbox.getPrefHeight());
		    	
		    	cancel.setMaxWidth(hbox.getPrefWidth());
		    	cancel.setMaxHeight(hbox.getPrefHeight());
		    	//cancel.setPadding(new Insets(0,0,0,10));
		    	hbox.getChildren().addAll(correct , cancel);
		    	hbox.setAlignment(Pos.CENTER_RIGHT);
		    	// create GridPanel 
		    	GridPane gridpane = new GridPane();
				gridpane.setAlignment(Pos.CENTER);
				gridpane.setHgap(10);
				gridpane.setVgap(10);
				gridpane.setPadding(new Insets(5, 5, 5, 5));
				
				
				gridpane.add(year , 0 ,0);	
				// textfield add 
				gridpane.add(year_field , 1 ,0);
	
				
				// button add 
				gridpane.add(hbox, 1, 1);
				
				// create new stage 
				Scene scene = new Scene(gridpane);
				Stage stage = new Stage();
				stage.setScene(scene);
				
				stage.setWidth(300);
				stage.setHeight(250);
				stage.show();
				
				
				correct.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {
						year_text.setText(year_field.getText());
						stage.close();
						String name = user_name.getText();
						if(name != null && name.length() != 0) {
						
							ObservableList<Data_Content> user = DB_Controller.select(name , year_text.getText());
							table_view.getItems().clear();
							table_view.getItems().addAll(user);
							total_prices.setText(Integer.toString(DB_Controller.no_pay_data_total_price(user_name.getText() ,year_text.getText() )));
						}
					}
					
					
				});
				


				
				cancel.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {
						stage.close();
					}
					
					
				});
				
			}
		
			
			
		});
		
		// printer_Button => To print the xlsx file on printer 
		
		printer_button.setStyle("-fx-background-color:linear-gradient(#768aa5 0%, #849cbb 5%, #5877a2 50%, #486a9a 51%, #4a6c9b 100%) ; -fx-text-fill:white;");
		printer_button.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				
				Label name = new Label("客戶名稱 : "+user_name.getText());
				
				Label month_label = new Label("月份");
		    	TextField Month = new TextField();
		    	Button correct = new Button("確認");
		    	Button cancel = new Button("取消");
		    	Month.setPrefColumnCount(15);
		    	Month.setAlignment(Pos.CENTER);
		    	Month.selectAll();
		    	
		    	HBox hbox = new HBox();
		    	hbox.setPrefWidth(100);
		    	hbox.setPrefHeight(20);
		    	hbox.setSpacing(10);
		    	correct.setMaxWidth(hbox.getPrefWidth());
		    	correct.setMaxHeight(hbox.getPrefHeight());
		    	
		    	cancel.setMaxWidth(hbox.getPrefWidth());
		    	cancel.setMaxHeight(hbox.getPrefHeight());
		    	//cancel.setPadding(new Insets(0,0,0,10));
		    	hbox.getChildren().addAll(correct , cancel);
		    	hbox.setAlignment(Pos.CENTER_RIGHT);
		    	
		    	GridPane gridpane = new GridPane();
				gridpane.setAlignment(Pos.CENTER);
				gridpane.setHgap(10);
				gridpane.setVgap(10);
				gridpane.setPadding(new Insets(5, 5, 5, 5));
				
				gridpane.add(name , 0 ,0);
				gridpane.add(month_label , 0 ,2);
		    	
				// textfield add 
				gridpane.add( Month, 1 ,2);
				gridpane.add(hbox, 1,3);
				
				correct.setOnAction(new EventHandler<ActionEvent>() {


					@Override
					public void handle(ActionEvent arg0) {
						// TODO Auto-generated method stub
						//
						choose_printer_file(Month.getText());
					}
				});
				
		    	Stage stage = new Stage();
		    	
		    	cancel.setOnAction(new EventHandler<ActionEvent>() {


					@Override
					public void handle(ActionEvent arg0) {
						// TODO Auto-generated method stub
						stage.close();
					}
				});
		    	
		    	String css = this.getClass().getResource("application.css").toExternalForm();
		    	Scene scene = new Scene(gridpane);
		    	scene.getStylesheets().add(css);
		    	stage.setWidth(350);
		    	stage.setHeight(300);
		    	stage.setScene(scene);
		    	stage.show();
		    
				
			}
			
			
			
		});
		
		
		
		delete_button.setStyle("-fx-background-color:linear-gradient(#768aa5 0%, #849cbb 5%, #5877a2 50%, #486a9a 51%, #4a6c9b 100%) ; -fx-text-fill:white;");
		delete_button.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				

				
				String id = null ;
				
				try {
					id = table_view.getSelectionModel().getSelectedItem().getId();
				}catch(Exception e) {
					final Alert alert = new Alert(AlertType.CONFIRMATION); // 實體化Alert對話框物件，並直接在建構子設定對話框的訊息類型
					alert.setTitle("錯誤"); //設定對話框視窗的標題列文字
					alert.setHeaderText("內容: 選擇ID再點選刪除"); //設定對話框視窗裡的標頭文字。若設為空字串，
					alert.showAndWait();
				}
						
				
				if(id != null) {
					
					Stage stage = new Stage();
					Data_Content temp_data = null ; 
					try {
						temp_data = DB_Controller.delete_select_id(id);
					} catch (SQLException e) {
						
						final Alert alert = new Alert(AlertType.CONFIRMATION); // 實體化Alert對話框物件，並直接在建構子設定對話框的訊息類型
						alert.setTitle("錯誤"); //設定對話框視窗的標題列文字
						alert.setHeaderText("內容: 找無此ID，請重新點選客戶"); //設定對話框視窗裡的標頭文字。若設為空字串，
						alert.showAndWait();
					}
					stage.close();
					final Alert alert = new Alert(AlertType.CONFIRMATION); // 實體化Alert對話框物件，並直接在建構子設定對話框的訊息類型
					alert.setTitle(temp_data.getName()); //設定對話框視窗的標題列文字
					alert.setHeaderText("內容: "+temp_data.getContent()); //設定對話框視窗裡的標頭文字。若設為空字串，則表示無標頭
					alert.setContentText("確定要刪除"); //設定對話框的訊息文字
					final Optional<ButtonType> opt = alert.showAndWait();
					final ButtonType rtn = opt.get(); //可以直接用「alert.getResult()」來取代
					
					if (rtn == ButtonType.OK) {
						
						DB_Controller.delete(id);
						ObservableList<Data_Content> user = DB_Controller.select(user_name.getText() , year_text.getText());
						table_view.getItems().clear();
						table_view.getItems().addAll(user);
						total_prices.setText(Integer.toString(DB_Controller.no_pay_data_total_price(user_name.getText() ,year_text.getText() )));
	
					} else if(rtn == ButtonType.CANCEL){
						stage.close();
					}
					
				}
				
				
				
				
			}
			
			
			
		});
		

		
		
	}

}
