public interface Language {
    void loadQuestion(int index);
    void checkAnswer();
    void showResult();

    void showInputResultDialog();

    void resetQuiz();

    void updateQuestionContent();

}
