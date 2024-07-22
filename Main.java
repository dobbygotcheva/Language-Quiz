import javax.swing.*;
import javax.swing.JOptionPane;
public class Main extends JOptionPane {

    public static void main(String[] args) {
        Bulgarian bulgarianQuizPanel=new Bulgarian();
        German germanQuizPanel=new German();

        String[] languages = {"Bulgarian", "German"};
        int selectedLanguage = JOptionPane.showOptionDialog(
                null,
                "Select a language to start the quiz:",
                "Language Selection",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                languages,
                languages[0]
        );

        if (selectedLanguage == 0) {
            bulgarianQuizPanel.setVisible(true);
            germanQuizPanel.setVisible(false);
        } else if (selectedLanguage == 1) {
            bulgarianQuizPanel.setVisible(false);
            germanQuizPanel.setVisible(true);
        } else {
            // User canceled language selection or closed the dialog
            System.exit(0);
        }
    }

    public static void startGermanQuiz() {
        // Create and start the German quiz panel
        German germanQuiz = new German();
        germanQuiz.setVisible(true);
    }

    public static void startBulgarianQuiz() {
        // Create and start the Bulgarian quiz panel
        Bulgarian bulgarianQuiz = new Bulgarian();
        bulgarianQuiz.setVisible(true);
    }

    

}
