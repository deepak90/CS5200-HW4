package model;

public class Cast {
 private String id;
 private String characterName;
 private String ActorId;
 private String MovieId;
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getCharacterName() {
	return characterName;
}
public void setCharacterName(String characterName) {
	this.characterName = characterName;
}
public String getActorId() {
	return ActorId;
}
public void setActorId(String actorId) {
	ActorId = actorId;
}
public String getMovieId() {
	return MovieId;
}
public void setMovieId(String movieId) {
	MovieId = movieId;
}
public Cast(String id, String characterName, String actorId, String movieId) {
	super();
	this.id = id;
	this.characterName = characterName;
	ActorId = actorId;
	MovieId = movieId;
}
public Cast() {
	super();
	// TODO Auto-generated constructor stub
}

}
