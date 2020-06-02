package com.innovattic

import com.android.tools.lint.checks.infrastructure.TestFile
import com.android.tools.lint.checks.infrastructure.TestLintTask.lint
import com.innovattic.lint.multiplesyntheticimport.IssueRegistry.Companion.MultipleSyntheticImportIssue
import com.innovattic.TestFiles.MULTIPLE_SYNTHETIC_IMPORT_CLASS
import com.innovattic.TestFiles.SINGLE_SYNTHETIC_IMPORT_CLASS
import org.junit.Test

class DetectorTest {
    @Test
    fun testMultipleSyntheticImport() {
        val expectation = """
                        src/android/app/TestFragment.kt:8: Warning: You should only have 1 synthetic import in each class [MultipleSyntheticImportId]
                        import kotlinx.android.synthetic.main.view_balloon_text.*
                        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                        0 errors, 1 warnings
                        """.trimIndent()
        testFile(MULTIPLE_SYNTHETIC_IMPORT_CLASS, expectation)
    }

    @Test
    fun testSingleSyntheticImport() {
        testFile(SINGLE_SYNTHETIC_IMPORT_CLASS, "No warnings.")
    }

    private fun testFile(file: TestFile, expectation: String) {
        lint().files(file)
                .allowMissingSdk()
                .issues(MultipleSyntheticImportIssue)
                .run()
                .expect(expectation)
    }
}
