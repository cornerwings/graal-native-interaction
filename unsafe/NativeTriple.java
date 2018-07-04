import java.io.*;
import java.util.*;
import sun.misc.Unsafe;
import java.lang.reflect.Field;

public class NativeTriple {

	private long ptr;

	private NativeValue subject;
	private NativeValue predicate;
	private NativeValue object;

	private NativeTriple(long ptr) {
		this.ptr = ptr;
		this.subject = new NativeValue(ptr + 0);
		this.predicate = new NativeValue(ptr + 16);
		this.object = new NativeValue(ptr + 32);
	}

	public NativeValue subject() {
		return subject;
	}

	public NativeValue predicate() {
		return predicate;
	}

	public NativeValue object() {
		return object;
	}

	public void close() {
		freeTriple(ptr);
	}

	public static NativeTriple create() {
		long ptr = allocRandomTriple();
		return new NativeTriple(ptr);
	}

	private static native long allocRandomTriple();

	private static native void freeTriple(long ptr);

	public static class NativeValue {

		private long ptr;
		private static Unsafe unsafe = getUnsafe();

		public NativeValue(long ptr) {
			this.ptr = ptr;
		}

		public int getType() {
			return unsafe.getInt(ptr);
		}

		public long getId() {
			return unsafe.getLong(ptr + 8);
		}

	}

	@SuppressWarnings("restriction")
    private static Unsafe getUnsafe() {

    	try {
	        Field singleoneInstanceField = Unsafe.class.getDeclaredField("theUnsafe");
	        singleoneInstanceField.setAccessible(true);
	        return (Unsafe) singleoneInstanceField.get(null);
	    } catch (Exception e) {
	    	return null;
	    }
    }

}