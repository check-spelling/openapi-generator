package org.openapitools.server.api.vertical
import io.vertx.core.Vertx
import io.vertx.core.AbstractVertical
import io.vertx.serviceproxy.ServiceBinder

fun main(){
    Vertx.vertx().deployVertical(PetApiVertical())
}

class PetApiVertical:AbstractVertical() {

    override fun start() {
        val instance = (javaClass.classLoader.loadClass("org.openapitools.server.api.vertical.PetApiImpl").newInstance() as PetApi)
        instance.init(vertx,config())
        ServiceBinder(vertx)
            .setAddress(PetApi.address)
            .register(PetApi::class.java,instance)
    }
}