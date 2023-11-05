package com.satyajit.newscompose.presentation.onboarding

sealed class OnBoardingEvent {
    /**
     * this class will have the events that will be send from ui to viewModel
     */

    object SaveAppEntry: OnBoardingEvent()
}