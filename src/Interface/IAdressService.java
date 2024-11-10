/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import java.util.List;

/**
 *
 * @author Koussay
 * @param <Adress>
 */
public interface IAdressService <Adress>{
    
    
      public void ajouterAdress(Adress a);
    public void supprimerAdress(Adress a);
    public void modifierAdress(Adress a);
    public List<Adress> afficherAdress();
    
    
}
