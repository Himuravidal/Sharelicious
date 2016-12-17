package cl.adachersoft.sharelicious;


import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Toast;

import com.frosquivel.magicalcamera.Functionallities.PermissionGranted;
import com.frosquivel.magicalcamera.MagicalCamera;
import com.frosquivel.magicalcamera.Objects.PermissionGrantedObject;


public class PhotoFragment extends Fragment {

    private MagicalCamera magicalCamera;
    private Dialog dialog;
    private ImageButton imageButton;

    public PhotoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_photo, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imageButton = (ImageButton) view.findViewById(R.id.photoBtn);
        setDialog();
    }

    private void setDialog() {
        dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_photo);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        ImageButton cameraBtn = (ImageButton) dialog.findViewById(R.id.cameraBtn);
        ImageButton galleryBtn = (ImageButton) dialog.findViewById(R.id.galleryBtn);

        cameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (magicalCamera.takeFragmentPhoto()) {
                    startActivityForResult(magicalCamera.getIntentFragment(), 333);
                }
            }
        });

        galleryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(magicalCamera.selectedFragmentPicture()){
                    startActivityForResult(Intent.createChooser(magicalCamera.getIntentFragment(),  "My Header Example"), 222);
                }
            }
        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //CALL THIS METHOD EVER
        magicalCamera.resultPhoto(requestCode, resultCode, data);

        //this is for rotate picture in this method
        //magicalCamera.resultPhoto(requestCode, resultCode, data, MagicalCamera.ORIENTATION_ROTATE_180);

        //with this form you obtain the bitmap (in this example set this bitmap in image view)

        imageButton.setImageBitmap(magicalCamera.getPhoto());

        //if you need save your bitmap in device use this method and return the path if you need this
        //You need to send, the bitmap picture, the photo name, the directory name, the picture type, and autoincrement photo name if           //you need this send true, else you have the posibility or realize your standard name for your pictures.
        String path = magicalCamera.savePhotoInMemoryDevice(magicalCamera.getPhoto(), "myPhotoName", "sharelicious", MagicalCamera.JPEG, true);

        if (path != null) {
            Toast.makeText(getContext(), "The photo is save in device, please check this path: " + path, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Sorry your photo dont write in devide, please contact with fabian7593@gmail and say this error", Toast.LENGTH_SHORT).show();
        }
    }



    public void showDialog(MagicalCamera magicalCamera) {
        this.magicalCamera =  magicalCamera;
        dialog.show();
    }

}
