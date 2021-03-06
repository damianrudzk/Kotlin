package examples

internal class Fibonacci private constructor(){

    companion object {
        val fibonacciSeq = sequence {
            var a = 0; var b = 1
            yield(1)

            while (true) {
                val count = a + b; a = b; b = count
                yield(count)
            }
        }
    }

}