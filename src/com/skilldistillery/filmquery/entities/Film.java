package com.skilldistillery.filmquery.entities;

import java.util.ArrayList;
import java.util.List;

public class Film {
	private int id, rentalDuration, length, year;
	private String title, description, rating, language, specialFeatures;
	private double rentalRate, replacementCost;
	private List<Actor> actors;
	private List<Category> categories;

	public Film(int id, int rentalDuration, int length, int year, String title, String description, String rating,
			String language, List<Category> categories, double rentalRate, double replacementCost,
			String specialFeatures, List<Actor> actors) {
		this.id = id;
		this.rentalDuration = rentalDuration;
		this.length = length;
		this.year = year;
		this.title = title;
		this.description = description;
		this.rating = rating;
		this.language = language;
		this.categories = categories;
		this.rentalRate = rentalRate;
		this.replacementCost = replacementCost;
		this.specialFeatures = specialFeatures;
		this.actors = actors;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRentalDuration() {
		return rentalDuration;
	}

	public void setRentalDuration(int rentalDuration) {
		this.rentalDuration = rentalDuration;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public List<Category> getCategories() {
		return new ArrayList<Category>(categories);
	}

	public void setCategories(List<Category> categories) {
		this.categories = new ArrayList<Category>(categories);
	}

	public double getRentalRate() {
		return rentalRate;
	}

	public void setRentalRate(double rentalRate) {
		this.rentalRate = rentalRate;
	}

	public double getReplacementCost() {
		return replacementCost;
	}

	public void setReplacementCost(double replacementCost) {
		this.replacementCost = replacementCost;
	}

	public String getSpecialFeatures() {
		return specialFeatures;
	}

	public void setSpecialFeatures(String specialFeatures) {
		this.specialFeatures = specialFeatures;
	}

	public List<Actor> getActors() {
		return new ArrayList<Actor>(actors);
	}

	public void setActors(List<Actor> actors) {
		this.actors = new ArrayList<Actor>(actors);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((actors == null) ? 0 : actors.hashCode());
		result = prime * result + ((categories == null) ? 0 : categories.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		result = prime * result + ((language == null) ? 0 : language.hashCode());
		result = prime * result + length;
		result = prime * result + ((rating == null) ? 0 : rating.hashCode());
		result = prime * result + rentalDuration;
		long temp;
		temp = Double.doubleToLongBits(rentalRate);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(replacementCost);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((specialFeatures == null) ? 0 : specialFeatures.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + year;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Film other = (Film) obj;
		if (actors == null) {
			if (other.actors != null)
				return false;
		} else if (!actors.equals(other.actors))
			return false;
		if (categories == null) {
			if (other.categories != null)
				return false;
		} else if (!categories.equals(other.categories))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (language == null) {
			if (other.language != null)
				return false;
		} else if (!language.equals(other.language))
			return false;
		if (length != other.length)
			return false;
		if (rating == null) {
			if (other.rating != null)
				return false;
		} else if (!rating.equals(other.rating))
			return false;
		if (rentalDuration != other.rentalDuration)
			return false;
		if (Double.doubleToLongBits(rentalRate) != Double.doubleToLongBits(other.rentalRate))
			return false;
		if (Double.doubleToLongBits(replacementCost) != Double.doubleToLongBits(other.replacementCost))
			return false;
		if (specialFeatures == null) {
			if (other.specialFeatures != null)
				return false;
		} else if (!specialFeatures.equals(other.specialFeatures))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (year != other.year)
			return false;
		return true;
	}

	// Default film output
	public String toStringSummary() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("\n%15s %s (%s)\n", "Title:  ", title, language));
		sb.append(String.format("%15s %d\n", "Released:  ", year));
		sb.append(String.format("%15s %s\n", "Rating:  ", rating));
		sb.append(String.format("%15s %s\n", "Description:  ", description));
		sb.append(String.format("%14s  ", "Categories: "));
		for (int i = 0; i < categories.size(); i++) {
			sb.append(String.format("%s", categories.get(i).getName()));
			if (i < categories.size() - 1) {
				sb.append(", ");
			}
		}
		sb.append(String.format("\n%14s  ", "Cast: "));
		for (int i = 0; i < actors.size(); i++) {
			sb.append(String.format("%s %s", actors.get(i).getFirstName(), actors.get(i).getLastName()));
			if (i < actors.size() - 1) {
				sb.append(", ");
			}
		}
		return sb.toString();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(String.format("\n%20s %s \n", "Film ID:  ", id));
		sb.append(String.format("%20s %s\n", "Title:  ", title));
		sb.append(String.format("%20s %d\n", "Released:  ", year));
		sb.append(String.format("%20s %s\n", "Rating:  ", rating));
		sb.append(String.format("%20s %s\n", "Language:  ", language));
		sb.append(String.format("%20s %s\n", "Length:  ", length));
		sb.append(String.format("%20s %s\n", "Rental Duration:  ", rentalDuration));
		sb.append(String.format("%20s %s\n", "Rental Rate:  ", rentalRate));
		sb.append(String.format("%20s %s\n", "Replacement Cost:  ", replacementCost));
		sb.append(String.format("%20s %s\n", "Special Features:  ", specialFeatures));
		sb.append(String.format("%20s %s\n", "Description:  ", description));
		sb.append(String.format("%19s  ", "Categories: "));
		for (int i = 0; i < categories.size(); i++) {
			sb.append(String.format("%s", categories.get(i).getName()));
			if (i < categories.size() - 1) {
				sb.append(", ");
			}
		}
		sb.append(String.format("\n%19s  ", "Cast: "));
		for (int i = 0; i < actors.size(); i++) {
			sb.append(String.format("%s %s", actors.get(i).getFirstName(), actors.get(i).getLastName()));
			if (i < actors.size() - 1) {
				sb.append(", ");
			}
		}		
		return sb.toString();
	}

}
