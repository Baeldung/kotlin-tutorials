package com.baeldung.commanddesign.command

import com.baeldung.commanddesign.Clipboard
import com.baeldung.commanddesign.receiver.TextEditor

class PasteCommand(private val receiver: TextEditor, private val clipboard: Clipboard) : Command {
    override fun execute() {
        receiver.write(clipboard.content)
    }

    override fun undo() {
        receiver.delete(clipboard.content)
    }
}