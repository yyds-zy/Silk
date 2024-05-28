# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
#指定代码的压缩级别
-optimizationpasses 5
#是否使用大小写混合  混淆时不生成大小写混合的类名
-dontusemixedcaseclassnames
#是否混淆第三方jar
-dontskipnonpubliclibraryclasses
#混淆时是否做预校验  不预校验
-dontpreverify
#混淆时是否记录日志  混淆过程中打印详细信息
-verbose

#如果引用了v4或者v7包，可以忽略警告，因为用不到android.support
-dontwarn android.support.**

#不混淆JIN方法
-keepclasseswithmembernames class * {
    native <methods>;
}

#保持Activity子类里面的参数类型为View的方法不被混淆，如被XML里面应用的onClick方法
# We want to keep methods in Activity that could be used in the XML attribute onClick
-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}

#保持R类静态成员不被混淆
-keepclassmembers class **.R$* {
    public static <fields>;
}

##保持枚举类不被混淆
-keepclassmembers enum * {
      public static **[] values();
      public static ** valueOf(java.lang.String);
}

#保持实体类不被混淆
-keep class com.qq.xwvoicesdk.bean.** { *; }

