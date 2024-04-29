/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.schmitt.projet_devis_s2;

/**
 *
 * @author Elève
 */
public class Bâtiment {

    private static int lastId = 0;
    public int idBatiment;
    public String typeBatiment;
    public int nbreNiveaux;
    
    public Bâtiment(int idBatiment, String typeBatiment, int nbreNiveaux) {
        this.nbreNiveaux=nbreNiveaux;
        this.typeBatiment=typeBatiment;
        this.idBatiment=++lastId;
    }
    
    public int getidBatiment(){
        return idBatiment;
    }
    
    public String gettypeBatiment(){
        return typeBatiment;
    }
    
    public int getnbreNiveaux(){
        return nbreNiveaux;
    } 
    
    public static int getLastIdbat() {
        return lastId;
    }
    
}
