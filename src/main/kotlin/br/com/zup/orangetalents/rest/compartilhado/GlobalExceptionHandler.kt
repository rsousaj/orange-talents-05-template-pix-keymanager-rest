package br.com.zup.orangetalents.rest.compartilhado

import io.grpc.Status
import io.grpc.StatusRuntimeException
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.hateoas.JsonError
import io.micronaut.http.server.exceptions.ExceptionHandler
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.inject.Singleton

@Singleton
class GlobalExceptionHandler : ExceptionHandler<StatusRuntimeException, HttpResponse<JsonError>> {

    override fun handle(request: HttpRequest<*>?, exception: StatusRuntimeException): HttpResponse<JsonError> {
        val (status, mensagem) = when (exception.status.code) {
            Status.Code.ALREADY_EXISTS -> Pair(HttpStatus.UNPROCESSABLE_ENTITY, exception.message)
            Status.Code.UNAVAILABLE -> Pair(HttpStatus.SERVICE_UNAVAILABLE, "Serviço indisponível. Tente novamente mais tarde.")
            else -> Pair(HttpStatus.INTERNAL_SERVER_ERROR, "Ocorreu um erro na requisição.")
        }

        return HttpResponse
            .status<JsonError>(status)
            .body(JsonError(mensagem))
    }
}

