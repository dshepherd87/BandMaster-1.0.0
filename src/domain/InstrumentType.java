/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import dataSource.InstrumentTypeDAO;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author David Shepherd
 */
public class InstrumentType {
    private int typeId;
    private String description;
    
    public InstrumentType(){
        
    }
    
    /**
     * Constructor that assigns values for both the typeId and the description
     * @param typeId
     * @param description 
     */
    public InstrumentType(int typeId, String description){
        this.typeId = typeId;
        this.description = description;
    }

    /**
     * Constructor that assigns value only for the description
     * @param description 
     */
    public InstrumentType(String description){
        this.description = description;
    }
    
    public static List<InstrumentType> fullList(){
        InstrumentTypeDAO t = new InstrumentTypeDAO();
        return t.getAll();
    }
    
    @Override
    public String toString(){
        return this.description;
    }
    
    /**
     * @return the typeID
     */
    public int getTypeId() {
        return typeId;
    }

    /**
     * @param typeID the typeID to set
     */
    public void setTypeId(int typeId) {
        this.typeId = typeId;
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
    
    public static InstrumentType[] listToArray(){
        InstrumentTypeDAO t = new InstrumentTypeDAO();
        List<InstrumentType> typeList = t.getAll();
        int length = typeList.size();
        InstrumentType[] types = new InstrumentType[length];
        for(int i = 0; i < length; i++){
            types[i] = typeList.get(i);
        }
        return types;
    }
    
}
