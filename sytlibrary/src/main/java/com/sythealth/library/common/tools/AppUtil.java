package com.sythealth.library.common.tools;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.telephony.TelephonyManager;

public class AppUtil {

    /**
     * 获取软件自身版本号
     * 
     * @return 当前应用的版本号
     */
    public static int getselfVersionCode(Context context) {
        String packname = context.getPackageName();
        return getappVersionCode(context, packname);
    }

    /**
     * 获取软件自身版本名
     * 
     * @return 当前应用的版本号
     */
    public static String getselfVersionName(Context context) {
        String packname = context.getPackageName();
        return getappVersionName(context, packname);
    }

    private static int getappVersionCode(Context context, String packageName) {
        try {
            return context.getPackageManager().getPackageInfo(packageName, 0).versionCode;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private static String getappVersionName(Context context, String packageName) {
        try {
            return context.getPackageManager().getPackageInfo(packageName, 0).versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取APP崩溃异常报告
     * 
     * @param ex
     * @return
     */
    public static String getCrashReport(Context context, Throwable ex) {
        // PackageInfo pinfo = getPackageInfo(context);
        StringBuffer exceptionStr = new StringBuffer();
        // exceptionStr.append("version: " + pinfo.versionName + "(" +
        // pinfo.versionCode + ")\n");
        // exceptionStr.append("android:" + android.os.Build.VERSION.RELEASE +
        // "("
        // + android.os.Build.MODEL + ")\n");
        exceptionStr.append("Exception:" + ex.getMessage() + "\n");
        StackTraceElement[] elements = ex.getStackTrace();
        for (int i = 0; i < elements.length; i++) {
            exceptionStr.append(elements[i].toString() + "\n");
        }
        return exceptionStr.toString();
    }

    /**
     * 获取App安装包信息
     * 
     * @return
     */
    public static PackageInfo getPackageInfo(Context context) {
        PackageInfo info = null;
        try {
            info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (NameNotFoundException e) {
        }
        if (info == null)
            info = new PackageInfo();
        return info;
    }

    /**
     * 提出APP自己结束进程 ）
     * 
     * @return
     */
    public static void exit() {
        killProcess(android.os.Process.myPid());
    }

    /**
     * 根据进程id结束进程
     * 
     * @param Processid
     */
    public static void killProcess(int Processid) {
        android.os.Process.killProcess(Processid);
    }

    /**
     * 获取Meta值
     * 
     * @param context
     * @param metaKey
     * @return
     */
    public static String getMetaValue(Context context, String metaKey) {
        Bundle metaData = null;
        String apiKey = null;
        if (context == null || metaKey == null) {
            return null;
        }
        try {
            ApplicationInfo ai = context.getPackageManager().getApplicationInfo(context.getPackageName(),
                    PackageManager.GET_META_DATA);
            if (null != ai) {
                metaData = ai.metaData;
            }
            if (null != metaData) {
                apiKey = metaData.getString(metaKey);
            }
        } catch (NameNotFoundException e) {

        }
        return apiKey;
    }

    public static String getImei(Context context) {
        // 权限：android:name="android.permission.READ_PHONE_STATE" />
        return ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
    }
}
