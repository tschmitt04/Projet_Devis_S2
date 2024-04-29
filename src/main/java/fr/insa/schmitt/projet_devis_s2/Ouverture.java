/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.schmitt.projet_devis_s2;

/**
 *
 * @author Elève
 */
public class Ouverture {
    
    private static int lastId = 0;
    private final int idouv ;
    public String typeouverture;
    public double xd;public double xf;
    public double yd;public double yf;
    public int revetement;
    public int idetage;
    
        
    public Ouverture(int idouv, double xd, double yd, double xf, double yf, String typeouverture, int idetage){
        this.idouv=lastId++;
        this.typeouverture=typeouverture;
        this.xf=xf;
        this.yf=yf;
        this.xd=xd;
        this.yd=yd;
        this.idetage = idetage;
    }
    
    public int getIdetage() {
        return idetage;
    }
    
    
    public String getTypeouverture() {
        return typeouverture;
    }
    
    public double getXd() {
        return xd;
    }
    public void setXd(Double xd) {
        this.xd = xd;
    }

    public double getYd() {
        return yd;
    }
    
    public void setYd(Double yd) {
        this.yd = yd;
    }
    
    public double getXf() {
        return xf;
    }
    public void setXf(Double xf) {
        this.xf = xf;
    }

    public double getYf() {
        return yf;
    }
    
    public void setYf(Double yf) {
        this.yf = yf;
    }
    
    public int getidouv() {
        return idouv;
    }

    public static int getLastIdouv() {
        return lastId;
    }   
    
}
