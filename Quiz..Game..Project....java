
//========================================='''Simple project'''===========================================
// Title--------------------------: OOP QUIZ MASTER – Interactive Quiz Game---------------------------------
//---------------------------Designed by Sonia-khan:--------------------------------

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// ===== Abstraction =====
abstract class Quiz {
    abstract void startQuiz();
}

// ===== Encapsulation =====
class Question {
    private String question;
    private String[] options;
    private int correctIndex;

    public Question(String question, String[] options, int correctIndex) {
        this.question = question;
        this.options = options;
        this.correctIndex = correctIndex;
    }

    public String getQuestion() { return question; }
    public String[] getOptions() { return options; }
    public int getCorrectIndex() { return correctIndex; }
}

// ===== Inheritance =====
class OOPQuiz extends Quiz {
    protected Question[] questions;
    protected int current = 0;
    protected int score = 0;
    protected JFrame frame;
    protected JLabel questionLabel;
    protected JRadioButton[] options;
    protected JButton nextButton;
    protected ButtonGroup group;

    public OOPQuiz() {
        questions = new Question[] {
            new Question("1. What does OOP stand for?", new String[]{"Object-Oriented Programming", "Open Object Processing", "Optical Operation Program", "None"}, 0),
            new Question("2. Which is not a pillar of OOP?", new String[]{"Encapsulation", "Polymorphism", "Iteration", "Abstraction"}, 2),
            new Question("3. Which keyword is used to inherit a class in Java?", new String[]{"super", "import", "extends", "inherits"}, 2),
            new Question("4. Which method shows Polymorphism?", new String[]{"Method Overloading", "Looping", "Switch Statement", "Variables"}, 0),
            new Question("5. Which concept hides the data?", new String[]{"Encapsulation", "Inheritance", "Abstraction", "Interface"}, 0)
        };
    }

    // ===== Polymorphism (method overriding) =====
    @Override
    void startQuiz() {
        frame = new JFrame("OOP QUIZ MASTER - By Sonia, Mishal & Quratulain");
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        questionLabel = new JLabel("", SwingConstants.CENTER);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 18));
        frame.add(questionLabel, BorderLayout.NORTH);

        JPanel optionsPanel = new JPanel(new GridLayout(4, 1));
        options = new JRadioButton[4];
        group = new ButtonGroup();
        for (int i = 0; i < 4; i++) {
            options[i] = new JRadioButton();
            options[i].setFont(new Font("Arial", Font.PLAIN, 16));
            group.add(options[i]);
            optionsPanel.add(options[i]);
        }
        frame.add(optionsPanel, BorderLayout.CENTER);

        nextButton = new JButton("Next");
        nextButton.setFont(new Font("Arial", Font.BOLD, 16));
        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkAnswer();
                current++;
                if (current < questions.length) showQuestion();
                else showResult();
            }
        });
        frame.add(nextButton, BorderLayout.SOUTH);

        showQuestion();
        frame.setVisible(true);
    }

    void showQuestion() {
        Question q = questions[current];
        questionLabel.setText(q.getQuestion());
        String[] opts = q.getOptions();
        for (int i = 0; i < 4; i++) options[i].setText(opts[i]);
        group.clearSelection();
    }

    void checkAnswer() {
        int selected = -1;
        for (int i = 0; i < 4; i++) {
            if (options[i].isSelected()) selected = i;
        }
        if (selected == questions[current].getCorrectIndex()) score++;
    }

    void showResult() {
        JOptionPane.showMessageDialog(frame, "Quiz Finished!\nYour Score: " + score + "/" + questions.length);
        frame.dispose();
    }
}

// ===== Main Class =====
public class QuizGame {
    public static void main(String[] args) {
        new OOPQuiz().startQuiz();
    }
}
