package org.pen.animusfirst.utilities;

import android.content.Context;
import android.content.res.Configuration;


public class PreRender {

    static int stat = 99;

    public static int ifNight(Context context) {
        int nightModeFlags =
                context.getResources().getConfiguration().uiMode &
                        Configuration.UI_MODE_NIGHT_MASK;
        switch (nightModeFlags) {
            case Configuration.UI_MODE_NIGHT_YES:
                stat = 0;
                break;

            case Configuration.UI_MODE_NIGHT_NO:
                stat = 1;
                break;

            case Configuration.UI_MODE_NIGHT_UNDEFINED:
                stat = 2;
                break;
        }

        return stat;
    }
}
