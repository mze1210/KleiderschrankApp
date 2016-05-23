package com.example.gruppe1.kleiderschrankapp.activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.gruppe1.kleiderschrankapp.R;
import com.example.gruppe1.kleiderschrankapp.model.Klamotte;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class KlamotteAnlegenActivity extends AppCompatActivity {

    //Dialogs
    public static final String DIALOG_IMAGE_TITLE = "Bild hinzufügen";
    public static final String DIALOG_CAPTURE_IMAGE = "Kamera";
    public static final String DIALOG_CHOOSE_IMAGE = "Aus Galerie auswählen";
    public static final CharSequence[] DIALOG_IMAGE_OPTIONS = {DIALOG_CAPTURE_IMAGE, DIALOG_CHOOSE_IMAGE, "Abbrechen"};
    public static final String DIALOG_DELETE_TITLE = "Please confirm!";
    public static final String DIALOG_DELETE_MESSAGE = "Are you sure you want to delete this contact?";
    public static final String DIALOG_DELETE_POSITIVE = "Delete";
    public static final String DIALOG_DELETE_NEGATIVE = "Cancel";

    public static final int REQUEST_IMAGE_CAPTURE = 0;
    public static final int REQUEST_IMAGE_CROP = 1;
    public static final int REQUEST_IMAGE_CHOOSE = 2;

    private Klamotte klamotte = new Klamotte();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_klamotte_anlegen);

        Button cameraButton = (Button) findViewById(R.id.cameraButton);
        cameraButton.setOnClickListener(new View.OnClickListener() {
            /**
             * OnClickListener for camera button opens AlertDialog for user selection
             * @param v clicked View
             */
            @Override
            public void onClick(View v) {
                Dialog selectionDialog = getSelectionDialog();
                selectionDialog.show();
            }
        });
    }

    /**
     * Starts Intent to choose existing image from gallery
     */
    private void chooseImage() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK);
        galleryIntent.setType("image/*");
        startActivityForResult(Intent.createChooser(galleryIntent, "Bild auswählen"), REQUEST_IMAGE_CHOOSE);
    }

    /**
     * Callback for startActivityForResult gets used to process result of intents
     *
     * @param requestCode request code of the started intent
     * @param resultCode  status code to determine whether intent was successful
     * @param data        returned data of the intent
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_IMAGE_CAPTURE:
                    cropImage();
                    break;
                case REQUEST_IMAGE_CHOOSE:
                    klamotte.setImage(data.getData());
                    cropImage();
                    break;
                case REQUEST_IMAGE_CROP:
                    ImageView preview = (ImageView) findViewById(R.id.imagePreview);
                    Bitmap tmp = data.getExtras().getParcelable("data");
                    preview.setImageBitmap(tmp);
                    break;
                default:
                    super.onActivityResult(requestCode, resultCode, data);
            }
        } else {
            Toast.makeText(this, "Whoops - something went wrong!", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Starts Intent to open camera and take picture
     */
    private void captureImage() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (isIntentSupported(cameraIntent)) {
            try {
                klamotte.setImage(Uri.fromFile(createImageFile()));
            } catch (IOException ex) {
                Toast.makeText(this, "Whoops - could not access external storage!", Toast.LENGTH_SHORT).show();
            }
            if (klamotte.getImage() != null) {
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, klamotte.getImage());
                try {
                    startActivityForResult(cameraIntent, REQUEST_IMAGE_CAPTURE);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(this, "Whoops - something went wrong!", Toast.LENGTH_SHORT).show();
                }
            }
        } else {
            Toast.makeText(this, "Whoops - your device doesn't support capturing images!", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Starts (unofficial) Intent to crop chosen image which is likely to fail
     */
    private void cropImage() {
        Intent cropIntent = new Intent("com.android.camera.action.CROP");
        cropIntent.setDataAndType(klamotte.getImage(), "image/*");
        cropIntent.putExtra("crop", "true");
        cropIntent.putExtra("aspectX", 1);
        cropIntent.putExtra("aspectY", 1);
        cropIntent.putExtra("outputX", 500);
        cropIntent.putExtra("outputY", 500);
        cropIntent.putExtra("return-data", true);
        cropIntent.putExtra(MediaStore.EXTRA_OUTPUT, klamotte.getImage());
        try {
            startActivityForResult(cropIntent, REQUEST_IMAGE_CROP);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "Whoops - your device doesn't support the crop action!", Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickHandler(View view) {
        Context context = getApplicationContext();
        Toast toast;
        CharSequence text = "";
        int duration = Toast.LENGTH_SHORT;

        if (view.getId() == R.id.cancelButton) {
            Intent listIntent = new Intent(this, MainActivity.class);
            startActivity(listIntent);
            text = "Abgebrochen";
        }
        if (view.getId() == R.id.saveButton) {
            //TODO
//            saveKlamotte();
            Intent listIntent = new Intent(this, MainActivity.class);
            startActivity(listIntent);
            text = "Abgespeichert";
        }
        toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    /**
     * Helper method which creates an AlertDialog to let the user decide
     *
     * @return the dialog
     */
    private Dialog getSelectionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(DIALOG_IMAGE_TITLE);
        builder.setItems(DIALOG_IMAGE_OPTIONS, new DialogInterface.OnClickListener() {
            /**
             * OnClickListener for dialog options to let user decide whether to open camera or choose existing image
             * @param dialog clicked Dialog
             * @param which position of the clicked option
             */
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String selected = (String) DIALOG_IMAGE_OPTIONS[which];
                switch (selected) {
                    case DIALOG_CAPTURE_IMAGE:
                        captureImage();
                        break;
                    case DIALOG_CHOOSE_IMAGE:
                        chooseImage();
                        break;
                    default:
                        dialog.dismiss();
                }
            }
        });
        return builder.create();
    }

    /**
     * Creates File for picture in external file directory
     *
     * @return new File in App's private file storage
     * @throws IOException if access to external storage fails
     */
    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "KLAMOTTE_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        if (!storageDir.exists()) {
            storageDir.mkdir();
        }

        return new File(storageDir, imageFileName + ".jpg");
    }

    /**
     * Helper method to check if a Intent is supported by device
     *
     * @param intent Intent to be checked
     * @return true if Intent is safe to use
     */
    private boolean isIntentSupported(Intent intent) {
        PackageManager packageManager = getPackageManager();
        List<ResolveInfo> activities = packageManager.queryIntentActivities(intent, PackageManager.MATCH_ALL);
        return !activities.isEmpty();
    }

}