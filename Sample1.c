#include "Sample1.h"
#include <string.h>
#include <ctype.h>

JNIEXPORT jint JNICALL Java_Sample1_intMethod
  (JNIEnv *env, jobject obj, jint num)
{
	return num * num;
}

JNIEXPORT jboolean JNICALL Java_Sample1_booleanMethod
  (JNIEnv *env, jobject obj, jboolean boolean)
{
	return !boolean;
}

// strupr is not a standard function; defining own version here
void mystrupr(char *str, int maxlen) 
{
  int i=0;
  for (; i < maxlen && str[i] != '\0'; i++)
    str[i] = (char)toupper(str[i]);
}

JNIEXPORT jstring JNICALL Java_Sample1_stringMethod
  (JNIEnv *env, jobject obj, jstring string)
{
	const char *str = (*env)->GetStringUTFChars(env, string, 0);
	char cap[128];
	strcpy(cap, str);
	(*env)->ReleaseStringUTFChars(env, string, str);
	mystrupr(cap,128);
	return (*env)->NewStringUTF(env, cap);
}

JNIEXPORT jint JNICALL Java_Sample1_intArrayMethod
  (JNIEnv *env, jobject obj, jintArray array)
{
	int i, sum = 0;
	jsize len = (*env)->GetArrayLength(env, array);
	jint *body = (*env)->GetIntArrayElements(env, array, 0);
	for (i=0; i<len; i++)
	{	sum += body[i];
	}
	(*env)->ReleaseIntArrayElements(env, array, body, 0);
	return sum;
}

//void main(){}
