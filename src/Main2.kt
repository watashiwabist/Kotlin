import java.io.*
import java.util.*
import java.lang.*
import kotlin.collections.HashMap


fun main(args: Array<String>) {
    with(PrintWriter("calc.out")) {
        val n = nextLong()
        val a = nextInt()
        val b = nextInt()
        val c = nextInt()
        val deque = ArrayDeque<Bambolini>()
        val map = HashMap<Bambolini, String>()
        deque.offer(Bambolini(n, a, b, c))
        var min = 1e18.toLong()
        while (deque.isNotEmpty()) {
            val v = deque.poll()
            if (!map.contains(v)) {
                map[v] = "True"
                if (v.a == 0 && v.b == 0 && v.c == 0) {
                    min = minOf(min, v.cur)
                } else {
                    if (v.a > 0) deque.offer(Bambolini(Calc(v.cur, 1), v.a - 1, v.b, v.c))
                    if (v.b > 0) deque.offer(Bambolini(Calc(v.cur, 2), v.a, v.b - 1, v.c))
                    if (v.c > 0) deque.offer(Bambolini(Calc(v.cur, 3), v.a, v.b, v.c - 1))
                }
            }
        }
        println(min)
        close()
    }
}

fun Calc(x: Long, type: Int): Long {
    return when (type) {
        1 -> x / 2
        2 -> (x + 1) / 2
        else -> if (x == 0L) 0L else (x - 1) / 2
    }
}


data class Bambolini(var cur: Long, var a: Int, var b: Int, var c: Int)

//Fast Scan
var st = StringTokenizer("")
val br = BufferedReader(FileReader("calc.in"))

fun nextToken(): String {
    while (!st.hasMoreTokens()) {
        st = StringTokenizer(br.readLine())
    }
    return st.nextToken()
}

fun nextLine(): String = br.readLine()
fun nextInt(): Int = Integer.parseInt(nextToken())
fun nextDouble(): Double = nextToken().toDouble()
fun nextLong(): Long = nextToken().toLong()