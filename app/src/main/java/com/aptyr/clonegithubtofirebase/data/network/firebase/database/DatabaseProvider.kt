package com.aptyr.clonegithubtofirebase.data.network.firebase.database

/**
 * Copyright (C) 2017 Aptyr (github.com/aptyr)
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

import com.aptyr.clonegithubtofirebase.model.RegisteredUser
import com.google.firebase.database.*


class DatabaseProvider {

    private var firebase = FirebaseDatabase.getInstance()

    fun getRegisteredUser(id: String, resultHandler: (RegisteredUser?) -> Unit, errorHandler: ((DatabaseError?) -> Unit)? = null) {
        val userReference = firebase.getReference("registered_users").child(id)

        userReference.addValueEventListener(object : ValueEventListener {

            override fun onCancelled(error: DatabaseError?) {
                errorHandler?.let { it(error) }
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.value == null) {
                    userReference.setValue(RegisteredUser(id))
                } else {
                    userReference.removeEventListener(this)
                    resultHandler(dataSnapshot.getValue<RegisteredUser>(RegisteredUser::class.java))
                }
            }
        })
    }
}