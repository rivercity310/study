//
// Created by seungsu on 6/11/23.
//

#include "queue.h"
#include <stdio.h>
#include <stdlib.h>

void queue_error(char* message) {
    fprintf(stderr, "%s\n", message);
    exit(1);
}

void queue_init(Queue* q) {
    q->rear = -1;
    q->front = -1;
    q->data = (element*)malloc(sizeof(element) * SIZE);
}

Boolean queue_is_full(Queue* q) {
    if (q->rear == SIZE - 1) return True;
    return False;
}

Boolean queue_is_empty(Queue* q) {
    if (q->front == q->rear) return True;
    return False;
}

void enqueue(Queue* q, int x) {
    if (queue_is_full(q)) {
        queue_error("Queue Full!");
    }

    q->data[++(q->rear)] = x;
}

element dequeue(Queue* q) {
    if (queue_is_empty(q)) {
        queue_error("Queue Empty!");
    }

    return q->data[++(q->front)];
}

element queue_peek(Queue* q) {
    if (queue_is_empty(q)) {
        queue_error("Queue Empty!");
    }

    return q->data[q->front + 1];
}

int queue_size(Queue* q) {
    return q->rear - q->front;
}

void queue_print(Queue* q) {
    for (int i = q->front + 1; i <= q->rear; i++) {
        printf("%d | ", q->data[i]);
    }

    printf("peek() = %d, size() = %d\n", queue_peek(q), queue_size(q));
}

void terminate_queue(Queue* q) {
    free(q->data);
}