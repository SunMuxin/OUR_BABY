package com.realsight.brain.timeseries.lib.util;

public class Pair <T,S>{
	private T a;
	private S b;
	
	public Pair(T a, S b){
		this.a = a;
		this.b = b;
	}
	
	public T getA() {
		return a;
	}
	public void setA(T a) {
		this.a = a;
	}
	public S getB() {
		return b;
	}
	public void setB(S b) {
		this.b = b;
	}
}
