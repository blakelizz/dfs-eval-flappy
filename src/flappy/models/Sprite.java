package flappy.models;

import java.awt.*;

public abstract class Sprite {

    protected int x;
    protected int y;
    protected int largeur;

    protected int vitesse;
    protected Color color;

    public abstract void dessiner(Graphics2D dessin);
    public abstract void deplacement();

    public boolean testCollision(Sprite cible){

        Zone[] zones = getZones();
        Zone[] zonesCible = cible.getZones();

        //pour toutes les zones de l'objet qui test
        for (Zone zone : zones) {
            //pour toutes les zones de l'objet qui est testé
            for (Zone zoneCible : zonesCible) {
                Point[] pointsCible = {
                        zoneCible.topLeft(),
                        zoneCible.topRight(),
                        zoneCible.bottomLeft(),
                        zoneCible.bottomRight()};

                //on vérifie si l'un des points de la zone cible se trouve
                // dans la zone de l'objet qui test
                for (Point pointCible : pointsCible) {
                    //si le point est dans la zone
                    if(pointCible.x() > zone.topLeft().x()
                            && pointCible.x() < zone.topRight().x()
                            && pointCible.y() > zone.topLeft().y()
                            && pointCible.y() < zone.bottomLeft().y()){
                            return true;
                        }
                }
            }
        }

        return false;
    }

    public Zone[] getZones(){
        Zone corps = new Zone(
                        new Point(x, y),
                        new Point(x + largeur, y),
                        new Point(x, y + largeur),
                        new Point(x + largeur, y + largeur));

        int becLargeur = largeur / 3;
        int becHauteur = largeur / 4;

        Zone bec = new Zone(
                new Point(x + largeur, y + largeur/2 - becLargeur/2),
                new Point(x + largeur + becLargeur, y + largeur/2 - becHauteur/2),
                new Point(x + largeur, y + largeur/2 - becHauteur/2),
                new Point(x + largeur + becLargeur, y + largeur/2 + becHauteur/2));

        return new Zone[]{corps, bec};
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getLargeur() {
        return largeur;
    }

    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }
}
