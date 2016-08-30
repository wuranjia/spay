package com.wrj.spay.util;


import com.wrj.spay.exception.BizRuntimeException;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;

/**
 * 集合工具类
 * @param <T>
 */
public class ListUtil<T> {

    public static boolean isEmpty(List list) {
        if(CollectionUtils.isEmpty(list)) return true;
        return false;
    }

    public static boolean isNotEmpty(List list) {
        if(CollectionUtils.isNotEmpty(list)) return true;
        return false;
    }

    public T getUniqueRecord(List<T> list) {
        if (isNotEmpty(list)) {
            return list.get(0);
        }
        else {
            return null;
        }
    }

    public T getUniqueRecordWithException(List<T> list) {

        if (CollectionUtils.isNotEmpty(list)){
            if (list.size() > 1) {
                throw new BizRuntimeException(ListUtil.class, "error in getUniqueRecord, size=[%s], list=[%s]" , list.size(), list);
            } else if(list.size() == 1) {
                return list.get(0);
            }
        }
        throw new BizRuntimeException(ListUtil.class, "error in getUniqueRecord, size=[%s], list=[%s]" , list.size(), list);
    }

}
