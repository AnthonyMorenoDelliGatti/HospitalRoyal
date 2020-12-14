package view;

import java.awt.GridLayout;
import java.awt.TextField;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuView extends JFrame {

	GridLayout layout = new GridLayout(0, 1);
	JLabel labelInfo;
	JButton buttonUpload, buttonDownload, buttonCreate, buttonDelete, buttonRename;

	public MenuView() {
		super("HOSPITAL ROYAL - Menú");
		setLocation(300, 300);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLayout(layout);
		labelInfo = new JLabel("HOSPITAL ROYAL - Menú");
		add(labelInfo);
		buttonUpload = new JButton("Upload File");
		add(buttonUpload);
		buttonDownload = new JButton("Download File");
		add(buttonDownload);
		buttonCreate = new JButton("Create floder");
		add(buttonCreate);
		buttonDelete = new JButton("Delete folder");
		add(buttonDelete);
		buttonRename = new JButton("Rename file or folder");
		add(buttonRename);
		pack();
		setVisible(true);
	}

	public JButton getButtonUpload() {
		return buttonUpload;
	}

	public JButton getButtonDownload() {
		return buttonDownload;
	}

	public JButton getButtonCreate() {
		return buttonCreate;
	}

	public JButton getButtonDelete() {
		return buttonDelete;
	}

	public JButton getButtonRename() {
		return buttonRename;
	}

}
