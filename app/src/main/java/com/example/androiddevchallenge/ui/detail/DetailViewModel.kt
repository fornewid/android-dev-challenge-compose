/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androiddevchallenge.data.InMemoryPuppyRepository
import com.example.androiddevchallenge.data.PuppyRepository
import com.example.androiddevchallenge.data.model.Puppy

class DetailViewModel(
    puppyId: String,
    private val repository: PuppyRepository = InMemoryPuppyRepository
) : ViewModel() {

    private val _puppy = MutableLiveData<Puppy>()
    val puppy: LiveData<Puppy>
        get() = _puppy

    init {
        updatePuppyDetails(puppyId)
    }

    fun onAdoptPuppyClick(puppyId: String) {
        if (repository.adoptPuppy(puppyId)) {
            updatePuppyDetails(puppyId)
        }
    }

    private fun updatePuppyDetails(puppyId: String) {
        repository.getPuppyDetails(puppyId)?.let { puppy ->
            _puppy.value = puppy
        }
    }
}
