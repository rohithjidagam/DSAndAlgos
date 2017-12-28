package com.google;

public class SentenceScreenFitting {

	public static void main(String[] args) {

		SentenceScreenFitting s = new SentenceScreenFitting();

		//System.out.println(s.wordsTyping(new String[] { "I", "had", "apple", "pie" }, 4, 5));
		//System.out.println(s.wordsTyping(new String[] { "hello", "world" }, 2, 8));
		System.out.println(s.wordsTyping(new String[] { "a", "bcd", "e" }, 3, 6));
	}

	public int wordsTyping(String[] sentence, int rows, int cols) {

		String wholeSentence = String.join(" ", sentence) + " ";
		int sentenceLength = wholeSentence.length();
		System.out.println(wholeSentence);
		System.out.println(sentenceLength);
		System.out.println("***********");
		int totalLength = 0;
		for (int i = 0; i < rows; i++) {
			totalLength += cols;
			System.out.println(totalLength);
			if (wholeSentence.charAt(totalLength % sentenceLength) == ' ')
				totalLength++;
			else {
				while (totalLength > 0 && wholeSentence.charAt((totalLength - 1) % sentenceLength) != ' ')
					totalLength--;
			}
			System.out.println(totalLength);
			System.out.println("***********");
			
		}
		
		System.out.println(totalLength);
		System.out.println(sentenceLength);

		return totalLength / sentenceLength;
	}

}
