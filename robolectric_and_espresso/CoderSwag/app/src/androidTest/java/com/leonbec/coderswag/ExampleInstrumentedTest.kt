package com.leonbec.coderswag

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.leonbec.coderswag.Controller.MainActivity
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
//    @Test
//    fun useAppContext() {
//        // Context of the app under test.
//        val appContext = InstrumentationRegistry.getTargetContext()
//        assertEquals("com.leonbec.coderswag", appContext.packageName)
//    }

    private lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun setUp() {
        Intents.init()
        val intent = Intent(
//                ApplicationProvider.getApplicationContext(),
                InstrumentationRegistry.getInstrumentation().context,
                MainActivity::class.java
        )
        scenario = ActivityScenario.launch<MainActivity>(intent)
    }

    @Test
    fun verifyTitle() {
//        val intent = Intent(
//                InstrumentationRegistry.getTargetContext(),
//                MainActivity::class.java
//        )
//        scenario = ActivityScenario.launch(intent)
        
        Espresso.onView(
                ViewMatchers.withId((R.id.textView))
        ).check(
                ViewAssertions.matches(ViewMatchers.withText("Categories"))
        )
    }

}
