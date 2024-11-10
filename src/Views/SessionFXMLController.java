/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Koussay
 */
public class SessionFXMLController implements Initializable {

    @FXML
    private Button SignUp;
    @FXML
    private Button Connect;
    @FXML
    private PasswordField pwdUser;
    @FXML
    private TextArea emailUser;
    @FXML
    private Button Close;
    @FXML
    private ComboBox<String> cmbrole;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     //le combo box prend comme valeur user ou admin
        cmbrole.getItems().addAll("user", "admin");
    }    

    @FXML
    private void SignUpUser(MouseEvent event) { 
        
         try {
                Parent page1 = FXMLLoader.load(getClass().getResource("creationUser.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(UserFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
               
    }

    @FXML
    private void ConnectUser(MouseEvent event) {
          String email = emailUser.getText();
    String password = pwdUser.getText();
    String role = cmbrole.getValue();

    try {
        // Établir une connexion à la base de données
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/prestafind", "root", "");

        // Vérifier si l'utilisateur existe dans la base de données
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM user WHERE email = ? AND pwd = ? AND role = ?");
        stmt.setString(1, email);
        stmt.setString(2, password);
        stmt.setString(3, role);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            // Utilisateur trouvé dans la base de données
            if (role.equals("admin")) {
                // Rediriger vers l'interface d'administration
                Parent page1 = FXMLLoader.load(getClass().getResource("AdminFXML.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } else if (role.equals("user")) {
                // Rediriger vers l'interface utilisateur
                Parent page1 = FXMLLoader.load(getClass().getResource("UserFXML.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            }
        } else {
            // Aucun utilisateur correspondant trouvé dans la base de données
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("L'email, le mot de passe ou le rôle est incorrect.");
            alert.showAndWait();
        }
    } catch (SQLException ex) {
        Logger.getLogger(SessionFXMLController.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
        Logger.getLogger(SessionFXMLController.class.getName()).log(Level.SEVERE, null, ex);
    }
 
        
    }

    @FXML
    private void CloseApp(MouseEvent event) {
        Stage stage=(Stage) Close.getScene().getWindow();
        stage.close();
    }
    
}
