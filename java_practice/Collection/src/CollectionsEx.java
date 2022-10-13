import java.util.*;

public class CollectionsEx {
	private static void printList(LinkedList<String> l) {
		Iterator<String> it = l.iterator();
		while (it.hasNext()) {
			String e = it.next();
			String separator = it.hasNext() ? " -> " : "\n";
			
			System.out.print(e + separator);
		}
	}
	
	public static void main(String[] args) {
		var myList = new LinkedList<String>();
		myList.add("트랜스포머");
		myList.add("스타워즈");
		myList.add("매트릭스");
		myList.add(0, "터미네이터");
		myList.add(2, "아바타");
		
		Collections.sort(myList);
		printList(myList);
		
		Collections.reverse(myList);
		printList(myList);
		
		int index = Collections.binarySearch(myList, "아바타");
		System.out.printf("아바타는 %d번째 요소입니다.", index + 1);
	}
}
