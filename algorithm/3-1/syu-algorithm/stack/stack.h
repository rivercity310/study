#define Boolean int
#define True 1
#define False 0
#define SIZE 10

typedef struct {
    int top;
    char* data;
} Stack;

void init_stack(Stack* stk);
Boolean is_full(Stack* stk);
Boolean is_empty(Stack* stk);
void push(Stack* stk, int x);
int pop(Stack* stk);
int peek(Stack* stk);