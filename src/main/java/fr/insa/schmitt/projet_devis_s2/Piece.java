package fr.insa.schmitt.projet_devis_s2;
import java.util.ArrayList;

public class Piece {
    
    public static boolean piece = false;
    private static int lastId = 0;
    private final int idpiece ;
    public int idetage;
    private final Sol sol;
    private final Plafond plafond;
    private Mur murGauche;
    private Mur murDroit;
    private Mur murHaut;
    private Mur murBas;
    public int numeroAppart;
    String usage;
    
    public Piece(int idpiece,Sol sol, Plafond plafond, Mur murHaut, Mur murDroit, Mur murBas, Mur murGauche, int idetage, String usage, int numeroAppart) {
        this.idpiece=++lastId;
        this.sol=sol;
        this.plafond=plafond;
        this.murGauche = murGauche;
        this.murDroit = murDroit;
        this.murHaut = murHaut;
        this.murBas = murBas;
        this.usage=usage;
        this.numeroAppart=numeroAppart;
    }
    
    public Sol getSol(){
        return sol;
    }
    
    public Plafond getPlafond(){
        return plafond;
    }
    
    public int getidpiece(){
        return idpiece;
    }
    
    public int getidetage(){
        return idetage;
    }
    
    public Mur getMurGauche() {
        return murGauche;
    }
    
    public void setmurGauche(Mur murGauche) {
        this.murGauche = murGauche;
    }
    
    public Mur getMurDroit() {
        return murDroit;
    }
    
    public void setmurDroit(Mur murDroit) {
        this.murDroit = murDroit;
    }
    
    public Mur getMurHaut() {
        return murHaut;
    }
    
    public void setmurHaut(Mur murHaut) {
        this.murHaut = murHaut;
    }
    
    public Mur getMurBas() {
        return murBas;
    }
    
    public void setmurBas(Mur murBas) {
        this.murBas = murBas;
    }
    
    public void setUsage(String usage) {
        this.usage = usage;
    }
    
    public String getUsage(){
        return usage;
    }
    
    public void setAppart(int numeroAppart) {
        this.numeroAppart = numeroAppart;
    }
    
    public int getAppart(){
        return numeroAppart;
    }
    
    public static int getLastIdpiece() {
        return lastId;
    }

    public ArrayList<Mur> getMurs() {
        ArrayList<Mur> murs = new ArrayList<>();
        murs.add(murGauche);
        murs.add(murHaut);
        murs.add(murDroit);
        murs.add(murBas);
    return murs;
    }
    public ArrayList<Coin> getCoins(){
        ArrayList<Coin> coins= new ArrayList<>();
        coins.add(murHaut.getDepart());
        coins.add(murDroit.getDepart());
        coins.add(murBas.getDepart());
        coins.add(murGauche.getDepart());
        return coins;
    }
    public double getSurface(Mur Haut, Mur Gauche){
        double prix;
        prix = ((Haut.getArrivee().getX()-Haut.getDepart().getX())*Devis.echellelongueur)*((Gauche.getDepart().getY()-Gauche.getArrivee().getY())*Devis.echellelargeur);
        return (prix);
    }
}