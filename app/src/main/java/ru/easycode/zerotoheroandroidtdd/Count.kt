package ru.easycode.zerotoheroandroidtdd

interface Count {
    fun increment(number: String): UiState
    class Base(private val step: Int, private val max: Int): Count {

        init {
            when {
                step <= 0 -> throw IllegalStateException("step should be positive, but was $step")
                max <= 0 -> throw IllegalStateException("max should be positive, but was $max")
                step > max -> throw IllegalStateException("max should be more than step")
            }
        }

        private var number: Int = 0

        override fun increment(number: String): UiState {
            val numberInt = number.toInt()
            this.number = if (numberInt > 0) this.number + numberInt else this.number + this.step
            if ((this.number + this.step) > this.max) {
                return UiState.Max(this.number.toString())
            }
            return UiState.Base(this.number.toString())
        }
    }
}