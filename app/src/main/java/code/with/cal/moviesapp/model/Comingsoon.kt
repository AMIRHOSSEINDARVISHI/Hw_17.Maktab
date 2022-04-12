package code.with.cal.moviesapp.model

data class Comingsoon(
    val dates: Dates,
    val page: Int,
    val results: List<ResultX>,
    val total_pages: Int,
    val total_results: Int
)