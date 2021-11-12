package servlets;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookiesUtil {
	public static Cookie getCookies(String name, HttpServletRequest request, HttpServletResponse response) {
		Cookie[] list = request.getCookies();
		for(Cookie ck : list) {
			if(ck.getName().equals(name)) {
				return ck;
			}
		}
		
		Cookie newCookie = new Cookie(name, "");
		response.addCookie(newCookie);
		return newCookie;
	}
	
	public static String getPropertieValue(String cartString, String name) {
		String[] properties = cartString.split("/");
		
		for(int i = 0; i < properties.length; i++) {
			if(properties[i].indexOf(name) == 0) {
				String value = properties[i].split("=")[1];
				return value;
			}
		}
		return null;
	}
}
