import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
	
	private Parent createContent(){
		TextField fieldvehicleID = new TextField();
		TextField fielddealerID = new TextField();
		TextField fieldname = new TextField();
		
		Button btnSave = new Button("SAVE");
		btnSave.setOnAction(event -> {
			SaveData data = new SaveData();
			data.vehicleID = Integer.parseInt(fieldvehicleID.getText());
			data.dealerID = Integer.parseInt(fielddealerID.getText());
			data.name = fieldname.getText();
			try {
				ResourceManager.save(data,  "Info.save");
			}
			catch (Exception e) {
				System.out.println("Could not save file: " + e.getMessage());
			}
			
		});
		
		Button btnLoad = new Button("LOAD");
		btnLoad.setOnAction(event -> {
			try {
				SaveData data = new SaveData();
				fieldvehicleID.setText(String.valueOf(data.vehicleID));
				fielddealerID.setText(String.valueOf(data.dealerID));
				fieldname.setText(data.name);
			}
			catch (Exception e) {
				System.out.println("Could not save file: " + e.getMessage());
			}
			
		});
		
		VBox vbox = new VBox(20, fieldvehicleID, fielddealerID, fieldname, btnSave,btnLoad);
		vbox.setAlignment(Pos.CENTER);
		vbox.setPadding(new Insets(50,50,50,50));
		return vbox;
			
		
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Scene scene = new Scene(createContent());
		primaryStage.setTitle("Vehicle/Dealer Data");
		primaryStage.setScene(scene);
		primaryStage.show();
	
		
}
	public static void main(String[] args) {
		launch(args);
	}
}