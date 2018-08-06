package view.controller;

/*
 * Enum difficoltà: permette nei settings del menu di scegliere la difficoltà. Essa fa variare la frequenza di sparo del nemico.
 */
public enum Difficult {
	
	EASY("Easy"){

		@Override
		public double getTimeShot() {
			return 1000;
		}
		
	},
	
	MEDIUM("Medium"){

		@Override
		public double getTimeShot() {
			return 500;
		}
		
	},
	
	HARD("Hard"){

		@Override
		public double getTimeShot() {
			return 300;
		}
		
	};
	
	private String difficultName;
	
	/**
	 * Private constructor.
	 * @param levelName
	 * 		the name of the level.
	 */
	
	private Difficult(String difficultName) {
		this.difficultName = difficultName;
	}
	
	/**
	 * Getter of the levels' name.
	 * @return the level's name.
	 */
	public String getName() {
		return this.difficultName;
	}
	
	abstract public double getTimeShot();

}
