import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class menu here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class menu extends World
{

    /**
     * Constructor for objects of class menu.
     * 
     */
    public menu()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(3, 4, 150);
        addObject(new spielmodus_title(), 1, 0);
        addObject(new einspieler_button(), 0, 1);
        addObject(new zweispieler_button(), 1, 1);
        
    }
    
    // public void changeWorld(world) {
        // LoseWorld w = new LoseWorld();
        // Greenfoot.setWorld(w);
    // }
    
}
