package com.gilbertohdz.tvmaze.compose.data

import com.gilbertohdz.domain.model.Episode
import com.gilbertohdz.domain.model.Rating
import com.gilbertohdz.domain.model.Show
import java.io.Serializable

data class Movie(
    var id: Int = 0,
    var title: String = "",
    var subtitle: String = "",
    var description: String = "",
    var backgroundImageUrl: String = "",
    var cardImageUrl: String = "",
    var videoUrl: String = "",
    var studio: String = "",
    var season: Int = 0,
    var number: Int = 0,
    var rating: Rating? = null,
    val type: String = "",
    val premiered: String? = null,
    val ended: String? = null,
    val language: String = "",
    val genres: List<String> = emptyList(),
) : Serializable {

    companion object {
        internal const val serialVersionUID = 727566175075960653L
    }

    override fun toString(): String {
        return "Movie(id=$id, title='$title', description='$description', " +
                "backgroundImageUrl='$backgroundImageUrl', cardImageUrl='$cardImageUrl', " +
                "videoUrl='$videoUrl', studio='$studio', season=$season, number=$number, " +
                "rating=$rating, type='$type', premiered=$premiered, ended=$ended, " +
                "language='$language', genres=$genres)"
    }
}

fun Show.toMovie(): Movie {
    return Movie(
        id = id,
        title = name,
        description = summary,
        type = type,
        premiered = premiered,
        ended = ended,
        rating = rating,
        language = language,
        genres = genres,
        backgroundImageUrl = image?.original ?: "",
        cardImageUrl = image?.medium ?: "",
    )
}

fun Episode.toMovie(): Movie {
    return Movie(
        id = id,
        title = name,
        description = summary,
        season = season,
        number = number,
        rating = rating,
        backgroundImageUrl = image?.original ?: "",
        cardImageUrl = image?.medium ?: "",
        type = type,
    )
}