#define Boolean int
#define True 1
#define False 0
#define SIZE 10

typedef struct {
    int top;
    char* data;
} Stack;

void stack_init(Stack* stk);
Boolean stack_is_full(Stack* stk);
Boolean stack_is_empty(Stack* stk);
void push(Stack* stk, int x);
int pop(Stack* stk);
int stack_peek(Stack* stk);
void stack_error(char* message);
void terminate_stack(Stack* stk);