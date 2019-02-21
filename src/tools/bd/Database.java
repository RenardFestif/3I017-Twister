package tools.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class Database {
	private DataSource dataSource;

	public Database(String jndiname) throws SQLException {
		try {
			dataSource = (DataSource) new InitialContext().lookup("java:comp/env/"+
					jndiname);
		} catch (NamingException e) {
			throw new SQLException(jndiname+ " is missing in JNDI! : "+e.getMessage());
		}
	}

	public Connection getConnection() throws SQLException{
		return dataSource.getConnection();
	}

	public static Connection getMySQLConnection() throws SQLException{
		Database database = null; 
		//Si ca bug c'est ici !
		if(DBStatic.pooling == true) {
			return(DriverManager.getConnection("jdbc:mysql://" + DBStatic.host + "/" + DBStatic.bd, DBStatic.user, DBStatic.password));
		}else {
			if (database == null) {
				database = new Database("jdbc/db");
			}
			return database.getConnection();
		}

	}
}
