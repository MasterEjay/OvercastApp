package me.masterejay.testapp;

/**
 * @author MasterEjay
 */
public class ForumEntry {

	String poster;
	String topicName;
	String replies;
	String views;

	public ForumEntry(String poster,String topicName,String replies,String views){
		this.poster=poster;
		this.topicName=topicName;
		this.replies=replies;
		this.views=views;
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
