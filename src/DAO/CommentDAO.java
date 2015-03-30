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

import model.Comment;


public class CommentDAO {
	DataSource ds;
	public CommentDAO(){
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
	//Create Comment
	public void createComment(Comment newComment){
		String sql = "insert into comment values (?,?,?,?,?);";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, newComment.getId());
			statement.setString(2, newComment.getUsername());
			statement.setString(3, newComment.getMovieId());
			statement.setString(4, newComment.getComment());
			statement.setDate(5, new Date(newComment.getCommentdate().getTime()));
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Read All Comments
	public List<Comment> readAllComments()
	{
		List<Comment> commentlist = new ArrayList<Comment>();
		String sql = "select * from comment;";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet results = statement.executeQuery();
			while(results.next())
			{
				Comment comment = new Comment();
				comment.setId(results.getString("id"));
				comment.setUsername(results.getString("username"));
				comment.setMovieId(results.getString("movieid"));
				comment.setComment(results.getString("comment"));
				comment.setCommentdate(results.getDate("commentdate"));
				commentlist.add(comment);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return commentlist;
	}
	
	//Select a comment from its comment id
    public Comment readCommentForId(String id){
    	Comment comment = new Comment();
    	
		String sql = "select * from comment where id = ?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, id);
			ResultSet result = statement.executeQuery();
			if(result.next())
			{
				comment.setId(result.getString("id"));
				comment.setUsername(result.getString("username"));
				comment.setMovieId(result.getString("movieid"));
				comment.setComment(result.getString("comment"));
				comment.setCommentdate(result.getDate("commentdate"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
    	return comment;
		 
	 }
    
    // Update a comment by it's ID and replace it with a new Comment
    public void updateUser(String id, String newComment){
    	String sql = "update comment set comment=? where id=?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, newComment);
			statement.setString(2, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
    }
    
    // Delete a comment given its ID
    public void deleteComment(String id)
	{
		String sql = "delete from comment where id=?";
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

    // Find all comments given the user who created those comments
    public List<Comment> readAllCommentsForUsername(String username){
		
    	List<Comment> commentlist = new ArrayList<Comment>();
		String sql = "select * from comment where username=?;";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, username);
			ResultSet results = statement.executeQuery();
			while(results.next())
			{
				Comment comment = new Comment();
				comment.setId(results.getString("id"));
				comment.setUsername(results.getString("username"));
				comment.setMovieId(results.getString("movieid"));
				comment.setComment(results.getString("comment"));
				comment.setCommentdate(results.getDate("commentdate"));
				commentlist.add(comment);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return commentlist;
    }
    
    // Find all comments given the movie name it is tagged with
    public List<Comment> readAllCommentsForMovie(String movieid){
		
    	List<Comment> commentlist = new ArrayList<Comment>();
		String sql = "select * from comment where username=?;";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, movieid);
			ResultSet results = statement.executeQuery();
			while(results.next())
			{
				Comment comment = new Comment();
				comment.setId(results.getString("id"));
				comment.setUsername(results.getString("username"));
				comment.setMovieId(results.getString("movieid"));
				comment.setComment(results.getString("comment"));
				comment.setCommentdate(results.getDate("commentdate"));
				commentlist.add(comment);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return commentlist;
    }

}	
