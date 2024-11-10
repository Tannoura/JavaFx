/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kenzapi;

import Entities.Adress;
import Entities.User;
import Interface.IAdressService;
import Interface.IUserService;
import MyConnection.MyConnection;
import Service.AdressService;
import Service.UserService;

/**
 *
 * @author Koussay
 */
public class KenzaPI {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       MyConnection mc = MyConnection.getInstance();
        System.out.println(mc.hashCode());
        
        
        User u = new User(1,"zarrouk","louay","louay.zarrouk@esprit.tn","4321","admin");
        Adress a = new Adress(3,1,9080,2,"RueReal","Kelibia","Nabeul","Tunisie");
        
        
        IUserService us = new UserService();
        IAdressService as = new AdressService();
       
      // us.ajouterUser(u);
        as.ajouterAdress(a);
        
        //us.afficherUsers();
        //as.afficherAdress();
        
        
        //us.supprimerUser(u);
        //as.supprimerAdress(a);
        
        
        //us.modifierUser(u);
        //as.modifierAdress(a);
        
        
    }
    
}
