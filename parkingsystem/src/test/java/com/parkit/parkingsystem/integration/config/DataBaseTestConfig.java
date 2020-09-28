package com.parkit.parkingsystem.integration.config;

import com.parkit.parkingsystem.config.DataBaseConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DataBaseTestConfig extends DataBaseConfig {

    private static final Logger logger = LogManager.getLogger("DataBaseTestConfig");
    
    
    public Connection getConnection() {
    	Properties props = new Properties();
    	FileInputStream fileInputStream = null;
    	Connection con = null;
    	try {
    		fileInputStream = new FileInputStream("config.properties");
    		props.load(fileInputStream);
    		
    		//Driver class
    		Class.forName(props.getProperty("sgbd.driver"));
    		
    		
    		//Connection
    		con = DriverManager.getConnection(props.getProperty("sgbd.url"),
    				props.getProperty("sgbd.login"),
    				props.getProperty("sgbd.password"));
    	}catch(IOException | ClassNotFoundException | SQLException e) {
    		e.printStackTrace();
    	}
    	return con;
    }

    public void closeConnection(Connection con){
        if(con!=null){
            try {
                con.close();
                logger.info("Closing DB connection");
            } catch (SQLException e) {
                logger.error("Error while closing connection",e);
            }
        }
    }

    public void closePreparedStatement(PreparedStatement ps) {
        if(ps!=null){
            try {
                ps.close();
                logger.info("Closing Prepared Statement");
            } catch (SQLException e) {
                logger.error("Error while closing prepared statement",e);
            }
        }
    }

    public void closeResultSet(ResultSet rs) {
        if(rs!=null){
            try {
                rs.close();
                logger.info("Closing Result Set");
            } catch (SQLException e) {
                logger.error("Error while closing result set",e);
            }
        }
    }
}
