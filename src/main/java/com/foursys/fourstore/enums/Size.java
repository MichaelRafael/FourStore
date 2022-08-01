package com.foursys.fourstore.enums;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public enum Size {
    PP,
    P,
    M,
    G,
    GG;

    private static final Map<String, Size> sizeMap = new HashMap<>();

    static {
        for(Size size : Size.values()) {
            sizeMap.put(size.toString(), size);
        }
    }

    public static Optional<Size> getFromString(String size) {
        if(!sizeMap.containsKey(size)) return Optional.empty();
        return Optional.of(sizeMap.get(size));
    }
}
