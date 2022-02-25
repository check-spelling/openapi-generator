package org.openapitools.server.api.vertical
import io.vertx.core.Vertx
import io.vertx.core.AbstractVertical
import io.vertx.serviceproxy.ServiceBinder

fun main(){
    Vertx.vertx().deployVertical(StoreApiVertical())
}

class StoreApiVertical:AbstractVertical() {

    override fun start() {
        val instance = (javaClass.classLoader.loadClass("org.openapitools.server.api.vertical.StoreApiImpl").newInstance() as StoreApi)
        instance.init(vertx,config())
        ServiceBinder(vertx)
            .setAddress(StoreApi.address)
            .register(StoreApi::class.java,instance)
    }
}