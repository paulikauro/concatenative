import kotlin.math.min

// Turns out this is a bicyclic semigroup
data class Arity(val consume: Int, val produce: Int)

fun Arity.compose(other: Arity): Arity {
    val min = min(produce, other.consume)
    return Arity(consume + other.consume - min, other.produce + produce - min)
}
