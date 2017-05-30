package edu.rit.se.milk.demoapp06;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link WeatherFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link WeatherFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WeatherFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private TextView txtHigh,txtLow;
    FrameLayout frameLayout;
    private ImageView weatherIcon;


    public WeatherFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WeatherFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WeatherFragment newInstance(String param1, String param2) {
        WeatherFragment fragment = new WeatherFragment();
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
        View view = inflater.inflate(R.layout.fragment_weather, container, false);
        txtHigh = (TextView) view.findViewById(R.id.textViewHigh);
        txtLow = (TextView) view.findViewById(R.id.textViewLow);
        frameLayout = (FrameLayout) view.findViewById(R.id.frameLayout_fragment);
        weatherIcon = (ImageView) view.findViewById(R.id.imageView);

        return view;
    }

    public void generateRandomWeatherForecast(String day){
        Random rand = new Random();
        int weather = rand.nextInt(4) + 1;
        int high = rand.nextInt(80 + 1 - 50) + 50;
        int low = rand.nextInt(high + 1 - 20) + 20;

        txtHigh.setText("High Temperature: "+high+"F");
        txtLow.setText("Low Temperature: "+low+"F");

        Resources res = getContext().getResources();
        Drawable img;
        String text;
        switch (weather){
            case 1:
                img = res.getDrawable(R.drawable.weather_icon_01);
                text = "sunny with a few thunderstorms";
                break;
            case 2:
                img = res.getDrawable(R.drawable.weather_icon_02);
                text = "sunny with a few clouds";
                break;
            case 3:
                img = res.getDrawable(R.drawable.weather_icon_03);
                text = "a bright and sunny day";
                break;
            default:
                img = res.getDrawable(R.drawable.weather_icon_04);
                text = "thunderstorms throughout the day";
                break;
        }

        day = day.equalsIgnoreCase("today")?"Today's":"Tomorrow's";

        String accessibilityText = day +
                " weather is set to be  " + text +
                "with a high of " + high +
                "and a low of " + low;
        frameLayout.setContentDescription(accessibilityText);
        weatherIcon.setImageDrawable(img);
    }

    public void setFragmentVisibility(boolean isVisible){
        if(isVisible){
            frameLayout.setBackgroundColor(Color.TRANSPARENT);
            weatherIcon.setVisibility(View.VISIBLE);
        }
        else{
            frameLayout.setBackgroundColor(Color.BLACK);
            weatherIcon.setVisibility(View.INVISIBLE);
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
