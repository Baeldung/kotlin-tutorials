package com.baeldung.proxypattern

interface RemoteObject {
    fun performRemoteOperation()
}
class RemoteObjectImpl : RemoteObject {
    override fun performRemoteOperation() {
        println("RemoteObject performing remote operation")
    }
}
class RemoteProxy(private val remoteObject: RemoteObjectImpl) : RemoteObject {
    override fun performRemoteOperation() {
        // Additional logic for handling remote communication
        println("Proxy: Initiating remote communication")
        remoteObject.performRemoteOperation()
        println("Proxy: Remote communication complete")
    }
}


interface ObjectToLog {
    fun operation()
}
class RealObjectToLog : ObjectToLog {
    override fun operation() {
        println("RealObjectToLog performing operation")
    }
}
class LoggingProxy(private val realObject: RealObjectToLog) : ObjectToLog {
    override fun operation() {
        println("Logging: Before operation")
        realObject.operation()
        println("Logging: After operation")
    }
}


interface SensitiveObject {
    fun access()
}
class SensitiveObjectImpl : SensitiveObject {
    override fun access() {
        println("SensitiveObject accessed")
    }
}
class ProtectionProxy(private val userRole: String) : SensitiveObject {
    private val realObject: SensitiveObjectImpl = SensitiveObjectImpl()
    override fun access() {
        if (userRole == "admin") {
            realObject.access()
        } else {
            println("Access denied. Insufficient privileges.")
        }
    }
}


interface RealObject {
    fun performOperation()
}
class RealObjectImpl : RealObject {
    override fun performOperation() {
        println("RealObject performing operation")
    }
}
class VirtualProxy : RealObject {
    private val realObject by lazy { RealObjectImpl() }
    override fun performOperation() {
        realObject.performOperation()
    }
}
