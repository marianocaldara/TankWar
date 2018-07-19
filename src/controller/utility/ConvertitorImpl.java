package controller.utility;

import model.Model;
import model.utility.Pair;
import view.ViewMain;

/**
 * Concrete implementation of {@link Convertitor}.
 */
public class ConvertitorImpl implements Convertitor{
	private final Model world;
	private final ViewMain view;
	
	/**
	 * Constructor.
	 * @param world
	 * 			the game {@link World}.
	 * @param view
	 * 			the game {@link MainView}.
	 */
	public ConvertitorImpl(Model world, ViewMain view) {
		this.world = world;
		this.view = view;
	}
	

	@Override
	public Pair<Double, Double> viewToModel(Pair<Double, Double> position){
		return this.convertitor(position, this.world.getBounds(), this.view.getBounds());
	}
	
	@Override
	public Pair<Double, Double> modelToView(Pair<Double, Double> position){
		return this.convertitor(position, this.view.getBounds(), this.world.getBounds());
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
