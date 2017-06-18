
#include "com_yxj_helloffmpeglibrary_HelloFFmpegLibrary.h"

#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     com_yxj_helloffmpeglibrary_HelloFFmpegLibrary
 * Method:    showFFmpegConfigution
 * Signature: ()Ljava/lang/String;
 */
#include <libavcodec/avcodec.h>

JNIEXPORT jstring JNICALL Java_com_yxj_helloffmpeglibrary_HelloFFmpegLibrary_showFFmpegConfiguration(JNIEnv *env, jobject obj)
{
	char info[10000] = { 0 };
	sprintf(info, "%s\n", avcodec_configuration());
	return (*env)->NewStringUTF(env, info);
	//return (*env)->NewStringUTF(env,"This just a test for Android Studio NDK JNI developer !");
}

#ifdef __cplusplus
}
#endif