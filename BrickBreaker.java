import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BrickBreaker extends JPanel implements KeyListener, ActionListener {

    private boolean play = true;
    private int score = 0;
    private int totalBricks = 21;

    private Timer timer;
    private int delay = 8;

    private int playerX = 310;
    private int ballX = 120;
    private int ballY = 350;
    private int ballDirX = -1;
    private int ballDirY = -2;

    private BrickMap map;

    public BrickBreaker() {
        map = new BrickMap(3,7);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
    }

    public void paint(Graphics g) {
        // Background
        g.setColor(new Color(245,245,245));
        g.fillRect(1, 1, 692, 592);

        // Draw bricks
        map.draw((Graphics2D) g);

        // Borders
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, 3, 592);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(691, 0, 3, 592);

        // Paddle
        g.setColor(new Color(34,139,34));
        g.fillRect(playerX, 550, 100, 10);

        // Ball
        g.setColor(Color.RED);
        g.fillOval(ballX, ballY, 20, 20);

        // Score
        g.setColor(Color.BLUE);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.drawString("Score: " + score, 590, 30);

        // Game Over
        if(ballY > 570) {
            play = false;
            ballDirX = 0;
            ballDirY = 0;
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 30));
            g.drawString("Game Over! Score: " + score, 150, 300);

            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("Press Enter to Restart", 200, 350);
        }

        // Win
        if(totalBricks <= 0) {
            play = false;
            ballDirX = 0;
            ballDirY = 0;
            g.setColor(Color.GREEN);
            g.setFont(new Font("Arial", Font.BOLD, 30));
            g.drawString("🎉 You Won! Score: " + score, 150, 300);

            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("Press Enter to Restart", 200, 350);
        }

        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if(play) {
            // Ball-paddle collision
            if(new Rectangle(ballX, ballY, 20, 20).intersects(new Rectangle(playerX, 550, 100, 10))) {
                ballDirY = -ballDirY;
            }

            // Ball-brick collision
            A: for(int i=0; i<map.map.length; i++){
                for(int j=0; j<map.map[0].length; j++){
                    if(map.map[i][j] > 0){
                        int brickX = j*map.brickWidth + 80;
                        int brickY = i*map.brickHeight + 50;
                        int brickWidth = map.brickWidth;
                        int brickHeight = map.brickHeight;

                        Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
                        Rectangle ballRect = new Rectangle(ballX, ballY, 20, 20);

                        if(ballRect.intersects(rect)){
                            map.setBrickValue(0, i, j);
                            totalBricks--;
                            score += 5;

                            if(ballX + 19 <= rect.x || ballX + 1 >= rect.x + rect.width){
                                ballDirX = -ballDirX;
                            } else {
                                ballDirY = -ballDirY;
                            }
                            break A;
                        }
                    }
                }
            }

            ballX += ballDirX;
            ballY += ballDirY;

            if(ballX < 0) ballDirX = -ballDirX;
            if(ballY < 0) ballDirY = -ballDirY;
            if(ballX > 670) ballDirX = -ballDirX;

            repaint();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            if(playerX >= 600) playerX = 600;
            else playerX += 20;
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            if(playerX <= 10) playerX = 10;
            else playerX -= 20;
        }
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            if(!play){
                play = true;
                ballX = 120;
                ballY = 350;
                ballDirX = -1;
                ballDirY = -2;
                playerX = 310;
                score = 0;
                totalBricks = 21;
                map = new BrickMap(3,7);

                repaint();
            }
        }
    }

    @Override public void keyReleased(KeyEvent e){}
    @Override public void keyTyped(KeyEvent e){}

    public static void main(String[] args){
        JFrame frame = new JFrame("🎮 Brick Breaker Game");
        BrickBreaker gamePlay = new BrickBreaker();
        frame.setBounds(10,10,700,600);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(gamePlay);
        frame.setVisible(true);
    }
}

// BrickMap class
class BrickMap{
    public int[][] map;
    public int brickWidth;
    public int brickHeight;

    public BrickMap(int row, int col){
        map = new int[row][col];
        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[0].length;j++){
                map[i][j] = 1;
            }
        }
        brickWidth = 540/col;
        brickHeight = 150/row;
    }

    public void draw(Graphics2D g){
        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[0].length;j++){
                if(map[i][j] > 0){
                    g.setColor(new Color(70,130,180));
                    g.fillRect(j*brickWidth + 80, i*brickHeight + 50, brickWidth, brickHeight);

                    g.setStroke(new BasicStroke(3));
                    g.setColor(Color.BLACK);
                    g.drawRect(j*brickWidth + 80, i*brickHeight + 50, brickWidth, brickHeight);
                }
            }
        }
    }

    public void setBrickValue(int value, int row, int col){
        map[row][col] = value;
    }
}
