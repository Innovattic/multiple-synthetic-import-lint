package com.innovattic

import com.android.tools.lint.checks.infrastructure.LintDetectorTest.kotlin
import com.android.tools.lint.checks.infrastructure.TestFile

object TestFiles {

    val MULTIPLE_SYNTHETIC_IMPORT_CLASS: TestFile = kotlin(
            "android/app/TestFragment.kt",
            """
                package android.app

                import android.os.Bundle
                import android.view.View
                import androidx.fragment.app.Fragment
                import com.innovattic.saba.R
                import kotlinx.android.synthetic.main.fragment_intakes.*
                import kotlinx.android.synthetic.main.view_balloon_text.*
                
                class TestFragment : Fragment(R.layout.fragment_intakes) {
                    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
                        super.onViewCreated(view, savedInstanceState)
                        questionnaireButton.text = "aaa"
                        textView.text = "bbb"
                    }
                }
            """)
            .indented().within("src")

    val SINGLE_SYNTHETIC_IMPORT_CLASS: TestFile = kotlin(
            "android/app/TestFragment.kt",
            """
                package android.app

                import android.os.Bundle
                import android.view.View
                import androidx.fragment.app.Fragment
                import com.innovattic.saba.R
                import kotlinx.android.synthetic.main.fragment_intakes.*
                
                class TestFragment : Fragment(R.layout.fragment_intakes) {
                    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
                        super.onViewCreated(view, savedInstanceState)
                        questionnaireButton.text = "aaa"
                    }
                }
            """)
            .indented().within("src")
}
