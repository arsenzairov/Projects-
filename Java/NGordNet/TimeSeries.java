package ngordnet;

import java.util.*;

/**
 * Created by Arsen on 10/18/17.
 */
public class TimeSeries<T extends Number> extends TreeMap<Integer,T> {


    public Collection<Number> years() {
        Collection<Number> yearSet = new TreeSet<Number>();
        for (Integer key : this.keySet()) {
            yearSet.add(key);
        }
        return yearSet;
    }

    public Collection<Number> data() {
        Collection<Number> dataSet = new ArrayList<Number>();
        for (Number key : this.years()) {
            dataSet.add(this.get(key));
        }
        return dataSet;
    }

    /** Returns the quotient of this time series divided by the relevant value in ts.
     * If ts is missing a key in this time series, return an IllegalArgumentException. */
    public TimeSeries<Double> dividedBy(TimeSeries<? extends Number> ts) {
        TimeSeries<Double> tQuotient = new TimeSeries<Double>();
        for (Integer key: this.keySet()) {
            if (ts.get(key) == null) {
                throw new IllegalArgumentException();
            } else {
                Double q = this.get(key).doubleValue() / ts.get(key).doubleValue();
                tQuotient.put(key, q);
            }
        }
        return tQuotient;
    }



    /** Returns the sum of this time series with the given ts. The result is a
     * a Double time series */
    public TimeSeries<Double> plus(TimeSeries<? extends Number> ts) {
        TimeSeries<Double> tSum = new TimeSeries<Double>();
        if (ts == null || ts.keySet().size() == 0) {
            for (Integer key: this.keySet()) {
                tSum.put(key, this.get(key).doubleValue());
            }
            return tSum;
        } else if (this.keySet().size() == 0) {
            for (Integer key: ts.keySet()) {
                tSum.put(key, ts.get(key).doubleValue());
            }
            return tSum;
        } else {
            for (Integer key : ts.keySet()) {
                Double s = 0.0;
                if (!this.containsKey(key)) {
                    s = ts.get(key).doubleValue();
                } else {
                    s = this.get(key).doubleValue() + ts.get(key).doubleValue();
                }
                tSum.put(key, s);
            }
            for (Integer key : this.keySet()) {
                Double s = 0.0;
                if (!ts.containsKey(key)) {
                    s = this.get(key).doubleValue();
                    tSum.put(key, s);
                }
            }
        }
        return tSum;
    }
}
