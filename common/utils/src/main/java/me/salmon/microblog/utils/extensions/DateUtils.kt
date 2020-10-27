package me.salmon.microblog.utils.extensions

import java.text.SimpleDateFormat

object DateUtils {

    fun String?.formatDate(): String {
        this?.let { 
            var dateFormatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            val date = dateFormatter.parse(it)
            dateFormatter = SimpleDateFormat("'Posted at' hh:mm a 'on' EEEE, dd MMM yyyy")
            return dateFormatter.format(date)
        }
        return ""
    }

    fun String?.getFirstLetters(): String {
        var firstLetters = ""
        this?.let {
            it.split(" ", limit = 2).map { part ->
                firstLetters += if (part.isNotEmpty()) part.subSequence(0, 1) else ""
            }
        }
        return firstLetters
    }
}