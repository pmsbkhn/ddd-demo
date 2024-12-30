package vn.softwaredesign.ddd.common.lang;

public class StringUtils {
    public static boolean isBlank(String str) {
        return str == null || str.trim().isEmpty();
    }
}
