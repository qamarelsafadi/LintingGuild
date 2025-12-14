package com.qamar.guildlint.detector

import com.android.tools.lint.client.api.UElementHandler
import com.android.tools.lint.detector.api.Detector
import com.android.tools.lint.detector.api.JavaContext
import com.android.tools.lint.detector.api.SourceCodeScanner
import com.qamar.guildlint.issue.MyHardcodedColorIssue
import org.jetbrains.uast.UCallExpression
import org.jetbrains.uast.UElement

class HardcodedColorDetector : Detector(),  SourceCodeScanner {

    // UAST stands for Unified Abstract Syntax Tree.
    override fun getApplicableUastTypes() = listOf(UCallExpression::class.java)

    override fun createUastHandler(context: JavaContext): UElementHandler =
        HardcodedColorVisitor(context)

    class HardcodedColorVisitor(private val context: JavaContext) : UElementHandler() {

        override fun visitCallExpression(node: UCallExpression) {
            if (node.methodName !in listOf("background", "border", "color", "tint")) return
             node.valueArguments.find { argument ->
                val source = argument.sourcePsi?.text
                source != null && isHardcodedColor(source)
            }?.let {
                reportIssue(it)
            }
        }

        private fun isHardcodedColor(source: String): Boolean {
            val hexColorRegex = """Color\s*\(\s*0[xX][0-9A-Fa-f]+\s*\)""".toRegex()
            return hexColorRegex.containsMatchIn(source)
        }

        private fun reportIssue(node: UElement) {
            context.report(
                MyHardcodedColorIssue.ISSUE,
                node,
                context.getLocation(node),
                "Avoid hardcoded colors. Use Them from Colors file only",
            )
        }
    }
}
