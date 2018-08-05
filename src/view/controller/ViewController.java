package view.controller;

import controller.Controller;

/*
 * Classe astratta per i controller di view. E' una scelta obbligata altrimenti è impossibile farli tornare come tipi
 * di ritorno nei metodi.
 */
public abstract class ViewController {
	
	public abstract void init(Controller controller);

}
