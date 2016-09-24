package com.realsight.brain.timeseries.lib.model.htm.neurongroups;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.realsight.brain.timeseries.lib.util.Pair;

public class NeuroGroup {
	
	private int maxActiveNeuronsNum;
	private List<Integer> leftFactsGroup;
	private NeuroGroupOperator neuroGroupOperator;
	private List<Pair<List<Integer>, List<Integer>>> potentialNewContextList;
	
	public NeuroGroup(int maxActiveNeuronsNum, int maxLeftSemiContextsLenght) {
		this.maxActiveNeuronsNum = maxActiveNeuronsNum;
		this.leftFactsGroup = new ArrayList<Integer>();
		this.neuroGroupOperator = new NeuroGroupOperator(maxLeftSemiContextsLenght);
		this.potentialNewContextList = new ArrayList<Pair<List<Integer>, List<Integer>>>();
	}
	
	public void sleep() {
		this.leftFactsGroup = new ArrayList<Integer>();
		this.potentialNewContextList = new ArrayList<Pair<List<Integer>, List<Integer>>>();
		List<Integer> currSensFacts = new ArrayList<Integer>();
		this.activate(currSensFacts, false);
	}
	
	@SuppressWarnings("unchecked")
	private double activate(List<Integer> currSensFacts, boolean rate) {
//		for(int i = 0; i < currSensFacts.size(); i++)
//			System.out.print(currSensFacts.get(i) + " ");
//		System.out.print("|||| ");
//		for(int i = 0; i < this.leftFactsGroup.size(); i++)
//			System.out.print(this.leftFactsGroup.get(i) + " ");
//		System.out.print("\n");
		List<Pair<List<Integer>, List<Integer>>> potNewZeroLevelContext = 
				new ArrayList<Pair<List<Integer>, List<Integer>>>();
		int newContextFlag = -2;
		if ( (this.leftFactsGroup.size()>0) && (currSensFacts.size()>0) && rate ) {
			potNewZeroLevelContext.add(new Pair<List<Integer>, List<Integer>>(this.leftFactsGroup, currSensFacts));
            newContextFlag = this.neuroGroupOperator.getContextByFacts(potNewZeroLevelContext, 1);
		}
		this.neuroGroupOperator.contextCrosser(1, currSensFacts, newContextFlag==-1, new ArrayList<Pair<List<Integer>, List<Integer>>>());
		double percentSelectedContextActive = 0.0;
		if (this.neuroGroupOperator.getNumSelectedContext() > 0) {
			percentSelectedContextActive = 
					1.0 * this.neuroGroupOperator.getActiveContexts().size() / this.neuroGroupOperator.getNumSelectedContext();
		}
		Collections.sort(this.neuroGroupOperator.getActiveContexts());
		this.leftFactsGroup = new ArrayList<Integer>();
		for ( int i = 0; i<this.neuroGroupOperator.getActiveContexts().size() && i<this.maxActiveNeuronsNum; i++){
			this.leftFactsGroup.add(this.neuroGroupOperator.getActiveContexts().get(i).getContextID()+(1<<30));
		}
		for ( int i = 0; i < currSensFacts.size(); i++ ) {
			this.leftFactsGroup.add(currSensFacts.get(i));
		}
		this.potentialNewContextList = this.neuroGroupOperator.getPotentialNewContextList();
		if(rate == false)
			this.potentialNewContextList = new ArrayList<Pair<List<Integer>, List<Integer>>>();
		this.neuroGroupOperator.contextCrosser(0, this.leftFactsGroup, false, this.potentialNewContextList);
		return percentSelectedContextActive;
	}
	
	public double learn(List<Integer> currSensFacts) {
		return activate(currSensFacts, true);
	}
	
	public double predict(List<Integer> currSensFacts) {
		return activate(currSensFacts, false);
	}
}

