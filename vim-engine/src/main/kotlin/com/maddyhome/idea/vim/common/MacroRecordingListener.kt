/*
 * Copyright 2003-2023 The IdeaVim authors
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE.txt file or at
 * https://opensource.org/licenses/MIT.
 */

package com.maddyhome.idea.vim.common

import com.maddyhome.idea.vim.api.VimEditor

public interface MacroRecordingListener {
  public fun recordingStarted(editor: VimEditor, register: Char)
  public fun recordingFinished(editor: VimEditor, register: Char)
}