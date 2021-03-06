package com.aptyr.clonegithubtofirebase.ui
/*
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

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.aptyr.clonegithubtofirebase.R
import com.aptyr.clonegithubtofirebase.model.User
import com.aptyr.clonegithubtofirebase.presenter.users.GithubUsersPresenter
import com.aptyr.clonegithubtofirebase.presenter.users.GithubUsersPresenterImpl
import com.aptyr.clonegithubtofirebase.ui.adapter.UsersAdapter
import com.aptyr.clonegithubtofirebase.view.GithubUsersView
import kotlinx.android.synthetic.main.activity_github_users.*

class GithubUsersActivity : AppCompatActivity(), GithubUsersView {

    val adapter : UsersAdapter = UsersAdapter()

    val presenter : GithubUsersPresenter = GithubUsersPresenterImpl(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_github_users)

        list.setEmptyView(empty)
        list.adapter = adapter

        presenter.getUsers()

    }

    override fun dataLoaded(data: List<User>) {
        adapter.addUsers(data)
    }

}
