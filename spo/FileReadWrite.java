package spo;

import java.io.*;
import java.security.SecureRandom;
import java.util.LinkedList;

public class FileReadWrite {
	private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
	private static final SecureRandom RANDOM = new SecureRandom();
	private static final int LENGTH = 32;

	/**
	 * generate strings
	 */
	public static String generate() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 2 + Math.random() * LENGTH; ++i) {
			sb.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
		}
		return sb.toString() + '\n';
	}

	/**
	 * create file and write strings there
	 */
	void writeFile() {
		try {
			FileOutputStream fileOutputStream = new FileOutputStream("java.txt", false);
			for (int i = 0; i < 25; i++) {
				fileOutputStream.write(generate().getBytes());
				// fileOutputStream.write('\n');
			}
			fileOutputStream.close();
		} catch (IOException e) {
			System.out.println("Error: " + e);
		}
	}

	/**
	 * read file and wtire string in list. return list
	 */
	LinkedList<String> readFile() {
		LinkedList<String> linkedList = new LinkedList<>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader("java.txt"));
			String buff;
			System.out.println("----------------\nIdentifiers:\n");
			while (reader.read() != -1) {
				linkedList.add(buff = reader.readLine());
				System.out.println(buff);
			}
			System.out.println("\n------------\n");
			reader.close();
		} catch (IOException e) {
			System.out.println(e);
		}
		return linkedList;
	}
}
