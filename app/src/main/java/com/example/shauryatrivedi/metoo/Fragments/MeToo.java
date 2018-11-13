package com.example.shauryatrivedi.metoo.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.shauryatrivedi.metoo.Activities.MainActivity;
import com.example.shauryatrivedi.metoo.Activities.Twitter;
import com.example.shauryatrivedi.metoo.Adapters.TweetRvAdapter;
import com.example.shauryatrivedi.metoo.Interface.ApiInterface;
import com.example.shauryatrivedi.metoo.R;
import com.example.shauryatrivedi.metoo.Retrofit.ApiClient;
import com.example.shauryatrivedi.metoo.Retrofit.MainPojo;
import com.example.shauryatrivedi.metoo.Retrofit.data;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.support.constraint.Constraints.TAG;
import static com.example.shauryatrivedi.metoo.R.layout.footer_view;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MeTooIn.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MeTooIn#newInstance} factory method to
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

    private SwipeRefreshLayout refreshLayout;
    private List<com.example.shauryatrivedi.metoo.Retrofit.data> data;
    private ListView tweets1;
    private TweetRvAdapter obj;
    String page1="1";
    public Handler mhandler;
    public View ftview;
    public boolean isLoading = false;

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
        LayoutInflater li = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ftview = li.inflate(R.layout.footer_view, null);
        mhandler = new Handler();
        data = new ArrayList<>();
        Getfeed();
        return view;
    }

//    public class MyHandler extends Handler{
//        @Override
//        public void handleMessage(Message msg) {
//            switch (msg.what){
//                case 0:
//                    //Add loading view during search processing
//                    tweets1.addFooterView(ftview);
//                    break;
//                case 1:
//                    //Update data adapter and UI
//                    obj.addListItemToAdapter((ArrayList<data>)msg.obj);
//                    //Remove loading view after update listView
//                    tweets1.removeFooterView(ftview);
//                    isLoading = false;
//                    break;
//                    default:
//                        break;
//            }
//        }
//    }

//    private ArrayList<data> getMoreData(){
//        ArrayList<data>lst = new ArrayList<>();
//        //Add loop for incrementing api
//        int value=Integer.parseInt(page1)+1;
//        page1=Integer.toString(value);
//        return lst;
//    }

//    public class ThreadGetMoreData extends Thread{
//        @Override
//        public void run() {
//            //Add footer view after get data
//            mhandler.sendEmptyMessage(0);
//            //Search more data
//            ArrayList<data>lstResult = getMoreData();
//            //Delay Line
//            try {
//                Thread.sleep(3000);
//            }catch (InterruptedException e){
//                e.printStackTrace();
//            }
//            //Send result to handle
//            Message msg = mhandler.obtainMessage(1,lstResult);
//            mhandler.sendMessage(msg);
//
//        }
//    }

    private void Getfeed()
    {
        ApiInterface api= ApiClient.getClient().create(ApiInterface.class);
        Call<MainPojo> calll=api.get_dat("MeToo",page1);

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
