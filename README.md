# README
This repository shows two ways to interact with native methods from Java. Code is not ready to compile due to the use of absolute paths but feel free to use the code for reference. Below are the instructions at a high level to get this working.

## Graal
1. Compile the shared library
```clang -shared -o libtriple.so triple.cc -Itriple.h```
2. Compile the java classes (using Graal javac compiler)
```javac Headers.java Main.java```
3. Build the native image
```native-image -cp . --verbose -Djava.library.path=. -H:CLibraryPath=.  Main```
4. Run the application
```./main```

## Unsafe
1. Compile the shared library
```clang -shared -o libtriple.so triple.cc -Itriple.h -INativeTriple.h -I/path/to/java/headers```
2. Compile the java classes
```javac NativeTriple.java Main.java```
3. Run the application
```java Main```