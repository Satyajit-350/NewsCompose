package com.satyajit.newscompose.domain.usecases.app_entry

import com.satyajit.newscompose.domain.manager.LocalUserManager

class SaveAppEntry(
    private val localUserManager: LocalUserManager
) {

    suspend operator fun invoke(){
        localUserManager.saveAppEntry()
    }

}