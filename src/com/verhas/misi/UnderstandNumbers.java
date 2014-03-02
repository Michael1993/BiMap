package com.verhas.misi;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;

public class UnderstandNumbers {
	private HashMap<Integer, String> numbers;
	private HashMap<Integer, String> hundreds;
	private ArrayList<Integer> hundredsList;
	private ArrayList<Integer> numbersList;

	private TreeMap<Integer, String> testMap;
	private TreeMap<Integer, String> testMapHundreds;

	public UnderstandNumbers() throws IOException {
		InitNumbers();
		InitHundreds();
		InitTestMap();
		InitTestMapHundreds();
	}

	private void InitTestMap() throws IOException {
		testMap = new TreeMap<Integer, String>();
		InputStream s = this.getClass().getClassLoader()
				.getResourceAsStream("N.txt");
		try (Scanner scr = new Scanner(s)) {
			while (scr.hasNext()) {
				testMap.put(new Integer(scr.nextInt()), new String(scr.next()));
			}
		}
	}

	private void InitTestMapHundreds() {
		testMapHundreds = new TreeMap<Integer, String>();
		InputStream s = this.getClass().getClassLoader()
				.getResourceAsStream("H.txt");
		try (Scanner scr = new Scanner(s)) {
			while (scr.hasNext()) {
				testMapHundreds.put(new Integer(scr.nextInt()),
						new String(scr.next()));
			}
		}
	}

	private void InitNumbers() throws IOException {
		numbers = new HashMap<Integer, String>();
		numbersList = new ArrayList<Integer>();
		InputStream s2 = this.getClass().getClassLoader()
				.getResourceAsStream("N.txt");
		try (Scanner s = new Scanner(s2)) {
			while (s.hasNext()) {
				Integer t = new Integer(s.nextInt());
				numbers.put(t, new String(s.next()));
				numbersList.add(t);
			}
			Collections.sort(numbersList);
		}
		s2.close();
	}

	private void InitHundreds() throws IOException {
		hundreds = new HashMap<Integer, String>();
		hundredsList = new ArrayList<Integer>();
		InputStream s2 = this.getClass().getClassLoader()
				.getResourceAsStream("H.txt");
		try (Scanner s = new Scanner(s2)) {
			while (s.hasNext()) {
				Integer t = new Integer(s.nextInt());
				hundreds.put(t, new String(s.nextLine()));
				hundredsList.add(t);
			}
			Collections.sort(hundredsList);
		}
		s2.close();

	}

	public String fromNumberToString(Integer theNumber) {
		if (theNumber == 0) {
			return "zero";
		}
		String n = new String();
		while (theNumber > 99) {
			int i = hundredsList.size() - 1;
			while (theNumber < hundredsList.get(i))
				i--;
			n = n.concat(fromNumberToString(theNumber / hundredsList.get(i)));
			n = n.concat(hundreds.get(hundredsList.get(i)) + " ");
			theNumber = theNumber % hundredsList.get(i);
		}
		if (theNumber > 19) {
			int i = numbersList.size() - 1;
			while (theNumber < numbersList.get(i))
				i--;
			n = n.concat(numbers.get(numbersList.get(i)) + " ");
			theNumber -= numbersList.get(i);
		}
		if (theNumber > 0) {
			n = n.concat(numbers.get(numbersList.get(theNumber - 1)));
		}
		return n;
	}

	public String fromNumberToStringTestMap(Integer theNumber)
			throws IOException {
		if (theNumber <= 0) {
			return "zero";
		}
		String theString = new String();
		while (theNumber > 99) {
			theString += fromNumberToStringTestMap(theNumber
					/ testMapHundreds.floorKey(theNumber));
			theString += testMapHundreds.floorEntry(theNumber).getValue() + " ";
			theNumber = theNumber % testMapHundreds.floorKey(theNumber);
		}
		if (theNumber > 19) {
			theString += testMap.floorEntry(theNumber).getValue() + " ";
			theNumber -= testMap.floorKey(theNumber);
		}
		if (theNumber > 0) {
			theString += testMap.get(theNumber) + " ";
		}
		return theString;
	}

	public Integer fromStringToNumber(String theString) {
		Scanner scan = new Scanner(theString);
		scan.close();
		return 0;
	}

	public static void main(String[] args) throws IOException {
		UnderstandNumbers b = new UnderstandNumbers();
		Scanner S = new Scanner(System.in);
		// System.out.println(b.fromNumberToString(S.nextInt()));
		// b.fromStringToNumber("two hundred twenty two");
		System.out.println(b.fromNumberToStringTestMap(S.nextInt()));
		S.close();
	}

}
