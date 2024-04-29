/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.schmitt.projet_devis_s2;

/**
 *
 * @author Elève
 */
import java.util.ArrayList;
//creation de la class etage avec association de differente liste pour definir la composition de l'etage
public class Etage {
    private final int idetage;
    public int etape;
    private final ArrayList<Coin> coins;
    private final ArrayList<Mur> murs;
    private final ArrayList<Piece> pieces;
    private final ArrayList<Appartement> appartements;
    private final ArrayList<Sol> sols;
    private final ArrayList<Plafond> plafonds;
    private final ArrayList<Revetement> revetements;
    private final ArrayList<Ouverture> ouvertures;
    static final ArrayList<Etage> etages = new ArrayList<>();

    //A chaque instance d'un étage, une liste de chaque objet est créée
    public Etage(int idetage, int etape) {
        this.idetage = idetage;
        this.etape = etape;
        this.coins = new ArrayList<>();
        this.murs = new ArrayList<>();
        this.pieces = new ArrayList<>();
        this.appartements = new ArrayList<>();
        this.sols = new ArrayList<>();
        this.plafonds = new ArrayList<>();
        this.revetements = new ArrayList<>();
        this.ouvertures = new ArrayList<>();
        etages.add(this);
    }
    
    public static ArrayList<Etage> getEtage(){
        return etages;
    }
    
    public int getIdetage() {
        return idetage;
    }
    
    public int getetape() {
        return etape;
    }
    
    public void setEtape(int etape) {
        this.etape=etape;
    }
    
    public ArrayList<Coin> getCoin() {
        return coins;
    }

    public ArrayList<Mur> getMur() {
        return murs;
    }
    
    public ArrayList<Revetement> getRevetement() {
        return revetements;
    }
    
    public ArrayList<Ouverture> getOuverture() {
        return ouvertures;
    }

    public ArrayList<Piece> getPiece() {
        return pieces;
    }
    public ArrayList<Sol> getSol() {
        return sols;
    }

    public ArrayList<Plafond> getPlafond() {
        return plafonds;
    }
    public ArrayList<Appartement> getAppartement() {
        return appartements;
    }
    
    //Calcul de la surface occupée par toutes les pièces créées afin de voir si l'étage est rempli 
    public static double getSurfacetot(Etage etage){
        double surfacetot=0;
        for (Piece piece : etage.getPiece()){
            surfacetot=surfacetot+Math.abs(Mur.getLargeur(piece.getMurHaut())*Mur.getLargeur(piece.getMurGauche()));
        }
        return(surfacetot);
    }
}
