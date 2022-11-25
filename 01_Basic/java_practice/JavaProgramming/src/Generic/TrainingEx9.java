package Generic;

import java.util.Vector;

interface IStack<T> {
	T pop();
	boolean push(T ob);
}

class MyStack<T> implements IStack<T> {
	private Vector<T> v;
	private int size;
	
	public MyStack() {
		v = new Vector<T>();
		size = 0;
	}
	
	public T pop() {
		if (size > 0) return v.get(--this.size);
		return null;
	}
	
	public boolean push(T ob) {
		if (this.size < 10) {
			v.add(this.size++, ob);
			return true;
		}
		
		return false;
	}
}

public class TrainingEx9 {
	private void run() {
		IStack<Integer> stack = new MyStack<Integer>();
		for (int i = 0; i < 10; i++) stack.push(i);
		
		while (true) {
			Integer n = stack.pop();
			if (n == null) break;
			System.out.print(n + " ");
		}
	}
	
	public static void main(String[] args) {
		var app = new TrainingEx9();
		app.run();
	}
}
