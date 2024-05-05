package com.example.clubquest

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.room.Room
import com.example.clubquest.ui.theme.ClubQuestTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch



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


        val db = Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "DB-final"
        ).build()

        val leagueDao = db.leagueDoa()

        fun saveNewLeague() {
            val League01 = League(leagueId = "4328", strLeague = "English Premier League",strSport ="Soccer",strLeagueAlternate ="Premier League, EPL")
            val League02 = League(leagueId = "4329", strLeague = "English League Championship",strSport ="Soccer",strLeagueAlternate ="Championship")
            val League03 = League(leagueId = "4330", strLeague = "Scottish Premier League",strSport ="Soccer",strLeagueAlternate ="Scottish Premiership, SPFL")
            val League04 = League(leagueId = "4331", strLeague = "German Bundesliga",strSport ="Soccer",strLeagueAlternate ="Bundesliga, FuÃŸball-Bundesliga")
            val League05 = League(leagueId = "4332", strLeague = "Italian Serie A",strSport ="Soccer",strLeagueAlternate ="Serie A")
            val League06 = League(leagueId = "4334", strLeague = "French Ligue 1",strSport ="Soccer",strLeagueAlternate ="Ligue 1 Conforama")
            val League07 = League(leagueId = "4335", strLeague = "Spanish La Liga",strSport ="Soccer",strLeagueAlternate ="LaLiga Santander, La Liga")
            val League08 = League(leagueId = "4336", strLeague = "Greek Superleague Greece",strSport ="Soccer",strLeagueAlternate ="")
            val League09 = League(leagueId = "4337", strLeague = "Dutch Eredivisie",strSport ="Soccer",strLeagueAlternate ="Eredivisie")
            val League10 = League(leagueId = "4338", strLeague = "Belgian First Division A",strSport ="Soccer",strLeagueAlternate ="Jupiler Pro League")
            val League11 = League(leagueId = "4339", strLeague = "Turkish Super Lig",strSport ="Soccer",strLeagueAlternate ="Super Lig")
            val League12 = League(leagueId = "4340", strLeague = "Danish Superliga",strSport ="Soccer",strLeagueAlternate ="")
            val League13 = League(leagueId = "4344", strLeague = "Portuguese Primeira Liga",strSport ="Soccer",strLeagueAlternate ="Liga NOS")
            val League14 = League(leagueId = "4346", strLeague = "American Major League Soccer",strSport ="Soccer",strLeagueAlternate ="MLS, Major League Soccer")
            val League15 = League(leagueId = "4347", strLeague = "Swedish Allsvenskan",strSport ="Soccer",strLeagueAlternate ="Fotbollsallsvenskan")
            val League16 = League(leagueId = "4350", strLeague = "Mexican Primera League",strSport ="Soccer",strLeagueAlternate ="Liga MX")
            val League17 = League(leagueId = "4351", strLeague = "Brazilian Serie A",strSport ="Soccer",strLeagueAlternate ="")
            val League18 = League(leagueId =  "4354", strLeague = "Ukrainian Premier League",strSport ="Soccer",strLeagueAlternate ="")
            val League19 = League(leagueId = "4355", strLeague = "Russian Football Premier League",strSport ="Soccer",strLeagueAlternate ="Ð§ÐµÐ¼Ð¿Ð¸Ð¾Ð½Ð°Ñ‚ Ð Ð¾ÑÑÐ¸Ð¸ Ð¿Ð¾ Ñ„ÑƒÑ‚Ð±Ð¾Ð»Ñƒ")
            val League20 = League(leagueId = "4356", strLeague = "Australian A-League",strSport ="Soccer",strLeagueAlternate ="A-League")
            val League21 = League(leagueId = "4358", strLeague = "Norwegian Eliteserien",strSport ="Soccer",strLeagueAlternate ="Eliteserien")
            val League22 = League(leagueId = "4359", strLeague = "Chinese Super League",strSport ="Soccer",strLeagueAlternate ="")
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
                                .fillMaxWidth(0.8f)
                        ) {
                            Text(
                                text = "Add Leagues to DB",
                                fontSize = 20.sp,
                                modifier = Modifier
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
                                startActivity(goToSearchClubs)
                            },
                            shape = RoundedCornerShape(20),
                            modifier = Modifier
                                .fillMaxHeight(0.5f)
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
                                .fillMaxWidth(0.8f)
                        ) {
                            Text(
                                text = "Search for Clubs",
                                fontSize = 20.sp,
                                modifier = Modifier
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
                                val goToJersey = Intent(this@MainActivity, JerseysSearch::class.java)
                                startActivity(goToJersey)

                            },
                            shape = RoundedCornerShape(20),
                            modifier = Modifier
                                .fillMaxHeight(0.5f)
                                .fillMaxWidth(0.8f)
                        ) {
                            Text(
                                text = "Search jerseys",
                                fontSize = 20.sp,
                                modifier = Modifier
                            )
                        }
                    }

                }
            }
        }
    }


}

