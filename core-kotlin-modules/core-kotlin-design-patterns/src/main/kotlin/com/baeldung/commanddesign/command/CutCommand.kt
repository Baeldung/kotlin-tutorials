package com.baeldung.commanddesign.command

import com.baeldung.commanddesign.Clipboard
import com.baeldung.commanddesign.receiver.TextEditor

class CutCommand(private val receiver: TextEditor, private val clipboard: Clipboard) : Command {
    override fun execute() {
        clipboard.content = receiver.cut()
    }

    override fun undo() {
        receiver.write(clipboard.content)
        clipboard.content = ""
    }
}