package me.masterejay.testapp;

/**
 * @author MasterEjay
 */
public class ForumEntry {

	String poster;
	String date;
	String topicName;
	String replies;
	String views;
	String link;
	int index;

	public ForumEntry(String poster,String topicName){
		this.poster=poster;
		this.topicName=topicName;
	}

	public ForumEntry(String poster,String date,String topicName,String link){
		this.poster=poster;
		this.date=date;
		this.topicName=topicName;
		this.link=link;
	}

	public String getLink(){
		return link;
	}

	public void setIndex(int index){
		this.index=index;
	}

	public String getDate(){
		return date;
	}

	public ForumEntry(String poster,String topicName,String link,String replies, String date){
		this.poster=poster;
		this.topicName=topicName;

		this.link=link;
		this.replies=replies;
		this.date = date;
	}

	public int getIndex(){
		return index;

	}

	public ForumEntry(String poster, String topicName, String replies, String views, String link, int index){
		this.poster = poster;
		this.topicName = topicName;
		this.replies = replies;
		this.views = views;
		this.link = link;

		this.index = index;
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
