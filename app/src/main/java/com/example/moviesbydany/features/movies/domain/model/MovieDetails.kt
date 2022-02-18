package com.example.moviesbydany.features.movies.domain.model

data class MovieDetails(
    val Response: String,
    val Error: String,
    val Actors: String, // Brie Larson, Samuel L. Jackson, Ben Mendelsohn
    val Awards: String, // 9 wins & 56 nominations
    val BoxOffice: String, // $426,829,839
    val Country: String, // United States, Australia
    val DVD: String, // 28 May 2019
    val Director: String, // Anna Boden, Ryan Fleck
    val Genre: String, // Action, Adventure, Sci-Fi
    val Language: String, // English
    val Metascore: String, // 64
    val Plot: String, // Carol Danvers becomes one of the universe's most powerful heroes when Earth is caught in the middle of a galactic war between two alien races.
    val Poster: String, // https://m.media-amazon.com/images/M/MV5BMTE0YWFmOTMtYTU2ZS00ZTIxLWE3OTEtYTNiYzBkZjViZThiXkEyXkFqcGdeQXVyODMzMzQ4OTI@._V1_SX300.jpg
    val Production: String, // N/A
    val Rated: String, // PG-13
    val Ratings: List<Rating>?,
    val Released: String, // 08 Mar 2019 // True
    val Runtime: String, // 123 min
    val Title: String, // Captain Marvel
    val Type: String, // movie
    val Website: String, // N/A
    val Writer: String, // Anna Boden, Ryan Fleck, Geneva Robertson-Dworet
    val Year: String, // 2019
    val imdbID: String, // tt4154664
    val imdbRating: String, // 6.8
    val imdbVotes: String,// 515,178,

) {
    constructor(Response: String, Error: String) : this(
        "", // Brie Larson, Samuel L. Jackson, Ben Mendelsohn
        "", // 9 wins & 56 nominations
        "", // $426,829,839
        "", // United States, Australia
        "", // 28 May 2019
        "", // Anna Boden, Ryan Fleck
        "", // Action, Adventure, Sci-Fi
        "", // English
        "", // 64
        "", // Carol Danvers becomes one of the universe's most powerful heroes when Earth is caught in the middle of a galactic war between two alien races.
        "", // https://m.media-amazon.com/images/M/MV5BMTE0YWFmOTMtYTU2ZS00ZTIxLWE3OTEtYTNiYzBkZjViZThiXkEyXkFqcGdeQXVyODMzMzQ4OTI@._V1_SX300.jpg
        "", // N/A
        "", // PG-13
        "",
        "", // 08 Mar 2019 // True
        null, // 123 min
        "", // Captain Marvel
        "", // movie
        "", // N/A
        "", // Anna Boden, Ryan Fleck, Geneva Robertson-Dworet
        "", // 2019
        "", // tt4154664
        "", // 6.8
        "", "", "" // 515,178
    )
}

