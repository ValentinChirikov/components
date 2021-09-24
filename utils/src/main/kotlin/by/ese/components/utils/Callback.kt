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

/**
 * Created by Valentin on 02.03.2017.
 */
fun interface Callback {
    fun notifyCaller()

    companion object {
        val EMPTY = Callback {}
    }
}