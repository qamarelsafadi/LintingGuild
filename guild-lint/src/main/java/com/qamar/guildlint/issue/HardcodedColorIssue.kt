package com.qamar.guildlint.issue

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.detector.api.CURRENT_API
import com.android.tools.lint.detector.api.Category
import com.android.tools.lint.detector.api.Implementation
import com.android.tools.lint.detector.api.Issue
import com.android.tools.lint.detector.api.Scope
import com.android.tools.lint.detector.api.Severity
import com.qamar.guildlint.detector.HardcodedColorDetector

@Suppress("UnstableApiUsage")
class HardcodedColorIssue : IssueRegistry() {
    override val issues = listOf(MyHardcodedColorIssue.ISSUE)

    override val api: Int
        get() = CURRENT_API

    override val minApi: Int
        get() = 8
}

object MyHardcodedColorIssue {
    private const val ID = "HardcodedColor"
    private const val PRIORITY = 10
    private const val DESCRIPTION = "Hardcoded colors should be avoided"
    private const val EXPLANATION = """
        Use colors from colors file and do not hard code it 
    """
    private val CATEGORY = Category.CORRECTNESS
    private val SEVERITY = Severity.ERROR

    val ISSUE = Issue.create(
        ID,
        briefDescription = DESCRIPTION,
        explanation = EXPLANATION,
        category = CATEGORY,
        priority = PRIORITY,
        severity = SEVERITY,
        implementation = Implementation(
            HardcodedColorDetector::class.java,
            Scope.JAVA_FILE_SCOPE
        )
    )
}