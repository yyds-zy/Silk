package com.tencent.xwappsdk.silk;

public final class Silk {

    static {
        System.loadLibrary("silk");
    }

    private static native void nativeInit();

    private static native void nativeUninit();

    private static native byte[] nativeEncode(byte[] data, int length);

    public static void init() {
        nativeInit();
    }

    public static void unInit() {
        nativeUninit();
    }

    public static byte[] encode(byte[] data) {
        return nativeEncode(data, data.length);
    }
}
