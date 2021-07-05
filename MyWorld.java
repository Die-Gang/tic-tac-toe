import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{
    
    static String[] spielFeld;
    static String[] spieler;
    static boolean[] klickbar;
    static boolean computer;
    static int level;
    
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld(String[] spielFeld, String[] spieler, boolean[] klickbar, boolean computer, int level)
    {  
        super(3, 4, 150);
        this.spielFeld = spielFeld;
        this.spieler = spieler;
        this.klickbar = klickbar;
        this.computer = computer;
        this.level = level;
        addObject(new menu_button(), 1, 3);
        field();
        setPaintOrder(Kreis.class, Kreuz.class);
    }
    
    /**
     * 
     */
    public void field ()
    {
        for (int i = 0; i < 3; i++){
            addObject(new field(), i, 0);
            addObject(new field(), i, 1);
            addObject(new field(), i, 2);
        }
    }
}
