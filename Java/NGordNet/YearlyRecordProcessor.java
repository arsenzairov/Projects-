package ngordnet;

/**
 * Created by Arsen on 10/18/17.
 */
public interface YearlyRecordProcessor {
    /** Returns some feature of a YearlyRecord as a double. */
    double process(YearlyRecord yearlyRecord);
}

