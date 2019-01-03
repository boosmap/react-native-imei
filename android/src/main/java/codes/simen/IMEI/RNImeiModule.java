package codes.simen.IMEI;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.content.pm.PackageManager;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;

import java.util.HashMap;
import java.util.Map;

public class RNImeiModule extends ReactContextBaseJavaModule {

    ReactApplicationContext reactContext;

    public RNImeiModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "IMEI";
    }

    @Override
    public Map<String, Object> getConstants() {
        final Map<String, Object> constants = new HashMap<>();

        String IMEINumber = "444444444444444";
        String permission = "android.permission.READ_PHONE_STATE";
        int res = this.reactContext.checkCallingOrSelfPermission(permission);

        if (res == PackageManager.PERMISSION_GRANTED) {
            try {
                TelephonyManager tm = (TelephonyManager) this.reactContext.getSystemService(Context.TELEPHONY_SERVICE);
                if (android.os.Build.VERSION.SDK_INT >= 26) {
                    IMEINumber = tm.getImei().trim();
                } else {
                    IMEINumber= tm.getDeviceId();
                }
            }
            catch (Exception e) {
                IMEINumber = "555555555555555";
                e.printStackTrace();
            }
        }

        constants.put("imei", IMEINumber);

        return constants;
    }

}
