/** 
 * Name: Daniel Nguyen
 * ID: A16129027
 * Email: d7nguyen@ucsd.edu
 * Sources used: tutor help(Ashley Kou)
 * 
 * This file contains the program to store recent IDs from users who test
 * positive for COVID-19. It has methods to add and return IDs using ArrayLists.
 */
 
import java.util.*;

/**
 * This class represents the server that contains the recent IDs of users who 
 * tested positive. It can add new IDs and get all stored IDs.
 */
public class Server
{
    /**instance variables*/
    public ArrayList<Integer> infectedIds;
    
    /**
     * This constructor initializes infectedIds with a new ArrayList.
     */
    public Server()  
    {
        infectedIds = new ArrayList<Integer>();
    }
    
    /**
     * This method puts all ids into the infectedIds array.
     * 
     * @param ids the random number assigned to a student's phone
     * @returns false if ids is null. Otherwise return true after successfully
     * adding the ids.
     */
    public boolean addInfectedIds(ArrayList<Integer> ids)
    {
        if (ids == null)
        {
            //return false and do not modify anything
            return false;
        }
        
        //iterate through the array list
        for(int a=0; a<ids.size(); a++)
        {
            //add every id to infectedIds
            infectedIds.add(ids.get(a));
        }
        
        //Once successfully done, return true
        return true;
    }
    
    /**
     * creates a new ArrayList and fills it with the elements in infectedIds
     *
     * @returns the new ArrayList copy.
     */
    public ArrayList<Integer> getInfectedIds()
    {
        //create a deep copy
        ArrayList<Integer> deepCopyInfectIds = new ArrayList<Integer>();
        
        //iterate through the array
        for(int a=0; a<infectedIds.size(); a++)
        {
            //fill it out
            deepCopyInfectIds.add(infectedIds.get(a));
        }
        
        //return the copy
        return deepCopyInfectIds;
    }
}
