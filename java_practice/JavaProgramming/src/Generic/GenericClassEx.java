package Generic;

import java.util.Stack;

class GenericStack<T> {
	int size;
	int tos;
	Object[] stack;	// 스택에 요소를 저장할 공간 배열
	
	public GenericStack(int size) {
		this.size = size;
		this.tos = 0;
		stack = new Object[size];  // 제네릭 매개변수로 객체를 생성할 수 없으므로 Object 배열 이용
	}
	
	public void push(T item) {
		if (tos == 10) return;	// 스택이 가득 참
		stack[tos] = item;
		tos++;
	}
	
	@SuppressWarnings("unchecked")
	public T pop() {
		if (tos == 0) return null;	// 스택이 비어 있음
		
		tos--;
		return (T)stack[tos]; 		// 타입 매개변수 타입으로 다운캐스팅
	}
	
	public int getLength() {
		return this.size;
	}
}

class GenericMethodEx {
	// 타입 매개변수는 메소드의 리턴 타입 앞에 선언한다.
	static <T> void toStack(T[] a, GenericStack<T> gs) {
		for (int i = 0; i < a.length; i++)
			gs.push(a[i]);
	}
	
	static <V> GenericStack<V> reverse(GenericStack<V> a) {
		GenericStack<V> s = new GenericStack<V>(a.getLength());
		
		while (true) {
			V temp = a.pop();
			if (temp == null) break;
			else s.push(temp);
		}
		
		return s;
	}
}

public class GenericClassEx {
	public static void main(String[] args) {
		var gs = new GenericStack<Double>(5);
		
		for (int i = 0; i < gs.getLength(); i++)
			gs.push(Double.valueOf(i));
		
		gs = GenericMethodEx.reverse(gs);
		for (int i = 0; i < gs.getLength(); i++)
			System.out.println(gs.pop());
		
		
		var st = new Stack<Integer>();
		st.push(1);
		System.out.println(st.pop());
	}
}