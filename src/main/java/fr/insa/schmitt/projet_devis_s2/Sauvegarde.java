package fr.insa.schmitt.projet_devis_s2;import java.io.BufferedReader;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javafx.scene.Scene;

public class Sauvegarde {
    
    String idetage;
    String txtcoins,idcoin,xcoin,ycoin,etage;
    String txtmurs,idmur,coindepart,coinarrivee;
    String txtpieces,idpiece,murhaut,murbas,murgauche,murdroit,usage,idappart;
    String txtsols,idsol;
    String txtplafonds,idplafond;
    String txtrevetements,idrevetement,typerevetement,prix;
    String txtbat,larg,haut,idbat,typbat,nbretg;
    String txtappart,nbrpiece;
    String txtouv,idouv,xd,yd,xf,yf,typeouv,idetgouv;
    char c;
    int debut,fin;
    String  etapeetage;
    String initiale;

    //partie servant à lire la sauvegarde (plus de détails dans le rapport)
    public void lecture(String chemin){
        try{
            FileReader fr= new FileReader(chemin);
            try (BufferedReader br = new BufferedReader(fr)) {
            //lit ligne par ligne le fichier texte

                initiale = br.readLine();
                Devis.etagemax=Integer.parseInt(initiale);
                initiale = br.readLine();
                Devis.echellelongueur=Double.parseDouble(initiale);
                initiale = br.readLine();
                Devis.echellelargeur=Double.parseDouble(initiale);
                txtbat = br.readLine();
                debut = 0;
                fin = 0;
                c = txtbat.charAt(debut);
                //permet de recuperer les "substring" de chaque attributs
                while (c != '/') {
                    fin = fin + 1;
                    c = txtbat.charAt(fin);
                }
                idbat = txtbat.substring(debut,fin);
                debut = fin + 1;
                c = txtbat.charAt(debut);
                while (c != '/') {
                    fin = fin + 1;
                    c = txtbat.charAt(fin);
                }
                typbat = txtbat.substring(debut,fin);
                debut = fin + 1;
                c = txtbat.charAt(debut);
                while (c != '/') {
                    fin = fin + 1;
                    c = txtbat.charAt(fin);
                }
                nbretg = txtbat.substring(debut,fin);
                debut = fin + 1;
                c = txtbat.charAt(debut);
                while (c != '/') {
                    fin = fin + 1;
                    c = txtbat.charAt(fin);
                }
                larg = txtbat.substring(debut,fin);
                debut = fin + 1;
                c = txtbat.charAt(debut);
                while (c != '/') {
                    fin = fin + 1;
                    c = txtbat.charAt(fin);
                }
                //les "substring" sont ensuites converties dans le format adequat puis utilisées
                haut = txtbat.substring(debut,fin);
                debut = fin + 1;
                Devis.largeur = Double.parseDouble(larg);
                Devis.hauteur = Double.parseDouble(haut);
                Bâtiment bat = new Bâtiment (Integer.parseInt(idbat),typbat,Integer.parseInt(nbretg));
                Devis.nbetage=bat.getnbreNiveaux();
                Devis.batiments.add(bat);
                
                while((idetage=br.readLine())!=null){
                    etapeetage=br.readLine();
                    Etage newEtage = new Etage(Integer.parseInt(idetage),Integer.parseInt(etapeetage));
                    txtcoins = br.readLine();
                    debut = 0;
                    fin = 0;
                    while (txtcoins.charAt(debut) != '@') {
                        c = txtcoins.charAt(debut);
                        while (c != '/') {
                            fin = fin + 1;
                            c = txtcoins.charAt(fin);
                        }
                        idcoin = txtcoins.substring(debut,fin);
                        debut = fin + 1;
                        c = txtcoins.charAt(debut);
                        while (c != '/') {
                            fin = fin + 1;
                            c = txtcoins.charAt(fin);
                        }
                        xcoin = txtcoins.substring(debut,fin);
                        debut = fin + 1;
                        c = txtcoins.charAt(debut);
                        while (c != '/') {
                            fin = fin + 1;
                            c = txtcoins.charAt(fin);
                        }
                        ycoin = txtcoins.substring(debut,fin);
                        debut = fin + 1;
                        c = txtcoins.charAt(debut);
                        while (c != '/') {
                            fin = fin + 1;
                            c = txtcoins.charAt(fin);
                        }
                        etage = txtcoins.substring(debut,fin);
                        debut = fin + 1;
                        Coin newCoin = new Coin(Integer.parseInt(idcoin),Double.parseDouble(xcoin),Double.parseDouble(ycoin),Integer.parseInt(etage));
                        newEtage.getCoin().add(newCoin);
                    }
                    txtrevetements = br.readLine();
                    debut = 0;
                    fin = 0;
                    while (txtrevetements.charAt(debut) != '@') {
                        c = txtrevetements.charAt(debut);
                        while (c != '/') {
                            fin = fin + 1;
                            c = txtrevetements.charAt(fin);
                        }
                        idrevetement = txtrevetements.substring(debut,fin);
                        debut = fin + 1;
                        c = txtrevetements.charAt(debut);
                        while (c != '/') {
                            fin = fin + 1;
                            c = txtrevetements.charAt(fin);
                        }
                        typerevetement = txtrevetements.substring(debut,fin);
                        debut = fin + 1;
                        c = txtrevetements.charAt(debut);
                        while (c != '/') {
                            fin = fin + 1;
                            c = txtrevetements.charAt(fin);
                        }
                        idmur = txtrevetements.substring(debut,fin);
                        debut = fin + 1;
                        c = txtrevetements.charAt(debut);
                        while (c != '/') {
                            fin = fin + 1;
                            c = txtrevetements.charAt(fin);
                        }
                        idsol = txtrevetements.substring(debut,fin);
                        debut = fin + 1;
                        c = txtrevetements.charAt(debut);
                        while (c != '/') {
                            fin = fin + 1;
                            c = txtrevetements.charAt(fin);
                        }
                        idplafond = txtrevetements.substring(debut,fin);
                        debut = fin + 1;
                        c = txtrevetements.charAt(debut);
                        while (c != '/') {
                            fin = fin + 1;
                            c = txtrevetements.charAt(fin);
                        }
                        prix = txtrevetements.substring(debut,fin);
                        debut = fin + 1; 
                        Revetement newrevetement = new Revetement(Integer.parseInt(idrevetement),typerevetement,Integer.parseInt(idmur),Integer.parseInt(idsol),Integer.parseInt(idplafond),Double.parseDouble(prix));
                        newEtage.getRevetement().add(newrevetement);
                    }
                    txtmurs = br.readLine();
                    debut = 0;
                    fin = 0;
                    while (txtmurs.charAt(debut) != '@') {
                        c = txtmurs.charAt(debut);
                        while (c != '/') {
                            fin = fin + 1;
                            c = txtmurs.charAt(fin);
                        }
                        idmur = txtmurs.substring(debut,fin);
                        debut = fin + 1;
                        c = txtmurs.charAt(debut);
                        while (c != '/') {
                            fin = fin + 1;
                            c = txtmurs.charAt(fin);
                        }
                        coindepart = txtmurs.substring(debut,fin);
                        debut = fin + 1;
                        c = txtmurs.charAt(debut);
                        while (c != '/') {
                            fin = fin + 1;
                            c = txtmurs.charAt(fin);
                        }
                        coinarrivee = txtmurs.substring(debut,fin);
                        debut = fin + 1;
                        c = txtmurs.charAt(debut);
                        while (c != '/') {
                            fin = fin + 1;
                            c = txtmurs.charAt(fin);
                        }
                        etage = txtmurs.substring(debut,fin);
                        debut = fin + 1;
                        c = txtmurs.charAt(debut);
                        while (c != '/') {
                            fin = fin + 1;
                            c = txtmurs.charAt(fin);
                        }
                        idrevetement = txtmurs.substring(debut,fin);
                        debut = fin + 1;
                        Mur newmur = new Mur(Integer.parseInt(idmur),getcoinid(Integer.parseInt(coindepart),newEtage),getcoinid(Integer.parseInt(coinarrivee),newEtage),Integer.parseInt(etage),getrevetementid(Integer.parseInt(idrevetement),newEtage));
                        newEtage.getMur().add(newmur);
                    }
                    txtsols = br.readLine();
                    debut = 0;
                    fin = 0;
                    while (txtsols.charAt(debut) != '@') {
                        c = txtsols.charAt(debut);
                        while (c != '/') {
                            fin = fin + 1;
                            c = txtsols.charAt(fin);
                        }
                        idsol = txtsols.substring(debut,fin);
                        debut = fin + 1;
                        c = txtsols.charAt(debut);
                        while (c != '/') {
                            fin = fin + 1;
                            c = txtsols.charAt(fin);
                        }
                        idrevetement = txtsols.substring(debut,fin);
                        debut = fin + 1;
                        Sol newsol = new Sol(Integer.parseInt(idsol),getrevetementid(Integer.parseInt(idrevetement),newEtage));
                        newEtage.getSol().add(newsol);
                    }
                    txtplafonds = br.readLine();
                    debut = 0;
                    fin = 0;
                    while (txtplafonds.charAt(debut) != '@') {
                        c = txtplafonds.charAt(debut);
                        while (c != '/') {
                            fin = fin + 1;
                            c = txtplafonds.charAt(fin);
                        }
                        idplafond = txtplafonds.substring(debut,fin);
                        debut = fin + 1;
                        c = txtplafonds.charAt(debut);
                        while (c != '/') {
                            fin = fin + 1;
                            c = txtplafonds.charAt(fin);
                        }
                        idrevetement = txtplafonds.substring(debut,fin);
                        debut = fin + 1;
                        Plafond newplafond = new Plafond(Integer.parseInt(idplafond),getrevetementid(Integer.parseInt(idrevetement),newEtage));
                        newEtage.getPlafond().add(newplafond);
                    }
                    txtpieces = br.readLine();
                    debut = 0;
                    fin = 0;
                    while (txtpieces.charAt(debut) != '@') {
                        c = txtpieces.charAt(debut);
                        while (c != '/') {
                            fin = fin + 1;
                            c = txtpieces.charAt(fin);
                        }
                        idpiece = txtpieces.substring(debut,fin);
                        debut = fin + 1;
                        c = txtpieces.charAt(debut);
                        while (c != '/') {
                            fin = fin + 1;
                            c = txtpieces.charAt(fin);
                        }
                        idsol = txtpieces.substring(debut,fin);
                        debut = fin + 1;
                        c = txtpieces.charAt(debut);
                        while (c != '/') {
                            fin = fin + 1;
                            c = txtpieces.charAt(fin);
                        }
                        idplafond = txtpieces.substring(debut,fin);
                        debut = fin + 1;
                        c = txtpieces.charAt(debut);
                        while (c != '/') {
                            fin = fin + 1;
                            c = txtpieces.charAt(fin);
                        }
                        murhaut = txtpieces.substring(debut,fin);
                        debut = fin + 1;
                        c = txtpieces.charAt(debut);
                        while (c != '/') {
                            fin = fin + 1;
                            c = txtpieces.charAt(fin);
                        }
                        murbas = txtpieces.substring(debut,fin);
                        debut = fin + 1;
                        c = txtpieces.charAt(debut);
                        while (c != '/') {
                            fin = fin + 1;
                            c = txtpieces.charAt(fin);
                        }
                        murdroit = txtpieces.substring(debut,fin);
                        debut = fin + 1;
                        c = txtpieces.charAt(debut);
                        while (c != '/') {
                            fin = fin + 1;
                            c = txtpieces.charAt(fin);
                        }
                        murgauche = txtpieces.substring(debut,fin);
                        debut = fin + 1;
                        c = txtpieces.charAt(debut);
                        while (c != '/') {
                            fin = fin + 1;
                            c = txtpieces.charAt(fin);
                        }
                        etage = txtpieces.substring(debut,fin);
                        debut = fin + 1;
                        c = txtpieces.charAt(debut);
                        while (c != '/') {
                            fin = fin + 1;
                            c = txtpieces.charAt(fin);
                        }
                        usage = txtpieces.substring(debut,fin);
                        debut = fin + 1;
                        c = txtpieces.charAt(debut);
                        while (c != '/') {
                            fin = fin + 1;
                            c = txtpieces.charAt(fin);
                        }
                        idappart = txtpieces.substring(debut,fin);
                        debut = fin + 1;
                        Piece newpiece = new Piece(Integer.parseInt(idpiece),getsolid(Integer.parseInt(idsol),newEtage),getplafondid(Integer.parseInt(idplafond),newEtage),getmurid(Integer.parseInt(murhaut),newEtage),getmurid(Integer.parseInt(murdroit),newEtage),getmurid(Integer.parseInt(murbas),newEtage),getmurid(Integer.parseInt(murgauche),newEtage),Integer.parseInt(etage),usage,Integer.parseInt(idappart));
                        newEtage.getPiece().add(newpiece);
                    }
                    txtappart = br.readLine();
                    debut = 0;
                    fin = 0;
                    while (txtappart.charAt(debut) != '@') {
                        c = txtappart.charAt(debut);
                        while (c != '/') {
                            fin = fin + 1;
                            c = txtappart.charAt(fin);
                        }
                        idappart = txtappart.substring(debut,fin);
                        debut = fin + 1;     
                        c = txtappart.charAt(debut);
                        while (c != '/') {
                            fin = fin + 1;
                            c = txtappart.charAt(fin);
                        }
                        nbrpiece = txtappart.substring(debut,fin);
                        debut = fin + 1;
                        Appartement newappartement = new Appartement(Integer.parseInt(idappart),Integer.parseInt(nbrpiece));
                        newEtage.getAppartement().add(newappartement);
                    }
                    txtouv = br.readLine();
                    debut = 0;
                    fin = 0;
                    while (txtouv.charAt(debut) != '@') {
                        c = txtouv.charAt(debut);
                        while (c != '/') {
                            fin = fin + 1;
                            c = txtouv.charAt(fin);
                        }
                        idouv = txtouv.substring(debut,fin);
                        debut = fin + 1;
                        c = txtouv.charAt(debut);
                        while (c != '/') {
                            fin = fin + 1;
                            c = txtouv.charAt(fin);
                        }
                        xd = txtouv.substring(debut,fin);
                        debut = fin + 1;
                        c = txtouv.charAt(debut);
                        while (c != '/') {
                            fin = fin + 1;
                            c = txtouv.charAt(fin);
                        }
                        yd = txtouv.substring(debut,fin);
                        debut = fin + 1;
                        c = txtouv.charAt(debut);
                        while (c != '/') {
                            fin = fin + 1;
                            c = txtouv.charAt(fin);
                        }
                        xf = txtouv.substring(debut,fin);
                        debut = fin + 1;
                        c = txtouv.charAt(debut);
                        while (c != '/') {
                            fin = fin + 1;
                            c = txtouv.charAt(fin);
                        }
                        yf = txtouv.substring(debut,fin);
                        debut = fin + 1;
                        c = txtouv.charAt(debut);
                        while (c != '/') {
                            fin = fin + 1;
                            c = txtouv.charAt(fin);
                        }
                        typeouv = txtouv.substring(debut,fin);
                        debut = fin + 1;
                        c = txtouv.charAt(debut);
                        while (c != '/') {
                            fin = fin + 1;
                            c = txtouv.charAt(fin);
                        }
                        idetgouv = txtouv.substring(debut,fin);
                        debut = fin + 1;
                        Ouverture newouverture = new Ouverture(Integer.parseInt(idouv),Double.parseDouble(xd),Double.parseDouble(yd),Double.parseDouble(xf),Double.parseDouble(yf),typeouv,Integer.parseInt(idetgouv));
                        newEtage.getOuverture().add(newouverture);
                    }
                    Devis.getScene().add(new Scene(new Interface(newEtage,Devis.largeur,Devis.hauteur), Devis.largeur, Devis.hauteur));
                }
                Devis.primaryStage.setTitle("Devis pour votre : "+bat.gettypeBatiment());
                Devis.primaryStage.setScene(Devis.scenes.get(Devis.etageActuel));
                Devis.primaryStage.show();
            }
        }catch(IOException e){
            System.out.println("Erreur"+e);
        }
    }
    //partie servant à ecrire la sauvegarde (plus de détails dans le rapport)
    public void ecriture(String chemin){
        try{
            FileWriter fw =new FileWriter (chemin, false);
            try (BufferedWriter bw = new BufferedWriter (fw)) {
                bw.write(Integer.toString(Devis.etagemax));
                bw.write("\n");
                bw.write(Double.toString(Devis.echellelongueur));
                bw.write("\n");
                bw.write(Double.toString(Devis.echellelargeur));
                bw.write("\n");
                bw.write(Integer.toString(Devis.getBatiment().get(0).getidBatiment())+"/");
                bw.write(Devis.getBatiment().get(0).gettypeBatiment()+"/");
                bw.write(Integer.toString(Devis.getBatiment().get(0).getnbreNiveaux())+"/");
                bw.write(Double.toString(Devis.largeur)+"/");
                bw.write(Double.toString(Devis.hauteur)+"/");
                bw.write("@");
                bw.write("\n");
                //On parcourt la liste d'etage
                for (Etage etage : Etage.getEtage()) {
                    bw.write(Integer.toString(etage.getIdetage()));
                    bw.write("\n");
                    bw.write(Integer.toString(etage.getetape()));
                    bw.write("\n");
                    //On parcout la liste de coins dans l'etage et on écrit chaque coordonnée
                    for (Coin coin : etage.getCoin()) {
                        bw.write(Integer.toString(coin.getIdcoin())+"/");
                        bw.write(Double.toString(coin.getX())+"/");
                        bw.write(Double.toString(coin.getY())+"/");
                        bw.write(Integer.toString(coin.getIdetage())+"/");
                    }
                    bw.write("@");
                    bw.write("\n");
                    for (Revetement revetement : etage.getRevetement()) {
                        bw.write(Integer.toString(revetement.getIdRevetement())+"/");
                        bw.write(revetement.getTypeRevetement()+"/");
                        bw.write(Integer.toString(revetement.getMur())+"/");
                        bw.write(Integer.toString(revetement.getSol())+"/");
                        bw.write(Integer.toString(revetement.getPlafond())+"/");
                        bw.write(Double.toString(revetement.getPrix())+"/");
                    }
                    bw.write("@");
                    bw.write("\n");
                    for (Mur mur : etage.getMur()) {
                        bw.write(Integer.toString(mur.getidMur())+"/");
                        bw.write(Integer.toString(mur.getDepart().getIdcoin())+"/");
                        bw.write(Integer.toString(mur.getArrivee().getIdcoin())+"/");
                        bw.write(Integer.toString(mur.getIdetage())+"/");
                        bw.write(Integer.toString(mur.getRevetement().getIdRevetement())+"/");
                    }
                    bw.write("@");
                    bw.write("\n");
                    for (Sol sol : etage.getSol()) {
                        bw.write(Integer.toString(sol.getidsol())+"/");
                        bw.write(Integer.toString(sol.getRevetement().getIdRevetement())+"/");
                    }
                    bw.write("@");
                    bw.write("\n");
                    for (Plafond plafond : etage.getPlafond()) {
                        bw.write(Integer.toString(plafond.getidplafond())+"/");
                        bw.write(Integer.toString(plafond.getRevetement().getIdRevetement())+"/");
                    }
                    bw.write("@");
                    bw.write("\n");
                    for (Piece piece : etage.getPiece()) {
                        bw.write(Integer.toString(piece.getidpiece())+"/");
                        bw.write(Integer.toString(piece.getSol().getidsol())+"/");
                        bw.write(Integer.toString(piece.getPlafond().getidplafond())+"/");
                        bw.write(Integer.toString(piece.getMurHaut().getidMur())+"/");
                        bw.write(Integer.toString(piece.getMurBas().getidMur())+"/");
                        bw.write(Integer.toString(piece.getMurDroit().getidMur())+"/");
                        bw.write(Integer.toString(piece.getMurGauche().getidMur())+"/");
                        bw.write(Integer.toString(piece.getidetage())+"/");
                        bw.write(piece.getUsage()+"/");
                        bw.write(piece.getAppart()+"/");
                    }
                    bw.write("@"); 
                    bw.write("\n");
                    for (Appartement appartement : etage.getAppartement()) {
                        bw.write(Integer.toString(appartement.getidAppart())+"/");
                        bw.write(Integer.toString(appartement.getNbpiece())+"/");
                    }
                    bw.write("@"); 
                    bw.write("\n");
                    for (Ouverture ouverture : etage.getOuverture()) {
                        bw.write(Integer.toString(ouverture.getidouv())+"/");
                        bw.write(Double.toString(ouverture.getXd())+"/");
                        bw.write(Double.toString(ouverture.getYd())+"/");
                        bw.write(Double.toString(ouverture.getXf())+"/");
                        bw.write(Double.toString(ouverture.getYf())+"/");
                        bw.write(ouverture.getTypeouverture()+"/");
                        bw.write(Integer.toString(ouverture.getIdetage())+"/");
                    }
                    bw.write("@"); 
                    bw.write("\n");
                }
                bw.close();
            }
        }catch (IOException err){
            System.out.println("Erreur d'écriture"+err);
        }
    }
    
    //Fonctions servant à recupérer un Objet suivant sont ID et son Etage

    public static Coin getcoinid(int index, Etage etg) {
        for (Coin coin : etg.getCoin()) {
            if (coin.getIdcoin() == index){
                return coin;
            }
        }
        return null;

    }

    public static Mur getmurid(int index, Etage etg) {
        for (Mur mur : etg.getMur()) {
            if (mur.getidMur() == index){
                return mur;
            }
        }
        return null;
    }

    public Sol getsolid(int index, Etage etg) {
        for (Sol sol : etg.getSol()) {
            if (sol.getidsol() == index){
                return sol;
            }
        }
        return null;
    }

    public Plafond getplafondid(int index, Etage etg) {
        for (Plafond plafond : etg.getPlafond()){
            if (plafond.getidplafond() == index){
                return plafond;
            }
        }
        return null;
    }

    public Revetement getrevetementid(int index, Etage etg) {
        for (Revetement revetement : etg.getRevetement()){
            if (revetement.getIdRevetement() == index){
                return revetement;
            }
        }
        return null;
    }
}
