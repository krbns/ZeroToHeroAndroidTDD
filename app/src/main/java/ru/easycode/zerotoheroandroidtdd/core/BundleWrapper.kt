package ru.easycode.zerotoheroandroidtdd.core

import android.os.Bundle

interface BundleWrapper {

    interface Save : BundleWrapper {
        fun save(list: ArrayList<CharSequence>)
    }

    interface Restore : BundleWrapper {
        fun restore(): List<CharSequence>
    }

    interface Mutable : Save, Restore

    class Base(
        private val bundle: Bundle
    ) : Mutable {

        companion object {
            const val ITEMS_KEY = "items"
        }

        override fun save(list: ArrayList<CharSequence>) {
            bundle.putCharSequenceArrayList(ITEMS_KEY, list)
        }

        override fun restore(): List<CharSequence> {
            return bundle.getCharSequenceArrayList(ITEMS_KEY) ?: ArrayList()
        }
    }
}