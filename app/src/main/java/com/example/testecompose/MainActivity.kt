package com.example.testecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Smartphone
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.pullToRefresh
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.example.testecompose.ui.ThemeViewModel
import com.example.testecompose.ui.navigation.AppNavHost
import com.example.testecompose.ui.theme.TesteComposeTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import kotlin.time.Duration.Companion.milliseconds

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val themeViewModel = koinViewModel<ThemeViewModel>()
            val darkTheme by themeViewModel.darkTheme.collectAsStateWithLifecycle(
                isSystemInDarkTheme()
            )
            TesteComposeTheme(
                darkTheme = darkTheme
            ) {
//                Surface(
//                    modifier = Modifier.fillMaxSize()
//                ) {
//                    AppNavHost(
//                        rememberNavController()
//                    )
//                }
                HomeScaffold()
            }
        }
    }
}

@Composable
fun HomeRowColumns() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Surface(
            modifier = Modifier.size(100.dp),
            color = Color.Green
        ) {}

        Surface(
            modifier = Modifier.size(100.dp),
            color = Color.Blue
        ) {}

        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom
        ) {
            Surface(
                modifier = Modifier
                    .size(100.dp),
                color = Color.Green
            ) {

            }
        }
    }
}

@Composable
fun HomeCanvas() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp)
                .background(Color.White)
        ) {
            drawRect(
                color = Color.Green,
                size = Size(
                    width = 250f,
                    height = 250f
                ),
                topLeft = Offset(
                    x = 50f,
                    y = 50f
                )
            )

            drawOval(
                color = Color.Green,
                size = Size(
                    width = 150f,
                    height = 150f
                ),
                topLeft = Offset(
                    x = 50f,
                    y = 500f
                )
            )

            drawLine(
                color = Color.Green,
                start = Offset(
                    x = 500f,
                    y = 500f
                ),
                end = Offset(
                    x = 700f,
                    y = 700f
                ),
                strokeWidth = 8f
            )
        }
    }
}

@Composable
fun PagerExample(modifier: Modifier = Modifier) {
    val pageState = rememberPagerState(pageCount = {
        20
    })

    VerticalPager(
        state = pageState,
        modifier = Modifier.fillMaxSize()
    ) { page ->
        Text(text = "Page: $page")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScaffold() {
    var showDialog by remember {
        mutableStateOf(false)
    }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    Scaffold(
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        },
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Teste")
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Cyan
                ),
                actions = {
                    Icon(
                        modifier = Modifier
                            .clickable {
                                showDialog = true
                            },
                        imageVector = Icons.Rounded.Home,
                        contentDescription = null
                    )
                    Icon(
                        modifier = Modifier
                            .clickable {
                                scope.launch {
                                    val result = snackbarHostState.showSnackbar(
                                        message = "Teste snackbar",
                                        actionLabel = "desfazer",
                                        withDismissAction = true,
                                        duration = SnackbarDuration.Short
                                    )
                                    when (result) {
                                        SnackbarResult.Dismissed -> TODO()
                                        SnackbarResult.ActionPerformed -> {
                                            scope.launch {
                                                snackbarHostState.showSnackbar(
                                                    message = "Snackbar desfeita"
                                                )
                                            }
                                        }
                                    }
                                }
                            },
                        imageVector = Icons.Rounded.Smartphone,
                        contentDescription = null
                    )
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = Color.Cyan
            ) {

            }
        }
    ) { innerPadding ->
        val scrollState = rememberScrollState()
        var isRefreshing by remember { mutableStateOf(false) }
//        val pullToRefreshState = rememberPullToRefreshState()
        val count = 15
        PullToRefreshBox(
            isRefreshing = isRefreshing,
            onRefresh = {
                scope.launch {
                    isRefreshing = true
                    delay(3000.milliseconds)
                    isRefreshing = false
                }
            },
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            LazyColumn {
                items(count) { position ->
                    GetColumnOfTexts()
                }
            }
//        LazyVerticalGrid(
//            modifier = Modifier
//                .padding(innerPadding),
//            columns = GridCells.Fixed(2)
//        ) {
//            items(count) { position ->
//                GetColumnOfTexts()
//            }
//        }

//        LazyVerticalStaggeredGrid(
//            modifier = Modifier
//                .padding(innerPadding),
//            columns = StaggeredGridCells.Fixed(2)
//        ) {
//            items(count) { position ->
//                GetColumnOfTexts()
//            }
//        }
            if (showDialog) {
                Dialog(
                    onDismissRequest = {
                        showDialog = false
                    }
                ) {
                    Card(
                        modifier = Modifier.size(200.dp)
                    ) {
                        Text("TesteDialog")
                    }
                }
            }
        }
    }
}

@Composable
private fun GetColumnOfTexts() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Teste1",
            fontSize = 20.sp
        )
        Text(
            text = "Teste2",
            fontSize = 20.sp
        )
        Text(
            text = "Teste3",
            fontSize = 20.sp
        )
    }
}

@Composable
fun HomeBox() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(200.dp)
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(Color.Black)
            ) {
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .background(Color.Green)
                ) {
                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .background(Color.Red)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun HomeBoxPreview() {
    HomeScaffold()
}


