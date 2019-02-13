package tools.bd;

import java.sql.Connection;
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
		} catch (NamingException e)
		throw new SQLException(jndiname+ " is missing in JNDI! : "+e.getMessage());
		
	}
}
