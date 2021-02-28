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
package com.example.androiddevchallenge.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androiddevchallenge.data.model.Puppy

@Composable
fun HomeScreen(navigateToDetails: (Puppy) -> Unit) {
    val viewModel: HomeViewModel = viewModel(key = "home")
    val puppies: List<Puppy> by viewModel.puppies.observeAsState(emptyList())
    Column {
        TopAppBar(title = { Text(text = "Home (${puppies.count { it.adoption }})") })
        PuppyList(
            puppies = puppies,
            modifier = Modifier.fillMaxSize(),
            navigateToDetails = navigateToDetails
        )
    }
}
