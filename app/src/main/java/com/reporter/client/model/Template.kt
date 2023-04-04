package com.reporter.client.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.reporter.util.model.Localizer

const val REPORT_TEMPLATE_TABLE = "report_template"

@Entity(
    tableName = REPORT_TEMPLATE_TABLE,
)
data class Template(
    @PrimaryKey
    val name: String,
    val content: String,
    val label_en: String?,
    val label_ar: String?,
    val label_fr: String?,
    val desc_en: String?,
    val desc_ar: String?,
    val desc_fr: String?,
    val lastUpdate: Long,
) {
    @delegate:Ignore
    val label by lazy {
        Localizer.inPrimaryLang(label_en, label_ar, label_fr)
    }

    @delegate:Ignore
    val desc by lazy {
        Localizer.inPrimaryLang(desc_en, desc_ar, desc_fr)
    }

    override fun equals(other: Any?) =
        this === other || (other is Template && this.name == other.name)

    override fun hashCode() = name.hashCode()

    override fun toString() = "ReportTemplate(name='$name',label=$label)"
}