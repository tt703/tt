package dz.nexatech.reporter.client.core

import dz.nexatech.reporter.client.common.AbstractLocalizer

const val TEMPLATE_TABLE = "template"
const val TEMPLATE_COLUMN_NAME = TEMPLATE_TABLE + "_name"
const val TEMPLATE_COLUMN_LABEL_EN = "label_en"
const val TEMPLATE_COLUMN_LABEL_AR = "label_ar"
const val TEMPLATE_COLUMN_LABEL_FR = "label_fr"
const val TEMPLATE_COLUMN_DESC_EN = "desc_en"
const val TEMPLATE_COLUMN_DESC_AR = "desc_ar"
const val TEMPLATE_COLUMN_DESC_FR = "desc_fr"
const val TEMPLATE_COLUMN_LAST_UPDATE = "last_update"

abstract class AbstractTemplate {
    abstract val name: String
    abstract val label_en: String?
    abstract val label_ar: String?
    abstract val label_fr: String?
    abstract val desc_en: String?
    abstract val desc_ar: String?
    abstract val desc_fr: String?
    abstract val lastUpdate: Long

    // localized properties
    abstract val label: String
    abstract val desc: String

    final override fun equals(other: Any?) =
        this === other || (other is AbstractTemplate && this.name == other.name)

    final override fun hashCode() = name.hashCode()

    override fun toString() = "Template(name='$name',label=$label)"

}

class SimpleTemplate(
    override val name: String,
    override val label_en: String?,
    override val label_ar: String?,
    override val label_fr: String?,
    override val desc_en: String?,
    override val desc_ar: String?,
    override val desc_fr: String?,
    override val lastUpdate: Long,
    private val localizer: AbstractLocalizer,
): AbstractTemplate() {
    override val label by lazy {
        localizer.inPrimaryLang(label_en, label_fr, label_ar)
    }
    override val desc by lazy {
        localizer.inPrimaryLang(desc_en, desc_fr, desc_ar)
    }
}
