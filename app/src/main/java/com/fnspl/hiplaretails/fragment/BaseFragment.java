package com.fnspl.hiplaretails.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.fnspl.hiplaretails.R;
import com.fnspl.hiplaretails.activity.BaseActivity;
import com.fnspl.hiplaretails.application.Apis;
import com.fnspl.hiplaretails.application.App;
import com.fnspl.hiplaretails.application.AppSettings;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BaseFragment extends Fragment {

    ProgressDialog mDialog;
    private OnFragmentInteractionListener mListener;


    public BaseFragment() {
        // Required empty public constructor
    }
    ImageLoader imageLoader;
    private DisplayImageOptions options = new DisplayImageOptions.Builder().showImageOnLoading(R.mipmap.ic_launcher)
            .cacheInMemory(true).cacheOnDisk(true)
            .build();
    App app;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = (App)getActivity().getApplication();
        app.setAppSettings(new AppSettings(getActivity()));

        mDialog = new ProgressDialog(getActivity());
        mDialog.setMessage("Please wait...");
        mDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mDialog.setIndeterminate(true);
        mDialog.setCancelable(false);

        if (imageLoader == null) {
            imageLoader = ImageLoader.getInstance();
            if (!imageLoader.isInited()) {
                imageLoader.init(ImageLoaderConfiguration.createDefault(getActivity()));
            }
        }



    }

    public void showProgressBar(){
        if (mDialog!=null){
            mDialog.show();
        }
    }
    public void hideProgressBar(){
        if (mDialog!=null){
            mDialog.dismiss();
        }
    }
    public DisplayImageOptions option(){
        return options;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return null;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    public void Log(String calssName,String massage){
        Log.e(calssName,massage);
    }
    public void Toast(String msg){
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }
    public static RequestBody toRequestBody (String value) {
        RequestBody body = RequestBody.create(MediaType.parse("text/plain"), value);
        return body ;
    }

}
