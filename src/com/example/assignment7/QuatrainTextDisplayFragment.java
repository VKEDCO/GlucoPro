package com.example.assignment7;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.VideoView;
import android.widget.MediaController;

public class QuatrainTextDisplayFragment extends Fragment
{

    static final String LOGTAG = QuatrainTextDisplayFragment.class.getSimpleName() + "_TAG";

    int mQuatrainIndex = 0;

    public static QuatrainTextDisplayFragment newInstance(int index)
    {
        Log.d(LOGTAG, "newInstance(" + index + ")");

        QuatrainTextDisplayFragment quatFrgmnt = new QuatrainTextDisplayFragment();

        Bundle args = new Bundle();
        args.putInt(MainActivity.mRes.getString(R.string.quat_index_key),
                                index);
        quatFrgmnt.setArguments(args);
        return quatFrgmnt;
    }

    public static QuatrainTextDisplayFragment newInstance(Bundle bundle)
    {
        int index = bundle.getInt(MainActivity.mRes.getString(R.string.quat_index_key),
                            0);
        return newInstance(index);
    }

    @SuppressLint("NewApi")
	@Override
    public void onInflate(Activity myActivity, AttributeSet attrs, Bundle savedInstanceState) {
            Log.d(LOGTAG, "onInflate(): AttributeSet key-value pairs:");
            for(int i=0; i<attrs.getAttributeCount(); i++)
            Log.d(LOGTAG, "" + attrs.getAttributeName(i) +
                            " = " + attrs.getAttributeValue(i));
            super.onInflate(myActivity, attrs, savedInstanceState);
    }

    @Override
    public void onAttach(Activity myActivity) {
            Log.d(LOGTAG, "onAttach(): activity is: " +
                            myActivity);
            super.onAttach(myActivity);
    }

    @Override
    public void onCreate(Bundle myBundle)
    {
        super.onCreate(myBundle);

        if(myBundle != null)
        {
            Log.d(LOGTAG, "Bundle keys:");
            for(String key : myBundle.keySet())
                Log.d(LOGTAG, "    " + key);
        }
        else
        {
            Log.d(LOGTAG, "Bundle is null");
        }

        mQuatrainIndex = getArguments().getInt(MainActivity.mRes.getString(R.string.quat_index_key), 0);
    }

    public int getDisplayedQuatrainIndex() {
            return mQuatrainIndex;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                    ViewGroup container, Bundle savedInstanceState)
    {
        Log.d(LOGTAG, "onCreateView(): container = " + container);

        if(container == null) {
                Log.d(LOGTAG, "Container is null.");
                return null;
        }

        View v = inflater.inflate(R.layout.quatrain_text_display, container, false);
        TextView tvQuatrainText = (TextView) v.findViewById(R.id.tvQuatrainText);
        String[] quatrain_lines = null;
        StringBuilder sb = new StringBuilder();

        switch ( mQuatrainIndex )
        {
            case 0: quatrain_lines = this.getActivity().getResources().getStringArray(R.array.q12);
                            sb.append("Quatrain 12\n\n");
                            break;
            case 1: quatrain_lines = this.getActivity().getResources().getStringArray(R.array.q77);
                            sb.append("Quatrain 77\n\n");
                            break;
            case 2: quatrain_lines = this.getActivity().getResources().getStringArray(R.array.q116);
                            sb.append("Quatrain 116\n\n");
                            break;
            case 3: quatrain_lines = this.getActivity().getResources().getStringArray(R.array.q494);
                            sb.append("Quatrain 494\n\n");
                            break;
            case 4: quatrain_lines = this.getActivity().getResources().getStringArray(R.array.q549);
                            sb.append("Quatrain 549\n\n");
                            break;
        }

        for(String ln : quatrain_lines )
        {
                sb.append(ln + "\n");
        }
        tvQuatrainText.setText(sb.toString());

        VideoView videoView = (VideoView)v.findViewById(R.id.video_view);
        videoView.setVideoURI(Uri.parse("android.resource://com.example.assignment7/" + R.raw.video));
        videoView.start();

        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedState)
    {
        super.onActivityCreated(savedState);
        if(savedState != null) {
                Log.d(LOGTAG, "onActivityCreated(): Bundle savedState contains:");
        for(String key : savedState.keySet()) {
            Log.d(LOGTAG, "    " + key);
        }
        }
        else {
        Log.d(LOGTAG, "onActivityCreated(): Bundle savedState is null");
        }

    }

    @Override
    public void onStart() {
            super.onStart();
            Log.d(LOGTAG, "onStart(): qindex = " + mQuatrainIndex);
    }


    @Override
    public void onResume() {
        super.onResume();
        Log.d(LOGTAG, "onResume(): qindex = " + mQuatrainIndex);
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(LOGTAG, "onPause(): qindex = " + mQuatrainIndex);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(LOGTAG, "onSaveInstanceState(): qindex = " + mQuatrainIndex);

    }
}
