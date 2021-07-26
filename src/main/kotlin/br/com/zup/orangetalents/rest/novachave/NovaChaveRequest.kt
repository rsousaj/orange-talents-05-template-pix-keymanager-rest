package br.com.zup.orangetalents.rest.novachave

import br.com.zup.orangetalents.ChavePixRequest
import br.com.zup.orangetalents.TipoDeChave
import br.com.zup.orangetalents.TipoDeConta
import br.com.zup.orangetalents.rest.compartilhado.validacoes.ValidoUUID
import io.micronaut.core.annotation.Introspected
import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Introspected
@ValidPixKey
data class NovaChaveRequest(
    @field:NotBlank
    @field:ValidoUUID
    val idCliente: String,

    @field:NotNull
    val tipoChave: TipoChave,

    @field:NotNull
    val tipoConta: TipoConta,

    val chave: String
) {

    fun paraGrpcRequest(): ChavePixRequest {
        return ChavePixRequest
            .newBuilder()
            .setChave(chave)
            .setTipoChave(tipoChave.grpcValue)
            .setTipoConta(tipoConta.grpcValue)
            .setCodigoCliente(idCliente)
            .build()
    }
}

enum class TipoChave(val grpcValue: TipoDeChave) {
    CPF(TipoDeChave.CPF) {
        override fun valida(chave: String?): Boolean {
            if (chave.isNullOrBlank()) return false

            return CPFValidator().run {
                initialize(null)
                isValid(chave, null)
            }
        }
    }, CELULAR(TipoDeChave.CELULAR) {
        override fun valida(chave: String?): Boolean {
            if (chave.isNullOrBlank()) return false

            return chave.matches("^\\+[1-9][0-9]\\d{1,14}\$".toRegex())
        }
    }, EMAIL(TipoDeChave.EMAIL) {
        override fun valida(chave: String?): Boolean {
            if (chave.isNullOrBlank()) return false

            return EmailValidator().run {
                initialize(null)
                isValid(chave, null)
            }
        }
    }, ALEATORIO(TipoDeChave.ALEATORIO) {
        override fun valida(chave: String?): Boolean {
            return chave.isNullOrBlank()
        }
    };

    abstract fun valida(chave: String?): Boolean
}

enum class TipoConta(val grpcValue: TipoDeConta) {
    CONTA_CORRENTE(TipoDeConta.CONTA_CORRENTE),
    CONTA_POUPANCA(TipoDeConta.CONTA_POUPANCA)
}