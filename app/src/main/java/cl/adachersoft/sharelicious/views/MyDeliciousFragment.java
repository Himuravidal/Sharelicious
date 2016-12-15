package cl.adachersoft.sharelicious.views;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cl.adachersoft.sharelicious.R;


public class MyDeliciousFragment extends Fragment {

    public MyDeliciousFragment() {
        // Required empty public constructor
    }
    public static MyDeliciousFragment newInstance() {
        return new MyDeliciousFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_delicious, container, false);
    }

}
