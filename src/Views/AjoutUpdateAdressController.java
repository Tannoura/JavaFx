/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Entities.Adress;
import Entities.User;
import MyConnection.MyConnection;
import Service.AdressService;
import Service.UserService;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.activation.DataSource;

/**
 * FXML Controller class
 *
 * @author Koussay
 */
public class AjoutUpdateAdressController implements Initializable {

    @FXML
     TextArea idadress_upd_del;
    TextArea id_user_upd_del;
    @FXML
     TextArea num_bati_upd_dele;
    @FXML
     TextArea code_postal_upd_dele;
    @FXML
     TextArea ville_upd_dele;
    @FXML
     TextArea rue_upd_dele;
    @FXML
     TextArea pays_upd_dele;
    @FXML
     TextArea gover_upd_del;
    @FXML
    private Button updttt;
    @FXML
    private Button delettt;
    @FXML
    private Button backtt;
    @FXML
     ComboBox<Integer> comb_id_user_upd_del;
      Connection mc;
    PreparedStatement ste;
    

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
            comb_id_user_upd_del.setItems(id);
        } catch (SQLException ex) {
            Logger.getLogger(AjoutUpdateAdressController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void modif_add(MouseEvent event) {
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Warning");
            alert.setContentText("Es-tu sûre de modifier?!");
        String value1 = idadress_upd_del.getText(); 
        String iduser = comb_id_user_upd_del.getValue().toString();
        
        String numBatim = num_bati_upd_dele.getText();
        String codeP = code_postal_upd_dele.getText(); 
         String Rue = rue_upd_dele.getText();
        String Ville = ville_upd_dele.getText();
       
        String gover = gover_upd_del.getText();
        String pays = pays_upd_dele.getText();
        
        
          Optional<ButtonType>result =  alert.showAndWait(); 
        
          
          if(result.get() == ButtonType.OK){ 
        
        Adress r= new Adress(Integer.parseInt(value1),Integer.parseInt(iduser),Integer.parseInt(numBatim),Integer.parseInt(codeP),Rue,Ville,gover,pays);
            
         AdressService rc = new AdressService();
         
         rc.modifierAdress(r);
         
            idadress_upd_del.setText(null);
             comb_id_user_upd_del.setValue(null);
             num_bati_upd_dele.setText(null);
             code_postal_upd_dele.setText(null);
             rue_upd_dele.setText(null);
             ville_upd_dele.setText(null);
             gover_upd_del.setText(null);
             pays_upd_dele.setText(null);
             
             
             
          }
    }

    @FXML
    private void delete_add(MouseEvent event) {
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Warning");
            alert.setContentText("Es-tu sûre de supprimer?!");
        String value1 = idadress_upd_del.getText(); 
        String iduser = comb_id_user_upd_del.getValue().toString();
        
        String numBatim = num_bati_upd_dele.getText();
        String codeP = code_postal_upd_dele.getText(); 
         String Rue = rue_upd_dele.getText();
        String Ville = ville_upd_dele.getText();
       
        String gover = gover_upd_del.getText();
        String pays = pays_upd_dele.getText();
        
        
          Optional<ButtonType>result =  alert.showAndWait(); 
        
          
          if(result.get() == ButtonType.OK){ 
        
        Adress r= new Adress(Integer.parseInt(value1),Integer.parseInt(iduser),Integer.parseInt(numBatim),Integer.parseInt(codeP),Rue,Ville,gover,pays);
            
         AdressService rc = new AdressService();
         
         rc.supprimerAdress(r);
         
            idadress_upd_del.setText(null);
             comb_id_user_upd_del.setValue(null);
             num_bati_upd_dele.setText(null);
             code_postal_upd_dele.setText(null);
             rue_upd_dele.setText(null);
             ville_upd_dele.setText(null);
             gover_upd_del.setText(null);
             pays_upd_dele.setText(null);
             
             
             
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
                Logger.getLogger(SessionFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }
    
}
