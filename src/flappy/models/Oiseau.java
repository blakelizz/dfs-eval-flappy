package flappy.models;

import flappy.Principal;
import flappy.utils.Utils;

import java.awt.*;

public class Oiseau extends Sprite{

    protected float gravite = 1.0f;

    public Oiseau() {

        largeur = 50;
        color = Color.YELLOW;
    }

    public void deplacement(){
        this.y += gravite;
        this.gravite += 0.2;
    }

    public void dessiner(Graphics2D dessin){
        //corps
        dessin.setColor(color);
        dessin.fillOval(x,y, largeur, largeur);

        //bec
        int becLargeur = largeur / 3;
        int becHauteur = largeur / 4;

        dessin.setColor(Color.ORANGE);
        dessin.fillRect(x + largeur, y + largeur/2 - becHauteur/2, becLargeur, becHauteur);
    }



    public void saut(){
       this.gravite = -5;
    }

    public float getGravite() {
        return gravite;
    }

    public void setGravite(float gravite) {
        this.gravite = gravite;
    }
}
