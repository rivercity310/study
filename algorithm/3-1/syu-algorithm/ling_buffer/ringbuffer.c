//
// Created by seungsu on 6/11/23.
//

#include "ringbuffer.h"
#include <stdio.h>
#include <stdlib.h>

void rb_init(RingBuffer* rb) {
    rb->front = rb->rear = 0;
    rb->data = (element*)malloc(sizeof(element) * SIZE);
}

void rb_terminate(RingBuffer* rb) {
    free(rb->data);
}

void rb_error(char* message) {
    fprintf(stderr, "%s\n", message);
    exit(0);
}

void rb_print_queue(RingBuffer* rb) {
    for (int i = rb->front + 1; i <= rb->rear; i++) {
        printf("%d | ", rb->data[i]);
    }
    printf("size() = %d, peek() = %d", rb_size(rb), rb_peek(rb));
    printf("\n");
}

Boolean rb_is_empty(RingBuffer* rb) {
    if (rb->front == rb->rear) return True;
    return False;
}

Boolean rb_is_full(RingBuffer* rb) {
    if (rb->front == (rb->rear + 1) % SIZE) return True;
    return False;
}

int rb_size(RingBuffer* rb) {
    return (rb->rear - rb->front + SIZE) % SIZE;
}

void rb_enqueue(RingBuffer* rb, int x) {
    if (rb_is_full(rb)) {
        rb_error("RingBuffer is Full!");
    }

    rb->rear = (rb->rear + 1) % SIZE;
    rb->data[rb->rear] = x;
}

element rb_dequeue(RingBuffer* rb) {
    if (rb_is_empty(rb)) {
        rb_error("RingBuffer is Empty!");
    }

    rb->front = (rb->front + 1) % SIZE;
    return rb->data[rb->front];
}

element rb_peek(RingBuffer* rb) {
    if (rb_is_empty(rb)) {
        rb_error("RingBuffer is Empty!");
    }

    return rb->data[(rb->front + 1) % SIZE];
}



