import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class menu_button here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class menu_button extends menuObjects
{
    /**
     * Act - do whatever the menu_button wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if (Greenfoot.mouseClicked(this)) {
            System.out.println("MENU PRESSED");
            ((field)getWorld().getObjects(field.class).get(0)).reset();
            menu m = new menu();
            Greenfoot.setWorld(m);
        }
    }    
}
