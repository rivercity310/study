#include <stdio.h>
#include <stdlib.h>

#define MAX_QUEUE_SIZE 100

typedef struct tn TreeNode;

typedef struct {
	TreeNode* data[MAX_QUEUE_SIZE];
	int front;
	int rear;
} QueueType;

static void init_queue(QueueType* q) {
	q->front = q->rear = 0;
}

static int is_empty(QueueType* q) {
	return q->front == q->rear;
}

static void enqueue(QueueType* q, TreeNode* data) {
	q->rear = (q->rear + 1) % MAX_QUEUE_SIZE;
	q->data[q->rear] = data;
}

static TreeNode* dequeue(QueueType* q) {
	if (is_empty(q)) {
		puts("Stack Empty!");
		exit(1);
	}

	q->front = (q->front + 1) % MAX_QUEUE_SIZE;
	return q->data[q->front];
}

/* -------------------------------------------------------------------------- */

typedef struct tn {
	struct tn* left;
	int data;
	struct tn* right;
} TreeNode;

static void preorder(TreeNode* root) {
	if (root) {
		printf("%d ", root->data);
		preorder(root->left);
		preorder(root->right);
	}
}

static void inorder(TreeNode* root) {
	if (root) {
		inorder(root->left);
		printf("%d ", root->data);
		inorder(root->right);
	}
}

static void postorder(TreeNode* root) {
	if (root) {
		postorder(root->left);
		postorder(root->right);
		printf("%d ", root->data);
	}
}

static void levelorder(TreeNode* node) {
	QueueType q;
	init_queue(&q);

	if (node == NULL) return;
	
	enqueue(&q, node);
	while (!is_empty(&q)) {
		node = dequeue(&q);
		printf("%d ", node->data);

		if (node->left) enqueue(&q, node->left);
		if (node->right) enqueue(&q, node->right);
	}
}

static void lt_terminate(TreeNode* root) {
	if (root) {
		lt_terminate(root->left);
		lt_terminate(root->right);
		printf("%d ", root->data);
		free(root);
	}
}


void traversal(TreeNode* root, int mode) {
	switch (mode) {
	case 1:
		puts("[ Preorder Start ]");
		preorder(root);
		puts("\n");
		break;
	
	case 2:
		puts("[ Inorder Start ]");
		inorder(root);
		puts("\n");
		break;
	
	case 3:
	
		puts("[ Postorder Start ]");
		postorder(root);
		puts("\n");
		break;

	case 4:
		puts("[ Levelorder Start ]");
		levelorder(root);
		puts("\n");
		break;

	case 0:
		puts("[ Tree Terminate ]");
		puts("�޸𸮸� �����մϴ�...");
		lt_terminate(root);
		puts("\n");
		break;

	default:
		printf("��ġ���� �ʴ� ��ȣ\n");
		return;
	}
}
