class TV {
	private int size;
	TV(int size) {
		this.size = size;
	}
	int getSize() {
		return this.size;
	}
}

class ColorTV extends TV {
	private int color;
	ColorTV(int size, int color) {
		super(size);
		this.color = color;
	}
	
	int getColor() {
		return this.color;
	}
}


public class IPTV extends ColorTV {
	private String IP;
	
	IPTV(int size, int color, String IP) {
		super(size, color);
		this.IP = IP;
	}
	
	void printProperty() {
		System.out.printf("%s 주소에 %d인치 %s컬러\n", this.IP, super.getSize(), super.getColor());
	}
	
	public static void main(String[] args) {
		IPTV myTV = new IPTV(32, 1024, "192.168.0.0.1");
		myTV.printProperty();
	}
}
