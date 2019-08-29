package br.com.doghero.dhproject.ui.base

import android.support.annotation.CallSuper
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BasePresenter(protected open val view: BaseView) {

    protected val compositeDisposable = CompositeDisposable()

    abstract fun subscribe()

    @CallSuper
    open fun unsubscribe() {
        compositeDisposable.clear()
    }

    protected fun Disposable.addToPresenterComposite() = apply { compositeDisposable.add(this) }
}