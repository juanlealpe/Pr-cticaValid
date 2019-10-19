package com.sport.practicavalid.repository

import com.sport.practicavalid.api.API
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseRespository {

    val api by lazy { API.create() }
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    protected fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    fun clear(){
        compositeDisposable.clear()
    }
}