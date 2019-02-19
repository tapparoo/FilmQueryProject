package com.skilldistillery.filmquery.database;

import java.sql.*;
import java.util.*;

import com.skilldistillery.filmquery.entities.*;

public class DatabaseAccessorObject implements DatabaseAccessor {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";
	private final String USER = "student";
	private final String PASS = "student";

	// Discover the jdbc driver
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// Joins three tables [actor, film_actor, film] and returns the list of actors
	// based on the filmId parameter
	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		List<Actor> list = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(URL, USER, PASS);
			stmt = conn.prepareStatement(
					"SELECT * FROM actor JOIN film_actor ON actor.id = actor_id "
							+ "WHERE film_id = ?");
			stmt.setInt(1, filmId);
			rs = stmt.executeQuery();

			while (rs.next()) {
				if (list == null) {
					list = new ArrayList<Actor>();
				}
				list.add(new Actor(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	// Joins three tables [category, film_category, film] and returns the list of
	// categories based on the filmId parameter
	@Override
	public List<Category> findCategoriesByFilmId(int filmId) {
		List<Category> list = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(URL, USER, PASS);
			stmt = conn.prepareStatement(
					"SELECT * FROM film JOIN film_category ON film.id = film_id JOIN category ON category_id = category.id "
							+ "WHERE film_id = ?");
			stmt.setInt(1, filmId);
			rs = stmt.executeQuery();

			while (rs.next()) {
				if (list == null) {
					list = new ArrayList<Category>();
				}
				list.add(new Category(rs.getInt("id"), rs.getString("name")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public Film findFilmById(int filmId) {
		Film film = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(URL, USER, PASS);
			stmt = conn.prepareStatement(
					"SELECT * FROM film JOIN language ON language_id = language.id WHERE film.id = ?");
			stmt.setInt(1, filmId);
			rs = stmt.executeQuery();

			if (rs.next()) {
				film = new Film(rs.getInt("id"), rs.getInt("rental_duration"), rs.getInt("length"),
						rs.getInt("release_year"), rs.getString("title"), rs.getString("description"),
						rs.getString("rating"), rs.getString("language.name"), findCategoriesByFilmId(filmId),
						rs.getDouble("rental_rate"), rs.getDouble("replacement_cost"), rs.getString("special_features"),
						findActorsByFilmId(filmId));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return film;
	}

	// Queries the film table's title/description columns for the keyWord parameter.
	// Returns a list of films matching the keyword
	@Override
	public List<Film> findFilmByKeyWord(String keyWord) {
		List<Film> films = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(URL, USER, PASS);
			stmt = conn.prepareStatement("SELECT * FROM film WHERE title LIKE ? OR description LIKE ?");
			stmt.setString(1, "%" + keyWord + "%");
			stmt.setString(2, "%" + keyWord + "%");
			rs = stmt.executeQuery();

			while (rs.next()) {
				if (films == null) {
					films = new ArrayList<Film>();
				}
				films.add(findFilmById(rs.getInt("id")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return films;
	}

	@Override
	public Actor findActorById(int actorId) {
		Actor actor = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(URL, USER, PASS);
			stmt = conn.prepareStatement("SELECT * FROM actor WHERE id = ?");
			stmt.setInt(1, actorId);
			rs = stmt.executeQuery();

			if (rs.next()) {
				actor = new Actor(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return actor;
	}
}
