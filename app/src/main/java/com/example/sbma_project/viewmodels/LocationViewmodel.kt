package com.example.sbma_project.viewmodels

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sbma_project.domain.GetLocationUseCase
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@RequiresApi(Build.VERSION_CODES.S)
@HiltViewModel
class LocationViewModel @Inject constructor(
    private val getLocationUseCase: GetLocationUseCase
) : ViewModel() {

    private val _viewState: MutableStateFlow<ViewState> = MutableStateFlow(ViewState.Loading)
    val viewState = _viewState.asStateFlow()

    // State to hold the list of LatLng points representing the polyline
    private val _pathPoints: MutableStateFlow<List<LatLng>> = MutableStateFlow(emptyList())
    val pathPoints = _pathPoints.asStateFlow()

    fun handle(event: PermissionEvent) {
        when (event) {
            PermissionEvent.Granted -> {
                viewModelScope.launch {
                    getLocationUseCase.invoke().collect {
                        _viewState.value = ViewState.Success(it)

                        // Update pathPoints with new location
                        _pathPoints.value = _pathPoints.value + (it ?: LatLng(0.00,0.00))
                    }
                }
            }
            PermissionEvent.Revoked -> {
                _viewState.value = ViewState.RevokedPermissions
            }
        }
    }
}

sealed interface ViewState {
    data object Loading : ViewState
    data class Success(val location: LatLng?) : ViewState
    data object RevokedPermissions : ViewState
}

sealed interface PermissionEvent {
    object Granted : PermissionEvent
    object Revoked : PermissionEvent
}