import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(3, 4, 100);
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
