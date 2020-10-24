package com.example.demo.common;

import java.util.Collection;
import java.util.Map;

/**
 * @author lyl
 * @version 1.0
 * @date 2020/10/11 17:12
 * @desc
 */
public class ObjectHelper {

    /**
     * 判断对象是否为空
     *
     * @param obj -参数对象
     * @return boolean -true:表示对象为空;false:表示对象为非空 集合： Collection.isEmpty()
     * 数组：判断数组每个元素，所有元素都为空，即判定数组为空
     * 字符串：判断字符串等于"null"，或去除两端""字窜后返回String.isEmpty()的结果 其它类型返回 false
     */
    public static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        }
        if (obj instanceof Map) {
            return ((Map<?, ?>) obj).entrySet().isEmpty();
        }
        if (obj instanceof Collection) {
            return ((Collection<?>) obj).isEmpty();
        }
        if (obj instanceof String) {
            return ("null").equalsIgnoreCase((String) obj) | ((String) obj).trim().isEmpty();
        }
        if (obj instanceof StringBuffer) {
            return ((StringBuffer) obj).length() == 0;
        }
        if (obj.getClass().isArray()) {
            try {
                Object[] a = (Object[]) obj;
                boolean b = true;
                for (Object o : a) {
                    b = b & ObjectHelper.isEmpty(o);
                    if (!b) {
                        break;
                    }
                }
                return b;
            } catch (ClassCastException e) {
            }
        }
        return false;
    }

    /**
     * 判断对象是否为非空
     *
     * @param obj
     * @return
     */
    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }
}
