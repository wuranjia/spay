package com.wrj.spay.exception;

import com.lufax.jersey.utils.Logger;

/**
 * RuntimeException
 */
public class BizRuntimeException extends RuntimeException {

    public BizRuntimeException(String msg) {
        super(msg);
    }

    public BizRuntimeException(String msg, Throwable t) {
        super(msg, t);
    }

    public BizRuntimeException(Class clazz, String msg, Object ... args) {
        super(msg);
        Logger.error(clazz, String.format(msg, args));
    }

    public BizRuntimeException(Class clazz, Throwable t, String msg, Object... args) {
        super(msg, t);
        Logger.error(clazz, String.format(msg, args));
    }
}
