package com.nju.edu.citibackend.util;

/**
 * @author Zyi
 */
public class Pair<K, V> {

	private K key;
	private V value;

	public Pair(K key, V value) {
		this.key = key;
		this.value = value;
	}

	public Pair() {

	}

	public K getKey() {
		return key;
	}

	public V getValue() {
		return value;
	}
}
