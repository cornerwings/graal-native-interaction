#include "triple.h"
#include "NativeTriple.h"
#include <iostream>
#include <cstddef>

static uint64_t counter = 1;

extern "C" {

	triple_t* allocRandomTriple() {
	    triple_t *triple = (triple_t*) malloc(sizeof(triple_t));
	    triple->subject.id = counter++;
	    triple->predicate.id = counter++;
	    triple->object.id = counter++;
	    return triple;
	}

	void freeTriple(triple_t *triple) {
	    free(triple);
	}

	/*
	 * Class:     NativeTriple
	 * Method:    allocRandomTriple
	 * Signature: ()J
	 */
	JNIEXPORT jlong JNICALL Java_NativeTriple_allocRandomTriple(JNIEnv *env, jclass clazz) {
		triple_t *triple = allocRandomTriple();
		return (long) triple;
	}

	/*
	 * Class:     NativeTriple
	 * Method:    freeTriple
	 * Signature: (J)V
	 */
	JNIEXPORT void JNICALL Java_NativeTriple_freeTriple(JNIEnv *env, jclass clazz, jlong ptr) {
		triple_t *triple = (triple_t*) ptr;
		freeTriple(triple);
	}

}
