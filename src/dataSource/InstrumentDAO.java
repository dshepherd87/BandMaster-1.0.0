/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataSource;

/**
 *
 * @author David Shepherd
 */

import domain.Instrument;
import domain.InstrumentSubType;
import domain.InstrumentType;
import java.util.List;
import java.util.ArrayList;
import java.sql.*;


public class InstrumentDAO implements DAOInterface<Instrument>{ 
    /**
     * 
     * @param instrumentId
     * @return 
     */
    @Override
    public Instrument getByUid(int instrumentId){
        String sql = "SELECT subType_UID, s.description, type_UID, t.description, " 
                +    "serialnumber, make, model, personnel_UID "
                +    "FROM instrument "
                +    "JOIN instrumentSubType AS s ON instrumentSubType_UID = subType_UID "
                +    "JOIN instrumentType AS t ON instrumentType_UID = type_UID "
                +    "WHERE instrument_UID = ?";
        try(Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, instrumentId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                InstrumentSubType subType = new InstrumentSubType(rs.getInt("subType_UID"),
                        rs.getString("description"));
                InstrumentType type = new InstrumentType(rs.getInt("type_UID"),
                        rs.getString("description"));
                String serialNumber = rs.getString("serialnumber");
                String make = rs.getString("make");
                String model = rs.getString("model");
                int personnelId = rs.getInt("personnel_UID");
                Instrument i = new Instrument(instrumentId, subType, type, serialNumber, make, model, personnelId);
                rs.close();
                return i;
            }else{
                rs.close();
                return null;
            }
        }catch(SQLException e){
            System.err.println(e);
            return null;
        }
    }    
    /**
     * 
     * @return list of all instrument records
     */
    @Override
    public List<Instrument> getAll(){
        String sql = "SELECT instrument_UID, subType_UID, s.description, type_UID, "
                +    "t.description, serialnumber, make, model, personnel_UID "
                +    "FROM instrument "
                +    "JOIN instrumentSubType AS s ON instrumentSubType_UID = subType_UID "
                +    "JOIN instrumentType AS t ON instrumentType_UID = type_UID "
                +    "ORDER BY t.description";
        List<Instrument> instruments = new ArrayList<>();
        try(Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()){
            while(rs.next()){
                int instrumentId = rs.getInt("instrument_UID");
                InstrumentSubType subType = new InstrumentSubType(rs.getInt("subType_UID"),
                        rs.getString("description"));
                InstrumentType type = new InstrumentType(rs.getInt("type_UID"),
                        rs.getString("description"));
                String serialNumber = rs.getString("serialnumber");
                String make = rs.getString("make");
                String model = rs.getString("model");
                int personnelId = rs.getInt("personnel_UID");
                
                Instrument i = new Instrument(instrumentId, subType, type, 
                        serialNumber, make, model, personnelId);
                instruments.add(i);
            }
            rs.close();
            ps.close();
            return instruments;
        }catch(SQLException e){
            System.err.println(e);
            return null;
        }
    }
    
    /**
     * 
     * @param subTypeId
     * @param typeId
     * @param serialNumberIn
     * @param makeIn
     * @param modelIn
     * @param personnelIdIn
     * @return 
     */
    public List<Instrument> getByValue(int subTypeId, int typeId, String serialNumberIn,
            String makeIn, String modelIn, int personnelIdIn){
        String sql = "SELECT instrument_uid, subtype_uid, instrumentSubType.description, "
                + "type_uid, instrumentType.description, serialNumber, make, model, personnel_uid "
                + "FROM instrument "
                + "JOIN instrumentSubType ON instrumentSubType_uid = subType_uid "
                + "JOIN instrumentType ON instrumentType_uid = type_uid "
                + "WHERE (subType_uid = ? OR ? = 0) "
                + "AND (type_uid = ? OR ? = 0) "
                + "AND (serialNumber = ? OR ? = '') "
                + "AND (make = ? OR ? = '') "
                + "AND (model = ? OR ? = '') "
                + "AND (personnel_uid = ? OR ? = 0)"
                + "ORDER BY instrumentType.description";
        List<Instrument> instruments = new ArrayList<>();
        try(Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)){
				ps.setInt(1, subTypeId);
                                ps.setInt(2, subTypeId);
				ps.setInt(3, typeId);
                                ps.setInt(4, typeId);
				ps.setString(5, serialNumberIn);
                                ps.setString(6, serialNumberIn);
				ps.setString(7, makeIn);
                                ps.setString(8, makeIn);
				ps.setString(9, modelIn);
                                ps.setString(10, modelIn);
				ps.setInt(11, personnelIdIn);
                                ps.setInt(12, personnelIdIn);
                ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int instrumentId = rs.getInt("instrument_UID");
                InstrumentSubType subType = new InstrumentSubType(
                        rs.getInt("subType_UID"), rs.getString("instrumentsubtype.description"));
                InstrumentType type = new InstrumentType(
                        rs.getInt("type_UID"), rs.getString("instrumenttype.description"));
                String serialNumber = rs.getString("serialnumber");
                String makeOut = rs.getString("make");
                String modelOut = rs.getString("model");
                int personnelId = rs.getInt("personnel_UID");
                
                Instrument i = new Instrument(instrumentId, subType, type,
                serialNumber, makeOut, modelOut, personnelId);
                instruments.add(i);
            }
            rs.close();
            ps.close();
            return instruments;
        }catch(SQLException e){
            System.err.println(e);
            return null;
        }
    }
    
    /**
     * 
     * @param instrument
     * @return 
     */
    @Override
    public boolean add(Instrument instrument){
        String sql = "INSERT INTO instrument(subtype_uid, type_uid, "
                + "serialNumber, make, model, personnel_UID) " 
                + "VALUES (?, ?, ?, ?, ?, ?)"; 
        try(Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, instrument.getSubType().getSubTypeId());
            ps.setInt(2, instrument.getType().getTypeId());
            ps.setString(3, instrument.getSerialNumber());
            ps.setString(4, instrument.getMake());
            ps.setString(5, instrument.getModel());
            if(instrument.getPersonnelId() == 0)
                ps.setString(6, null);
            else
                ps.setInt(6, instrument.getPersonnelId());           
            ps.executeUpdate();
            return true;
        }catch(SQLException e){
            System.err.println(e);
            return false;
        }       
    }
    /**
     * Delete an instrument from the database
     * @param instrument
     * @return 
     */
    @Override
    public boolean delete(Instrument instrument){
        String sql = "DELETE FROM instrument WHERE instrument_UID = ?";
        try(Connection connection  = getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, instrument.getInstrumentId());
            ps.executeUpdate();
            return true;
        }catch(SQLException e){
            System.err.println(e);
            return false;
        }
    }
    
    /**
     * Update an existing i record in the database
     * @param i
     * @return 
     */
    @Override
    public boolean update(Instrument i){
        String sql = "UPDATE instrument SET "
                + "subType_UID = ?, "
                + "type_UID = ?, "
                + "serialNumber = ?, "
                + "make =  ?, "
                + "model = ?, "
                + "personnel_UID = ? "
                + "WHERE instrument_UID = ?"; 
        try(Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, i.getSubType().getSubTypeId());
            ps.setInt(2, i.getType().getTypeId());
            ps.setString(3, i.getSerialNumber());
            ps.setString(4, i.getMake());
            ps.setString(5, i.getModel());
            if(i.getPersonnelId() == 0)
                ps.setString(6, null);
            else
                ps.setInt(6, i.getPersonnelId());
            ps.setInt(7, i.getInstrumentId());
            ps.executeUpdate();
            return true;
        }catch(SQLException e){
            System.err.println(e);
            return false;
        }
    }
}
