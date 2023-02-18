package com.livingtechusa.gotjokes.util;


import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.compose.ui.unit.Dp;
import androidx.core.app.ActivityCompat;

import com.livingtechusa.gotjokes.BaseApplication;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Objects;

public class TakeScreenShot {

    public static Uri takeScreenShot(View view, String caption, Dp height, Dp width) {
        Integer length = caption.length() / 3;
        String fileName = caption.substring(0, length);
        fileName = fileName.replaceAll("\\s", "_");
        fileName = fileName.replace(".", "-");
        Date date = new Date();
        CharSequence formatedDate = DateFormat.format("yyyy-MM-dd_hh:mm:ss", date);
        Integer top = ((int) height.getValue() / 5);
        double bottom = (top * 1.5);

        view.setPadding(0, top, 0, (int) bottom);
        try {
            String dirPath = Environment.getExternalStorageDirectory().toString() + "/Got_Jokes";
            File fileDir = new File(dirPath);
            if (!fileDir.exists()) {
                boolean mkDir = fileDir.mkdir();
            }
            String path = dirPath + "/" + fileName + "-" + formatedDate + ".jpeg";


            view.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(
                    view.getDrawingCache()
            );
            view.setDrawingCacheEnabled(false);
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.Q) {
                String filePath = fileName + "-" + formatedDate;
                Uri imageUri = saveImage(bitmap, filePath);
                view.setPadding(0, 0, 0, 0);
                return imageUri;
            }
            File imageFile = new File(path);

            FileOutputStream fileOutputStream = new FileOutputStream(imageFile);
            int quality = 100;
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            view.setPadding(0, 0, 0, 0);
            return Uri.fromFile(BaseApplication.getInstance().getFileStreamPath(imageFile.getName()));

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(view.getContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return null;
    }

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSION_STORAGE = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };

    public static void verifyStoragePermission(Activity activity) {
        int permissionWrite = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int permissionRead = ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE);
        if (permissionWrite != PackageManager.PERMISSION_GRANTED || permissionRead != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSION_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

    private static Uri saveImage(Bitmap bitmap, String fileName) {
        String TAG = "ScreenShot";
        OutputStream fos;
        try {
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.Q) {
                ContentResolver resolver = BaseApplication.getInstance().getContentResolver();
                ContentValues contentValues = new ContentValues();
                contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, fileName + ".jpg");
                contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg");
                Uri imageUri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
                fos = resolver.openOutputStream(Objects.requireNonNull(imageUri));
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                Objects.requireNonNull(fos);
                return imageUri;
            }
        } catch (Exception e) {
            Log.e(TAG, "Error: " + e.getMessage() + " with cause " + e.getCause());
        }
        return null;
    }
}