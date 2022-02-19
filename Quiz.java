import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//This is a simple Java quiz where you will have 1 question and 4 options to choose  then go to the next question and at the end you will have summary of your quiz

public class Quiz implements ActionListener {

    String[] questions = {
            "Which company created java?",
            "What was Java originally called?",
            "Which year was Java created?",
            "Who is credited with creating Java?",
            "What is a String value in Java?",
            "What is a correct syntax to output \"Hello World\" in Java?",
            "How do you insert COMMENTS in Java code?"

    };

    String[][] options = {
            {"Sun Microsystems", "Starbuks", "Microsoft", "Alphabet"},
            {"Apple", "Latte", "Oak", "Koffing"},
            {"1989", "1996", "1972", "1492"},
            {"Steve Jobs", "Bill Gates", "James Gosling", "Mark Zuckerburg"},
            {"Object to store time","Object to store single digit","Object to store single char","Object to store single java string"},
            {"System.out.println(\"Hello World\");","echo(\"Hello World\");","print(\"Hello World\");","Console.WriteLine(\"Hello World\");"},
            {"/* This is a comment","// This is a comment","/ This is a comment","#/ This is a comment"}
    };

    char[] answers = {'A', 'C', 'B', 'C','D','A','B'};

    char guess;
    char answer;
    int index;
    int correct_guesses = 0;
    int total_questions = questions.length;
    int result;
    int seconds = 10;
    int width = 850;

    //frame to hold everything
    JFrame frame = new JFrame();

    //text field to hold the current question
    JTextField textField = new JTextField();

    //text area to hold the current question text
    JTextArea textArea = new JTextArea();

    //buttons for all the possible answers
    JButton buttonA = new JButton();
    JButton buttonB = new JButton();
    JButton buttonC = new JButton();
    JButton buttonD = new JButton();
    JButton restartButton = new JButton();

    //label to hold all the different answers
    JLabel answer_labelA = new JLabel();
    JLabel answer_labelB = new JLabel();
    JLabel answer_labelC = new JLabel();
    JLabel answer_labelD = new JLabel();

    //display the word Timer
    JLabel time_label = new JLabel();

    //display the seconds timer
    JLabel seconds_left = new JLabel();

    //this will appear after we calculate the results
    JTextField number_right = new JTextField();

    //what percentage you got on your score
    JTextField percentage = new JTextField();

