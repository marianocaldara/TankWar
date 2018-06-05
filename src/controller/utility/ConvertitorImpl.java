package controller.utility;

import model.utility.Pair;

/**
 * Concrete implementation of {@link Convertitor}.
 */
public class ConvertitorImpl implements Convertitor{
	private final Pair<Double, Double> modelDimension;
	private Pair<Double, Double> viewDimension;
	
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
	public void setViewDimension(Pair<Double, Double> viewDimension) {
		this.viewDimension = viewDimension;		
	}
		
	@Override
	public Pair<Double, Double> viewToModel(Pair<Double, Double> position){
		return this.convertitor(position, this.modelDimension, this.viewDimension);
	}
	
	@Override
	public Pair<Double, Double> modelToView(Pair<Double, Double> position){
		return this.convertitor(position, this.viewDimension, this.modelDimension);
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
	private Pair<Double, Double> convertitor(Pair<Double, Double> toConvert, Pair<Double, Double> firstDimension, Pair<Double, Double> secondDimension){
		return new Pair<>(toConvert.getFirst() * firstDimension.getFirst() / secondDimension.getFirst(), 
				toConvert.getSecond() * firstDimension.getSecond() / secondDimension.getSecond());		
	}	
	
}
