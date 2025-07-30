package com.example.zikrapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zikrapp.data.DatabaseInitializer
import com.example.zikrapp.data.Zikr
import com.example.zikrapp.data.ZikrDao
import com.example.zikrapp.data.ZikrStateee
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DataBaseViewModel : ViewModel() {


    val ZikrDao: ZikrDao = DatabaseInitializer.zikrDatabase.getzikrDao()

    private val _uiState = MutableStateFlow(ZikrUiState())

    val uiState: StateFlow<ZikrUiState> = _uiState.asStateFlow()
    val zikrlist: StateFlow<List<Zikr>> = ZikrDao.getAllZikrs()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())


        fun updatelastZikrId(id:Int){
        _uiState.update {
            it.copy(
                lastZikrId = id
            )
        }

    }

        fun loadZikrBounds(start: Int, end: Int) {
        _uiState.update { it.copy(countCurrent = start, countTotal = end) }
    }


    fun getLastZikrId(): Flow<Int?> = ZikrDao.getLastZikrId()


    suspend fun setLastZikr(id: Int) = withContext(Dispatchers.IO) {
        ZikrDao.setLastZikr(id)
    }


    suspend fun getZikrById(id: Int): Flow<Zikr?> = withContext(Dispatchers.IO) {
        ZikrDao.getZikrById(id)
    }

    fun updatezikrbycount(id: Int, count: Int) {

        viewModelScope.launch(Dispatchers.IO) {

            ZikrDao.updatebyidcount(id, count)

        }
    }

    fun deleteZikr(zikrId: Int) {

        viewModelScope.launch(Dispatchers.IO) {

            ZikrDao.deleteZikr(zikrId)
        }
    }


    fun addZikr(zikr: Zikr) {
        viewModelScope.launch(Dispatchers.IO) {
            ZikrDao.addZikr(zikr)
        }

    }

    fun addtest(id: ZikrStateee) {

        viewModelScope.launch(Dispatchers.IO) {
            ZikrDao.addtest(id)
        }

    }

    fun updateZikr(
        zikrId: Int,
        zikrName: String,
        Start: Int,
        End: Int,
        zikrDescription: String
    ) {

        viewModelScope.launch(Dispatchers.IO) {

            ZikrDao.updateZikr(zikrId, zikrName, Start, End, zikrDescription)

        }
    }


    fun incrementCount() {

        if(_uiState.value.countCurrent < _uiState.value.countTotal){



     _uiState.update {
                it.copy(
                    countCurrent = it.countCurrent + 1
                )
            }


            updatezikrbycount(_uiState.value.lastZikrId,_uiState.value.countCurrent)


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

    fun showResetConfirmationDialog() {
        _uiState.update {
            it.copy(
                showResetConfirmationDialog = true
            )
        }
    }

        fun dismissZikrCompletedDialog() {
        _uiState.update { currentState ->
            currentState.copy(showZikrCompletedDialog = false)
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

        updatezikrbycount(_uiState.value.lastZikrId,_uiState.value.countCurrent)


    }

    fun confirmAndResetZikr() {
        _uiState.update {
            it.copy(
                countCurrent = 0, // Reset the count
                showResetConfirmationDialog = false // Hide the dialog
                // Potentially reset other relevant states if needed
            )
        }
        updatezikrbycount(_uiState.value.lastZikrId,_uiState.value.countCurrent)

    }

    fun toggleVibration() {
        _uiState.value = _uiState.value.copy(
            isVibrationOn = !_uiState.value.isVibrationOn
        )
        // Add your vibration control logic here, e.g.:
        if (_uiState.value.isVibrationOn) startVibration() else stopVibration()
    }

        fun toggleSpeaker() {
        _uiState.value = _uiState.value.copy(
            isSpeakerOn = !_uiState.value.isSpeakerOn
        )
        // Add your sound control logic here, e.g.:
        if (_uiState.value.isSpeakerOn) playSound() else stopSound()
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