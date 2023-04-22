package com.example.gameduo.validation

object NullArgChecker {
    internal fun check(vararg args: Any?): Boolean {
        args.forEach { if (it == null) return false }
        return true
    }
}