import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class German extends JFrame implements Language {
    private JLabel questionLabel;
    private JRadioButton option1, option2, option3, option4;
    private JButton submitButton;
    private int currentQuestionIndex = 0;
    private int score = 0;

    private String[][] questions = {
            {"Ich schenke ___ Schnaps ein.", "mir", "mich", "dich", "euch"},
            {"Verarschest du ____?", "mich", "mir", "ich", "zu mir"},
            {"Oberdunaudampfschiffahrtelektrizitaetenbetriebswerkbauunterbeamten____________", "gesellschaft", "zapfen", "kapuzinerkresse", "katze"}
    };

    private String [][] answers={
            {"mir"},
            {"mich"},
            {"gesellschaft"}
    };

    public German() {
        setTitle("Language Quiz");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 300);
        setLayout(new BorderLayout());
        setLocation(100, 300);

        JPanel questionPanel = new JPanel(new GridLayout(6, 1));
        questionLabel = new JLabel();
        questionPanel.add(questionLabel);

        ButtonGroup optionsGroup = new ButtonGroup();
        option1 = new JRadioButton();
        option2 = new JRadioButton();
        option3 = new JRadioButton();
        option4 = new JRadioButton();
        optionsGroup.add(option1);
        optionsGroup.add(option2);
        optionsGroup.add(option3);
        optionsGroup.add(option4);
        questionPanel.add(option1);
        questionPanel.add(option2);
        questionPanel.add(option3);
        questionPanel.add(option4);

        submitButton = new JButton("Submit");
        questionPanel.add(submitButton);

        add(questionPanel, BorderLayout.CENTER);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAnswer();
                currentQuestionIndex++;
                if (currentQuestionIndex < questions.length) {
                    loadQuestion(currentQuestionIndex);
                } else {
                    showResult();
                }
            }
        });

        loadQuestion(currentQuestionIndex);
    }

    @Override
    public void loadQuestion(int index) {
        questionLabel.setText(questions[index][0]);
        option1.setText(questions[index][1]);
        option2.setText(questions[index][2]);
        option3.setText(questions[index][3]);
        option4.setText(questions[index][4]);
        option1.setSelected(true);
    }

    @Override
    public void checkAnswer() {
        JRadioButton selectedOption = null;
        if (option1.isSelected()) {
            selectedOption = option1;
        } else if (option2.isSelected()) {
            selectedOption = option2;
        } else if (option3.isSelected()) {
            selectedOption = option3;
        } else if (option4.isSelected()) {
            selectedOption = option4;
        }

        if (selectedOption != null && selectedOption.getText().equals(answers[currentQuestionIndex][0])) {
            score++;
        }
    }

    @Override
    public void showResult() {
        setVisible(false);
        JOptionPane.showMessageDialog(this, "Quiz completed!\nYour score: " + score + "/" + questions.length);
        showInputResultDialog();
    }
    @Override
    public void showInputResultDialog() {
        String currentLanguage = "";
        String[] options = {"Quit", "Redo Quiz", "Switch Language"};
        int choice = JOptionPane.showOptionDialog(
                this,
                null, // No message text
                "Select what to happen",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]
        );
        String selectedLanguage = "";
        if (choice == 0) {
            System.exit(0); // Quit the application
        } else if (choice == 1) {
            // Redo the quiz
            setVisible(false); // Hide the current German quiz frame
            resetQuiz(); // Reset the quiz
            loadQuestion(0); // Load the first question
            updateQuestionContent(); // Update question content after reset
            setVisible(true); // Show the German quiz frame again

        } else if (choice == 2) {
            setVisible(false);
            String[] languages = {"Bulgarian", "German"};
            selectedLanguage = (String) JOptionPane.showInputDialog(this, "Choose a language: ",
                    "Language selection", JOptionPane.QUESTION_MESSAGE, null, languages, languages[0]);
        }
        if (selectedLanguage != null) {
            currentLanguage = selectedLanguage;
            if (currentLanguage.equals("Bulgarian")) {
                setVisible(false);
                Main.startBulgarianQuiz();
            } else {
                setVisible(false);
                Main.startGermanQuiz();
            }
        }
    }
    @Override
    public void resetQuiz () {
        currentQuestionIndex = 0;
        score = 0;
        loadQuestion(currentQuestionIndex);
        updateQuestionContent();
    }

    @Override
    public void updateQuestionContent() {
        questionLabel.setText(questions[currentQuestionIndex][0]);
        option1.setText(questions[currentQuestionIndex][1]);
        option2.setText(questions[currentQuestionIndex][2]);
        option3.setText(questions[currentQuestionIndex][3]);
        option4.setText(questions[currentQuestionIndex][4]);
        option1.setSelected(true);
    }
}