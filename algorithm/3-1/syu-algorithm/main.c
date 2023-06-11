#define STACK
#define QUEUE
#define RING_BUFFER
#define TREE

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

int main() {
    bt1();
}
