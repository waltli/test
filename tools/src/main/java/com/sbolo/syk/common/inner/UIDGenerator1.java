package com.sbolo.syk.common.inner;

public class UIDGenerator1 {
    private final long twepoch = 1509976472321L;

    private final long wIdBits = 3L;

    private final long maxWId = -1L ^ (-1L << wIdBits);
    
    private final long tsLeftShift = wIdBits;

    private long wId;
    
    private long lastTs = -1L;
    
    private static class UIDGeneratorHolder {
        private static final UIDGenerator1 instance = new UIDGenerator1();
    }
    
    private static UIDGenerator1 get(){
        return UIDGeneratorHolder.instance;
    }
    
	public static long getUID() {
    	return getUID(null);
	}

	public static long getUID(Long wId) {
		UIDGenerator1 generator = get();
		if(wId == null){
			wId = 0l;
		}else if (wId.longValue() > generator.maxWId || wId.longValue() < 0) {
            throw new IllegalArgumentException("workId has over the limit");
        }
		generator.wId = wId;
        return generator.nextId();
	}

    private synchronized long nextId() {
        long ts = timeGen();

        if (ts < lastTs) {
            throw new RuntimeException(
                    String.format("the invalid timestamp %d", lastTs - ts));
        }

        if (lastTs == ts) {
        	ts = tilNextMillis(lastTs);
        }

        lastTs = ts;

        return ((ts - twepoch) << tsLeftShift) | wId;
                
    }

    private long tilNextMillis(long lastTs) {
        long ts = timeGen();
        while (ts <= lastTs) {
        	ts = timeGen();
        }
        return ts;
    }

    private long timeGen() {
        return System.currentTimeMillis();
    }
}
