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
 * @param <User>
 */
public interface IUserService <User>{
    
    
      public void ajouterUser(User p);
    public void supprimerUser(User p);
    public void modifierUser(User p);
    public List<User> afficherUsers();
    
}
