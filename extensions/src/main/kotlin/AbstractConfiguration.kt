package splitties.wff.extensions

abstract class AbstractConfiguration {

    protected fun registration(block: () -> Unit) {
        require(registered.not()) { "Already registered!" }
        block()
        registered = true
    }

    protected fun checkRegistered() {
        check(registered) { "Not registered! Call register in the userConfigurations block." }
    }

    private var registered: Boolean = false
}
