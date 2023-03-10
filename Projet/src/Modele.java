import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Modele {
    //Toutes les couleurs disponibles
    public static Color[] COULEURS = {Color.YELLOW, Color.GREEN,Color.BLUE,Color.MAGENTA,Color.RED,Color.ORANGE,Color.WHITE,Color.BLACK};
    //Nombre maximum de tentative
    static int NBR_MAX_TENTATIVES=10;
    //Nombre de couleurs à trouver
    public static  int DIFFICULTE=4;

    //Etat du jeu
    enum Etat {
        EN_COURS,GAGNE,PERDU
    };

    Etat etat;
    //Combinaison à trouver
    Rangée combinaison;
    //Historique des propositions
    ArrayList<Rangée> proposition =  new ArrayList<>();
    //Proposition en cours
    Rangée proposer;
    //Couleurs choisies pour la proposition en cours
    Color[] couleurs_choisi = new Color[DIFFICULTE];
    //Indice du tableau des couleurs choisies
    int indice_tableau_couleurs_choisi = 0;
    Random randomizer = new Random();
    //Proposition en cours
    int proposition_en_cours = 0;
    //Nombre de tentatives
    int tentatives;

    ArrayList<Integer> resultat = new ArrayList<>();
    /**
     * Historique des propositions
     * @return ArrayList : Historique des propositions
     */
    public ArrayList historique_des_propositions()

    {

        return this.proposition;
    }

    /**
     * Permet de savoir le nombre de réponses correctes et incorrectes
     */
    public void evaluer_propositions()
    {
        ArrayList<Integer> resultat = new ArrayList<>();
        int bien_placé = 0;
        int mal_placé = 0;

        for (int i = 0;i<DIFFICULTE-1;i++)
        {
            if (this.proposer.couleurs_choisi(i) == this.combinaison.couleurs_choisi(i))
            {
                bien_placé++;
            }
            else
            {
                mal_placé++;
            }
        }
        resultat.add(bien_placé);
        resultat.add(mal_placé);
        this.resultat = resultat;
    }
    /**
     * Fonction qui permet de completer les propositions en ajoutant une couleur à adapter avec le click.
     * Retourne rien.
     */
    public void completer_propositions(Color couleur)
    {
        if (this.indice_tableau_couleurs_choisi == DIFFICULTE-1)
        {
            this.proposer = new Rangée(this.couleurs_choisi);

            this.proposition.add(proposer);
            this.tentatives++;
            //evaluer_propositions();
            //demarrer_nouvelle_propositions();
        }
        else
        {
            this.couleurs_choisi[this.indice_tableau_couleurs_choisi] = couleur;
            this.indice_tableau_couleurs_choisi++;
        }
    }
    /**
     * Fonction qui permet de démarrer une nouvelle proposition
     * Réniitialise les variables
     */
    public void demarrer_nouvelle_propositions()
    {
            this.proposition_en_cours++;
            this.couleurs_choisi = new Color[DIFFICULTE];
            this.indice_tableau_couleurs_choisi = 0;
            this.proposer = null;
    }

    /**
     * Fonction qui permet de générer une combinaison aléatoire de couleurs à trouver.
     * Stocker dans
     */
    private void combinaison()
    {
        Color[] couleurs = new Color[DIFFICULTE-1];

        for (int i =0;i<DIFFICULTE-1;i++)
        {
            couleurs[i] = COULEURS[this.randomizer.nextInt(COULEURS.length)];
        }
        this.combinaison = new Rangée(couleurs);
    }

    /**
     * Constructeur
     * @param difficulté
     */
     Modele(int difficulté)
    {
        DIFFICULTE = difficulté;
        this.etat = Etat.EN_COURS;
    }

    public static Color[] getCOULEURS() {
        return COULEURS;
    }

    public int getNBR_MAX_TENTATIVES() {
        return NBR_MAX_TENTATIVES;
    }

    public static int getDIFFICULTE() {
        return DIFFICULTE;
    }

    public Etat getEtat() {
        return etat;
    }

    public Rangée getCombinaison() {
        return combinaison;
    }

    public ArrayList<Rangée> getProposition() {
        return proposition;
    }

    public Rangée getProposer() {
        return proposer;
    }

    public Color[] getCouleurs_choisi() {
        return couleurs_choisi;
    }



    public Random getRandomizer() {
        return randomizer;
    }

    public int getProposition_en_cours() {
        return proposition_en_cours;
    }

    public int getTentatives() {
        return tentatives;
    }

    public ArrayList<Integer> getResultat() {
        return resultat;
    }

    public static void main(String[] args) {
        Modele a = new Modele(5);
        a.combinaison();
        Color[] test = new Color[a.DIFFICULTE];
        for (int i = 0;i<a.DIFFICULTE;i++)
        {
            Color couleur = a.COULEURS[a.randomizer.nextInt(a.COULEURS.length)];
            a.completer_propositions(couleur);

        }
        System.out.println(a.proposer);
        System.out.println(a.combinaison);
        System.out.println(a.resultat);

    }



}
