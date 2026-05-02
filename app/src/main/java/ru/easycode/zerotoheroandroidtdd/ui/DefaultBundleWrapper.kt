package ru.easycode.zerotoheroandroidtdd.ui

import android.os.Bundle

class DefaultBundleWrapper(
    private val bundle: Bundle
) : BundleWrapper.Mutable {

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
