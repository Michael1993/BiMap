package com.verhas.misi;

import java.util.Map;

public class BiMap<KEY, VALUE> {
	private final Map<KEY, VALUE> mapByKey;
	private final Map<VALUE, KEY> mapByValue;

	public BiMap(Map<KEY, VALUE> mapByKey, Map<VALUE, KEY> mapByValue) {
		if (mapByKey == null || mapByValue == null)
			throw new NullPointerException();
		this.mapByKey = mapByKey;
		this.mapByValue = mapByValue;
	}

	public void put(KEY key, VALUE value) {
		if (key == null || value == null)
			throw new IllegalArgumentException();
		mapByKey.put(key, value);
		mapByValue.put(value, key);
	}

	public VALUE getByKey(KEY key) {
		return mapByKey.get(key);
	}

	public KEY getByValue(VALUE value) {
		return mapByValue.get(value);
	}

	public void clear() throws UnsupportedOperationException {
		mapByKey.clear();
		mapByValue.clear();
	}

	public boolean containsKey(KEY key) {
		return mapByKey.containsKey(key);
	}

	public boolean containsValue(VALUE value) {
		return mapByKey.containsValue(value);
	}

	public int size() {
		return mapByKey.size();
	}

	public boolean isEmpty() {
		return mapByKey.isEmpty();
	}

	public void removeByKey(KEY key) throws UnsupportedOperationException {
		mapByValue.remove(mapByKey.get(key));
		mapByKey.remove(key);
	}

	public void removeByValue(VALUE value) throws UnsupportedOperationException {
		mapByKey.remove(mapByValue.get(value));
		mapByValue.remove(value);
	}
}
