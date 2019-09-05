package org.kl.util;

import java.util.function.Supplier;

public final class Lazy<T> {
	private Supplier<T> supplier;
	private T value;
	private boolean supplied;
	 
	public Lazy(Supplier<T> supplier) {
		this.supplier = supplier;
	}
	 
	public T get() {
		if (supplied) {
			return value;
		}
		
		this.supplied = true;
		this.value = supplier.get();
		
		return value;
	}
}
