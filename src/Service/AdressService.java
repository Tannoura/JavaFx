/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.Adress;
import Entities.User;
import Interface.IAdressService;
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
public class AdressService implements IAdressService<Adress>{
    
    
       @Override
    public void ajouterAdress(Adress a) {
         try {
            String requete= "INSERT INTO adress (idUser,codepostal,numbatiment,rue,ville,governement,pays)"
                    + "VALUES (?,?,?,?,?,?,?)";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
           
            pst.setInt(1,a.getIdUser());
            pst.setInt(2, a.getCodepostal());
            pst.setInt(3, a.getNumbatiment());
            pst.setString(4,a.getRue());
            pst.setString(5,a.getVille());
            pst.setString(6,a.getGovernement());
            pst.setString(7,a.getPays());
           
            pst.executeUpdate();
            System.out.println("Adress inserée");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
    }
    
    
    
    
    
    
    
     @Override
    public void supprimerAdress(Adress a) {
        try {
            String requete = "DELETE FROM adress where idAdress=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setInt(1, a.getIdAdress());
            pst.executeUpdate();
            System.out.println("Adress supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        }
    

    
    
@Override
    public void modifierAdress(Adress u) {
        try {
            String requete = "UPDATE adress SET idUser=?,codepostal=?,numbatiment=?,rue=?,ville=?,governement=?,pays=?  WHERE idAdress=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            
            pst.setInt(1, u.getIdUser());
            pst.setInt(2, u.getCodepostal());
            pst.setInt(3, u.getNumbatiment());
            pst.setString(4,u.getRue());
            pst.setString(5,u.getVille());
            pst.setString(6, u.getGovernement());
            pst.setString(7, u.getPays());
            pst.setInt(8, u.getIdAdress());
            
            
            pst.executeUpdate();
            System.out.println("Adress modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    
    
    
      @Override
    public List<Adress> afficherAdress() {
         List<Adress> AdressList = new ArrayList<>();
        try {
            String requete = "SELECT * FROM adress u ";
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs =  st.executeQuery(requete);
            while(rs.next()){
                Adress a = new Adress();
                a.setIdAdress(rs.getInt("idAdress"));
                a.setIdUser(rs.getInt("idUser"));
                a.setCodepostal(rs.getInt("codepostal"));
                a.setNumbatiment(rs.getInt("numbatiment"));
                a.setRue(rs.getString("rue"));
                a.setVille(rs.getString("ville"));
                a.setGovernement(rs.getString("governement"));
                a.setPays(rs.getString("pays"));
                System.out.println(a.toString());
                AdressList.add(a);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return AdressList;
    }
    
    
    
    
    
    
    
    
    
}
