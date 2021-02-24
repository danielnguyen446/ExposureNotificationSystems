/** 
 * Name: Daniel Nguyen
 * ID: A16129027
 * Email: d7nguyen@ucsd.edu
 * Sources used: tutor help(Ashley Kou)
 * 
 * This file contains the program to represent the functionality of 
 * a student's phone when using the exposure notification system.
 */

import java.util.*;

/**
 * This class handles ID exchanges, movements, and COVID-19 test status.
 * The methods also handle what happens when the student tests positive.
 */
public class Student
{
    /**instance variables*/
    public int id;  //random current id
    public int location;    //current location
    public boolean covidPositive;   //indicate if the student tested positive
    public boolean inQuarantine;    //indicate if the student cannot move
    public ArrayList<Integer> usedIds;  //all random ids the student used
    public ArrayList<ContactInfo> contactHistory;   //objects received
    
    /**
     * This constructor initializes the instance variables before the student
     * is ready for simulation.
     * 
     */
    public Student()
    {
        //student is not yet ready for simulation
        id = -1;
        location = -1;
        
        covidPositive = false;
        inQuarantine = false;
        
        //initialize with empty ArrayList
        usedIds = new ArrayList<Integer>();
        contactHistory = new ArrayList<>(ContactInfo);
    }
    
    /**
     * This method sets location to the new location only if the new location
     * is a valid number and if the student is not in quarantine.
     * 
     * @param newLocation
     * @return true if the new location is successfully set.
     */
    public boolean setLocation(int newLocation)
    {
        if (newLocation>=0 && inQuarantine==false)
        {
            location = newLocation;
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**
     * This method sets id to a random integer and stores it in usedIds
     */
    public void updateId()
    {
        Random rand = new Random();
        id = rand.nextInt(Integer.MAX_VALUE+1); //set id to random integer
        
        //store it in usedIds
        usedIds.add(id);
    }
    
    /**
     * This method adds information to contactHistory
     * 
     * @param info
     * @return false if info is invalid. Otherwise return true
     */
    public boolean addContactInfo(ContactInfo info)
    {
        if(info!=null && info>=0)//if info is valid, add it to contactHistory
        {
            contactHistory.add(info);
            return true;
        }
        else
        {
            //else return false
            return false;
        }
    }
    
    /**
     * This method adds all IDs in the student object's usedIds into the server
     * 
     * @param server
     * @return true if added successfully
     */
    public boolean uploadAllUsedIds(Server server)
    {
        //if server is not null, usedIds go into infectedIds in server 
        if (server!=null)
        {
            //iterate through and add all usedIds to infectedIds
            for(int a=0; a<usedIds.size(); a++)
            {
                addInfectedIds(usedIds.get());
            }
            return true;
        }
        else
        {
            //else return false, indicating that uploading failed
            return false;
        }
    }
    
    /**
     * This method updates covidPositive and inQuarantine to true and uploads
     * usedIds to server
     * 
     * @param server
     * @return true if uploadAllUsedIds executed successfully
     */
    public boolean testPositive(Server server)
    {
        covidPositive = true;
        inQuarantine = true;
        
        //upload usedIds to server
        uploadAllUsedIds();
    }
    
    /**
     * This method will call getInfectedIds() and check contactHistory.
     * 
     * @param server
     * @param fromTime
     * 
     * @return a list of contacts from contactHistory that have not been used,
     * its ID is in infectedIds, and its time is greater than or 
     * equal to fromTime
     * @return null if server is null, fromTime is negative, or getInfectedIds 
     * returns null
     */
    public ArrayList<ContactInfo> getRecentPositiveContacts(Server server,
    int fromTime)
    {
        
    }
    public int riskCheck(Server server, int fromTime, boolean quarantineChoice)
    {
        //call getRecentPositiveContacts. If it returns null, return -1
        //If student has at least 1 recent contact who tested positive 
            //if this contact had distance less than or equal to 1, mark as used
        
        //if at least 3 contacts tested positive
            //all ContactInfo should be marked as used
        
        //if high risk, 
            //if quarantineChoice is true
                //inQuarantine = true;
            //return 1;
        //else
            //return 0;
    }
}
