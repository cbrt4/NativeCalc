package com.alex.nativecalc

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var cppCalled = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nativeTextButton.setOnClickListener {
            if (!cppCalled) {
                sampleTextView.text = stringFromJNI()
                nativeTextButton.text = getString(R.string.get_text_from_kotlin)
            } else {
                sampleTextView.text = stringFromKotlin()
                nativeTextButton.text = getString(R.string.get_text_from_cpp_lib)
            }

            cppCalled = !cppCalled
        }

        Toast.makeText(this, calculate(3, 7).toString(), Toast.LENGTH_SHORT).show()
    }

    private fun stringFromKotlin() : String {
        return getString(R.string.hello_from_kotlin)
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    private external fun stringFromJNI(): String
    private external fun calculate(a: Int, b : Int): Int

    companion object {

        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
            System.loadLibrary("calc")
        }
    }
}
