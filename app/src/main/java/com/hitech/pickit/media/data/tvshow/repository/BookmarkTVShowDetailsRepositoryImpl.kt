//package com.hitech.pickit.core.domain.repository.tvshow.detail
//
//import com.hitech.pickit.core.data.source.entity.asDatabaseModel
//import com.hitech.pickit.core.data.source.local.TVShowDao
//import com.hitech.pickit.feature_content.domain.repository.BookmarkDetailsRepository
//import com.hitech.pickit.movie.data.mappers.asDomainModel
//import com.hitech.pickit.feature_content.domain.model.TVShow
//import javax.inject.Inject
//import javax.inject.Singleton
//
//@Singleton
//class BookmarkTVShowDetailsRepositoryImpl @Inject constructor(
//    private val tvShowDao: TVShowDao
//) : BookmarkDetailsRepository<TVShow> {
//    override suspend fun addBookmark(item: TVShow) {
//        tvShowDao.addBookmark(item.asDatabaseModel())
//    }
//
//    override suspend fun deleteBookmark(id: Int) {
//        tvShowDao.deleteBookmark(id)
//    }
//
//    override suspend fun isBookmarked(id: Int): Boolean = tvShowDao.isBookmarked(id)
//    override suspend fun getBookmarks(): List<TVShow> {
//        return tvShowDao.getBookmarks().map { it.asDomainModel() }
//    }
//}
