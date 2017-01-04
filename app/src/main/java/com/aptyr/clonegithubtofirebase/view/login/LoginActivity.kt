package com.aptyr.clonegithubtofirebase.view.login

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
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import com.aptyr.clonegithubtofirebase.R
import com.aptyr.clonegithubtofirebase.flowcontroller.FlowController
import com.aptyr.clonegithubtofirebase.presenter.login.LoginPresenter
import com.aptyr.clonegithubtofirebase.presenter.login.LoginPresenterImpl
import com.aptyr.clonegithubtofirebase.view.BaseActivity
import com.aptyr.clonegithubtofirebase.viewmodel.LoginViewModel

class LoginActivity : BaseActivity(), LoginView {

    override val presenter: LoginPresenter = LoginPresenterImpl(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        presenter.activity(this)

        signInButton.setOnClickListener { FlowController.instance.googleSignInView(this, presenter.googleApiClient) }
    }

    public override fun onStart() {
        super.onStart()
        presenter.start()
    }

    public override fun onStop() {
        super.onStop()
        presenter.stop()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        presenter.onActivityResult(requestCode, resultCode, data)
    }

    override fun signInFail() {
        Toast.makeText(this, "Can't sign in user.",Toast.LENGTH_SHORT).show()
    }

    override fun authFail() {
        Toast.makeText(this, "Can't authenticate in user.",Toast.LENGTH_SHORT).show()
    }

    override fun signedIn(viewModel: LoginViewModel) {
        signInButton.visibility = View.GONE
        avatar.visibility = View.VISIBLE
        greeting.visibility = View.VISIBLE
        viewModel.avatar(avatar)
        greeting.text = viewModel.greet
    }

    override fun signedOut() {
        signInButton.visibility = View.VISIBLE
        avatar.visibility = View.GONE
        greeting.visibility = View.GONE
    }
}