package com.terentiev.notes

import android.util.Log
import androidx.appcompat.widget.Toolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.terentiev.notes.ui.NoteListActivity
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class NoteActivityTest {

    private lateinit var noteListActivity: NoteListActivity

    @Before
    fun setup() {
        noteListActivity = Robolectric.buildActivity(NoteListActivity::class.java).create().get()
    }

    @Test
    fun verifyToolbarAttributes() {
        val toolbar: Toolbar = noteListActivity.findViewById(R.id.toolbar)

        assertEquals("Notes", toolbar.title)
        assertTrue(toolbar.background.isVisible)
    }

    @Test
    fun verifyFABAttributes() {
        val fab: FloatingActionButton = noteListActivity.findViewById(R.id.fab_new_todo)

        assertTrue(fab.useCompatPadding)
//        assertEquals(fab.background.current, R.drawable.ic_note_add_white_24dp)
    }

}