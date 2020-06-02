package com.innovattic.lint.multiplesyntheticimport

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.detector.api.*
import com.innovattic.lint.multiplesyntheticimport.MultipleSyntheticImportDetector.Companion.MULTIPLE_SYNTHETIC_IMPORT_MESSAGE

@Suppress("UnstableApiUsage")
class IssueRegistry : IssueRegistry() {
    override val issues = listOf(MultipleSyntheticImportIssue)

    override val api: Int = CURRENT_API

    companion object {
        private const val MULTIPLE_SYNTHETIC_IMPORT_ID = "MultipleSyntheticImport"
        private const val MULTIPLE_SYNTHETIC_IMPORT_DESCRIPTION = "Multiple synthetic imports are discouraged"

        val MultipleSyntheticImportIssue = Issue.create(
                id = MULTIPLE_SYNTHETIC_IMPORT_ID,
                briefDescription = MULTIPLE_SYNTHETIC_IMPORT_DESCRIPTION,
                explanation = MULTIPLE_SYNTHETIC_IMPORT_MESSAGE,
                category = Category.CORRECTNESS,
                priority = 6,
                severity = Severity.ERROR,
                implementation = Implementation(MultipleSyntheticImportDetector::class.java, Scope.JAVA_FILE_SCOPE)
        )
    }
}
