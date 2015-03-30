package model;

import java.util.Date;

public class Comment {
private String Id;
private String username;
private String movieId;
private String comment;
private Date commentdate;
public String getId() {
	return Id;
}
public void setId(String id) {
	Id = id;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getMovieId() {
	return movieId;
}
public void setMovieId(String movieId) {
	this.movieId = movieId;
}
public String getComment() {
	return comment;
}
public void setComment(String comment) {
	this.comment = comment;
}
public Date getCommentdate() {
	return commentdate;
}
public void setCommentdate(Date commentdate) {
	this.commentdate = commentdate;
}
public Comment(String id, String username, String movieId, String comment,
		Date commentdate) {
	super();
	Id = id;
	this.username = username;
	this.movieId = movieId;
	this.comment = comment;
	this.commentdate = commentdate;
}
public Comment() {
	super();
	// TODO Auto-generated constructor stub
}

}
