/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Koussay
 */
public class Adress {
    
 private int idAdress;
 private int idUser;
 private int codepostal;
 private int numbatiment;
 private String rue ;
 private String ville;
 private String governement;
 private String pays;

    public Adress() {
    }

    public Adress(int idAdress, int idUser, int codepostal, int numbatiment, String rue, String ville, String governement, String pays) {
        this.idAdress = idAdress;
        this.idUser = idUser;
        this.codepostal = codepostal;
        this.numbatiment = numbatiment;
        this.rue = rue;
        this.ville = ville;
        this.governement = governement;
        this.pays = pays;
    }

    public Adress(int idUser, int codepostal, int numbatiment, String rue, String ville, String governement, String pays) {
        this.idUser = idUser;
        this.codepostal = codepostal;
        this.numbatiment = numbatiment;
        this.rue = rue;
        this.ville = ville;
        this.governement = governement;
        this.pays = pays;
    }

    public int getIdAdress() {
        return idAdress;
    }

    public void setIdAdress(int idAdress) {
        this.idAdress = idAdress;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getCodepostal() {
        return codepostal;
    }

    public void setCodepostal(int codepostal) {
        this.codepostal = codepostal;
    }

    public int getNumbatiment() {
        return numbatiment;
    }

    public void setNumbatiment(int numbatiment) {
        this.numbatiment = numbatiment;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getGovernement() {
        return governement;
    }

    public void setGovernement(String governement) {
        this.governement = governement;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    @Override
    public String toString() {
        return "Adress{" + "idAdress=" + idAdress + ", idUser=" + idUser + ", codepostal=" + codepostal + ", numbatiment=" + numbatiment + ", rue=" + rue + ", ville=" + ville + ", governement=" + governement + ", pays=" + pays + '}';
    }
 
 
 
 
         
    
}
