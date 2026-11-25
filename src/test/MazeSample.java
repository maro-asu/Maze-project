package src.test;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MazeSample extends JFrame
{
	public MazeSample()
	{
		setTitle("Maze Game");
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		MyJPanel myJPanel = new MyJPanel();
		Container c = getContentPane();
		c.add(myJPanel);
		pack();                      // パネルの大きさに合わせてフレームを調整
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(() -> new MazeSample());
	}

	public class MyJPanel extends JPanel implements KeyListener
	{
		// 0=通路, 1=壁, 2=ゴール
		int[][] maze = {
			{1,1,1,1,1,1,1,1,1,1},
			{1,0,0,0,1,0,0,0,0,1},
			{1,0,1,0,1,0,1,1,0,1},
			{1,0,1,0,0,0,0,1,0,1},
			{1,0,1,1,1,1,0,1,0,1},
			{1,0,0,0,0,1,0,0,0,1},
			{1,1,1,1,0,1,1,1,0,1},
			{1,0,0,0,0,0,0,1,0,1},
			{1,0,1,1,1,1,0,0,2,1},
			{1,1,1,1,1,1,1,1,1,1}
		};

		final int SIZE = 40;     // 1マスの大きさ(px)
		int px = 1, py = 1;      // プレイヤーの位置(列, 行)
		boolean clear = false;

		public MyJPanel()
		{
			setPreferredSize(new Dimension(maze[0].length * SIZE, maze.length * SIZE));
			setFocusable(true);
			addKeyListener(this);
		}

		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);

			// 迷路の描画
			for (int row = 0; row < maze.length; row++)
			{
				for (int col = 0; col < maze[row].length; col++)
				{
					if (maze[row][col] == 1)      g.setColor(Color.BLACK);
					else if (maze[row][col] == 2) g.setColor(Color.GREEN);
					else                          g.setColor(Color.WHITE);
					g.fillRect(col * SIZE, row * SIZE, SIZE, SIZE);
				}
			}

			// プレイヤーの描画
			g.setColor(Color.RED);
			g.fillOval(px * SIZE + 5, py * SIZE + 5, SIZE - 10, SIZE - 10);

			// クリア表示
			if (clear)
			{
				g.setColor(Color.BLUE);
				g.setFont(new Font("Century", Font.BOLD, 48));
				g.drawString("CLEAR!", 100, 210);
			}
		}

		public void keyPressed(KeyEvent e)
		{
			if (clear) return;

			int nx = px, ny = py;
			switch (e.getKeyCode())
			{
				case KeyEvent.VK_UP:    ny--; break;
				case KeyEvent.VK_DOWN:  ny++; break;
				case KeyEvent.VK_LEFT:  nx--; break;
				case KeyEvent.VK_RIGHT: nx++; break;
				default: return;
			}

			// 壁でなければ移動
			if (maze[ny][nx] != 1)
			{
				px = nx;
				py = ny;
				if (maze[py][px] == 2) clear = true;
				repaint();
			}
		}

		public void keyReleased(KeyEvent e){}
		public void keyTyped(KeyEvent e){}
	}
}
