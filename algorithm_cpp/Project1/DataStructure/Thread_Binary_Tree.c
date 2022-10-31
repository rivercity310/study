#include <stdio.h>

/*
[ 스레드 이진 트리 ]

이진트리의 NULL 링크를 이용하여 순환 호출 없이도 트리 노드들을 순회
NULL 링크에 중위 후속자(inorder successor)를 저장
*/

typedef struct tr {
	char data;
	struct tr* left;
	struct tr* right;
	int is_thread;          // 만약 오른쪽 링크가 스레드이면 True
} ThreadTree;

/* 중위 후속자를 찾는 함수 */
static ThreadTree* find_successor(ThreadTree* p) {
	ThreadTree* q = p->right;

	if (q == NULL || p->is_thread)
		return q;

	while (q->left) q = q->left;
	return q;
}

/* 스레드 버전 중위 순회 함수 */
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

	// 스레드 설정
	n1.right = &n3;
	n2.right = &n7;
	n4.right = &n6;

	thread_inorder(root);
}