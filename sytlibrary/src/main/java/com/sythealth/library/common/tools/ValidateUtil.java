package com.sythealth.library.common.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateUtil {
	
	/**
	 * 验证手机号码
	 * @param mobile
	 * @return
	 */
	public static boolean isMobile(String mobile){
		Pattern p = Pattern.compile("1[0-9]{10}");
        Matcher m = p.matcher(mobile);
        return m.matches();
	}
	
}
