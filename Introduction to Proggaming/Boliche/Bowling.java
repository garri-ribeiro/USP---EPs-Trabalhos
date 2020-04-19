import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

public class Bowling {

	private static final LinkedList<Frame> FRAMES = new LinkedList<>();
	private static final Stack<Frame> stackBonus = new Stack<>();
	private static final Scanner scanner = new Scanner(System.in);

	private static int ROUND = 0;

	public static void main (String[]args){
		System.out.print("Type your name:	");
		final String name = scanner.nextLine();

		System.out.print("\nStarting game: \n\n");

		startGame();
		printFinalScore(name);
	}

	private static void startGame() {
		while(ROUND != 10) {
			startRound();
		}

		if (!stackBonus.isEmpty()) {
			extraBall();
		}
	}

	private static void printFinalScore(final String name) {
		final Integer score = FRAMES.stream().mapToInt(Frame::getScore).sum();

		System.out.printf("%nCongratulations %s%n", name);
		System.out.printf("Your score is %d", score);
	}

	private static void startRound() {
		ROUND++;
		final Frame currentFrame = new Frame();

		for(int ball = 1; ball <=2; ball++) {
			System.out.printf("Round: %d, Ball  %d %n", ROUND, ball);

			final int pins = insertPins(currentFrame.getScore());
			currentFrame.setScore(pins);
			setBonusScore(pins);

			if(currentFrame.getScore() == 10) {
				if (ball == 1) {
					System.out.print("Strike! \n");
					currentFrame.setBonusTime(2);
				} else {
					System.out.print("Spare! \n");
					currentFrame.setBonusTime(1);
				}

				stackBonus.push(currentFrame);
				break;
			}
		}

		FRAMES.add(currentFrame);
	}

	private static void extraBall() {
		final Frame frame = stackBonus.pop();
		int currentBall  = 2;

		while(frame.getBonusTime() != 0) {
			System.out.printf("Round: %d, Ball  %d %n", ROUND, currentBall);

			int pins = insertPins(0);
			frame.setScore(pins);

			frame.decreaseBonusTime();
			currentBall++;
		}
	}

	private static void setBonusScore(int pins) {
		while(!stackBonus.isEmpty()) {
			final Frame frame = stackBonus.pop();
			frame.decreaseBonusTime();
			frame.setScore(pins);

			if (frame.hasBonusTime()) {
				stackBonus.push(frame);
			}
		}
	}

	private static int insertPins(int currentScore) {
		boolean retry = true;
		int pins = 0;

		while (retry) {
			try {
				pins = scanner.nextInt();

				if (pins < 0 || pins > 10) {
					throw new IllegalArgumentException();
				}

				final int inputTotal = pins + currentScore;
				if (inputTotal > 10) {

					throw new IllegalStateException(String.valueOf(10 - currentScore));
				}

				retry = false;
			} catch (InputMismatchException | IllegalArgumentException e) {
				System.out.print("Invalid value, type a number between 0 and 10 \n");
				scanner.nextLine();
			} catch (IllegalStateException e) {
				System.out.printf("Invalid value, you already has %d points this round, type a number between 0 and %s%n", currentScore, e.getMessage());
			}
		}

		return pins;
	}
}

class Frame {

	private int score;
	private int	bonusTime;

	public void decreaseBonusTime() {
		this.bonusTime--;
	}

	public boolean hasBonusTime() {
		return this.bonusTime != 0;
	}

	public int getBonusTime() {
		return bonusTime;
	}

	public void setBonusTime(int bonusTime) {
		this.bonusTime = bonusTime;
	}

	public int getScore() {
		return this.score;
	}

	public void setScore(int score) {
		this.score += score;
	}
}
