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
    private LifeTime lifeTime;
    private int permissionCount = 0;
    private String[] permissions = {
            Manifest.permission.POST_NOTIFICATIONS
    };

    private List<String> permissionList = new ArrayList<>();

    private ActivityResultLauncher<String[]> permissionLauncher;
    private ActivityResultLauncher<Intent> manageAllFilesPermissionLauncher;

    public static synchronized PermissionUtils getInstance() {
        if (permissionUtils == null) {
            permissionUtils = new PermissionUtils();
        }
        return permissionUtils;
    }

    private PermissionUtils() {
    }

    public void checkPermission(ComponentActivity activity, LifeTime lifeTime) {
        this.lifeTime = lifeTime;
        permissionList.clear(); // Clear previous permission requests

        // Initialize the launcher if not already initialized
        if (permissionLauncher == null) {
            initLaunchers(activity);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (String permission : permissions) {
                if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
                    permissionList.add(permission);
                }
            }
            permissionLauncher.launch(permissionList.toArray(new String[0]));
//            getAllFileEditPermission(activity);
        } else {
            lifeTime.after(); // No need to check permissions on lower API levels
        }
    }

    private void initLaunchers(ComponentActivity activity) {
        permissionCount = 0;
        // Initialize the launcher for requesting permissions
        permissionLauncher = activity.registerForActivityResult(
                new ActivityResultContracts.RequestMultiplePermissions(),
                new ActivityResultCallback<Map<String, Boolean>>() {
                    @Override
                    public void onActivityResult(Map<String, Boolean> result) {
                        boolean allGranted = true;
                        for (boolean isGranted : result.values()) {
                            if (isGranted) {
                                permissionCount++;
                            }
                        }
                        if (permissionCount == 0) {
                            Toast.makeText(activity, "获取权限失败，无法使用app.", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (permissionCount < 10) {
                            Toast.makeText(activity, "获取部分权限失败，只能使用部分功能.", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Toast.makeText(activity, "成功获取权限,可以使用全部功能.", Toast.LENGTH_SHORT).show();

                    }
                }
        );

        // Initialize the launcher for managing all files access
        manageAllFilesPermissionLauncher = activity.registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (Environment.isExternalStorageManager()) {
                            lifeTime.after();
                            permissionCount += 10;
                        }
                    }
                }
        );
    }

    private void getAllFileEditPermission(ComponentActivity activity) {
        if (Environment.isExternalStorageManager()) {
            lifeTime.after();
            Toast.makeText(activity, "Permission granted! You can now access all files.", Toast.LENGTH_SHORT).show();
        } else {
            launchGrantAllFilesIntent(activity);
        }
    }

    private void launchGrantAllFilesIntent(ComponentActivity activity) {
        Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
        intent.setData(Uri.parse("package:" + activity.getPackageName()));
        manageAllFilesPermissionLauncher.launch(intent);
    }

    public interface LifeTime {
        void before();

        void after();
    }
}
