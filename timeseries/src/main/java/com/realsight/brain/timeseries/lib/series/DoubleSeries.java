package com.realsight.brain.timeseries.lib.series;

import static com.realsight.brain.timeseries.lib.util.Util.check;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DoubleSeries extends TimeSeries<Double> {
    String mName;

    public DoubleSeries(List<Entry<Double>> data, String name) {
        super(data);
        mName = name;
    }

    public DoubleSeries(String name) {
        super();
        mName = name;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public DoubleSeries and(DoubleSeries other) {
    	check(this.isAscending());
        check(other.isAscending());

        Iterator<Entry<Double>> i1 = this.iterator();
        Iterator<Entry<Double>> i2 = other.iterator();

        List<Entry<Double>> newEntries = new ArrayList<>();

        while (i1.hasNext() && i2.hasNext()) {
            Entry<Double> n1 = i1.next();
            Entry<Double> n2 = i2.next();

            while (!n2.mInstant.equals(n1.mInstant)) {
                if (n1.mInstant < n2.mInstant) {
                    while (i1.hasNext()) {
                        n1 = i1.next();
                        if (!(n1.mInstant<n2.mInstant)) {
                            break;
                        }
                    }
                } else if (n2.mInstant<n1.mInstant) {
                    while (i2.hasNext()) {
                        n2 = i2.next();
                        if (!(n2.mInstant<n1.mInstant)) {
                            break;
                        }
                    }
                }
            }

            if (n2.mInstant.equals(n1.mInstant)) {
                newEntries.add(new Entry<Double>(n1.mT, n1.mInstant));
            }
        }

        return new DoubleSeries(newEntries, mName);
    }

    public DoubleSeries or(DoubleSeries other) {
    	check(this.isAscending());
        check(other.isAscending());

        Iterator<Entry<Double>> i1 = this.iterator();
        Iterator<Entry<Double>> i2 = other.iterator();

        List<Entry<Double>> newEntries = new ArrayList<>();

        while (i1.hasNext() && i2.hasNext()) {
            Entry<Double> n1 = i1.next();
            Entry<Double> n2 = i2.next();

            while (!n2.mInstant.equals(n1.mInstant)) {
                if (n1.mInstant < n2.mInstant) {
                    while (i1.hasNext()) {
                    	newEntries.add(new Entry<Double>(n1.mT, n1.mInstant));
                        n1 = i1.next();
                        if (!(n1.mInstant<n2.mInstant)) {
                            break;
                        }
                    }
                } else if (n2.mInstant<n1.mInstant) {
                    while (i2.hasNext()) {
                    	newEntries.add(new Entry<Double>(n2.mT, n2.mInstant));
                        n2 = i2.next();
                        if (!(n2.mInstant<n1.mInstant)) {
                            break;
                        }
                    }
                }
            }

            if (n2.mInstant.equals(n1.mInstant)) {
                newEntries.add(new Entry<Double>(n1.mT, n1.mInstant));
            }
        }

        return new DoubleSeries(newEntries, mName);
    }

    public DoubleSeries plus(DoubleSeries other) {
    	check(this.isAscending());
        check(other.isAscending());

        Iterator<Entry<Double>> i1 = this.iterator();
        Iterator<Entry<Double>> i2 = other.iterator();

        List<Entry<Double>> newEntries = new ArrayList<>();

        while (i1.hasNext() && i2.hasNext()) {
            Entry<Double> n1 = i1.next();
            Entry<Double> n2 = i2.next();

            while (!n2.mInstant.equals(n1.mInstant)) {
                if (n1.mInstant < n2.mInstant) {
                    while (i1.hasNext()) {
                        n1 = i1.next();
                        if (!(n1.mInstant<n2.mInstant)) {
                            break;
                        }
                    }
                } else if (n2.mInstant<n1.mInstant) {
                    while (i2.hasNext()) {
                        n2 = i2.next();
                        if (!(n2.mInstant<n1.mInstant)) {
                            break;
                        }
                    }
                }
            }

            if (n2.mInstant.equals(n1.mInstant)) {
                newEntries.add(new Entry<Double>(n1.mT+n2.mT, n1.mInstant));
            }
        }

        return new DoubleSeries(newEntries, mName);
    }

    public DoubleSeries plus(double factor) {
    	check(this.isAscending());

        Iterator<Entry<Double>> i1 = this.iterator();

        List<Entry<Double>> newEntries = new ArrayList<>();

        while (i1.hasNext()) {
            Entry<Double> n1 = i1.next();
            newEntries.add(new Entry<Double>(n1.mT+factor, n1.mInstant));
        }
        
        return new DoubleSeries(newEntries, mName);
    }

    public DoubleSeries mul(DoubleSeries other) {
    	check(this.isAscending());
        check(other.isAscending());

        Iterator<Entry<Double>> i1 = this.iterator();
        Iterator<Entry<Double>> i2 = other.iterator();

        List<Entry<Double>> newEntries = new ArrayList<>();

        while (i1.hasNext() && i2.hasNext()) {
            Entry<Double> n1 = i1.next();
            Entry<Double> n2 = i2.next();

            while (!n2.mInstant.equals(n1.mInstant)) {
                if (n1.mInstant < n2.mInstant) {
                    while (i1.hasNext()) {
                        n1 = i1.next();
                        if (!(n1.mInstant<n2.mInstant)) {
                            break;
                        }
                    }
                } else if (n2.mInstant<n1.mInstant) {
                    while (i2.hasNext()) {
                        n2 = i2.next();
                        if (!(n2.mInstant<n1.mInstant)) {
                            break;
                        }
                    }
                }
            }

            if (n2.mInstant.equals(n1.mInstant)) {
                newEntries.add(new Entry<Double>(n1.mT*n2.mT, n1.mInstant));
            }
        }

        return new DoubleSeries(newEntries, mName);
    }

    public DoubleSeries mul(double factor) {
    	check(this.isAscending());

        Iterator<Entry<Double>> i1 = this.iterator();

        List<Entry<Double>> newEntries = new ArrayList<>();

        while (i1.hasNext()) {
            Entry<Double> n1 = i1.next();
            newEntries.add(new Entry<Double>(n1.mT*factor, n1.mInstant));
        }
        
        return new DoubleSeries(newEntries, mName);
    }

    public DoubleSeries div(DoubleSeries other) {
    	check(this.isAscending());
        check(other.isAscending());

        Iterator<Entry<Double>> i1 = this.iterator();
        Iterator<Entry<Double>> i2 = other.iterator();

        List<Entry<Double>> newEntries = new ArrayList<>();

        while (i1.hasNext() && i2.hasNext()) {
            Entry<Double> n1 = i1.next();
            Entry<Double> n2 = i2.next();

            while (!n2.mInstant.equals(n1.mInstant)) {
                if (n1.mInstant < n2.mInstant) {
                    while (i1.hasNext()) {
                        n1 = i1.next();
                        if (!(n1.mInstant<n2.mInstant)) {
                            break;
                        }
                    }
                } else if (n2.mInstant<n1.mInstant) {
                    while (i2.hasNext()) {
                        n2 = i2.next();
                        if (!(n2.mInstant<n1.mInstant)) {
                            break;
                        }
                    }
                }
            }

            if (n2.mInstant.equals(n1.mInstant)) {
                newEntries.add(new Entry<Double>(n1.mT/n2.mT, n1.mInstant));
            }
        }

        return new DoubleSeries(newEntries, mName);
    }
    
    public DoubleSeries div(double other) {
    	check(this.isAscending());

        Iterator<Entry<Double>> i1 = this.iterator();

        List<Entry<Double>> newEntries = new ArrayList<>();

        while (i1.hasNext()) {
            Entry<Double> n1 = i1.next();
            newEntries.add(new Entry<Double>(n1.mT/other, n1.mInstant));
        }

        return new DoubleSeries(newEntries, mName);
    }
    
    public DoubleSeries returns() {
        return this.div(lag(1)).plus(-1);
    }

    public double getLast() {
        return getData().get(size() - 1).getItem();
    }

    public DoubleSeries tail(int n) {
        return new DoubleSeries(getData().subList(size() - n, size()), getName());
    }

    public DoubleSeries returns(int days) {
        return this.div(lag(days)).plus(-1);
    }

    public double[] toArray() {
    	double[] res = new double[mData.size()];
    	for(int i = 0; i < mData.size(); i++)
    		res[i] = mData.get(i).getItem();
        return res;
    }

    @Override public DoubleSeries toAscending() {
        return new DoubleSeries(super.toAscending().mData, getName());
    }

    @Override public DoubleSeries toDescending() {
        return new DoubleSeries(super.toDescending().mData, getName());
    }

    @Override public DoubleSeries lag(int k) {
        return new DoubleSeries(super.lag(k).mData, getName());
    }
    
    public DoubleSeries subSeries(int l, int r) throws Exception{
    	if(l<0 || r>size())
    		throw new Exception("l<0 || r>size()");
    	List<Entry<Double>> newEntries = new ArrayList<>();
    	for(int i = l; i < r; i++){
    		newEntries.add(new Entry<Double>(mData.get(i).getItem(), this.mData.get(i).getInstant()));
    	}
    	return new DoubleSeries(newEntries, mName);
    }
    
    public int getIdxFromInstant(Long mInstant){
    	for(int i = 0; i < this.size(); i++){
    		if(mInstant == this.get(i).getInstant())
    			return i;
    	}
    	return -1;
    }
    
    public double sqrtSum(){
    	double sum = 0;
    	for(int i = 0; i < this.size(); i++){
    		sum += Math.pow(this.getData().get(i).getItem(), 2);
    	}
    	return sum/this.size();
    }

    public double min(){
    	double min_value = Double.MAX_VALUE;
    	for(int i = 0; i < this.size(); i++){
    		min_value = Math.min(min_value, this.getData().get(i).getItem());
    	}
    	return min_value;
    }
    
    public double max(){
    	double max_value = Double.MIN_VALUE;
    	for(int i = 0; i < this.size(); i++){
    		max_value = Math.max(max_value, this.getData().get(i).getItem());
    	}
    	return max_value;
    }
    
    public double kth(int k) {
    	double l = min(), r = max();
    	for(int iter = 0; iter < 50; iter++){
    		double k_value = (l+r)/2.0;
    		int num = 0;
    		for(int i = 0; i < this.size(); i++){
        		if(k_value > this.getData().get(i).getItem())
        			num++;
        	}
    		if(num < k) l = k_value;
    		else r = k_value;
    	}
    	return (l+r)/2.0;
    }
    
    @Override public String toString() {
        return mData.isEmpty() ? "DoubleSeries{empty}" :
            "DoubleSeries{" +
                "mName=" + mName +
                ", from=" + mData.get(0).getInstant() +
                ", to=" + mData.get(mData.size() - 1).getInstant() +
                ", size=" + mData.size() +
                '}';
    }
}

