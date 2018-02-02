package com.neos.trackandroll.view.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;

import com.neos.trackandroll.R;
import com.neos.trackandroll.model.constant.Constant;
import com.neos.trackandroll.utils.DialogUtils;
import com.neos.trackandroll.utils.PermissionUtils;

import java.util.HashMap;

public class SplashActivity extends AbstractActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set splash Layout
        setContentView(R.layout.activity_splash);

        if(PermissionUtils.areAllPermissionsGranted(this)){
            onPermissionGranted();
        }
    }


    /**
     * Method that set the splash screen timer
     */
    private void launchTimerSplash(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                HashMap<String,String> map = new HashMap<>();
                finishWithResult(ServiceActivity.DISPLAY_ACTIVITY_LOGIN,map);
            }
        }, Constant.TIME_SPLASH_SCREEN);
    }

    /**
     * Method that launch the splash screen if permission is granted
     */
    private void onPermissionGranted(){
        launchTimerSplash();
    }

    /**
     * method that dispatches the activities in fuction of the request and result codes
     * @param requestCode the request code of an activity
     * @param resultCode the result code of an activity
     * @param data the intent
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(PermissionUtils.areAllPermissionsGranted(this)){
            onPermissionGranted();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * Method called when the user accept or decline the permission requested by the app
     * @param requestCode : the request code of the permission request
     * @param permissions : the different permissions
     * @param grantResults : the result of the permissions
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case PermissionUtils.MY_PERMISSIONS_REQUEST_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                String permission="";
                if(permissions.length>0){
                    permission = permissions[0];
                }
                boolean reAskPermission=false;
                if(grantResults.length>0&&grantResults[0] == PackageManager.PERMISSION_DENIED) {
                    reAskPermission = true;
                }
                if (reAskPermission) {
                    // user rejected the permission
                    boolean showRationale = false;
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                        showRationale = shouldShowRequestPermissionRationale( permission );
                    }
                    if (! showRationale) {
                        // user also CHECKED "never ask again" you can either enable some fall back,
                        // disable features of your app or open another dialog explaining again the permission and directing to
                        // the app setting

                        DialogUtils.displayDialogAlertPermission(this);
                    }else {
                        // Ooooooooh...
                        PermissionUtils.areAllPermissionsGranted(this);
                    }
                } else {
                    // SUPER !!!
                    onPermissionGranted();
                }
            }
        }
    }
}
