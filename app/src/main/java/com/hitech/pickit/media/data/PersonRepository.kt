package com.hitech.pickit.media.data

import com.hitech.pickit.core.di.IoDispatcher
import com.hitech.pickit.media.data.datasource.remote.api.PersonService
import com.hitech.pickit.media.data.datasource.remote.mappers.asDomainModel
import com.hitech.pickit.media.domain.model.Person
import com.hitech.pickit.media.presentation.models.base.BaseRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PersonRepository @Inject constructor(
    private val personApi: PersonService,
    // @ApplicationContext context: Context,
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
) : BaseRepository<Person>(ioDispatcher) {

    override suspend fun getSuccessResult(id: Any?): Person {

        require(id is String) { "Person ID must be a String and not null" }
        return personApi.getPerson(id).asDomainModel()
    }
}