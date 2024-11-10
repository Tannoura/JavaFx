/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Entities.Adress;
import MyConnection.MyConnection;
import Service.AdressService;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Koussay
 */
public class AjoutAdresseController implements Initializable {

 
    @FXML
    private Button backtt;
    private ComboBox<Integer> comb_id_user_upd_del;
    @FXML
    private Button add_adress_ajout;
    
       Connection mc;
    PreparedStatement ste;
    @FXML
    private TextArea num_bati;
    @FXML
    private TextArea code_postal;
    @FXML
    private TextArea ville_upd;
    @FXML
    private TextArea rue_upd;
    @FXML
    private TextArea pays;
    @FXML
    private TextArea gover;
    @FXML
    private ComboBox<Integer> comb_id_user;
    
    public static boolean estCodePostalValide(int codePostal) {
    // Vérifier si le code postal est composé de 4 chiffres
    String codePostalStr = Integer.toString(codePostal);
    if (codePostalStr.length() != 4 || !codePostalStr.matches("\\d+")) {
        return false;
    }
    
    // Vérifier si le code postal commence par un chiffre compris entre 1 et 9
    int premierChiffre = Integer.parseInt(codePostalStr.substring(0, 1));
    if (premierChiffre < 1 || premierChiffre > 9) {
        return false;
    }
    
    // Le code postal est valide
    return true;
}
public static boolean estNumeroBatimentValide(int numBatiment) {
    // Vérifier si le numéro de bâtiment est un nombre entier positif
    if (numBatiment < 0) {
        return false;
    }
    
    // Le numéro de bâtiment est valide
    return true;
}


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mc=MyConnection.getInstance().getCnx();
       try {
           
             String requete = "SELECT idUser FROM user r ";
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs =  st.executeQuery(requete); 
            ObservableList<Integer> id = null;
            List<Integer> list = new ArrayList<>();
            while(rs.next()){
                
             list.add(rs.getInt("idUser"));
                
            }
            id = FXCollections
                    .observableArrayList(list);
            comb_id_user.setItems(id);
        } catch (SQLException ex) {
            Logger.getLogger(AjoutUpdateAdressController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void retour_add(MouseEvent event) {
         try {
                Parent page1 = FXMLLoader.load(getClass().getResource("UserFXML.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(UserFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void adress_add_ajout(MouseEvent event) throws SQLException {
       
       
        Integer iduser = comb_id_user.getSelectionModel().getSelectedItem();
        
        String numBatim = num_bati.getText();
        String codeP = code_postal.getText(); 
         String Rue = rue_upd.getText();
        String Ville = ville_upd.getText();
       
        String govern = gover.getText();
        String payss = pays.getText();
        
        if (iduser==null||numBatim.isEmpty()|| codeP.isEmpty()|| Rue.isEmpty()||Ville.isEmpty() || govern.isEmpty()|| payss.isEmpty()){
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setContentText("Champs Vides"); // controle de saisie
             alert.showAndWait();          
         }
        
        else if(estCodePostalValide(Integer.parseInt(codeP))==false){
         Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setContentText("Code Postal non valide"); // controle de saisie
             alert.showAndWait();      
        }
        else if(estNumeroBatimentValide(Integer.parseInt(numBatim))==false){
         Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setContentText("Code Postal non valide"); // controle de saisie
             alert.showAndWait();      
        }
        
        
        
        
         else {     
        
        
         Adress r=new Adress(iduser,Integer.parseInt(codeP),Integer.parseInt(numBatim),Rue,Ville,govern,payss);
            AdressService rc = new AdressService();
             
       
             rc.ajouterAdress(r);
             
             //notifications
              Notifications.create()
                .title("Adresse ajoutée")
                .text("L'adresse a été ajoutée avec succès.")
                .showInformation();
    }
    
}
    

}
