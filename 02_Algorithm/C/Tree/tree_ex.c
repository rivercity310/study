#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct tree {
	struct tree* left;
	char* data;
	struct tree* right;
} TreeNode;

static int inorder_count;

static int eval(TreeNode* root) {
	if (root == NULL) return 0;
	if (root->left == NULL && root->right == NULL) return atoi(root->data);
	else {
		int op1 = eval(root->left);
		int op2 = eval(root->right);

		printf("op1: %d, op2: %d\n", op1, op2);

		if (!(op1 == 0 || op2 == 0)) {
			if (root->data == "+")
				return op1 + op2;
			else if (root->data == "-")
				return op1 - op2;
			else if (root->data == "/")
				return op1 / op2;
			else if (root->data == "*")
				return op1 * op2;
		}
	}
}

static void preorder(TreeNode* node) {
    if (node) {
        printf("%s ", node->data);
        preorder(node->left);
        preorder(node->right);
    }
}

static void inorder(TreeNode* node) {
	inorder_count++;
	if (node) {
		inorder(node->left);
        printf("%s ", node->data);
		inorder(node->right);
	}
}

static void postorder(TreeNode* node) {
    if (node) {
        postorder(node->left);
        postorder(node->right);
        printf("%s ", node->data);
    }
}

#define SIZE 100
static int top = -1;
static TreeNode* stack[SIZE];

static void push(TreeNode* p) {
    if (top < SIZE - 1) stack[++top] = p;
}

static TreeNode* pop() {
    if (top >= 0) return stack[top--];
    return NULL;
}

static void inorder_iter(TreeNode* root) {
    while (1) {
        for (; root; root = root->left) push(root);
        root = pop();
        if (!root) break;
        printf("%s ", root->data);
        root = root->right;
    }
}

/*
[ ((10 + 20) * (40 - 30)) + ((60 + 70) * (80 - 40)) = 5500 ]
				

			                +
				  *					   *
			  +        -		  +			-
		  10	20	40   30    60   70   80   40
	      
*/

typedef struct {
    TreeNode* data[SIZE];
    int front;
    int rear;
} QueueType;

static void init_queue(QueueType* q) {
   q->front = q->rear = 0;
}

static int is_empty(QueueType* q) {
    return q->front == q->rear;
}

static int is_full(QueueType* q) {
    return (q->rear + 1) % SIZE == q->front;
}

static void enqueue(QueueType* q, TreeNode* item) {
    if (!is_full(q)) {
        q->rear = (q->rear + 1) % SIZE;
        q->data[q->rear] = item;
    }
}

static TreeNode* dequeue(QueueType* q) {
    if (!is_empty(q)) {
        q->front = (q->front + 1) % SIZE;
        return q->data[q->front];
    }
}

static void level_order(TreeNode* root) {
    if (!root) return;

    QueueType* q = (QueueType*) malloc(sizeof(QueueType));
    init_queue(q);
    enqueue(q, root);

    while (!is_empty(q)) {
        root = dequeue(q);
        printf("%s ", root->data);
        if (root->left) enqueue(q, root->left);
        if (root->right) enqueue(q, root->right);
    }

    free(q);
}

static int get_node_count(TreeNode* node) {
    int cnt = 0;
    if (node)
        cnt = 1 + get_node_count(node->left) + get_node_count(node->right);

    return cnt;
}

static int get_leaf_count(TreeNode* node) {
    int cnt = 0;
    if (node) {
        if (!node->left && !node->right) return 1;
        cnt = get_leaf_count(node->left) + get_leaf_count(node->right);
    }

    return cnt;
}

#define max(a, b) ((a > b) ? a : b);
static int get_height(TreeNode* node) {
    int h = 0;
    if (node)
        h = 1 + max(get_height(node->left), get_height(node->right));

    return h;
}

void tree_Ex() {
	TreeNode n8 = { NULL, "10", NULL};
	TreeNode n9 = { NULL, "20", NULL};
	TreeNode n10 = { NULL, "40", NULL};
	TreeNode n11 = { NULL, "30", NULL};
	TreeNode n4 = { &n8, "+", &n9};
	TreeNode n5 = { &n10, "-", &n11};
	TreeNode n2 = { &n4, "*", &n5};
	
	TreeNode n12 = { NULL, "60", NULL };
	TreeNode n13 = { NULL, "70", NULL};
	TreeNode n14 = { NULL, "80", NULL};
	TreeNode n15 = { NULL, "40", NULL};
	TreeNode n6 = { &n12, "+", &n13 };
	TreeNode n7 = { &n14, "-", &n15};
	TreeNode n3 = { &n6, "*", &n7 };

	TreeNode n1 = { &n2, "+", &n3 };
	TreeNode* root = &n1;

    preorder(root);
    putchar('\n');
	inorder(root);
    putchar('\n');
    inorder_iter(root);
    putchar('\n');
    postorder(root);
    putchar('\n');
    level_order(root);
    putchar('\n');

    printf("노드의 개수: %d\n", get_node_count(root));
    printf("단말 노드 개수: %d\n", get_leaf_count(root));
    printf("트리의 높이: %d\n", get_height(root));

	putchar('\n');
	printf("Ans: %d\n", eval(root));
}