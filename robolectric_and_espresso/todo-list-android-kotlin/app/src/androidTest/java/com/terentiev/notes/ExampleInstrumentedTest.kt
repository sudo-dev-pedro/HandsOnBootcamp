package com.terentiev.notes

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.terentiev.notes.ui.CreateNoteActivity
import com.terentiev.notes.ui.NoteListActivity
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Before

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4ClassRunner::class)
class ExampleInstrumentedTest {

    private lateinit var scenario: ActivityScenario<NoteListActivity>

    @Before
    fun setUp() {
        Intents.init()
        val intent = Intent(
            ApplicationProvider.getApplicationContext(),
            NoteListActivity::class.java
        )
        scenario = ActivityScenario.launch<NoteListActivity>(intent)
    }

    @Test
    fun verifyToolbarText() {

        Espresso.onView(ViewMatchers.withId(R.id.toolbar))
            .check(
                ViewAssertions.matches(ViewMatchers.isDisplayed())
            )
//            .check(
//                ViewAssertions.matches(ViewMatchers.withText(R.string.app_name)
//                )
//            )

        // Context of the app under test.
//        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
//        assertEquals("com.terentiev.notes", appContext.packageName)
    }

    @Test
    fun verifyFABClick() {
        Espresso.onView(
            ViewMatchers.withId(R.id.fab_new_todo)
        ).check(
            ViewAssertions.matches(ViewMatchers.isDisplayed())
        ).check(
            ViewAssertions.matches(ViewMatchers.isClickable())
        ).perform(
            ViewActions.click()
        )

        Intents.intended(IntentMatchers.hasComponent(CreateNoteActivity::class.java.name))
        IntentMatchers.hasExtra("Pedro", "PEDRO")

        // Checar se há extras (Key, Value)
        //IntentMatchers.hasExtra()
    }

    @After
    fun teardown() {
        Intents.release()
        scenario.close()
    }
}
