/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Entities.EmailSender;
import Entities.User;
import Service.UserService;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.mail.MessagingException;

/**
 * FXML Controller class
 *
 * @author Koussay
 */
public class CreationUserController implements Initializable {

    @FXML
    private TextArea nomtxt;
    @FXML
    private TextArea prenomtxt;
    @FXML
    private TextArea emailtxt;
    @FXML
    private TextArea pwdtxt;
    @FXML
    private ComboBox<String> cmb_role;
    @FXML
    private Button log_out_is;
    @FXML
    private Button ajouter_user_in;
    
     public static boolean isValidEmail(String email) {
    String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z]{2,6}$";
    Pattern pattern = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);
    Matcher matcher = pattern.matcher(email);
    return matcher.matches();
}
     
     private String generateRandomCode() {
    Random random = new Random();
    int code = random.nextInt(900000) + 100000; // génère un entier aléatoire entre 100000 et 999999
    return String.valueOf(code);
}

     
    @FXML
    private TextArea emailcode;
     
      private boolean checkEmailExistence(String email) {
    try {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/prestafind", "root", "");
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM user WHERE email='" + email  + "'");
        if (rs.next()) {
            return true;
        }
        conn.close();
    } catch (SQLException ex) {
        System.out.println("Erreur lors de la vérification de l'existence de l'utilisateur : " + ex.getMessage());
    }
    return false;
}
      
       private boolean checkUserExistence(String nom,String prenom) {
   
        try {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/prestafind", "root", "");
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM user WHERE nom='" + nom + "' AND prenom='" + prenom + "'");
        if (rs.next()) {
            return true;
        }
        conn.close();
    } catch (SQLException ex) {
        System.out.println("Erreur lors de la vérification de l'existence de l'utilisateur : " + ex.getMessage());
    }
    return false;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         cmb_role.getItems().addAll("user");
         emailcode.setVisible(false);
    }    

    
         
    

    @FXML
    private void retour_user_in(MouseEvent event) {
          try {
                Parent page1 = FXMLLoader.load(getClass().getResource("SessionFXML.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(CreationUserController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void add_user_in(MouseEvent event) {
         String nom = nomtxt.getText();
    String prenom = prenomtxt.getText();
    String email = emailtxt.getText();
    String pwd = pwdtxt.getText();
    String Role = cmb_role.getSelectionModel().getSelectedItem();


    if (nom.isEmpty() || prenom.isEmpty()|| email.isEmpty()|| pwd.isEmpty()||Role.isEmpty()){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Veuiller Remplir Les champs!!");
        alert.showAndWait();
    }
    else if (isValidEmail(email)==false){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Email Non Valide");
        alert.showAndWait();
    }
    else if (checkEmailExistence(email)==true){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Email Existan");
        alert.showAndWait();
    }
    else if (checkUserExistence(nom,prenom)==true){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Nom et Prenom deja existent");
        alert.showAndWait();
    }
    else {
        try {
            // Affichage de la zone de texte pour entrer le code
            emailcode.setVisible(true);

            // Désactivation du bouton ajouter pour empêcher l'ajout tant que le code n'a pas été vérifié
            ajouter_user_in.setDisable(true);
            String code = generateRandomCode();

            
            //envoie email
            EmailSender.sendEmail(emailtxt.getText(), "LE Code Secret", code);
            
            //apparition du champ
 ajouter_user_in.setDisable(false);
            // Gestion du clic sur le bouton ajouter après saisie du code de vérification
            ajouter_user_in.setOnAction((e) -> {
                if (emailcode.getText().equals(code))
                {
                   
                    User user = new User(nom, prenom, email, pwd, Role);
                    UserService userService = new UserService();
                    userService.ajouterUser(user);

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("L'utilisateur a été ajouté avec succès!");
                    alert.showAndWait();
                    // Réactiver le bouton ajouter
                    
                }
            });
        } catch (MessagingException ex) {
            Logger.getLogger(CreationUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
}
          
    
