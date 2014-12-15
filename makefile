ifeq ($(OS),Windows_NT)
    MYOS=Windows_NT
	java=$(JAVA_HOME)/bin/java
else
    MYOS=Linux
	java=java
endif

.SUFFIXES: .java .class
.java.class:
	javac $*.java

DetectOS:
	@make $(MYOS)

Java:
	javah Sample1

Windows_NT: Java
	gcc -fpic -m64 -shared -I"$(JAVA_HOME)\include" -I"$(JAVA_HOME)\include\win32" -o Sample1.dll Sample1.c

Linux: Java
	gcc -fpic -m64 -shared -I"$(JAVA_HOME)/include" -I"$(JAVA_HOME)/include/linux" -o libSample1.so Sample1.c

test: DetectOS Sample1.class
	$(java) -version
	$(java) Sample1

clean:
	-$(RM) *.o
	-$(RM) *~
	-$(RM) \#*
	-$(RM) *.core

