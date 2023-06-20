package dz.nexatech.reporter

import com.google.common.truth.Truth.assertThat
import dz.nexatech.reporter.util.ui.formatTemplateDate
import dz.nexatech.reporter.util.ui.parseTemplateDate
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    val testEpoch = 1687258800000L
    val testDate = "20 Juin 2023"
    val epochEveDate = "31 Décembre 1969"

    @Test
    fun testTemplateDateFormatting() {
        assertThat(formatTemplateDate(testEpoch)).isEqualTo(testDate)
        assertThat(formatTemplateDate(null)).isNull()
        assertThat(formatTemplateDate(-1)).isEqualTo(epochEveDate)
    }

    @Test
    fun testTemplateDateParsingErrors() {
        assertThat(parseTemplateDate("")).isNull()
        assertThat(parseTemplateDate("123")).isNull()
        assertThat(parseTemplateDate("1234567890123")).isNull()
    }

    @Test
    fun testParsingEpoch() {
        assertThat(parseTemplateDate(testDate)).isEqualTo(testEpoch)
    }

    @Test
    fun testParsingBothWays() {
        assertThat(formatTemplateDate(parseTemplateDate(testDate))).isEqualTo(testDate)
    }
}