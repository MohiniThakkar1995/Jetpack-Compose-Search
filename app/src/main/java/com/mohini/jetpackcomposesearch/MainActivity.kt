package com.mohini.jetpackcomposesearch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mohini.jetpackcomposesearch.ui.theme.JetpackComposeSearchTheme

class MainActivity : ComponentActivity() {
    private val viewModel: SearchViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeSearchTheme(dynamicColor = false) {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ComposableScreen(
                        modifier = Modifier.padding(innerPadding),
                        viewModel,
                        onSearchValueChange = { viewModel.onValueChange(it) })
                }
            }
        }
    }
}

@Composable
fun ComposableScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel,
    onSearchValueChange: (String) -> Unit
) {
    val state by viewModel.searchText.collectAsStateWithLifecycle()
    val userListState by viewModel.listOfNames.collectAsStateWithLifecycle()
    val isSearching by viewModel.isSearching.collectAsStateWithLifecycle()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        TextField(value = state, onValueChange = {
            onSearchValueChange(it)
        }, label = {
            Text(
                text = "Enter name"
            )
        })
        if (isSearching) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(content = {
                items(userListState) { person ->
                    Text(text = person.name)
                }
            })
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetpackComposeSearchTheme {
        ComposableScreen(viewModel = SearchViewModel(), onSearchValueChange = {})
    }
}