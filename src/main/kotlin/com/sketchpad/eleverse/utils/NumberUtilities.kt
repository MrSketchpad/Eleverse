package com.sketchpad.eleverse.utils

import java.lang.IllegalArgumentException
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat
import java.util.regex.Pattern

fun formatWithCommas(num: String, int: Boolean): String {
    val pattern = Pattern.compile("-?\\d+(\\.\\d+)?")
    if (pattern.matcher(num).matches()) {
        val decimal = BigDecimal(num)
        val format = if (int) DecimalFormat("###,###") else DecimalFormat("#,##0.0")
        return format.format(decimal)
    } else {
        throw IllegalArgumentException("Parameter num must be an integer of float in string form!")
    }
}