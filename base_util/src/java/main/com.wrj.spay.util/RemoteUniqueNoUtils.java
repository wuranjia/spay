package com.wrj.spay.util;

import java.security.SecureRandom;

/**
 * @author liuzhi105
 * @date 2015-12-25 19-25
 */
public class RemoteUniqueNoUtils {

    public static Long getLongInstructionNo(Long userId){
        String userIdStr = String.format("%d",userId%1000000000);
        int length = 10 - userIdStr.length();
        String suffix = length > 0 ? String.format("%0"+length+"d",0) : "";
        String numberStr = userIdStr + suffix + String.format("%06d",Math.abs(new SecureRandom().nextLong()%100000));
        return Long.parseLong(numberStr);
    }

}
