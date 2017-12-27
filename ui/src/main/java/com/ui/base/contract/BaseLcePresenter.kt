package com.ui.base.contract

import com.domain.entity.Error
import com.domain.interactor.base.UseCase

open class BaseLcePresenter<in M, V : BaseLceView<M>>(vararg useCases: UseCase) : BasePresenter<V>(*useCases) {

    open fun doAction(action: String) {

    }

    open protected fun resolveError(error: Throwable): Boolean {
        error.printStackTrace()
        return when (error) {
            is Error.Content -> {
                view?.showError(error.isNetworkError)
                true
            }
            is Error.NonCritical -> {
                view?.showMessage(error.message)
                true
            }
            is Error.Critical -> {
                //TODO ADD ROUTING
                true
            }
            else -> false
        }
    }
}