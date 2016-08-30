package com.wrj.spay.util;

import com.lufax.utility.digit.NumberUtils;

import java.math.BigDecimal;

/**
 * User: weigongjiu779
 * =====================================
 * Date: 2016/3/28
 * =====================================
 * Discription:
 */
public class BigDecimalUtils extends NumberUtils {

    public static BigDecimal exchangeLong(Long args) {
        return new BigDecimal(args);
    }

    public static boolean isEmpty(BigDecimal value) {
        if (value == null) return true;
        return false;
    }

    public static boolean isGreaterThan(BigDecimal value1, BigDecimal value2) {
        return value1.compareTo(value2) > 0;
    }
}
