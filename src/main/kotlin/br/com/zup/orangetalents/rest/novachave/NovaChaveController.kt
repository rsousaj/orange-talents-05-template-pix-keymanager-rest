package br.com.zup.orangetalents.rest.novachave

import br.com.zup.orangetalents.KeyManagerRegisterGrpcServiceGrpc
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.http.uri.UriBuilder
import io.micronaut.validation.Validated
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.validation.Valid

@Controller("/api/pix")
@Validated
class NovaChaveController(
    private val novaChaveClient: KeyManagerRegisterGrpcServiceGrpc.KeyManagerRegisterGrpcServiceBlockingStub
) {
    @Post
    fun registra(@Valid @Body request: NovaChaveRequest): HttpResponse<Any> {
        val response = novaChaveClient.registraChavePix(request.paraGrpcRequest())

        val uri = UriBuilder.of("/api/pix/{id}").expand(mutableMapOf(Pair("id", response.pixId)))
        return HttpResponse.created(uri)
    }
}