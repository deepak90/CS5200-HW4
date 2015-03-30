package DAO;
import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Movie;


public class MovieDAO {
	DataSource ds;
	public MovieDAO(){
		
		  try {
				Context ctx = new InitialContext();
				ds = (DataSource)ctx.lookup("java:comp/env/jdbc/DBHW4");
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
		
	//Create A Movie DAO
	  public Movie createMovie(Movie movie)
		{
			String sql = "insert into movie values (?,?,?,?);";
			try {
				Connection connection = ds.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql);
				statement.setString(1, movie.getId());
				statement.setString(2, movie.getTitle());
				statement.setString(3, movie.getPosterImage());
				statement.setDate(4, new Date(movie.getReleaseDate().getTime()));
				statement.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
	  
	 
		// Retrieve all Movies
		public List<Movie> readAllMovies()
		{
			List<Movie> movielist = new ArrayList<Movie>();
			String sql = "select * from movie;";
			try {
				Connection connection = ds.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql);
				ResultSet results = statement.executeQuery();
				while(results.next())
				{
					Movie movie = new Movie();
					movie.setId(results.getString("id"));
					movie.setTitle(results.getString("title"));
					movie.setPosterImage(results.getString("posterimage"));
					movie.setReleaseDate(results.getDate("releasedate"));
					movielist.add(movie);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return movielist;
		}
	  
		//Select a Movie from a movie id
	    public Movie readMovie(String id){
			Movie movie = new Movie();
	    	
			String sql = "select * from movie where id = ?";
			try {
				Connection connection = ds.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql);
				statement.setString(1, id);
				ResultSet result = statement.executeQuery();
				if(result.next())
				{
					movie.setId(result.getString("id"));
					movie.setTitle(result.getString("title"));
					movie.setPosterImage(result.getString("posterimage"));
					movie.setReleaseDate(result.getDate("releasedate"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
	    	return movie;
		 }
	  // Update a movie by the Movie id
	    public void updateMovie(String id, Movie movie){
	    	String sql = "update movie set id=?, title=?, posterimage=?, releasedate=? where id=?";
			try {
				Connection connection = ds.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql);
				statement.setString(1, movie.getId());
				statement.setString(2, movie.getTitle());
				statement.setString(3, movie.getPosterImage());
				statement.setDate(4, new Date(movie.getReleaseDate().getTime()));
				statement.setString(5, id);
				statement.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
	    }
	    
	    // Delete a movie given Movie ID
	    public void deleteMovie(String id)
		{
			String sql = "delete from movie where id=?";
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
