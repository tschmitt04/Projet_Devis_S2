/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.schmitt.projet_devis_s2;

/**
 *
 * @author Elève
 */
import static fr.insa.schmitt.projet_devis_s2.Interface.aide;
import static fr.insa.schmitt.projet_devis_s2.Interface.aide4;
import java.text.DecimalFormat;
import java.util.Optional;
import javafx.beans.value.ChangeListener;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundSize;
import java.util.ArrayList;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;











public final class Interface extends Pane implements EventHandler<MouseEvent> {
    
    public static Label aide;
    public static Label aide2;
    public static Label aide3;
    public static Label aide4;
    public static Text text;
    public static Text text1;
    private final Label devis;
    private final Label devisetage;
    public static Image imagegrue;
    public Etage etage;
    public double largeur;
    public double hauteur;
    public static int nbappart;
    public static int choixAppart ;
    public static Coin premierCoin=null; 
    private Coin selectedCoin = null;
    public static ImagePattern imagePattern;
    static final ArrayList<Interface> interfaces = new ArrayList<>(Devis.getnbetage());
    private final ImageView imageView = new ImageView("file:grue.jpg");
    
    //file:legende.png
    
    //declare l'interface pour un etage spécifique
    public Interface(Etage etage, double largeur, double hauteur) {
        this.etage = etage;
        this.largeur=largeur;
        this.hauteur=hauteur;
        Bouton.creationboutons(largeur, hauteur);
        aide=new Label();
        aide2=new Label();
        aide3=new Label();
        aide4=new Label();
        devis=new Label();
        devisetage=new Label();
        text = new Text();
        text1 = new Text("--SCMITT--SQUILLARIO--THOMAS--B7--");
        setOnMouseClicked(this);
        redraw();
        interfaces.add(this);
        

    // Ajoutez les lignes pour définir l'image d'arrière-plan
    
    Image grue = new Image("file:grue.jpg");
        BackgroundImage backgroundImage = new BackgroundImage(grue, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background background = new Background(backgroundImage);
        this.setBackground(background);
        
        
    }
    
    //Méthodes permettant d'actualiser les attributs des différents objets 
    public void updateCoin(int idCoin, double x, double y) {
        for (Coin coin : etage.getCoin()) {
            if (coin.getIdcoin() == idCoin) {
                coin.setX(x);
                coin.setY(y);
                break;
            }
        }
    }
    
    public void updateMur(int idMur, Revetement revetement) {
        for (Mur mur : etage.getMur()) {
            if (mur.getidMur() == idMur) {
                mur.setRevetement(revetement);
                break;
            }
        }
    }
    
    public void updateOuverture(int idouv, double xd, double yd,double xf, double yf) {
        for (Ouverture ouv : etage.getOuverture()) {
            if (ouv.getidouv() == idouv) {
                ouv.setXd(xd);
                ouv.setYd(yd);
                ouv.setXf(xf);
                ouv.setYf(yf);
                break;
            }
        }
    }
    
    public void updatePiece(int idpiece, Mur murHaut, Mur murDroit, Mur murBas, Mur murGauche, String usage, Etage etage) {
        for (Piece piece : etage.getPiece()) {
            if (piece.getidpiece()== idpiece) {
                piece.setmurGauche(murGauche);
                piece.setmurHaut(murHaut);
                piece.setmurDroit(murDroit);
                piece.setmurBas(murBas);
                piece.setUsage(usage);
                break;
            }
        }
    }
    
    public void updateSol(int idsol, Revetement revetement) {
        for (Sol sol : etage.getSol()) {
            if (sol.getidsol()== idsol) {
                sol.setRevetement(revetement);
                break;
            }
        }
    }
    
    public void updatePlafond(int idplafond, Revetement revetement) {
        for (Plafond plafond : etage.getPlafond()) {
            if (plafond.getidplafond()== idplafond) {
                plafond.setRevetement(revetement);
                break;
            }
        }
    }
    
    public void updateRevetement(int idrev, String typerev,double prix){
        for(Revetement rev : etage.getRevetement()){
            if (rev.getIdRevetement()== idrev) {
                rev.setTypeRevetement(typerev);
                rev.setPrix(prix);
                break;
            }
        }
    }
    
    
    
    
    
    
    
    
    
    
    //méthode servant à afficher des elements dans l'interface
    public void redraw(){
        getChildren().clear();
        
        // Définition de la position des différents labels et boutons
        imageView.setLayoutX(largeur * 2 / 3);
        imageView.setLayoutY(180);
        imageView.setFitWidth(largeur * 1 / 3);
        imageView.setFitHeight(largeur * 1 / 3);
       
        
        aide.setLayoutX(20);
        aide.setLayoutY(150);
        aide.setFont(new Font ("Papyrus", 17));
        
        aide2.setLayoutX(20);
        aide2.setLayoutY(hauteur*1/3+30);
        aide2.setFont(new Font ("Papyrus", 14));
        
        aide3.setLayoutX(largeur/3);    //POUR L'INSTANT INUTILE
        aide3.setLayoutY(hauteur/3);
        aide3.setFont(new Font ("Papyrus", 100));
        
        aide4.setLayoutX(20);      // POUR LE MESSAGE D'ALERTE ERREUR
        aide4.setLayoutY(hauteur*1/3+50);
        aide4.setFont(new Font ("Papyrus", 15));
        aide4.setText("SQUILLARIO-THOMAS-SCMITT");
        
        devis.setLayoutX(largeur*1/4);
        devis.setLayoutY(10);
        devis.setFont(new Font("Papyrus",15));
        devis.setText ("Le prix du bâtiment est de : "+getPrix()+"euros");
        
        devisetage.setLayoutX(largeur*1/4);
        devisetage.setLayoutY(hauteur-75);
        devisetage.setFont(new Font("Papyrus",15));
        devisetage.setText ("Le devis pour cet étage est de : "+getPrixEtage()+"euros");
        
        //Bouton indiquant le numéro de l'étage actuelle (sous forme de bouton pour une question d'esthétisme même s'il pourrait être défini comme un Label
        Button idetage = new Button("Etage n°"+String.valueOf(etage.getIdetage()));
        idetage.setLayoutX(largeur*2/3-120);
        idetage.setLayoutY(hauteur-75); 
        
        Rectangle rectangle1=new Rectangle();//association d'un revetement au sol selectionné (affichage d'un rectangle plein)
        rectangle1.setX(5);
        rectangle1.setY(150);
        rectangle1.setWidth(largeur*1/4-40);
        rectangle1.setHeight(hauteur*1/3-150);
        rectangle1.setFill(Color.LIGHTGREY);
        
        Rectangle rectangle2=new Rectangle();//association d'un revetement au sol selectionné (affichage d'un rectangle plein)
        rectangle2.setX(5);
        rectangle2.setY(150);
        rectangle2.setWidth(largeur*1/4-40);
        rectangle2.setHeight(hauteur*1/3-150);
        rectangle2.setFill(Color.WHITE);
        
        text.setX(20); // Position X du texte
        text.setY(180); // Position Y du texte
        text.setFill(Color.BLACK); // Couleur du texte
        text.setFont(Font.font("Papyrus", FontWeight.BOLD, 17));
        
        text1.setX(20); // Position X du texte
        text1.setY(hauteur-40); // Position Y du texte
        text1.setFill(Color.BLACK); // Couleur du texte
        text1.setFont(Font.font("Papyrus", FontWeight.BOLD, 10));
        
        
        
        Button validation = Bouton.creationbouton("Valider", largeur * 2 / 3 - 150, hauteur - 150, () -> {
            switch (etage.getetape()) {
                case 3 -> {
                    if (appart()+1==getNbpiece()){
                        Bouton.appart=false;
                        etage.setEtape(4);
                    }else{
                        Interface.aide.setText("Toutes les pièces n'ont pas\n été affectées à un appartement");
                    }
                }
                case 6 -> {
                    etage.setEtape(7);
                }
                default -> {
                    if (getNbSurface()==getRev()){
                        Bouton.rev=false;
                        etage.setEtape(6);
                    }
                }
            }
        });
        
        
        
        
        
        
        
        //menu déroulant des appartements
        ComboBox<String> boutonAppart = new ComboBox<>();
        boutonAppart.setLayoutX(20);
        boutonAppart.setLayoutY(70);
        boutonAppart.setPromptText("Sélectionnez le numéro de l'appartement");
        for (int i =1; i<nbappart+1;i++){
            boutonAppart.getItems().add(String.valueOf(i));
        }
        ChangeListener<String> choixAppartListener = (observable, oldValue, newValue) -> {
            String choixAppartStr = boutonAppart.getValue(); 
            choixAppart = Integer.parseInt(choixAppartStr);
        };
        boutonAppart.getSelectionModel().selectedItemProperty().addListener(choixAppartListener);
        
        
        
        
        
        
        
        
        
       
        
        
        //Une variable (attribut de l'étage) prend en compte l'etat d'avancements de l'etage pour afficher les elements correspondant
        switch (etage.getetape()) {
            
            case 0 -> {
                /*Rectangle rectangle1=new Rectangle();//association d'un revetement au sol selectionné (affichage d'un rectangle plein)
                rectangle1.setX(5);
                rectangle1.setY(150);
                rectangle1.setWidth(largeur*1/4-40);
                rectangle1.setHeight(hauteur*1/3-150);
                rectangle1.setFill(Color.LIGHTGREY);
                Text text = new Text("Veuillez créer la cage d'escalier");
                text.setX(20); // Position X du texte
                text.setY(180); // Position Y du texte
                text.setFill(Color.WHITE); // Couleur du texte
                text.setFont(Font.font("Papyrus", FontWeight.BOLD, 17));*/
                text.setText("Veuillez créer la cage d'escalier");
                aide.setText("Veuillez créer la cage d'escalier");  
                getChildren().addAll(aide, devis, devisetage, rectangle1, text, text1); //Création de l'escalier uniquement pour le rdc
            }
            
            
            
            
            
            case 1 -> { // Création des pièces
                /*ComboBox<String> bouton1 = new ComboBox<>();
                bouton1.setLayoutX(largeur*2/3);
                bouton1.setLayoutY(70);
                bouton1.setPromptText("Choisissez la pièce");
                for (int i=0; i<2; i++){
                    bouton1.getItems().add(String.valueOf(i));
                }               
                
                bouton1.getSelectionModel().selectedItemProperty().addListener(choixAppartListener);*/ 
                for (int i=0 ; i<2;i++) {
                    Button button=Bouton.boutons.get(i);
                    getChildren().add(button);
                }
                
                            
                aide2.setText("Surface totale : "+Math.round(Etage.getSurfacetot(etage))+" m²\n(arrondie de :"+Etage.getSurfacetot(etage)+"m²)\n \nSurface terrain : "+Math.round((Mur.getLargeur(etage.getMur().get(1))*Devis.getechellelongeur())*(Mur.getLargeur(etage.getMur().get(2))*Devis.getechellelargeur()))+" m²\n(arrondie de :"+(Mur.getLargeur(etage.getMur().get(1))*Devis.getechellelongeur())*(Mur.getLargeur(etage.getMur().get(2))*Devis.getechellelargeur())+"m²)");
                getChildren().addAll(aide, aide2, devis,devisetage,idetage,Bouton.boutons.get(5), rectangle1, text, text1);
            }
            
            
            
            
            
            case 2 -> { //Définition du nombre d'appart pour l'étage
                TextInputDialog dialog = new TextInputDialog();
                boolean validInput = false;
                dialog.setTitle("Définissez le nombre d'appartements de l'étage");
                dialog.setHeaderText("Combien d'appartements ?");
                dialog.setContentText("Entrez le nombre d'appartements :");
                while (!validInput) {
                    Optional<String> result = dialog.showAndWait();
                    if (result.isPresent()) {
                        String userInput = result.get();
                        nbappart = Integer.parseInt(userInput);
                        try {
                            if (nbappart > 0&&nbappart<etage.getPiece().size()) {
                                validInput = true;
                                etage.setEtape(3);
                            } else {
                                System.out.println("Veuillez saisir un nombre positif");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Veuillez saisir un nombre entier");
                            aide4.setText("ERREUR");
                            /*Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(4),event->{aide4.setText("77 ");}));
                            timeline.setCycleCount(1);
                            timeline.play();
                            getChildren().addAll(aide4);*/
                        }
                    }
                }
            }
            
            
            
            
            
            case 3 -> {//menu déroulant des appartements
                getChildren().addAll(boutonAppart, aide,validation,idetage,Bouton.boutons.get(5),rectangle1, text, text1);
                text.setText("mettre une aide je sais\n meme pas comment ca marche\n (ligne316 du code)");
                aide.setText("mettre une aide je sais\n meme pas comment ca marche\n (ligne316 du code)");
            }
            
            
            
            
            
            case 4 -> {//Ouvertures
                for (int i =1 ; i< 3;i++) {
                    Button button=Bouton.boutons.get(i);
                    getChildren().add(button);
                }
                getChildren().addAll(aide, devis,devisetage,idetage,Bouton.boutons.get(5),rectangle1, text, text1);
            }
            
            
            
            
            
            case 5 -> {//Revêtements
                getChildren().add(Bouton.boutons.get(1));
                getChildren().add(Bouton.boutons.get(3));
                aide2.setText("nombre de surface à revetir = "+getNbSurface()+"nombre de surface revetit = "+getRev());
                getChildren().addAll(aide, aide2, devis, devisetage, idetage, Bouton.boutons.get(5), validation, rectangle1, text, text1);
                getChildren().add(imageView);//Légende des revêtement
            }
            
            
            
            
            
            case 6 -> {//Possibilité d'incrémenter l'étage
                if (etage.getIdetage()==Devis.getnbetage()-1){
                    getChildren().add(validation);
                }
                getChildren().add(Bouton.boutons.get(4));
                getChildren().addAll(aide, aide2, devis,devisetage, idetage, rectangle1, text, text1);
                getChildren().add(imageView);
            }
            
            
            
            
            
            case 7 -> {// Si le bâtiment est finit (tous les étages on été revêtit et que l'utilisateur valide
                Alert alert = new Alert(Alert.AlertType.NONE);
                alert.setTitle("Validation du bâtiment");
                alert.setHeaderText(null);
                alert.setContentText("Valider le bâtiment");
                ButtonType oui = new ButtonType("Oui");
                alert.getButtonTypes().setAll(oui);
                Optional<ButtonType> reponse = alert.showAndWait();
                if (reponse.get() == oui) {//Affichage du devis final
                    alert.setTitle("Devis final");
                    alert.setHeaderText(null);
                    alert.setContentText("Le devis de votre bâtiment est de : "+getPrix()+" euros");
                    ButtonType ok = new ButtonType("Ok");
                    alert.getButtonTypes().setAll(ok);
                    Optional<ButtonType> finir = alert.showAndWait();
                        if (finir.get() == ok) {
                            Devis.primaryStage.close();
                        }
                }
                
            }
            
            
            
            
            
            
            default -> {}
        }
        
        
        
        
        
        
        
        //Dessiner les murs extérieur (terrain) et définition de la couleur en fonction de son revetement///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        for (int i = 0; i<9;i++) {
            Mur cote = etage.getMur().get(i);
            Line lineMur = new Line (cote.getDepart().getX(),cote.getDepart().getY(),cote.getArrivee().getX(),cote.getArrivee().getY());
                switch (cote.getRevetement().getTypeRevetement()){
                case "Peinture" -> lineMur.setStroke(Color.DARKORANGE);
                case "Bardage" -> lineMur.setStroke(Color.BROWN);
                case "Crépis" -> lineMur.setStroke(Color.BISQUE);
                case " "-> lineMur.setStroke(Color.BLACK);
            }  
            getChildren().add(lineMur);
        }
            //Affichage de tous les coins par des cerlces
        for (int i =2; i<etage.getCoin().size();i++) {
            Coin coin = etage.getCoin().get(i);
            Circle cercle;
            if (coin == selectedCoin) {
                cercle = new Circle(coin.getX(), coin.getY(), 5, Color.GREEN); //Bleu si coin selectionné
            } else {
                cercle = new Circle(coin.getX(), coin.getY(), 5, Color.BLUE); //Rouge sinon
            }
            getChildren().add(cercle);
        }
        //affichage des ouvertures comme ligne très épaisse sur un mur de couleur et taille differente selon le type choisi
        
        for (Ouverture ouv : etage.getOuverture()){
            Line o = new Line(ouv.getXd(),ouv.getYd(),ouv.getXf(),ouv.getYf());
            o.setStrokeWidth(6);
            o.setStroke(Color.RED);
            getChildren().add(o);
        }
        
        for (Piece piece : etage.getPiece()) {
            Label typepiece = new Label();
            Label appart = new Label();
            Rectangle rectangle=new Rectangle();    //association d'un revetement au sol selectionné (affichage d'un rectangle plein)
            rectangle.setX(piece.getMurHaut().getDepart().getX()+5);rectangle.setY(piece.getMurHaut().getDepart().getY()+5);
            rectangle.setWidth(Mur.getLargeur(piece.getMurHaut())-10);rectangle.setHeight(Mur.getLargeur(piece.getMurDroit())-10);
            
            switch(piece.getSol().getRevetement().getTypeRevetement()){
                case "Carrelage" -> rectangle.setFill(Color.HONEYDEW);
                case "Parquet" -> rectangle.setFill(Color.DARKSALMON);
                case "Bardage" -> rectangle.setFill(Color.BEIGE);
                case "Moquette" -> rectangle.setFill(Color.DARKSEAGREEN);
                case "Vinyle Lino" -> rectangle.setFill(Color.LIGHTGRAY);
                case "Stratifie" -> rectangle.setFill(Color.KHAKI);
                default -> rectangle.setFill(Color.WHITE);
            }
            getChildren().add(rectangle);
            
             
            
            
            //meme principe pour les murs (ligne décalée de 2 pixels vers l'intérieurs pour reconnaitre l'intérieur et l'extérieur
            for (Mur mur : piece.getMurs()) {
                Line lineMur;               
                if(mur==piece.getMurHaut()){
                    lineMur = new Line(mur.getDepart().getX()+2, mur.getDepart().getY()+2, mur.getArrivee().getX()-2, mur.getArrivee().getY()+2);
                }else if (mur==piece.getMurBas()){
                    lineMur = new Line(mur.getDepart().getX()-2, mur.getDepart().getY()-2, mur.getArrivee().getX()+2, mur.getArrivee().getY()-2);
                }else if (mur==piece.getMurGauche()){
                    lineMur = new Line(mur.getDepart().getX()+2, mur.getDepart().getY()-2, mur.getArrivee().getX()+2, mur.getArrivee().getY()+2);
                }else if (mur==piece.getMurDroit()){
                    lineMur = new Line(mur.getDepart().getX()-2, mur.getDepart().getY()+2, mur.getArrivee().getX()-2, mur.getArrivee().getY()-2);
                }else{
                    lineMur = new Line(mur.getDepart().getX(), mur.getDepart().getY(), mur.getArrivee().getX(), mur.getArrivee().getY());
                }
                switch (mur.getRevetement().getTypeRevetement()){
                    case "Peinture" -> lineMur.setStroke(Color.DARKORANGE);
                    case "Carrelage" -> lineMur.setStroke(Color.CORNFLOWERBLUE);
                    case "Parquet" -> lineMur.setStroke(Color.INDIANRED);
                    case "Bardage" -> lineMur.setStroke(Color.ANTIQUEWHITE);
                    case "Lambris" -> lineMur.setStroke(Color.HOTPINK);
                    case "Liège" -> lineMur.setStroke(Color.LIGHTPINK);
                    case " "-> lineMur.setStroke(Color.BLACK);
                }
                getChildren().add(lineMur);
            }
            
            
            
            
            //meme principe pour les plafond, ici l'affichache se fait en hachures pour eviter la supperposition avec le sol (plusieurs lignes verticales)
            double i = piece.getMurHaut().getDepart().getX()+5;
                while (i<piece.getMurHaut().getArrivee().getX()-5){
                    Line newLine = new Line (i,piece.getMurHaut().getDepart().getY()+5,i,piece.getMurBas().getDepart().getY()-5);
                    switch(piece.getPlafond().getRevetement().getTypeRevetement()){
                        case "Peinture" -> newLine.setStroke(Color.MAROON);
                        case "Parquet" -> newLine.setStroke(Color.DARKSLATEBLUE);
                        case "Bardage" -> newLine.setStroke(Color.TEAL);
                        case "Lambris" -> newLine.setStroke(Color.PURPLE);
                        case " "->newLine.setStroke(Color.WHITE);
                    }
                    i=i+25;
                    getChildren().add(newLine);
            }
                
                
                
                
                //Affichage du numéro d'appartement auquel appartient la pièce
            if (piece.getAppart()!=0){
                appart.setText("Appartement n°" +piece.getAppart());appart.toFront();
                appart.setLayoutX((piece.getMurHaut().getArrivee().getX()-piece.getMurHaut().getDepart().getX())/2+piece.getMurHaut().getDepart().getX());
                appart.setLayoutY((piece.getMurDroit().getArrivee().getY()-piece.getMurDroit().getDepart().getY())/2+piece.getMurDroit().getDepart().getY()+15);
                getChildren().add(appart);
            }
            //Affichage de l'usage de de la pièce
            typepiece.setText(piece.getUsage());typepiece.toFront();
            typepiece.setLayoutX((piece.getMurHaut().getArrivee().getX()-piece.getMurHaut().getDepart().getX())/2+piece.getMurHaut().getDepart().getX());
            typepiece.setLayoutY((piece.getMurDroit().getArrivee().getY()-piece.getMurDroit().getDepart().getY())/2+piece.getMurDroit().getDepart().getY());       
            getChildren().add(typepiece);
        }
    }    
    
    
    
    
    
    
    
    
    //Méthode de détection de la souris (click)
    @Override
    public void handle(MouseEvent e){
        redraw();
        double x = e.getX();//Récupération des coordonnées
        double y = e.getY();
        boolean deja=false;
        
        //si l'escalier n'a pas deja ete fait, il est créé en premier sur l'etage
        if (etage.getetape()==0){
            Bouton.usage="Escalier";
          if (premierCoin ==null){  
                for (Coin cote : etage.getCoin()) {
                        if (Math.abs(cote.getX() - e.getX()) < 20 && Math.abs(cote.getY() - e.getY()) < 20) {
                                premierCoin = cote;
                                selectedCoin = cote;
                                text.setText("Veuillez maintenant placer un coin");
                                aide.setText("Veuillez maintenant placer un coin");
                                redraw();
                                break;
                        }
                }
          }else{
            for (Coin coin : etage.getCoin()){
                if (Math.abs(coin.getX() - e.getX()) < 20 && Math.abs(coin.getY() - e.getY()) < 20) {
                    if (coin==selectedCoin){
                        text.setText("Vous avez déjà sélectionner ce coin");
                        aide.setText("Vous avez déjà sélectionner ce coin");
                        /*aide4.setText("ERREUR");
                            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(4),event->{aide4.setText(" ");}));
                            timeline.setCycleCount(1);
                            timeline.play();*/
                           
                    }
                }else if ((x>etage.getMur().get(4).getDepart().getX())&&(x<etage.getMur().get(2).getArrivee().getX())&&
                         (y>etage.getMur().get(1).getDepart().getY())&&(y<etage.getMur().get(3).getArrivee().getY())){  
                        Coin deuxiemeesca = new Coin(Coin.getLastIdcoin(), x, y, etage.getIdetage());
                        creationpiece(deuxiemeesca,deja);
                    selectedCoin=null;
                    premierCoin=null;
                    Bouton.piece=false;
                    etage.setEtape(1);
                    break;
                }
            }
          }
        }              
        
        
        
        
        //Si un type de piece a été selectionné
        if (Bouton.piece) {
            //si c'est le premeir coin que l'on pose
            if (premierCoin == null) {
                for (Coin cote : etage.getCoin()) {
                    //selection d'un coin dans une rayon de 20 pixels
                    if (Math.abs(cote.getX() - e.getX()) < 20 && Math.abs(cote.getY() - e.getY()) < 20) {
                        premierCoin = cote;
                        selectedCoin = cote;         
                        aide.setText("Veuillez maintenant placer un coin\n sur un mur existant, ou sur un autre\n coin");
                        text.setText("Veuillez maintenant placer un coin\n sur un mur existant, ou sur un autre\n coin");
                        redraw();
                        /*aide4.setText("ERREUR");
                            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(4),event->{aide4.setText("ERREUR");}));
                            timeline.setCycleCount(1);
                            timeline.play();*/
                    }
                }
            } else {
                //pose du deuxieme coin
                for (Coin coin : etage.getCoin()) {
                    if (Math.abs(coin.getX() - e.getX()) < 20 && Math.abs(coin.getY() - e.getY()) < 20) {
                        if (coin == selectedCoin) {
                            aide.setText("Vous avez déjà sélectionné ce coin là");
                            text.setText("Vous avez déjà sélectionné ce coin là");
                            Alert alert1 = new Alert(Alert.AlertType.NONE);
                            alert1.setTitle("ERREUR...");
                            alert1.setHeaderText(null);
                            alert1.setContentText("Vous ne pouvez construire une pièce avec un seul coin !!");
                            ButtonType ok = new ButtonType("Ok");
                                    alert1.getButtonTypes().setAll(ok);
                                    Optional<ButtonType> finir = alert1.showAndWait();
                                if (finir.get() == ok) {
                                    alert1.close();
                                }
                            /*aide2.setText("ERREUR");*/
                            /*Text text = new Text("erreur");
                            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1),text);
                            fadeTransition.setFromValue(1.0);
                            fadeTransition.setToValue(0.0);
                            fadeTransition.setCycleCount(3); // Répéter indéfiniment
                            fadeTransition.setAutoReverse(true); // Revenir en arrière automatiquement

                             // Commencer la transition
                            fadeTransition.play();
                            StackPane root = new StackPane();
                            root.getChildren().add(text);*/

                            // Créer la scène et afficher la fenêtre
                            
                            break;
                        } else {
                            deja=true;
                            creationpiece(coin,deja);
                            premierCoin=null;
                            selectedCoin = null;
                            Bouton.piece = false;
                            redraw();
                        }
                    }
                }
                //verification que la piece n'est pas de coins à l'interieur d'une autre 
                if ((x > etage.getMur().get(4).getDepart().getX()) && (x < etage.getMur().get(2).getArrivee().getX())
                        && (y > etage.getMur().get(1).getDepart().getY()) && (y < etage.getMur().get(3).getArrivee().getY())) {
                    boolean insidePiece = false;
                    for (Piece piece : etage.getPiece()) {
                        if ((x > piece.getMurGauche().getArrivee().getX()) && (x < piece.getMurDroit().getDepart().getX())
                                && (y > piece.getMurHaut().getArrivee().getY()) && (y < piece.getMurBas().getDepart().getY())) {
                            insidePiece = true;
                        }
                    }
                    //si ce n'est pas le cas
                    if (!insidePiece) {
                            double xcoin=0,ycoin=0;
                            for (Piece piece : etage.getPiece()){
                                for (Mur mur : piece.getMurs()){
                                    if (mur==piece.getMurHaut()){
                                        if(Math.abs(piece.getMurHaut().getDepart().getY() - e.getY()) < 25){
                                            xcoin=x;ycoin=mur.getDepart().getY();
                                        }
                                    }else if (mur==piece.getMurDroit()){
                                        if(Math.abs(piece.getMurDroit().getDepart().getX() - e.getX()) < 25){
                                            xcoin=mur.getDepart().getX();ycoin=y;
                                        }
                                    }else if (mur==piece.getMurBas()){
                                        if(Math.abs(piece.getMurBas().getDepart().getY() - e.getY()) < 25){
                                            xcoin=x;ycoin=mur.getDepart().getY();
                                        }
                                    }else if (mur==piece.getMurGauche()){
                                        if(Math.abs(piece.getMurGauche().getDepart().getX() - e.getX()) < 25){
                                            xcoin=mur.getDepart().getX();ycoin=y;
                                        }
                                    }
                                }    
                            }
                            for (int i=5;i<etage.getMur().size();i++){
                                Mur mur = etage.getMur().get(i);
                                if (mur==etage.getMur().get(1)){
                                        if(Math.abs(etage.getMur().get(1).getDepart().getY() - e.getY()) < 25){
                                            xcoin=x;ycoin=mur.getDepart().getY();
                                        }
                                    }else if (mur==etage.getMur().get(2)){
                                        if(Math.abs(etage.getMur().get(2).getDepart().getX() - e.getX()) < 25){
                                            xcoin=mur.getDepart().getX();ycoin=y;
                                        }
                                    }else if (mur==etage.getMur().get(3)){
                                        if(Math.abs(etage.getMur().get(3).getDepart().getY() - e.getY()) < 25){
                                            xcoin=x;ycoin=mur.getDepart().getY();
                                        }
                                    }else if (mur==etage.getMur().get(4)){
                                        if(Math.abs(etage.getMur().get(4).getDepart().getX() - e.getX()) < 25){
                                            xcoin=mur.getDepart().getX();ycoin=y;
                                        }
                                    }
                            }
                            Coin newCoin = new Coin(Coin.getLastIdcoin(),xcoin,ycoin,etage.getIdetage());
                            creationpiece(newCoin,deja);
                            premierCoin=null;
                            selectedCoin=null;
                            updateCoin(newCoin.getIdcoin(),newCoin.getX(),newCoin.getY());
                            Bouton.piece=false;
                            redraw();
                        }else{
                            aide.setText("Le coin ne peut pas être placé à\n l'intérieur d'une pièce existante.");
                            Alert alert1 = new Alert(Alert.AlertType.NONE);
                            alert1.setTitle("ERREUR...");
                            alert1.setHeaderText(null);
                            alert1.setContentText("Vous ne pouvez construire une pièce qui se chevauche avec une autre !!");
                            ButtonType ok = new ButtonType("Ok");
                                    alert1.getButtonTypes().setAll(ok);
                                    Optional<ButtonType> finir = alert1.showAndWait();
                                if (finir.get() == ok) {
                                    alert1.close();
                                }
                        }
                    }
                }
        }        
        
        
        
        
        //selection des appartements
        if (Bouton.appart==true){
            for (int i=1;i<etage.getPiece().size();i++){
                Piece piece = etage.getPiece().get(i);
                if((x > piece.getMurGauche().getDepart().getX()) && (x < piece.getMurDroit().getArrivee().getX())
                        && (y > piece.getMurHaut().getDepart().getY()) && (y < piece.getMurBas().getArrivee().getY())) {
                   piece.setAppart(choixAppart);
                   redraw();
                }
            }
        }
        
        
        
        
        //pose des revetements
        if (Bouton.rev){
                if(null != Bouton.surfacerev)
                    //il y a un double jeu de switch car le revetement est caractérisé par une surface (ex: mur) et un revetement (ex: peinture)
                    switch (Bouton.surfacerev) {
                case "Mur Intérieur" -> {
                    for (int i=5;i<etage.getMur().size();i++){
                        Mur mur = etage.getMur().get(i);
                        if ((mur.getDepart().getX()-mur.getArrivee().getX()!=0)&&(mur.getArrivee().getX()-mur.getDepart().getX()>0)&&(Math.abs(mur.getDepart().getY() - e.getY()) < 15)&&(e.getY()>mur.getDepart().getY()&&(e.getX()<mur.getArrivee().getX())&&(e.getX()>mur.getDepart().getX()))
                                ||(mur.getDepart().getX()-mur.getArrivee().getX()!=0)&&(mur.getArrivee().getX()-mur.getDepart().getX()<0)&&(Math.abs(mur.getDepart().getY() - e.getY()) < 15)&&(e.getY()<mur.getDepart().getY()&&(e.getX()>mur.getArrivee().getX())&&(e.getX()<mur.getDepart().getX()))
                                || (mur.getDepart().getY()-mur.getArrivee().getY()!=0)&&(mur.getArrivee().getY()-mur.getDepart().getY()>0)&&(Math.abs(mur.getDepart().getX() - e.getX()) < 15)&&(e.getX()<mur.getDepart().getX()&&(e.getY()<mur.getArrivee().getY())&&(e.getY()>mur.getDepart().getY()))
                                || (mur.getDepart().getY()-mur.getArrivee().getY()!=0)&&(mur.getArrivee().getY()-mur.getDepart().getY()<0)&&(Math.abs(mur.getDepart().getX() - e.getX()) < 15)&&(e.getX()>mur.getDepart().getX())&&(e.getY()>mur.getArrivee().getY())&&(e.getY()<mur.getDepart().getY())){
                            switch (Bouton.revetement){
                                case "Peinture" -> {
                                    updateRevetement(mur.getRevetement().getIdRevetement(),"Peinture",mur.getSurface(mur)*3);
                                    }
                                case "Carrelage" -> {
                                    updateRevetement(mur.getRevetement().getIdRevetement(),"Carrelage",mur.getSurface(mur)*7);
                                    }
                                case "Parquet" -> {
                                    updateRevetement(mur.getRevetement().getIdRevetement(),"Parquet",mur.getSurface(mur)*9);
                                    }
                                case "Bardage" -> {
                                    updateRevetement(mur.getRevetement().getIdRevetement(),"Bardage Bois",mur.getSurface(mur)*8);
                                    }
                                case "Lambris" -> {
                                    updateRevetement(mur.getRevetement().getIdRevetement(),"Lambris",mur.getSurface(mur)*5);
                                    }
                                case "Liège" -> {
                                    updateRevetement(mur.getRevetement().getIdRevetement(),"Liege",mur.getSurface(mur)*4);
                                    }
                            }
                            updateMur(mur.getidMur(),mur.getRevetement()); 
                        }
                    }
                    }
                case "Mur Extérieur" -> {
                    for (int i = 1; i<5 ;i++){
                        Mur mur = etage.getMur().get(i);
                        if ((mur.getDepart().getX()-mur.getArrivee().getX()!=0)&&(Math.abs(mur.getDepart().getY() - e.getY()) < 15)
                                || (mur.getDepart().getY()-mur.getArrivee().getY()!=0)&&(Math.abs(mur.getDepart().getX() - e.getX()) < 15)){
                            switch (Bouton.revetement){
                                case "Crépis" -> {
                                    updateRevetement(mur.getRevetement().getIdRevetement(),"Crépis",mur.getSurface(mur)*5);
                                    }
                                case "Peinture" -> {
                                    updateRevetement(mur.getRevetement().getIdRevetement(),"Peinture",mur.getSurface(mur)*3);
                                    }
                                case "Bardage" -> {
                                    updateRevetement(mur.getRevetement().getIdRevetement(),"Bardage Bois",mur.getSurface(mur)*8);
                                    }
                            }
                            updateMur(mur.getidMur(),mur.getRevetement());
                        }
                    }
                    }
                case "Sol" -> {
                    for (Piece sol :etage.getPiece()){
                        if((x>sol.getMurGauche().getDepart().getX())&&(x<sol.getMurDroit().getArrivee().getX())&&
                                (y>sol.getMurHaut().getDepart().getY())&&(y<sol.getMurBas().getArrivee().getY())){
                            switch (Bouton.revetement){
                                case "Carrelage" -> {
                                    updateRevetement(sol.getSol().getRevetement().getIdRevetement(),"Carrelage",sol.getSurface(sol.getMurHaut(),sol.getMurGauche())*7);
                                    }
                                case "Parquet" -> {
                                    updateRevetement(sol.getSol().getRevetement().getIdRevetement(),"Parquet",sol.getSurface(sol.getMurHaut(),sol.getMurGauche())*9);
                                    }
                                case "Bardage" -> {
                                    updateRevetement(sol.getSol().getRevetement().getIdRevetement(),"Bardage",sol.getSurface(sol.getMurHaut(),sol.getMurGauche())*8);
                                    }
                                case "Moquette" -> {
                                    updateRevetement(sol.getSol().getRevetement().getIdRevetement(),"Moquette",sol.getSurface(sol.getMurHaut(),sol.getMurGauche())*12);
                                    }
                                case "Vinyle Lino" -> {
                                    updateRevetement(sol.getSol().getRevetement().getIdRevetement(),"Vinyle Lino",sol.getSurface(sol.getMurHaut(),sol.getMurGauche())*5.5);
                                    }
                                case "Stratifie" -> {
                                    updateRevetement(sol.getSol().getRevetement().getIdRevetement(),"Stratifie",sol.getSurface(sol.getMurHaut(),sol.getMurGauche())*4.5);
                                    }
                            }
                            updateSol(sol.getSol().getidsol(),sol.getSol().getRevetement());
                        }
                    } 
                    }
                case "Plafond" -> {
                    for (Piece plafond :etage.getPiece()){
                        if((x>plafond.getMurGauche().getDepart().getX())&&(x<plafond.getMurDroit().getArrivee().getX())&&
                                (y>plafond.getMurHaut().getDepart().getY())&&(y<plafond.getMurBas().getArrivee().getY())){
                            switch (Bouton.revetement){
                                case "Peinture" -> {
                                    updateRevetement(plafond.getPlafond().getRevetement().getIdRevetement(),"Peinture",plafond.getSurface(plafond.getMurHaut(),plafond.getMurGauche())*3);
                                    }
                                case "Parquet" -> {
                                    updateRevetement(plafond.getPlafond().getRevetement().getIdRevetement(),"Parquet",plafond.getSurface(plafond.getMurHaut(),plafond.getMurGauche())*9);
                                    }
                                case "Bardage" -> {
                                    updateRevetement(plafond.getPlafond().getRevetement().getIdRevetement(),"Bardage Bois",plafond.getSurface(plafond.getMurHaut(),plafond.getMurGauche())*8);
                                    }
                                case "Lambris" -> {
                                    updateRevetement(plafond.getPlafond().getRevetement().getIdRevetement(),"Lambris",plafond.getSurface(plafond.getMurHaut(),plafond.getMurGauche())*5);
                                    }
                                }
                            }
                            updatePlafond(plafond.getPlafond().getidplafond(),plafond.getPlafond().getRevetement());
                        }
                    }
            }
        }
        
        
        
        
        //Pose des ouvertures
        if (Bouton.ouv==true){
            if ("Valider les ouvertures".equals(Bouton.ouverture)){
                etage.setEtape(5);
                Bouton.ouv=false;
                redraw();
            }
            
            
            
            
            int largeurouv;
                switch(Bouton.ouverture){
                    case "Porte" -> {
                        largeurouv=50;
                    }
                    case "Fenêtre" -> {
                        largeurouv=20;  
                    }
                    default -> {
                        largeurouv=0;
                    }
                }
            boolean paspossible= false;
            for (Ouverture o : etage.getOuverture()){
                if ((o.getXd()-o.getXf()==0)&&(y-largeurouv<o.getYf())&&(y+largeurouv>o.getYd())||
                      (o.getYd()-o.getYf()==0)&&(x-largeurouv<o.getXf())&&(x+largeurouv>o.getXd())){
                    aide.setText("Pas possible");
                    paspossible=true;
                }
            }
            
            //verification si l'ouvertuer est à un endroit adequat
            if(paspossible!=true){
                boolean placer=false;
                int i=0;
                while (placer==false){
                    Mur mur = etage.getMur().get(i);
                    Ouverture ouverture ;
                    if ((x>etage.getMur().get(4).getDepart().getX()+largeurouv)&&(x<etage.getMur().get(2).getArrivee().getX()-largeurouv)&&
                         (y>etage.getMur().get(1).getDepart().getY()+largeurouv)&&(y<etage.getMur().get(3).getArrivee().getY()-largeurouv)){
                        if (mur.getArrivee().getY()-mur.getDepart().getY()==00){
                            if(Math.abs(mur.getDepart().getY() - e.getY()) < 15){
                                ouverture = new Ouverture(Ouverture.getLastIdouv(),x-largeurouv,mur.getDepart().getY()+2,x+largeurouv,mur.getDepart().getY()+2,Bouton.ouverture,etage.getIdetage());
                                etage.getOuverture().add(ouverture);
                                placer=true;
                                updateOuverture(ouverture.getidouv(),ouverture.getXd(),ouverture.getYd(),ouverture.getXf(),ouverture.getYf());
                            }
                        }else{
                            if(Math.abs(mur.getDepart().getX() - e.getX()) < 15){
                                ouverture = new Ouverture(Ouverture.getLastIdouv(),mur.getDepart().getX()-2,y-largeurouv,mur.getDepart().getX()-2,y+largeurouv,Bouton.ouverture,etage.getIdetage());
                                etage.getOuverture().add(ouverture);
                                placer=true;
                                updateOuverture(ouverture.getidouv(),ouverture.getXd(),ouverture.getYd(),ouverture.getXf(),ouverture.getYf());
                            }
                        }
                    }
                    i++;
                }
           }
        }
        Bouton.ouv=false;
        redraw();
    }
    
    
    
    
    
    //méthode de création de la piece
    public void creationpiece(Coin deuxiemecoin, boolean deja){
        boolean pasdedans=false;
        //verification si la piece à un format valite (rectangle)
        if((deuxiemecoin.getX()==0)||(premierCoin.getX()==deuxiemecoin.getX())||(premierCoin.getY()==deuxiemecoin.getY())){
            pasdedans=true;
        }
        //creation des attributs de la pieces en fonction du sens de pose des deux coins
        //Important pour la définition des murs (Haut, Bas, Gauche, Droite)
        if(!pasdedans){
            Coin un, deux;
            Mur Haut, Bas, Gauche,Droit ;
            
            Sol newSol = new Sol(Sol.getLastIdsol(),new Revetement(Revetement.getLastIdrev()," ",0,1,0,0));
            Plafond newPlafond = new Plafond(Plafond.getLastIdplafond(),new Revetement(Revetement.getLastIdrev()," ",0,0,1,0));
            if((premierCoin.getX()<deuxiemecoin.getX())&&(premierCoin.getY()>deuxiemecoin.getY())){
                un = new Coin(Coin.getLastIdcoin(),premierCoin.getX(),deuxiemecoin.getY(),etage.getIdetage());
                deux = new Coin(Coin.getLastIdcoin(),deuxiemecoin.getX(),premierCoin.getY(),etage.getIdetage());
                Haut = new Mur(Mur.getLastIdmur(), un, deuxiemecoin ,etage.getIdetage(),new Revetement(Revetement.getLastIdrev()," ",1,0,0,0));
                Bas = new Mur(Mur.getLastIdmur(),deux,premierCoin,etage.getIdetage(),new Revetement(Revetement.getLastIdrev()," ",1,0,0,0));
                Gauche = new Mur(Mur.getLastIdmur(),premierCoin,un,etage.getIdetage(),new Revetement(Revetement.getLastIdrev()," ",1,0,0,0));
                Droit = new Mur(Mur.getLastIdmur(),deuxiemecoin,deux,etage.getIdetage(),new Revetement(Revetement.getLastIdrev()," ",1,0,0,0)); 
                
            }else if ((premierCoin.getX()>deuxiemecoin.getX())&&(premierCoin.getY()<deuxiemecoin.getY())){
                un = new Coin(Coin.getLastIdcoin(),premierCoin.getX(),deuxiemecoin.getY(),etage.getIdetage());
                deux = new Coin(Coin.getLastIdcoin(),deuxiemecoin.getX(),premierCoin.getY(),etage.getIdetage());
                Haut = new Mur(Mur.getLastIdmur(), deux, premierCoin ,etage.getIdetage(),new Revetement(Revetement.getLastIdrev()," ",1,0,0,0));
                Bas = new Mur(Mur.getLastIdmur(),un,deuxiemecoin,etage.getIdetage(),new Revetement(Revetement.getLastIdrev()," ",1,0,0,0));
                Gauche = new Mur(Mur.getLastIdmur(),deuxiemecoin,deux,etage.getIdetage(),new Revetement(Revetement.getLastIdrev()," ",1,0,0,0));
                Droit = new Mur(Mur.getLastIdmur(),premierCoin,un,etage.getIdetage(),new Revetement(Revetement.getLastIdrev()," ",1,0,0,0));
                
            }else if ((premierCoin.getX()<deuxiemecoin.getX())&&(premierCoin.getY()<deuxiemecoin.getY())){
                un = new Coin(Coin.getLastIdcoin(),deuxiemecoin.getX(),premierCoin.getY(),etage.getIdetage());
                deux = new Coin(Coin.getLastIdcoin(),premierCoin.getX(),deuxiemecoin.getY(),etage.getIdetage());
                Haut = new Mur(Mur.getLastIdmur(), premierCoin, un ,etage.getIdetage(),new Revetement(Revetement.getLastIdrev()," ",1,0,0,0));
                Bas = new Mur(Mur.getLastIdmur(),deuxiemecoin,deux,etage.getIdetage(),new Revetement(Revetement.getLastIdrev()," ",1,0,0,0));
                Gauche = new Mur(Mur.getLastIdmur(),deux,premierCoin,etage.getIdetage(),new Revetement(Revetement.getLastIdrev()," ",1,0,0,0));
                Droit = new Mur(Mur.getLastIdmur(),un,deuxiemecoin,etage.getIdetage(),new Revetement(Revetement.getLastIdrev()," ",1,0,0,0));
                
            }else {
                un = new Coin(Coin.getLastIdcoin(),deuxiemecoin.getX(),premierCoin.getY(),etage.getIdetage());
                deux = new Coin(Coin.getLastIdcoin(),premierCoin.getX(),deuxiemecoin.getY(),etage.getIdetage());
                Haut = new Mur(Mur.getLastIdmur(), deuxiemecoin, deux ,etage.getIdetage(),new Revetement(Revetement.getLastIdrev()," ",1,0,0,0));
                Bas = new Mur(Mur.getLastIdmur(),premierCoin,un,etage.getIdetage(),new Revetement(Revetement.getLastIdrev()," ",1,0,0,0));
                Gauche = new Mur(Mur.getLastIdmur(),un,deuxiemecoin,etage.getIdetage(),new Revetement(Revetement.getLastIdrev()," ",1,0,0,0));
                Droit = new Mur(Mur.getLastIdmur(),deux,premierCoin,etage.getIdetage(),new Revetement(Revetement.getLastIdrev()," ",1,0,0,0));
            }
            //ajout des objets aux listes de l'etage
            etage.getSol().add(newSol);
            etage.getPlafond().add(newPlafond);
            Piece newPiece = new Piece(Piece.getLastIdpiece(),newSol,newPlafond,Haut,Droit,Bas,Gauche,etage.getIdetage(),Bouton.usage,0);
            etage.getRevetement().add(newSol.getRevetement());etage.getRevetement().add(newPlafond.getRevetement());
            etage.getRevetement().add(Haut.getRevetement());etage.getRevetement().add(Bas.getRevetement());etage.getRevetement().add(Gauche.getRevetement());etage.getRevetement().add(Droit.getRevetement());
            //si le deuxième coin est un coins deja créé, il n'est pas ajouté à la liste (il y est deja)
            if (deja==false){
                etage.getCoin().add(deuxiemecoin);
            }
            etage.getCoin().add(un);etage.getCoin().add(deux);              
            etage.getMur().add(Haut);etage.getMur().add(Bas);etage.getMur().add(Gauche);etage.getMur().add(Droit);
            etage.getPiece().add(newPiece);
            updatePiece(newPiece.getidpiece(),newPiece.getMurHaut(),newPiece.getMurDroit(),newPiece.getMurBas(),newPiece.getMurGauche(),newPiece.getUsage(),etage);
            }else{
                etage.getCoin().remove(deuxiemecoin);
            }
        //Calcul pour savoir si l'utilisateur à rempli l'étage de pièce
            if(Math.abs(Etage.getSurfacetot(etage)-Mur.getLargeur(etage.getMur().get(1))*Mur.getLargeur(etage.getMur().get(2)))<2){
                etage.setEtape(2);
                Bouton.appart=true;
            }
    }
    
    //calcul du prix du batiment
    public String getPrix(){
        double devistot=0;
        for (Etage niveau :Etage.getEtage()){
            for (Revetement rev :niveau.getRevetement()){
                devistot=devistot+rev.getPrix();
            }
        }
        DecimalFormat arrondi = new DecimalFormat("#,##0.00"); 
        String prix = arrondi.format(devistot);
        return(prix);
    }
    
    //calcul du prix de l'etage
    public String getPrixEtage(){
        double devis1etage=0;
        for (Revetement rev :etage.getRevetement()){
            devis1etage=devis1etage+rev.getPrix();
        }
        
        DecimalFormat arrondi = new DecimalFormat("#,##0.00"); 
        String prix = arrondi.format(devis1etage);
        return(prix);
    }
    
    //Calcul nb de revêtement posé 
    public int getRev(){
        int revtot=0;
            for(Mur mur : etage.getMur()){
                if(!" ".equals(mur.getRevetement().getTypeRevetement())){
                    revtot++;
                }
            }
            for(Sol sol : etage.getSol()){
                if(!" ".equals(sol.getRevetement().getTypeRevetement())){
                    revtot++;
                }
            }
            for(Plafond pla : etage.getPlafond()){
                if(!" ".equals(pla.getRevetement().getTypeRevetement())){
                    revtot++;
                }
            }
        return(revtot);
    }
    
    public int getNbSurface(){
        int nbsurf=-1+etage.getMur().size()+etage.getSol().size()+etage.getPlafond().size();
        return(nbsurf);
    }
    
    public int appart(){
        int appart=0;
        for (Piece piece : etage.getPiece()){
            if (piece.getAppart()!=0){
                appart++;
            }
        }
        return(appart);
    }
    
    public int getNbpiece(){
        return(etage.getPiece().size());
    }
    
    
    
    
   /* 
    
    
    
    class Event {
    private String name;
    private int year;

    public Event(String name, int year) {
        this.name = name;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }
}

class TimelinePanel extends JPanel {
    private ArrayList<Event> events;

    public TimelinePanel(ArrayList<Event> events) {
        this.events = events;
    }

   
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int margin = 50;
        int height = getHeight1();
        int width = getWidth1();

        // Dessine une ligne horizontale
        g.drawLine(margin, height / 2, width - margin, height / 2);

        // Dessine les événements
        for (Event event : events) {
            int x = margin + (event.getYear() - events.get(0).getYear()) * (width - 2 * margin) / (events.get(events.size() - 1).getYear() - events.get(0).getYear());
            int y = height / 2 - 10; // Ajuste la coordonnée Y pour le marqueur d'événement
            g.drawString(event.getName(), x, y);
            g.drawLine(x, height / 2 - 5, x, height / 2 + 5); // Dessine le marqueur
        }
    }
}

public class TimelineExample {
    public static void main(String[] args) {
        ArrayList<Event> events = new ArrayList<>();
        events.add(new Event("Événement A", 2000));
        events.add(new Event("Événement B", 2010));
        events.add(new Event("Événement C", 2020));

        JFrame frame = new JFrame("Timeline");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize((hauteur-(hauteur/8)), largeur);

        TimelinePanel timelinePanel = new TimelinePanel(events);
        frame.add(timelinePanel);

        frame.setVisible(true);
    }
}*/
}

/* Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1),event->{aide4.setText(" ");}));
                timeline.setCycleCount(1);
                timeline.play();
                getChildren().addAll(aide4);
*/


