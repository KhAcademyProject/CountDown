package countDown.model.domain;

public class User {

	private String code;
	private	String userId;
	private	String userPass;
	private	String nickName;
	private	String timeScore;
	
	
	public User() {}

	public User(String code,String userId, String userPass, String nickName) {
		super();
		this.code = code;
		this.userId = userId;
		this.userPass = userPass;
		this.nickName = nickName;
	}

	@Override
	public String toString() {
		return code +"," + userId + "," + userPass + "," + nickName +"," +timeScore;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userName) {
		this.userId = userName;
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
 