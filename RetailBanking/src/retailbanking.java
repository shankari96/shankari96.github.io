import java.sql.*;
import org.json.*;
import java.io.*;

public class retailbanking {
	public static void main(String args[])
	{
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Driver loaded.");
			Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "hr1");
			System.out.println("Connection Established.");
			Statement statement = connection.createStatement();
			String sql = "SELECT * FROM TRANSACTIONSNEW";                                        
			JSONObject jsonObject = null;
			JSONArray jsonArray = new JSONArray();
			ResultSet resultSet = statement.executeQuery(sql);
			int ctr = 0;
			while (resultSet.next()) {
				System.out.println(ctr++);
				int noOfColumns = resultSet.getMetaData().getColumnCount();
				jsonObject = new JSONObject();
				for (int i = 0; i < noOfColumns; i++) {
					String columnName = resultSet.getMetaData().getColumnLabel(i + 1).toLowerCase();
					Object columnValue = resultSet.getObject(i + 1);
					// if (columnValue == null) {
					// columnValue = "null";
					// }
					// if (jsonObject.has(columnName)) {
					// columnName += "1";
					// }
					jsonObject.put(columnName, columnValue);
				}
				jsonArray.put(jsonObject);
			}
			// Writing to a file
						File file = new File("JsonFile1.json");
						file.createNewFile();
						FileWriter fileWriter = new FileWriter(file);
						System.out.println("Writing JSON object to file");
						System.out.println("-----------------------");
						System.out.print(jsonArray);

						fileWriter.write(jsonArray.toString());
						fileWriter.flush();
						fileWriter.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
