package com.realsight.brain.timeseries.lib.model.htm;

import java.util.ArrayList;
import java.util.List;

import com.realsight.brain.timeseries.lib.model.htm.neurongroups.*;
import com.realsight.brain.timeseries.lib.series.DoubleSeries;

public class SoundHierarchy {
	
	private double minValue;
	private double maxValue;
	private static final double threshold = 0.25;
	private static final int maxLeftSemiContextsLenght = 7;
	private static final int maxActiveNeuronsNum = 15;
	private static final int numNeuroGroup = 1;
	private static final int numBit = 5;
	private static final int numFact = 3;
	private double fullValueRange;
	private double minValueStep;
	private List<NeuroGroup> neuroGroups = new ArrayList<NeuroGroup>();
	
	private SoundHierarchy(double minValue, double maxValue) {
		
		this.minValue = minValue;
		this.maxValue = maxValue;
		this.fullValueRange = this.maxValue - this.minValue;
		int numNormValue = (1<<numBit) - 1;
        if ( this.fullValueRange == 0.0 ) {
        	this.fullValueRange = numNormValue;
        }
		this.minValueStep = this.fullValueRange / numNormValue;
		for ( int i = 0; i < numNeuroGroup; i++ ) {
			this.neuroGroups.add(new NeuroGroup(maxActiveNeuronsNum, maxLeftSemiContextsLenght));
		}
	}
	private void learn(List<Integer> currSensFacts){
		for ( int i = 0; i < numNeuroGroup; i++ ) {
			double tmp = this.neuroGroups.get(i).learn(currSensFacts);
//			System.out.println(tmp);
		}
	}
	private double predict(List<Integer> currSensFacts) {
		double sum = 0.0;
		for ( int i = 0; i < numNeuroGroup; i++ ) {
			double p = this.neuroGroups.get(i).predict(currSensFacts);
//			sum += p;
			if ( p > threshold ) sum += 1.0;
		}
		return sum;
	}
	public void train(DoubleSeries sound) {
		for(int i = 0; i < sound.size(); i += 1){
			List<Integer> currSensFacts = new ArrayList<Integer>();
			for(int j = 0; j<numFact && j+i<sound.size(); j++){
				int bit = (int) ((sound.get(i+j).getItem()-this.minValue)/minValueStep);
				currSensFacts.add(bit);
			}
			learn(currSensFacts);
		}
	}
	
	public double test(DoubleSeries sound) {
		for ( int i = 0; i < numNeuroGroup; i++ ) {
			this.neuroGroups.get(i).sleep();
		}
		double res = 0.0;
		for(int i = 0; i < sound.size(); i += 1){
			int bit = (int) ((sound.get(i).getItem()-this.minValue)/minValueStep);
			if ( bit == 0 ) continue;
			List<Integer> currSensFacts = new ArrayList<Integer>();
			for(int j = 0; j < numBit; j++){
				if((bit&1) > 0){
					currSensFacts.add(j*2+1);
				} else {
					currSensFacts.add(j*2);
				}
				bit >>= 1;
			}
			res += predict(currSensFacts);
		}
//		System.out.print("\n");
		return res/sound.size();
	}
	
	public static SoundHierarchy build(DoubleSeries sound){
		if ( sound==null || sound.size()==0 )
			return null;
		double minValue = Double.MAX_VALUE;
		double maxValue = Double.MIN_VALUE;
		for(int i = 0; i < sound.size(); i++){
			minValue = Math.min(minValue, sound.get(i).getItem());
			maxValue = Math.max(maxValue, sound.get(i).getItem());
		}
		SoundHierarchy h = new SoundHierarchy(minValue, maxValue);
		h.train(sound);
		return h;
	}
	
	public static SoundHierarchy build(double minValue, double maxValue){
		SoundHierarchy h = new SoundHierarchy(minValue, maxValue);
		return h;
	}
}

