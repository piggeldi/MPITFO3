package Monopoly;

import java.awt.geom.Point2D;

/**
 *
 * @authore Frederik
 */
public class Utility extends Property 
{
    /**
     * Constructs a new Utility with name, price, coordinates, and random String
     * @param newName The name of the Utility
     * @param newPrice Price of the Utility
     * @param coord Coordinates
     * @param g Useless
     */
    public Utility(String newName, int newPrice, Point2D.Double coord, String g)
    {
        //Rent is 4x roll if 1 is owned, 10x if both.
        super(newName, newPrice,-1,-1,-1,-1,-1,-1, coord, -1, "", null, g);
    }
    
    @Override
    public String toString()
    {
        return name + "\n"
                + "\nBesitzer: " +owner + "\n"
                + "Kaufen: $" + price + "\n"
                + "Hypothek: $" + price / 2 +"\n"
                + "Besitze ein Werk: $(4 x Augenzahl)\n"
                + "Besitze beide Werke: $(10 x Augenzahl)\n"
                + "Hypothek: "+mortgaged;
    }
    
    
}
