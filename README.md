jni
===
Tutorial can be found here : http://www.ibm.com/developerworks/java/tutorials/j-jni/j-jni.html

Run **make test** to compile everything and run the main Java program.  The makefile will detect your operating system.

### System setup
Ensure `LD_LIBRARY_PATH` environment variable includes the current directory. A blank entry suffices.  For example, `/usr/local/cuda-6.0/lib64:` will do the trick due to the trailing `:`.

### Windows Notes
Install [make for Windows](http://gnuwin32.sourceforge.net/packages/make.htm).

Install a 64-bit compiler version of gcc, or install <http://tdm-gcc.tdragon.net/download>.

Add the following to your `Path` environment variable (may be different on your system):

	C:\Program Files (x86)\GnuWin32\bin;C:\Program Files\Java\jdk1.7.0_72\bin

Ensure that the JNI library actually exists in `%JAVA_HOME%\include` and that the directory `%JAVA_HOME%\include\win32` exists.

