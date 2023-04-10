package chapter1

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

const val INF = 10000
fun boj() = with(BufferedReader(InputStreamReader(System.`in`))) {
    var st = StringTokenizer(readLine())
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()

    var sb = StringBuilder()
    sb.append(INF).append("\n")

    for (i in 1 until n) {
        for (j in 1 until m)
            sb.append(i * j).append(" ")
        sb.append("\n")
    }

    print(sb)
}