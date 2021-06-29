import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class field here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class field extends Rounds
{
    
    private static String[] spielFeld = {" ", " ", " ", " ", " ", " ", " ", " ", " "};
    private static String[] spieler = {"X", "O"}; // Spieler 1, Spieler 2 (Spieler 2 ist Computer wenn eingeschaltet)
    private static boolean[] klickbar = {true, false}; // Ob Spieler 1, Spieler 2 setzen kann
    private static boolean computer = true; // Einspieler/Zweispielermodus
    private static int level = 3; //Schwierigkeitstufe
    /**
     * Act - do whatever the field wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        place();
    }
    
    /**
     * 
     */
    public void place()
    {
        if (Greenfoot.mouseClicked(this) && freieZüge(spielFeld) == true && überprüfeErgebnis(spielFeld) == "-"){
            if (klickbar[0] == true) {
                int[] koordinaten = {getX(), getY()};
                figurSetzen(spieler[0], koordinatenZuIndex(koordinaten));
            }
            if (klickbar[1] == true) {
                int[] koordinaten = {getX(), getY()};
                figurSetzen(spieler[1], koordinatenZuIndex(koordinaten));
            }
            if (computer == false) {
                if (klickbar[0] == true) {
                    klickbar[0] = false;
                    klickbar[1] = true;
                } else {
                    if (klickbar[1] == true) {
                        klickbar[0] = true;
                        klickbar[1] = false;
                    }
                }
            }
            überprüfeErgebnis(spielFeld);
            if (computer == true && freieZüge(spielFeld) == true && überprüfeErgebnis(spielFeld) == "-") {
                klickbar[0] = true;
                klickbar[1] = false;
                if (level == 1) {
                    int[] freiePositionen = freiePositionen();
                    int zufälligesFreiesFeld = freiePositionen[(int)(Math.random() * freiePositionen.length)];
                    figurSetzen(spieler[1], zufälligesFreiesFeld);
                }
                if (level == 3) {
                    int besterZug = findeBestenZug(spielFeld);
                    System.out.println("ZUG: " + besterZug);
                    figurSetzen(spieler[1], besterZug);
                }
                überprüfeErgebnis(spielFeld);
            }
        }
    }
    
    private static int[] freiePositionen() {
        int[] freiePositionen = new int[9];
        int counter = 0;
        for (int i = 0; i < spielFeld.length; i++) {
            if (spielFeld[i] != "X" && spielFeld[i] != "O" ) {
                int[] feld = zuFeldHinzufügen(i - counter, freiePositionen, i);
                freiePositionen = feld;
            } else {
                counter = counter + 1;
            }
        }
        return freiePositionen;
    }
    
    private static int[] zuFeldHinzufügen(int n, int feld[], int x) {
        int i;
        int newfeld[] = new int[n + 1];
        for (i = 0; i < n; i++) {
            newfeld[i] = feld[i];
        }
        newfeld[n] = x;
        return newfeld;
    }
    
    private static int koordinatenZuIndex(int[] koordinaten) {
        int ergebnis = (((koordinaten[0] + 1) * 1 + (koordinaten[1] * 3)) - 1);
        return ergebnis;
    }

    private static int[] indexZuKoordinaten(int index) {
        int[] ergebnis = new int[2];
        ergebnis[0] = index;
        if (index > -1 && index < 3) {
            ergebnis[1] = 0;
        }
        if (index > 2 && index < 6) {
            ergebnis[1] = 1;
            ergebnis[0] = ergebnis[0] - (1 * 3);
        }
        if (index > 5 && index < 9) {
            ergebnis[1] = 2;
            ergebnis[0] = ergebnis[0] - (2 * 3);
        }
        return ergebnis;
    }
    
    /* Fragt nach Feld und setzt aktuelle Figur dort hin. */
    private void figurSetzen(String figur, int position) {
        spielFeld[position] = figur;
        if (figur == "X") {
            getWorld().addObject(new Kreuz(), indexZuKoordinaten(position)[0], indexZuKoordinaten(position)[1]);
        }
        if (figur == "O") {
            getWorld().addObject(new Kreis(), indexZuKoordinaten(position)[0], indexZuKoordinaten(position)[1]);
        }
        System.out.println("\n" + spielFeld[0] + " | " + spielFeld[1] + " | " + spielFeld[2] + "\n---------\n" + spielFeld[3] + " | " + spielFeld[4] + " | " + spielFeld[5] + "\n---------\n" + spielFeld[6] + " | " + spielFeld[7] + " | " + spielFeld[8] + "\n");
    }
    
    static Boolean freieZüge(String spielFeld[])
    {
        for (int i = 0; i < 9; i++) {
            if (spielFeld[i] == " ") {
                return true;
            }
        }
        return false;
    }
    
    private static String überprüfeErgebnis(String[] spielFeld) {
        String ergebnis = "-";
        String spielFigur = "X";
        for (int i = 0; i < 2; i++) {
            if (spielFeld[0] == spielFigur && spielFeld[1] == spielFigur && spielFeld[2] == spielFigur) {
                ergebnis = spielFigur;
                int[] gewinnPosition = {0, 1, 2};
                ergebnisSpielfeld(gewinnPosition);
            }
            if (spielFeld[3] == spielFigur && spielFeld[4] == spielFigur && spielFeld[5] == spielFigur) {
                ergebnis = spielFigur;
                int[] gewinnPosition = {3, 4, 5};
                ergebnisSpielfeld(gewinnPosition);
            }
            if (spielFeld[6] == spielFigur && spielFeld[7] == spielFigur && spielFeld[8] == spielFigur) {
                ergebnis = spielFigur;
                int[] gewinnPosition = {6, 7, 8};
                ergebnisSpielfeld(gewinnPosition);
            }
            if (spielFeld[0] == spielFigur && spielFeld[3] == spielFigur && spielFeld[6] == spielFigur) {
                ergebnis = spielFigur;
                int[] gewinnPosition = {0, 3, 6};
                ergebnisSpielfeld(gewinnPosition);
            }
            if (spielFeld[1] == spielFigur && spielFeld[4] == spielFigur && spielFeld[7] == spielFigur) {
                ergebnis = spielFigur;
                int[] gewinnPosition = {1, 4, 7};
                ergebnisSpielfeld(gewinnPosition);
            }
            if (spielFeld[2] == spielFigur && spielFeld[5] == spielFigur && spielFeld[8] == spielFigur) {
                ergebnis = spielFigur;
                int[] gewinnPosition = {2, 5, 8};
                ergebnisSpielfeld(gewinnPosition);
            }
            if (spielFeld[8] == spielFigur && spielFeld[4] == spielFigur && spielFeld[0] == spielFigur) {
                ergebnis = spielFigur;
                int[] gewinnPosition = {8, 4, 0};
                ergebnisSpielfeld(gewinnPosition);
            }
            if (spielFeld[2] == spielFigur && spielFeld[4] == spielFigur && spielFeld[6] == spielFigur) {
                ergebnis = spielFigur;
                int[] gewinnPosition = {2, 4, 6};
                ergebnisSpielfeld(gewinnPosition);
            }
            spielFigur = "O";
        }
        if (ergebnis == "-" && freieZüge(spielFeld) == false) {
            System.out.println("ERGEBNIS: Unentschieden");
            klickbar[0] = false;
            klickbar[1] = false;
        }
        if (ergebnis == "X") {
            System.out.println("ERGEBNIS: Kreuz hat gewonnen");
            klickbar[0] = false;
            klickbar[1] = false;
        }
        if (ergebnis == "O") {
            System.out.println("ERGEBNIS: Kreis hat gewonnen");
            klickbar[0] = false;
            klickbar[1] = false;
        }
        return ergebnis; 
    }
    
    private static void ergebnisSpielfeld(int[] gewinnPosition) {
        String[] gewinnSpielFeld = new String[9];
        gewinnSpielFeld = spielFeld;
        entmarkieren(gewinnSpielFeld);
        System.out.println("\nERGEBNIS\n\n" + markieren(gewinnPosition, gewinnSpielFeld)[0] + " | " + markieren(gewinnPosition, gewinnSpielFeld)[1] + " | " + markieren(gewinnPosition, gewinnSpielFeld)[2] + "\n---------\n" + markieren(gewinnPosition, gewinnSpielFeld)[3] + " | " + markieren(gewinnPosition, gewinnSpielFeld)[4] + " | " + markieren(gewinnPosition, gewinnSpielFeld)[5] + "\n---------\n" + markieren(gewinnPosition, gewinnSpielFeld)[6] + " | " + markieren(gewinnPosition, gewinnSpielFeld)[7] + " | " + markieren(gewinnPosition, gewinnSpielFeld)[8] + "\n");
    }
    
    private static String[] markieren(int[] gewinnPosition, String[] gewinnSpielFeld) {
        for (int i = 0; i < gewinnPosition.length; i++) {
            String spielFigur = gewinnSpielFeld[gewinnPosition[i]];
            if (spielFigur == "x") {
                gewinnSpielFeld[gewinnPosition[i]] = "X";
            }
            if (spielFigur == "o") {
                gewinnSpielFeld[gewinnPosition[i]] = "O";
            }
        }
        return gewinnSpielFeld;
    }

    private static void entmarkieren(String[] gewinnSpielFeld) {
        for (int i = 0; i < gewinnSpielFeld.length; i++) {
            String spielFigur = spielFeld[i];
            if (spielFigur == "X") {
                gewinnSpielFeld[i] = "x";
            }
            if (spielFigur == "O") {
                gewinnSpielFeld[i] = "o";
            }
        }
    }
    
    /* MINIMAX START */
    
    static int evaluate(String[] spielFeld)
    {
        if (spielFeld[0] == spielFeld[1] && spielFeld[1] == spielFeld[2]) {
            if (spielFeld[0] == spieler[1])
                    return +10;
                else if (spielFeld[0] == spieler[0])
                    return -10;
        }
        if (spielFeld[3] == spielFeld[4] && spielFeld[4] == spielFeld[5]) {
            if (spielFeld[3] == spieler[1])
                    return +10;
                else if (spielFeld[3] == spieler[0])
                    return -10;
        }
        if (spielFeld[6] == spielFeld[7] && spielFeld[7] == spielFeld[8]) {
            if (spielFeld[6] == spieler[1])
                    return +10;
                else if (spielFeld[6] == spieler[0])
                    return -10;
        }
        if (spielFeld[0] == spielFeld[3] && spielFeld[3] == spielFeld[6]) {
            if (spielFeld[0] == spieler[1])
                    return +10;
                else if (spielFeld[0] == spieler[0])
                    return -10;
        }
        if (spielFeld[1] == spielFeld[4] && spielFeld[4] == spielFeld[7]) {
            if (spielFeld[1] == spieler[1])
                    return +10;
                else if (spielFeld[1] == spieler[0])
                    return -10;
        }
        if (spielFeld[2] == spielFeld[5] && spielFeld[5] == spielFeld[8]) {
            if (spielFeld[2] == spieler[1])
                    return +10;
                else if (spielFeld[2] == spieler[0])
                    return -10;
        }
        if (spielFeld[0] == spielFeld[4] && spielFeld[4] == spielFeld[8]) {
            if (spielFeld[0] == spieler[1])
                    return +10;
                else if (spielFeld[0] == spieler[0])
                    return -10;
        }
        if (spielFeld[2] == spielFeld[4] && spielFeld[4] == spielFeld[6]) {
            if (spielFeld[2] == spieler[1])
                    return +10;
                else if (spielFeld[2] == spieler[0])
                    return -10;
        }
        return 0;
    }
    
    static int minimax(String spielFeld[], int tiefe, Boolean istMaximal) {
        int score = evaluate(spielFeld);
        if (score == 10)
            return score;
        if (score == -10)
            return score;
        if (freieZüge(spielFeld) == false)
            return 0;
        if (istMaximal)
        {
            int best = -1000;
            for (int i = 0; i < 9; i++)
            {
                if (spielFeld[i]==" ")
                {
                    spielFeld[i] = spieler[1];
                    best = Math.max(best, minimax(spielFeld, tiefe + 1, !istMaximal));
                    spielFeld[i] = " ";
                }
            }
            return best;
        }
        else
        {
            int best = 1000;
            for (int i = 0; i < 9; i++)
            {
                if (spielFeld[i] == " ")
                {
                    spielFeld[i] = spieler[0];
                    best = Math.min(best, minimax(spielFeld, tiefe + 1, !istMaximal));
                    spielFeld[i] = " ";
                }
                
            }
            return best;
        }
    }
    
    static int findeBestenZug(String spielFeld[])
    {
        int besterWert = -1000;
        int besterZug = 0;
        for (int i = 0; i < 9; i++)
        {
            if (spielFeld[i] == " ")
            {
                spielFeld[i] = spieler[1];
                int zugWert = minimax(spielFeld, 0, false);
                spielFeld[i] = " ";
                if (zugWert > besterWert)
                {
                    besterZug = i;
                    besterWert = zugWert;
                }
            }
        }
        System.out.println("ZUGWERT " + besterWert);
        return besterZug;
    }

    /* MINIMAX END */
}
