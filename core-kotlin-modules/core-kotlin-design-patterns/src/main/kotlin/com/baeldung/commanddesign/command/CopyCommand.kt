package com.baeldung.commanddesign.command

import com.baeldung.commanddesign.Clipboard
import com.baeldung.commanddesign.receiver.TextEditor

class CopyCommand(private val receiver: TextEditor, private val clipboard: Clipboard) : Command {
    override fun execute() {
        clipboard.content = receiver.copy()
    }

    override fun undo() {
        clipboard.content = ""
    }
}