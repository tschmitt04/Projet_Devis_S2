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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

public class Bouton {

    static final ArrayList<Button> boutons = new ArrayList<>();
    public static boolean supprime = false;
    public static boolean rev = false;
    public static boolean piece = false;
    public static boolean ouv = false;
    public static boolean appart = false;
    public static String usage;
    public static String ouverture;
    public static String surfacerev =" ";
    public static String revetement;
    
    public static ArrayList<Button> creationboutons(double largeur, double hauteur) {
        
       /*Création du bouton pièce, au clic deroulement d'autre boutton lié a la nature de la piece,
        puis définition du texte d'aide pour selectionner un coin afin de former la pièce */
       
        Button nouvellepièce = creationbouton("Pièce", 20, 65, () -> {
            usage = Choix("Choisir l'usage de la pièce",
                    "Choisissez l'usage de la pièce :", 
                    "Chambre", 
                    "Salle de bain", 
                    "Cuisine", 
                    "Couloir",
                    "Toilette",
                    "Salon",
                    "Bureau");
            piece = true;
            Interface.premierCoin = null;
            Interface.aide.setText("Veuillez sélectionner un coin");
        });
        boutons.add(nouvellepièce);
        nouvellepièce.setPrefWidth(largeur/10);
        nouvellepièce.setPrefHeight(15);
        
        //Création du boutons "sauvegarde" lié directement à la sauvegarde du devis (classe Sauvegarde
        
        Sauvegarde svg = new Sauvegarde();
        Button sauvegarder = creationbouton("Sauvegarder", largeur-200, hauteur-75, () -> {
            svg.ecriture("sauvegarde.txt");
        });
        boutons.add(sauvegarder);
        sauvegarder.setPrefWidth(largeur/10);
        sauvegarder.setPrefHeight(15);
        
      
        //Création du bouton "ouverture" et affichage une fois toutes les pièces créées, au clic deroulement d'autre boutton lié a la nature des ouvertures*/
        
        Button ouverturebouton = creationbouton("Ouverture", 20, 65, () -> {
            ouverture = Choix("Choisir le type d'ouverture",
                    "Choisissez le type d'ouverture : ", 
                    "Valider les ouvertures",
                    "Porte", 
                    "Fenêtre");
            ouv = true;
        });
        boutons.add(ouverturebouton);
        ouverturebouton.setPrefWidth(largeur/10);
        ouverturebouton.setPrefHeight(15);
        
        //Création du bouton "revêtement", au clic deroulement d'autre boutton lié a type de revêtement et type de surface
        
        Button boutrevetement = creationbouton("Revêtement", 20, 65, () -> {
            surfacerev = Choix("Choisir la surface à revêtir",
                    "Choisir la surface à revêtir :", 
                    "Sol", 
                    "Mur Intérieur", 
                    "Mur Extérieur",
                    "Plafond");
            typerev(surfacerev);
        });
        boutons.add(boutrevetement);
        boutrevetement.setPrefWidth(largeur/10);
        boutrevetement.setPrefHeight(15);

        /*Affichage des boutons d'incrément d'étage (un bouton +, -, pour passer d'un étage à un autre) 
        en bas à droite de l'interface graphqiue de la pièce*/
        
        Button etageplus = creationbouton("+", largeur * 2 / 3 - 45, hauteur - 75, () -> {
            Devis.etageSuivant(largeur, hauteur);
        });
        boutons.add(etageplus);

        Button etagemoins = creationbouton("-", largeur * 2 / 3 - 150, hauteur - 75, () -> {
            Devis.etagePrecedant();
        });
        boutons.add(etagemoins);
        
        return boutons;
    }
    
    static Button creationbouton(String text, double X, double Y, Runnable action) {
        Button bouton = new Button(text);
        bouton.setLayoutX(X);
        bouton.setLayoutY(Y);
        bouton.setOnAction(event -> action.run());
        return bouton;
    }
    
    private static String Choix(String titre, String content, String... options) {
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(content);

        ButtonType[] bouton = new ButtonType[options.length];
        for (int i = 0; i < options.length; i++) {
            bouton[i] = new ButtonType(options[i]);
        }
        alert.getButtonTypes().setAll(bouton);
         Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent()) {
            return result.get().getText();
        }
        return null;
    }

    //Selon la surface choisie différents type de revêtement apparaissent
    public static void typerev(String surface){
        switch (surface){
            case "Sol" -> {
                revetement = Choix ("Choisir le revêtement du sol",
                        "Choisir le revêtement du sol :", 
                        "Carrelage 1",
                        "Carrelage 2",
                        "Carrelage 3",
                        "Parquet",
                        "Marbre",
                        "Moquette",
                        "Vinyle Lino",
                        "Stratifie",
                        "Liège",
                        "Lambris 1");
                rev = true;
            }

            case "Mur Intérieur" -> {
                revetement = Choix ("Choisir le revêtement du mur intérieur",
                        "Choisir le revêtement du mur intérieur :",
                        "Peinture 1",
                        "Peinture 2",
                        "Peinture 3",
                        "Carrelage 1",
                        "Carrelage 2",
                        "Carrelage 3",
                        "Marbre",
                        "Plaquettes de parement",
                        "Lambris 1",
                        "Lambris 2",
                        "Liège",
                        "Crépi",
                        "Papier Peint");
                rev=true;
            }
            case "Mur Extérieur" -> {
                revetement = Choix ("Choisir le revêtement du mur extérieur",
                        "Choisir le revêtement du mur extérieur :",
                        "Lambris 1",
                        "Lambris 2",
                        "Plaquette de parement",
                        "Crépi");
                rev = true;
            }
            case "Plafond" -> {
                revetement = Choix ("Choisir le revêtement du plafond",
                        "Choisir le revêtement du plafond :",
                        "Lambris 1",
                        "Lambris 2",
                        "Peinture 1",
                        "Peinture 2",
                        "Peinture 3");
                rev=true;
            }
        }
    }
}
