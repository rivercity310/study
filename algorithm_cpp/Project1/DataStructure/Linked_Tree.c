#include <stdio.h>
#include <stdlib.h>
#include <memory.h>
#include <string.h>

/* 메모리 할당 정책에 따라 24바이트 할당 (8, 8, 8) */
typedef struct tn {
	struct tn* left;
	int data;
	struct tn* right;
} TreeNode;

extern void traversal(TreeNode* root, int mode);

/* 특정 노드를 초기화하는 함수 */
static TreeNode* initNode(int data, TreeNode* leftChild, TreeNode* rightChild) {
	TreeNode* node = (TreeNode*)malloc(sizeof(TreeNode));

	node->data = data;
	node->left = leftChild;
	node->right = rightChild;

	return node;
}

extern TreeNode* makeTree() {
	TreeNode* n7 = initNode(7, NULL, NULL);
	TreeNode* n6 = initNode(6, NULL, NULL);
	TreeNode* n5 = initNode(5, NULL, NULL);
	TreeNode* n4 = initNode(4, NULL, NULL);
	TreeNode* n3 = initNode(3, n6, n7);
	TreeNode* n2 = initNode(2, n4, n5);
	TreeNode* n1 = initNode(1, n2, n3);

	return n1;
}

/* 노드 개수 연산 함수 */
static int node_count(TreeNode* root) {
	int count = 0;
	if (root) 
		count = 1 + node_count(root->left) + node_count(root->right);
	
	return count;
}

/* 단말 노드 개수 */
static int leaf_node_count(TreeNode* node) {
	int count = 0;

	if (node) {
		if (node->left == NULL && node->right == NULL)
			return 1;
		else {
			count = leaf_node_count(node->left) + leaf_node_count(node->right);
		}
	}

	return count;
}

/* 트리 높이 */
static int get_height(TreeNode* node) {
	int height = 0;

	if (node) 
		height = 1 + max(get_height(node->left), get_height(node->right));

	return height;
}

void Linked_Tree() {
	TreeNode* root = makeTree();

	printf("생성된 트리의 노드 개수: %d\n", node_count(root));
	printf("생성된 트리의 단말 노드 개수:  %d\n", leaf_node_count(root));
	printf("생성된 트리의 높이: %d\n", get_height(root));

	for (int i = 1; i <= 4; i++)
		traversal(root, i);

	traversal(root, 0);
}