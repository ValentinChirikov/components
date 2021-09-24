package by.ese.components.domains

import java.io.Serializable
import javax.persistence.*

/**
 * Created by Valentin on 08.04.16.
 */
@MappedSuperclass
actual abstract class IdentityModel : Serializable {
    /**
     * Entity ID
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    actual var id: Long? = null
}