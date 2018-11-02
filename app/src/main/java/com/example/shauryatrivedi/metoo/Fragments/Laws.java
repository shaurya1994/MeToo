package com.example.shauryatrivedi.metoo.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.shauryatrivedi.metoo.Activities.LawActivity;
import com.example.shauryatrivedi.metoo.Adapters.LawRvAdapter;
import com.example.shauryatrivedi.metoo.Interface.ApiInterface;
import com.example.shauryatrivedi.metoo.R;
import com.example.shauryatrivedi.metoo.Retrofit.ApiClient;
import com.example.shauryatrivedi.metoo.Retrofit.TweetList;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Laws.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Laws#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Laws extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private String laws, id;
    private RecyclerView recyclerView;
    private ProgressDialog pDialogue;

    List<TweetList> lawList;

    private LawRvAdapter lawRvAdapter;
    private String TAG = LawActivity.class.getSimpleName();

    private OnFragmentInteractionListener mListener;

    public Laws() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Laws.
     */
    // TODO: Rename and change types and number of parameters
    public static Laws newInstance(String param1, String param2) {
        Laws fragment = new Laws();
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

        View view = inflater.inflate(R.layout.fragment_laws, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.fragLawRV);
        recyclerView.setHasFixedSize(true);

        final ApiInterface api = ApiClient.getClient().create(ApiInterface.class);

        Call<List<TweetList>> call = api.get_tweet("1","http:\\/\\/mapi.trycatchtech.com\\/uploads\\/twitter\\/15397696630.jpg");

        call.enqueue(new Callback<List<TweetList>>() {
            @Override
            public void onResponse(Call<List<TweetList>> call, Response<List<TweetList>> response) {
                if (pDialogue.isShowing())
                    pDialogue.dismiss();

                List<TweetList> lawList=response.body();
                if(lawList!=null)
                {
                    Log.d(TAG,"Number of images recieved: "+ lawList.size());
                }

                lawRvAdapter=new LawRvAdapter(getContext(),lawList);
                LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(lawRvAdapter);
                lawRvAdapter.notifyDataSetChanged();
                Toast.makeText(getActivity(), "IN RETROFIT FRAGMENT" , Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<List<TweetList>> call, Throwable t) {
                Log.e(TAG, t.toString());
                Toast.makeText(getActivity(),"NOT IN RETROFIT ACTIVITY",Toast.LENGTH_LONG).show();

            }
        });

        showProgDiag();

        return view;
    }

    private void showProgDiag() {
        pDialogue = new ProgressDialog(getActivity());
        pDialogue.setMessage("Loading photos");
        pDialogue.setCancelable(true);
        pDialogue.show();
    }
    public static boolean isNetworkAvailable(Context context ){
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnected();
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
