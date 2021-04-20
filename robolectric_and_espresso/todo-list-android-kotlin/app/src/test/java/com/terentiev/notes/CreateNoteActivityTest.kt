package com.terentiev.notes

import com.terentiev.notes.ui.CreateNoteActivity
import com.terentiev.notes.ui.NoteListActivity
import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class CreateNoteActivityTest {

    private lateinit var noteListActivity: CreateNoteActivity

    @Before
    fun setup() {
        noteListActivity = Robolectric.buildActivity(CreateNoteActivity::class.java).create().get()
    }
}