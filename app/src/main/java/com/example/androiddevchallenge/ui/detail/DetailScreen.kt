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

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androiddevchallenge.data.model.Puppy
import com.example.androiddevchallenge.ui.util.viewModelProviderFactoryOf

@Composable
fun DetailScreen(puppyId: String, upPress: () -> Unit) {
    Surface(color = MaterialTheme.colors.background) {
        val viewModel: DetailViewModel = viewModel(
            key = "detail_$puppyId",
            factory = viewModelProviderFactoryOf { DetailViewModel(puppyId = puppyId) }
        )
        val puppy: Puppy? by viewModel.puppy.observeAsState()
        puppy?.let {
            Column {
                TopAppBar(
                    title = { Text(text = it.type) },
                    navigationIcon = {
                        Image(
                            Icons.Default.ArrowBack,
                            contentDescription = null,
                            contentScale = ContentScale.Inside,
                            colorFilter = ColorFilter.tint(LocalContentColor.current.copy(alpha = LocalContentAlpha.current)),
                            modifier = Modifier
                                .fillMaxSize()
                                .clickable {
                                    upPress()
                                }
                        )
                    }
                )
                PuppyDetail(
                    puppy = it,
                    modifier = Modifier.fillMaxSize(),
                    adoptPuppy = { puppyId ->
                        viewModel.onAdoptPuppyClick(puppyId)
                    }
                )
            }
        }
    }
}
