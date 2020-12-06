package deep.decaf.ir

fun <T> MutableMap<String, T>.addNoOverwrite(key: String, data: T): Boolean {
    return if (key in this) true
    else {
        this[key] = data
        false
    }
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

    var inLoop = false
    var enclosingMethodName = ""

    private val blockBindings = mutableListOf(BlockEnv())

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

    fun getNonArrayVarBinding(name: String): Type? {
        for (blockEnv in blockBindings.reversed()) {
            if (name in blockEnv.bindings) return blockEnv.bindings[name]!!
        }
        return regularFieldBindings[name]
    }
}