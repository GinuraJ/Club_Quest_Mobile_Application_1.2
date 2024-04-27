package com.example.clubquest

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.clubquest.ui.theme.ClubQuestTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext



class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ClubQuestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainPageContent(name = "Android", context = applicationContext)
                }
            }
        }
    }

    @Composable
    fun MainPageContent(name: String, context: Context, modifier: Modifier = Modifier){

        var jerseyName by remember { mutableStateOf("") }

        val db = Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "leaguess"
        ).build()

        val leagueDao = db.leagueDoa()

        fun saveNewLeague() {
            val League01 = League(leagueId = "4328", strLeague = "English Premier League",strSport ="Soccer",strLeagueAlternate ="Premier League, EPL", strLogo = "")
            val League02 = League(leagueId = "4329", strLeague = "English League Championship",strSport ="Soccer",strLeagueAlternate ="Championship", strLogo = "")
            val League03 = League(leagueId = "4330", strLeague = "Scottish Premier League",strSport ="Soccer",strLeagueAlternate ="Scottish Premiership, SPFL", strLogo = "")
            val League04 = League(leagueId = "4331", strLeague = "German Bundesliga",strSport ="Soccer",strLeagueAlternate ="Bundesliga, FuÃŸball-Bundesliga", strLogo = "")
            val League05 = League(leagueId = "4332", strLeague = "Italian Serie A",strSport ="Soccer",strLeagueAlternate ="Serie A", strLogo = "")
            val League06 = League(leagueId = "4334", strLeague = "French Ligue 1",strSport ="Soccer",strLeagueAlternate ="Ligue 1 Conforama", strLogo = "")
            val League07 = League(leagueId = "4335", strLeague = "Spanish La Liga",strSport ="Soccer",strLeagueAlternate ="LaLiga Santander, La Liga", strLogo = "")
            val League08 = League(leagueId = "4336", strLeague = "Greek Superleague Greece",strSport ="Soccer",strLeagueAlternate ="", strLogo = "")
            val League09 = League(leagueId = "4337", strLeague = "Dutch Eredivisie",strSport ="Soccer",strLeagueAlternate ="Eredivisie", strLogo = "")
            val League10 = League(leagueId = "4338", strLeague = "Belgian First Division A",strSport ="Soccer",strLeagueAlternate ="Jupiler Pro League", strLogo = "")
            val League11 = League(leagueId = "4339", strLeague = "Turkish Super Lig",strSport ="Soccer",strLeagueAlternate ="Super Lig", strLogo = "")
            val League12 = League(leagueId = "4340", strLeague = "Danish Superliga",strSport ="Soccer",strLeagueAlternate ="", strLogo = "")
            val League13 = League(leagueId = "4344", strLeague = "Portuguese Primeira Liga",strSport ="Soccer",strLeagueAlternate ="Liga NOS", strLogo = "")
            val League14 = League(leagueId = "4346", strLeague = "American Major League Soccer",strSport ="Soccer",strLeagueAlternate ="MLS, Major League Soccer", strLogo = "")
            val League15 = League(leagueId = "4347", strLeague = "Swedish Allsvenskan",strSport ="Soccer",strLeagueAlternate ="Fotbollsallsvenskan", strLogo = "")
            val League16 = League(leagueId = "4350", strLeague = "Mexican Primera League",strSport ="Soccer",strLeagueAlternate ="Liga MX", strLogo = "")
            val League17 = League(leagueId = "4351", strLeague = "Brazilian Serie A",strSport ="Soccer",strLeagueAlternate ="", strLogo = "")
            val League18 = League(leagueId =  "4354", strLeague = "Ukrainian Premier League",strSport ="Soccer",strLeagueAlternate ="", strLogo = "")
            val League19 = League(leagueId = "4355", strLeague = "Russian Football Premier League",strSport ="Soccer",strLeagueAlternate ="Ð§ÐµÐ¼Ð¿Ð¸Ð¾Ð½Ð°Ñ‚ Ð Ð¾ÑÑÐ¸Ð¸ Ð¿Ð¾ Ñ„ÑƒÑ‚Ð±Ð¾Ð»Ñƒ", strLogo = "")
            val League20 = League(leagueId = "4356", strLeague = "Australian A-League",strSport ="Soccer",strLeagueAlternate ="A-League", strLogo = "")
            val League21 = League(leagueId = "4358", strLeague = "Norwegian Eliteserien",strSport ="Soccer",strLeagueAlternate ="Eliteserien", strLogo = "")
            val League22 = League(leagueId = "4359", strLeague = "Chinese Super League",strSport ="Soccer",strLeagueAlternate ="", strLogo = "")
            GlobalScope.launch(Dispatchers.IO) {
                leagueDao.insertUser(League01)
                leagueDao.insertUser(League02)
                leagueDao.insertUser(League03)
                leagueDao.insertUser(League04)
                leagueDao.insertUser(League05)
                leagueDao.insertUser(League06)
                leagueDao.insertUser(League07)
                leagueDao.insertUser(League08)
                leagueDao.insertUser(League09)
                leagueDao.insertUser(League10)
                leagueDao.insertUser(League11)
                leagueDao.insertUser(League12)
                leagueDao.insertUser(League13)
                leagueDao.insertUser(League14)
                leagueDao.insertUser(League15)
                leagueDao.insertUser(League16)
                leagueDao.insertUser(League17)
                leagueDao.insertUser(League18)
                leagueDao.insertUser(League19)
                leagueDao.insertUser(League20)
                leagueDao.insertUser(League21)
                leagueDao.insertUser(League22)
                leagueDao.insertUser(League22)


            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource (id = R.drawable.bg),
                contentDescription = "Background image",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.matchParentSize()
            )
            Column(
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .fillMaxHeight(0.25f),
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.Center
                ) {
                }
                Column(
                    modifier = Modifier
                        .fillMaxHeight(0.8f)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Box(
                        modifier = Modifier
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Button(
                            onClick = {
                                saveNewLeague()
                            },
                            shape = RoundedCornerShape(20),
                            modifier = Modifier
                                .fillMaxHeight(0.5f)
//                                .padding(bottom = 10.dp)
                                .fillMaxWidth(0.8f)
                        ) {
                            Text(
                                text = "Add Leagues to DB",
                                fontSize = 20.sp,
                                modifier = Modifier
//                                    .padding(10.dp)
                            )
                        }
                    }
                    Box(
                        modifier = Modifier
                            .weight(1f),
                        contentAlignment = Alignment.Center

                    ) {
                        Button(
                            onClick = {
                                val goToSearchClubs = Intent(this@MainActivity, SearchClubs::class.java)
//                                goToSearchClubs.putExtra("DATABASE_NAME", "leagues")

                                startActivity(goToSearchClubs)
                            },
                            shape = RoundedCornerShape(20),
                            modifier = Modifier
                                .fillMaxHeight(0.5f)
//                                .padding(bottom = 10.dp)
                                .fillMaxWidth(0.8f)
                        ) {
                            Text(
                                text = "Search for Clubs By League",
                                fontSize = 20.sp,
                            )
                        }
                    }
                    Box(
                        modifier = Modifier
                            .weight(1f),
                        contentAlignment = Alignment.Center

                    ) {
                        Button(
                            onClick = {
                                val goToSearch = Intent(this@MainActivity, Search::class.java)
                                startActivity(goToSearch)

                            },
                            shape = RoundedCornerShape(20),
                            modifier = Modifier
                                .fillMaxHeight(0.5f)
//                                .padding(bottom = 10.dp)
                                .fillMaxWidth(0.8f)
                        ) {
                            Text(
                                text = "Search for Clubs",
                                fontSize = 20.sp,
                                modifier = Modifier
//                                    .padding(10.dp)
                            )
                        }
                    }
                    Box(
                        modifier = Modifier
                            .weight(1f),
                        contentAlignment = Alignment.Center

                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            OutlinedTextField(
                                modifier = Modifier
                                    .height(63.dp)
//                                    .padding(4.dp)
//                            .background(Color.Blue)
//                                    .fillMaxWidth()
                                ,
                                value = jerseyName,
                                onValueChange = {jerseyName = it},
                                enabled = true,
                                label = {
                                    Text(
                                        text = "Enter Name"
                                    )
                                }
                            )
                            Box(
                                modifier = Modifier
                                    .height(55.dp)
                                    .width(55.dp)
                            ) {
                                IconButton(
                                    onClick = {
                                        val goToJersey = Intent(this@MainActivity, Jerseys::class.java)
                                        goToJersey.putExtra("Name","${jerseyName}")
                                        startActivity(goToJersey)
                                    },
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .fillMaxWidth()
//                                        .size(width = 50.dp, height = 50.dp)
                                        .background(color = Color.LightGray)
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.Search,
                                        contentDescription = null,
                                        Modifier.size(40.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }


}

