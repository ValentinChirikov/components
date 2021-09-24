package by.ese.components.domains

/**
 * Created by Valentin on 12.05.16.
 */
actual abstract class NamedModel : IdentityModel() {
    actual var name: String? = null
}