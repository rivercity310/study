#include <stdio.h>



typedef struct tr {
	char data;
	struct tr* left;
	struct tr* right;
	int is_thread;
} ThreadTree;

static ThreadTree* find_successor(ThreadTree* p) {
	ThreadTree* q = p->right;

	if (q == NULL || p->is_thread)
		return q;

	while (q->left) q = q->left;
	return q;
}


static void thread_inorder(ThreadTree* t) {
	ThreadTree* q = t;

	while (q->left) q = q->left;

	do {
		printf("%c ", q->data);
		q = find_successor(q);
	} while (q);
}

void Thread_Binary_Tree() {
	ThreadTree n1 = { 'A', NULL, NULL, 1 };      
	ThreadTree n2 = { 'B', NULL, NULL, 1 };
	ThreadTree n3 = { 'C', &n1, &n2, 0 };
	ThreadTree n4 = { 'D', NULL, NULL, 1 };
	ThreadTree n5 = { 'E', NULL, NULL, 0 };
	ThreadTree n6 = { 'F', &n4, &n5, 0 };
	ThreadTree n7 = { 'G', &n3, &n6, 0 };
	ThreadTree* root = &n7;

	n1.right = &n3;
	n2.right = &n7;
	n4.right = &n6;

	thread_inorder(root);
}