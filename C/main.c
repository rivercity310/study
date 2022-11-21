#define LINKED_LIST
#define STACK
#define QUEUE
#define TREE
#define GRAPH
#define SORTING

#ifdef SORTING
extern void selection_sort_test();
extern void quick_sort_test();
#endif

#ifdef LINKED_LIST
extern void double_linkedlist();
extern void DoublePointer_LinkedList();
extern void Linked_List();
extern void Ring_LinkedList();
#endif

#ifdef STACK
extern void Linked_Stack();
#endif

#ifdef QUEUE
extern void double_ended_queue();
extern void linear_queue();
extern void Linked_Queue();
extern void Multi_Linked_Queue_Test();
extern void ring_buffer();
#endif

#ifdef TREE
extern void Binary_Search_Tree();
extern void sorting_with_heap();
extern void Linked_Tree();
extern void Thread_Binary_Tree();
extern void tree_Ex();
#endif

#ifdef GRAPH
extern void adj_list_test();
extern void adj_matrix_test();
extern void dijkstra_test();
extern void floyd_test();
extern void graph_assignment();
extern void graph_assignment2();
extern void graph_sch();
extern void kruskal_mst_test();
extern void prim_mst_test();
#endif

int main() {
    quick_sort_test();
}
