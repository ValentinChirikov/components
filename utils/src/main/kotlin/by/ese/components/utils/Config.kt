package by.ese.components.utils

import java.io.*
import java.util.*

/**
 * Helper class to read properties, either from external resources or inner, bundled with app
 */
class Config(filePath: String) : Properties() {
    /**
     * Read properties either from external file or from classpath in order:
     * 1.try to read external file
     * 2.try to read resource from classpath
     *
     * @param filePath path to file
     */
    private fun readProperties(filePath: String) {
        val inputStream = getConfigInputStream(filePath)
        try {
            load(inputStream)
        } catch (ignored: IOException) {
        }
    }

    companion object {
        /**
         * If code runs from jar.
         * @return true if runs from jar file.
         */
        fun jarFile(cls: Class<*>): Boolean {
            return File(cls.protectionDomain.codeSource.location.path).isFile
        }

        fun getConfigInputStream(filePath: String): InputStream {
            val jarFile = File(Config::class.java.protectionDomain.codeSource.location.path)
            val inputStream: InputStream
            inputStream = if (jarFile.isFile) {  // Run with JAR file
                val propertiesPath = jarFile.parentFile.absolutePath
                try {  // Check if external config available
                    FileInputStream("$propertiesPath/$filePath")
                } catch (e: FileNotFoundException) { // Use bundled config
                    Config::class.java.classLoader.getResourceAsStream(filePath)
                }
            } else { // Run with IDE
                val url = Config::class.java.getResource("/config")
                if (url != null) { // if external config available
                    Config::class.java.getResourceAsStream("/$filePath")
                } else { // Use bundled config
                    Config::class.java.getResourceAsStream(filePath)
                }
            }
            return inputStream
        }
    }

    init {
        readProperties(filePath)
    }
}