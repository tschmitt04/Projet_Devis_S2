package fr.insa.schmitt.projet_devis_s2;
public class Revetement {
    
    private static int lastId = 0;
    private double prix ; 
    private final int idRevetement;
    private final int mur;
    private final int sol;
    private final int plafond;
    private String typeRevetement;
    
    public Revetement(int idRevetement, String typeRevetement, int mur, int sol, int plafond, double prix) {
        this.idRevetement = ++lastId;
        this.typeRevetement = typeRevetement;        
        this.mur = mur;
        this.sol = sol;
        this.plafond = plafond;
        this.prix = prix;
        
    }   
    
    public int getIdRevetement() {
        return idRevetement;
    }

    public int getMur() {
        return mur;
    }

    public int getSol() {
        return sol;
    }

    public int getPlafond() {
        return plafond;
    }

    public String getTypeRevetement() {
        return typeRevetement;
    }
    
    public void setTypeRevetement(String typeRevetement){
        this.typeRevetement=typeRevetement;
    }
    
    public static int getLastIdrev() {
        return lastId;
    }
     
    public double getPrix() {
        return prix;
    }
    
    public void setPrix(Double prix){
        this.prix=prix;
    }
}
