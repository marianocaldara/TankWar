package controller.utility;

import model.utility.Pair;

/**
 * Interface to convert the position and the dimension of {@link Model} object to 
 * the position and the dimension of the {@link View} and the other way around.
 *
 */
public interface Convertitor {
	
	/**
	 * Setter of the {@link View} dimension.
	 * @param viewDimension
	 * 		the dimension as a {@link Pair}.
	 */
	void setViewDimension(Pair<Double, Double> viewDimension);

	/**
	 * Convert position or dimension of {@link View} to a position or dimension of {@link Model}.
	 * @param position
	 * 		the position of the object.
	 * @return the new position.
	 */
	Pair<Double, Double> viewToModel(Pair<Double, Double> position);
	
	/**
	 * Convert position or dimension of {@link Model} to position or dimension of {@link View}.
	 * @param position
	 * 		the position of the object.
	 * @return the new position.
	 */
	Pair<Double, Double> modelToView(Pair<Double, Double> position);
	
}
