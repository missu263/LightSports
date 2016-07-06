/**
 * Copyright 2014 Zhenguo Jin
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sythealth.library.common.tools;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 通用工具类
 *
 * @author jingle1267@163.com
 */
public class CommonUtil {

    /**
     * 是否有SDCard
     *
     * @return 是否有SDCard
     */
    public static boolean hasSDCard() {

        String status = Environment.getExternalStorageState();
        return status.equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 获取应用运行的最大内存
     *
     * @return 最大内存
     */
    public static long getMaxMemory() {

        return Runtime.getRuntime().maxMemory() / 1024;
    }

    /**
     * 拨打电话
     *
     * @param ctx
     * @param tel
     */
    public static void callTel(Context ctx, String tel) {
        Uri telUri = Uri.parse("tel:" + tel);
        Intent intent = new Intent(Intent.ACTION_DIAL, telUri);
        ctx.startActivity(intent);
    }

    // 验证手机格式
    public static boolean isPhone(String phone) {
        boolean isPhone = true;
        String value = phone.substring(0, 1);
        if (value.equals("1") && phone.length() == 11) {
            isPhone = true;
            // 验证手机的正则表达式
            String str = "^1[0-9]{10}$";
            Pattern pattern = Pattern.compile(str);
            Matcher matcher = pattern.matcher(phone);

            if (matcher.matches()) {
                isPhone = true;
            } else {
                isPhone = false;
            }
        } else {
            isPhone = false;
        }
        return isPhone;
    }

    public static double changeDouble(Double dou) {
        NumberFormat nf = new DecimalFormat("0.0 ");
        dou = Double.parseDouble(nf.format(dou));
        return dou;
    }

    public static boolean isListEmpty(List<?> data) {
        return !(data != null && !data.isEmpty());
    }
}
