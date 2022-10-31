#define MAIN
#define QUEUE
#define LIST
#define TREE

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
#endif

int main() {
	Multi_Linked_Queue_Test();
}