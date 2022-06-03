package com.dairymaster.composebizcard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dairymaster.composebizcard.ui.theme.ComposeBizCardTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBizCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    CreateComposeCard()
                }
            }
        }
    }
}

@Composable
fun CreateComposeCard() {

    // special val that has a mutable state that will be
    // remembered
    val buttonClickedState = remember {
        mutableStateOf(false)
    }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Card(
            modifier = Modifier
                .width(200.dp)
                .height(390.dp)
                .padding(16.dp),
            elevation = 6.dp,
            shape = RoundedCornerShape(corner = CornerSize(15.dp))
        ) {
            Column(
                modifier = Modifier.height(300.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ProfilePic()

                Divider()

                ProfileInfo()

                ProfileButton(buttonClickedState)

                if (buttonClickedState.value) {
                    Content()
                } else {
                    Box {} // hide content
                }
            }
        }
    }
}

@Composable
fun Content() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(6.dp)
    ) {
        Surface(
            modifier = Modifier
                .padding(3.dp)
                .fillMaxSize(),
            shape = RoundedCornerShape(corner = CornerSize(6.dp)),
            border = BorderStroke(width = 3.dp, Color.LightGray)
        ) {
            Portfolio(
                data = listOf(
                    "Project 1",
                    "Project 2",
                    "Project 3",
                    "Project 4",
                    "Project 5",
                    "Project 6"
                )
            )
        }
    }
}

@Composable
fun Portfolio(data: List<String>) {
    LazyColumn {
        items(data) { item ->
            Card(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                shape = RectangleShape
            ) {
                Row(
                    modifier = Modifier
                        .padding(8.dp)
                        .background(MaterialTheme.colors.surface)
                        .padding(7.dp)
                ) {
                    ProfilePic(modifier = Modifier.size(65.dp))
                    Column(
                        modifier = Modifier.padding(8.dp).align(alignment = Alignment.CenterVertically)
                    ) {
                        Text(text = item, fontWeight = FontWeight.Bold)
                        Text(text = "A Great Project!", style = MaterialTheme.typography.body2)
                    }
                }
            }
        }
    }
}

@Composable
private fun ProfileButton(buttonClickedState: MutableState<Boolean>) {
    Button(onClick = {
        buttonClickedState.value = !buttonClickedState.value
    }) {
        Text(
            text = "Portfolio",
            style = MaterialTheme.typography.button
        )
    }
}

@Composable
private fun ProfileInfo() {
    Column(modifier = Modifier.padding(6.dp)) {
        Text(
            text = "Chris D.",
            style = MaterialTheme.typography.h4,
            color = MaterialTheme.colors.primaryVariant
        )
        Text(
            text = "Android Compose Programmer",
            modifier = Modifier.padding(4.dp)
        )
        Text(
            text = "@composeDevel",
            modifier = Modifier.padding(4.dp),
            style = MaterialTheme.typography.subtitle1
        )
    }
}

@Composable
fun ProfilePic(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .size(150.dp)
            .padding(8.dp),
        shape = CircleShape,
        border = BorderStroke(0.5.dp, Color.LightGray),
        elevation = 8.dp,
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.5f)
    ) {
        Image(
            painter = painterResource(id = R.drawable.profile_image),
            contentDescription = "Profile Image",
            modifier = modifier.size(135.dp),
            contentScale = ContentScale.Crop
        )

    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeBizCardTheme {
        CreateComposeCard()
    }
}