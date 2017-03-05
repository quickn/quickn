package cn.json.menu;

public interface PermissionsAdapter {
	public boolean isAllowed(MenuComponent menu, String[] roles);
}
