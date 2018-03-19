package seleniumProject;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Connection;

public class mysql {
	static Connection connectionDb = null;

	public Connection dbConnection(String dbAddress, String dbPort, String dbUserName, String dbPassword, String dbName)
			throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Connection");
		Connection connectionDb = (Connection) DriverManager
				.getConnection("jdbc:mysql://" + dbAddress + ":" + dbPort + "/" + dbName, dbUserName, dbPassword);
		return connectionDb;
	}

	public int carDbCount(String carId, Object connectionDb) throws SQLException, ClassNotFoundException {

		String query = "select count(*) from carlist where carId='" + carId + "';";
		Statement stm = (Statement) ((Connection) connectionDb).createStatement();
		ResultSet result = stm.executeQuery(query);
		result.next();
		int carCount = Integer.parseInt(result.getObject(1).toString());
		stm.close();
		result.close();
		return carCount;
	}

	public int carDbSave(String carId, Object connectionDb) throws SQLException, ClassNotFoundException {

		String query = "insert into carlist(carId) value ('" + carId + "')";
		Statement stm = (Statement) ((Connection) connectionDb).createStatement();
		int result = stm.executeUpdate(query);
		stm.close();
		return result;
	}
}
