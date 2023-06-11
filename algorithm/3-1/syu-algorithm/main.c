#define STACK
#define QUEUE
#define RING_BUFFER
#define TREE
#define BST
#define GRAPH

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

#ifdef GRAPH
void graph1();
void graph2();
void kruskal();
#endif

int main() {
    kruskal();
}
