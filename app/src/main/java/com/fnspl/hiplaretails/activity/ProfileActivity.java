package com.fnspl.hiplaretails.activity;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fnspl.hiplaretails.R;
import com.fnspl.hiplaretails.databinding.ActivityProfileBinding;
import com.fnspl.hiplaretails.databinding.ActivitySignUpBinding;
import com.fnspl.hiplaretails.model.apiResponse.Profile;
import com.fnspl.hiplaretails.model.apiResponse.User;
import com.fnspl.hiplaretails.model.apiResponse.UserDetails;
import com.fnspl.hiplaretails.model.apiResponse.UserUploadDetails;
import com.fnspl.hiplaretails.utils.ImageRotated;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends BaseActivity {


    TextView tv_title, tv_update;
    ImageView img_profile;
    String email, first_name, last_name, password, address, phone, pinCode;
    String Tag = SignUpActivity.class.getName();
    ActivityProfileBinding binding;
    Uri selectedImage;
    String filePath="";
    final int RESULT_LOAD_IMAGE = 400;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile);
        binding.setActivity(this);

        initView();
        findViewById(R.id.img_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void initView() {
        tv_title = findViewById(R.id.tv_title);
        tv_update = findViewById(R.id.tv_update);
        img_profile = findViewById(R.id.img_profile);
        binding.etPincode.setText(app.getAppSettings().__zip_code);
        binding.etMobile.setText(app.getAppSettings().__mobile);
        binding.etAddress.setText(app.getAppSettings().__address);
        binding.etFName.setText(app.getAppSettings().__fName);
        binding.etLName.setText(app.getAppSettings().__lName);
        binding.etEmail.setText(app.getAppSettings().__uEmail);

        /*if (!app.getAppSettings().__uImage.equals("")){
            Glide.with(ProfileActivity.this)
                    .load(app.getAppSettings().__uImage)
                    .into(binding.imgProfile);
        }*/


    }


    public void imageDialogCall() {
        checkCameraPermissions();
    }

    public void profileSubmit() {
        if (validate()) {
            if (connection.isActive()) {
                profileUpdateApi();
            }
        }

    }


    public boolean validate() {
        email = binding.etEmail.getText().toString().trim();
        first_name = binding.etFName.getText().toString().trim();
        last_name = binding.etLName.getText().toString().trim();
        phone = binding.etMobile.getText().toString().trim();
        address = binding.etAddress.getText().toString().trim();
        pinCode = binding.etPincode.getText().toString().trim();
        password = binding.etPassword.getText().toString().trim();

        if (email.length() == 0) {
            binding.etEmail.setError("Put Your Email");
            binding.etEmail.setFocusable(true);
            return false;
        }

        if (!isValidEmail(email)) {
            binding.etEmail.setError("Put Your Valid Email");
            binding.etEmail.setFocusable(true);
            return false;
        }
        if (phone.length() == 0) {
            binding.etMobile.setError("Put Your Phone No.");
            binding.etMobile.setFocusable(true);
            return false;
        }
        if (phone.length() != 10) {
            binding.etMobile.setError("Put Your Valid Phone No.");
            binding.etMobile.setFocusable(true);
            return false;
        }
        if (password.length() == 0) {
            binding.etPassword.setError("Put Your Password");
            binding.etPassword.setFocusable(true);

            return false;
        }
        if (first_name.length() == 0) {
            binding.etFName.setError("Put Your First Name");
            binding.etLName.setFocusable(true);
            return false;
        }
        if (last_name.length() == 0) {
            binding.etLName.setError("Put Your Last Name");
            binding.etLName.setFocusable(true);
            return false;
        }
        if (address.length() == 0) {
            binding.etAddress.setError("Put Your Address");
            binding.etAddress.setFocusable(true);
            return false;
        }
        if (pinCode.length() == 0) {
            binding.etPincode.setError("Put Your Pincode");
            binding.etPincode.setFocusable(true);
            return false;
        }


        return true;
    }


    private void checkCameraPermissions() {
        Dexter.withActivity(this).withPermissions(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE).withListener(new MultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport report) {
                if (report.areAllPermissionsGranted()) {

                    getImageDialog();
                }
            }

            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                token.continuePermissionRequest();
            }
        }).check();
    }

    public void getImageDialog() {
        AlertDialog.Builder getImageFrom = new AlertDialog.Builder(ProfileActivity.this);
        getImageFrom.create();
        final String[] opsChars = {"Capture Photo", "Choose from Gallery"};
        getImageFrom.setItems(opsChars, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {

                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, 0);
                } else if (which == 1) {

                    Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                    startActivityForResult(i, RESULT_LOAD_IMAGE);

                }
                dialog.dismiss();
            }
        });


        getImageFrom.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            ImageRotated imageRotated = new ImageRotated();
            if (requestCode == 0) {
                selectedImage = data.getData();

                if (selectedImage == null) {
                    Bitmap photo = (Bitmap) data.getExtras().get("data");
                    selectedImage = getImageUri(this, photo);
                    filePath = getRealPathFromURI(selectedImage);
                    Log.e("selectedImage1", selectedImage.toString() + "+++");
                } else {
                    filePath = getRealPathFromURI(selectedImage);
                }


                Log.e("selectedImage", selectedImage + "+++");


                // imageView.setImageBitmap((BitmapFactory.decodeFile(filePath)));

                // binding.imgProfile.setImageBitmap(imageRotated.getRotatedBitmap(this,filePath));

                Log.e("getImageFilePath", imageRotated.getCompressFilePath(this, filePath) + "");

                Bitmap bitmap = imageRotated.getRotatedBitmap(this, filePath);
                binding.imgProfile.setImageBitmap(bitmap);

                filePath = imageRotated.getImagePathFromBitmap(this, bitmap);

                Log.e("selectedfilepath", filePath + "");

            } else if (requestCode == RESULT_LOAD_IMAGE && null != data) {
                Uri selectedImage = data.getData();
                filePath = getRealPathFromURI(selectedImage);
                Bitmap bitmap = imageRotated.getRotatedBitmap(this, filePath);
                binding.imgProfile.setImageBitmap(bitmap);
                filePath = imageRotated.getImagePathFromBitmap(this, bitmap);

                Log.e("selectedfilepath", filePath + "");

            }

        }
    }


    public String getRealPathFromURI(Uri contentUri) {
        String path = null;
        String[] proj = {MediaStore.MediaColumns.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
            path = cursor.getString(column_index);
        }
        cursor.close();
        return path;
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 75, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    private void profileUpdateApi() {
        showProgressBar();
        Map<String, RequestBody> map = new HashMap<>();
        map.put("userid", toRequestBody(app.getAppSettings().__uId));
        map.put("email", toRequestBody(email));
        map.put("phone", toRequestBody(phone));
        map.put("password", toRequestBody(password));
        map.put("firstname", toRequestBody(first_name));
        map.put("lastname", toRequestBody(last_name));
        map.put("address", toRequestBody(address));
        map.put("pincode", toRequestBody(pinCode));



        Call<UserUploadDetails> call;
        if (filePath.length()>10){
            File file = new File(filePath);
            RequestBody mFile = RequestBody.create(MediaType.parse("image*"),file);
            MultipartBody.Part  fileToUpload = MultipartBody.Part.createFormData("image", file.getName(), mFile);
            call = mApis.updateProfileImage(fileToUpload,map);
        }else{
            call = mApis.updateProfile(map);
        }

          Log(Tag, map.toString() + "");


            call.enqueue(new Callback<UserUploadDetails>() {
                @Override
                public void onResponse(Call<UserUploadDetails> call, Response<UserUploadDetails> response) {
                    if (response.isSuccessful()) {
                        if (response.body().getStatus().equals("success")) {
                            Profile user = response.body().getProfile().get(0);
                            app.getAppSettings().setSession(user);
                            finish();
                        }

                    }
                    Toast(response.body().getMessage());
                    hideProgressBar();

                }

                @Override
                public void onFailure(Call<UserUploadDetails> call, Throwable t) {
                    hideProgressBar();
                }
            });


    }


}
