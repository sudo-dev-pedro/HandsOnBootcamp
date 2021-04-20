package com.leonardoamurca.getting_started_robolectric

import android.app.Activity
import android.app.Application
import android.app.NotificationChannel
import android.os.Build
import android.widget.Button
import android.widget.TextView
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.Robolectric
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

@RunWith(RobolectricTestRunner::class)
// Rode a classe com essa versão do SDK
@Config(sdk = [Build.VERSION_CODES.P])
class ExampleUnitTest {

    lateinit var activity: Activity

    @Before
    fun setUp() {
        // Instanciando a Activity
        activity = Robolectric.buildActivity(MainActivity::class.java).create().get()
    }

    @Test
    fun verifyHelloWorld() {
        val textView: TextView = activity.findViewById(R.id.helloWorldTextView)

        assertEquals("Hello World!", textView.text)
    }

    @Test
    fun vefifyTextViewForVersionIsGreaterThanPie() {
        val btn: Button = activity.findViewById(R.id.simpleButton)
        val textView: TextView = activity.findViewById(R.id.helloWorldTextView)

        btn.performClick()

        assertEquals("Ola mundo", textView.text)
    }

    // Teste rodando sob uma config específica.
    // É útil para testar funcionalidades específicas de um SDK
    @Test
    @Config(sdk = [Build.VERSION_CODES.O])
    fun vefifyTextViewForVersionIsLessThanPie() {
        val btn: Button = activity.findViewById(R.id.simpleButton)
        val textView: TextView = activity.findViewById(R.id.helloWorldTextView)

        btn.performClick()

        assertEquals("Less than pie", textView.text)
    }

    @Test
    @Config(qualifiers = "pt")
    fun vefifyStringsForNonEnglishLanguage() {
        val context: Application = RuntimeEnvironment.application

        assertEquals("Not Overridden", context.getString(R.string.not_overridden))
        assertEquals("English qualified value A", context.getString(R.string.overridden))
        assertEquals("English qualified value A", context.getString(R.string.overridden_twice))
    }


    // Landscape Mode
    @Test
    @Config(qualifiers = "en-land")
    fun vefifyStringsForEnglishLanguage() {
        val context: Application = RuntimeEnvironment.application

        assertEquals("Not Overridden", context.getString(R.string.not_overridden))
        assertEquals("English qualified value", context.getString(R.string.overridden))
        assertEquals("English qualified value", context.getString(R.string.overridden_twice))
    }

    // Portrait Mode
    // Checando as Strings quando em Portrait Mode
    @Test
    @Config(qualifiers = "en-port")
    fun vefifyStringsForEnglishPortLanguage() {
        val context: Application = RuntimeEnvironment.application

        assertEquals("Not Overridden", context.getString(R.string.not_overridden))
        assertEquals("English qualified value", context.getString(R.string.overridden))
        assertEquals(
            "English portrait qualified value",
            context.getString(R.string.overridden_twice)
        )
    }
}