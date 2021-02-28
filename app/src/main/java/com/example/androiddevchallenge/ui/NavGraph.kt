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
package com.example.androiddevchallenge.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.data.model.Puppy
import com.example.androiddevchallenge.ui.MainDestinations.DETAIL
import com.example.androiddevchallenge.ui.MainDestinations.DETAIL_KEY_ID
import com.example.androiddevchallenge.ui.MainDestinations.HOME
import com.example.androiddevchallenge.ui.detail.DetailScreen
import com.example.androiddevchallenge.ui.home.HomeScreen

object MainDestinations {
    const val HOME = "home"
    const val DETAIL = "detail"
    const val DETAIL_KEY_ID = "puppyId"
}

@Composable
fun NavGraph(startDestination: String = HOME) {
    val navController = rememberNavController()
    val actions = remember(navController) { MainActions(navController) }
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(HOME) {
            HomeScreen(navigateToDetails = actions.navigateToDetails)
        }
        composable(
            "$DETAIL/{$DETAIL_KEY_ID}",
            arguments = listOf(navArgument(DETAIL_KEY_ID) { type = NavType.StringType })
        ) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            DetailScreen(
                puppyId = arguments.getString(DETAIL_KEY_ID).orEmpty(),
                upPress = actions.upPress
            )
        }
    }
}

class MainActions(navController: NavHostController) {
    val upPress: () -> Unit = {
        navController.navigateUp()
    }
    val navigateToDetails: (Puppy) -> Unit = { puppy: Puppy ->
        navController.navigate("$DETAIL/${puppy.id}")
    }
}
