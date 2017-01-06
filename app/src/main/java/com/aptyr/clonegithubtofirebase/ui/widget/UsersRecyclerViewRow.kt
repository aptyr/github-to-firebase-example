package com.aptyr.clonegithubtofirebase.ui.widget

/**
 * Copyright (C) 2016 Aptyr (github.com/aptyr)

 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at

 * http://www.apache.org/licenses/LICENSE-2.0

 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater

import com.aptyr.clonegithubtofirebase.R
import com.aptyr.clonegithubtofirebase.databinding.LayoutUserBinding
import com.aptyr.clonegithubtofirebase.model.User

class UsersRecyclerViewRow(context: Context, attributeSet: AttributeSet) : ConstraintLayout(context, attributeSet) {

    private val mDataBinding: LayoutUserBinding

    init {

        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        mDataBinding = DataBindingUtil.inflate<LayoutUserBinding>(inflater, R.layout.layout_user, this, true)
    }


    fun setData(user: User) {
        mDataBinding.user = user
    }

}
