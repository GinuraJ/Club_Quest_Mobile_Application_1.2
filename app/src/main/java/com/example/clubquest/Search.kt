package com.example.clubquest

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.State
import androidx.room.Room
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.example.clubquest.ui.theme.ui.theme.ClubQuestTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class Search : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ClubQuestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SearchClubContent()
                }
            }
        }
    }


    @Composable
    @OptIn(ExperimentalMaterial3Api::class)
    fun SearchClubContent() {

        val db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "leaguess").build()
        val leagueDao = db.leagueDoa()
        var searchKeyword by remember { mutableStateOf("") }
        var searchResults by remember { mutableStateOf<List<League>>(emptyList()) }

        Column{
            TopAppBar(
                title = {
                    Text(
                        text = "Search Club",
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 20.dp)
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            val goBack = Intent(this@Search, MainActivity::class.java)
                            startActivity(goBack)
                        },
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            GlobalScope.launch(Dispatchers.IO){
                                leagueDao.deleteAll()
                            }
                        },
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Delete,
                            contentDescription = null
                        )
                    }
                },

            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Image(
                    painter = painterResource (id = R.drawable.bgsearch),
                    contentDescription = "Background image",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.matchParentSize()
                )
                Column(
                    modifier = Modifier
                        .fillMaxSize()
//                    .background(Color.Blue)
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .weight(1f)
                    ) {
                        Text(
                            text = "Search Club",
                            fontSize = 34.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(start = 5.dp, top = 10.dp, bottom = 20.dp)
                        )

                        OutlinedTextField(
                            modifier = Modifier
                                .padding(4.dp)
//                            .background(Color.Blue)
                                .fillMaxWidth(),
                            value = searchKeyword, // Provide a non-null initial value here
                            onValueChange = { searchKeyword = it },
                            enabled = true,
                            label = {
                                Text(
                                    text = "Enter Name"
                                )
                            }
                        )

                        Button(
                            onClick = { searchResults = searchLeagues(leagueDao, searchKeyword.lowercase()) },
                            shape = RoundedCornerShape(20),
                            modifier = Modifier
                                .padding(top = 10.dp, bottom = 10.dp)
                                .fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(31,56,83)

                            )
                        )
                        {
                            Text(
                                text = "Search",
                                fontSize = 18.sp,
                                modifier = Modifier
                                    .padding(10.dp)
                            )
                        }
                        LazyColumn(
                            modifier = Modifier
//                                .background(Color(146, 203, 255))
                                .background(Color(31, 56, 83))
                                .fillMaxHeight()
                                .weight(2f)
                        ) {
                            items(searchResults) { league ->
                                Column(
                                    modifier = Modifier
                                        .padding(start = 10.dp, end = 10.dp, top = 10.dp)
                                        .background(Color.White)

                                ) {
//                                    if(league.strLogo != ""){
//                                        image("${league.strLogo}")
//                                    }else if(league.strLogo == "null"){
//                                        image("https://salonlfc.com/wp-content/uploads/2018/01/image-not-found-1-scaled-1150x647.png")
//                                    }
//                                    else{
//                                        image("https://salonlfc.com/wp-content/uploads/2018/01/image-not-found-1-scaled-1150x647.png")
//                                    }

                                    if(league.strLogo?.contains("https") == true){
                                        image("${league.strLogo}")
                                    }
                                    else{
                                        image("https://salonlfc.com/wp-content/uploads/2018/01/image-not-found-1-scaled-1150x647.png")
                                    }

                                    Text(
                                        text = "League ID: ${league.leagueId}\n" +
                                                "Name: ${league.strLeague}\n" +
                                                "Sport: ${league.strSport}\n" +
                                                "Alternate Name: ${league.strLeagueAlternate}\n" +
                                                "Logo: ${league.strLogo}",
                                        modifier = Modifier
                                            .padding(10.dp)
                                            .fillMaxWidth()
//                                            .background(Color.White)

                                    )
                                }
                            }
                        }
                    }
                }
            }
        }



    }


    fun searchLeagues(leagueDao: LeagueDoa, keyword: String): List<League> {
        return runBlocking {
            leagueDao.search("%$keyword%")
        }
    }

    @Composable
    fun image(url: String){

        Box(
            modifier = Modifier
                .background(Color(31, 56, 83))
                .padding(bottom = 10.dp)
                .height(150.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ){
            val painter = rememberImagePainter(data = url,
                builder = {

                }
            )

            Image(painter = painter, contentDescription = "LOGO")

        }


    }


}




