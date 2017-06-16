
#include "com_yxj_helloffmpeglibrary_HelloFFmpegLibrary.h"

#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     com_yxj_helloffmpeglibrary_HelloFFmpegLibrary
 * Method:    showFFmpegConfigution
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_yxj_helloffmpeglibrary_HelloFFmpegLibrary_showFFmpegConfiguration(JNIEnv *env, jobject obj)
{
	return (*env)->NewStringUTF(env,"This just a test for Android Studio NDK JNI developer !");
}

#ifdef __cplusplus
}
#endif