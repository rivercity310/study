// List를 구성하는 Node 클래스
class ListNode {
	private String data;   // 데이터 저장 변수
	public ListNode link;  // 다른 노드를 참조할 링크 노드
	
	public ListNode() {
		this(null, null);
	}
	
	public ListNode(String data) {
		this(data, null);
	}
	
	public ListNode(String data, ListNode link) {
		this.data = data;
		this.link = link;
	}
	
	public String getData() {
		return this.data;
	}
}

public class LinkedList {
	private ListNode head;    // head 노드
	
	public LinkedList() {
		head = null;         // head 노드 초기화
	}
	
	// Node 삽입 (중간에 삽입)
	public void insertNode(ListNode preNode, String data) {
		ListNode newNode = new ListNode(data);
		
		// preNode -> newNode -> 기존 preNode의 다음 노드로 구성되게 함
		newNode.link = preNode.link;
		preNode.link = newNode;
	}
	
	// Node 삽입 (마지막에 삽입)
	public void insertNode(String data) {
		ListNode newNode = new ListNode(data);
		if (head == null) this.head = newNode;
		else {
			ListNode tempNode = head;
			
			while (tempNode.link != null) 
				tempNode = tempNode.link;
			
			tempNode.link = newNode;
		}
	}
	
	// Node 삭제 (중간 노드 삭제)
	public void deleteNode(String data) {
		ListNode preNode = head;
		ListNode tempNode = head.link;
		
		if (data.equals(preNode.getData())) {
			head = preNode.link;
			preNode.link = null;
		} else {
			while (tempNode != null) {
				if (data.equals(tempNode.getData())) {
					if (tempNode.link == null) {
						preNode.link = null;
					} else {
						preNode.link = tempNode.link;
						tempNode.link = null;
					}
					break;
				} else {
					preNode = tempNode;
					tempNode = tempNode.link;
				} 
			}
		}
	}
	
	// Node 삭제 (마지막 노드 삭제)
	public void deleteNode() {
		ListNode preNode;
		ListNode tempNode;
		
		if (head == null) return;
		
		// head 노드의 link가 null인 경우 -> 노드가 1개 남았을 경우
		if (head.link == null) head = null;
		else {
			preNode = head;
			tempNode = head.link;
			
			while (tempNode.link != null) {
				preNode = tempNode;
				tempNode = tempNode.link;
			}
			
			preNode.link = null;
		}
	}
	
	// Node 탐색
	public ListNode searchNode(String data) {
		ListNode tempNode = this.head;
		
		while (tempNode != null) {
			if (data.equals(tempNode.getData())) return tempNode;
			else tempNode = tempNode.link;
		}
		
		return tempNode;
	}
	
	// 리스트의 노드를 역순으로 구성
	public void reverseList() {
		ListNode nextNode = head;
		ListNode currentNode = null;
		ListNode preNode = null;
		
		while (nextNode != null) {
			preNode = currentNode;
			currentNode = nextNode;
			nextNode = nextNode.link;
			currentNode.link = preNode;
		}
		
		head = currentNode;
	}
	
	// 연결 리스트에 저장된 모든 데이터 출력
	public void printList() {
		ListNode tempNode = this.head;
		
		while (tempNode != null) {
			System.out.print(tempNode.getData() + " ");
			tempNode = tempNode.link;
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		LinkedList linkedList = new LinkedList();
		String str = "wed";
		
		linkedList.insertNode("sun");
		linkedList.insertNode("mon");
		linkedList.insertNode("tue");
		linkedList.insertNode("wed");
		linkedList.insertNode("thu");
		linkedList.insertNode("fri");
		linkedList.insertNode("sat");
		
		System.out.println(linkedList.searchNode(str).getData());
		
		linkedList.deleteNode(linkedList.searchNode(str).getData());
		linkedList.printList();
		
		linkedList.reverseList();
		linkedList.printList();
	}
}










