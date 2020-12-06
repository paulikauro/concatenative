import kotlin.test.Test

class ArityTests {
    private val testFunctions = mapOf(
        "1" to Arity(0, 1),
        "+" to Arity(2, 1),
    )

    private fun String.arity() = this
        .split(" ")
        .map(testFunctions::getValue)
        .reduce(Arity::compose)

    // simple cases
    @Test
    fun `just 1`() = assert("1".arity() == Arity(0, 1))

    @Test
    fun `just +`() = assert("+".arity() == Arity(2, 1))

    @Test
    fun `two 1s`() = assert("1 1".arity() == Arity(0, 2))

    @Test
    fun `three 1s`() = assert("1 1 1".arity() == Arity(0, 3))

    @Test
    fun increment() = assert("1 +".arity() == Arity(1, 1))

    @Test
    fun `add two numbers`() = assert("1 1 +".arity() == Arity(0, 1))

    @Test
    fun `add two numbers + extra`() = assert("1 1 1 +".arity() == Arity(0, 2))

    @Test
    fun `add two numbers + extra, different order`() = assert("1 1 + 1".arity() == Arity(0, 2))

    @Test
    fun `add three numbers 1`() = assert("1 1 + 1 +".arity() == Arity(0, 1))

    @Test
    fun `add three numbers 2`() = assert("1 1 1 + +".arity() == Arity(0, 1))

    @Test
    fun `3-operand add`() = assert("+ +".arity() == Arity(3, 1))

    @Test
    fun `add + const`() = assert("+ 1".arity() == Arity(2, 2))

    @Test
    fun `3-operand add + const`() = assert("+ + 1".arity() == Arity(3, 2))
}
