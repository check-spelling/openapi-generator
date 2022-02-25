package org.openapitools.server.api.vertical
import io.vertx.core.Vertx
import io.vertx.core.AbstractVertical
import io.vertx.serviceproxy.ServiceBinder

fun main(){
    Vertx.vertx().deployVertical(UserApiVertical())
}

class UserApiVertical:AbstractVertical() {

    override fun start() {
        val instance = (javaClass.classLoader.loadClass("org.openapitools.server.api.vertical.UserApiImpl").newInstance() as UserApi)
        instance.init(vertx,config())
        ServiceBinder(vertx)
            .setAddress(UserApi.address)
            .register(UserApi::class.java,instance)
    }
}