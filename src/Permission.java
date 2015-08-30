import java.awt.Color;

public class Permission {

	public static final byte USER = 1;
	public static final byte MODERATOR = 2;
	public static final byte ADMINISTRATOR = 3;
	public static final byte CONSOLE = 4;
	private static final Color USER_COLOR = new Color(100, 100, 100);
	private static final Color MODERATOR_COLOR = new Color(0, 0, 255);
	private static final Color ADMINISTRATOR_COLOR = new Color(255, 0, 0);
	private static final Color CONSOLE_COLOR = new Color(0, 0, 255);
	protected byte permission;
	
	public Permission(byte PermissionValue) {
		
		switch (PermissionValue) {
		
			case USER:
				permission = USER;
				break;
			
			case MODERATOR:
				permission = MODERATOR;
				break;
			
			case ADMINISTRATOR:
				permission = ADMINISTRATOR;
				break;
				
			case CONSOLE:
				permission = CONSOLE;
				break;
		
			default:
				break;
				
		}
		
	}
	
	public Color getColorAttribute() {
		
		byte PermissionValue = this.permission;
		
		switch (PermissionValue) {
		
			case USER:
				return USER_COLOR;
			
			case MODERATOR:
				return MODERATOR_COLOR;
			
			case ADMINISTRATOR:
				return ADMINISTRATOR_COLOR;
			
			case CONSOLE:
				return CONSOLE_COLOR;
			
			default:
				return null;
		
		}
				
	}
	
	public byte getByteValue() {
		
		return this.permission;
		
	}
	
	@Override
	public String toString() {
		
		byte PermissionValue = this.permission;
	
		switch (PermissionValue) {
		
			case USER:
				return "User";
			
			case MODERATOR:
				return "Moderator";
			
			case ADMINISTRATOR:
				return "Administrator";
			
			case CONSOLE:
				return "Console";
			
			default:
				return null;
	
		}
		
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (obj instanceof Permission) {
			
			Permission permission2 = (Permission) obj;
			
			if (this.getByteValue() == permission2.getByteValue()) {
				
				return true;
				
			}
			
		}
		
		return false;
		
	}
	
}
