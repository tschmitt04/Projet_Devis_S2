/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.schmitt.projet_devis_s2;

/**
 *
 * @author Elève
 */
public class Mur {
    
    private static int lastId = 0;
    private final int idmur;
    public static int idetage;
    private final Coin depart;
    private final Coin arrivee;
    public Revetement revetement;
    public Ouverture ouverture;
    
    public Mur(int idmur, Coin depart, Coin arrivee,int idetage,Revetement revetement) {
        this.idmur=++lastId;
        this.depart = depart;
        this.arrivee = arrivee;
        this.idetage = idetage;
        this.revetement=revetement;
    }
   
    public Revetement getRevetement(){
        return revetement;
    }
    public void setRevetement(Revetement revetement){
        this.revetement=revetement;
    }
    
     public int getidMur(){
        return idmur;
    }
    
    public Coin getDepart() {
        return depart;
    }
    
    public Coin getArrivee() {
        return arrivee;
    }
    
    public int getIdetage() {
        return idetage;
    }

    public static void setEtage(int idetage) {
        Mur.idetage = idetage;
    }

    
    public static int getLastIdmur() {
        return lastId;
    }
    
    public static double getLargeur(Mur mur){
        double largeur ; 
        if((mur.getArrivee().getX()-mur.getDepart().getX())!=0){
            largeur=(mur.getArrivee().getX()-mur.getDepart().getX());
        }else{
            largeur=(mur.getArrivee().getY()-mur.getDepart().getY());
        }
        return largeur;
    }
    
    //Application de l'échelle pour calculer la surface d'un mur
    public double getSurface(Mur mur){
        double prix;
        if((mur.getArrivee().getX()-mur.getDepart().getX())==0){
            prix = (Math.abs(mur.getArrivee().getY()-mur.getDepart().getY()))*2.5*Devis.echellelargeur;
        }else{
            prix= (Math.abs(mur.getArrivee().getX()-mur.getDepart().getX()))*2.5*Devis.echellelongueur;
        }
        return (prix);
    }
    
}