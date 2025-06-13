# Regole ProGuard per ottimizzare un modulo Xposed
-dontobfuscate
-keepattributes *Annotation*
-keep class de.robv.android.xposed.** { *; }
-keep class com.android.pairipfix.mainHook { *; }

# Rimuovi tutto ciò che non è necessario
-dontwarn **
-dontnote **

# Ottimizzazioni aggressive
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*,!code/allocation/variable
-allowaccessmodification
-repackageclasses ''