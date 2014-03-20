package web.carrito.ernest.model;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import com.mysql.jdbc.Connection;

public class DatabaseController {

	private Connection connect = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public CD selectCD(int id) throws Exception{
		
		CD cd = new CD();
		try {
		      // this will load the MySQL driver, each DB has its own driver
		      Class.forName("com.mysql.jdbc.Driver");
		      // setup the connection with the DB.
		      connect = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/webCarrito?"+ "user=root&password=3espi3");

		      // statements allow to issue SQL queries to the database
		      String query = "SELECT id,album,artist,country,price FROM CDS WHERE id="+id;
		      preparedStatement = connect.prepareStatement(query);
		      //preparedStatement.setInt(1, id );
		      resultSet = preparedStatement.executeQuery(query);
		      
		      //Tractem el resultat de la query i creem el nou objecte CD
		      
		      while (resultSet.next()) {
			      cd.setId(resultSet.getInt("id"));
			      cd.setAlbum(resultSet.getString("album"));	     
			      cd.setArtist(resultSet.getString("artist"));	     
			      cd.setCountry(resultSet.getString("country"));	     
			      cd.setPrice(resultSet.getFloat("price"));	
		      }
		     		      
		    } catch (Exception e) {
		      throw e;
		    } finally {
		       resultSet.close();
		       preparedStatement.close();
		       connect.close();
		    }			   
		return cd;
	}
		 	 		  
			

	public Vector selectCDs() throws Exception{
		
		Vector cds = new Vector(); 		
		try {
		      // this will load the MySQL driver, each DB has its own driver
		      Class.forName("com.mysql.jdbc.Driver");
		      // setup the connection with the DB.
		      connect = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/webCarrito?"+ "user=root&password=3espi3");

		      // statements allow to issue SQL queries to the database
		      String query = "SELECT id,album,artist,country,price FROM CDS";
		      preparedStatement = connect.prepareStatement(query);
		      resultSet = preparedStatement.executeQuery(query);
		      
		      //Tractem el resultat de la query i creem el nou objecte CD
		      
		      while (resultSet.next()) {
		    	  CD cd = new CD();
			      cd.setId(resultSet.getInt("id"));
			      cd.setAlbum(resultSet.getString("album"));	     
			      cd.setArtist(resultSet.getString("artist"));	     
			      cd.setCountry(resultSet.getString("country"));	     
			      cd.setPrice(resultSet.getFloat("price"));	
			      cds.addElement(cd);
		      }
		     		      
		    } catch (Exception e) {
		      throw e;
		    } finally {
		       resultSet.close();
		       preparedStatement.close();
		       connect.close();
		    }			   
		return cds;
	} 
	 
}
