package org.example.mvc.view

class ModelAndView(private val view: Any) {
    val model: Map<String, Any> = hashMapOf()

    fun getViewName() : String? =
        if (view is String) this.view
        else null
}