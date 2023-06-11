//
// Created by seungsu on 6/11/23.
//

/*
 * [ 중위 표기식 -> 후위 표기식 변환 알고리즘 ]
 * 1. 피연산자 -> 바로 출력
 * 2. 연산자 -> 자기보다 우선순위가 높거나 같은 것들을 빼고 자신을 스택에 담는다
 *   - 처리중인 연산자: '+' or '-', 스택에 있는 연산자: '/' or '*' 인 경우: 스택에 있는 연산자 pop 후 출력, 처리중인 연산자 push
 *   - 반대의 경우 -> 현재 처리중인 연산자 그냥 push
 *   - 우선순위 같은 경우: 1번과 동일하게 처리
 * 3. 여는 괄호 '(' -> 스택에 push
 * 4. 닫는 괄호 ')' -> '('를 만날 떄까지 스택에서 출력
*/

/*
 * [ 후위 표기식 계산 알고리즘 ]
 * 1. 피연산자인 경우 Stack에 Push
 * 2. 연산자인 경우 필요한 만큼 Stack에서 값을 꺼내 계산후 결과를 Stack에 Push
 * */

#include "stack.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

static Stack stk;

// 연산자 우선순위 반환
static int prec(char op) {
    switch (op) {
    case '(': case ')': return 0;
    case '+': case '-': return 1;
    case '*': case '/': return 2;
    }

    return -1;
}

static Boolean is_digit(char c) {
    int k = c - '0';
    if (k >= 0 && k <= 9) return True;
    return False;
}

static char* infix_to_postfix(char* exp) {
    stack_init(&stk);

    int size = strlen(exp);
    char* postfix_exp = (char*)malloc(sizeof(char) * (size + 1));
    int idx = 0;

    // 중위 표기식 순회
    for (int i = 0; i < size; i++) {
        char c = *(exp + i);

        // 일반 숫자라면 그대로 postfix_exp에 추가한다
        if (is_digit(c)) {
            postfix_exp[idx++] = c;
        }

        // 연산자인 경우
        else if (c == '+' || c == '-' || c == '*' || c == '/') {
            // 스택의 top과 현재 연산자의 우선순위를 비교
            while (!stack_is_empty(&stk) && (prec(c) <= prec(stack_peek(&stk)))) postfix_exp[idx++] = pop(&stk);
            push(&stk, c);
        }

        // 여는 괄호인 경우 스택에 추가
        else if (c == '(') {
            push(&stk, c);
        }

        // 닫는 괄호인 경우 여는 괄호가 나올 때까지 스택에서 pop하여 추가해준다
        else if (c == ')') {
            char op = pop(&stk);
            while (op != '(') {
                postfix_exp[idx++] = op;
                op = pop(&stk);
            }
        }
    }

    // 스택에 남은 값들 모두 추가
    while (!stack_is_empty(&stk)) {
        postfix_exp[idx++] = pop(&stk);
    }

    // 문자열 끝 지정
    postfix_exp[idx] = '\0';
    return postfix_exp;
}

static int eval(char* postfix) {
    stack_init(&stk);

    for (int i = 0; i < strlen(postfix); i++) {
        char c = *(postfix + i);

        if (is_digit(c)) {
            push(&stk, c);
        }

        else {
            int op2 = pop(&stk) - '0';
            int op1 = pop(&stk) - '0';

            switch (c) {
                case '+': push(&stk, (op1 + op2) + '0'); break;
                case '-': push(&stk, (op1 - op2) + '0'); break;
                case '*': push(&stk, (op1 * op2) + '0'); break;
                case '/': push(&stk, (op1 / op2) + '0'); break;
            }
        }
    }

    return pop(&stk) - '0';
}

void stk2() {
    char* input = "9*(3/(5+4)-6)";
    char* postfix = infix_to_postfix(input);
    terminate_stack(&stk);

    printf("%s\n", postfix);
    printf("후위 표기식 계산 결과: %d\n", eval(postfix));
    terminate_stack(&stk);
}