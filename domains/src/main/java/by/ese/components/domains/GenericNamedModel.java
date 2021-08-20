package by.ese.components.domains;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotEmpty;

/**
 * Created by Valentin on 12.05.16.
 */
@MappedSuperclass
public abstract class GenericNamedModel extends GenericModel {

    @NotEmpty
    @Column(name = "name")
    protected String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
