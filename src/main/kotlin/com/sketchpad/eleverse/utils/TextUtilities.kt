package com.sketchpad.eleverse.utils

import org.apache.commons.lang.math.NumberUtils
import java.util.regex.Pattern

fun formatForDescription(text: String, attributes: HashMap<String, String> = hashMapOf()): List<String> {
    val words = text.split(" ")
    val formatted = mutableListOf<String>()
    var currentLength = 0
    var currentLine = ""
    for (w in words) {
        var word = w
        for (at in attributes) {
            if (w.contains("[${at.key}]")) {
                val pattern = Pattern.compile("-?\\d+(\\.\\d+)?")
                val isDecimalPattern = Pattern.compile("^\\d+\\.\\d+")
                word = if (pattern.matcher(at.value).matches()) {
                    w.replace("[${at.key}]", formatWithCommas(at.value, !isDecimalPattern.matcher(at.value).matches()))
                } else w.replace("[${at.key}]", at.value)
            }
        }

        if (word=="#n") {
            formatted.add(currentLine)
            currentLine = ""
            currentLength = 0
        }
        else if (currentLength <= 17) {
            currentLine += "${if (currentLength==0) "" else " "}$word"
            currentLength += word.length
        } else {
            formatted.add("$currentLine $word")
            currentLine = ""
            currentLength = 0
        }
    }
    if (currentLine.isNotEmpty()) {
        formatted.add(currentLine)
    }
    return formatted
}

fun String.formatForDesc(attributes: HashMap<String, String> = hashMapOf()): List<String> {
    return formatForDescription(this, attributes)
}
