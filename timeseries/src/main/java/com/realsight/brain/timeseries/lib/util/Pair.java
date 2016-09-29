package com.realsight.brain.timeseries.lib.util;

public class Pair <T,S> implements Comparable<Pair<Double, String>>{
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

	@Override
	public int compareTo(Pair<Double, String> o) {
		// TODO Auto-generated method stub
		if((Double) this.a < o.a){
			return -1;
		}
		if((Double) this.a > o.a){
			return 1;
		}
		return 0;
	}
}
