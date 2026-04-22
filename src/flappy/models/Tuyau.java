package flappy.models;

import flappy.Principal;
import flappy.utils.Utils;

import java.awt.*;

public class Tuyau extends Sprite{

    protected int ecartement = 200;
    protected int marge = 50;
    protected int life = 3;

    public Tuyau(){

        largeur = 100;
        vitesse = 4;
        color = Color.GREEN;

        x = Principal.LARGEUR;
        y = Utils.aleatoire(marge + ecartement, Principal.HAUTEUR - marge);
        life = 3;
    }

    public void touch() {
        life --;
    }

    public boolean destroyed() {
        return life <= 0;
    }

    public void deplacement(){
        x -= vitesse;

        if(x < -largeur) {
            x = Principal.LARGEUR;
            y = Utils.aleatoire(marge + ecartement, Principal.HAUTEUR - marge);
            life = 3; //reset de la vie
        }
    }

    public void dessiner(Graphics2D dessin){

        if (life == 3){
            dessin.setColor(new Color(0, 130, 0));
        } else if (life == 2) {
            dessin.setColor(new Color(80, 200, 80));
        } else if (life == 1) {
            dessin.setColor(new Color(230, 140, 0));
        }

        dessin.fillRect(x,y, largeur, Principal.HAUTEUR);
        dessin.fillRect(x,y-ecartement-Principal.HAUTEUR, largeur, Principal.HAUTEUR);
    }

    @Override
    public Zone[] getZones() {

        Zone zoneTuyauBas = new Zone(
                new Point(x, y),
                new Point(x + largeur, y),
                new Point(x, y + Principal.HAUTEUR),
                new Point(x+ largeur, y + Principal.HAUTEUR));

        Zone zoneTuyauHaut = new Zone(
                new Point(x, y - Principal.HAUTEUR),
                new Point(x + largeur, y - Principal.HAUTEUR),
                new Point(x, y - ecartement),
                new Point(x+ largeur, y - ecartement));

        return new Zone[]{zoneTuyauBas, zoneTuyauHaut};
    }
}
