package by.ese.components.microservices

/**
 * Microservices Registry.
 */
interface Registry {
    fun register(service: Service?): RegistrationStatus?
    fun getService(name: String?): Service?
    val services: List<Service?>?

    companion object {
        const val REGISTER = "/bind"
        const val GET_SERVICE = "/lookup"
        const val GET_SERVICES = "/list"
    }
}