#define QUEUE
#define LIST
#define TREE

#ifdef QUEUE
extern void linear_queue();
extern void ring_buffer();
extern void double_ended_queue();
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

#endif

int main() {
	DoublePointer_LinkedList();
}