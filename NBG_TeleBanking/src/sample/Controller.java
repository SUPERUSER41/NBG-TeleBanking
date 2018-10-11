package sample;

import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Controller {
    @FXML
    private  JFXButton login;

    @FXML
    private  JFXButton newAccount;

    @FXML
    private  JFXButton resetPassword;

    @FXML
    private  JFXButton Submint;

    public void Loginwindow(ActionEvent event) throws Exception {
        Stage primaryStages = new Stage();
        primaryStages.setTitle("National Banking Group TeleBanking System.");
        Parent root = FXMLLoader.load(getClass().getResource("account window.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        primaryStages.setScene(scene);
        primaryStages.show();
        //handleCloseButtonAction();
    }

    public void newCusWindow(ActionEvent event) throws Exception {
        Stage primaryStages = new Stage();
        primaryStages.setTitle("National Banking Group TeleBanking System");
        Parent root = FXMLLoader.load(getClass().getResource("new account window.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        primaryStages.setScene(scene);
        primaryStages.show();
     //   handleCloseButtonAction();
    }

    public void newMangeWindow(ActionEvent event) throws Exception {
        Stage primaryStages = new Stage();
        primaryStages.setTitle("National Banking Group TeleBanking System");
        Parent root = FXMLLoader.load(getClass().getResource("new Manager window.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        primaryStages.setScene(scene);
        primaryStages.show();
        //   handleCloseButtonAction();
    }



    public void mainmenu(ActionEvent event) throws Exception {
        Stage primaryStages = new Stage();
        primaryStages.setTitle("National Banking Group TeleBanking System");
        Parent root = FXMLLoader.load(getClass().getResource("customer_dashboard.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        primaryStages.setScene(scene);
        primaryStages.show();
        //   handleCloseButtonAction();
    }





}
