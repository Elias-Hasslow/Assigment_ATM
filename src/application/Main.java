package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			AnchorPane root = 
					(AnchorPane)FXMLLoader.load(getClass().getResource("Sample.fxml"));

			
//			// Buildning table view and table columns. Tutorial seen on Java Code Junkie https://www.youtube.com/watch?v=vego72w5kPU&ab_channel=JavaCodeJunkie
//			TableView table = new TableView<Person>();
//			
//			TableColumn nameColumn = new TableColumn<Person, String>("Name");
//			nameColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("name"));
//			
//			TableColumn ageColumn = new TableColumn<Person, Integer>("Age");
//			ageColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("age"));
//			
//			TableColumn idColumn = new TableColumn<Person, String>("ID-Number");
//			idColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("identificationNumber"));
//			
//			TableColumn balanceColumn = new TableColumn<Person, String>("Balance");
//			balanceColumn.setCellValueFactory((Callback) new TableColumn<Person, String>("calculateTotalBalance"));
//			
//			TableView extratable = new TableView<BankAccount>();
//			
//			
//			TableColumn accNrColumn = new TableColumn<BankAccount, String>("Account-Number");
//			accNrColumn.setCellValueFactory((Callback) new TableColumn<BankAccount, String>("accountNumber"));
//			
//			table.getColumns().add(nameColumn);
//			table.getColumns().add(ageColumn);
//			table.getColumns().add(idColumn);
//			table.getColumns().add(balanceColumn);
//			table.getColumns().add(accNrColumn);
//			
			
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Assignment 4");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	// This is a test!
	
	public static void main(String[] args) {
		launch(args);
	}
}
