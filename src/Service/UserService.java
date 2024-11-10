/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.User;
import Interface.IUserService;
import MyConnection.MyConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Koussay
 */
public class UserService implements IUserService<User>{
    
     @Override
    public void ajouterUser(User u) {
         try {
            String requete= "INSERT INTO user (nom,prenom,email,pwd,role)"
                    + "VALUES (?,?,?,?,?)";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setString(1,u.getNom());
            pst.setString(2, u.getPrenom());
            pst.setString(3, u.getEmail());
            pst.setString(4,u.getPwd());
            pst.setString(5,u.getRole());
            pst.executeUpdate();
            System.out.println("User inserée");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
    }
    
    
    
    
    
    
    
     @Override
    public void supprimerUser(User u) {
        try {
            String requete = "DELETE FROM user where idUser=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setInt(1, u.getIdUser());
            pst.executeUpdate();
            System.out.println("User supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        }
    

    
    
@Override
    public void modifierUser(User u) {
        try {
            String requete = "UPDATE user SET nom=?,prenom=?,email=?,pwd=?,role=?  WHERE idUser=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setString(1, u.getNom());
            pst.setString(2, u.getPrenom());
            pst.setString(3, u.getEmail());
            pst.setString(4,u.getPwd());
            pst.setString(5,u.getRole());
            pst.setInt(6,u.getIdUser());
            
            pst.executeUpdate();
            System.out.println("User modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    
    
    
      @Override
    public List<User> afficherUsers() {
         List<User> UsersList = new ArrayList<>();
        try {
            String requete = "SELECT * FROM user u ";
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs =  st.executeQuery(requete);
            while(rs.next()){
                User u = new User();
                u.setIdUser(rs.getInt("idUser"));
                u.setNom(rs.getString("nom"));
                u.setPrenom(rs.getString("prenom"));
                u.setEmail(rs.getString("email"));
                u.setPwd(rs.getString("pwd"));
                u.setRole(rs.getString("role"));
                System.out.println(u.toString());
                UsersList.add(u);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return UsersList;
    }
    
    
    
    
    
    
    
}
