package ru.easycode.zerotoheroandroidtdd.main.ui

import java.io.Serializable

interface Count : Serializable {

    fun increment(): Count

    class Base(val value: Int, val step: Int) : Count {

        private val v = value
        override fun increment(): Count {
            val sum = v + step
            return Base(sum, step)
        }

        override fun toString(): String {
            return v.toString()
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is Base) return false
            return value == other.value && step == other.step
        }

        override fun hashCode(): Int {
            var result = value
            result = 31 * result + step
            return result
        }
    }
}