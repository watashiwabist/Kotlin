import java.io.*
import java.util.*
import java.lang.*


fun main(args: Array<String>) {
    with(PrintWriter("game.out")) {
        val n = nextInt()
        val k = nextLong()
        var arr = Array(n) { nextLong() }
        arr.sort()
        var count = 0L

        when (k) {
            in 1..1 -> {
                var was = TreeMap<Long, String>()
                for (i in 0 until n) {
                    if (was.contains(arr[i])) continue
                    was[arr[i]] = "True"
                    var pointer = binSearch(arr[i] * k, arr)
                    if (pointer - i < 2) continue
                    var m = (pointer - i).toLong() + 1
                    count += if (m / 3 >= 1) 1 else 0
                }
            }
            else -> {
                for (i in 0 until n) {
                    if (i + 3 > arr.size) continue
                    var pointer = binSearch(arr[i] * k, arr)
                    var m = (pointer - i).toLong()
                    count += (m * (m - 1)) * 3

                }
            }
        }
        print(count)
        close()
    }
}

fun binSearch(x: Long, s: Array<Long>): Int {
    var l = 0
    var r = s.size
    while (l + 1 < r) {
        var m = (l + r) / 2
        if (s[m] <= x) l = m
        else r = m
    }
    return l
}


//Fast Scan
var st = StringTokenizer("")
val br = BufferedReader(FileReader("game.in"))

fun nextToken(): String {
    while (!st.hasMoreTokens()) {
        st = StringTokenizer(br.readLine())
    }
    return st.nextToken()
}

fun nextInt(): Int = Integer.parseInt(nextToken())
fun nextLong(): Long = nextToken().toLong()