package by.ese.components.domains

import javax.persistence.Column
import javax.persistence.MappedSuperclass
import javax.validation.constraints.NotEmpty

/**
 * Created by Valentin on 12.05.16.
 */
@MappedSuperclass
actual abstract class NamedModel : IdentityModel() {
    @Column(name = "name")
    actual var name: @NotEmpty String? = null
}