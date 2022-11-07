// #define MAIN
// #define QUEUE
// #define LIST
#define TREE
// #define HEAP
// #define GRAPH

#ifdef MAIN
#include <stdio.h>
#include <stdlib.h>
#endif

#ifdef QUEUE
extern void linear_queue();
extern void ring_buffer();
extern void double_ended_queue();
extern void Multi_Linked_Queue_Test();
#endif

#ifdef LIST
extern void Linked_List();
extern void DoublePointer_LinkedList();
extern void Ring_LinkedList();
extern void double_linkedlist();
extern void Linked_Stack();
extern void Linked_Queue();
#endif

#ifdef TREE
extern void Linked_Tree();
extern void Thread_Binary_Tree();
extern void Binary_Search_Tree();
extern void tree_Ex();
#endif

#ifdef HEAP
extern void array_heap();
extern void sorting_with_heap();
#endif

#ifdef GRAPH
extern void adj_matrix_test();
extern void adj_list_test();
extern void kruskal_mst_test();
extern void prim_mst_test();
extern void dijkstra_test();
extern void floyd_test();
extern void topology_test();
#endif

int main() {
	tree_Ex();
}