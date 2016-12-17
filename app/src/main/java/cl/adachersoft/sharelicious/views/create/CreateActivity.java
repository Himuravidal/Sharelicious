package cl.adachersoft.sharelicious.views.create;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.frosquivel.magicalcamera.Functionallities.PermissionGranted;
import com.frosquivel.magicalcamera.MagicalCamera;
import com.frosquivel.magicalcamera.Objects.PermissionGrantedObject;

import cl.adachersoft.sharelicious.PhotoFragment;
import cl.adachersoft.sharelicious.R;

public class CreateActivity extends AppCompatActivity {

    private MagicalCamera magicalCamera;
    private PhotoFragment photoFragment;
    private PermissionGranted permissionGranted;
    private static final int PHOTO_SIZE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        photoFragment = (PhotoFragment) getSupportFragmentManager().findFragmentById(R.id.photoFragment);

        permissionGranted = new PermissionGranted(this);

        permissionGranted.checkCameraPermission();
        permissionGranted.checkWriteExternalPermission();
        permissionGranted.checkReadExternalPermission();

        magicalCamera = new MagicalCamera(this, PHOTO_SIZE, permissionGranted);

        if (Build.VERSION.SDK_INT < 23) {
            photoFragment.showDialog(magicalCamera);
        } else {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                photoFragment.showDialog(magicalCamera);
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        magicalCamera.permissionGrant(requestCode, permissions, grantResults);
        try {
            Log.d("REQUEST_CODE", String.valueOf(requestCode));
            Log.d("PERMISSIONS", permissions[0]);
            Log.d("GRANT", String.valueOf(grantResults[0]));
        } catch (NullPointerException e) {
            Log.d("CATCH", "CATCH");
        }

        if (PermissionGrantedObject.REQUEST_CAMERA_PERMISSION == requestCode) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            }
        }

        if (PermissionGrantedObject.REQUEST_WRITE_EXTERNAL_STORAGE_PERMISSION == requestCode) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            }
        }

        if (PermissionGrantedObject.REQUEST_READ_EXTERNAL_STORAGE_PERMISSION == requestCode) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //photoFragment.showDialog(magicalCamera);
            }
        }
    }
}
