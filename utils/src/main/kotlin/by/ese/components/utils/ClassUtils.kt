/*
 * (C) Copyright 30 окт. 2018 г. Valentin Chirikov (http://ese.by/) 
 * valc@ese.by +375 44 7629763
 */
package by.ese.components.utils

import java.lang.reflect.Field

/**
 *
 * @author Valentin
 */
object ClassUtils {
    /**
     * Convert "niceNameMethod" to "Nice name method"
     *
     * @param name
     * @return
     */
    fun nicify(name: String): String {
        val sb = StringBuilder()
        return if (name.length > 1) {
            sb.append(Character.toUpperCase(name[0]))
            for (i in 1 until name.length) {
                if (Character.isUpperCase(name[i])) {
                    sb.append(" ").append(Character.toLowerCase(name[i]))
                } else {
                    sb.append(name[i])
                }
            }
            sb.toString()
        } else {
            name
        }
    }

    private fun searchField(fields: Array<Field>, name: String): Field? {
        for (field in fields) {
            if (field.name == name) {
                return field
            }
        }
        return null
    }

    /**
     * @param clazz
     * @param name
     * @return
     * @throws NoSuchFieldException
     */
    @Deprecated(
        """@see com.querydsl.core.util.ReflectionUtils or spring
      findField
      """
    )
    @Throws(
        NoSuchFieldException::class
    )
    fun getField(clazz: Class<*>, name: String): Field {
        var searchableClass = clazz
        var field: Field?
        field = searchField(searchableClass.declaredFields, name)
        if (field == null) {
            while (searchableClass.superclass != null && field == null) {
                searchableClass = searchableClass.superclass
                field = searchField(searchableClass.declaredFields, name)
            }
        }
        return field ?: throw NoSuchFieldException()
    }
}