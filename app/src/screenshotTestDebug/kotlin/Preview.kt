import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.mohini.jetpackcomposesearch.ComposableScreen
import com.mohini.jetpackcomposesearch.SearchViewModel
import com.mohini.jetpackcomposesearch.ui.theme.JetpackComposeSearchTheme

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetpackComposeSearchTheme {
        ComposableScreen(viewModel = SearchViewModel(), onSearchValueChange = {})
    }
}