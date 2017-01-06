package com.aptyr.clonegithubtofirebase.interactor.login

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

import android.app.Activity
import com.aptyr.clonegithubtofirebase.data.network.firebase.auth.AuthProvider
import com.aptyr.clonegithubtofirebase.data.network.firebase.database.DatabaseProvider
import com.aptyr.clonegithubtofirebase.model.RegisteredUser
import com.aptyr.clonegithubtofirebase.ui.LoginActivity
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.DatabaseError
import rx.Observer
import rx.Subscription
import rx.subjects.PublishSubject

class LoginInteractorImpl: LoginInteractor, GoogleApiClient.OnConnectionFailedListener, FirebaseAuth.AuthStateListener {

    override val googleApiClient: GoogleApiClient?
        get() = authProvider?.googleApiClient

    private val databaseProvider: DatabaseProvider = DatabaseProvider()
    private var authProvider: AuthProvider? = null
    private var activity: Activity? = null

    private var authSubject: PublishSubject<FirebaseUser?> = PublishSubject.create()
    private var subscription: Subscription? = null

    private var firebaseUser: FirebaseUser? = null

    override fun activity(activity: LoginActivity) {
        if (authProvider == null) {
            authProvider = AuthProvider.Builder(activity).listener(this).build()
        }
        this.activity = activity
    }

    override fun auth(account: GoogleSignInAccount) {
        activity?.let {
            val credential = GoogleAuthProvider.getCredential(account.idToken, null)
            authProvider?.firebaseAuth?.signInWithCredential(credential)
                    ?.addOnCompleteListener(it) { task ->
                        firebaseUser = task.result.user
                        authSubject.onNext(firebaseUser)
                        authSubject.onCompleted()
                    }
        } ?: authSubject.onError(NullPointerException("Set activity before"))
    }

    override fun stop() {
        authProvider?.firebaseAuth?.removeAuthStateListener(this)
    }

    override fun start() {
        authProvider?.firebaseAuth?.addAuthStateListener(this)
    }

    override fun onConnectionFailed(connectionResult: ConnectionResult) {
        authSubject.onError(Exception("Conn err"))
    }

    override fun onAuthStateChanged(firebaseAuth: FirebaseAuth) {
        authSubject.onNext(firebaseAuth.currentUser)
    }

    override fun subscribe(observer: Observer<FirebaseUser?>) {
        unsubscribe()
        this.subscription = authSubject.subscribe(observer)
    }

    override fun unsubscribe(){
        if(this.subscription != null && !this.subscription!!.isUnsubscribed){
            this.subscription!!.unsubscribe()
        }
    }

    override fun getRegisteredUser(resultHandler: (RegisteredUser?) -> Unit, errorHandler: ((DatabaseError?) -> Unit)?) {
        firebaseUser?.let { databaseProvider.getRegisteredUser(it.uid, resultHandler, errorHandler) }
    }
}