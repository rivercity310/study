//
// Created by seungsu on 6/11/23.
//

#include "queue.h"

void queue1() {
    Queue q;
    queue_init(&q);

    enqueue(&q, 10); queue_print(&q);
    enqueue(&q, 20); queue_print(&q);
    enqueue(&q, 30); queue_print(&q);

    dequeue(&q); queue_print(&q);
    dequeue(&q); queue_print(&q);
    dequeue(&q); queue_print(&q);

    terminate_queue(&q);
}