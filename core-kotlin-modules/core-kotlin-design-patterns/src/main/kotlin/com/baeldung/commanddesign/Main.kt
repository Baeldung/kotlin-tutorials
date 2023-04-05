package com.baeldung.commanddesign

import com.baeldung.commanddesign.command.CopyCommand
import com.baeldung.commanddesign.command.CutCommand
import com.baeldung.commanddesign.command.PasteCommand
import com.baeldung.commanddesign.executor.TextEditorInvoker
import com.baeldung.commanddesign.receiver.TextEditor

fun main() {
    val clipboard = Clipboard()
    val editor = TextEditor("Baeldung")
    val invoker = TextEditorInvoker()

    invoker.executeCommand(CutCommand(editor, clipboard))
    invoker.executeCommand(CopyCommand(editor, clipboard))
    invoker.executeCommand(PasteCommand(editor, clipboard))

    editor.print() // prints "BaeldungBaeldun"

    invoker.undo() // undoes the cut command

    editor.print() // prints "BaeldungBaeldung"
}