package DAO;
import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import model.Actor;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ActorDAO {
	DataSource ds;
	public ActorDAO(){
		try {
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/DBHW4");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Create A Actor DAO
	  public Actor createActor(Actor actor)
		{
			String sql = "insert into actor values (?,?,?,?);";
			try {
				Connection connection = ds.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql);
				statement.setString(1, actor.getId());
				statement.setString(2, actor.getFirstName());
				statement.setString(3, actor.getLastName());
				statement.setDate(4, new Date(actor.getDateOfBirth().getTime()));
				statement.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
	  
	 
		// Retrieve all Actors
		public List<Actor> readAllActors()
		{
			List<Actor> actorlist = new ArrayList<Actor>();
			String sql = "select * from actor;";
			try {
				Connection connection = ds.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql);
				ResultSet results = statement.executeQuery();
				while(results.next())
				{
					Actor actor = new Actor();
					actor.setId(results.getString("id"));
					actor.setFirstName(results.getString("firstName"));
					actor.setLastName(results.getString("lastName"));
					actor.setDateOfBirth(results.getDate("date"));
					actorlist.add(actor);
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return actorlist;
		}
	  
		//Select a Actor from a actor id
	    public Actor readActor(String id){
			Actor actor = new Actor();
	    	
			String sql = "select * from actor where id = ?";
			try {
				Connection connection = ds.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql);
				statement.setString(1, id);
				ResultSet result = statement.executeQuery();
				if(result.next())
				{
					actor.setId(result.getString("id"));
					actor.setFirstName(result.getString("firstName"));
					actor.setLastName(result.getString("lastName"));
					actor.setDateOfBirth(result.getDate("date"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
	    	return actor;
			 
		 }
	  // Update a actor by the Actor id
	    public void updateActor(String id, Actor actor){
	    	String sql = "update actor set id=?, firstname=?, lastname=?, dateofbirth=? where id=?";
			try {
				Connection connection = ds.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql);
				statement.setString(1, actor.getId());
				statement.setString(2, actor.getFirstName());
				statement.setString(3, actor.getLastName());
				statement.setDate(4, new Date(actor.getDateOfBirth().getTime()));;
				statement.setString(5, id);
				statement.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
	    }
	    
	    // Delete a actor given Actor ID
	    public void deleteActor(String id)
		{
			String sql = "delete from actor where id=?";
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


}
