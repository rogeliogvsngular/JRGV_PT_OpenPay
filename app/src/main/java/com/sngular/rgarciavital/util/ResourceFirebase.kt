package com.sngular.rgarciavital.util


data class ResourceFirebase<out T>(val status: Status, val data: T?, val message: String?) {

    companion object {
        // Creates a ResourceFirebase object with a SUCCESS status and some data.
        fun <T> success(data: T?): ResourceFirebase<T> {
            return ResourceFirebase(Status.SUCCESS, data, null)
        }

        // Creates a ResourceFirebase object with an ERROR status and an error message.
        fun <T> error(msg: String, data: T?): ResourceFirebase<T> {
            return ResourceFirebase(Status.ERROR, data, msg)
        }

        // Creates a ResourceFirebase object with a LOADING status to notify the UI.
        fun <T> loading(data: T?): ResourceFirebase<T> {
            return ResourceFirebase(Status.LOADING, data, null)
        }
    }
}



enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}