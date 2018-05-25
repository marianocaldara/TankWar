package model.utility;

/**
 * Generic class to calculate main operation like distance, probabilty.
 */
public class Calculate {
    /**
     * Calculate the distance between two points.
     * @param pos1
     *          first point
     * @param pos2
     *          second point
     * @return the distance in double
     * 
     * @see Pair
     */
    static public double distance(Pair<Double, Double> pos1, Pair<Double, Double> pos2) {
        return Math.sqrt(Math.pow(pos1.getFirst() - pos2.getFirst(), 2) + Math.pow(pos1.getSecond() - pos2.getSecond(), 2));
    }
    /**
     * 
     * @return
     */
    static public double probability() {
        return Math.random() * 100;
    }
    /**
     * Return angle between two points
     * @param pos0
     *          first position
     * @param pos1
     *          second position
     * @return the angle in degrees
     * 
     * @see Pair
     */
    static public double angle(Pair<Double, Double> pos0, Pair<Double, Double> pos1) {
        double angle =  Math.toDegrees(Math.atan2(pos1.getSecond() - pos0.getSecond(), pos1.getFirst() - pos0.getFirst()));
        
        if(angle < 0){
                angle += 360;
        }
        
        return angle;
}
}
