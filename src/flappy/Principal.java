package flappy;

import flappy.models.Bonus;
import flappy.models.Nuage;
import flappy.models.Oiseau;
import flappy.models.Tuyau;
import flappy.utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Principal extends Canvas implements KeyListener {

    public static final int LARGEUR = 800;
    public static final int HAUTEUR = 600;

    private JFrame fenetre = new JFrame();
    private Oiseau oiseau;
    private Tuyau tuyau;
    private Nuage[] nuages = new Nuage[10];
    private ArrayList<Bonus> listeBonus = new ArrayList<>();

    private boolean pause = false;
    private int score = 0;
    private int iteration = 0;

    public Principal() throws InterruptedException {


        fenetre.setSize(LARGEUR, HAUTEUR);

        this.setSize(LARGEUR, HAUTEUR);
        this.setBounds(0, 0, LARGEUR, HAUTEUR);

        //addEventListener("click", () => console.log("coucou") )
        fenetre.addKeyListener(this);


        JPanel panel = new JPanel();
        panel.add(this);
        fenetre.setContentPane(panel);

        fenetre.requestFocus();
        this.setFocusable(false);

        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        fenetre.setIgnoreRepaint(true);
        fenetre.setResizable(false);

        fenetre.pack();

        fenetre.setVisible(true);

        this.createBufferStrategy(2);

        for(int i = 0 ; i < nuages.length ; i++) {
            nuages[i] = new Nuage();
        }

        demarrer();
    }

    public void reset() {

        pause = false;

        oiseau = new Oiseau();
        oiseau.setX(200);
        oiseau.setY(200);

        tuyau = new Tuyau();
        tuyau.setX(LARGEUR);
        tuyau.setY(300);

        score = 0;

        for(Nuage nuage : nuages) {
            nuage.setX(Utils.aleatoire(0, LARGEUR));
            nuage.setY(Utils.aleatoire(0, HAUTEUR - 200));
            nuage.setLargeur(Utils.aleatoire(30,200));
        }
    }

    public void demarrer() throws InterruptedException {

        reset();

        while(true) {

            if(!pause) {

                score++;
                iteration ++;

                Graphics2D dessin = (Graphics2D) this.getBufferStrategy().getDrawGraphics();

                //---- fond ----

                dessin.setColor(Color.CYAN);
                dessin.fillRect(0, 0, LARGEUR, HAUTEUR);

                //---- nuages ----

                for(Nuage nuage : nuages) {
                    nuage.deplacement();
                    nuage.dessiner(dessin);
                }

                //---- bonus ----

                //on fait apparaitre un bonus toutes les 2 secondes
                if(iteration % 120 == 0) {
                    listeBonus.add(new Bonus());
                }

                //pour eviter de supprimer des element alors que l'on parcourt la liste
                //on créait une liste vide qui va stocker les bonus touche, puis on les supprimera
                ArrayList<Bonus> bonusTouche = new ArrayList<>();

                for(Bonus bonus : listeBonus) {
                    bonus.deplacement();
                    bonus.dessiner(dessin);

                    if(oiseau.testCollision(bonus)) {
                        score+=100;
                        bonusTouche.add(bonus);
                    }
                }

                //on supprime tous les bonus touche
                for(Bonus bonus : bonusTouche) {
                    listeBonus.remove(bonus);
                }

                //---- oiseau ----

                oiseau.deplacement();
                oiseau.dessiner(dessin);

                //---- tuyau ----

                tuyau.deplacement();
                tuyau.dessiner(dessin);

                if (tuyau.testCollision(oiseau) || oiseau.getY() > HAUTEUR - 50) {
                    pause = true;
                }

                //---- UI ----

                dessin.setColor(Color.BLACK);
                dessin.setFont(new Font("Arial", Font.BOLD, 20));
                dessin.drawString("score " + score, LARGEUR - 200, 20);

                //enregistrement du dessin
                dessin.dispose();
                //on switch du buffer d'affichage au buffer de preparation
                this.getBufferStrategy().show();


            }

            Thread.sleep(1000 / 60);
        }
    }

    public static void main(String[] args) throws InterruptedException {

        new Principal();

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE) {

            if(pause) {
                reset();
            } else {
                oiseau.saut();
            }
        }
    }
}
