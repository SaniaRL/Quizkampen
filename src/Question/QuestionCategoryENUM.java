package Question;

public enum QuestionCategoryENUM {

        MOVIES("Film"),
        HISTORY("Historia"),
        SPORT("Sport"),
        MUSIC("Musik"),
        ANIMALS("Djur och Natur"),
        LITERATURE("Litteratur"),
        TECHNOLOGY("Teknologi"),
        SPACE("Rymden");

        public final String questionCategoryENUM;

        QuestionCategoryENUM(String questionCategoryENUM){this.questionCategoryENUM = questionCategoryENUM;}
}
