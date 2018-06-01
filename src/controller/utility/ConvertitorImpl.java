package controller.utility;

import model.utility.Pair;

/**
 * Concrete implementation of {@link Convertitor}.
 */
public class ConvertitorImpl implements Convertitor{
	private final Pair<Double, Double> modelDimension;
	private final Pair<Double, Double> viewDimension;
	
	/**
	 * Constructor.
	 * @param modelDimension
	 * 		the dimension of the model like a {@link Pair} of width and height.
	 * @param viewDimension
	 * 		the dimension of the view like a {@link Pair} of width and height.
	 */
	public ConvertitorImpl(Pair<Double, Double> modelDimension, Pair<Double, Double> viewDimension) {
		this.modelDimension = modelDimension;
		this.viewDimension = viewDimension;
	}
	
	@Override
	public Pair<Double, Double> viewToModelPosition(Pair<Double, Double> position){
		return this.convertitor(position, new Pair<>(this.modelDimension, this.viewDimension));
	}
	
	@Override
	public Pair<Double, Double> modelToViewPosition(Pair<Double, Double> position){
		return this.convertitor(position, new Pair<>(this.viewDimension, this.modelDimension));
	}
	
	@Override
	public Pair<Double, Double> viewToModelDimension(Pair<Double, Double> dimension){
		return this.convertitor(dimension, new Pair<>(this.modelDimension, this.viewDimension));
	}
	
	@Override
	public Pair<Double, Double> modelToViewDimension(Pair<Double, Double> dimension){
		return this.convertitor(dimension, new Pair<>(this.viewDimension, this.modelDimension));
	}	
	
	/**
	 * Convertitor of the position and dimension of an object.
	 * @param toConvert
	 * 		the object to convert.
	 * @param dimension
	 * 		the model and view dimension.
	 * @return the converted object.
	 */
	private Pair<Double, Double> convertitor(Pair<Double, Double> toConvert, Pair<Pair<Double, Double>, Pair<Double, Double>> dimension){
		return new Pair<>(toConvert.getFirst() * dimension.getFirst().getFirst() / dimension.getSecond().getFirst(), 
				toConvert.getSecond() * dimension.getFirst().getFirst() / dimension.getSecond().getSecond());		
	}
	
	
	
	
}
