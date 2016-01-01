package com.aptyr.clonegithubtofirebase.presenter.login

/**
 * Copyright (C) 2016 Aptyr (github.com/aptyr)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.content.Intent
import android.util.Log
import com.aptyr.clonegithubtofirebase.flowcontroller.FlowController
import com.aptyr.clonegithubtofirebase.interactor.login.LoginInteractorImpl
import com.aptyr.clonegithubtofirebase.view.login.LoginActivity
import com.aptyr.clonegithubtofirebase.view.login.LoginView
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.auth.FirebaseUser
import rx.Observer

class LoginPresenterImpl(val view: LoginView) : LoginPresenter {

    override val googleApiClient: GoogleApiClient?
        get() = loginInteractor.googleApiClient

    private val loginInteractor = LoginInteractorImpl()

    private var observer: Observer<FirebaseUser?> = object : Observer<FirebaseUser?> {
        override fun onCompleted() {
            Log.d("observer", "onComplete")

        }

        override fun onError(e: Throwable) {
            Log.d("observer", "error ")

        }

        override fun onNext(firebaseUser: FirebaseUser?) {
            Log.d("observer", "next " + firebaseUser?.displayName)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode === FlowController.instance.RC_GOOGLE_SIGN_IN) {
            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            if (result.isSuccess) {
                val account = result.signInAccount
                loginInteractor.auth(account!!)
            } else {

            }
        }
    }

    override fun activity(activity: LoginActivity) {
        loginInteractor.activity(activity)
    }

    override fun stop() {
        loginInteractor.stop()
        loginInteractor.unsubscribe()
    }

    override fun start() {
        loginInteractor.start()
        loginInteractor.subscribe(observer)
    }
}