@file:Suppress("UnstableApiUsage")

package com.innovattic

import com.android.tools.lint.client.api.UElementHandler
import com.android.tools.lint.detector.api.Detector
import com.android.tools.lint.detector.api.JavaContext
import com.android.tools.lint.detector.api.SourceCodeScanner
import com.innovattic.IssueRegistry.Companion.MultipleSyntheticImportIssue
import org.jetbrains.uast.UElement
import org.jetbrains.uast.UImportStatement

class MultipleSyntheticImportDetector : Detector(), SourceCodeScanner {

    override fun createUastHandler(context: JavaContext) = object : UElementHandler() {
        private var syntheticImportCount = 0

        override fun visitImportStatement(node: UImportStatement) {
            node.importReference?.let { importReference ->
                if (importReference.asSourceString().matches(SYNTHETIC_IMPORT.toRegex())) {
                    syntheticImportCount++

                    if (syntheticImportCount > 1) {
                        context.report(
                                MultipleSyntheticImportIssue,
                                node,
                                context.getLocation(node),
                                MULTIPLE_SYNTHETIC_IMPORT_MESSAGE
                        )
                    }
                }
            }
        }
    }

    override fun getApplicableUastTypes(): List<Class<out UElement>>? {
        return listOf(UImportStatement::class.java)
    }

    companion object {
        private const val SYNTHETIC_IMPORT = "kotlinx.android.synthetic.main.*"
        const val MULTIPLE_SYNTHETIC_IMPORT_MESSAGE = "You should only have 1 synthetic import in each file. Having more than 1 synthetic import in a file is usually an mistake. If not a mistake, it is in any case confusing and therefore discouraged."
    }
}
