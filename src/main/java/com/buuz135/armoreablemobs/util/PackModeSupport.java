package com.buuz135.armoreablemobs.util;

import io.sommers.packmode.api.PackModeAPI;

public class PackModeSupport {

    public static boolean isPackModeEnabled(String packModeCheck) {
        return PackModeAPI.getInstance().getCurrentPackMode().equalsIgnoreCase(packModeCheck);
    }
}
