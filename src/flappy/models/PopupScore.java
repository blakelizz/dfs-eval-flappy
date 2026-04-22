package flappy.models;

import java.awt.*;

public class PopupScore {
    private int x;
    private int y;
    private String texte;
    private long expiration;


    public PopupScore(int x, int y, String texte, int dureeMs) {
        this.x = x;
        this.y =y;
        this.texte = texte;
        this.expiration = System.currentTimeMillis() + dureeMs;
    }

    public boolean isExpired() {
        return System.currentTimeMillis() > expiration;
    }

    public void dessiner(Graphics2D dessin){
        dessin.setColor(new Color(200, 120, 0));
        dessin.setFont(new Font("Arial", Font.BOLD, 20));
        dessin.drawString(texte, x, y);
    }
}
