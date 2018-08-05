package controller.utility;

import model.Model;
import model.utility.Pair;
import view.View;

/**
 * Utility class to convert from model dimension to view dimension.
 */
public class Convertitor{
	private static Model WORLD;
	private static View VIEW;	
	
	/**
	 * Initialize the fields.
	 * @param world
	 * 		the game {@link Model}.
	 * @param view
	 * 		the game {@link View}.
	 */
	public static void initialize(Model world, View view) {
		WORLD = world;
		VIEW = view;
	}
		
	/**
	 * Convert from the {@link View} dimension to the {@link Model} dimension.
	 * @param toConvert
	 * 			a {@link Pair} to convert.
	 * @return a {@link Pair} of the new values.
	 */
	public static Pair<Double, Double> viewToModel(Pair<Double, Double> toConvert){
		return convertitor(toConvert, WORLD.getBounds(), VIEW.getBounds());
	}
	
	/**
	 * Convert from the {@link Model} dimension to the {@link View} dimension.
	 * @param toConvert
	 * 			a {@link Pair} to convert.
	 * @return a {@link Pair} of the new values.
	 */
	public static Pair<Double, Double> modelToView(Pair<Double, Double> toConvert){
		return convertitor(toConvert, VIEW.getBounds(), WORLD.getBounds());
	}
	
	/**
	 * Convertitor of the position and dimension of an object.
	 * @param toConvert
	 * 		the object to convert.
	 * @param firstDimension
	 * 		the {@link Model} or the {@link View} dimension.
	 * @param secondDimension
	 * 		the {@link Model} or the {@link View} dimension.
	 * @return the converted object.
	 */
	private static Pair<Double, Double> convertitor(Pair<Double, Double> toConvert, Pair<Double, Double> firstDimension, Pair<Double, Double> secondDimension){
		return new Pair<>(toConvert.getFirst() * firstDimension.getFirst() / secondDimension.getFirst(), 
				(toConvert.getSecond() * firstDimension.getSecond() / secondDimension.getSecond()));		
	}	
	
}
