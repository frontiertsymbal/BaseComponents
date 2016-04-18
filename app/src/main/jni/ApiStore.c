#include <jni.h>

jstring Java_mock_brains_basecomponents_core_ApiKeyStoreManager_getKeyOne(JNIEnv *env,
                                                                          jobject javaThis) {
    return (*env)->NewStringUTF(env, "GetSomeStringKeyOne");
}

jstring Java_mock_brains_basecomponents_core_ApiKeyStoreManager_getKeyTwo(JNIEnv *env,
                                                                          jobject javaThis) {
    return (*env)->NewStringUTF(env, "GetSomeStringKeyTwo");
}