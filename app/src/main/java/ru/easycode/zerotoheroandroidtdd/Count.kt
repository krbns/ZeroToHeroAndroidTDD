package ru.easycode.zerotoheroandroidtdd

interface Count {
    fun increment(number: String): UiState

    fun decrement(number: String): UiState
    fun initial(number: String): UiState
    fun save(number: String)
    class Base(private val step: Int, private val max: Int, private val min: Int) : Count {

        init {
            when {
                step <= 0 -> throw IllegalStateException("step should be positive, but was $step")
                max <= 0 -> throw IllegalStateException("max should be positive, but was $max")
                step > max -> throw IllegalStateException("max should be more than step")
                min >= max -> throw IllegalStateException("max should be more than min")
            }
        }

        private var number: Int = 0

        override fun save(number: String) {
            this.number = number.toInt()
        }

        override fun increment(number: String): UiState {
            this.number += this.step
            if ((this.number + this.step) > this.max) {
                return UiState.Max(this.number.toString())
            }
            return UiState.Base(this.number.toString())
        }

        override fun decrement(number: String): UiState {
            this.number -= this.step
            if (this.number < this.min) {
                this.number = this.min
                return UiState.Min(this.number.toString())
            }
            return when (this.number) {
                this.min -> UiState.Min(this.number.toString())
                else -> UiState.Base(this.number.toString())
            }
        }

        override fun initial(number: String): UiState {
            val numberInt = number.toInt()
            return when (numberInt) {
                max -> UiState.Max(number)
                min -> UiState.Min(number)
                else -> UiState.Base(number)
            }
        }
    }
}