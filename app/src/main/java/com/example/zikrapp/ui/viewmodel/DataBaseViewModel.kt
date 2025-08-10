package com.example.zikrapp.ui.viewmodel

import android.content.Context
import android.media.MediaPlayer
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zikrapp.R
import com.example.zikrapp.data.DatabaseInitializer
import com.example.zikrapp.data.Zikr
import com.example.zikrapp.data.ZikrDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DataBaseViewModel : ViewModel() {


    val ZikrDao: ZikrDao = DatabaseInitializer.zikrDatabase.getzikrDao()

    val coro = viewModelScope
    private val _uiState = MutableStateFlow(ZikrUiState())

    val uiState: StateFlow<ZikrUiState> = _uiState.asStateFlow()
    val zikrlist: StateFlow<List<Zikr>> = ZikrDao.getAllZikrs()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())


    init
    {
        viewModelScope.launch {
        val zikrlastid = getLastZikrId().first()?: 0
            val speakerState = getSoundState().first()?:1
            val vibrationState = getVibrationState().first()?:1

            updatelastZikrId(zikrlastid)
            setStateForspeaker(speakerState)
            setStateForvibration(vibrationState)
            if(zikrlastid == 0){
                _uiState.update {
                    it.copy(
                        saveIconEnabled = true
                    )
                }
            }
            else{
                _uiState.update {
                    it.copy(
                        saveIconEnabled = false
                    )
                }
            }
    }
    }
    fun updatesaveState(){
        if(_uiState.value.lastZikrId == 0){
            _uiState.update {
                it.copy(
                    saveIconEnabled = true
                )
            }
        }
        else{
            _uiState.update {
                it.copy(
                    saveIconEnabled = false
                )
            }
        }
    }
    fun updatelastZikrId(id: Int) {
        _uiState.update {
            it.copy(
                lastZikrId = id
            )
        }

    }


    fun setStateForspeaker(soundState: Int) {

        if (soundState == 1) {
            _uiState.update {
                it.copy(

                    isSpeakerOn = true
                )
            }
        }
        if (soundState == 0) {
            _uiState.update {
                it.copy(

                    isSpeakerOn = false
                )
            }

        }


    }


    fun setStateForvibration(vibrationState: Int) {

        if (vibrationState == 1) {
            _uiState.update {
                it.copy(

                    isVibrationOn = true
                )
            }
        }
        if (vibrationState == 0) {
            _uiState.update {
                it.copy(

                    isVibrationOn = false
                )
            }

        }


    }

    fun loadZikrBounds(start: Int, end: Int) {


        _uiState.update {

            it.copy(

            countCurrent = start, countTotal = end) }
    }


    fun getLastZikrId(): Flow<Int?> = ZikrDao.getLastZikrId()

    fun getSoundState(): Flow<Int?> = ZikrDao.getSoundState()

    suspend fun setSoundState(id: Int) = withContext(Dispatchers.IO) {
        ZikrDao.setSoundState(id)
    }

    fun getVibrationState(): Flow<Int?> = ZikrDao.getVibrationState()

    suspend fun setVibrationState(id: Int) = withContext(Dispatchers.IO) {
        ZikrDao.setVibrationState(id)
    }

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

    fun resetZikr(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            ZikrDao.resetZikr(id)
        }
    }

    fun addZikr(zikr: Zikr) {
        viewModelScope.launch(Dispatchers.IO) {
            ZikrDao.addZikr(zikr)
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

        if (_uiState.value.countCurrent < _uiState.value.countTotal) {


            _uiState.update {
                it.copy(
                    countCurrent = it.countCurrent + 1
                )
            }


            updatezikrbycount(_uiState.value.lastZikrId, _uiState.value.countCurrent)


        } else if (_uiState.value.countTotal == 0) {
            _uiState.update {
                it.copy(
                    countCurrent = it.countCurrent + 1,

                    )
            }
        } else {
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

    fun restartZikrAfterCompletion() {
        _uiState.update {
            it.copy(
                countCurrent = 0,
                showZikrCompletedDialog = false

            )


        }

        updatezikrbycount(_uiState.value.lastZikrId, _uiState.value.countCurrent)


    }

    fun confirmAndResetZikr() {
        _uiState.update {
            it.copy(
                countCurrent = 0, // Reset the count
                showResetConfirmationDialog = false // Hide the dialog
                // Potentially reset other relevant states if needed
            )
        }
        updatezikrbycount(_uiState.value.lastZikrId, _uiState.value.countCurrent)

    }

    fun playclick(context: Context) {
        val mediaPlayer = MediaPlayer.create(context, R.raw.click11)
        mediaPlayer.setOnCompletionListener { it.release() }
        mediaPlayer.start()
    }

    fun toggleVibration() {
        _uiState.value = _uiState.value.copy(
            isVibrationOn = !_uiState.value.isVibrationOn
        )
        coro.launch {
            setVibrationState(if (_uiState.value.isVibrationOn) 1 else 0)
        }

    }

    fun toggleSpeaker() {
        _uiState.value = _uiState.value.copy(
            isSpeakerOn = !_uiState.value.isSpeakerOn
        )
        coro.launch {
            setSoundState(if (_uiState.value.isSpeakerOn) 1 else 0)
        }


    }

    fun toggleLock() {
        _uiState.value = _uiState.value.copy(
            islock = !_uiState.value.islock
        )
        // Add your vibration control logic here, e.g.:
        if (_uiState.value.islock) startLockLogic() else stopLockLogic()
    }

    fun startThemLogic() {

    }

    private fun stopLockLogic() {
    }

    private fun startLockLogic() {
    }

    private fun stopVibration() {

    }

    private fun startVibration() {

    }


}