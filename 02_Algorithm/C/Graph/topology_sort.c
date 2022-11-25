//#include <stdio.h>
//#include <stdlib.h>
//
//#define TRUE 1
//#define FALSE 0
//#define MAX_VERTICES 50
//#define MAX_STACK_SIZE 100
//
//typedef struct gNode {
//	int vertex;
//	struct gNode* next;
//} GraphNode;
//
//typedef struct gType {
//	int n;
//	GraphNode* adj_list[MAX_VERTICES];
//} GraphType;
//
//typedef struct {
//	int stack[MAX_STACK_SIZE];
//	int top;
//} StackType;
//
//
//extern void init_graph(GraphType* g);
//extern void insert_vertex(GraphType* g, int v);
//extern void insert_edge(GraphType* g, int u, int v);
//extern void terminate_graph(GraphType* g);
//
//static void init_stack(StackType* stk) {
//	stk->top = -1;
//}
//
//static void push_stack(StackType* stk, int x) {
//	if (stk->top >= MAX_STACK_SIZE) {
//		fprintf(stderr, "Stack Full\n");
//		return;
//	}
//
//	stk->top++;
//	stk->stack[stk->top] = x;
//}
//
//static int is_empty_stack(StackType* stk) {
//	return stk->top == -1;
//}
//
//static int pop_stack(StackType* stk) {
//	if (stk->top == -1) {
//		fprintf(stderr, "Stack Empty\n");
//		exit(1);
//	}
//
//	return stk->stack[stk->top--];
//}
//
//// ---------------------------------------------------------------
//
//
//static int indegree[MAX_VERTICES];
//
//static int topology_sort(GraphType* g) {
//	StackType s;
//	GraphNode* node;
//	int i = -1;
//
//	for (i = 0; i < g->n; i++) {
//		node = g->adj_list[i];
//
//		while (node != NULL) {
//			indegree[node->vertex]++;
//			node = node->next;
//		}
//	}
//
//	init_stack(&s);
//	for (i = 0; i < g->n; i++)
//		if (indegree[i] == 0)
//			push_stack(&s, i);
//
//	while (!is_empty_stack(&s)) {
//		int w = pop_stack(&s);
//		printf("���� %d -> ", w);
//
//		node = g->adj_list[w];
//		while (node != NULL) {
//			int u = node->vertex;
//			indegree[u]--;
//
//			if (indegree[u] == 0)
//				push_stack(&s, u);
//
//			node = node->next;
//		}
//	}
//
//	putchar('\n');
//	return (i == g->n);
//}
//
//void topology_test() {
//	GraphType g;
//
//	init_graph(&g);
//
//	for (int i = 0; i <= 5; i++)
//		insert_vertex(&g, i);
//
//	insert_edge(&g, 0, 2); insert_edge(&g, 0, 3);
//	insert_edge(&g, 1, 3); insert_edge(&g, 1, 4);
//	insert_edge(&g, 2, 3); insert_edge(&g, 2, 5);
//	insert_edge(&g, 3, 5);
//	insert_edge(&g, 4, 5);
//
//	topology_sort(&g);
//	
//	terminate_graph(&g);
//}