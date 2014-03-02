package com.verhas.misi;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.isA;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class BiMapTest {
	private static final Map<Object, Object> MAP = new HashMap<>();
	private static final Object KEY = new Object();
	private static final Object VALUE = new Object();

	private final BiMap<Object, Object> biMap = new BiMap<>(MAP, MAP);

	@Test(expected = NullPointerException.class)
	public void constructorDoesNotCreateOnNullKeyMap() {
		new BiMap<>(null, MAP);
	}

	@Test(expected = NullPointerException.class)
	public void constructorDoesNotCreateOnNullValueMap() {
		new BiMap<>(MAP, null);
	}

	@Test
	public void constructorCreatesNewBiMapOnNonNullMaps() {
		BiMap<Object, Object> biMap = new BiMap<>(MAP, MAP);
		assertThat(biMap, isA(BiMap.class));
	}

	@Test(expected = IllegalArgumentException.class)
	public void doesNotPutsNullKeyInBiMap() {
		biMap.put(null, VALUE);
	}

	@Test(expected = IllegalArgumentException.class)
	public void doesNotPutNullValueInBiMap() {
		biMap.put(KEY, null);
	}

	@Test
	public void putsElementInBiMap() {
		biMap.put(KEY, VALUE);
	}

	@Test
	public void getsElementByKeyFromBiMap() {
		biMap.put(KEY, VALUE);
		assertThat(biMap.getByKey(KEY), equalTo(VALUE));
	}

	@Test
	public void getsElementByValueFromBiMap() {
		biMap.put(KEY, VALUE);
		assertThat(biMap.getByValue(VALUE), equalTo(KEY));
	}

	@Test
	public void getsSizeOfBiMap() {
		BiMap<Object, Object> biMap = new BiMap<>(
				new HashMap<Object, Object>(), new HashMap<Object, Object>());
		assertThat(biMap.size(), equalTo(0));
		biMap.put(KEY, VALUE);
		assertThat(biMap.size(), equalTo(1));
	}

	@Test
	public void clearsBiMap() {
		biMap.clear();
		assertThat(biMap.size(), equalTo(0));
	}

	@Test
	public void checksIfBiMapContainsKey() {
		biMap.clear();
		assertThat(biMap.containsKey(KEY), equalTo(false));
		biMap.put(KEY, VALUE);
		assertThat(biMap.containsKey(KEY), equalTo(true));
	}

	@Test
	public void checksIfBiMapContainsValue() {
		biMap.clear();
		assertThat(biMap.containsValue(VALUE), equalTo(false));
		biMap.put(KEY, VALUE);
		assertThat(biMap.containsValue(VALUE), equalTo(true));
	}

	@Test
	public void checksIfBiMapIsEmpty() {
		biMap.clear();
		assertThat(biMap.isEmpty(), equalTo(true));
		biMap.put(KEY, VALUE);
		assertThat(biMap.isEmpty(), equalTo(false));
	}

	@Test
	public void removesElementsByKeyFromBiMap() {
		biMap.clear();
		biMap.put(KEY, VALUE);
		Object removedValue = biMap.getByKey(KEY);
		biMap.removeByKey(KEY);
		assertThat(biMap.containsValue(removedValue), equalTo(false));
	}

	@Test
	public void removesElementsByValueFromBiMap() {
		biMap.clear();
		biMap.put(KEY, VALUE);
		Object removedKey = biMap.getByValue(VALUE);
		biMap.removeByValue(VALUE);
		assertThat(biMap.containsKey(removedKey), equalTo(false));
	}
}
