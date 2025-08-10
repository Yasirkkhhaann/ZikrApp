package com.example.zikrapp.ui.viewmodel

data class ZikrUiState(
    val isSpeakerOn: Boolean = true,
    val isVibrationOn: Boolean = true,
    val islock : Boolean = false,
    val countCurrent: Int=0,
    val countTotal: Int=0,
    val limitReached: Boolean = false,
    val showZikrCompletedDialog: Boolean = false,
    val showResetConfirmationDialog: Boolean = false,


    val lastZikrId: Int = 0,

    val saveIconEnabled: Boolean = true,

    val zikrName: String = "",
    val zikrStart: String = "",
    val zikrEnd: String = "",
    val zikrDescription: String = "",


)
