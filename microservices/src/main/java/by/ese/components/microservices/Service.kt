package by.ese.components.microservices

import java.io.Serializable

/**
 * Service name & location.
 */
class Service : Serializable {

    constructor()
    constructor(name: String, url: String)

    /**
     * Service name.
     */
    var name: String? = null

    /**
     * Service URL.
     */
    var url: String? = null

    override fun toString(): String {
        return "Service{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}'
    }

    companion object {
        private const val serialVersionUID = -6L
    }


}