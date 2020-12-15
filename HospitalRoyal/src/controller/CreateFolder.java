package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;

import view.CreateFolderView;

public class CreateFolder {

	public CreateFolder(FTPClient client) {
		CreateFolderView createView = new CreateFolderView();
		createView.setVisible(true);
		createView.pack();
		createView.getBtnCreate().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String folder = "/" + createView.getTxtNameFolder().getText();
				try {
					Boolean success = client.makeDirectory(folder);
					if (success) {
						createView.getLblMessage().setText("Successfully created directory: " + folder);
						createView.dispose();
					} else {
						createView.getLblMessage().setText("Failed to create directory");
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
}