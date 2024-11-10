/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Entities.User;
import MyConnection.MyConnection;
import Service.UserService;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.activation.DataSource;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Koussay
 */
public class AdminFXMLController implements Initializable {

    @FXML
    private TableView<User> tableViewUser;
    @FXML
    private TableColumn<User, Integer> iduser_admin;
    @FXML
    private TableColumn<User, String> nom_admin;
    @FXML
    private TableColumn<User, String> prenom_admin;
    @FXML
    private TableColumn<User, String> email_admin;
    @FXML
    private TableColumn<User, String> pwd_admin;
    @FXML
    private TableColumn<User, String> role_admin;
    @FXML
    private TextArea iduser_txt;
    @FXML
    private TextArea nom_txt;
    @FXML
    private TextArea prenom_txt;
    @FXML
    private TextArea email_txt;
    @FXML
    private TextArea pwd_txt;
    @FXML
    private Button update_user_ad;
    @FXML
    private Button delete_user_ad;
    @FXML
    private Button pdf;
    @FXML
    private TextArea role_txt;
    
     ObservableList<User>userList;
    
    Connection mc;
    PreparedStatement ste;
    @FXML
    private Button retour;
    
public static boolean isValidEmail(String email) {
    String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z]{2,6}$";
    Pattern pattern = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);
    Matcher matcher = pattern.matcher(email);
    return matcher.matches();
}
    @FXML
    private Button tribtutton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         afficherUser();
    }    

   void afficherUser()
      {
          mc=MyConnection.getInstance().getCnx();
          userList = FXCollections.observableArrayList();
   
        
        try {
            String requete = "SELECT * FROM user u ";
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs =  st.executeQuery(requete); 
            while(rs.next()){
                User r = new User();
                r.setIdUser(rs.getInt("idUser"));
                r.setNom(rs.getString("nom"));
                r.setPrenom(rs.getString("prenom"));
                r.setEmail(rs.getString("email"));
                r.setPwd(rs.getString("pwd")); 
                 r.setRole(rs.getString("role"));
                userList.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        iduser_admin.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getIdUser()));
        nom_admin.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNom()));
        prenom_admin.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPrenom()));
        email_admin.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEmail()));
        pwd_admin.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPwd()));
        role_admin.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getRole()));
        
        
        tableViewUser.setItems(userList);
       
      
      
      }
    
    
    
    
       void refresh()
      {
           userList.clear();
          mc=MyConnection.getInstance().getCnx();
          userList = FXCollections.observableArrayList();
   
        
        try {
            String requete = "SELECT * FROM user u ";
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs =  st.executeQuery(requete); 
            while(rs.next()){
                User r = new User();
                r.setIdUser(rs.getInt("idUser"));
                r.setNom(rs.getString("nom"));
                r.setPrenom(rs.getString("prenom"));
                r.setEmail(rs.getString("email"));
                r.setPwd(rs.getString("pwd")); 
                 r.setRole(rs.getString("role"));
                userList.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        iduser_admin.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getIdUser()));
        nom_admin.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNom()));
        prenom_admin.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPrenom()));
        email_admin.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEmail()));
        pwd_admin.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPwd()));
        role_admin.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getRole()));
        
        
        tableViewUser.setItems(userList);
       
      
      
      }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @FXML
    private void select_admin(MouseEvent event) {
          User clicked = tableViewUser.getSelectionModel().getSelectedItem();
         iduser_txt.setText(String.valueOf(clicked.getIdUser()));
         nom_txt.setText(String.valueOf(clicked.getNom()));
        prenom_txt.setText(String.valueOf(clicked.getPrenom()));
        email_txt.setText(String.valueOf(clicked.getEmail()));
        pwd_txt.setText(String.valueOf(clicked.getPwd()));
        role_txt.setText(String.valueOf(clicked.getRole()));
        
    }

    @FXML
    private void modifier_user_admin(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Warning");
            alert.setContentText("Es-tu sûre de modifier?!");
        String value1 = iduser_txt.getText(); 
        
        String nom = nom_txt.getText();
        String prenom = prenom_txt.getText(); 
        String email = email_txt.getText();
        String pwd = pwd_txt.getText();
        String role = role_txt.getText();
          Optional<ButtonType>result =  alert.showAndWait(); 
        
          
          if(result.get() == ButtonType.OK){ 
        
        User r= new User(Integer.parseInt(value1),nom,prenom,email,pwd,role);
            
         UserService rc = new UserService();
         
         rc.modifierUser(r);
         refresh();
            nom_txt.setText(null);
             prenom_txt.setText(null);
             email_txt.setText(null);
             pwd_txt.setText(null);
             role_txt.setText(null);
          }
        
        
    }

    @FXML
    private void supprimer_user_admin(MouseEvent event) {
           Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Warning");
            alert.setContentText("Es-tu sûre de supprimer?!");
        String value1 = iduser_txt.getText(); 
        
        String nom = nom_txt.getText();
        String prenom = prenom_txt.getText(); 
        String email = email_txt.getText();
        String pwd = pwd_txt.getText();
        String role = role_txt.getText();
          Optional<ButtonType>result =  alert.showAndWait(); 
        
          
          if(result.get() == ButtonType.OK){ 
        
        User r= new User(Integer.parseInt(value1),nom,prenom,email,pwd,role);
            
         UserService rc = new UserService();
         
         rc.supprimerUser(r);
         refresh();}
            nom_txt.setText(null);
             prenom_txt.setText(null);
             email_txt.setText(null);
             pwd_txt.setText(null);
             role_txt.setText(null);
          
        
    }

    @FXML
    private void addpdf(MouseEvent event) throws SQLException, FileNotFoundException, DocumentException, IOException {
              String sql = "SELECT * from user";
    ste=mc.prepareStatement(sql);
    ResultSet rs=ste.executeQuery();

    Document doc = new Document();
    PdfWriter.getInstance(doc, new FileOutputStream("./ListeDesReclamations.pdf"));

    doc.open();
   
    doc.add(new Paragraph("   "));
    doc.add(new Paragraph(" *************************************** Liste Des Utilisateurs *************************************** "));
    doc.add(new Paragraph("   "));
 doc.add(new Paragraph("   "));
  doc.add(new Paragraph("   "));
   doc.add(new Paragraph("   "));
   
   
   
   
    PdfPTable table = new PdfPTable(3);
    table.setWidthPercentage(50);
    PdfPCell cell;

    cell = new PdfPCell(new Phrase("Nom", FontFactory.getFont("Comic Sans MS", 14)));
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    
    table.addCell(cell);
   
    cell = new PdfPCell(new Phrase("Prenom", FontFactory.getFont("Comic Sans MS", 14)));
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    
    table.addCell(cell);
    
    cell = new PdfPCell(new Phrase("email", FontFactory.getFont("Comic Sans MS", 14)));
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    
    table.addCell(cell);
    
    
    
    while (rs.next()) {

        User e = new User();
      
        e.setNom(rs.getString("nom"));
        e.setPrenom(rs.getString("prenom"));
       e.setEmail(rs.getString("email"));
       
       
      
        cell = new PdfPCell(new Phrase(e.getNom(), FontFactory.getFont("Comic Sans MS", 12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase(e.getPrenom(), FontFactory.getFont("Comic Sans MS", 12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase(e.getEmail(), FontFactory.getFont("Comic Sans MS", 12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        
        
        
    }

    doc.add(table);
    doc.close();
    Desktop.getDesktop().open(new File("./ListeDesReclamations.pdf"));

        
        
    }

    @FXML
    private void logout(MouseEvent event) {
        
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

    @FXML
    private void tri_signal(MouseEvent event) {
        
         mc=MyConnection.getInstance().getCnx();
          userList = FXCollections.observableArrayList();
   
        
        try {
            String requete = "SELECT * FROM user u ORDER BY nom ASC";
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs =  st.executeQuery(requete); 
            while(rs.next()){
                User r = new User();
                r.setIdUser(rs.getInt("idUser"));
                r.setNom(rs.getString("nom"));
                r.setPrenom(rs.getString("prenom"));
                r.setEmail(rs.getString("email"));
                r.setPwd(rs.getString("pwd")); 
                 r.setRole(rs.getString("role"));
                userList.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        iduser_admin.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getIdUser()));
        nom_admin.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNom()));
        prenom_admin.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPrenom()));
        email_admin.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEmail()));
        pwd_admin.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPwd()));
        role_admin.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getRole()));
        
        
        tableViewUser.setItems(userList);
    }
    
}
