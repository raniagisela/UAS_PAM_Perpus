package com.example.uaspamperpus.model

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory

object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer {
            SewaViewModel(perpusApp().container.repositoriSewa)
        }
        initializer {
            InputViewModel(perpusApp().container.repositoriSewa)
        }
        initializer {
            DetailsViewModel(createSavedStateHandle(),
                perpusApp().container.repositoriSewa
            )
        }
        initializer {
            EditViewModel(createSavedStateHandle(),
                perpusApp().container.repositoriSewa
            )
        }
    }
}

fun CreationExtras.perpusApp():PerpusApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]as PerpusApp)