//
// Created by seungsu on 6/11/23.
//

#include "ringbuffer.h"

void rb1() {
    RingBuffer rb;
    rb_init(&rb);

    for (int i = 1; i < 10; i++) {
        rb_enqueue(&rb, i);
        rb_print_queue(&rb);
    }

    while (!rb_is_empty(&rb)) {
        rb_dequeue(&rb);
        rb_print_queue(&rb);
    }

    rb_terminate(&rb);
}