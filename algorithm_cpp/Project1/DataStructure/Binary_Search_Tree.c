#include <stdio.h>
#include <stdlib.h>
#include <time.h>

/*
[ 이진 탐색트리, BST ]
왼쪽 서브트리: 루트보다 작은 값
오른쪽 서브트리: 루트보다 큰 값

BST를 중위순회하면 오름차순으로 정렬된 값을 얻을 수 있다.
*/


typedef struct tr {
	int data;
	struct tr* left;
	struct tr* right;
} TreeNode;

/* 순환적인 방식의 탐색 함수 */
static TreeNode* search(TreeNode* node, int key) {
	if (node == NULL) 
		return NULL;

	if (node->data == key)
		return node;

	else if (node->data > key) 
		return search(node->left, key);
	
	return search(node->right, key);
}

/* 반복적인 방식의 탐색 함수 */
static TreeNode* search2(TreeNode* node, int key) {
	while (node) {
		if (node->data == key)
			return node;

		else if (node->data > key)
			node = node->left;

		else
			node = node->right;
	}

	return NULL;
}

static TreeNode* new_node(int data) {
	TreeNode* tmp = (TreeNode*)malloc(sizeof(TreeNode));
	tmp->data = data;
	tmp->left = tmp->right = NULL;

	return tmp;
}

/* 삽입연산 : 탐색에 실패한 위치가 새로운 노드를 삽입하는 위치가 됨 */
static TreeNode* insert_node(TreeNode* node, int key) {
	// 트리가 공백이면 새로운 노드를 반환한다. 
	if (node == NULL) return new_node(key);

	// 그렇지 않으면 순환적으로 트리를 내려간다. 
	if (key < node->data)
		node->left = insert_node(node->left, key);
	else if (key > node->data)
		node->right = insert_node(node->right, key);

	// 변경된 루트 포인터를 반환한다. 
	return node;
}

static TreeNode* min_value_node(TreeNode* node);

/* 
삭제 연산은 다음 3가지 경우가 존재 

1. 단말 노드인 경우
=> 간단. 부모 노드를 찾아 연결만 끊으면 됨

2. 자식으로 하나의 서브트리만 가진 경우
=> 부모 노드의 삭제하려는 노드의 자식 노드를 연결시키고 삭제

3. 자식으로 두개의 서브트리를 모두 가진 경우
=> 삭제하려는 노드와 가장 비슷한 값을 가진 노드를 삭제 노드 위치로 가져오고,
   원래 위치에 있는 노드 삭제
   (왼쪽 서브트리에서 제일 큰 값 or 오른쪽 서브트리에서 제일 작은 값)
*/
static TreeNode* delete_node(TreeNode* root, int key) { // key 노드 삭제 후 루트 반환
	if (root == NULL) return root;
	if (key < root->data) 			// 키가 루트보다 작으면 왼쪽 서브 트리에 있음
		root->left = delete_node(root->left, key);
	else if (key > root->data) 		// 키가 루트보다 크면 오른쪽 서브 트리에 있음
		root->right = delete_node(root->right, key);
	else {				// 키가 루트와 같으면 이 노드를 삭제함
		if (root->left == NULL) {	// (1) or (2)
			TreeNode* temp = root->right;
			free(root);
			return temp;
		}
		else if (root->right == NULL) {	// (1) or (2)
			TreeNode* temp = root->left;
			free(root);
			return temp;
		}

		TreeNode* temp = min_value_node(root->right); 		// (3)
		root->data = temp->data; 				// 직후 키 복사
		root->right = delete_node(root->right, temp->data); 	// 직후 노드 삭제
	}
	return root;
}

static TreeNode* min_value_node(TreeNode* node) {
	TreeNode* curr = node;

	// 맨 왼쪽 단말 노드를 찾으러 내려감
	while (curr->left)
		curr = curr->left;

	return curr;
}

static void inorder(TreeNode* root) {
	if (root) {
		inorder(root->left);
		printf("%d ", root->data);
		inorder(root->right);
	}
}

void Binary_Search_Tree() {
	TreeNode* root = NULL;
	TreeNode* tmp = NULL;

	int n;
	scanf_s("%d", &n);

	srand(time(NULL));

	for (int i = 0; i < n; i++)
		root = insert_node(root, rand() % 100);

	puts("[ BST Inorder ]");
	inorder(root);
	puts("\n");

	for (int i = 0; i < 3; i++) {
		printf("del: ");
		scanf_s("%d", &n);
		delete_node(root, n);
	
		puts("[ BST Inorder ]");
		inorder(root);
		puts("\n");
	}
}