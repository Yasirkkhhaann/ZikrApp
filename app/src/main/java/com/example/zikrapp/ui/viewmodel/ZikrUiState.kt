package com.example.zikrapp.ui.viewmodel

data class ZikrUiState(
    val isSpeakerOn: Boolean = true,
    val isVibrationOn: Boolean = true,
    val islock : Boolean = false,
    val start: Int = 0,
    val end: Int = 10,
    val limitReached: Boolean = false,
    val showZikrCompletedDialog: Boolean = false,
    val showResetConfirmationDialog: Boolean = false,
    val showSnackbar: Boolean = false,
    val snackbarMessage: String = "",


    val CurrentZikrId: Int = 1,


    val zikrName: String = "",
    val zikrStart: String = "",
    val zikrEnd: String = "",
    val zikrDescription: String = "",


)
