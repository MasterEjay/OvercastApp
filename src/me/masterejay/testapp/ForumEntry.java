package me.masterejay.testapp;

/**
 * @author MasterEjay
 */
public class ForumEntry {

	String poster;
	String topicName;
	String replies;
	String views;
	String link;

	public ForumEntry(String poster,String topicName){
		this.poster=poster;
		this.topicName=topicName;
	}

	public String getLink(){
		return link;
	}

	public ForumEntry(String poster, String topicName, String replies, String views, String link){
		this.poster = poster;
		this.topicName = topicName;
		this.replies = replies;
		this.views = views;
		this.link = link;
	}

	public String getPoster(){
		return poster;
	}

	public String getTopicName(){
		return topicName;
	}

	public String getReplies(){
		return replies;
	}

	public String getViews(){
		return views;
	}
}
