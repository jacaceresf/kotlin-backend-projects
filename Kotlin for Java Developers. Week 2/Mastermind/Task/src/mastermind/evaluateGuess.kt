package mastermind

data class Evaluation(val rightPosition: Int, val wrongPosition: Int)

fun evaluateGuess(secret: String, guess: String): Evaluation {

    var rightPosition = 0
    var wrongPosition = 0

    val rights = HashMap<Char, ArrayList<Int>>()
    val guessMap = HashMap<Char, ArrayList<Int>>()
    val wrongCounted = mutableListOf<Char>()

    for ((index, letter) in secret.withIndex()) {
        if (letter in rights.keys) {
            val lst = rights[letter]!!
            lst.add(index)
            rights[letter] = lst
        } else {
            val lst = ArrayList<Int>()
            lst.add(index)
            rights[letter] = lst
        }
    }

    for ((index, letter) in guess.withIndex()) {
        if (letter in guessMap.keys) {
            val lst = guessMap[letter]!!
            lst.add(index)
            guessMap[letter] = lst
        } else {
            val lst = ArrayList<Int>()
            lst.add(index)
            guessMap[letter] = lst
        }
    }

    for ((letter, positions) in guessMap) {

        if (letter in rights.keys) {
            val rightPositions = rights[letter]!!
            var it = 0
            for (position in positions) {
                if (position in rightPositions) {
                    rightPosition++
                } else if (rightPositions.size == positions.size  ) {
                    wrongPosition++
                } else if (positions.size > rightPositions.size){
                    wrongPosition += positions.size - rightPositions.size
                    break
                }
            }
        }
    }


    return Evaluation(rightPosition = rightPosition, wrongPosition = wrongPosition)

}
