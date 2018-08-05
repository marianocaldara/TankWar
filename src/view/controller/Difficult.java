package view.controller;

/*
 * Enum difficoltà: permette nei settings del menu di scegliere la difficoltà. Essa fa variare la frequenza di sparo del nemico.
 */
public enum Difficult {
	
	EASY{

		@Override
		public double getTimeShot() {
			return 1000;
		}
		
	},
	
	MEDIUM{

		@Override
		public double getTimeShot() {
			return 500;
		}
		
	},
	
	HARD{

		@Override
		public double getTimeShot() {
			return 300;
		}
		
	};
	
	abstract public double getTimeShot();

}
