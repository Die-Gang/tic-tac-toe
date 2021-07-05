import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class einspieler_button here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class einspieler_button extends menuObjects
{
    /**
     * Act - do whatever the einspieler_button wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if (Greenfoot.mouseClicked(this)) {
            getWorld().addObject(new level_1_button(), 0, 3);
            getWorld().addObject(new level_2_button(), 1, 3);
            getWorld().addObject(new level_3_button(), 2, 3);
            getWorld().addObject(new level_title(), 1, 2);
        }
    }
}
