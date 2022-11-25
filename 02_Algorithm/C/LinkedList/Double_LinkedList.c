#include <stdio.h>
#include <stdlib.h>

typedef struct dnode {
	struct dnode* left;
	int data;
	struct dnode* right;
} DNODE;

static void dll_init(DNODE* phead) {
	phead->left = phead;
	phead->right = phead;
}

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

		for (int i = 0; i < n; i++) {
			dll_insert(head, i);
			dll_print(head);
		}

		puts("\n");
		for (int i = 0; i < n; i++) {
			dll_print(head);
			dll_delete(head, head->right);
		}

		free(head);
	}
}