@file:Suppress("UnstableApiUsage")

package com.innovattic.lint.multiplesyntheticimport

import com.android.tools.lint.client.api.UElementHandler
import com.android.tools.lint.detector.api.Detector
import com.android.tools.lint.detector.api.JavaContext
import com.android.tools.lint.detector.api.SourceCodeScanner
import com.innovattic.lint.multiplesyntheticimport.IssueRegistry.Companion.MultipleSyntheticImportIssue
import org.jetbrains.uast.UElement
import org.jetbrains.uast.UImportStatement

class MultipleSyntheticImportDetector : Detector(), SourceCodeScanner {

    override fun createUastHandler(context: JavaContext) = object : UElementHandler() {
        private val syntheticLayouts = mutableSetOf<String>()

        override fun visitImportStatement(node: UImportStatement) {
            node.importReference?.let { importReference ->
                val importSource = importReference.asSourceString()
                if (importSource.startsWith(SYNTHETIC_IMPORT_PATTERN)) {
                    val viewImport = importSource.substring(SYNTHETIC_IMPORT_PATTERN.length)
                    val layoutName = viewImport.split('.').first()
                    syntheticLayouts.add(layoutName)

                    if (syntheticLayouts.size > 1) {
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
        /**
         * When using wildcard imports, the source string is provided without the '*'.
         * So 'kotlinx.android.synthetic.main.layout.*' becomes 'kotlinx.android.synthetic.main.layout'
         * When using specific imports, everything is kept:
         * 'kotlinx.android.synthetic.main.layout.view'
         */
        private const val SYNTHETIC_IMPORT_PATTERN = "kotlinx.android.synthetic.main."
        const val MULTIPLE_SYNTHETIC_IMPORT_MESSAGE = "You should only have 1 synthetic import in each file. Having more than 1 synthetic import in a file is usually a mistake. If not a mistake, it is in any case confusing and therefore discouraged."
    }
}
