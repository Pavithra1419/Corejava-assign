package com.training.services;

import java.io.FileWriter;
import java.io.IOException;

public class FileService {
	FileWriter writer;

	public FileService(String fileName) {
		super();
		try {
			this.writer = new FileWriter(fileName);
		} catch (IOException e) {
			System.out.println("Couldnt create file");
			e.printStackTrace();
		}
	}
	
	public void addToFile(String data) {
		try {
			writer.append(data);
			writer.flush();
			writer.close();
			System.out.println("Data is written to file");
		} catch (IOException e) {
			System.out.println("Couldnt complete the task!!");
			e.printStackTrace();
		}
		
		
	}
}
