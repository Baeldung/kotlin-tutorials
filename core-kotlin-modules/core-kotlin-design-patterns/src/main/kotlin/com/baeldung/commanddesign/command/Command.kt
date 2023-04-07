package com.baeldung.commanddesign.command

interface Command {
    fun execute()
    fun undo()
}