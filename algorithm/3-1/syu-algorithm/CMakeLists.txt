cmake_minimum_required(VERSION 3.25)
project(syu_algorithm C)

set(CMAKE_C_STANDARD 11)

add_executable(
        syu_algorithm
        main.c
        stack/stk1.c stack/stk2.c stack/stack.h stack/stack.c queue/queue.h queue/queue.c queue/queue1.c)
