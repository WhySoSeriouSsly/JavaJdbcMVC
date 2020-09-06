package models;

public class UserModel {

	private String userName;
	private String password;
	public String getUserName() {
		return userName;
	}
	public UserModel() {
		
	}
	public UserModel(String userName,String password) {
		this.userName=userName;
		this.password=password;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUser() {
		return getUserName().toString()+" "+getPassword().toString();
	}
}
