package Server;
import java.io.*;

public class HttpRequest {
	public String receive(InputStream is) throws IOException {
		ByteArrayOutputStream bao = new ByteArrayOutputStream();
		
		byte buf[] = new byte[1024];
		int cnt;
		
		while ((cnt = is.read(buf)) != -1) {
			bao.write(buf);
			if (cnt < buf.length) break;
		}
		
		return bao.toString();
	}
	
	public String getHost(String msg) {
		String str[] = msg.trim().split("\n");
		if (str.length <= 1) return null;
		
		String hosts[] = str[1].split(":");
		if (hosts.length <= 1) return null;
		if (!hosts[0].equals("Host") && !hosts[0].equals("host"))
			return null;
		
		return hosts[1].trim();
	}
	
	public String getFile(String url) {
		int idx = url.indexOf("?");
		
		return idx < 0 ? url : url.substring(0, idx);
	}
	
	public String[] getParams(String url) {
		int idx = url.indexOf("?");
		
		return idx < 0 ? null : url.substring(idx + 1).split("&");
	}
	
	public String getUrl(String msg) {
		String lines[] = msg.trim().split("\n");
		if (lines.length == 0) return null;
		
		String tokens[] = lines[0].trim().split(" ");
		if (tokens.length < 2) return null;
		if (!tokens[0].equals("GET") && !tokens[0].equals("POST"))
			return null;
		
		return tokens[1].trim();
	}
}
