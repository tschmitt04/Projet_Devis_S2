package fr.insa.schmitt.projet_devis_s2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class LecteurCatalogueRevetements {
    // Méthode pour charger le catalogue des revêtements depuis le fichier texte
    public static List<List<Revetement>> chargerCatalogue(String nomFichier) {
        List<Revetement> mur_intérieur = new ArrayList<>();
        List<Revetement> mur_extérieur = new ArrayList<>();
        List<Revetement> plafond = new ArrayList<>();
        List<Revetement> sol = new ArrayList<>();
        List<List<Revetement>> catalogue = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(nomFichier))) {
            String ligne;
            while ((ligne = br.readLine()) != null) {
                String[] elements = ligne.split(";");
                int idRevetement = Integer.parseInt(elements[0]);
                String typeRevetement = elements[1];
                int pourmur = Integer.parseInt(elements[2]);
                int poursol = Integer.parseInt(elements[3]);
                int pourplafond = Integer.parseInt(elements[4]);
                double prix = Double.parseDouble(elements[5]);
                Revetement nouveau = new Revetement(idRevetement, typeRevetement, pourmur, poursol, pourplafond, prix);
                
                if (pourmur == 1 ){
                    mur_intérieur.add(nouveau);
                    mur_extérieur.add(nouveau);
                }else if (poursol==1){
                   plafond.add(nouveau);
                }else if(pourplafond == 1){
                    plafond.add(nouveau);
                    
                    
                }
            }
          
        } catch (IOException e) {
        }

        return catalogue;
    }
}