    Timer timer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            seconds--;
            seconds_left.setText(String.valueOf(seconds));
            if (seconds <= 0) {
                displayAnswer();
            }
        }
    });


    public Quiz() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, 650);
        //background color for the frame
        frame.getContentPane().setBackground(new Color(50, 50, 50));
        frame.setLayout(null);
        frame.setResizable(false);
        //setBounds determine where the textfield will be + height and width
        textField.setBounds(0, 0, width, 50);
        textField.setBackground(new Color(25, 25, 25));
        textField.setForeground(new Color(25, 255, 25));
        textField.setFont(new Font("Lucida Sans", Font.BOLD, 30));
        textField.setBorder(BorderFactory.createBevelBorder(1));
        textField.setHorizontalAlignment(JTextField.CENTER);
        //the text in that textfield can`t be edited
        textField.setEditable(false);

        //textArea will display our question
        textArea.setBounds(0, 50, width, 50);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBackground(new Color(25, 25, 25));
        textArea.setForeground(new Color(25, 255, 25));
        textArea.setFont(new Font("MV Boli", Font.BOLD, 25));
        textArea.setBorder(BorderFactory.createBevelBorder(1));
        textArea.setEditable(false);


        buttonA.setBounds(20, 120, 80, 80);
        buttonA.setFont(new Font("Univers", Font.BOLD, 35));
        //dont let the button to be highlited
        buttonA.setFocusable(false);
        buttonA.addActionListener(this);
        buttonA.setText("A");

        buttonB.setBounds(20, 220, 80, 80);
        buttonB.setFont(new Font("Univers", Font.BOLD, 35));
        buttonB.setFocusable(false);
        buttonB.addActionListener(this);
        buttonB.setText("B");

        buttonC.setBounds(20, 320, 80, 80);
        buttonC.setFont(new Font("Univers", Font.BOLD, 35));
        buttonC.setFocusable(false);
        buttonC.addActionListener(this);
        buttonC.setText("C");

        buttonD.setBounds(20, 420, 80, 80);
        buttonD.setFont(new Font("Univers", Font.BOLD, 35));
        buttonD.setFocusable(false);
        buttonD.addActionListener(this);
        buttonD.setText("D");

        restartButton.setBounds(300, 450, 200, 100);
        restartButton.setFont(new Font("Univers", Font.BOLD, 30));
        restartButton.setFocusable(false);
        restartButton.addActionListener(this);
        restartButton.setText("Restart");

        answer_labelA.setBounds(125, 100, width, 100);
        answer_labelA.setBackground(new Color(50, 50, 50));
        answer_labelA.setForeground(new Color(25, 255, 0));
        answer_labelA.setFont(new Font("The Hand", Font.ITALIC, 35));

        answer_labelB.setBounds(125, 200, width, 100);
        answer_labelB.setBackground(new Color(50, 50, 50));
        answer_labelB.setForeground(new Color(25, 255, 0));
        answer_labelB.setFont(new Font("The Hand", Font.ITALIC, 35));

        answer_labelC.setBounds(125, 300, width, 100);
        answer_labelC.setBackground(new Color(50, 50, 50));
        answer_labelC.setForeground(new Color(25, 255, 0));
        answer_labelC.setFont(new Font("The Hand", Font.ITALIC, 35));

        answer_labelD.setBounds(125, 400, width, 100);
        answer_labelD.setBackground(new Color(50, 50, 50));
        answer_labelD.setForeground(new Color(25, 255, 0));
        answer_labelD.setFont(new Font("The Hand", Font.ITALIC, 35));

        seconds_left.setBounds(745, 525, 80, 80);
        seconds_left.setBackground(new Color(25, 25, 100));
        seconds_left.setForeground(new Color(255, 0, 0));
        seconds_left.setFont(new Font("Univers", Font.BOLD, 50));
        seconds_left.setBorder(BorderFactory.createBevelBorder(1));
        seconds_left.setOpaque(true);
        seconds_left.setHorizontalAlignment(JTextField.CENTER);
        seconds_left.setText(String.valueOf((seconds)));

        time_label.setBounds(730, 495, 100, 25);
        time_label.setBackground(new Color(50, 50, 50));
        time_label.setForeground(new Color(245, 0, 0));
        time_label.setFont(new Font("MV Boli", Font.BOLD, 16));
        time_label.setHorizontalAlignment(JTextField.CENTER);
        time_label.setText("TIMER >:D");

        number_right.setBounds(115, 150, 600, 100);
        number_right.setBackground(new Color(25, 25, 25));
        number_right.setForeground(new Color(25, 255, 0));
        number_right.setFont(new Font("Yu Mincho", Font.BOLD, 30));
        number_right.setBorder(BorderFactory.createBevelBorder(1));
        number_right.setHorizontalAlignment(JTextField.CENTER);
        number_right.setEditable(false);

        percentage.setBounds(115, 250, 600, 100);
        percentage.setBackground(new Color(25, 25, 25));
        percentage.setForeground(new Color(25, 255, 0));
        percentage.setFont(new Font("Yu Mincho", Font.BOLD, 30));
        percentage.setBorder(BorderFactory.createBevelBorder(1));
        percentage.setHorizontalAlignment(JTextField.CENTER);
        percentage.setEditable(false);


        frame.add(time_label);
        frame.add(seconds_left);
        frame.add(answer_labelA);
        frame.add(answer_labelB);
        frame.add(answer_labelC);
        frame.add(answer_labelD);
        frame.add(buttonA);
        frame.add(buttonB);
        frame.add(buttonC);
        frame.add(buttonD);
        frame.add(textArea);
        frame.add(textField);
        //set the frame to the center of the screen on starting
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        nextQuestion();

    }

    public void nextQuestion() {
        if (index >= total_questions) {
            results();
        } else {
            textField.setText("Question " + (index + 1));
            textArea.setText("                   "+questions[index]);
            answer_labelA.setText(options[index][0]);
            answer_labelB.setText(options[index][1]);
            answer_labelC.setText(options[index][2]);
            answer_labelD.setText(options[index][3]);
            timer.start();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //disable all other buttons when one is clicked
        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);

        if (e.getSource() == buttonA) {
            answer = 'A';
            if (answer == answers[index]) {
                correct_guesses++;
            }
        }

        if (e.getSource() == buttonB) {
            answer = 'B';
            if (answer == answers[index]) {
                correct_guesses++;
            }
        }

        if (e.getSource() == buttonC) {
            answer = 'C';
            if (answer == answers[index]) {
                correct_guesses++;
            }
        }

        if (e.getSource() == buttonD) {
            answer = 'D';
            if (answer == answers[index]) {
                correct_guesses++;
            }
        }
        displayAnswer();
    }

    public void displayAnswer() {
        timer.stop();
        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);

        if (answers[index] != 'A')
            answer_labelA.setForeground(new Color(255, 0, 0));
        if (answers[index] != 'B')
            answer_labelB.setForeground(new Color(255, 0, 0));
        if (answers[index] != 'C')
            answer_labelC.setForeground(new Color(255, 0, 0));
        if (answers[index] != 'D')
            answer_labelD.setForeground(new Color(255, 0, 0));


        //giving the user 2 sec time before flipping the answers back to green color
        Timer pause = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                answer_labelA.setForeground(new Color(25, 255, 0));
                answer_labelB.setForeground(new Color(25, 255, 0));
                answer_labelC.setForeground(new Color(25, 255, 0));
                answer_labelD.setForeground(new Color(25, 255, 0));

                answer = ' ';
                seconds = 10;
                seconds_left.setText(String.valueOf(seconds));
                buttonA.setEnabled(true);
                buttonB.setEnabled(true);
                buttonC.setEnabled(true);
                buttonD.setEnabled(true);
                index++;
                nextQuestion();
            }
        });
        //action performer is activated only once when entering the method
        pause.setRepeats(false);
        pause.start();

    }

    public void results() {
        index=0;
        frame.remove(buttonA);
        frame.remove(buttonB);
        frame.remove(buttonC);
        frame.remove(buttonD);
        frame.remove(seconds_left);
        frame.remove(time_label);
        frame.remove(textArea);
        frame.revalidate();
        frame.repaint();
        //NOT WORKING PROPERLY
        frame.add(restartButton);

        result = (int)((correct_guesses/(double)(total_questions)) * 100);
        textField.setText("RESULTS!");
        answer_labelA.setText("");
        answer_labelB.setText("");
        answer_labelC.setText("");
        answer_labelD.setText("");


        if (result<=25){
            number_right.setText("Sadly you need moore practice  "+"(" + correct_guesses + "/" + total_questions + ")");
        }else if(result>25&&result<80){
            number_right.setText("Very good , keep on practice  "+"(" + correct_guesses + "/" + total_questions + ")");
        }else {
            number_right.setText("Perfect!  "+"(" + correct_guesses + "/" + total_questions + ")");
        }

        percentage.setText("You are "+result + "%"+" correct!");

        frame.add(percentage);
        frame.add(number_right);

        new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource()==restartButton){
                    frame.dispose();
                    new Quiz();
                }
            }
        };
    }
}
