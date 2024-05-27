package fr.insa.schmitt.projet_devis_s2;

public class Sol {
        private static int lastId = 0;
        private final int idsol ;
        public Revetement revetement;

        public Sol(int idsol, Revetement revetement) {
            this.idsol=lastId++;
            this.revetement=revetement;
        }

        public int getidsol() {
            return idsol;
        }

        public Revetement getRevetement() {
            return revetement;
        }

        public void setRevetement(Revetement revetement) {
            this.revetement = revetement;
        } 

        public static int getLastIdsol() {
            return lastId;
        }   
    }