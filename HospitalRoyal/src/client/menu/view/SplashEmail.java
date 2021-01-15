package client.menu.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.geom.RoundRectangle2D;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import com.sun.awt.AWTUtilities;


public class SplashEmail extends JFrame{

	Thread splash = new Thread();
	private JPanel contentPane;
	JProgressBar progressBar = new JProgressBar();
	
	public SplashEmail() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("iconos\\logo.png"));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		this.setUndecorated(true);
		JLabel gif = new JLabel();
		gif.setIcon(new ImageIcon("iconos\\cargando.gif"));
		gif.setBorder(BorderFactory.createEmptyBorder());
		contentPane.add(gif,BorderLayout.EAST);
		AWTUtilities.setWindowOpacity (this,0.7f);
		pack();
		Shape forma = new RoundRectangle2D.Double(0, 0, this.getBounds().width, this.getBounds().height, 300, 300);
        AWTUtilities.setWindowShape(this, forma);
		setLocationRelativeTo(null);
		setVisible(true);
	}

}
