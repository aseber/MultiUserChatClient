public class User {

	protected String username;
	protected Permission permission;
	
	public User(String username, Permission permission) {
		
		this.username = new String(username);
		this.permission = permission;
		
	}
	
	public Permission getPermission() {
		
		return permission;
		
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (obj instanceof User) {
			
			User user2 = (User) obj;
			
			if (this.username.equals(user2.username) && this.permission.equals(user2.permission)) {
				
				return true;
				
			}
			
		}
		
		return false;
		
	}
	
	@Override
	public String toString() {
		
		if (username != null && permission != null) {
			
			return username + " | " + permission.toString();
			
		}
		
		return null;
		
	}
	
}
