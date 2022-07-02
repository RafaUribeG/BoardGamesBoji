package com.example.boardgamesboji.view


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.boardgamesboji.R
import com.example.boardgamesboji.adapter.GameAdapter

import org.junit.Rule
import org.junit.Test

import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val mainActivity = ActivityScenarioRule(MainActivity::class.java)

    @Test
    @LargeTest
    fun prueba_click_recycler_muestra_detalle(){
        onView(withId(R.id.myRecycler))
            .perform(scrollToPosition<GameAdapter.ViewHolder>(4)
            )
            .perform(
                actionOnItemAtPosition<GameAdapter.ViewHolder>(
                    2,
                    click()
                )
            )

        //Revisar que se cargue el juego
        onView(withId(R.id.tvName))
            .check(
                ViewAssertions.matches(
                    ViewMatchers.withText("Stars of Akarios")
                )
            )
    }
}