package com.example.zikrapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ZikrControlModel : ViewModel() {

    private val _uiState = MutableStateFlow(ZikrUiState())

    val uiState: StateFlow<ZikrUiState> = _uiState.asStateFlow()




    fun getAllZikrs(){


    }

    fun addZikrs(zikrName: String,zikrStart: Int,zikrEnd: Int,zikrDescription: String){



    }

    fun deleteZikr(zikrId:Int) {

    }








    fun toggleSpeaker() {
        _uiState.value = _uiState.value.copy(
            isSpeakerOn = !_uiState.value.isSpeakerOn
        )
        // Add your sound control logic here, e.g.:
        if (_uiState.value.isSpeakerOn) playSound() else stopSound()
    }

    fun loadZikrBounds(start: Int, end: Int) {
        _uiState.update { it.copy(countCurrent = start, countTotal = end) }
    }


    fun incrementCount() {

       if(_uiState.value.countCurrent < _uiState.value.countTotal){
           _uiState.update {
               it.copy(
                   countCurrent = it.countCurrent + 1
               )
           }
       }

        else if(_uiState.value.countTotal == 0){
            _uiState.update {
                it.copy(
                    countCurrent = it.countCurrent + 1,

                )
            }
        }

        else{
           showZikrCompletedDialog()
        }
    }

    fun showZikrCompletedDialog() {
        _uiState.update {
            it.copy(
                showZikrCompletedDialog = true
            )
        }
    }

    fun dismissZikrCompletedDialog() {
        _uiState.update { currentState ->
            currentState.copy(showZikrCompletedDialog = false)
        }
    }


    fun showResetConfirmationDialog() {
        _uiState.update {
            it.copy(
                showResetConfirmationDialog = true
            )
        }
    }

    fun dismissResetConfirmationDialog() {
        _uiState.update { currentState ->
            currentState.copy(showResetConfirmationDialog = false)
        }
    }
    fun restartZikrAfterCompletion(){
        _uiState.update {
            it.copy(
                countCurrent = 0,
                showZikrCompletedDialog = false

            )

        }
    }



    fun showSnackbar(){


    }

    fun confirmAndResetZikr() {
        _uiState.update {
            it.copy(
                countCurrent = 0, // Reset the count
                showResetConfirmationDialog = false // Hide the dialog
                // Potentially reset other relevant states if needed
            )
        }
        // Add any other logic needed when a Zikr is reset (e.g., logging, etc.)
    }

    fun addZikr(zikrName:String,zikrStart:String,zikrEnd:String,zikrDescription:String){
        _uiState.update {
            it.copy(
                zikrName = zikrName,
                zikrStart = zikrStart,
                zikrEnd = zikrEnd,
                zikrDescription = zikrDescription
            )
        }

    }



    fun toggleVibration() {
        _uiState.value = _uiState.value.copy(
            isVibrationOn = !_uiState.value.isVibrationOn
        )
        // Add your vibration control logic here, e.g.:
        if (_uiState.value.isVibrationOn) startVibration() else stopVibration()
    }

    fun toggleLock() {
        _uiState.value = _uiState.value.copy(
            islock = !_uiState.value.islock
        )
        // Add your vibration control logic here, e.g.:
        if (_uiState.value.islock) startLockLogic() else stopLockLogic()
    }

    fun startThemLogic(){

    }

    private fun stopLockLogic() {
    }

    private fun startLockLogic() {
    }

    private fun stopVibration() {

    }

    private fun startVibration() {

    }

    private fun stopSound() {

    }

    private fun playSound() {

    }


}