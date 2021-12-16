package com.cheb.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class JspHelper {
	
	public String getPath(String name) {
		return String.format("/WEB-INF/jsp/%s.jsp", name);
	}

}
