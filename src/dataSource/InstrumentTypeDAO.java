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

import domain.InstrumentType;
import java.util.List;
import java.util.ArrayList;
import java.sql.*;


public class InstrumentTypeDAO implements DAOInterface<InstrumentType>{
    /**
     * 
     * @return 
     */
    @Override
    public List<InstrumentType> getAll(){
        String sql = "SELECT instrumenttype_uid, description FROM instrumenttype ORDER BY description";
        List<InstrumentType> types = new ArrayList<>();
        try(Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()){
            while(rs.next()){
                int typeId = rs.getInt("instrumenttype_uid");
                String typeName = rs.getString("description");
                InstrumentType i = new InstrumentType(typeId, typeName);
                types.add(i);
            }
            return types;
        }catch(SQLException e){
            System.err.println(e);
            return null;
        }
    }

    /**
     * 
     * @param typeId
     * @return 
     */
    @Override
    public InstrumentType getByUid(int typeId){
        String sql = "SELECT description FROM instrumentType " +
                    "WHERE instrumenttype_uid = ?";
        try(Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, typeId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                String typeName = rs.getString("description");
                InstrumentType t = new InstrumentType(typeId, typeName);
                rs.close();
                return t;
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
     * @param t
     * @return 
     */
    @Override
    public boolean add(InstrumentType t){
        String sql = "INSERT INTO instrumentType(description) VALUES(?)";
        try(Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, t.getDescription());
            ps.executeUpdate();
            return true;
        }catch(SQLException e){
            System.err.println(e);
            return false;
        }       
    }
    
    /**
     * 
     * @param t
     * @return 
     */
   @Override
   public boolean delete(InstrumentType t){
       String sql = "DELETE FROM instrumentType WHERE instrumentType_UID = ?";
        try(Connection connection  = getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, t.getTypeId());
            ps.executeUpdate();
            return true;
        }catch(SQLException e){
            System.err.println(e);
            return false;
        }       
   }
   /**
    * 
    * @param t
    * @return 
    */
   @Override
   public boolean update(InstrumentType t){
       String sql = "UPDATE instrumentType SET description = ? " + 
                    "WHERE instrumentType_UID = ?"; 
        try(Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, t.getDescription());
            ps.setInt(2, t.getTypeId());
            ps.executeUpdate();
            return true;
        }catch(SQLException e){
            System.err.println(e);
            return false;
        }       
   }
   
}
