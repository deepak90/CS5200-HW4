package DAO;

import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Cast;


public class CastDAO {
	DataSource ds;
	public CastDAO(){
		// TODO Auto-generated constructor stub
		  try {
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/DBHW4");
			System.out.println(ds);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Create Cast
	public void createComment(Cast newCast){
		String sql = "insert into comment values (?,?,?,?);";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, newCast.getId());
			statement.setString(2, newCast.getCharacterName());
			statement.setString(3, newCast.getActorId());
			statement.setString(4, newCast.getMovieId());
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Read All Cast
	public List<Cast> readAllCast()
	{
		List<Cast> castlist = new ArrayList<Cast>();
		String sql = "select * from cast;";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet results = statement.executeQuery();
			while(results.next())
			{
				Cast cast = new Cast();
				cast.setId(results.getString("id"));
				cast.setCharacterName(results.getString("charactername"));
				cast.setActorId(results.getString("actorid"));
				cast.setMovieId(results.getString("movieid"));
				castlist.add(cast);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return castlist;
	}
	
	//Select a cast from its cast id
    public Cast readCastForId(String id){
    	Cast cast = new Cast();
    	
		String sql = "select * from cast where id = ?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, id);
			ResultSet result = statement.executeQuery();
			if(result.next())
			{
				cast.setId(result.getString("id"));
				cast.setCharacterName(result.getString("charactername"));
				cast.setActorId(result.getString("actorid"));
				cast.setMovieId(result.getString("movieid"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
    	return cast;
		 
	 }
    
    // Update a cast by it's ID and replace it with a new Cast
    public void updateUser(String id, Cast newCast){
    	String sql = "update cast set id=?, charactername=?, actorid=?, movieid=? where id=?";
    	Cast cast=new Cast();
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, id);
			statement.setString(2, cast.getCharacterName());
			statement.setString(3, cast.getActorId());
			statement.setString(4, cast.getMovieId());
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
    }
    
    // Delete a cast given its ID
    public void deleteCast(String id)
	{
		String sql = "delete from cast where id=?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

    // Find all casts given the Actor
    public List<Cast> readAllCastForActor(String actor){
		
    	List<Cast> castlist = new ArrayList<Cast>();
		String sql = "select * from cast where username=?;";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, actor);
			ResultSet results = statement.executeQuery();
			while(results.next())
			{
				Cast cast = new Cast();
				cast.setId(results.getString("id"));
				cast.setCharacterName(results.getString("charactername"));
				cast.setActorId(results.getString("actorid"));
				cast.setMovieId(results.getString("movieid"));
				castlist.add(cast);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return castlist;
    }
    
    // Find all casts given the movie name it is tagged with
    public List<Cast> readAllCastForMovie(String movieid){
		
    	List<Cast> castlist = new ArrayList<Cast>();
		String sql = "select * from cast where username=?;";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, movieid);
			ResultSet results = statement.executeQuery();
			while(results.next())
			{
				Cast cast = new Cast();
				cast.setId(results.getString("id"));
				cast.setCharacterName(results.getString("charactername"));
				cast.setActorId(results.getString("actorid"));
				cast.setMovieId(results.getString("movieid"));
				castlist.add(cast);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return castlist;
    }
}
