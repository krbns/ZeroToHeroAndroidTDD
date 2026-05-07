package ru.easycode.zerotoheroandroidtdd.main.ui

import java.io.Serializable

interface Count : Serializable {

    fun increment(): Count

    fun decrement(): Count

    fun isMax(): Boolean
    fun isMin(): Boolean

    data class Base(val value: Int, val max: Int, val min: Int, val step: Int) : Count {

        private val v = value
        override fun increment(): Count {
            val sum = v + step
            return Base(value = sum, step = step, max = max, min = min)
        }

        override fun decrement(): Count {
            val sum = v - step
            return Base(value = sum, step = step, max = max, min = min)
        }

        override fun isMax(): Boolean {
            val sum = v + step
            return sum > max
        }

        override fun isMin(): Boolean {
            val sum = v - step
            return sum < min
        }

        override fun toString(): String {
            return v.toString()
        }
    }
}