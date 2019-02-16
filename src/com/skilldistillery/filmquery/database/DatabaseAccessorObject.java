package com.skilldistillery.filmquery.database;

import java.sql.*;
import java.util.*;

import com.skilldistillery.filmquery.entities.*;

public class DatabaseAccessorObject implements DatabaseAccessor {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";
	private final String USER = "student";
	private final String PASS = "student";

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		List<Actor> list = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(URL, USER, PASS);
			stmt = conn.prepareStatement(
					"SELECT * FROM actor JOIN film_actor ON actor.id = actor_id JOIN film ON film_id = film.id"
							+ "WHERE film_id LIKE ?");
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

	@Override
	public Film findFilmById(int filmId) {
		Film film = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(URL, USER, PASS);
			stmt = conn.prepareStatement("SELECT * FROM film WHERE id = ?");
			stmt.setInt(1, filmId);
			rs = stmt.executeQuery();

			if (rs.next()) {
				film = new Film(rs.getInt("id"), rs.getInt("language_id"), rs.getInt("rental_duration"),
						rs.getInt("length"), rs.getString("title"), rs.getString("description"),
						rs.getInt("release_year"), rs.getString("rating"), rs.getDouble("rental_rate"),
						rs.getDouble("replacement_cost"), rs.getString("special_features"));
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
