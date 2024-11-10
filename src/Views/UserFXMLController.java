/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Entities.Adress;
import Entities.User;
import MyConnection.MyConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Koussay
 */
public class UserFXMLController implements Initializable {

    @FXML
    private TableView<Adress> tableViewAdresse;
    @FXML
    private TableColumn<Adress, Integer> idadress;
    @FXML
    private TableColumn<Adress, Integer> iduser;
    @FXML
    private TableColumn<Adress, Integer> codepostal;
    @FXML
    private TableColumn<Adress, Integer> numbatiment;
    @FXML
    private TableColumn<Adress, String> rue;
    @FXML
    private TableColumn<Adress, String> ville;
    @FXML
    private TableColumn<Adress, String> gouvernorant;
    @FXML
    private TableColumn<Adress, String> pays;
    @FXML
    private Button add_adr_user;
    @FXML
    private Button retour_user;
         ObservableList<Adress>adressList;
           Connection mc;
    PreparedStatement ste;
    @FXML
    private TextArea rechtxt;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        
        afficherAdress();
    }    

        void afficherAdress()
      {
          
          mc=MyConnection.getInstance().getCnx();
          adressList = FXCollections.observableArrayList();
   
        
        try {
            String requete = "SELECT * FROM adress u ";
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs =  st.executeQuery(requete); 
            while(rs.next()){
                Adress r = new Adress();
                r.setIdAdress(rs.getInt("idAdress"));
                r.setIdUser(rs.getInt("idUser"));
                r.setCodepostal(rs.getInt("codepostal"));
                r.setNumbatiment(rs.getInt("numbatiment"));
                r.setRue(rs.getString("rue")); 
                 r.setVille(rs.getString("ville"));
                  r.setGovernement(rs.getString("governement"));
                   r.setPays(rs.getString("pays"));
                adressList.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        idadress.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getIdAdress()));
         iduser.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getIdUser()));
        codepostal.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getCodepostal()));
        numbatiment.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getNumbatiment()));
        rue.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getRue()));
        ville.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getVille()));
        gouvernorant.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getGovernement()));
        pays.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPays()));
        
        tableViewAdresse.setItems(adressList);
       
      search();
      
      }
        
           private void search() {      
        
        FilteredList<Adress>filteredData = new FilteredList<>(adressList, b->true);
        rechtxt.textProperty().addListener((observable, oldValue, newValue)->{
           
            filteredData.setPredicate(Adress->{
               
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }
               
                String lowerCaseFilter = newValue.toLowerCase();
                 
                  if(String.valueOf(Adress.getCodepostal()).indexOf(lowerCaseFilter) != -1){
                    return true;
                }
                  else if(String.valueOf(Adress.getGovernement()).indexOf(lowerCaseFilter) != -1){
                    return true;
                }else{
                return false;
                }
            });          
        });
        SortedList<Adress>sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableViewAdresse.comparatorProperty());
        tableViewAdresse.setItems(sortedData);
    }
    
    
    
    
    
    
    @FXML
    private void tableViewSelect(MouseEvent event) {
              try {
               // Récupérer la scène actuelle
               Scene currentScene = tableViewAdresse.getScene();
               
               
        // Récupérer la ligne sélectionnée dans le TableView
        Adress clicked = tableViewAdresse.getSelectionModel().getSelectedItem();

        // Charger le fichier FXML de la fenêtre UpdateReclamation
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutUpdateAdress.fxml"));
        Parent root = loader.load();

        // Récupérer le contrôleur de la fenêtre UpdateReclamation
        AjoutUpdateAdressController updateController = loader.getController();

        // Mettre à jour les text areas avec les données de la ligne sélectionnée
        updateController.idadress_upd_del.setText(Integer.toString(clicked.getIdAdress()));
        updateController.comb_id_user_upd_del.setValue(clicked.getIdUser());
        
        
         updateController.code_postal_upd_dele.setText(Integer.toString(clicked.getCodepostal()));
        updateController.num_bati_upd_dele.setText(Integer.toString(clicked.getNumbatiment()));
         
         
        updateController.ville_upd_dele.setText(clicked.getVille());
        updateController.rue_upd_dele.setText(clicked.getRue());
        updateController.pays_upd_dele.setText(clicked.getPays());
        updateController.gover_upd_del.setText(String.valueOf(clicked.getGovernement()));
        
      
        

        // Fermer la fenêtre actuelle
        currentScene.getWindow().hide();
        
        

        // Afficher la fenêtre UpdateReclamation
        Stage updateStage = new Stage();
        updateStage.setScene(new Scene(root));
        updateStage.show();

    } catch (IOException ex) {
        ex.printStackTrace();
    }
        
        
    }

    @FXML
    private void ajouter_add_user(MouseEvent event) {
        
          try {
                Parent page1 = FXMLLoader.load(getClass().getResource("AjoutAdresse.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(UserFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void log_out_user(MouseEvent event) {
         try {
                Parent page1 = FXMLLoader.load(getClass().getResource("SessionFXML.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(SessionFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
}
