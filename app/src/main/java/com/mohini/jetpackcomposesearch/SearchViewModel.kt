package com.mohini.jetpackcomposesearch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class SearchViewModel : ViewModel() {
    private val list: List<Employee> = listOf(Employee(1, "Mohini"), Employee(2, "Test"))

    private val _searchText = MutableStateFlow("")
    var searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _listOfNames = MutableStateFlow<List<Employee>>(emptyList())
    var listOfNames = searchText.debounce(1000)
        .onEach { _isSearching.update { true } }
        .combine(_listOfNames) { searchText, listOfNames ->
            delay(1000)
            if (searchText.isEmpty()) list else list.filter { it.name.contains(searchText) }
        }.onEach { _isSearching.update { false } }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), _listOfNames.value)

    fun onValueChange(searchedValue: String) {
        _searchText.update { searchedValue }
    }
}

data class Employee(val id: Int, val name: String)

