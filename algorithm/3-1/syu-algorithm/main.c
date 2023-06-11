#define STACK
#define QUEUE
#define RING_BUFFER
#define TREE
#define BST

#ifdef STACK
void stk1();
void stk2();
#endif

#ifdef QUEUE
void queue1();
#endif

#ifdef RING_BUFFER
void rb1();
#endif

#ifdef TREE
void bt1();
#endif

#ifdef BST
void bst1();
#endif

int main() {
    bst1();
}
