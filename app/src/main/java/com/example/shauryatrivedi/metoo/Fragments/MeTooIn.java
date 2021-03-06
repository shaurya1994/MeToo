package com.example.shauryatrivedi.metoo.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
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
import static com.example.shauryatrivedi.metoo.Fragments.Laws.isNetworkAvailable;

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

    private data data;
    private List<JSON> json = new ArrayList<>();
    private ListView tweets1;
    String page = "1";
    int temp = Integer.parseInt(page);
    ProgressDialog proDlog;

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
                 loadMoreListView();
            }
        });
        tweets1.addFooterView(loadMore);

        if(isNetworkAvailable(getContext())){
            showProgDiag();
            Getfeed();
            Snackbar snackbar = Snackbar.make(view, "Connected", Snackbar.LENGTH_SHORT);
            View sbView = snackbar.getView();
            sbView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
            TextView tv = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
            tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            snackbar.show();}
        else {
            Snackbar snackbar = Snackbar.make(view, "Not Connected", Snackbar.LENGTH_SHORT);
            View sbView = snackbar.getView();
            sbView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
            TextView tv = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
            tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            snackbar.show();
        }
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
        Call<data> call=api.get_data("MeToo",page);

        call.enqueue(new Callback<data>() {
            @Override
            public void onResponse(Call<data> call, Response<data> response) {
                data = response.body();
                json = data.getData();

                tweets1.setAdapter(new TweetRvAdapter(getActivity(), json)); }

            @Override
            public void onFailure(Call<data> call, Throwable t) {
                Log.e(TAG, t.toString());
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadMoreListView() {

        showProgDiag();
        // Increment current page
        temp = temp+1;
        page = Integer.toString(temp);
        if (proDlog.isShowing())
            proDlog.dismiss();

        if (temp<33) {
            // Next page request
           Getfeed();
        }else {
            Toast.makeText(getContext(),"No More Posts",Toast.LENGTH_SHORT).show();
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
