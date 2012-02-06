package jcf.edu.tweet.model;

public class TweetDTO implements Comparable<TweetDTO> {

	private int id;
	private String register;
	private String tweets;
	private String regDate;

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRegister() {
		return register;
	}

	public void setRegister(String register) {
		this.register = register;
	}

	public String getTweets() {
		return tweets;
	}

	public void setTweets(String tweets) {
		this.tweets = tweets;
	}

	public int compareTo(TweetDTO o) {
		return o.getRegDate().compareTo(regDate);
	}

	@Override
	public String toString() {
		return "ContentDTO [id=" + id + ", register=" + register + ", tweets="
				+ tweets + ", regDate=" + regDate + "]";
	}

}
