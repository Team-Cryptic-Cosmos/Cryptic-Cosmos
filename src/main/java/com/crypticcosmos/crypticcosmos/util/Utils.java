package com.crypticcosmos.crypticcosmos.util;

public class Utils {
    public static float clamp(float value, float min, float max) {
        return Math.max(min, Math.min(max, value));
    }
}
