import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class zweispieler_button here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class zweispieler_button extends menuObjects
{
    /**
     * Act - do whatever the zweispieler_button wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    static String[] spielFeld = {" ", " ", " ", " ", " ", " ", " ", " ", " "};
    static String[] spieler = {"X", "O"}; // Spieler 1, Spieler 2 (Spieler 2 ist Computer wenn eingeschaltet)
    static boolean[] klickbar = {true, false}; // Ob Spieler 1, Spieler 2 setzen kann
    static boolean computer = false; // Einspieler/Zweispielermodus
    static int level = 3; //Schwierigkeitstufe

    public void act() 
    {
        if (Greenfoot.mouseClicked(this)) {
            System.out.println("ZWEISPIELER PRESSED");
            MyWorld w = new MyWorld(spielFeld, spieler, klickbar, computer, level);
            Greenfoot.setWorld(w);
        }
    }
}
