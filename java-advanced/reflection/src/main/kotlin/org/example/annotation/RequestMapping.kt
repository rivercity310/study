package org.example.annotation

@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class RequestMapping(
    val value: String = "",
    val method: RequestMethod = RequestMethod.GET
)
