package fr.insa.schmitt.projet_devis_s2;

public class Plafond {
    
    private static int lastId = 0;
        private final int idplafond ;
        public Revetement revetement;

        public Plafond(int idplafond, Revetement revetement) {
            this.idplafond=lastId++;
            this.revetement=revetement;
        }

        public int getidplafond() {
            return idplafond;
        }

        public Revetement getRevetement() {
            return revetement;
        }

        public void setRevetement(Revetement revetement) {
            this.revetement = revetement;
        } 

        public static int getLastIdplafond() {
            return lastId;
        }   
  
}
