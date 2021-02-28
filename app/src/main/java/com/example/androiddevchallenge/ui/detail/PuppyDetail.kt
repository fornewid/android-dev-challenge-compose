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
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.data.model.Puppy
import com.example.androiddevchallenge.ui.theme.MyTheme

@Composable
fun PuppyDetail(
    puppy: Puppy,
    modifier: Modifier = Modifier,
    adoptPuppy: (String) -> Unit = {}
) {
    val scrollState = rememberScrollState()
    Box(modifier) {
        Column(
            modifier = Modifier
                .padding(bottom = 48.dp)
                .verticalScroll(scrollState)
        ) {
            Image(
                painterResource(puppy.imageResId),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            )
            Text(
                text = puppy.type,
                style = MaterialTheme.typography.h4,
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
            )
        }
        Button(
            onClick = { adoptPuppy(puppy.id) },
            enabled = puppy.adoption.not(),
            shape = MaterialTheme.shapes.large,
            modifier = Modifier
                .fillMaxWidth()
                .requiredHeight(48.dp)
                .align(Alignment.BottomCenter)
        ) {
            Text(text = if (puppy.adoption) "Already adopted!" else "Adopt")
        }
    }
}

@Preview("Adopted", widthDp = 360, heightDp = 640)
@Composable
fun Adopted() {
    MyTheme {
        PuppyDetail(puppy = Puppy("", R.drawable.puppy_01, "Labrador Retriever", adoption = true))
    }
}

@Preview("Not adopted", widthDp = 360, heightDp = 640)
@Composable
fun NotAdopted() {
    MyTheme {
        PuppyDetail(puppy = Puppy("", R.drawable.puppy_01, "Labrador Retriever", adoption = false))
    }
}
