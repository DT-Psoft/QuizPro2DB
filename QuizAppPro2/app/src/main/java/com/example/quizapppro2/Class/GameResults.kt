package com.example.quizapppro2.Class

class GameResults {
    var currentPlayer: Player = Player()
    var scoreboardName = MutableList(6) { String() }
    var scoreboardPuntuation = MutableList(6) { String() }
    var scoreboardPlayer = MutableList(6) { Player() }
    private var cont = 0

    init {
        scoreboardName.clear()
        scoreboardPlayer.clear()
        scoreboardPuntuation.clear()
    }

    fun AddPlayer() {
        var aux = scoreboardPlayer.find { x -> x.score == currentPlayer.score }
        //si esto su cumple es que hay un cheater que tiene igual score
        if (aux != null && aux.cheater) {
            var i = scoreboardPlayer.indexOf(aux)
            scoreboardPlayer.add(i, currentPlayer)
        } else {
            scoreboardPlayer.add(currentPlayer)
            cont++

        }
        currentPlayer = Player()
        scoreboardPlayer.sortByDescending{ x -> x.score }
        if(cont == 5){
            scoreboardPlayer.removeAll { x -> scoreboardPlayer.indexOf(x) > 5 }
        }
        scoreboardName.clear()
        scoreboardPlayer.forEach{x->scoreboardName.add(x.toString())}
    }

}