package controller.utility;

import model.utility.Pair;

/**
 * Interface to convert the position and the dimension of {@link Model} object to 
 * the position and the dimension of the {@link View} and the other way around.
 *
 */
public interface Convertitor {

	/**
	 * Convert a position of {@link View} to a position of {@link Model}.
	 * @param position
	 * 		the position of the object.
	 * @return the modified position.
	 */
	Pair<Double, Double> viewToModelPosition(Pair<Double, Double> position);
	
	/**
	 * Convert a position of {@link Model} to a position of {@link View}.
	 * @param position
	 * 		the position of the object.
	 * @return the modified position.
	 */
	Pair<Double, Double> modelToViewPosition(Pair<Double, Double> position);
	
	/**
	 * Convert a dimension of {@link View} to a dimension of {@link Model}.
	 * @param dimension
	 * 		the dimension of the object.
	 * @return the modified dimension.
	 */
	Pair<Double, Double> viewToModelDimension(Pair<Double, Double> dimension);
	
	/**
	Convert a dimension of {@link Model} to a dimension of {@link View}.
	 * @param dimension
	 * 		the dimension of the object.
	 * @return the modified dimension.
	 */
	Pair<Double, Double> modelToViewDimension(Pair<Double, Double> dimension);
	
}
