package com.wrj.spay.util;

/**
 * 系统常量
 */
public class Constants {

    public static final String OK = "0000";

    public static final String SUCCESS = "success";

    public static final String FAIL = "fail";

    public static final String SCHEDULER_APP_RETURN = "PENDING";

    public static final int JOB_PURCHASE_THREADS_NUMBER = 6;

    public static final int JOB_REDEEM_THREADS_NUMBER = 6;

    public static final int JOB_REDEEM_FUNDS_THREADS_NUMBER = 6;

    public static final int MAX_PURCHASE_RECORDS_PER_MINUTE = 300;

    public static final int MAX_REDEEM_FUNDS_RECORDS_PER_MINUTE = 300;

    public static final int MAX_REDEEM_RECORDS_PER_MINUTE = 300;

    public static final long PURCHASE_PROCESS_TIME = 1000 * 60 * 60 * 24 * 1; //申购批处理使用，捞取过去一天的未能处理的订单。

    public static final long REDEEM_PROCESS_TIME = 1000 * 60 * 60 * 24 * 1; //申购批处理使用，捞取过去一天的未能处理的订单。

    public static final long REDEEM_FUNDS_PROCESS_TIME = 1000 * 60 * 60 * 24 * 1; //申购批处理使用，捞取过去一天的未能处理的订单。

    public static final long PURCHASE_PROTECT_TIME = 1000 * 60 * 60;//申购批处理使用，供重试使用，捞取超过60分钟的已经处理过的订单，以免对方卡单。

    public static final int MAX_RECORDS_PER_BATCH = 10000; //申购单次处理数据的数据量

    public static final long LOWER_MOER_THREAD_NUM = 20; //开始使用多线程处理的数据量

    public static final String YEB_APP_MOF = "MOF";

    public static final String QUERY_PROCITY_LIST_CACHEKEY = "ConfigService_queryProCityListV2";

    public static final int QUERY_PROCITY_LIST_CACHETIME = 10 * 60;

    public static final Long MAX_ROW_NUM = 500L;
}
