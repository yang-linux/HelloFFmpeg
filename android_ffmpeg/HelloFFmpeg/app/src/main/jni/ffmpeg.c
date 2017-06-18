
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
#include <libavformat/avformat.h>
#include  <libavfilter/avfilter.h>

#define CONFIGURATION 	1
#define PROTOCOL 		2
#define FORMAT 			3
#define FILTER			4
#define CODEC			5

static void showConfiguration(char * pcInfo)
{
	sprintf(pcInfo, "%s\n", avcodec_configuration());
}

static void showProtocol(char * pcInfo)
{
	struct URLProtocol *ptPup = NULL;
	struct URLProtocol **pptPTemp = &ptPup;
	avio_enum_protocols((void **)pptPTemp, 0);
	while ((*pptPTemp) != NULL){
		sprintf(pcInfo, "%s[In ][%10s]\n", pcInfo, avio_enum_protocols((void **)pptPTemp, 0));
	}

    ptPup = NULL;
	avio_enum_protocols((void **)pptPTemp, 1);
	while ((*pptPTemp) != NULL){
		sprintf(pcInfo, "%s[Out][%10s]\n", pcInfo, avio_enum_protocols((void **)pptPTemp, 1));
	}
}

static void showCodec(char * pcInfo)
{
    AVCodec *ptAVCode = av_codec_next(NULL);

    while(ptAVCode!=NULL){
        if (ptAVCode->decode!=NULL){
            sprintf(pcInfo, "%s[Dec]", pcInfo);
        }
        else{
            sprintf(pcInfo, "%s[Enc]", pcInfo);
        }
        switch (ptAVCode->type){
            case AVMEDIA_TYPE_VIDEO:
                sprintf(pcInfo, "%s[Video]", pcInfo);
                break;
            case AVMEDIA_TYPE_AUDIO:
                sprintf(pcInfo, "%s[Audio]", pcInfo);
                break;
            default:
                sprintf(pcInfo, "%s[Other]", pcInfo);
                break;
        }
        sprintf(pcInfo, "%s[%10s]\n", pcInfo, ptAVCode->name);
        ptAVCode = ptAVCode->next;
    }
}

static void showFilter(char * pcInfo)
{
	AVFilter *ptAVFilter = (AVFilter *)avfilter_next(NULL);
	while (ptAVFilter != NULL){
		sprintf(pcInfo, "%s[%10s]\n", pcInfo, ptAVFilter->name);
	}
}

static void showFormat(char * pcInfo)
{
	AVInputFormat *ptInputFormatTemp = av_iformat_next(NULL);
	AVOutputFormat *ptOutputFormatTemp = av_oformat_next(NULL);
	//Input
	while(ptInputFormatTemp!=NULL){
		sprintf(pcInfo, "%s[In ][%10s]\n", pcInfo, ptInputFormatTemp->name);
        ptInputFormatTemp = ptInputFormatTemp->next;
	}
	//Output
	while (ptOutputFormatTemp != NULL){
		sprintf(pcInfo, "%s[Out][%10s]\n", pcInfo, ptOutputFormatTemp->name);
        ptOutputFormatTemp = ptOutputFormatTemp->next;
	}
}


JNIEXPORT jstring JNICALL Java_com_yxj_helloffmpeglibrary_HelloFFmpegLibrary_showFFmpegInfo(JNIEnv *env, jobject obj, jint iSwitch)
{
	char info[40000] = { 0 };

	av_register_all();

	switch(iSwitch)
	{
		case CONFIGURATION:
			showConfiguration(info);
			return (*env)->NewStringUTF(env, info);
			break;
		case PROTOCOL:
			showProtocol(info);
			return (*env)->NewStringUTF(env, info);
			break;
		case FORMAT:
			showFormat(info);
			return (*env)->NewStringUTF(env, info);
			break;
		case FILTER:
			showFilter(info);
			return (*env)->NewStringUTF(env, info);
			break;
		case CODEC:
			showCodec(info);
			return (*env)->NewStringUTF(env, info);
			break;

	}
}

#ifdef __cplusplus
}
#endif