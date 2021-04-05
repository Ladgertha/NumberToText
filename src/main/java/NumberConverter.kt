import java.text.DecimalFormat
import kotlin.math.abs

class NumberConverter {
    private val tensNumbers = arrayOf(
            "",
            " десять",
            " двадцать",
            " тридцать",
            " сорок",
            " пятьдесят",
            " шестьдесят",
            " семьдесят",
            " восемьдесят",
            " девяносто"
    )

    private val uniqueNumbers = arrayOf(
            "",
            " один",
            " два",
            " три",
            " четыре",
            " пять",
            " шесть",
            " семь",
            " восемь",
            " девять",
            " десять",
            " одинадцать",
            " двенадцать",
            " тринадцать",
            " четырнадцать",
            " пятнадцять",
            " шестнадцять",
            " семнадцять",
            " восемнадцять",
            " девятнадцять"
    )

    private val uniqueFeminineNumbers = arrayOf(
            "",
            " одна",
            " две",
            " три",
            " четыре",
            " пять",
            " шесть",
            " семь",
            " восемь",
            " девять",
            " десять",
            " одинадцать",
            " двенадцать",
            " тринадцать",
            " четырнадцать",
            " пятнадцять",
            " шестнадцять",
            " семнадцять",
            " восемнадцять",
            " девятнадцять"
    )

    private val hundredsNumbers = arrayOf(
            "",
            " сто",
            " двести",
            " триста",
            " четыреста",
            " пятьсот",
            " шестьсот",
            " семьсот",
            " восемьсот",
            " девятьсот",
            "одна тысяча "
    )

    private val thousandDeclination = arrayOf(
            " тысяча ",
            " тысячи ",
            " тысяч "
    )

    private val millionDeclination = arrayOf(
            " миллион ",
            " миллиона ",
            " миллионов "
    )

    private val billionDeclination = arrayOf(
            " миллиард ",
            " миллиарда ",
            " миллиардов "
    )

    private val zeroString = "ноль"
    private val minus = "минус "
    private val thousand = 1000
    private val hundred = 100
    private val ten = 10
    private val twenty = 20

    fun convertNumberToString(number: Int): String {
        val negativeNumber = number < 0
        if (number == Int.MIN_VALUE) return convertNumberToString(number.toLong())
        val absoluteNumber = abs(number)
        return when {
            absoluteNumber == 0 -> zeroString
            absoluteNumber <= thousand -> {
                if (negativeNumber) minus.plus(convertLessThanOneThousand(absoluteNumber).trim())
                else convertLessThanOneThousand(absoluteNumber).trim()
            }
            else -> {
                if (negativeNumber) minus.plus(convert(absoluteNumber.toLong()).trim())
                else convert(absoluteNumber.toLong()).trim()
            }
        }
    }

    fun convertNumberToString(number: Long): String {
        val negativeNumber = number < 0
        val absoluteNumber = abs(number)
        return when {
            absoluteNumber == 0L -> zeroString
            absoluteNumber <= thousand -> {
                if (negativeNumber) minus.plus(convertLessThanOneThousand(absoluteNumber.toInt()).trim())
                else convertLessThanOneThousand(absoluteNumber.toInt()).trim()
            }
            else -> {
                if (negativeNumber) minus.plus(convert(absoluteNumber).trim())
                else convert(absoluteNumber).trim()
            }
        }
    }


    private fun convertLessThanOneThousand(number: Int, isFeminine: Boolean = false): String {
        val (hundredsNumber, lastWord) = getNumberAndLastWord(number, isFeminine)
        return if (hundredsNumber == 0) lastWord else hundredsNumbers[hundredsNumber] + lastWord
    }

    private fun getNumberAndLastWord(number: Int, isFeminine: Boolean = false): Pair<Int, String> {
        return if (number % hundred < twenty) {
            Pair(
                    number / hundred,
                    if (isFeminine) uniqueFeminineNumbers[number % hundred] else uniqueNumbers[number % hundred]
            )
        } else {
            val lastWord =
                    if (isFeminine) uniqueFeminineNumbers[number % ten] else uniqueNumbers[number % ten]
            val lastNumber = number / ten
            Pair(lastNumber / ten, tensNumbers[lastNumber % ten] + lastWord)
        }
    }

    private fun convert(number: Long): String {
        val formatNumber = DecimalFormat("000000000000").format(number)
        var result = ""
        val billionString: String =
                getMillion(formatNumber.substring(0, 3).toInt(), billionDeclination)
        result += billionString
        val millionString: String =
                getMillion(formatNumber.substring(3, 6).toInt(), millionDeclination)
        result += millionString
        val hundredThousandString: String = getThousand(formatNumber.substring(6, 9).toInt())
        result += hundredThousandString
        val thousandString: String =
                convertLessThanOneThousand(formatNumber.substring(9, 12).toInt())
        result += thousandString
        return result.replace("^\\s+".toRegex(), "").replace("\\b\\s{2,}\\b".toRegex(), " ")
    }

    private fun getThousand(hundredThousands: Int): String {
        if (hundredThousands == 0) return ""
        if (hundredThousands in ten..twenty) {
            return convertLessThanOneThousand(
                    hundredThousands,
                    isFeminine = true
            ).plus(thousandDeclination.last())
        }

        return when (hundredThousands % ten) {
            1 -> convertLessThanOneThousand(
                    hundredThousands,
                    isFeminine = true
            ).plus(thousandDeclination.first())
            2, 3, 4 -> convertLessThanOneThousand(
                    hundredThousands,
                    isFeminine = true
            ).plus(thousandDeclination[1])
            else -> convertLessThanOneThousand(
                    hundredThousands,
                    isFeminine = true
            ).plus(thousandDeclination.last())
        }
    }

    private fun getMillion(number: Int, declinationWords: Array<String>): String {
        if (number == 0) return ""
        if (number in ten..twenty) {
            return convertLessThanOneThousand(
                    number
            ).plus(declinationWords.first())
        }

        return when (number % ten) {
            1 -> convertLessThanOneThousand(
                    number
            ).plus(declinationWords.first())
            2, 3, 4 -> convertLessThanOneThousand(
                    number
            ).plus(declinationWords[1])
            else -> convertLessThanOneThousand(
                    number
            ).plus(declinationWords.last())
        }
    }
}