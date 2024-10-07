package com.example.kotlinlearn.Common;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.widget.Toast;

import androidx.activity.ComponentActivity;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PermissionUtils {
    private static PermissionUtils permissionUtils;
    private String[] permissions = {
            Manifest.permission.POST_NOTIFICATIONS
    };

    private List<String> permissionList = new ArrayList<>();

    private ActivityResultLauncher<String[]> permissionLauncher;

    public static synchronized PermissionUtils getInstance() {
        if (permissionUtils == null) {
            permissionUtils = new PermissionUtils();
        }
        return permissionUtils;
    }

    private PermissionUtils() {
    }

    public void checkPermission(ComponentActivity activity) {
        permissionList.clear(); // Clear previous permission requests

        // Initialize the launcher if not already initialized
        if (permissionLauncher == null) {
            initLaunchers(activity);
        }


        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(permission);
            }
        }
        permissionLauncher.launch(permissionList.toArray(new String[0]));

    }

    private void initLaunchers(ComponentActivity activity) {
        // Initialize the launcher for requesting permissions
        permissionLauncher = activity.registerForActivityResult(
                new ActivityResultContracts.RequestMultiplePermissions(),
                new ActivityResultCallback<Map<String, Boolean>>() {
                    @Override
                    public void onActivityResult(Map<String, Boolean> result) {
                    }
                }
        );
    }
}
