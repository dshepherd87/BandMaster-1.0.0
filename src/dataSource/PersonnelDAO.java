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

import domain.Personnel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonnelDAO implements DAOInterface<Personnel>{
    
    /**
     * 
     * @param personnelId
     * @return 
     */
    @Override
    public Personnel getByUid(int personnelId){
        String sql = "SELECT personnel_uid, realworld_id, firstName, "
                + "middleName, lastName, title "
                + "FROM personnel "
                + "ORDER BY lastName";     
        try(Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, personnelId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                String realWorldId = rs.getString("realWorld_id");
                String firstName = rs.getString("firstName");
                String middleName = rs.getString("middleName");
                String lastName = rs.getString("lastName");
                String title = rs.getString("title");
                Personnel p = new Personnel(personnelId, realWorldId, firstName,
                middleName, lastName, title);
                rs.close();
                return p;
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
 * @return 
 */    
    @Override
    public List<Personnel> getAll(){
        String sql = "SELECT personnel_uid, realworld_id, firstName, "
                + "middleName, lastName, title "
                + "FROM personnel "
                + "ORDER BY lastName";               
        List<Personnel> personnel = new ArrayList<>();
        try(Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()){
            while(rs.next()){
                int personnelId = rs.getInt("personnel_uid");
                String realWorldId = rs.getString("realWorld_id");
                String firstName = rs.getString("firstName");
                String middleName = rs.getString("middleName");
                String lastName = rs.getString("lastName");
                String title = rs.getString("title");
                
                Personnel p = new Personnel(personnelId, realWorldId, firstName,
                middleName, lastName, title);
                personnel.add(p);
            }
            rs.close();
            ps.close();
            return personnel;
        }catch(SQLException e){
            System.err.println(e);
            return null;
        }
    }
    
    public List<Personnel> getMatchingList(Personnel p){
        String sql = "SELECT personnel_uid, realworld_id, firstName, "
                + "middleName, lastName, title "
                + "FROM personnel "
                + "WHERE (realworld_id = ? OR ? = '') "
                + "AND (firstName = ? OR ? = '') "
                + "AND (middleName = ? OR ? = '') "
                + "AND (lastName = ? OR ? = '') "
                + "AND (title = ? OR ? = '') "
                + "ORDER BY lastName";               
        List<Personnel> personnel = new ArrayList<>();
        try(Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)){
                ps.setString(1, p.getRealWorldId());
                ps.setString(2, p.getRealWorldId());
                ps.setString(3, p.getFirstName());
                ps.setString(4, p.getFirstName());
                ps.setString(5, p.getMiddleName());
                ps.setString(6, p.getMiddleName());
                ps.setString(7, p.getLastName());
                ps.setString(8, p.getLastName());
                ps.setString(9, p.getTitle());
                ps.setString(10, p.getTitle());
                ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int personnelId = rs.getInt("personnel_uid");
                String realWorldId = rs.getString("realWorld_id");
                String firstName = rs.getString("firstName");
                String middleName = rs.getString("middleName");
                String lastName = rs.getString("lastName");
                String title = rs.getString("title");
                
                Personnel q = new Personnel(personnelId, realWorldId, firstName,
                middleName, lastName, title);
                personnel.add(q);
            }
            rs.close();
            ps.close();
            return personnel;
        }catch(SQLException e){
            System.err.println(e);
            return null;
        }        
    }
/**
 * 
 * @param p
 * @return 
 */    
    @Override
    public boolean add(Personnel p){
        String sql = "INSERT INTO personnel (realWorld_id, firstName, "
                + "middleName, lastName, title) "
                + "VALUES(?, ?, ?, ?, ?)";
        try(Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, p.getRealWorldId());
            ps.setString(2, p.getFirstName());
            ps.setString(3, p.getMiddleName());
            ps.setString(4, p.getLastName());
            ps.setString(5, p.getTitle());
            ps.executeUpdate();
            return true;
        }catch(SQLException e){
            System.err.println(e);
            return false;
        }       
    }
    /**
     * 
     * @param p
     * @return 
     */
    @Override
    public boolean delete(Personnel p){
        String sql = "DELETE FROM personnel WHERE personnel_uid = ?;";
        try(Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, p.getPersonnelId());
            ps.executeUpdate();
            return true;
        }catch(SQLException e){
            System.err.println(e);
            return false;
        }                 
    }
    /**
     * 
     * @param p
     * @return 
     */
    @Override
    public boolean update(Personnel p){
        String sql = "UPDATE personnel SET realworld_id = ?, "
                + "firstName = ?, "
                + "middleName = ?, "
                + "lastName = ?, "
                + "title = ? "
                + "WHERE personnel_uid = ?";
        try(Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, p.getRealWorldId());
            ps.setString(2, p.getFirstName());
            ps.setString(3, p.getMiddleName());
            ps.setString(4, p.getLastName());
            ps.setString(5, p.getTitle());
            ps.setInt(6, p.getPersonnelId());
            ps.executeUpdate();
            return true;
        }catch(SQLException e){
            System.err.println(e);
            return false;
        }               
    }
}
