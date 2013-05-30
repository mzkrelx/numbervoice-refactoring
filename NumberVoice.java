class NumberVoice {
	
	/**
	 * Long値をカタカナにして出力します。
	 * 
	 * @param number Long値
	 */
	public static void printNumberVoice(Long number) {
		
		short[] fourDigitNumberArray = makeFourDigitNumberArray(number);

		for (int fourDigitIndex = fourDigitNumberArray.length - 1; fourDigitIndex >= 0; fourDigitIndex--) {
			
			short[] numberArray = makeNumberArray(fourDigitNumberArray[fourDigitIndex]);
			
			byte continueZeroCount = countZero(numberArray);

			boolean isExistsFourDigitPosition = false;	// 4桁毎の位があるかどうか
			
			for (int numberArrayIndex = 0; numberArrayIndex < numberArray.length; numberArrayIndex++) {
				if (!isZero(numberArray[numberArrayIndex])) {
					isExistsFourDigitPosition = true;
					
					printNumber(numberArray[numberArrayIndex], numberArrayIndex, fourDigitIndex);
					
					printPosition(numberArray[numberArrayIndex], numberArrayIndex, fourDigitIndex,
							continueZeroCount);
				}
			}
			
			if (isExistsFourDigitPosition) {
				printFourDigitPosition(fourDigitIndex);
			}
		}
		
		System.out.println();
		
	}

	/**
	 * 0が上の桁から何回続いているかを数えます。
	 * 
	 * @param numberArray 数の配列
	 * @return 0が何回か
	 */
	private static byte countZero(short[] numberArray) {
		byte continueZeroCount = 0;
		for (int i = numberArray.length - 1; i >= 0; i--) {
			if (numberArray[i] == 0) {
				continueZeroCount++;
			} else {
				break;
			}
		}
		return continueZeroCount;
	}

	/**
	 * 十、百、千の位を出力します。
	 * 
	 * @param number 位の数
	 * @param numberArrayIndex 位のインデックス。0は千
	 * @param fourDigitIndex 4桁毎の数のインデックス
	 * @param continueZeroCount 4桁毎の数が上の桁から何回0があるか
	 */
	private static void printPosition(short number, int numberArrayIndex, int fourDigitIndex,
			byte continueZeroCount) {
		switch (numberArrayIndex) {
		case 0:
			if (number == 3) {
				System.out.print("ゼン");
			} else {
				System.out.print("セン");
			}
			break;
		case 1:
			if (fourDigitIndex == 4 && continueZeroCount == 2) {
				if (number == 3) {
					System.out.print("ビャッ");
				} else if (number == 6
						|| number == 8) {
					System.out.print("ピャッ");
				} else {
					System.out.print("ヒャッ");
				}
			} else if (number == 3) {
				System.out.print("ビャク");
			} else if (number == 6
					|| number == 8) {
				System.out.print("ピャク");
			} else {
				System.out.print("ヒャク");
			}
			break;
		case 2:
			if ((fourDigitIndex == 4 || fourDigitIndex == 3) && continueZeroCount == 1) {
				System.out.print("ジュッ");
			} else {
				System.out.print("ジュウ");
			}
			break;
		case 3:
			break;
		default:
			break;
		}
	}
	
	private static boolean isZero(int num) {
		return num == 0;
	}

	/**
	 * 数をカタカナで出力します。
	 * 
	 * @param number 数
	 * @param position 一から千の位までの位。 0は千
	 * @param fourDigitIndex 4桁毎の位。5はガイ
	 */
	private static void printNumber(short number, int position, int fourDigitIndex) {
		switch (number) {
		case 1:
			if (position == 0 && fourDigitIndex >= 1) {
				System.out.print("イッ");
			}

			if (position == 3) {
				if (fourDigitIndex == 3 || fourDigitIndex == 4) {
					System.out.print("イッ");
				} else {
					System.out.print("イチ");
				}
			}
			break;
		case 2:
			System.out.print("二");
			break;
		case 3:
			System.out.print("サン");
			break;
		case 4:
			System.out.print("ヨン");
			break;
		case 5:
			System.out.print("ゴ");
			break;
		case 6:
			if (position == 1) {
				System.out.print("ロッ");
			} else {
				System.out.print("ロク");
			}
			break;
		case 7:
			System.out.print("ナナ");
			break;
		case 8:
			if (position == 0 || position == 1) {
				System.out.print("ハッ");
			} else {
				System.out.print("ハチ");
			}
			break;
		case 9:
			System.out.print("キュウ");
			break;
		default:
			break;
		}
	}

	/**
	 * 4桁ごとの数から、各桁の数が入る配列を作ります。0も入ります。
	 * 
	 * @param fourDigitNumber
	 * @return 各桁の数が入る配列(0でも入る)
	 */
	private static short[] makeNumberArray(short fourDigitNumber) {
		short[] numberArray = new short[4];
		for (int j = numberArray.length - 1; j >= 0; j--) {
			numberArray[j] = (short) (fourDigitNumber % 10);
			fourDigitNumber = (short) (fourDigitNumber / 10);
		}
		return numberArray;
	}

	/**
	 * 4桁毎の位を出力します。
	 * 
	 * @param fourDigitIndex 4桁毎の暗いの何番目か。5はガイ。
	 */
	private static void printFourDigitPosition(int fourDigitIndex) {
		switch (fourDigitIndex) {
		case 5:
			System.out.print("ガイ");
			break;
		case 4:
			System.out.print("ケイ");
			break;
		case 3:
			System.out.print("チョウ");
			break;
		case 2:
			System.out.print("オク");
			break;
		case 1:
			System.out.print("マン");
			break;
		case 0:
			break;
		default:
			break;
		}
	}

	/**
	 * 数から、四桁毎に数字が入る配列を作ります。
	 * 
	 * @param number 数
	 * @return 四桁毎に数字が入る配列
	 */
	private static short[] makeFourDigitNumberArray(Long number) {
		short[] tmpFourDigitNumberArray = new short[5];

		long tmpTargetNumber = number;
		int digitCount = 0;

		while (true) {
			tmpFourDigitNumberArray[digitCount] = (short) (tmpTargetNumber % 10000);
			tmpTargetNumber = tmpTargetNumber / 10000;
			if (tmpTargetNumber == 0) {
				break;
			}
			digitCount++;
		}
		short[] fourDigitNumberArray = new short[digitCount + 1];
		for (int i = 0; i < fourDigitNumberArray.length; i++) {
			fourDigitNumberArray[i] = tmpFourDigitNumberArray[i];
		}
		return fourDigitNumberArray;
	}
}