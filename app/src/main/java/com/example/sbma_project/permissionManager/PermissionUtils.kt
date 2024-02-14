package com.example.sbma_project.permissionManager

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat


object PermissionUtils {

    //necessary location permissions
    val locationPermissions = listOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION,
    ).toTypedArray()

    // bluetooth permission to connect to heart rate sensor
    @RequiresApi(Build.VERSION_CODES.S)
    val bluetoothPermission = Manifest.permission.BLUETOOTH

    // combining all the permissions
    val allPermissions = mutableListOf<String>().apply {
        addAll(locationPermissions)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            add(bluetoothPermission)
        }
    }.toTypedArray()

    // check if bluetooth permission is granted
    @RequiresApi(Build.VERSION_CODES.S)
    fun Context.hasBluetoothPermission() =
        ContextCompat.checkSelfPermission(
            this,
            bluetoothPermission,
        ) == PackageManager.PERMISSION_GRANTED

    // check if location permission is granted
    fun Context.hasLocationPermission() =
        locationPermissions.all {
            ContextCompat.checkSelfPermission(
                this,
                it
            ) == PackageManager.PERMISSION_GRANTED
        }

    // check if all permissions are granted
    fun Context.hasAllPermission() =
        allPermissions.all {
            ContextCompat.checkSelfPermission(
                this,
                it
            ) == PackageManager.PERMISSION_GRANTED
        }

    // function to open app setting in case a permission is permanently declined
    fun Context.openAppSetting() {
        Intent(
            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
            Uri.fromParts("package", packageName, null)
        ).also(::startActivity)
    }
}
