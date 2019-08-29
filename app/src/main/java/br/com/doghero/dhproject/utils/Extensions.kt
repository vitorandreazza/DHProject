package br.com.doghero.dhproject.utils

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Toast
import br.com.doghero.dhproject.ui.base.BaseSchedulerProvider
import br.com.doghero.dhproject.ui.base.BaseView
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.text.NumberFormat

/** Views **/
fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

/** RxJava **/
fun <T> Single<T>.applyIoToMainSchedulers(schedulerProvider: BaseSchedulerProvider): Single<T> =
        subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())

fun <T> Single<T>.applyLoading(view: BaseView): Single<T> =
        doOnSubscribe { view.showProgress() }
                .doFinally { view.hideProgress() }

fun Disposable.addTo(compositeDisposable: CompositeDisposable) =
        apply { compositeDisposable.add(this) }


/** General **/
fun Double.toCurrency(): String = NumberFormat.getCurrencyInstance().format(this)

fun Throwable.log(): String = Log.getStackTraceString(this)