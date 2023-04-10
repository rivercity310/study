package http;

import Server.Session;

abstract public class JspService {
	abstract public String getHtml(Session ses, String host, String params[]);
	
	public String getParamValue(String params[], String name) {
		for (String param : params) {
			String tokens[] = param.split("=");
			if (tokens.length == 2 && tokens[0].equals(name)) return tokens[1];
		}

		return null;
	}
}
