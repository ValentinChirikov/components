package by.ese.components.domains

/**
 *
 * @author Valentin
 */
interface Domain {
    fun model(): Class<*>?
    fun name(): String?
}