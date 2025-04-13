import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class FlappyBird extends JPanel implements ActionListener, KeyListener {

    int frameWidth = 360;
    int frameHeight = 640;

    Image backgroundImage;
    Image birdImage;
    Image lowerPipeImage;
    Image upperPipeImage;

    // player
    int playerStartPosX = frameWidth / 8;
    int playerStartPosY = frameHeight / 2;
    int playerWidth = 34;
    int playerHeight = 24;

    Player player;

    // pipes attributes
    int pipeStartPosX = frameWidth;
    int pipeStartPosY = 0;
    int pipeWidth = 64;
    int pipeHeight = 512;
    ArrayList<Pipe> pipes;

    Timer gameLoop;
    Timer pipesCooldwon;
    int gravity = 1;
    private boolean isGameOver = false; // TAMBAHAN: status game over
    private JLabel scoreLabel; // TAMBAHAN
    private int score = 0; // TAMBAHAN

    // constructor
    public FlappyBird(){
        setPreferredSize(new Dimension(frameWidth,frameHeight));
        setFocusable(true);
        addKeyListener(this);
        // setBackground(Color.blue);

        backgroundImage = new ImageIcon(getClass().getResource("assets/background.png")).getImage();
        birdImage = new ImageIcon(getClass().getResource("assets/bird.png")).getImage();
        lowerPipeImage = new ImageIcon(getClass().getResource("assets/lowerPipe.png")).getImage();
        upperPipeImage = new ImageIcon(getClass().getResource("assets/upperPipe.png")).getImage();

        player = new Player(playerStartPosX, playerStartPosY, playerWidth, playerHeight, birdImage);
        pipes = new ArrayList<Pipe>();

        pipesCooldwon = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                System.out.println("pipa");
                placePipes();
            }
        });
        pipesCooldwon.start();

        setLayout(null); // TAMBAHAN: untuk posisi absolut

        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setBounds(10, 10, 200, 30); // TAMBAHAN: atur posisi label
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(scoreLabel); // TAMBAHAN: pastikan panel menggunakan layout yang sesuai

        gameLoop = new Timer(1000/60, this);
        gameLoop.start();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    // TAMBAHAN: method cek tabrakan
    private boolean checkCollision() {
        Rectangle playerRect = new Rectangle(player.getPosX(), player.getPosY(),
                player.getWidth(), player.getHeight());
        for (Pipe pipe : pipes) {
            Rectangle pipeRect = new Rectangle(pipe.getPosX(), pipe.getPosY(),
                    pipe.getWidth(), pipe.getHeight());
            if (playerRect.intersects(pipeRect)) return true;
        }
        return false;
    }

    public void draw(Graphics g){
        g.drawImage(backgroundImage, 0, 0, frameWidth, frameHeight, null);
        g.drawImage(player.getImage(), player.getPosX(), player.getPosY(), player.getWidth(), player.getHeight(), null);

        for(int i = 0; i < pipes.size(); i++){
            Pipe pipe = pipes.get(i);
            g.drawImage(pipe.getImage(), pipe.getPosX(), pipe.getPosY(), pipe.getWidth(), pipe.getHeight(), null);
        }
    }

    public void move(){
        if (isGameOver) return; // TAMBAHAN: hentikan gerakan

        player.setVelocityY(player.getVelocityY() + gravity);
        player.setPosY(player.getPosY() + player.getVelocityY());
        player.setPosY(Math.max(player.getPosY(), 0));

        for (int i = 0; i < pipes.size() ; i++) {
            Pipe pipe = pipes.get(i);
            pipe.setPosX(pipe.getPosX() + pipe.getVelocityX());

            // TAMBAHAN: cek jika pemain melewati pipa
            if (!pipe.isPassed() && pipe.getPosX() + pipe.getWidth() < player.getPosX()) {
                score++;
                scoreLabel.setText("Score: " + score);
                pipe.setPassed(true);
            }
        }



        // TAMBAHAN: cek jatuh atau tabrakan
        if (player.getPosY() + player.getHeight() >= frameHeight || checkCollision()) {
            isGameOver = true;
            gameLoop.stop();
            pipesCooldwon.stop();
        }
    }

    public void placePipes(){
        int randomPosY = (int) (pipeStartPosY - pipeHeight/4 - Math.random() * (pipeHeight/2));
        int openingSpace = frameHeight/4;

        Pipe upperPipe = new Pipe(pipeStartPosX, randomPosY, pipeWidth, pipeHeight, upperPipeImage);
        pipes.add(upperPipe);

        Pipe lowerpipe = new Pipe(pipeStartPosX, (randomPosY + openingSpace + pipeHeight), pipeWidth, pipeHeight, lowerPipeImage);
        pipes.add(lowerpipe);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        move();
        repaint();
    }

    // Di method keyPressed()
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE && !isGameOver) {
            player.setVelocityY(-10);
        }
        // TAMBAHAN: restart game
        if (e.getKeyCode() == KeyEvent.VK_R && isGameOver) {
            isGameOver = false;
            player.setPosY(playerStartPosY);
            player.setVelocityY(0);
            pipes.clear();
            score = 0;
            scoreLabel.setText("Score: 0"); // TAMBAHAN: reset skor
            gameLoop.start();
            pipesCooldwon.start();
        }
    }

    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}
}
