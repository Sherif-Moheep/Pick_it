package com.hitech.pickit.media.presentation.screens.credit

import androidx.lifecycle.SavedStateHandle
import com.hitech.pickit.media.domain.model.Person
import com.hitech.pickit.media.presentation.models.base.BaseRepository
import com.hitech.pickit.media.presentation.models.base.TMDbViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PersonViewModel @Inject constructor(
    repository: BaseRepository<Person>,
    savedStateHandle: SavedStateHandle
) :
    TMDbViewModel<Person>(
        repository,
        savedStateHandle["personId"],
    )
