package br.com.zup.orangetalents.rest.compartilhado.validacoes

import javax.validation.Payload
import javax.validation.constraints.Pattern
import kotlin.reflect.KClass

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD, AnnotationTarget.PROPERTY, AnnotationTarget.VALUE_PARAMETER)
@Pattern(
    regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}$",
    flags = [Pattern.Flag.CASE_INSENSITIVE]
)
annotation class ValidoUUID(
    val message: String = "Identificador UUID inválido",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)