jni
===
Tutorial can be found here : http://www.ibm.com/developerworks/java/tutorials/j-jni/j-jni.html

copy paste this in command prompt to compile dll library 
gcc -Wl,--add-stdcall-alias -I"%JAVA_HOME%\include" -I"%JAVA_HOME%\include\win32" -shared -o Sample1.dll Sample1.c

Use this to compile in windows for 64 bit http://tdm-gcc.tdragon.net/download
