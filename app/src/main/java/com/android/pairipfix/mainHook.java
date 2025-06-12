package com.android.pairipfix;

import android.os.Bundle;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class mainHook implements IXposedHookLoadPackage {


    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        try {
            //checks for pairip latest version
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
        } catch(Exception err){
            // checks for pairip older versions
            try{
                XposedHelpers.findAndHookMethod(
                        "com.pairip.licensecheck3.ResponseValidator",
                        lpparam.classLoader,
                        "validateResponse",
                        Bundle.class, String.class,
                        XC_MethodReplacement.DO_NOTHING
                );
                XposedHelpers.findAndHookMethod(
                        "com.pairip.licensecheck3.LicenseClientV3",
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
            } catch(Exception err2){
                XposedBridge.log("could not locate pairip classes, please open an issue on https://github.com/ahmedmani/pairipfix and include your apk");
                XposedBridge.log(err2);
            }

        }
    }
}
