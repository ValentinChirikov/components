package by.ese.components.utils

import java.util.Properties
import java.io.IOException
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.lang.StringBuilder
import kotlin.Throws
import java.lang.NoSuchFieldException
import by.ese.components.utils.ClassUtils
import by.ese.components.utils.HtmlHighlighter
import java.lang.Exception
import java.util.regex.Pattern

object HtmlHighlighter {
    private const val HighLightTemplate = "<span style='background:yellow;'>$1</span>"
    fun highlightText(text: String, textToHighlight: String): String {
        var text = text
        if (textToHighlight.length == 0) {
            return text
        }
        text = try {
            text.replace("(?i)(" + Pattern.quote(textToHighlight) + ")".toRegex(), HighLightTemplate)
        } catch (e: Exception) {
            return text
        }
        return "<html>$text</html>"
    }
}