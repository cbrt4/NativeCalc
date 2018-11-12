//
// Created by alex on 12.11.18.
//

#include "calc.h"
#include <jni.h>

JNIEXPORT jint JNICALL Java_com_alex_nativecalc_MainActivity_calculate(JNIEnv *env, jobject instance, int a, int b)
{
    return a + b;
}
