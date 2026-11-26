package src.Hina1126;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import java.awt.Image;

public class Hina1126 extends JFrame
{
	public Hina1126()
	{
		setSize(1000, 500);		//最初のフレームサイズ
		setTitle("Java Example");	//タイトル
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		//コンテナを取得してパネルを貼る
		MyJPanel myJPanel = new MyJPanel();
		Container c = getContentPane();	//コンテナの取得
		c.add(myJPanel);		//パネルを貼る
		setVisible(true);
		setResizable(false);
	}

	/***************** main ****************************/
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(() -> new Hina1126());
	}

	/********************************************************
		MyJPanel:パネル
	*********************************************************/
	public class MyJPanel extends JPanel implements ActionListener
	{
		Image ima1,ima2;
		int width, height;
		int motodata[];
		int newdata[];
		/************ コンストラクタ ****************/
		public MyJPanel()
		{
			ima1   = new ImageIcon("sample2.jpg").getImage();
			width  = ima1.getWidth(this);
			height = ima1.getHeight(this);
			motodata = new int[width*height];
			newdata = new int[width*height];
			PixelGrabber pg = new PixelGrabber(ima1, 0, 0, width, height, motodata, 0, width);
			try
			{
				pg.grabPixels();
			}
			catch (InterruptedException e)
			{ System.out.println("エラー"); }
		}

		/*************** paintComponent *******************/
		public void paintComponent(Graphics g)
		{
			int i;
			int red, green, blue, temp;
			g.drawImage(ima1, 0, 0, this);
			for (i = 0; i < width * height; i++)
			{
				red = (0xff & (motodata[i] >> 16));
				green = (0xff & (motodata[i] >> 8));
				blue = (0xff & motodata[i]);
				temp = (red + green + blue)/3; //他の方法もある
				newdata[i] = (0xff000000 | temp << 16 | temp << 8 | temp);
			}
			ima2 = createImage(new MemoryImageSource(width, height, newdata, 0, width));
			g.drawImage(ima2, width, 0, this);

		}


		/* イベント発生ごとに呼び出される */
		public void actionPerformed(ActionEvent e)
		{

		}

     }
}