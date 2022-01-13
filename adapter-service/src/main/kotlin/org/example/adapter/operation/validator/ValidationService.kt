package org.example.adapter.operation.validator

interface ValidationService {

    fun <T> isValid(value: T): Boolean

}