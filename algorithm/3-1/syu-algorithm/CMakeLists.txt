cmake_minimum_required(VERSION 3.25)
project(syu_algorithm C)

set(CMAKE_C_STANDARD 11)

add_executable(
        syu_algorithm
        main.c
        stack/stk1.c stack/stk2.c stack/stack.h stack/stack.c queue/queue.h queue/queue.c queue/queue1.c ling_buffer/ringbuffer.h ling_buffer/ringbuffer.c ling_buffer/rb1.c tree/bintree.h tree/bintree.c tree/bt1.c bst/bst.h bst/bst.c bst/bst1.c)
