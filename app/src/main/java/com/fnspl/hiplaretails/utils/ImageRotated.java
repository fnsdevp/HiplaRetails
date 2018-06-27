package com.fnspl.hiplaretails.utils;


import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import java.io.ByteArrayOutputStream;

/**
 * Created by nur on 24/11/17.
 */

public class ImageRotated {

    private ExifInterface exif = null;



    public Bitmap getRotatedBitmap(Context context, String picturePath){

        ProgressDialog mDialog = new ProgressDialog(context);
        mDialog.setMessage("wait...");
        mDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mDialog.setIndeterminate(true);
        mDialog.setCancelable(false);
        mDialog.show();

        try {
            exif = new ExifInterface(picturePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int exifOrientation = Integer.parseInt(exif.getAttribute(ExifInterface.TAG_ORIENTATION));

        Bitmap profileImageBitmap = BitmapFactory.decodeFile(picturePath);

        Matrix matrix = new Matrix();

        if (exifOrientation == ExifInterface.ORIENTATION_NORMAL) {
            matrix.postRotate(0);
        } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_90) {
            matrix.postRotate(90);
        } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_180) {
            matrix.postRotate(180);
        } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_270) {
            matrix.postRotate(270);
        }

        Bitmap scaledBitmap = Bitmap.createScaledBitmap(profileImageBitmap, profileImageBitmap.getWidth() / 2, profileImageBitmap.getHeight() / 2, true);

        mDialog.dismiss();
        return Bitmap.createBitmap(scaledBitmap, 0, 0, scaledBitmap.getWidth(), scaledBitmap.getHeight(), matrix, true);

    }


    public String getImagePathFromBitmap(Context context, Bitmap bitmapPath){

        ByteArrayOutputStream bos1 = new ByteArrayOutputStream();
        bitmapPath.compress(Bitmap.CompressFormat.JPEG, 90, bos1);

        String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmapPath, "Title", null);
        Uri uti =  Uri.parse(path);


        String Filepath = "";
        try{
            String[] proj = {MediaStore.MediaColumns.DATA};
            Cursor cursor = context.getContentResolver().query(uti, proj, null, null, null);
            if (cursor.moveToFirst()) {
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
                Filepath = cursor.getString(column_index);
            }
            cursor.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        Log.v("ProfileFrag user image", Filepath);

        return Filepath;

    }


    public String getCompressFilePath(Context context, String picturePath){

        ProgressDialog mDialog = new ProgressDialog(context);
        mDialog.setMessage("wait...");
        mDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mDialog.setIndeterminate(true);
        mDialog.setCancelable(false);
        mDialog.show();

        Bitmap profileImageBitmap = null;
        try {
            exif = new ExifInterface(picturePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int exifOrientation = Integer.parseInt(exif.getAttribute(ExifInterface.TAG_ORIENTATION));

        profileImageBitmap = BitmapFactory.decodeFile(picturePath);

        Matrix matrix = new Matrix();

        if (exifOrientation == ExifInterface.ORIENTATION_NORMAL) {
            matrix.postRotate(0);
        } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_90) {
            matrix.postRotate(90);
        } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_180) {
            matrix.postRotate(180);
        } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_270) {
            matrix.postRotate(270);
        }

     //   Bitmap scaledBitmap = Bitmap.createScaledBitmap(profileImageBitmap, profileImageBitmap.getWidth() / 2, profileImageBitmap.getHeight() / 2, true);


        profileImageBitmap = Bitmap.createBitmap(profileImageBitmap, 0, 0, profileImageBitmap.getWidth() / 2, profileImageBitmap.getHeight() / 2, matrix, true);

        ByteArrayOutputStream bos1 = new ByteArrayOutputStream();
        profileImageBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bos1);

        String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), profileImageBitmap, "Title", null);
        Uri uti =  Uri.parse(path);


        String Filepath = "";
        try{
            String[] proj = {MediaStore.MediaColumns.DATA};
            Cursor cursor = context.getContentResolver().query(uti, proj, null, null, null);
            if (cursor.moveToFirst()) {
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
                Filepath = cursor.getString(column_index);
            }
            cursor.close();
        }catch (Exception e){
            e.printStackTrace();
        }


        mDialog.dismiss();
        return Filepath;

    }



}
