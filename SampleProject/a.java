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
            if (temp == null)
                break;
            else
                s.push(temp);
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
    }
}