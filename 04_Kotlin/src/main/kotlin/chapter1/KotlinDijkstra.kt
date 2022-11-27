package chapter1

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.PriorityQueue

data class Node(val index: Int, val dist: Int): Comparable<Node> {
    override fun compareTo(other: Node): Int {
        return this.dist - other.dist
    }
}

fun solve(grp: MutableList<PriorityQueue<Node>>, K: Int) {
    var distance: IntArray = IntArray(grp.size) {Int.MAX_VALUE} // 초깃값은 따로 바깥 중괄호에 선언
    var pq: PriorityQueue<Node> = PriorityQueue()

    distance[K] = 0
    pq.add(Node(K, 0))

    while (pq.isNotEmpty()) {
        val now = pq.poll().index

        for (next in grp[now]) {
            var cost = distance[now] + next.dist

            if (distance[next.index] > cost) {
                distance[next.index] = cost
                pq.add(Node(next.index, cost))
            }
        }
    }

    val sb = StringBuilder()
    for (i in 1 until distance.size) {
        if (distance[i] == Int.MAX_VALUE)
            sb.append("INF")
        else
            sb.append(distance[i])
        sb.append("\n")
    }

    print(sb)
}

fun dijkstra() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val (V, E) = readLine()
        .split(" ")
        .map { it.toInt() }
    val K = Integer.parseInt(readLine())

    var grp = MutableList(V + 1) {PriorityQueue<Node>()}

    repeat(E) {
        val (u: Int, v: Int, w: Int) = readLine()
            .split(" ")
            .map{ it.toInt() }

        grp[u].add(Node(v, w))
    }

    solve(grp, K)
    close()
}