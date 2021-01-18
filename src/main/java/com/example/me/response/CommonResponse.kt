package com.example.me.response

class CommonResponse {
    var data: Any? = null
    var code: Int = 0
    var message: String = "SUCCESS"

    constructor(code: Int, message: String) {
        this.code = code
        this.message = message
    }

    constructor(data: Any?) {
        this.data = data
    }
}