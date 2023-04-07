package com.baeldung.commanddesign

import com.baeldung.commanddesign.command.CopyCommand
import com.baeldung.commanddesign.command.CutCommand
import com.baeldung.commanddesign.command.PasteCommand
import com.baeldung.commanddesign.executor.TextEditorInvoker
import com.baeldung.commanddesign.receiver.TextEditor
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CommandDesignPatternUnitTest {

    @Test
    fun `executes cut command on a text editor`() {
        // given
        val clipboard = Clipboard()
        val editor = TextEditor("Baeldung")
        val invoker = TextEditorInvoker()

        // when
        invoker.executeCommand(CutCommand(editor, clipboard))

        // then
        assertThat(editor.content).isEqualTo("Baeldun")
    }

    @Test
    fun `executes copy and paste command on a text editor`() {
        // given
        val clipboard = Clipboard()
        val editor = TextEditor("Baeldung")
        val invoker = TextEditorInvoker()

        // when
        invoker.executeCommand(CopyCommand(editor, clipboard))
        invoker.executeCommand(PasteCommand(editor, clipboard))

        // then
        assertThat(editor.content).isEqualTo("BaeldungBaeldung")
    }

    @Test
    fun `undoes paste command`() {
        // given
        val clipboard = Clipboard()
        val editor = TextEditor("Baeldung")
        val invoker = TextEditorInvoker()

        invoker.executeCommand(CopyCommand(editor, clipboard))
        invoker.executeCommand(PasteCommand(editor, clipboard))

        // when
        invoker.undo()

        // then
        assertThat(editor.content).isEqualTo("Baeldung")
    }
}