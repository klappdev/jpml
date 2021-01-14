package org.kl.jpml.util;

import java.util.function.Supplier;

public final class Lazy<T> {
	private volatile Supplier<T> delegate;
	private volatile boolean initialized;
	private T value;

	public Lazy(Supplier<T> delegate) {
		this.delegate = delegate;
	}
	 
	public T get() {
		if (initialized) {
			return value;
		}
		
		this.initialized = true;
		this.value = delegate.get();
		
		return value;
	}

	public T getSync() {
		if (!initialized) {
			synchronized (this) {
				if (!initialized) {
					T result = delegate.get();
					this.value = result;

					this.initialized = true;
					this.delegate = null;

					return result;
				}
			}
		}

		return value;
	}
}
