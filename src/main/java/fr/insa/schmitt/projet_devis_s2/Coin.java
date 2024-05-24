package fr.insa.schmitt.projet_devis_s2;

public class Coin {
    static int lastId = 0;
    protected int idcoin;
    public int idetage;
    protected double x;
    protected double y;
    
    public Coin(int idcoin, double x, double y,int idetage) {
        this.idcoin=++lastId; //Chaque fois qu'une instances est créée le lastId est augmenté (pareil pour les autres objets)
        this.x=x;
        this.y=y;
        this.idetage=idetage;
    }

    public int getIdcoin(){
        return idcoin;
    }
    
    public double getX() {
        return x;
    }
    public void setX(Double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }
    
    public void setY(Double y) {
        this.y = y;
    }
    
    public int getIdetage() {
        return idetage;
    }

    public static int getLastIdcoin() {
        return lastId;
    }
    
}