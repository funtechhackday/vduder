package com.example.vduder.vduder.Core;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by torte on 14.10.2017.
 */

public class IdGenerator {

    public static String GenerateId()
    {
        Date time = Calendar.getInstance().getTime();
        return "" + time.getTime();
    }

}

