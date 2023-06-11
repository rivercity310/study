//
// Created by seungsu on 6/11/23.
//

#include "stack.h"
#include <stdio.h>
#include <stdlib.h>

void init_stack(Stack* stk) {
    stk->top = -1;
    stk->data = (char*)malloc(sizeof(char) * SIZE);
}

Boolean is_full(Stack* stk) {
    if (stk->top == SIZE) return True;
    return False;
}

Boolean is_empty(Stack* stk) {
    if (stk->top == -1) return True;
    return False;
}

void push(Stack* stk, int x) {
    if (is_full(stk)) {
        fprintf(stderr, "Stack Full\n");
        exit(0);
    }

    stk->data[++stk->top] = x;
}

int pop(Stack* stk) {
    if (is_empty(stk)) {
        fprintf(stderr, "Stack Empty!\n");
        exit(0);
    }

    return stk->data[stk->top--];
}

int peek(Stack* stk) {
    if (is_empty(stk)) {
        fprintf(stderr, "Stack Empty!\n");
        exit(0);
    }

    return stk->data[stk->top];
}