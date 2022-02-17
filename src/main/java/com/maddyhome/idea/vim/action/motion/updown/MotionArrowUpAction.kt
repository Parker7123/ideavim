/*
 * IdeaVim - Vim emulator for IDEs based on the IntelliJ platform
 * Copyright (C) 2003-2022 The IdeaVim authors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */

package com.maddyhome.idea.vim.action.motion.updown

import com.intellij.openapi.actionSystem.DataContext
import com.intellij.openapi.editor.Caret
import com.intellij.openapi.editor.Editor
import com.maddyhome.idea.vim.VimPlugin
import com.maddyhome.idea.vim.action.ComplicatedKeysAction
import com.maddyhome.idea.vim.command.Argument
import com.maddyhome.idea.vim.command.Command
import com.maddyhome.idea.vim.command.MotionType
import com.maddyhome.idea.vim.handler.NonShiftedSpecialKeyHandler
import com.maddyhome.idea.vim.helper.EditorHelper
import com.maddyhome.idea.vim.helper.StringHelper.parseKeys
import com.maddyhome.idea.vim.newapi.ExecutionContext
import com.maddyhome.idea.vim.api.VimCaret
import com.maddyhome.idea.vim.newapi.VimEditor
import com.maddyhome.idea.vim.newapi.ij
import java.awt.event.KeyEvent
import javax.swing.KeyStroke

class MotionArrowUpAction : NonShiftedSpecialKeyHandler(), ComplicatedKeysAction {
  override val motionType: MotionType = MotionType.LINE_WISE

  override val keyStrokesSet: Set<List<KeyStroke>> =
    setOf(parseKeys("<Up>"), listOf(KeyStroke.getKeyStroke(KeyEvent.VK_KP_UP, 0)))

  private var col: Int = 0

  override fun offset(
    editor: Editor,
    caret: Caret,
    context: DataContext,
    count: Int,
    rawCount: Int,
    argument: Argument?,
  ): Int {
    return VimPlugin.getMotion().moveCaretVertical(editor, caret, -count)
  }

  override fun preOffsetComputation(editor: VimEditor, caret: VimCaret, context: ExecutionContext, cmd: Command): Boolean {
    col = EditorHelper.prepareLastColumn(caret.ij)
    return true
  }

  override fun postMove(editor: VimEditor, caret: VimCaret, context: ExecutionContext, cmd: Command) {
    EditorHelper.updateLastColumn(caret.ij, col)
  }
}
