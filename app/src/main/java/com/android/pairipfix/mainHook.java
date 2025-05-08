package com.android.pairipfix;

import android.os.Bundle;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class mainHook implements IXposedHookLoadPackage {


    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        XposedHelpers.findAndHookMethod(
                "com.pairip.licensecheck.ResponseValidator",
                lpparam.classLoader,
                "validateResponse",
                Bundle.class, String.class,
                XC_MethodReplacement.DO_NOTHING
        );
        XposedHelpers.findAndHookMethod(
                "com.pairip.licensecheck.LicenseClient",
                lpparam.classLoader,
                "processResponse",
                int.class, Bundle.class,
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam params){
                        params.args[0] = 0;
                    }
                }
        );
    }
}
