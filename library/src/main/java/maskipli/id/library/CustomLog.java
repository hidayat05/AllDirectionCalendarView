package maskipli.id.library;

import android.util.Log;

public class CustomLog {
    final private static boolean[] DebugOn = {false, true};
    final private static String[] DebugTypeTag = {"ViewGroup", "ViewPageTest"};

    public CustomLog() {
        // TODO Auto-generated constructor stub
    }

    public static void d(int debug_type, String format, Object... args) {
        boolean enableDebug = true;
        if (debug_type <= DebugOn.length) {
            enableDebug = DebugOn[debug_type];
        }
        if (enableDebug)
            Log.d(DebugTypeTag[debug_type], String.format(format, args));
    }
}
