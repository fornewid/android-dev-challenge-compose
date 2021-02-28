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
package com.example.androiddevchallenge.data

import androidx.collection.ArraySet
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.data.model.Puppy
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

object InMemoryPuppyRepository : PuppyRepository {

    private val puppies = (0..100).map { newInstance(it) }
    private val puppiesFlow = MutableStateFlow(puppies)
    private val adoptedPuppyIds = ArraySet<String>()

    override fun getPuppies(): Flow<List<Puppy>> {
        return puppiesFlow
    }

    override fun getPuppyDetails(puppyId: String): Puppy? {
        return puppies.find { it.id == puppyId }?.let {
            it.copy(adoption = it.id in adoptedPuppyIds)
        }
    }

    override fun adoptPuppy(puppyId: String): Boolean {
        return adoptedPuppyIds.add(puppyId).also { success ->
            if (success) {
                puppiesFlow.value = puppies.map {
                    it.copy(adoption = it.id in adoptedPuppyIds)
                }
            }
        }
    }

    private fun newInstance(id: Int): Puppy {
        return when (id % 9) {
            0 -> Puppy("puppy_$id", R.drawable.puppy_01, "Labrador Retriever")
            1 -> Puppy("puppy_$id", R.drawable.puppy_02, "Golden Retriever")
            2 -> Puppy("puppy_$id", R.drawable.puppy_03, "Cockapoo")
            3 -> Puppy("puppy_$id", R.drawable.puppy_04, "Yorkshire Terrier")
            4 -> Puppy("puppy_$id", R.drawable.puppy_05, "Boxer")
            5 -> Puppy("puppy_$id", R.drawable.puppy_06, "Pomeranian")
            6 -> Puppy("puppy_$id", R.drawable.puppy_07, "Beagle")
            7 -> Puppy("puppy_$id", R.drawable.puppy_08, "BassetHound")
            else -> Puppy("puppy_$id", R.drawable.puppy_09, "Newfoundland")
        }
    }
}
