package by.ese.components.domains

import by.ese.components.utils.ClassUtils

/**
 * ETableModel column description
 *
 * @author Valentin
 */
class ColumnDescription constructor(
    name: String?,
    caption: String?,
    width: Int = 75,
    isEditable: Boolean = true
) {
    /**
     * Domain model property name
     * Column identifier
     */
    var name: String? = null

    /**
     * Column caption(header)
     */
    var caption: String? = null

    /**
     * Column width
     */
    var width = 0

    /**
     * Is column editable
     */
    var isEditable = false

    constructor(name: String) : this(name, ClassUtils.nicify(name))
    constructor(name: String, width: Int) : this(name, ClassUtils.nicify(name), width, true)
    constructor(name: String, isEditable: Boolean) : this(name, ClassUtils.nicify(name), 75, isEditable)
    constructor(name: String, width: Int, isEditable: Boolean) : this(
        name,
        ClassUtils.nicify(name),
        width,
        isEditable
    )

    /**
     *
     * @param name Column name
     * @param caption Column caption
     * @param width Column width
     * @param isEditable Is column editable
     */
    init {
        this.name = name
        this.caption = caption
        this.width = width
        this.isEditable = isEditable
    }
}