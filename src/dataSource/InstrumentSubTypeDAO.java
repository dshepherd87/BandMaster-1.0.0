/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataSource;

/**
 *
 * @author sheph
 */

import domain.InstrumentSubType;
import java.util.List;
import java.util.ArrayList;
import java.sql.*;

public class InstrumentSubTypeDAO implements DAOInterface<InstrumentSubType>{
    /**
     * 
     * @return list of all instrumentSubTypes
     */
    @Override
    public List<InstrumentSubType> getAll(){
        String sql = "SELECT instrumentsubtype_uid, description FROM instrumentsubtype";
        List<InstrumentSubType> subTypes = new ArrayList<>();
        try(Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()){
            while(rs.next()){
                int subTypeId = rs.getInt("instrumentsubtype_uid");
                String subTypeName = rs.getString("description");
                InstrumentSubType s = new InstrumentSubType(subTypeId, subTypeName);
                subTypes.add(s);
            }
            return subTypes;
        }catch(SQLException e){
            System.err.println(e);
            return null;
        }
    }
  
    /**
     * 
     * @param subTypeId
     * @return single matching subType
     */
    @Override
    public InstrumentSubType getByUid(int subTypeId){
        String sql = "SELECT InstrumentSubType_UID, description " +
                    "FROM instrumentSubType " +
                    "WHERE instrumentSubType_UID = ?";
        try(Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, subTypeId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                String description = rs.getString("description");
                InstrumentSubType s = new InstrumentSubType(subTypeId, description);
                rs.close();
                return s;
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
 * @param s
 * @return 
 */
    @Override
    public boolean add(InstrumentSubType s){
        String sql = "INSERT INTO instrumentSubType(description) VALUES(?)";
        try(Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, s.getDescription());
            ps.executeUpdate();
            return true;
        }catch(SQLException e){
            System.err.println(e);
            return false;
        }       
    }
    /**
     * 
     * @param s
     * @return 
     */
   @Override
   public boolean delete(InstrumentSubType s){
       String sql = "DELETE FROM instrumentSubType WHERE instrumentSubType_UID = ?";
        try(Connection connection  = getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, s.getSubTypeId());
            ps.executeUpdate();
            return true;
        }catch(SQLException e){
            System.err.println(e);
            return false;
        }       
   }
   /**
    * 
    * @param s
    * @return 
    */
   @Override
   public boolean update(InstrumentSubType s){
       String sql = "UPDATE instrumentSubType SET description = ? " + 
                    "WHERE instrumentSubType_UID = ?";
        try(Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, s.getDescription());
            ps.setInt(2, s.getSubTypeId());
            ps.executeUpdate();
            return true;
        }catch(SQLException e){
            System.err.println(e);
            return false;
        }       
   }    
}
