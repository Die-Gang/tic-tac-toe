import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class field here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class field extends Rounds
{
    
    
    // private World world = getWorldOfType(World.class);
    
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
        if (Greenfoot.mouseClicked(this) && freieZüge(((MyWorld)getWorld()).spielFeld) == true && überprüfeErgebnis(((MyWorld)getWorld()).spielFeld) == "-"){
            if (((MyWorld)getWorld()).klickbar[0] == true) {
                int[] koordinaten = {getX(), getY()};
                figurSetzen(((MyWorld)getWorld()).spieler[0], koordinatenZuIndex(koordinaten));
            }
            if (((MyWorld)getWorld()).klickbar[1] == true) {
                int[] koordinaten = {getX(), getY()};
                figurSetzen(((MyWorld)getWorld()).spieler[1], koordinatenZuIndex(koordinaten));
            }
            if (((MyWorld)getWorld()).computer == false) {
                if (((MyWorld)getWorld()).klickbar[0] == true) {
                    ((MyWorld)getWorld()).klickbar[0] = false;
                    ((MyWorld)getWorld()).klickbar[1] = true;
                } else {
                    if (((MyWorld)getWorld()).klickbar[1] == true) {
                        ((MyWorld)getWorld()).klickbar[0] = true;
                        ((MyWorld)getWorld()).klickbar[1] = false;
                    }
                }
            }
            überprüfeErgebnis(((MyWorld)getWorld()).spielFeld);
            if (((MyWorld)getWorld()).computer == true && freieZüge(((MyWorld)getWorld()).spielFeld) == true && überprüfeErgebnis(((MyWorld)getWorld()).spielFeld) == "-") {
                ((MyWorld)getWorld()).klickbar[0] = true;
                ((MyWorld)getWorld()).klickbar[1] = false;
                if (((MyWorld)getWorld()).level == 1) {
                    int[] freiePositionen = freiePositionen();
                    int zufälligesFreiesFeld = freiePositionen[(int)(Math.random() * freiePositionen.length)];
                    figurSetzen(((MyWorld)getWorld()).spieler[1], zufälligesFreiesFeld);
                }
                if (((MyWorld)getWorld()).level == 3) {
                    int besterZug = findeBestenZug(((MyWorld)getWorld()).spielFeld);
                    System.out.println("ZUG: " + besterZug);
                    figurSetzen(((MyWorld)getWorld()).spieler[1], besterZug);
                }
                überprüfeErgebnis(((MyWorld)getWorld()).spielFeld);
            }
        }
    }
    
    private void reset()
    {
        System.out.println("RESET");
        for (int i = 0; i < ((MyWorld)getWorld()).spielFeld.length; i++){
            ((MyWorld)getWorld()).spielFeld[i] = " "; // https://www.greenfoot.org/topics/63215/0
        }
        ((MyWorld)getWorld()).removeObjects(((MyWorld)getWorld()).getObjects(Kreis.class)); // https://www.greenfoot.org/topics/61014/0
        ((MyWorld)getWorld()).removeObjects(((MyWorld)getWorld()).getObjects(Kreuz.class));
    }
    
    
    private int[] freiePositionen() {
        int[] freiePositionen = new int[9];
        int counter = 0;
        for (int i = 0; i < ((MyWorld)getWorld()).spielFeld.length; i++) {
            if (((MyWorld)getWorld()).spielFeld[i] != "X" && ((MyWorld)getWorld()).spielFeld[i] != "O" ) {
                int[] feld = zuFeldHinzufügen(i - counter, freiePositionen, i);
                freiePositionen = feld;
            } else {
                counter = counter + 1;
            }
        }
        return freiePositionen;
    }
    
    private int[] zuFeldHinzufügen(int n, int feld[], int x) {
        int i;
        int newfeld[] = new int[n + 1];
        for (i = 0; i < n; i++) {
            newfeld[i] = feld[i];
        }
        newfeld[n] = x;
        return newfeld;
    }
    
    private int koordinatenZuIndex(int[] koordinaten) {
        int ergebnis = (((koordinaten[0] + 1) * 1 + (koordinaten[1] * 3)) - 1);
        return ergebnis;
    }

    private int[] indexZuKoordinaten(int index) {
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
        ((MyWorld)getWorld()).spielFeld[position] = figur;
        if (figur == "X") {
            getWorld().addObject(new Kreuz(), indexZuKoordinaten(position)[0], indexZuKoordinaten(position)[1]);
        }
        if (figur == "O") {
            getWorld().addObject(new Kreis(), indexZuKoordinaten(position)[0], indexZuKoordinaten(position)[1]);
        }
        System.out.println("\n" + ((MyWorld)getWorld()).spielFeld[0] + " | " + ((MyWorld)getWorld()).spielFeld[1] + " | " + ((MyWorld)getWorld()).spielFeld[2] + "\n---------\n" + ((MyWorld)getWorld()).spielFeld[3] + " | " + ((MyWorld)getWorld()).spielFeld[4] + " | " + ((MyWorld)getWorld()).spielFeld[5] + "\n---------\n" + ((MyWorld)getWorld()).spielFeld[6] + " | " + ((MyWorld)getWorld()).spielFeld[7] + " | " + ((MyWorld)getWorld()).spielFeld[8] + "\n");
    }
    
    Boolean freieZüge(String spielFeld[])
    {
        for (int i = 0; i < 9; i++) {
            if (((MyWorld)getWorld()).spielFeld[i] == " ") {
                return true;
            }
        }
        return false;
    }
    
    private String überprüfeErgebnis(String[] spielFeld) {
        String ergebnis = "-";
        String spielFigur = "X";
        for (int i = 0; i < 2; i++) {
            if (((MyWorld)getWorld()).spielFeld[0] == spielFigur && ((MyWorld)getWorld()).spielFeld[1] == spielFigur && ((MyWorld)getWorld()).spielFeld[2] == spielFigur) {
                ergebnis = spielFigur;
                int[] gewinnPosition = {0, 1, 2};
                ergebnisSpielfeld(gewinnPosition);
            }
            if (((MyWorld)getWorld()).spielFeld[3] == spielFigur && ((MyWorld)getWorld()).spielFeld[4] == spielFigur && ((MyWorld)getWorld()).spielFeld[5] == spielFigur) {
                ergebnis = spielFigur;
                int[] gewinnPosition = {3, 4, 5};
                ergebnisSpielfeld(gewinnPosition);
            }
            if (((MyWorld)getWorld()).spielFeld[6] == spielFigur && ((MyWorld)getWorld()).spielFeld[7] == spielFigur && ((MyWorld)getWorld()).spielFeld[8] == spielFigur) {
                ergebnis = spielFigur;
                int[] gewinnPosition = {6, 7, 8};
                ergebnisSpielfeld(gewinnPosition);
            }
            if (((MyWorld)getWorld()).spielFeld[0] == spielFigur && ((MyWorld)getWorld()).spielFeld[3] == spielFigur && ((MyWorld)getWorld()).spielFeld[6] == spielFigur) {
                ergebnis = spielFigur;
                int[] gewinnPosition = {0, 3, 6};
                ergebnisSpielfeld(gewinnPosition);
            }
            if (((MyWorld)getWorld()).spielFeld[1] == spielFigur && ((MyWorld)getWorld()).spielFeld[4] == spielFigur && ((MyWorld)getWorld()).spielFeld[7] == spielFigur) {
                ergebnis = spielFigur;
                int[] gewinnPosition = {1, 4, 7};
                ergebnisSpielfeld(gewinnPosition);
            }
            if (((MyWorld)getWorld()).spielFeld[2] == spielFigur && ((MyWorld)getWorld()).spielFeld[5] == spielFigur && ((MyWorld)getWorld()).spielFeld[8] == spielFigur) {
                ergebnis = spielFigur;
                int[] gewinnPosition = {2, 5, 8};
                ergebnisSpielfeld(gewinnPosition);
            }
            if (((MyWorld)getWorld()).spielFeld[8] == spielFigur && ((MyWorld)getWorld()).spielFeld[4] == spielFigur && ((MyWorld)getWorld()).spielFeld[0] == spielFigur) {
                ergebnis = spielFigur;
                int[] gewinnPosition = {8, 4, 0};
                ergebnisSpielfeld(gewinnPosition);
            }
            if (((MyWorld)getWorld()).spielFeld[2] == spielFigur && ((MyWorld)getWorld()).spielFeld[4] == spielFigur && ((MyWorld)getWorld()).spielFeld[6] == spielFigur) {
                ergebnis = spielFigur;
                int[] gewinnPosition = {2, 4, 6};
                ergebnisSpielfeld(gewinnPosition);
            }
            spielFigur = "O";
        }
        if (ergebnis == "-" && freieZüge(((MyWorld)getWorld()).spielFeld) == false) {
            System.out.println("ERGEBNIS: Unentschieden");
            ((MyWorld)getWorld()).klickbar[0] = false;
            ((MyWorld)getWorld()).klickbar[1] = false;
            reset();
        }
        if (ergebnis == "X") {
            System.out.println("ERGEBNIS: Kreuz hat gewonnen");
            ((MyWorld)getWorld()).klickbar[0] = false;
            ((MyWorld)getWorld()).klickbar[1] = false;
            reset();
        }
        if (ergebnis == "O") {
            System.out.println("ERGEBNIS: Kreis hat gewonnen");
            ((MyWorld)getWorld()).klickbar[0] = false;
            ((MyWorld)getWorld()).klickbar[1] = false;
            reset();
        }
        return ergebnis; 
    }
    
    private void ergebnisSpielfeld(int[] gewinnPosition) {
        String[] gewinnSpielFeld = new String[9];
        gewinnSpielFeld = ((MyWorld)getWorld()).spielFeld;
        entmarkieren(gewinnSpielFeld);
        System.out.println("\nERGEBNIS\n\n" + markieren(gewinnPosition, gewinnSpielFeld)[0] + " | " + markieren(gewinnPosition, gewinnSpielFeld)[1] + " | " + markieren(gewinnPosition, gewinnSpielFeld)[2] + "\n---------\n" + markieren(gewinnPosition, gewinnSpielFeld)[3] + " | " + markieren(gewinnPosition, gewinnSpielFeld)[4] + " | " + markieren(gewinnPosition, gewinnSpielFeld)[5] + "\n---------\n" + markieren(gewinnPosition, gewinnSpielFeld)[6] + " | " + markieren(gewinnPosition, gewinnSpielFeld)[7] + " | " + markieren(gewinnPosition, gewinnSpielFeld)[8] + "\n");
    }
    
    private String[] markieren(int[] gewinnPosition, String[] gewinnSpielFeld) {
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

    private void entmarkieren(String[] gewinnSpielFeld) {
        for (int i = 0; i < gewinnSpielFeld.length; i++) {
            String spielFigur = ((MyWorld)getWorld()).spielFeld[i];
            if (spielFigur == "X") {
                gewinnSpielFeld[i] = "x";
            }
            if (spielFigur == "O") {
                gewinnSpielFeld[i] = "o";
            }
        }
    }
    
    /* MINIMAX START */
    
    int evaluate(String[] spielFeld)
    {
        if (((MyWorld)getWorld()).spielFeld[0] == ((MyWorld)getWorld()).spielFeld[1] && ((MyWorld)getWorld()).spielFeld[1] == ((MyWorld)getWorld()).spielFeld[2]) {
            if (((MyWorld)getWorld()).spielFeld[0] == ((MyWorld)getWorld()).spieler[1])
                    return +10;
                else if (((MyWorld)getWorld()).spielFeld[0] == ((MyWorld)getWorld()).spieler[0])
                    return -10;
        }
        if (((MyWorld)getWorld()).spielFeld[3] == ((MyWorld)getWorld()).spielFeld[4] && ((MyWorld)getWorld()).spielFeld[4] == ((MyWorld)getWorld()).spielFeld[5]) {
            if (((MyWorld)getWorld()).spielFeld[3] == ((MyWorld)getWorld()).spieler[1])
                    return +10;
                else if (((MyWorld)getWorld()).spielFeld[3] == ((MyWorld)getWorld()).spieler[0])
                    return -10;
        }
        if (((MyWorld)getWorld()).spielFeld[6] == ((MyWorld)getWorld()).spielFeld[7] && ((MyWorld)getWorld()).spielFeld[7] == ((MyWorld)getWorld()).spielFeld[8]) {
            if (((MyWorld)getWorld()).spielFeld[6] == ((MyWorld)getWorld()).spieler[1])
                    return +10;
                else if (((MyWorld)getWorld()).spielFeld[6] == ((MyWorld)getWorld()).spieler[0])
                    return -10;
        }
        if (((MyWorld)getWorld()).spielFeld[0] == ((MyWorld)getWorld()).spielFeld[3] && ((MyWorld)getWorld()).spielFeld[3] == ((MyWorld)getWorld()).spielFeld[6]) {
            if (((MyWorld)getWorld()).spielFeld[0] == ((MyWorld)getWorld()).spieler[1])
                    return +10;
                else if (((MyWorld)getWorld()).spielFeld[0] == ((MyWorld)getWorld()).spieler[0])
                    return -10;
        }
        if (((MyWorld)getWorld()).spielFeld[1] == ((MyWorld)getWorld()).spielFeld[4] && ((MyWorld)getWorld()).spielFeld[4] == ((MyWorld)getWorld()).spielFeld[7]) {
            if (((MyWorld)getWorld()).spielFeld[1] == ((MyWorld)getWorld()).spieler[1])
                    return +10;
                else if (((MyWorld)getWorld()).spielFeld[1] == ((MyWorld)getWorld()).spieler[0])
                    return -10;
        }
        if (((MyWorld)getWorld()).spielFeld[2] == ((MyWorld)getWorld()).spielFeld[5] && ((MyWorld)getWorld()).spielFeld[5] == ((MyWorld)getWorld()).spielFeld[8]) {
            if (((MyWorld)getWorld()).spielFeld[2] == ((MyWorld)getWorld()).spieler[1])
                    return +10;
                else if (((MyWorld)getWorld()).spielFeld[2] == ((MyWorld)getWorld()).spieler[0])
                    return -10;
        }
        if (((MyWorld)getWorld()).spielFeld[0] == ((MyWorld)getWorld()).spielFeld[4] && ((MyWorld)getWorld()).spielFeld[4] == ((MyWorld)getWorld()).spielFeld[8]) {
            if (((MyWorld)getWorld()).spielFeld[0] == ((MyWorld)getWorld()).spieler[1])
                    return +10;
                else if (((MyWorld)getWorld()).spielFeld[0] == ((MyWorld)getWorld()).spieler[0])
                    return -10;
        }
        if (((MyWorld)getWorld()).spielFeld[2] == ((MyWorld)getWorld()).spielFeld[4] && ((MyWorld)getWorld()).spielFeld[4] == ((MyWorld)getWorld()).spielFeld[6]) {
            if (((MyWorld)getWorld()).spielFeld[2] == ((MyWorld)getWorld()).spieler[1])
                    return +10;
                else if (((MyWorld)getWorld()).spielFeld[2] == ((MyWorld)getWorld()).spieler[0])
                    return -10;
        }
        return 0;
    }
    
    int minimax(String spielFeld[], int tiefe, Boolean istMaximal) {
        int score = evaluate(((MyWorld)getWorld()).spielFeld);
        if (score == 10)
            return score;
        if (score == -10)
            return score;
        if (freieZüge(((MyWorld)getWorld()).spielFeld) == false)
            return 0;
        if (istMaximal)
        {
            int best = -1000;
            for (int i = 0; i < 9; i++)
            {
                if (((MyWorld)getWorld()).spielFeld[i]==" ")
                {
                    ((MyWorld)getWorld()).spielFeld[i] = ((MyWorld)getWorld()).spieler[1];
                    best = Math.max(best, minimax(((MyWorld)getWorld()).spielFeld, tiefe + 1, !istMaximal));
                    ((MyWorld)getWorld()).spielFeld[i] = " ";
                }
            }
            return best;
        }
        else
        {
            int best = 1000;
            for (int i = 0; i < 9; i++)
            {
                if (((MyWorld)getWorld()).spielFeld[i] == " ")
                {
                    ((MyWorld)getWorld()).spielFeld[i] = ((MyWorld)getWorld()).spieler[0];
                    best = Math.min(best, minimax(((MyWorld)getWorld()).spielFeld, tiefe + 1, !istMaximal));
                    ((MyWorld)getWorld()).spielFeld[i] = " ";
                }
                
            }
            return best;
        }
    }
    
    int findeBestenZug(String spielFeld[])
    {
        int besterWert = -1000;
        int besterZug = 0;
        for (int i = 0; i < 9; i++)
        {
            if (((MyWorld)getWorld()).spielFeld[i] == " ")
            {
                ((MyWorld)getWorld()).spielFeld[i] = ((MyWorld)getWorld()).spieler[1];
                int zugWert = minimax(((MyWorld)getWorld()).spielFeld, 0, false);
                ((MyWorld)getWorld()).spielFeld[i] = " ";
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
