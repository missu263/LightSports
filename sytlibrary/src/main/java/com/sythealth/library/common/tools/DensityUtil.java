package com.sythealth.library.common.tools;

import java.util.UUID;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;

/**
 * @author supernan 屏幕适配工具
 */
public class DensityUtil {
    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static float px2dip(Context context, float pxValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return pxValue / scale + 0.5f;
    }

    /**
     * 返回屏幕宽度
     *
     * @param context
     * @return
     */
    public static int getWindowWidth(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    /**
     * 返回屏幕高度
     *
     * @param context
     * @return
     */
    public static int getWindowHeight(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }

    /**
     * 获得设备ID
     *
     * @return
     */
    public static String getSid(Context ctx) {
        TelephonyManager manager = (TelephonyManager) ctx.getSystemService(Context.TELEPHONY_SERVICE);
        String sid = manager.getDeviceId();
        if (StringUtils.isEmpty(sid)) {
            WifiManager wifiManger = (WifiManager) ctx.getSystemService(Context.WIFI_SERVICE);
            String wifiStr = wifiManger.getConnectionInfo().getMacAddress();
            if (StringUtils.isEmpty(wifiStr)) {
                sid = UUID.randomUUID().toString();
            } else {
                sid = wifiStr.replace(":", "-");
            }
        }
        return sid;
    }
}
