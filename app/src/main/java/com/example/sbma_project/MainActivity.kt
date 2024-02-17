package com.example.sbma_project

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.sbma_project.internetConnection.ConnectionStatus
import com.example.sbma_project.internetConnection.currentConnectivityStatus
import com.example.sbma_project.internetConnection.observeConnectivityAsFLow
import com.example.sbma_project.pemissionManager.PermissionRequestDialog
import com.example.sbma_project.pemissionManager.PermissionUtils
import com.example.sbma_project.pemissionManager.PermissionUtils.hasAllPermission
import com.example.sbma_project.pemissionManager.PermissionUtils.hasLocationPermission
import com.example.sbma_project.pemissionManager.PermissionUtils.openAppSetting
import com.example.sbma_project.ui.theme.SBMAProjectTheme
import com.example.sbma_project.views.NoInternetScreen

@RequiresApi(Build.VERSION_CODES.S)
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SBMAProjectTheme {
                // request permission
                PermissionRequester()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Using AppNavigation composable
                    // checkConnectivityStatus()

                    val isConnected = checkConnectivityStatus()
                    MainScreen(isConnected)
                }
            }
        }
    }

    @Composable
    private fun PermissionRequester() {
        var showPermissionDeclinedRationale by rememberSaveable { mutableStateOf(false) }
        var showRationale by rememberSaveable { mutableStateOf(false) }
        val permissionLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestMultiplePermissions(),
            onResult = {
                it.forEach { (permission, isGranted) ->
                    if (!isGranted && PermissionUtils.locationPermissions.contains(permission)) {
                        showPermissionDeclinedRationale = true
                    }
                }
            }
        )
        if (showPermissionDeclinedRationale)
            PermissionRequestDialog(
                onDismissClick = {
                    if (!hasLocationPermission())
                        finish()
                    else showPermissionDeclinedRationale = false
                },
                onOkClick = { openAppSetting() }
            )
        if (showRationale)
            PermissionRequestDialog(
                onDismissClick = ::finish,
                onOkClick = {
                    showRationale = false
                    permissionLauncher.launch(PermissionUtils.allPermissions)
                }
            )
        LaunchedEffect(key1 = Unit) {
            when {
                hasAllPermission() -> return@LaunchedEffect
                PermissionUtils.locationPermissions.any { shouldShowRequestPermissionRationale(it) } -> showRationale =
                    true
                else -> permissionLauncher.launch(PermissionUtils.allPermissions)
            }
        }
    }
}

@Composable
fun checkConnectivityStatus(): Boolean {
    val connection by connectivityStatus()
    return connection != ConnectionStatus.Unavailable
}


/*@Composable
fun checkConnectivityStatus() : Boolean{
    val connection by connectivityStatus()

    val isConnected = connection === ConnectionStatus.Available

    if (isConnected)
    {
        MainScreen()


    } else {
        NoInternetScreen()
    }

}*/
@Composable
fun connectivityStatus(): State<ConnectionStatus> {
    val mContext = LocalContext
        .current

    return produceState(
        initialValue = mContext.currentConnectivityStatus
    ) {
        mContext.observeConnectivityAsFLow().collect {
            value = it
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SBMAProjectTheme {
        // Replace with your preview content
        Text("Preview Content")
    }
}
