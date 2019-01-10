package oth.common.http;

public class HttpResult<V> {
	private V value;
	private Exception exception;
	private Boolean hasException = false;

	public V getValue() throws Exception {
		if (HasException()) {
			throw getException();
		}
		return value;
	}

	public void setValue(V value) {
		this.value = value;
	}

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}

	public Boolean HasException() {
		return hasException;
	}

	public void setHasException(Boolean hasException) {
		this.hasException = hasException;
	}
}
