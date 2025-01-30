package com.esmanureral.filmjc.data.model

data class MovieItem(
    val movieImdbID: String, // IMDb kimliği
    val movieComingSoon: Boolean, // Yakında çıkacak filmler
    val movieActors: String, // Oyuncular
    val movieAwards: String, // Ödüller
    val movieCountry: String, // Ülke
    val movieDirection: String, // Yönetmen
    val movieGenre: String, // Tür
    val movieImages: List<String>, // Film görselleri
    val movieLanguage: String, // Dil
    val movieMetaScore: String, // Metascore puanı
    val moviePlot: String, // Konu özeti
    val moviePoster: String, // Konu özeti
    val movieRate: String, // Puanı
    val movieReleased: String, // Vizyon tarihi
    val movieTitle: String, // Film adı
    val movieType: String, // Film türü (film, dizi vb.)
    val movieWriter: String, // Senarist
    val movieYear: String, // Yayın yılı
    val movieImdbRating: String, // IMDb puanı
    val movieImdbVotes: String, // IMDb oy sayısı
)
