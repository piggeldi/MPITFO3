package Monopoly;

import java.awt.geom.Point2D;

/**
 *
 * @authore Thomas
 */
public class RailRoad extends Property
{
    public RailRoad(String newName, int newPrice, Point2D.Double coord, String g)
    {
       super(newName, newPrice,-1,-1,-1,-1,-1,-1, coord, -1, "", null, g);
    }
    
    @Override
    public String toString()
    {
        return  name + "\n"
                + "\nBesitzer : " + owner 
                + "\nKaufen: $"+price+""
                + "\nHypothek: $"+price / 2 
                + "\nBesitzt 1 Bahnhof: $25" 
                + "\nBesitzt 2 Bahnhof: $50"
                + "\nBesitzt 3 Bahnhof: $100" 
                + "\nBesitzt 4 Bahnhof: $200";
    }
    
    
}
