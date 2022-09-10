/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.citricos.dao;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import org.springframework.stereotype.Service;
@Service
public class Conexion { 
	public Connection get_connection() {
		Connection con=null;
		try
		{ 
			String urlJDBC="jdbc:mysql://localhost:3306/syscadillo?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			String password="Azul2018*";
			String userName="root";
			String driverClass="com.mysql.jdbc.Driver";
			Class.forName(driverClass);
			con =  DriverManager.getConnection(urlJDBC, userName, password);
			return con;
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}		
		return null;
	}
}

