ifeq ($(OS),Windows_NT)
    MYOS=Windows_NT
	java=$(JAVA_HOME)/bin/java
#    CCFLAGS += -D WIN32
#    ifeq ($(PROCESSOR_ARCHITECTURE),AMD64)
#        CCFLAGS += -D AMD64
#    else 
#    ifeq ($(PROCESSOR_ARCHITEW6432),AMD64)
#        CCFLAGS += -D AMD64
##    else
##    ifeq ($(PROCESSOR_ARCHITECTURE),x86)
##        CCFLAGS += -D IA32
##    endif
#    endif
#    endif
else
    MYOS=Linux
#    JLP=".:/usr/java/packages/lib/amd64:/usr/lib64:/lib64:/lib:/usr/lib"
#	lpath=$(LD_LIBRARY_PATH):.
	java=java
#    UNAME_S := $(shell uname -s)
#    ifeq ($(UNAME_S),Linux)
#        CCFLAGS += -D LINUX
#    endif
#    ifeq ($(UNAME_S),Darwin)
#        CCFLAGS += -D OSX
#    endif
#    UNAME_P := $(shell uname -p)
#    ifeq ($(UNAME_P),x86_64)
#        CCFLAGS += -D AMD64
#    endif
#    ifneq ($(filter %86,$(UNAME_P)),)
#        CCFLAGS += -D IA32
#    endif
#    ifneq ($(filter arm%,$(UNAME_P)),)
#        CCFLAGS += -D ARM
#    endif
endif

#ifndef LD_LIBRARY_PATH
#	LD_LIBRARY_PATH=""
#endif


.SUFFIXES: .java .class
.java.class:
	javac $*.java

DetectOS:
	@make $(MYOS)

Java:
	javah Sample1

Windows_NT: Java
#	gcc -Wl,--add-stdcall-alias -I"%JAVA_HOME%\include" -I"%JAVA_HOME%\include\win32" -shared -o Sample1.dll Sample1.c
	gcc -fpic -m64 -shared -I"$(JAVA_HOME)\include" -I"$(JAVA_HOME)\include\win32" -o Sample1.dll Sample1.c

Linux: Java
	gcc -fpic -m64 -shared -I"$(JAVA_HOME)/include" -I"$(JAVA_HOME)/include/linux" -o libSample1.so Sample1.c
#	gcc -Wl,--add-stdcall-alias -I"$(JAVA_HOME)/include" -I"$(JAVA_HOME)/include/linux" -shared -o Sample1.dll Sample1.c

test: DetectOS Sample1.class
	$(java) -version
	$(java) Sample1

clean:
	-$(RM) *.o
	-$(RM) *~
	-$(RM) \#*
	-$(RM) *.core

