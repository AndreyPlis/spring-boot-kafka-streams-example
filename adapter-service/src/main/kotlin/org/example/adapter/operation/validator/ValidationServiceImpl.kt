package org.example.adapter.operation.validator

import org.slf4j.*
import org.springframework.stereotype.*
import javax.validation.*


@Service
class ValidationServiceImpl(private val validator: Validator):ValidationService {
    private val log: Logger = LoggerFactory.getLogger(ValidationServiceImpl::class.java)

    override fun <T> isValid(value: T): Boolean {
        val validate: Set<ConstraintViolation<T>> = validator.validate<T>(value)
        for (v in validate) {
            log.error(v.rootBean.toString() + ": " + v.propertyPath + " - " + v.message)
        }
        return validate.isEmpty()
    }
}