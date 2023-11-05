package com.satyajit.newscompose.domain.manager

import kotlinx.coroutines.flow.Flow


interface LocalUserManager {

    /**
     * In this interface we will save the app entry when we click on get started
     * on onBoarding screen
     */

    suspend fun saveAppEntry()

    fun readAppEntry():Flow<Boolean>

}