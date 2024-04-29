package com.example.clubquest

import android.annotation.SuppressLint
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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.room.Room
import coil.compose.rememberImagePainter
import com.example.clubquest.ui.theme.ClubQuestTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class JerseysSearch : ComponentActivity() {

    var jersey = ""

    var allLeaguesMain = mutableListOf<String>()

    var teamIDList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        jersey = intent.getStringExtra("Name").toString()




        setContent {
            ClubQuestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    JerseyPageContent("Android")
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun JerseyPageContent(name: String, modifier: Modifier = Modifier) {


        Column {
            TopAppBar(
                title = {
                    Text(
                        text = "Jerseys",
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 20.dp)
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            val goBack = Intent(this@JerseysSearch, MainActivity::class.java)
                            startActivity(goBack)
                        },
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
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
                    painter = painterResource (id = R.drawable.bg3),
                    contentDescription = "Background image",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.matchParentSize()
                )
                Column(
                    modifier = Modifier
                        .fillMaxSize()

                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState()),

                        ) {



//                        viewAllLeagues()

                        allLeaguesMain.add("English Premier League")
                        allLeaguesMain.add("English League Championship")
                        allLeaguesMain.add("Scottish Premier League")



                        collectTeamIDs()


                    }


                }
            }
        }
    }




    @SuppressLint("CoroutineCreationDuringComposition")
    @Composable
    fun viewAllLeagues(){

        val coroutineScope = rememberCoroutineScope()

        coroutineScope.launch{

            allLeaguesMain = fetchLeagues()
            Log.i("","all leagues length ${allLeaguesMain.size}")


        }

    }

    @SuppressLint("CoroutineCreationDuringComposition")
    @Composable
    fun collectTeamIDs(){

        val coroutineScope = rememberCoroutineScope()

        coroutineScope.launch{

            for (i in allLeaguesMain){
                fetchTeams("${i}")
            }

            Log.i("","length id list: ${teamIDList.size}")

            for (j in teamIDList){
                Log.i("","$j")
            }

        }

    }


    suspend fun fetchLeagues(): MutableList<String> {

        val url_string = "https://www.thesportsdb.com/api/v1/json/3/all_leagues.php"
        val url = URL(url_string)
        val con: HttpURLConnection = url.openConnection() as HttpURLConnection
        // collecting all the JSON string
        var stb = StringBuilder()
        // run the code of the launched coroutine in a new thread
        withContext(Dispatchers.IO) {
            try {
                var bf = BufferedReader(InputStreamReader(con.inputStream))
                var line: String? = bf.readLine()
                while (line != null) { // keep reading until no more lines of text
                    stb.append(line + "\n")
                    line = bf.readLine()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Log.i("","Error error")
            } finally {
                con.disconnect()
            }
        }
        // Call parseJSON function here
        val parsedData = parseJSONleagues(stb)
        // Return the parsed data
        return parsedData
    }

    fun parseJSONleagues(stb: StringBuilder): MutableList<String> {
        val json = JSONObject(stb.toString())
        val jsonArray: JSONArray = json.getJSONArray("leagues")

        val allLeague = mutableListOf<String>()

        for (i in 0 until jsonArray.length()) {
            val league: JSONObject = jsonArray.getJSONObject(i)

            val strLeague = league.getString("strLeague")

            allLeague.add("${strLeague}")
        }

        return allLeague
    }

    suspend fun fetchTeams(leagueName:String) {

        var Name = leagueName.replace(" ", "%20")

        try{

            val url_string = "https://www.thesportsdb.com/api/v1/json/3/search_all_teams.php?l=${Name}"
            val url = URL(url_string)
            val con: HttpURLConnection = url.openConnection() as HttpURLConnection
            // collecting all the JSON string
            var stb = StringBuilder()
            // run the code of the launched coroutine in a new thread
            withContext(Dispatchers.IO) {
                try {
                    var bf = BufferedReader(InputStreamReader(con.inputStream))
                    var line: String? = bf.readLine()
                    while (line != null) { // keep reading until no more lines of text
                        stb.append(line + "\n")
                        line = bf.readLine()
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    Log.i("","Error error")
                } finally {
                    con.disconnect()
                }
            }
            // Call parseJSON function here
            val parsedData = parseJSONteams(stb)
            // Return the parsed data
            return parsedData

        }catch (e: Exception) {
            e.printStackTrace()
            Log.e("JerseysSearch", "Error fetching teams: ${e.message}")
        }
    }

    fun parseJSONteams(stb: StringBuilder){


        try{
            val json = JSONObject(stb.toString())
            val jsonArray: JSONArray = json.getJSONArray("teams")

//        val allTeams = mutableListOf<String>()

            for (i in 0 until jsonArray.length()) {
                val league: JSONObject = jsonArray.getJSONObject(i)

                val strTeam = league.getString("strTeam")
                val idTeam = league.getString("idTeam")

                if (strTeam.lowercase().contains("${jersey}")){
                    teamIDList.add("${idTeam}")
                }

//            allLeague.add("${strLeague}")
            }
        }catch (e: JSONException) {
            e.printStackTrace()
            Log.e("JerseysSearch", "Error parsing JSON teams: ${e.message}")
        }



    }





}


