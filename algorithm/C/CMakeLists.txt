cmake_minimum_required(VERSION 3.23)
project(C C)

set(CMAKE_C_STANDARD 99)

add_executable(
        C
        main.c
        Stack/Linked_Stack.c
        Queue/double_ended_queue.c Queue/linear_queue.c Queue/Linked_Queue.c Queue/Multi_Linked_Queue.c Queue/ring_buffer.c
        LinkedList/Double_Pointer_LinkedList.c LinkedList/Double_LinkedList.c LinkedList/Linked_List.c LinkedList/Ring_LinkedList.c
        Tree/Binary_Search_Tree.c Tree/heap.c Tree/Linked_Tree.c Tree/Thread_Binary_Tree.c Tree/tree_ex.c Tree/TreeUtil.c
        Graph/adj_list.c Graph/adj_matrix.c Graph/dijkstra.c Graph/floyd.c Graph/graph_assignment.c Graph/graph_assignment2.c
        Graph/graph_sch.c Tree/kruskal.c Tree/prim.c Tree/topology_sort.c
        Sorting/SelectionSort.c Sorting/QuickSort.c Sorting/MergeSort.c Tree/mst_assignment.c)

