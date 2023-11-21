import org.gradle.api.provider.Property

internal infix fun <T> Property<T>.by(value: T) {
    set(value)
}
