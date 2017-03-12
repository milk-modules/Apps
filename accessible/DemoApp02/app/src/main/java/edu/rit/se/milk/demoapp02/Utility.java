package edu.rit.se.milk.demoapp02;

import android.view.View;
import java.util.Random;


class Utility {
    static int GenerateRandomInteger(int Min, int Max)
    {
        Random random = new Random();
        return  random.nextInt(Max - Min +1) + Min;
    }

    static float ConvertToDP(View view, int value)
    {
        return value * view.getResources().getDisplayMetrics().density;
    }
}
