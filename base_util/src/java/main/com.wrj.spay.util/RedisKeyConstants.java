package com.wrj.spay.util;

public class RedisKeyConstants {

    public static final String REDIS_LOCK_PREFIX_BASE_JOB = "REDIS_LOCK_PREFIX_BASE_JOB";

    public static final String REDIS_LOCK_PREFIX_RETRY_JOB = "REDIS_LOCK_PREFIX_RETRY_JOB";

    public static final String REDIS_LOCK_PREFIX_ACCOUNT_CREATE = "REDIS_LOCK_PREFIX_ACCOUNT_CREATE";

    public static final String REDIS_LOCK_PREFIX_REFUND_FUNDS = "REDIS_LOCK_PREFIX_REFUND_FUNDS";

    public static final String REDIS_LOCK_PREFIX_REFUND_FUNDS_NOTIFY = "REDIS_LOCK_PREFIX_REFUND_FUNDS_NOTIFY";

    public static final String REDIS_LOCK_PREFIX_FILE_OPERATE = "REDIS_LOCK_PREFIX_FILE_OPERATE";

    public static final String REDIS_LOCK_PREFIX_FUND_TRANSACTION_PROCESS = "REDIS_LOCK_PREFIX_FUND_TRANSACTION_PROCESS";

    public static final long REDIS_LOCK_TIME_1_MINUTES = 60 * 1000; //1 minute

    public static final int REDIS_BLOCK_TIME_5_SECONDS = 5 * 1000; //5S

    public static final int REDIS_BLOCK_TIME_3_SECONDS = 3 * 1000; //5S

    public static final int REDIS_BLOCK_TIME_10_SECONDS = 10 * 1000; //10S

    public static final long REDIS_LOCK_TIME_10_MINUTES = 60 * 10 * 1000; //10 minute

    public static final long REDIS_LOCK_TIME_30_MINUTES = 60 * 30 * 1000; //30 minute;

    public static final String REDIS_LOCK_PREFIX_SAVE_REDEEM_APPLY = "REDIS_LOCK_PREFIX_SAVE_REDEEM_APPLY";

    public static final String REDIS_LOCK_PREFIX_PURCHASE_CANCEL = "REDIS_LOCK_PREFIX_PURCHASE_CANCEL";

    public static final String REDIS_LOCK_PREFIX_BY_REDEEM_NO = "REDIS_LOCK_PREFIX_BY_REDEEM_NO";

    public static final String REDIS_LOCK_PREFIX_BY_LUFAX_ACCOUNT = "REDIS_LOCK_PREFIX_BY_LUFAX_ACCOUNT";
}
