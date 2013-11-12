package com.example.assignment7;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class QuatrainNumberListFragment extends ListFragment {
        static final String LOGTAG = QuatrainNumberListFragment.class.getSimpleName() + "_TAG";

    MainActivity mHostAct = null;
    int mQuatrainSelection = 0;

    @Override
    public void onInflate(Activity act, AttributeSet attrs, Bundle icicle) {
        super.onInflate(act, attrs, icicle);

        Log.d(LOGTAG, "onInflate(): AttributeSet:");
        for(int i=0; i < attrs.getAttributeCount(); i++) {
        Log.d(LOGTAG, "" + attrs.getAttributeName(i) + " = " + attrs.getAttributeValue(i));
        }
    }

    @Override
    public void onAttach(Activity act) {
            super.onAttach(act);

            Log.d(LOGTAG, "onAttach(): host activity == " + act);
            mHostAct = (MainActivity) act;
    }

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        Log.d(LOGTAG, "onCreate()");
        if(icicle != null) {
                Log.d(LOGTAG, "Bundle contents:");
        for(String key : icicle.keySet()) {
            Log.d(LOGTAG, " " + key);
        }
        mQuatrainSelection =
                        icicle.getInt(MainActivity
                                                        .mRes
                                                        .getString(R.string.quat_selection_key),
                                                        0);
        }
        else {
        Log.d(LOGTAG, "onCreate():  Bundle is null");
        }
    }

    @Override
    public View onCreateView(LayoutInflater myInflater, ViewGroup container, Bundle icicle) {
            Log.d(LOGTAG, "ViewGroup == " + container);
            return super.onCreateView(myInflater, container, icicle);
    }

    @Override
    public void onActivityCreated(Bundle icicle) {
        super.onActivityCreated(icicle);

        Log.d(LOGTAG, "onActivityCreated()");
        if(icicle != null) {
                Log.d(LOGTAG, "Bundle's Keys:");
        for(String key : icicle.keySet()) {
            Log.d(LOGTAG, "" + key);
        }
        }
        else {
        Log.d(LOGTAG, "Bundle is null");
        }


        ArrayAdapter<CharSequence> adptr = ArrayAdapter.createFromResource(getActivity(),
                R.array.quatrain_numbers,
                android.R.layout.simple_list_item_1);
        this.setListAdapter(adptr);

        ListView lv = this.getListView();
        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lv.setSelection(mQuatrainSelection);

        mHostAct.displayQuatrainText(mQuatrainSelection);
    }

    @Override
    public void onStart() {
            Log.d(LOGTAG, "onStart(): qselect = " + mQuatrainSelection);
            super.onStart();
    }

    @Override
    public void onResume() {
            Log.d(LOGTAG, "onResume(): qselect = " + mQuatrainSelection);
            super.onResume();
    }

    @Override
    public void onPause() {
            Log.d(LOGTAG, "onPause(): qselect = " + mQuatrainSelection);
            super.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle icicle) {
            Log.d(LOGTAG, "onSaveInstanceState(): qselect = " + mQuatrainSelection);
        super.onSaveInstanceState(icicle);
        // this is how we persist the selected position
        icicle.putInt(MainActivity.mRes.getString(R.string.quat_selection_key), mQuatrainSelection);
    }

    @Override
    public void onListItemClick(ListView l, View v, int pos, long id) {
            Log.d(LOGTAG, "onListItemClick(): pos = " + pos);
            mQuatrainSelection = pos;
            mHostAct.displayQuatrainText(pos);
    }

    @Override
    public void onStop() {
            Log.d(LOGTAG, "onStop(): qselect = " + mQuatrainSelection);
            super.onStop();
    }

    @Override
    public void onDestroyView() {
            Log.d(LOGTAG, "onDestroyView(): qselect = " + mQuatrainSelection);
            super.onDestroyView();
    }

    @Override
    public void onDestroy() {
            Log.d(LOGTAG, "onDestroy(): qselect = " + mQuatrainSelection);
            super.onDestroy();
    }

    @Override
    public void onDetach() {
            Log.d(LOGTAG, "onDetach(): qselect = " + mQuatrainSelection);
            super.onDetach();
            mHostAct = null;
    }
}
