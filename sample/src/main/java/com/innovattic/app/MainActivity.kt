package com.innovattic.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main_1.textView1
import kotlinx.android.synthetic.main.activity_main_1.textView2
import kotlinx.android.synthetic.main.activity_main_2.textView3
import kotlinx.android.synthetic.main.activity_main_2.textView4
import kotlinx.android.synthetic.main.activity_main_3.*

class MainActivity : AppCompatActivity(R.layout.activity_main_1) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        textView1.text = "a"
        textView2.text = "a"
        textView3.text = "a"
        textView4.text = "a"
        textView5.text = "a"
        textView6.text = "a"
    }
}
