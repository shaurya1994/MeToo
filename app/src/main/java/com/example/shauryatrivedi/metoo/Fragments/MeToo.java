package com.example.shauryatrivedi.metoo.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
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
import com.example.shauryatrivedi.metoo.Retrofit.JSON;
import com.example.shauryatrivedi.metoo.Retrofit.data;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.support.constraint.Constraints.TAG;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MeToo.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MeToo#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MeToo extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private data data;
    private List<JSON> json;
    private ListView tweets1;
    private TweetRvAdapter tweetRvAdapter;
    private String page = "1";
    private int temp = Integer.parseInt(page);
    private ProgressDialog proDlog;
    private Button loadPrev, loadMore ;
    private OnFragmentInteractionListener mListener;

    public MeToo() {
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
        View view = inflater.inflate(R.layout.fragment_me_too, container, false);
        tweets1 = (ListView)view.findViewById(R.id.frag_meToo);

        loadMore = new Button(getActivity());
        loadMore.setText("Load More Posts");
        loadMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadMoreListView();
            }
        });
        tweets1.addFooterView(loadMore);

        showProgDiag();
        Getfeed();
        return view;
    }

    private void showProgDiag(){
        proDlog = new ProgressDialog(getContext());
        proDlog.setMessage("Please wait..");
        proDlog.setCancelable(true);
        proDlog.show();
    }

    private void Getfeed()
    {
        if (proDlog.isShowing()){
            proDlog.dismiss();}

        ApiInterface api= ApiClient.getClient().create(ApiInterface.class);
        Call<data> call=api.get_data("MeTooIndia",page);

        call.enqueue(new Callback<data>() {
            @Override
            public void onResponse(Call<data> call, Response<data> response) {
                data = response.body();
                json = data.getData();

                tweetRvAdapter = new TweetRvAdapter(getActivity(), json);
                tweets1.setAdapter(tweetRvAdapter);

                tweets1.setAdapter(new TweetRvAdapter(getActivity(), json));
                tweets1.deferNotifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<data> call, Throwable t) {
                Log.e(TAG, t.toString());
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadPrevPost() {

        showProgDiag();
        // increment current page
        temp -= 1;
        page = Integer.toString(temp);

        if (proDlog.isShowing())
            proDlog.dismiss();

        // Next page request
        if (temp==1) {
//            tweets1.removeHeaderView(loadPrev);
//            loadPrev.setVisibility(View.INVISIBLE);
            Toast.makeText(getContext(),"No Previous Posts",Toast.LENGTH_SHORT).show();
        }else {
            Getfeed();
        }
    }

    private void loadMoreListView() {

        showProgDiag();
        // increment current page
        temp += 1;
        page = Integer.toString(temp);

        if (proDlog.isShowing())
            proDlog.dismiss();

        // Next page request
        if (temp<7) {
            Getfeed();
        }else {
            Toast.makeText(getContext(),"No More Posts",Toast.LENGTH_SHORT).show();
        }

        if (temp > 1){
            loadPrev = new Button(getActivity());
            loadPrev.setText("Load Previous Posts");
            loadPrev.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loadPrevPost();
                }
            });
            tweets1.addHeaderView(loadPrev);
        }
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
