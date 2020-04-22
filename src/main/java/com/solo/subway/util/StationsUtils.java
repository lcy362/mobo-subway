/**
 * @(#)StationsUtils.java, 4月 22, 2020.
 * <p>
 * Copyright 2020 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.solo.subway.util;

import org.apache.commons.collections4.CollectionUtils;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author licy03
 */
public class StationsUtils {

    /**
     * 输入两个站点的所在线路，判断是否换乘过
     */
    public static boolean transferred(Set<String> currentLine, Set<String> previous, Set<String> next) {
        Set<String> union = new HashSet<>();
        union.addAll(previous);
        union.addAll(next);
        if (union.size() == previous.size() + next.size()) {
            return true;
        }
        Collection<String> intersection = CollectionUtils.intersection(previous, next);
        boolean result = !(CollectionUtils.containsAny(currentLine, intersection));
        return result;
    }
}