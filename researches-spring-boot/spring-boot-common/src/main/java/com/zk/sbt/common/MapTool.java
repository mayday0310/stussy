package com.zk.sbt.common;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Supplier;

/**
 * Created by qcon on 2017/3/27.
 */
public class MapTool {

    // ==========================================builder===============================================

    public static <K, V> Builder<K, V> builder() {
        return new Builder<>();
    }

    public static class Builder<K, V> {

        private Map<K, V> map;

        private Supplier<Boolean> whenFlag = () -> true;

        Builder() {
            map = new TreeMap<>();
        }

        Builder(Map<K, V> exist) {
            map = new TreeMap<>(exist);
        }

        public Builder<K ,V> when(Supplier<Boolean> whenFlag) {
            this.whenFlag = whenFlag;
            return this;
        }

        private void resetWhenFlag() {
            this.whenFlag = () -> true;
        }

        public Builder<K ,V> put(K k, V v) {
            if (whenFlag.get()) {
                if (null == k || "".equals(k)) {
                    throw new NullPointerException("key may not be null in Builder");
                }
                map.put(k, v);
            }
            resetWhenFlag();
            return this;
        }

        public Builder<K ,V> put(K k, V v, V dv) {
            if (null == v) {
                put(k, dv);
            } else {
                put(k, v);
            }
            return this;
        }

        public Builder<K ,V> compute(K k, Supplier<V> supplierV) {
            return put(k, supplierV.get());
        }

        public Builder<K ,V> compute(K k, Supplier<V> supplierV, Supplier<V> supplierDV) {
            return put(k, supplierV.get(), supplierDV.get());
        }

        public Map<K, V> buildMap() {
            return map;
        }

    }

    // ============================================= tools =============================================================

    public static Number getNumber(final Map map, final Object key) {
        if (map != null) {
            Object answer = map.get(key);
            if (answer != null) {
                if (answer instanceof Number) {
                    return (Number) answer;

                } else if (answer instanceof String) {
                    try {
                        String text = (String) answer;
                        return NumberFormat.getInstance().parse(text);

                    } catch (ParseException e) {
                        return null;
                    }
                }
            }
        }
        return null;
    }

    public static Long getLong(final Map map, final Object key) {
        Number answer = getNumber(map, key);
        if (answer == null) {
            return null;
        } else if (answer instanceof Long) {
            return (Long) answer;
        }
        return answer.longValue();
    }

    public static Integer getInteger(final Map map, final Object key) {
        Number answer = getNumber(map, key);
        if (answer == null) {
            return null;
        } else if (answer instanceof Integer) {
            return (Integer) answer;
        }
        return answer.intValue();
    }

    public static Double getDouble(final Map map, final Object key) {
        Number answer = getNumber(map, key);
        if (answer == null) {
            return null;
        } else if (answer instanceof Double) {
            return (Double) answer;
        }
        return answer.doubleValue();
    }

    public static String getString(final Map map, final Object key) {
        if (map != null) {
            Object answer = map.get(key);
            if (answer != null) {
                return answer.toString();
            }
        }
        return null;
    }

}
