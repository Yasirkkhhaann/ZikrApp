package com.example.zikrapp.ui.viewmodel

import ads_mobile_sdk.t
import com.example.zikrapp.data.Zikr
import kotlinx.coroutines.flow.Flow

data class ZikrUiState(
    val isSpeakerOn: Boolean = true,
    val isVibrationOn: Boolean = true,
    val islock: Boolean = false,
    val countCurrent: Int = 0,
    val countTotal: Int = 0,
    val limitReached: Boolean = false,
    val showZikrCompletedDialog: Boolean = false,
    val showResetConfirmationDialog: Boolean = false,
    val showZikrNotSavedDialog: Boolean = false,
    val showDeleleDialog: Boolean = false,
    val showConfirmationDialogueToDeleteActiveZikr: Boolean = false,
    val isLoadingforNew: Boolean = false,
    val deleleConfirm: Boolean = false,

    val setzikrIdForDelete: Int = 0,

    val countingScreenLoadForNewZikr: Boolean = false,

    val lastZikrId: Int = 0,

    val saveIconEnabled: Boolean = true,

    val zikrName: String = "",
    val zikrStart: String = "",
    val zikrEnd: String = "",
    val zikrDescription: String = "",

    val zikrfromdb: Flow<Zikr?> = Zikr(1,"","",1,1,),


    )
