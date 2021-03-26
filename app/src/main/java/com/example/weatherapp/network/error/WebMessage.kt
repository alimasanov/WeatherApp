package com.example.weatherapp.network.error

import com.example.weatherapp.R
import com.example.weatherapp.tools.localise
import java.lang.Exception

class WebMessage {
    class PARSE(key: String = "") : Exception("${localise(R.string.error_parse)} $key")
    class UNKNOWN() : Exception(localise(R.string.error_unknown))
    class NO_DATA : Exception(localise(R.string.error_no_data))
}