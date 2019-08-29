package br.com.doghero.dhproject.ui.base

import android.support.annotation.StringRes

interface BaseView {

    fun showProgress()

    fun hideProgress()

    fun showMessage(@StringRes message: Int)
}