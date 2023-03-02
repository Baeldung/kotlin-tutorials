package com.baeldung.commanddesign.executor

import com.baeldung.commanddesign.command.Command

class TextEditorInvoker {
    private val commands = mutableListOf<Command>()

    fun executeCommand(command: Command) {
        commands.add(command)
        command.execute()
    }

    fun undo() {
        if (commands.isNotEmpty()) {
            commands.removeLast().undo()
        }
    }
}