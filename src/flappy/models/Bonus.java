package flappy.models;

import flappy.Principal;
import flappy.utils.Utils;

import java.awt.*;

public class Bonus extends Sprite {

    private String text;

    public Bonus() {
        largeur = 25;
        x = Principal.LARGEUR;
        y = Utils.aleatoire(25, Principal.HAUTEUR - 25);
        color = Color.YELLOW;
    }

    @Override
    public void dessiner(Graphics2D dessin) {
        dessin.setColor(color);
        dessin.setStroke(new BasicStroke(8));
        dessin.drawOval(x,y,largeur,largeur);
    }

    @Override
    public void deplacement() {
        x -= 6;
    }
}
