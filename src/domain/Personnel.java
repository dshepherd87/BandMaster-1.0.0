/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import dataSource.PersonnelDAO;
import java.util.List;

/**
 *
 * @author David Shepherd
 */
public class Personnel {
    private int personnelId;
    private String realWorldId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String title;
    
    public Personnel(){        
    }
    
    public Personnel(int personnelId, String lastName){
        this.personnelId = personnelId;
        this.lastName = lastName;
    }
    
    /**
     * Constructor initializing all values
     * @param personnelId
     * @param realWorldId
     * @param firstName
     * @param middleName
     * @param lastName
     * @param title 
     */
    public Personnel(int personnelId, String realWorldId, String firstName, 
            String middleName, String lastName, String title){
        this.personnelId = personnelId;
        this.realWorldId = realWorldId;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.title = title;
    }
    
    /**
     * Constructor initializing all values except personnelID
     * @param realWorldId
     * @param firstName
     * @param middleName
     * @param lastName
     * @param title 
     */
    public Personnel(String realWorldId, String firstName, 
            String middleName, String lastName, String title){
        PersonnelDAO p = new PersonnelDAO();
        //this.personnelId = p.getId();
        this.realWorldId = realWorldId;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.title = title;
    }
    public static Personnel[] getArray(){
        PersonnelDAO p = new PersonnelDAO();
        List<Personnel> personnelList = p.getAll();
        int length = personnelList.size();
        Personnel[] personnelArray = new Personnel[length];
        for(int i = 0; i < length; i++){
            personnelArray[i] = personnelList.get(i);
        }
        return personnelArray;
    }
    public static Personnel[] getArray(Personnel personnel){
        PersonnelDAO p = new PersonnelDAO();
        List<Personnel> pList = p.getMatchingList(personnel);
        int length = pList.size();
        Personnel[] pArray = new Personnel[length];
        for(int i = 0; i < length; i++){
            pArray[i] = pList.get(i);
        }
        return pArray;
    }
    public static List<Personnel> getList(){
        PersonnelDAO p = new PersonnelDAO();
        return p.getAll();
    }    

    /**
     * Passthrough method for adding new records
     * @return 
     */
    public boolean add(){
        PersonnelDAO p = new PersonnelDAO();
        return p.add(this);
    }
    
    /**
     * Passthrough method for deleting records
     * @return 
     */
    public boolean delete(){
        PersonnelDAO p = new PersonnelDAO();
        return p.delete(this);
    }
    
    /**
     * Passthrough method for updating records
     * @return 
     */
    public boolean update(){
        PersonnelDAO p = new PersonnelDAO();
        return p.update(this);
    }
    
    /**
     * @return the personnelId
     */
    public int getPersonnelId() {
        return personnelId;
    }

    /**
     * @param personnelId the personnelId to set
     */
    public void setPersonnelId(int personnelId) {
        this.personnelId = personnelId;
    }

    /**
     * @return the realWorldId
     */
    public String getRealWorldId() {
        return realWorldId;
    }

    /**
     * @param realWorldId the realWorldId to set
     */
    public void setRealWorldId(String realWorldId) {
        this.realWorldId = realWorldId;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the middleName
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * @param middleName the middleName to set
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }
    /**
     * display name like "Smith, J."
     * @return 
     */
    @Override
    public String toString(){
        String outputString;
        String fName, mName, lName;
        if(this.firstName == null)
            fName = "";
        else
            fName = this.firstName;
        if(this.middleName == null)
            mName = "";
        else
            mName = this.middleName;
        if(this.lastName == null)
            lName = "";
        else
            lName = this.lastName;
        if(!fName.equals("") && !mName.equals("")){
            outputString = lName + ", " + fName + " " + mName.charAt(0) + ".";
        }else if(!fName.equals("") && mName.equals("")){
            outputString = lName + ", " + fName;
        }else
            outputString = lName;
        return outputString;
    }
    public static String[] personnelArray(){
        String[] personnel;
        PersonnelDAO p = new PersonnelDAO();
        List<Personnel> pList = p.getAll();
        personnel = new String[pList.size()];
        for(int i = 0; i < personnel.length; i++){
            personnel[i] = pList.get(i).toString();
        }
        return personnel;
    }    
}
