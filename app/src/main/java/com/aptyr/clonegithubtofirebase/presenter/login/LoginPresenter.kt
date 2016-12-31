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
import com.aptyr.clonegithubtofirebase.interactor.login.LoginInteractor
import com.aptyr.clonegithubtofirebase.presenter.BasePresenter
import com.aptyr.clonegithubtofirebase.view.login.LoginActivity
import com.google.android.gms.common.api.GoogleApiClient

interface LoginPresenter : BasePresenter<LoginInteractor> {

    val googleApiClient: GoogleApiClient?

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)

    fun activity(activity: LoginActivity)

}