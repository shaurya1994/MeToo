package com.example.shauryatrivedi.metoo.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.shauryatrivedi.metoo.Adapters.TweetRvAdapter;
import com.example.shauryatrivedi.metoo.Interface.ApiInterface;
import com.example.shauryatrivedi.metoo.R;
import com.example.shauryatrivedi.metoo.Retrofit.ApiClient;
import com.example.shauryatrivedi.metoo.Retrofit.MainPojo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.support.constraint.Constraints.TAG;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MeTooIn.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MeTooIn#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MeTooIn extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private SwipeRefreshLayout refreshLayout;
    private List<com.example.shauryatrivedi.metoo.Retrofit.data> data;
    ListView tweets1;
    String page1="1";
    ProgressDialog pDialog;
    TweetRvAdapter obj;
    private OnFragmentInteractionListener mListener;

    public MeTooIn() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MeTooIn.
     */
    // TODO: Rename and change types and number of parameters
    public static MeTooIn newInstance(String param1, String param2) {
        MeTooIn fragment = new MeTooIn();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_me_too_in, container, false);
        tweets1 = (ListView)view.findViewById(R.id.frag_meTooIn);
        Button loadMore = new Button(getActivity());
        loadMore.setText("Load More");
        loadMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new loadMoreListView().execute();
            }
        });
        tweets1.addFooterView(loadMore);
        Getfeed();
        return view;
    }

    private class loadMoreListView extends AsyncTask<Void, Void, List<com.example.shauryatrivedi.metoo.Retrofit.data>> {
        @Override
        protected void onPreExecute() {
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Please wait..");
            pDialog.setIndeterminate(true);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        protected List<com.example.shauryatrivedi.metoo.Retrofit.data> doInBackground(Void... unused) {
            getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    // increment current page
                    page1+= 1;

                    // Next page request
                    ApiInterface api= ApiClient.getClient().create(ApiInterface.class);
                    Call<MainPojo> calll=api.get_dat("MeTooIndia",page1);

                    calll.enqueue(new Callback<MainPojo>() {
                        @Override
                        public void onResponse(Call<MainPojo> call, Response<MainPojo> response) {
                            MainPojo pojo = response.body();
                            data = pojo.getData();
                            tweets1.setAdapter(new TweetRvAdapter(getActivity(), data));
                        }

                        @Override
                        public void onFailure(Call<MainPojo> call, Throwable t) {
                            Log.e(TAG, t.toString());
                            Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                    // get listview current position - used to maintain scroll position
                    int currentPosition = tweets1.getFirstVisiblePosition();

                    // Appending new data to menuItems ArrayList
                    obj = new TweetRvAdapter(getActivity(), data);

                    // Setting new scroll position
                    tweets1.setSelectionFromTop(currentPosition + 1, 0);
                }
            });
            return (data);
        }

        protected void onPostExecute(List<com.example.shauryatrivedi.metoo.Retrofit.data> unused) {
            // closing progress dialog
            pDialog.dismiss();
        }
    }

    private void Getfeed()
    {
        ApiInterface api= ApiClient.getClient().create(ApiInterface.class);
        Call<MainPojo> calll=api.get_dat("MeTooIndia",page1);

        calll.enqueue(new Callback<MainPojo>() {
            @Override
            public void onResponse(Call<MainPojo> call, Response<MainPojo> response) {
                MainPojo pojo = response.body();
                data = pojo.getData();
                tweets1.setAdapter(new TweetRvAdapter(getActivity(), data));
            }

            @Override
            public void onFailure(Call<MainPojo> call, Throwable t) {
                Log.e(TAG, t.toString());
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
