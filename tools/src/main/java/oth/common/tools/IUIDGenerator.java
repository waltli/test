package oth.common.tools;

import com.qzsoft.common.inner.UIDGenerator1;

public class IUIDGenerator {
	public static long getUID() {
		return UIDGenerator1.getUID();
	}

	public static long getUID(Long wId) {
		return UIDGenerator1.getUID(wId);
	}
}
