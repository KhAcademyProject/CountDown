package countDown.model.domain;

public class User {

	private	String userName;
	private	String userPass;
	private	String nickName;
	private	String timeScore;
	
	public User() {}

	public User(String userName, String userPass, String nickName) {
		super();
		this.userName = userName;
		this.userPass = userPass;
		this.nickName = nickName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPass() {
		return userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getTimeScore() {
		return timeScore;
	}

	public void setTimeScore(String timeScore) {
		this.timeScore = timeScore;
	}
	
	
}
 