package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App extends Application  {

    @Override
    public void start(Stage primaryStage) throws Exception{

        JFrame frame = new MainFrame("Contador de Bilhar");
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(550,450);

        frame.setVisible(true);
        frame.setResizable(false);

    }


    public static void main(String[] args) {
        launch(args);
    }

}
