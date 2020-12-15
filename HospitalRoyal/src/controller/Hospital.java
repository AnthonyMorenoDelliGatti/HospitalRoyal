package controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Hospital {

	public Hospital() {
		
	}
	public synchronized static void UploadFile(Socket client) {
		BufferedInputStream bis;
		BufferedOutputStream bos;
		int in;
		byte[] recivedData;
		String fileName;
		recivedData = new byte[1024];
		try {
			bis = new BufferedInputStream(client.getInputStream());
			DataInputStream dis = new DataInputStream(client.getInputStream());
			// Recibimos el nombre del fichero
			fileName = dis.readUTF();
			fileName = "C:\\Server\\"+fileName.substring(fileName.indexOf('\\') + 1, fileName.length());
			// Para guardar fichero recibido
			bos = new BufferedOutputStream(new FileOutputStream(fileName));
			while ((in = bis.read(recivedData)) != -1) {
				bos.write(recivedData, 0, in);
			}
			
			bos.close();
			dis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
