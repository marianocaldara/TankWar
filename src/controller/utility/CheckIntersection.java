package controller.utility;

import model.utility.Pair;

/**
 * Utility class to check the intersection between two objects
 */
public class CheckIntersection {

    /**
     * Control if two objects intersect each other.
     * 
     * @param positionFirst
     *            the position of the first object.
     * @param dimensionFirst
     *            the dimension of the first object.
     * @param positionSecond
     *            the position of the second object.
     * @param dimensionSecond
     *            the dimension of the second object.
     * @return true if the two objects intersect, false otherwise.
     */
    public static boolean intersects(Pair<Double, Double> positionFirst, Pair<Double, Double> dimensionFirst,
            Pair<Double, Double> positionSecond, Pair<Double, Double> dimensionSecond) {
        if (positionFirst == null || dimensionFirst == null || positionSecond == null || dimensionSecond == null) {
            return false;
        }
        return positionFirst.getFirst() + dimensionFirst.getFirst() > positionSecond.getFirst()
                && positionFirst.getSecond() + dimensionFirst.getSecond() > positionSecond.getSecond()
                && positionFirst.getFirst() < positionSecond.getFirst() + dimensionSecond.getFirst()
                && positionFirst.getSecond() < positionSecond.getSecond() + dimensionSecond.getSecond();
    }

}
