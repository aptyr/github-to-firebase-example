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

import com.aptyr.clonegithubtofirebase.presenter.login.LoginPresenter
import com.aptyr.clonegithubtofirebase.view.BaseView
import com.aptyr.clonegithubtofirebase.viewmodel.LoginViewModel

interface LoginView : BaseView<LoginPresenter> {

    fun signInFail()

    fun authFail()

    fun signedIn(viewModel: LoginViewModel)

    fun signedOut()

}