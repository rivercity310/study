//
// Created by seungsu on 6/11/23.
//

// 스택을 이용한 괄호 검사 알고리즘

#include <stdio.h>
#include <string.h>
#include "stack.h"

static Boolean check_matching(Stack* stk, char* input) {
    for (int i = 0; i < strlen(input); i++) {
        char c = input[i];

        switch (c) {
        case '(': case '[': case '{':
            push(stk, c);
            break;

        case ')': case ']': case '}':
            if (stack_is_empty(stk)) {
                return False;
            }

            char p = pop(stk);

            if (p == '(' && c == ')') continue;
            if (p == '[' && c == ']') continue;
            if (p == '{' && c == '}') continue;
            return False;
        }
    }

    if (!stack_is_empty(stk)) return False;
    return True;
}

void stk1() {
    Stack* stk;
    stack_init(stk);

    char* input = "{ A[(I+1)]=0;}";
    Boolean result = check_matching(stk, input);

    if (result) printf("정상 괄호 문자열\n");
    else printf("비정상 괄호 문자열\n");

    terminate_stack(stk);
}