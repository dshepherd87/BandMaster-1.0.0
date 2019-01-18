/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import dataSource.InstrumentSubTypeDAO;
import java.util.List;

/**
 *
 * @author David Shepherd
 */
public class InstrumentSubType {
    private int subTypeId;
    private String description;
    
    public InstrumentSubType(){
        
    }
    
    /**
     * Constructor method that takes subTypeId and description
     * @param subTypeId
     * @param description 
     */
    public InstrumentSubType(int subTypeId, String description){
        this.subTypeId = subTypeId;
        this.description = description;
    }
    
    public static String[] descriptionArray(){
        String[] subTypes;
        InstrumentSubTypeDAO t = new InstrumentSubTypeDAO();
        List<InstrumentSubType> subTypeList = t.getAll();
        subTypes = new String[subTypeList.size()];
        for(int i = 0; i < subTypes.length; i++){
            subTypes[i] = subTypeList.get(i).getDescription();
        }
        return subTypes;
    }
    public static List<InstrumentSubType> fullList(){
        InstrumentSubTypeDAO s = new InstrumentSubTypeDAO();
        return s.getAll();
    }
    public static InstrumentSubType[] listToArray(){
        InstrumentSubTypeDAO s = new InstrumentSubTypeDAO();
        List<InstrumentSubType> subTypes = s.getAll();
        int length = subTypes.size();
        InstrumentSubType[] subTypeArray = new InstrumentSubType[length];
        for(int i = 0; i < length; i++){
            subTypeArray[i] = subTypes.get(i);
        }
        return subTypeArray;
    }

    @Override
    public String toString(){
        return this.description;
    }
    /**
     * @return the subTypeId
     */
    public int getSubTypeId() {
        return subTypeId;
    }

    /**
     * @param subTypeId the subTypeId to set
     */
    public void setSubTypeId(int subTypeId) {
        this.subTypeId = subTypeId;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
}
