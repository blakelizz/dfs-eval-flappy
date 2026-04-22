package flappy.models;

import java.awt.*;

public class Projectile extends Sprite {

    public Projectile(int x, int y){
        this.x = x;
        this.y = y;
        this.largeur = 10;
        this.vitesse = 10;
        this.color = Color.RED;
    }


    @Override
    public void dessiner(Graphics2D dessin) {
        dessin.setColor(color);
        dessin.fillOval(x,y,largeur,largeur);

    }

    @Override
    public void deplacement() {
        x += vitesse;

    }
}
