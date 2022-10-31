#include <stdio.h>
#include <stdlib.h>

/*
[ Double Linked List, 이중 연결리스트 ]
- 선행 노드를 찾기 쉽다는 장점
- 헤드 노드 : 데이터를 가지지 않고 단지 삽입, 삭제 코드를 간단하게 할 목적으로 만든 노드
  => 헤드 포인터와 구별 필요, 공백 상태에서는 헤드 노드만 존재
*/

typedef struct dnode {
	struct dnode* left;
	int data;
	struct dnode* right;
} DNODE;

/* 이중 연결리스트 초기화 */
static void dll_init(DNODE* phead) {
	phead->left = phead;
	phead->right = phead;
}

/* before 노드 오른쪽에 새 노드 삽입 */
static void dll_insert(DNODE* before, int data) {
	DNODE* tmp;
	if ((tmp = (DNODE*)malloc(sizeof(DNODE)))) {
		tmp->data = data;
		tmp->left = before;
		tmp->right = before->right;

		before->right->left = tmp;
		before->right = tmp;
	}
}

/* removed 노드 삭제 */
static void dll_delete(DNODE* head, DNODE* removed) {
	if (removed == head) return;

	removed->left->right = removed->right;
	removed->right->left = removed->left;
	free(removed);
}

static void dll_print(DNODE* phead) {
	for (DNODE* p = phead->right; p != phead; p = p->right)
		printf("  <-| %d |->  ", p->data);
	putchar('\n');
}

void double_linkedlist() {
	DNODE* head;
	if ((head = (DNODE*)malloc(sizeof(DNODE)))) {
		dll_init(head);
		
		int n = 5;
		puts("[ 추가 단계 ]");

		for (int i = 0; i < n; i++) {
			/* 헤드 노드 오른쪽에 삽입 */
			dll_insert(head, i);
			dll_print(head);
		}


		puts("\n");
		puts("[ 삭제 단계 ]");
		for (int i = 0; i < n; i++) {
			dll_print(head);
			dll_delete(head, head->right);
		}

		free(head);
	}
}