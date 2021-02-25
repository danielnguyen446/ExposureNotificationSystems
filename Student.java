/** 
 * Name: Daniel Nguyen
 * ID: A16129027
 * Email: d7nguyen@ucsd.edu
 * Sources used: tutor help(Ashley Kou, Claudia Handoyo, Peony Lum, Qingyang Hu)
 * 
 * This file contains the program to represent the functionality of 
 * a student's phone when using the exposure notification system.
 * It will handle both ID exchanges and student-related functions.
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
    
    /**constant(to avoid magic number)*/
    private static final int RISK_CONDITION = 3;
    
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
        contactHistory = new ArrayList<ContactInfo>();
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
     * This method sets id to a random integer and stores it in usedIds.
     */
    public void updateId()
    {
        Random rand = new Random();
        id = rand.nextInt(Integer.MAX_VALUE); //set id to random integer
        
        //store it in usedIds
        usedIds.add(id);
    }
    
    /**
     * This method adds information to contactHistory. It also indicates if 
     * this is done successfully.
     * 
     * @param info the information exchanged when phones come into contact 
     *      with each other.
     * @return false if info is invalid. Otherwise return true
     */
    public boolean addContactInfo(ContactInfo info)
    {
        //if info is valid, add it to contactHistory
        if(info.isValid() && info!=null)
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
     * @param server contains recent IDs of students who tested positive
     * @return true if added successfully
     */
    public boolean uploadAllUsedIds(Server server)
    {
        //if server is not null, usedIds go into infectedIds in server 
        if (server!=null)
        {
            server.addInfectedIds(usedIds);
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
     * @param server contains recent IDs of students who tested positive
     * @return true if uploadAllUsedIds executed successfully
     */
    public boolean testPositive(Server server)
    {
        covidPositive = true;
        inQuarantine = true;
        
        //upload usedIds to server
        return uploadAllUsedIds(server);
    }
    
    /**
     * This method will call getInfectedIds() and check contactHistory. 
     * It makes a list by finding contacts that satisfy a few requirements.
     * 
     * @param server contains recent IDs of students who tested positive
     * @param fromTime the starting time that students should check risk from   
     * 
     * @return a list of contacts from contactHistory that have not been used,
     *      its ID is in infectedIds, and its time is greater than or 
     *      equal to fromTime
     * @return null if server is null, fromTime is negative, or getInfectedIds 
     *      returns null
     */
    public ArrayList<ContactInfo> getRecentPositiveContacts(Server server,
    int fromTime)
    {
        //null checking
        if (server == null)
        {
            return null;
        }
        
        //create new sublist
        ArrayList<ContactInfo> contactList = new ArrayList<ContactInfo>(); 
        
        //a list of Ids made by calling getInfectedIds
        ArrayList<Integer> infectedIds = server.getInfectedIds();
        
        for(ContactInfo element: contactHistory)
        {
            //add each contact only if it satisfies the requirements
            if(element.used!=true && infectedIds.contains(element.id) && element.time>=fromTime)
            {
                contactList.add(element);
            }
        }
        //return the list of contacts
        return contactList;
    }
    
    /**
     * This method will assess the student's risk of being infected and allow
     * them to quarantine if they choose. Risk determined by at contact who 
     * tested positive or 
     * 
     * @param server contains recent IDs of students who tested positive
     * @param fromTime the starting time that students should check risk from   
     * @param quarantineChoice will be true if the student wants to quarantine.
     *      Otherwise will be false.
     * @return 1 if the student is high risk. Otherwise return 0.
     */
    public int riskCheck(Server server, int fromTime, boolean quarantineChoice)
    {
        //call getRecentPositiveContacts. If it returns null, return -1
        if(getRecentPositiveContacts(server, fromTime) == null)
        {
            return -1;
        }
        
        int riskLevel = 0;
        
        //If student has at least 1 recent contact who tested positive 
        if(getRecentPositiveContacts(server, fromTime).size()>=1)
        {
            for(ContactInfo element: contactHistory)
            {
                /**
                 * If this contact had distance less than or equal to 1,
                 * set to high risk and mark as used
                 */
                if(element.distance<=1)
                {
                    riskLevel = 1;
                    element.used = true;
                }
            }
        }
        
        //if at least 3 contacts tested positive
        if(getRecentPositiveContacts(server, fromTime).size()>=RISK_CONDITION)
        {
            //all ContactInfo should be marked as used
            for(ContactInfo element: contactHistory)
            {
                riskLevel = 1;
                element.used = true;
            }
        }
        
        //if high risk,
        if(riskLevel == 1)
        {            
            //if quarantineChoice is true
            if(quarantineChoice == true)
            {
                //the student will now be in quarantine
                inQuarantine = true;
            }        
            return 1;
        }
        else
        {
            return 0;
        }
    }
}
