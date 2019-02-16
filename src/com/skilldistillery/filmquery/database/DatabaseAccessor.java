package com.skilldistillery.filmquery.database;

import java.util.List;
import com.skilldistillery.filmquery.entities.*;

public interface DatabaseAccessor {
	Film findFilmById(int filmId);

	Actor findActorById(int actorId);

	List<Actor> findActorsByFilmId(int filmId);

	List<Film> findFilmByKeyWord(String keyWord);

	List<Category> findCategoriesByFilmId(int filmId);
}
