package chapter1

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.util.*

// [ 2. Java To Kotlin ]

/**
 - Java 파일을 확장자가 .kt인 코틀린 파일에 Copy-Paste -> 자동으로 코틀린으로 변환됨
 */

object JtoK {
    private var st: StringTokenizer? = null
    private val br = BufferedReader(InputStreamReader(System.`in`))

    @Throws(IOException::class)
    @JvmStatic
    fun boj() {
        st = StringTokenizer(br.readLine())
        val n = st!!.nextToken().toInt()
        val m = st!!.nextToken().toInt()

        val arr = IntArray(m)
        var idx = 0

        st = StringTokenizer(br.readLine())
        while (st!!.hasMoreTokens()) arr[idx++] = st!!.nextToken().toInt()

        for (k in arr) print("$k ")
    }
}
