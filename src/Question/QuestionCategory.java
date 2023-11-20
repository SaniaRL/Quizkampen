package Question;

public enum QuestionCategory {

        MOVIES("Film"),
        HISTORY("Historia"),
        SPORT("Sport"),
        MUSIC("Musik"),
        ANIMALS("Djur"),
        LITERATURE("Litteratur"),
        TECHNOLOGY("Teknologi"),
        SPACE("Rymden");

        public final String label;

        QuestionCategory(String label){
                this.label = label;
        }

        public static QuestionCategory getQuestionCategory(String category){
                switch (category){
                        case "Film" -> {
                                return MOVIES;
                        }
                        case "Historia" -> {
                                return HISTORY;
                        }
                        case "Sport" -> {
                                return SPORT;
                        }
                        case "Musik" -> {
                                return MUSIC;
                        }
                        case "Djur" -> {
                                return ANIMALS;
                        }
                        case "Litteratur" -> {
                                return LITERATURE;
                        }
                        case "Teknologi" -> {
                                return TECHNOLOGY;
                        }
                        default -> {
                                return SPACE;
                        }
                }
        }
}
