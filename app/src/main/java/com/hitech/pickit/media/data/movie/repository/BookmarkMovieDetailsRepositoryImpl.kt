//package com.hitech.pickit.core.domain.repository.movie.detail
//
//import com.hitech.pickit.core.data.source.entity.asDatabaseModel
//import com.hitech.pickit.core.data.source.local.MovieDao
//import com.hitech.pickit.feature_content.domain.repository.BookmarkDetailsRepository
//import com.hitech.pickit.movie.data.mappers.asDomainModel
//import com.hitech.pickit.feature_content.domain.model.Movie
//import javax.inject.Inject
//import javax.inject.Singleton
//
//@Singleton
//class BookmarkMovieDetailsRepositoryImpl @Inject constructor(
//    private val movieDao: MovieDao) :
//    BookmarkDetailsRepository<Movie> {
//    override suspend fun addBookmark(item: Movie) {
//        movieDao.addBookmark(item.asDatabaseModel())
//    }
//
//    override suspend fun deleteBookmark(id: Int) {
//        movieDao.deleteBookmark(id)
//    }
//
//    override suspend fun isBookmarked(id: Int): Boolean = movieDao.isBookmarked(id)
//
//    override suspend fun getBookmarks(): List<Movie> {
//        return movieDao.getBookmarks().map { it.asDomainModel() }
//    }
//}
