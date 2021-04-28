package com.phro.cardscanpoc

import android.app.Application
import com.microblink.blinkcard.MicroblinkSDK

class App : Application() {

    companion object {
        const val REQUEST_CODE = 1
    }

    override fun onCreate() {
        super.onCreate()
//        val file = this::class.java.getResource().readText(Charsets.UTF_8)
//        MicroblinkSDK.setLicenseKey(file, this)
        MicroblinkSDK.setLicenseKey("sRwAAAAUY29tLnBocm8uY2FyZHNjYW5wb2NnVNaVpIz6v6ld2XrJDrJQmA/LqA5QLthf617lwzpJuweyVTI9SnKmIfS202smcB9mZYH1zgMS2WJV5SXBiwF7/pf/ZdIRahcqOSKmdpJOgjlYj3pu8weJRgXZ7kdF+oPq42vN+AemGf3OYBl8W436lRL75hb30z0HhAopJCfy8cb6ev4CYQ6GM56N/VuU6Mxha+DVRygcCvfniSN+/BUtu4E/C1TFyV5fz31dkj7E", this)
//        MicroblinkSDK.setLicenseFile(
//            R.ra,
//            this
//        )
    }
}