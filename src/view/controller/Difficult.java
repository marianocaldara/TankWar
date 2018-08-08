package view.controller;

/*
 * Enumeration that describe the difficult of the game.
 * The difficult depend to the frequency of shot of the enemy tank.
 */
public enum Difficult {

    EASY("Easy") {

        @Override
        public double getTimeShot() {
            return 3000;
        }

    },

    MEDIUM("Medium") {

        @Override
        public double getTimeShot() {
            return 1000;
        }

    },

    HARD("Hard") {

        @Override
        public double getTimeShot() {
            return 500;
        }

    };

    private String difficultName;

    /**
     * Private constructor.
     * 
     * @param difficultName
     *            the name of the difficult.
     */

    private Difficult(String difficultName) {
        this.difficultName = difficultName;
    }

    /**
     * Getter of the difficult name.
     * 
     * @return the difficult name.
     */
    public String getName() {
        return this.difficultName;
    }

    /**
     * Getter of the frequency time of shot of the enemy {@link Tank}.
     * 
     * @return the period of shot in milliseconds.
     */
    abstract public double getTimeShot();

}
