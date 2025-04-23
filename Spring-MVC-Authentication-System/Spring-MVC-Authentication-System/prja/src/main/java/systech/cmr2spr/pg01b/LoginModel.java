package systech.cmr2spr.pg01b;
public class LoginModel {
	private String uid, upwd;
	private boolean isSignIn;
	public LoginModel() {
		uid=null;
		upwd=null;
		isSignIn=false;
	}
	public LoginModel(String uid, String upwd) throws Exception {
		this(uid, upwd, false);
	}
	public LoginModel(String uid, String upwd, boolean isSignIn) throws Exception{
		if(isValidate(uid) == false) {
			throw new Exception("Invalid User ID or Password");
		}
		if(isValidate(upwd) == false) {
			throw new Exception("Invalid User ID or Password");
		}
		this.uid=uid;
		this.upwd=upwd;
		this.isSignIn=isSignIn;
	}
	public String getUid() {
		return uid;
	}
	public String getUpwd() {
		return upwd;
	}
	public boolean getIsSignIn() {
		return isSignIn;
	}
	private boolean isValidate(String s) {
		if(s == null || s.trim().length() < 3) {
			return false;
		}
		return true;
	}
}
