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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public interface DAOInterface<T>{
    T getByUid(int uid);
    List<T> getAll();
    boolean add(T t);
    boolean update(T t);
    boolean delete(T t);
    String dbURL = "jdbc:mysql://localhost:3306/bandmaster";
    String USER = "root";
    String PASS = "08260";
    
    default Connection getConnection() throws SQLException{
        Connection connection = DriverManager.getConnection(dbURL, USER, PASS);
        return connection;
    }
}
