package com.wrj.spay.util;


public class JobRetryConstants {
    public static final long _60_MINUTES = 60 * 60; //60分钟

    public static final long _30_MINUTES = 30 * 60; //30分钟

    public static final long _10_MINUTES = 10 * 60; //10分钟

    public static final long _3_MINUTES = 3 * 60; //3分钟

    public static final long _3_SECONDES = 3;   //3s

    public static final long _10_TIMES = 10; //10次

    public static final long _20_TIMES = 20; //20次

    public static final long HIGH_PRIORITY_SYS = 12; //JobRetry系统高等级

    public static final long FILE_PRIORITY_SYS = 10; //文件类jobRetry等级

    public static final long LOWER_PRIORITY_SYS = 11; //JobRetry系统低等级

    public static final long DEFAULT_PRIORITY_SYS = 1; //默认等级

    public static final int JOB_RETRY_THREADS_NUMBER = 6; //重试job的线程数量

    public static final int JOB_RETRY_DEFAULT_THREADS_NUMBER = 1; //默认线程数

    public static final long JOB_RETRY_RECORDS_NUMBER_FOR_FILE = 6; //重试文件job每分钟业务量

    public static final long JOB_RETRY_RECORDS_NUMBER_PER_MINUTE = 1000; //重试job的每分钟业务量

}
