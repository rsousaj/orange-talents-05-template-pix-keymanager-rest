package br.com.zup.orangetalents.rest.compartilhado.grpc

import br.com.zup.orangetalents.KeyManagerRegisterGrpcServiceGrpc
import io.grpc.ManagedChannel
import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import io.micronaut.grpc.annotation.GrpcChannel

@Factory
class GrpcClientFactory {

    @Bean
    fun registraChaveClient(@GrpcChannel("keymanager") channel: ManagedChannel): KeyManagerRegisterGrpcServiceGrpc.KeyManagerRegisterGrpcServiceBlockingStub {
        return KeyManagerRegisterGrpcServiceGrpc.newBlockingStub(channel);
    }
}