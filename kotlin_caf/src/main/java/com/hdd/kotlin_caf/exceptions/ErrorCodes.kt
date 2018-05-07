package com.hdd.kotlin_caf.exceptions

/**
 * Created on 2/28/2018.
 * @author duonghd
 */

object ErrorCodes {
    const val UNAUTHORIZED = 401

    const val GENERAL_ERROR = 999

    const val NOT_AUTHENTICATED_ERROR = 1000

    const val NETWORK_NOT_AVAILABLE_ERROR = 1001

    const val HTTP_IO_ERROR = 1002

    const val HTTP_TIMEOUT_ERROR = 1003

    const val HTTP_JSON_SYNTAX_ERROR = 1004

    const val HTTP_RESPONSE_JSON_ERROR = 1005
}
