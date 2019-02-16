package com.skilldistillery.filmquery.app;

import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.*;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) {
		FilmQueryApp app = new FilmQueryApp();
		app.launch();
	}

	private void launch() {
		Scanner sc = new Scanner(System.in);

		startUserInterface(sc);

		sc.close();
	}

	private void startUserInterface(Scanner sc) {
		int userSelection = 0;

		do {
			printOptions();
			try {
				userSelection = Integer.parseInt(sc.next());
				if (userSelection == 3) {
					continue;
				}
				doUserSelection(userSelection, sc);
			} catch (NumberFormatException e) {
				System.out.print("\nInvalid selection. (Q)uit, or any other key to try again.\n  >> ");
				if (sc.next().equalsIgnoreCase("Q")) {
					break;
				} else {
					System.out.println("\n\n");
				}
			}

		} while (userSelection != 3);
	}

	private void doUserSelection(int option, Scanner sc) {
		switch (option) {
		// ID search - returns a single film
		case 1:
			do {
				System.out.print("\nEnter a Film ID: ");
				try {
					option = Integer.parseInt(sc.next());
					System.out.println(db.findFilmById(option).toStringSummary());

					System.out.print("\n1. See film details.\n2. Return to Main Menu\n>> ");
					int filmDetailsOrMainMenu = Integer.parseInt(sc.next());

					if (filmDetailsOrMainMenu == 1) {
						System.out.println(db.findFilmById(option).toString());
					} else {
						return;
					}
				} catch (NumberFormatException e) {
					System.out.print("\nInvalid selection. (Q)uit, or any other key to try again.\n  >> ");
					if (sc.next().equalsIgnoreCase("Q")) {
						return;
					} else {
						continue;
					}
				}
				break;
			} while (true);
			break;
		// Keyword search - returns multiple films
		case 2:
			System.out.print("Enter a search string: ");
			String input = sc.next();
			List<Film> films = db.findFilmByKeyWord(input);
			if (films == null) {
				System.out.println("\n\tNo films found with the search term: " + input + "\n");
			} else {
				for (Film film : films) {
					System.out.println(film.toStringSummary());
				}
			}
			break;
		default:
			System.out.println("\nInvalid option.\n");
		}
	}

	private void printOptions() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n\nFilm Query Application\n");
		sb.append("----------------------\n");
		sb.append("1. Look up film by ID\n");
		sb.append("2. Look up film by keyword\n");
		sb.append("3. Quit\n");
		sb.append("  >> ");
		System.out.println(sb);
	}

}
