package com.baeldung.log4j

import Game

fun main() {
    val game = Game()
    val player = Player("Baeldung")
    val enemy = Enemy()

    game.startGame()
    game.loadLevel("Level 1")

    player.joinGame()
    enemy.shoot()

    player.takeDamage(100)
    GameUtil().logEvent("Player died")

    game.loadLevel("")
}