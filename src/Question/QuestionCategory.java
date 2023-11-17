package Question;

public enum QuestionCategory {

        MOVIES("Film"),
        HISTORY("Historia"),
        SPORT("Sport"),
        MUSIC("Musik"),
        ANIMALS("Djur och Natur"),
        LITERATURE("Litteratur"),
        TECHNOLOGY("Teknologi"),
        SPACE("Rymden");

        public final String label;

        QuestionCategory(String label){
                this.label = label;
        }
}