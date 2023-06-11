//
// Created by seungsu on 6/11/23.
//

#include "stack.h"
#include <stdio.h>
#include <stdlib.h>

void stack_init(Stack* stk) {
    stk->top = -1;
    stk->data = (char*)malloc(sizeof(char) * SIZE);
}

Boolean stack_is_full(Stack* stk) {
    if (stk->top == SIZE) return True;
    return False;
}

Boolean stack_is_empty(Stack* stk) {
    if (stk->top == -1) return True;
    return False;
}

void push(Stack* stk, int x) {
    if (stack_is_full(stk)) {
        stack_error("Stack Full!");
    }

    stk->data[++stk->top] = x;
}

int pop(Stack* stk) {
    if (stack_is_empty(stk)) {
        stack_error("Stack Empty!");
    }

    return stk->data[stk->top--];
}

int stack_peek(Stack* stk) {
    if (stack_is_empty(stk)) {
        stack_error("Stack Full!");
    }

    return stk->data[stk->top];
}

void stack_error(char* message) {
    fprintf(stderr, "%s\n", message);
    exit(0);
}

void terminate_stack(Stack* stk) {
    free(stk->data);
}