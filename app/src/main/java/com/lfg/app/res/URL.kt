package com.lfg.app.res

/**
 * Created by LFG on 10/11/2017.
 */

enum class URL(val url: String) : CharSequence by url {

    USER_GET("wwww.google.com");


    override fun toString(): String = url
}