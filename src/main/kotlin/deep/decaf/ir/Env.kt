package deep.decaf.ir

fun <T> MutableMap<String, T>.addNoOverwrite(key: String, data: T): Boolean {
    return if (key in this) false
    else {
        this[key] = data
        true
    }
}

fun <T> MutableMap<String, T>.getNoNull(key: String): T {
    if (key !in this) throw java.lang.IllegalArgumentException("binding for $key not present")
    else return this[key]!!
}

data class MethodSignature(val returnType: Type, val argList: List<Arg>)
data class ArrayFieldSignature(val type: Type, val size: Int)

class BlockEnv {
    val bindings = mutableMapOf<String, Type>()
}

class Env {
    private val regularFieldBindings = mutableMapOf<String, Type>()
    val arrayFieldBindings = mutableMapOf<String, ArrayFieldSignature>()
    val methodSignatureBindings = mutableMapOf<String, MethodSignature>()

    private val blockBindings = mutableListOf<BlockEnv>()

    fun enterBlock() {
        blockBindings.add(BlockEnv())
    }

    fun leaveBlock() {
        blockBindings.removeLast()
    }

    fun addVariableBinding(name: String, type: Type): Boolean {
        return blockBindings.last().bindings.addNoOverwrite(name, type)
    }

    fun addRegularFieldBinding(name: String, type: Type): Boolean {
        return regularFieldBindings.addNoOverwrite(name, type)
    }

    fun getNonArrayVarBinding(name: String): Type {
        for (blockEnv in blockBindings.reversed()) {
            if (name in blockEnv.bindings) return blockEnv.bindings[name]!!
        }
        return regularFieldBindings.getNoNull(name)
    }
}