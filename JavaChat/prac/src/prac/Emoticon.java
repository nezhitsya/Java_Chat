package prac;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
// 프로필 사진 프레임
public class Emoticon extends JFrame {

	public static final String EMOTICONPATH = "Image/emoticon";
	private JPanel contentPane;
	JPanel panel;
	
	public Emoticon() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 380, 265);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(0, 0, 374, 226);
		
		contentPane.add(scrollPane);
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		scrollPane.setViewportView(panel);
		
		File path = new File(EMOTICONPATH);
		int numberOfEmoticon = path.list().length;
		int rowLength = numberOfEmoticon%3==0 ? numberOfEmoticon/3 : numberOfEmoticon/3+1; 
		panel.setLayout(new GridLayout(rowLength, 3, 0, 12));
		
		JLabel[] lblEmoticon = new JLabel[numberOfEmoticon];
		
		for (int i = 0; i < numberOfEmoticon; i++) {
			lblEmoticon[i] = new JLabel(getEmoticonImage(i));
			lblEmoticon[i].setForeground(Color.WHITE);
			lblEmoticon[i].setText(""+i);
			lblEmoticon[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent event) {
					JLabel emoticon = (JLabel) event.getSource();
					ChatPanel.sendEmoticon(Integer.parseInt(emoticon.getText()));
					dispose();
				}
			});
			panel.add(lblEmoticon[i]);
		}		
	}

	private ImageIcon getEmoticonImage(int index) {
		return new ImageIcon(new ImageIcon(EMOTICONPATH+"/icon"+index+".png").getImage().getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH));
	}
}
