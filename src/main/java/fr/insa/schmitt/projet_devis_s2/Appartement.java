/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.schmitt.projet_devis_s2;

/**
 *
 * @author Elève
 */
public class Appartement {

    
    public int idAppart;
    public int nbpiece;
    
    
    public Appartement(int idAppart, int nbpiece) {
        this.idAppart=idAppart;
        this.nbpiece=nbpiece;
    }
    
    public int getidAppart() {
        return idAppart;
    }

    public void setidAppart(Integer idAppart) {
        this.idAppart = idAppart;
    } 
    
    public int getNbpiece() {
        return nbpiece;
    }

    public void setNbpiece(Integer nbpiece) {
        this.nbpiece = nbpiece;
    } 
    
}

