
public class main {
	public static void main(String args[]) throws ClassNotFoundException {
		String path = "jsp.GetUser";
		Class c = Class.forName(path);
		System.out.println(c.toString());
	}
}
