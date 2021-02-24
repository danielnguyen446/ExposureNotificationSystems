/** 
 * Name: Daniel Nguyen
 * ID: A16129027
 * Email: d7nguyen@ucsd.edu
 * Sources used: tutor help(Eric Song)
 * 
 * This file contains program that stores the information exchanged
 * when phones come into contact with each other. It includes the 
 * random ID, distance, and time of that contact.
 */

/**
 * This class stores the information. It contains the constructor to
 * initialize the data, as well as the method to check if the data is valid.
 */
public class ContactInfo
{
    /** instance variables */
    public int id; 
    public int time;
    public int distance;
    public boolean used;
    
    /**
     * This constructor initializes the instance variables.
     * 
     * @param the id, distance, and time
     */
    public ContactInfo(int id, int distance, int time)
    {
        id = id;
        time = time;
        distance = distance;
        used = false;
    }
    
    /**
     * this method will determine if the instance variables are valid
     * (non-negative)
     * 
     * @return true if all instance variables are valid. Otherwise return false.
     */
    public boolean isValid()
    {
        //returns true if all the variables are greater than or equal to zero.
        return (id>=0 && time>=0 && distance>=0);
    }
}
