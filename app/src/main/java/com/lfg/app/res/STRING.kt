package com.lfg.app.res

/**
 * Created by LFG on 10/5/2017.
 */

enum class STRING(val string: String) : CharSequence by string {

    // ============================================================================= \\
    // BASE
    // ============================================================================= \\

    PREFERENCE_FILE_KEY("com.lfg.app.PREFERENCE_FILE_KEY"),
    PREFERENCE_LOGIN_JSON("login_json");

    override fun toString(): String = string



    // ============================================================================= \\
    // VOLLEY
    // ============================================================================= \\

    enum class VOLLEY(val string: String) : CharSequence by string {
        SUCCESS("success"),
        MESSAGE("message");

        override fun toString(): String = string
    }



    // ============================================================================= \\
    // LOGIN
    // ============================================================================= \\

    enum class LOGIN(val string: String) : CharSequence by string {

        ID("LOGIN_ID"),
        NAME("LOGIN_IMAGE_URL"),
        IMAGE_URL("LOGIN_IMAGE_URL");

        override fun toString(): String = string
    }



    // ============================================================================= \\
    // USER
    // ============================================================================= \\

    enum class USER(val string: String) : CharSequence by string {

        ID("userId"),
        NAME("userName"),
        IMAGE_URL("userImage"),
        EMAIL("userEmail"),
        DOB("userDob");

        override fun toString(): String = string
    }



    // ============================================================================= \\
    // EVENT
    // ============================================================================= \\

    enum class EVENT(val string: String) : CharSequence by string {

        ID("eventId"),
        TITLE("eventTitle"),
        IMAGE_URL("eventImage");

        override fun toString(): String = string
    }
}