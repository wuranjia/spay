package com.wrj.spay.util;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class MapUtils {

    public static Map buildKeyValueMap(Object... conditions) {
        Map conditionMap = new HashMap();
        for (int i = 0; i < conditions.length; i += 2) {
            conditionMap.put(conditions[i], conditions[i + 1]);
        }
        return conditionMap;
    }

    public static Map<String, Object> buildKeyValueMapWithGenerics(Object... conditions) {
        Map<String, Object> conditionMap = new HashMap<String, Object>();
        for (int i = 0; i < conditions.length; i += 2) {
            if (conditions[i] != null) {
                conditionMap.put(conditions[i].toString(), conditions[i + 1]);
            }
        }
        return conditionMap;
    }

    public static Map<String, String> convertFromString(String mapAsString) {
        Map<String, String> map = new HashMap<String, String>();

        String actualInput = mapAsString.substring(1, mapAsString.length() - 1);

        String[] keyValuePairs = actualInput.split(",");
        for (String keyValuePair : keyValuePairs) {
            String[] keyValue = keyValuePair.split("=");
            map.put(keyValue[0].trim(), keyValue.length > 1 ? keyValue[1].trim() : "");
        }

        return map;
    }

    /**
     * @param map
     * @param key
     * @param defaultValue
     * @return
     */
    public static Object getValue(Map map,String key,Object defaultValue){
        if(map==null || StringUtils.isBlank(key))
            return null;
        Object obj = map.get(key);
        return obj==null?defaultValue:obj;
    }

    public static Map toMap(Object obj)throws Exception{
        Class type = obj.getClass();
        Map returnMap = new HashMap();
        BeanInfo beanInfo = Introspector.getBeanInfo(type);

        PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors();
        for (int i = 0; i< propertyDescriptors.length; i++) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();
            if (!propertyName.equals("class")) {
                Method readMethod = descriptor.getReadMethod();
                Object result = readMethod.invoke(obj, new Object[0]);
                if (result != null) {
                    returnMap.put(propertyName, result);
                }
                /*else {
                    returnMap.put(propertyName, "");
                }*/
            }
        }
        return returnMap;
    }

    /**
     * 把gson格式数据转换成Map格式
     * @param _value
     * @return
     */
    public static Map<String, String> gsonToMap(String _value) {

        Map<String, String> retMap = new Gson().fromJson(_value,
                new TypeToken<Map<String, String>>() {
                }.getType());

        return retMap;
    }

    /**
     * 检查map是否为空
     * @param map
     * @return
     */
    public static boolean isEmpty(Map map) {
        if (map == null || map.keySet().size() == 0) return true;

        return false;
    }
}
