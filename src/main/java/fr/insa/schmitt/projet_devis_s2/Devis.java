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
import java.util.Optional;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class Devis extends Application {
    static public int nbetage;
    static public double echellelargeur;
    static public double echellelongueur;
    static public String type;
    static public boolean reprendre;
    static public int etageActuel = 0;
    static public int etagemax = 0;
    static public Stage primaryStage;
    static final ArrayList<Scene> scenes = new ArrayList<>(getnbetage());
    static final ArrayList<Bâtiment> batiments = new ArrayList<>();
    static double largeur,hauteur;
    boolean validInput = false;
    
    public static double getechellelargeur(){ 
        return echellelargeur;
    }
    
    public static double getechellelongeur(){ 
        return echellelongueur;
    }
    
    
    @Override
    public void start(Stage primaryStage) {
        Devis.primaryStage=primaryStage;
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        largeur = screenBounds.getWidth();
        hauteur = screenBounds.getHeight();
        
        
        //Affichage de deux boutons, reprendre une sauvegarde effectuée precedemment (si "oui") ou creer un nouveau devis (si "non")
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle("Sauvegarde");
        alert.setHeaderText(null);
        alert.setContentText("Voulez-vous reprendre la sauvegarde précedente ?");
        ButtonType oui = new ButtonType("Oui");
        ButtonType non = new ButtonType("Non");
        alert.getButtonTypes().setAll(oui, non);
        Optional<ButtonType> reponse = alert.showAndWait();
        if (reponse.get() == oui) {
            reprendre = true;
        } else if (reponse.get() == non) {
            reprendre = false;
            }
        
        if ( reprendre == false ) {
            
        //création d'un nouveau devis, avec determination du type et de la surface au sol du batiment
        
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("Définissez les caractéristiques du terrain");
            dialog.setHeaderText(null);  
            
        //affichage des boutons "maison","immeuble" dans un menu déroulant
        
            ComboBox<String> typeBatimentComboBox = new ComboBox<>();
            typeBatimentComboBox.getItems().addAll("Maison", "Immeuble");
            typeBatimentComboBox.setPromptText("Sélectionnez le type de bâtiment");
            
        //affichage de questionnement a l'utilisateur pour rentrer les caracteristiques premières du batîment
        
            TextField largeurField = new TextField();
            largeurField.setPromptText("Largeur du terrain");
            TextField longueurField = new TextField();
            longueurField.setPromptText("Longueur du terrain");
            TextField nbEtagesField = new TextField();
            nbEtagesField.setPromptText("Nombre d'étages");
            
        //affichage des "cases à trou" a remplir par l'utilisateur
        
            GridPane gridPane = new GridPane();
            gridPane.setHgap(10);
            gridPane.setVgap(10);
            gridPane.addRow(0, new Label("Type de bâtiment :"), typeBatimentComboBox);
            gridPane.addRow(1, new Label("Largeur du terrain :"), largeurField);
            gridPane.addRow(2, new Label("Longueur du terrain :"), longueurField);
            gridPane.addRow(3, new Label("Nombre d'étages de votre bâtiment :"), nbEtagesField);
            
        //création du batiment (si"ok")
        
            dialog.getDialogPane().setContent(gridPane);
            ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            ButtonType cancelButton = new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE);
            dialog.getDialogPane().getButtonTypes().addAll(okButton, cancelButton);
            
            //boucle gérant les exceptions (si l'utilisateur ne rentre pas un nombre entier positifs par exemple)
            while (!validInput) {
                dialog.setResultConverter(dialogButton -> {
                    if (dialogButton == okButton) {
                        String largeurText = largeurField.getText();
                        String longueurText = longueurField.getText();
                        String nbEtagesText = nbEtagesField.getText();
                        String selectedType = typeBatimentComboBox.getValue();
                        try {
                            if (selectedType == null) {
                                throw new IllegalArgumentException("Veuillez indiquer le type de bâtiment.");
                            }
                            double largeurValue = Double.parseDouble(largeurText); //transition de chaine de caracteres en valeur double
                            double longueurValue = Double.parseDouble(longueurText);
                            int nbEtagesValue = Integer.parseInt(nbEtagesText);
                        
                            if (largeurValue > 0 && longueurValue > 0 && nbEtagesValue > 0) {
                                echellelargeur = largeurValue / (hauteur-150);  //Application d'un échelle sur la largeur du terrain
                                echellelongueur = longueurValue / ((largeur-50)-largeur*1/4); //d'une longueur
                                nbetage = nbEtagesValue;
                                type = selectedType;
                                validInput = true;
                            } else {
                                System.out.println("Veuillez saisir des valeurs positives.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Veuillez saisir des valeurs numériques valides.");
                        } catch (IllegalArgumentException t) {
                            
                            // Afficher le message d'erreur dans une boîte de dialogue
                            Alert ah = new Alert(Alert.AlertType.ERROR);
                            ah.setTitle("Erreur de saisie");
                            ah.setHeaderText(null);
                            ah.setContentText(t.getMessage());
                            ah.showAndWait();
                        }
                    }
                    return null; 
                });
                dialog.showAndWait();
            }
            //creation du batîment
            Bâtiment batiment = new Bâtiment (Bâtiment.getLastIdbat(),type,nbetage);
            batiments.add(batiment);
            //Création du premier étage
            etageSuivant(largeur,hauteur);
            primaryStage.setTitle("Devis pour votre : "+batiment.gettypeBatiment());
            //Affichage du premier étage (première scène)
            primaryStage.show();
        
        } else {
            //Sinon reprendre la sauvegarde précédante
            Sauvegarde svg = new Sauvegarde();
            svg.lecture("sauvegarde.txt"); //Nom du fichier texte contenant la sauvegarde
        }
        
       
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    //méthode de création d'un etage avec le terrain et la ligne séparant les deux parties de l'interface
    
    public static void creerEtage(int idetage,double largeur, double hauteur) {
        Etage newEtage = new Etage(idetage,1);
        
        Coin ch = new Coin(Coin.getLastIdcoin(),largeur*1/4-30, 0,newEtage.getIdetage());
        Coin cb = new Coin(Coin.getLastIdcoin(),largeur*1/4-30, hauteur,newEtage.getIdetage());
        
        /* Coin ch1 = new Coin(Coin.getLastIdcoin(),largeur*1/4-35, hauteur*1/3,newEtage.getIdetage());
        Coin cb1 = new Coin(Coin.getLastIdcoin(),5, hauteur*1/3,newEtage.getIdetage());
        
        Coin ch2 = new Coin(Coin.getLastIdcoin(),largeur*1/4-35, 150,newEtage.getIdetage());
        Coin cb2 = new Coin(Coin.getLastIdcoin(),5, 150,newEtage.getIdetage());
        
        Coin ch3 = new Coin(Coin.getLastIdcoin(),5, 150,newEtage.getIdetage());
        Coin cb3 = new Coin(Coin.getLastIdcoin(),5, hauteur*1/3,newEtage.getIdetage());
        
        Coin ch4 = new Coin(Coin.getLastIdcoin(),largeur*1/4-35, 150,newEtage.getIdetage());
        Coin cb4 = new Coin(Coin.getLastIdcoin(),largeur*1/4-35, hauteur*1/3,newEtage.getIdetage());*/
        
        Coin coins1 = new Coin(Coin.getLastIdcoin(),largeur*1/4, 50,newEtage.getIdetage());
        Coin coins2 = new Coin(Coin.getLastIdcoin(),largeur-50, 50,newEtage.getIdetage());
        Coin coins3 = new Coin(Coin.getLastIdcoin(),largeur-50, hauteur-100,newEtage.getIdetage());
        Coin coins4 = new Coin(Coin.getLastIdcoin(),largeur*1/4, hauteur-100,newEtage.getIdetage()); 
        
        newEtage.getCoin().add(ch);
        newEtage.getCoin().add(cb);
        
        newEtage.getCoin().add(coins1);
        newEtage.getCoin().add(coins2);
        newEtage.getCoin().add(coins3);
        newEtage.getCoin().add(coins4);
        //creation du terrain de base au RDC
        if (idetage ==0){
            Mur line = new Mur(Mur.getLastIdmur(),ch,cb,newEtage.getIdetage(),new Revetement(Revetement.getLastIdrev()," ",1,0,0,0));
           /* Mur line1 = new Mur(Mur.getLastIdmur(),ch1,cb1,newEtage.getIdetage(),new Revetement(Revetement.getLastIdrev()," ",1,0,0,0));
            Mur line2 = new Mur(Mur.getLastIdmur(),ch2,cb2,newEtage.getIdetage(),new Revetement(Revetement.getLastIdrev()," ",1,0,0,0));
            Mur line3 = new Mur(Mur.getLastIdmur(),ch3,cb3,newEtage.getIdetage(),new Revetement(Revetement.getLastIdrev()," ",1,0,0,0));
            Mur line4 = new Mur(Mur.getLastIdmur(),ch4,cb4,newEtage.getIdetage(),new Revetement(Revetement.getLastIdrev()," ",1,0,0,0));*/
            Mur murs1 = new Mur(Mur.getLastIdmur(),coins1, coins2,newEtage.getIdetage(),new Revetement(Revetement.getLastIdrev()," ",1,0,0,0));
            Mur murs2 = new Mur(Mur.getLastIdmur(),coins2, coins3,newEtage.getIdetage(),new Revetement(Revetement.getLastIdrev()," ",1,0,0,0));
            Mur murs3 = new Mur(Mur.getLastIdmur(),coins3, coins4,newEtage.getIdetage(),new Revetement(Revetement.getLastIdrev()," ",1,0,0,0));
            Mur murs4 = new Mur(Mur.getLastIdmur(),coins4, coins1,newEtage.getIdetage(),new Revetement(Revetement.getLastIdrev()," ",1,0,0,0));
            newEtage.getRevetement().add(line.getRevetement());
          /*  newEtage.getRevetement().add(line1.getRevetement());
            newEtage.getRevetement().add(line2.getRevetement());
            newEtage.getRevetement().add(line3.getRevetement());
            newEtage.getRevetement().add(line4.getRevetement());*/
            newEtage.getRevetement().add(murs1.getRevetement());
            newEtage.getRevetement().add(murs2.getRevetement());
            newEtage.getRevetement().add(murs3.getRevetement());
            newEtage.getRevetement().add(murs4.getRevetement());
            newEtage.getMur().add(line);
            newEtage.getMur().add(murs1);
            newEtage.getMur().add(murs2);
            newEtage.getMur().add(murs3);
            newEtage.getMur().add(murs4);
       /*     newEtage.getMur().add(line1);
            newEtage.getMur().add(line2);
            newEtage.getMur().add(line3);
            newEtage.getMur().add(line4);*/
            newEtage.setEtape(0);
            //Pour les étages suivants, on récupère les carractéristique de l'etage précedent (terrain et ligne et cage d'escalier) pour les créer de nouveau (meme attributs) dans le nouvel etage 

        }else{
            int dernieretage = Etage.getEtage().size()-2;
            //Récupération de tous les objets devant figurer sur les étages suivant tel que la cage d'escalier (première pièce de la liste de pièce de l'étage précédant)
            Piece avant =  Etage.getEtage().get(dernieretage).getPiece().get(0);
            Plafond pla =Etage.getEtage().get(dernieretage).getPiece().get(0).getPlafond();
            Sol sol =Etage.getEtage().get(dernieretage).getPiece().get(0).getSol();
            Mur hautavant = Etage.getEtage().get(dernieretage).getPiece().get(0).getMurHaut();
            Mur basavant = Etage.getEtage().get(dernieretage).getPiece().get(0).getMurBas();
            Mur gaucheavant= Etage.getEtage().get(dernieretage).getPiece().get(0).getMurGauche();
            Mur droitavant = Etage.getEtage().get(dernieretage).getPiece().get(0).getMurDroit();
            Mur lineavant = Etage.getEtage().get(dernieretage).getMur().get(0);
            Mur murs1avant = Etage.getEtage().get(dernieretage).getMur().get(1);
            Mur murs2avant= Etage.getEtage().get(dernieretage).getMur().get(2);
            Mur murs3avant = Etage.getEtage().get(dernieretage).getMur().get(3);
            Mur murs4avant = Etage.getEtage().get(dernieretage).getMur().get(4);
            Coin hdavant = Etage.getEtage().get(dernieretage).getPiece().get(0).getMurDroit().getDepart();
            Coin hgavant = Etage.getEtage().get(dernieretage).getPiece().get(0).getMurHaut().getDepart();
            Coin bdavant = Etage.getEtage().get(dernieretage).getPiece().get(0).getMurBas().getDepart();
            Coin bgavant = Etage.getEtage().get(dernieretage).getPiece().get(0).getMurGauche().getDepart();
            
            //Nouvelle instance pour créer le nouvel étage
            Mur lineterrain = new Mur (Mur.getLastIdmur(),ch,cb,newEtage.getIdetage(),new Revetement(Revetement.getLastIdrev(),lineavant.getRevetement().getTypeRevetement(),1,0,0,lineavant.getRevetement().getPrix()));
            Mur mur1terrain = new Mur (Mur.getLastIdmur(),coins1,coins2,newEtage.getIdetage(),new Revetement(Revetement.getLastIdrev(),murs1avant.getRevetement().getTypeRevetement(),1,0,0,murs1avant.getRevetement().getPrix()));
            Mur mur2terrain = new Mur (Mur.getLastIdmur(),coins2,coins3,newEtage.getIdetage(),new Revetement(Revetement.getLastIdrev(),murs2avant.getRevetement().getTypeRevetement(),1,0,0,murs2avant.getRevetement().getPrix()));
            Mur mur3terrain = new Mur (Mur.getLastIdmur(),coins3,coins4,newEtage.getIdetage(),new Revetement(Revetement.getLastIdrev(),murs3avant.getRevetement().getTypeRevetement(),1,0,0,murs3avant.getRevetement().getPrix()));
            Mur mur4terrain = new Mur (Mur.getLastIdmur(),coins4,coins1,newEtage.getIdetage(),new Revetement(Revetement.getLastIdrev(),murs4avant.getRevetement().getTypeRevetement(),1,0,0,murs4avant.getRevetement().getPrix()));
            newEtage.getRevetement().add(lineterrain.getRevetement()); 
            newEtage.getRevetement().add(mur1terrain.getRevetement()); 
            newEtage.getRevetement().add(mur2terrain.getRevetement()); 
            newEtage.getRevetement().add(mur3terrain.getRevetement()); 
            newEtage.getRevetement().add(mur4terrain.getRevetement());
            
            newEtage.getMur().add(lineterrain);
            newEtage.getMur().add(mur1terrain);
            newEtage.getMur().add(mur2terrain);newEtage.getMur().add(mur3terrain);newEtage.getMur().add(mur4terrain);
            
            Plafond newPlafond =new Plafond(Plafond.getLastIdplafond(),new Revetement (Revetement.getLastIdrev(),pla.getRevetement().getTypeRevetement(),pla.getRevetement().getMur(),pla.getRevetement().getSol(),pla.getRevetement().getPlafond(),pla.getRevetement().getPrix()));
            Sol newSol =new Sol(Sol.getLastIdsol(),new Revetement (Revetement.getLastIdrev(),sol.getRevetement().getTypeRevetement(),sol.getRevetement().getMur(),sol.getRevetement().getSol(),sol.getRevetement().getPlafond(),sol.getRevetement().getPrix()));
            Coin hg = new Coin (Coin.getLastIdcoin(),hgavant.getX(),hgavant.getY(),idetage); newEtage.getCoin().add(hg);
            Coin hd = new Coin (Coin.getLastIdcoin(),hdavant.getX(),hdavant.getY(),idetage); newEtage.getCoin().add(hd);
            Coin bd = new Coin (Coin.getLastIdcoin(),bdavant.getX(),bdavant.getY(),idetage); newEtage.getCoin().add(bd);
            Coin bg = new Coin (Coin.getLastIdcoin(),bgavant.getX(),bgavant.getY(),idetage); newEtage.getCoin().add(bg);
            Mur Haut = new Mur (Mur.getLastIdmur(),hg,hd,idetage, new Revetement (Revetement.getLastIdrev(),hautavant.getRevetement().getTypeRevetement(),1,0,0,hautavant.getRevetement().getPrix())); newEtage.getMur().add(Haut);
            Mur Droit = new Mur (Mur.getLastIdmur(),hd,bd,idetage,new Revetement (Revetement.getLastIdrev(),droitavant.getRevetement().getTypeRevetement(),1,0,0,droitavant.getRevetement().getPrix())); newEtage.getMur().add(Droit);
            Mur Bas = new Mur (Mur.getLastIdmur(),bd,bg,idetage,new Revetement (Revetement.getLastIdrev(),basavant.getRevetement().getTypeRevetement(),1,0,0,basavant.getRevetement().getPrix())); newEtage.getMur().add(Bas);
            Mur Gauche = new Mur (Mur.getLastIdmur(),bg,hg,idetage,new Revetement (Revetement.getLastIdrev(),gaucheavant.getRevetement().getTypeRevetement(),1,0,0,gaucheavant.getRevetement().getPrix())); newEtage.getMur().add(Gauche);
            Piece newPiece = new Piece (Piece.getLastIdpiece(),newSol,newPlafond,Haut,Droit,Bas,Gauche,idetage,avant.getUsage(),avant.getAppart());
            newEtage.getPiece().add(newPiece);newEtage.getPlafond().add(newPlafond);newEtage.getSol().add(newSol);
            newEtage.getRevetement().add(newPlafond.getRevetement());newEtage.getRevetement().add(newSol.getRevetement());
            newEtage.getRevetement().add(Haut.getRevetement());newEtage.getRevetement().add(Droit.getRevetement());newEtage.getRevetement().add(Bas.getRevetement());newEtage.getRevetement().add(Gauche.getRevetement());
        }
        //Création d'une nouvelle scène avec une nouvelle interface contenant l'étage qui contient les listes d'objets propre à lui
        Scene scene = new Scene(new Interface(newEtage,largeur, hauteur), largeur, hauteur);
        scenes.add(scene);
    }
    
      
    
    //pouvoir se rendre à un étage supérieur 
    public static void etageSuivant(double largeur, double hauteur) {
        if (etageActuel < nbetage) {// Vérification si on ne dépasse pas le nombre d'étage donné par l'utilisateur
            if(etageActuel == etagemax){//Si on est sur l'étage le plus haut déjà créé on doit créé un nouvel étage
                creerEtage(etageActuel, largeur, hauteur);
                etagemax++;
                primaryStage.setScene(scenes.get(etageActuel));
                etageActuel++;
            } else { //Sinon on actualise juste la scène
                etageActuel++;
                primaryStage.setScene(scenes.get(etageActuel));
            }
        } else {
            Interface.aide.setText("Nombre d'étage dépassé");
        }
    }
    //pouvoir se rendre à un etage precedent avec message d'alerte si on est déjà au rdc
    
    public static void etagePrecedant() {
        if (etageActuel> 0) {
            etageActuel--;
        } else {
            Interface.aide.setText("Vous êtes déjà au rdc");
        }
        primaryStage.setScene(scenes.get(etageActuel));
    }
    
    public static int getnbetage (){ 
        return nbetage;
    }
    
    public static ArrayList<Bâtiment> getBatiment(){ 
        return batiments;
    }
    
    public static ArrayList<Scene> getScene(){ 
        return scenes;
    }
    
    
}
