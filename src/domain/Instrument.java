/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import dataSource.InstrumentDAO;
import java.util.List;

/**
 *
 * @author David Shepherd
 */
public class Instrument 
{
    private int instrumentId;
    private InstrumentSubType subType;
    private InstrumentType type;
    private String serialNumber;
    private String make;
    private String model;
    private int personnelId;
    
    /**
     * Empty constructor for Instrument
     */
    public Instrument()
    {
        this.instrumentId = 0;
        this.subType = null;
        this.type = null;
        this.serialNumber = null;
        this.make = null;
        this.model = null;
        this.personnelId = 0;
    }

    /**
     * Construct an Instrument object using all values
     * @param instrumentId
     * @param subType
     * @param type
     * @param serialNumber
     * @param make
     * @param model
     * @param personnelId
     */
    public Instrument(int instrumentId, 
                    InstrumentSubType subType,                    
                    InstrumentType type,
                    String serialNumber, 
                    String make,
                    String model,
                    int personnelId){
        this.instrumentId = instrumentId;
        this.subType = subType;
        this.type = type;
        this.serialNumber = serialNumber;
        this.make = make;
        this.model = model;
        this.personnelId = personnelId;       
    }

    /**
     * Constructor including all elements except the instrumentId
     * @param subType
     * @param type
     * @param serialNumber
     * @param make
     * @param model
     * @param personnelId
     */
    public Instrument(InstrumentSubType subType,
                    InstrumentType type,
                    String serialNumber, 
                    String make,
                    String model,
                    int personnelId){
        this.subType = subType;
        this.type = type;
        this.serialNumber = serialNumber;
        this.make = make;
        this.model = model;
        this.personnelId = personnelId;          
    }
    /**
     * Return a string representing the Instrument object 
     * @return 
     */
    @Override
    public String toString(){
        String returnString;
        try{
            returnString =  this.subType.getDescription() + "    " + this.type.getDescription() 
                + "    " + this.getSerialNumber() + "    " + this.getMake() + "    " + 
                this.getModel();            
        }catch(NullPointerException e){
        returnString =  "Error retrieving record";
        }
        return returnString;
    }            

    public static List<Instrument> getList(){
        InstrumentDAO instruments = new InstrumentDAO();
        return instruments.getAll();        
    }
    
    public static List<Instrument> getList(int subTypeId, int typeId, 
            String serialNumber, String make, String model, int personnelId){
        InstrumentDAO instruments = new InstrumentDAO();
        return instruments.getByValue(subTypeId, typeId, serialNumber, make, model, personnelId);
    }
    
    public static Instrument[] getArray(int subTypeId, int typeId, 
        String serialNumber, String make, String model, int personnelId){
        List<Instrument> instrumentList = Instrument.getList(subTypeId, typeId, serialNumber,
        make, model, personnelId);
        int length = instrumentList.size();
        Instrument[] instrumentArray = new Instrument[length];
        for(int i = 0; i < length; i++){
            instrumentArray[i] = instrumentList.get(i);
        }
        return instrumentArray;
    }

    public boolean add(){
        InstrumentDAO i = new InstrumentDAO();
        return i.add(this);
    }
    
    public boolean update(){
        InstrumentDAO i = new InstrumentDAO();
        return i.update(this);
    }
    
    public boolean delete(){
        InstrumentDAO i = new InstrumentDAO();
        return i.delete(this);
    }
    
    /**
     * @return the instrumentId
     */
    public int getInstrumentId() {
        return instrumentId;
    }

    /**
     * @param instrumentId the instrumentId to set
     */
    public void setInstrumentId(int instrumentId) {
        this.instrumentId = instrumentId;
    }

    /**
     * @return the subType
     */
    public InstrumentSubType getSubType() {
        return subType;
    }

    /**
     * @param subType the subType to set
     */
    public void setSubType(InstrumentSubType subType) {
        this.subType = subType;
    }

    /**
     * @return the type
     */
    public InstrumentType getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(InstrumentType type) {
        this.type = type;
    }

    /**
     * @return the serialNumber
     */
    public String getSerialNumber() {
        return serialNumber;
    }

    /**
     * @param serialNumber the serialNumber to set
     */
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    /**
     * @return the make
     */
    public String getMake() {
        return make;
    }

    /**
     * @param make the make to set
     */
    public void setMake(String make) {
        this.make = make;
    }

    /**
     * @return the model
     */
    public String getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * @return the personnel
     */
    public int getPersonnelId() {
        return personnelId;
    }

    /**
     * @param personnelId the personnel to set
     */
    public void setPersonnelId(int personnelId) {
        this.personnelId = personnelId;
    }
        
}

