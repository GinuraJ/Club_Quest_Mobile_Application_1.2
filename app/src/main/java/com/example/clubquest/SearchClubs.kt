package com.example.clubquest

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.room.Database
import androidx.room.Room
import com.example.clubquest.ui.theme.ui.theme.ClubQuestTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL



class SearchClubs : ComponentActivity() {

    val leagueList = mutableListOf<Teams>()


    @OptIn(ExperimentalMaterial3Api::class)
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


        var bookInfoDisplay by remember { mutableStateOf(" ") }
        // the book title keyword to search for
        var keyword by remember { mutableStateOf("") }
        // Creates a CoroutineScope bound to the GUI composable lifecycle
        val scope = rememberCoroutineScope()

        var showDialog by remember { mutableStateOf(false) }

        // Function to show the dialog
        fun showAlertDialog() {
            showDialog = true
        }

        Column{
            TopAppBar(
                title = {
                    Text(
                        text = "Search Club",
                        textAlign = TextAlign.Right,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 20.dp)
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            val goBack = Intent(this@SearchClubs,MainActivity::class.java)
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
                    painter = painterResource (id = R.drawable.bg2),
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
                            .weight(1.5f)
                    ) {
                        Text(
                            text = "Football league name",
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
                            value = keyword,
                            onValueChange = { keyword = it },
                            enabled = true,
                            label = {
                                Text(
                                    text = "Enter Name"
                                )
                            }
                        )
//                        TextField(
//                            modifier = Modifier
//                                .padding(4.dp)
////                            .background(Color.Blue)
//                                .fillMaxWidth(),
//                            value = keyword,
//                            onValueChange = { keyword = it }
//                        )

                        Button(
                            onClick = {

                                scope.launch {
                                    // Set loading state
//                                    isLoading = true
                                    // Fetch data
                                    val result = fetchBooks(keyword)
                                    // Set bookInfoDisplay with result
                                    bookInfoDisplay = result
                                    // Reset loading state
//                                    isLoading = false
                                }

                                Log.i("","Ginura ${leagueList.size}")



                            },
                            shape = RoundedCornerShape(20),
                            modifier = Modifier
                                .padding(top = 10.dp, bottom = 10.dp)
                                .fillMaxWidth()
                        )
                        {
                            Text(
                                text = "Retrieve Clubs",
                                fontSize = 18.sp,
                                modifier = Modifier
                                    .padding(10.dp)
                            )
                        }

                        Button(
                            onClick = {
                                scope.launch {
                                    retrieveWebData()
                                    showAlertDialog()
                                }
                            },
                            shape = RoundedCornerShape(20),
                            modifier = Modifier
                                .fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(31,56,83)

                            )
                        )
                        {
                            Text(
                                text = "Save clubs to Database",
                                fontSize = 18.sp,
                                modifier = Modifier
                                    .padding(10.dp)
                            )
                        }


                        if (showDialog) {
                            AlertDialog(
                                onDismissRequest = { showDialog = false },
                                title = { Text("Database Updated") },
                                text = { Text("Clubs saved successfully!") },
                                confirmButton = {
                                    TextButton(
                                        onClick = { showDialog = false },
                                        colors = ButtonDefaults.textButtonColors(contentColor = Color.Red)
                                    ) {
                                        Text("Close")
                                    }
                                }
                            )
                        }
                    }

                    Column(
                        modifier = Modifier
//                            .background(Color.Blue)
                            .fillMaxWidth()
                            .weight(1.5f)
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(10.dp)
                                .background(Color.Yellow)
                                .verticalScroll(rememberScrollState()),
                            text = "${bookInfoDisplay}"
                        )
                    }
                }
            }
        }
    }

    suspend fun fetchBooks(keyword: String): String {

        var test = keyword.replace(" ", "%20")

        val url_string = "https://www.thesportsdb.com/api/v1/json/3/search_all_teams.php?l=$test"
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
//        return stb.toString()
        // Call parseJSON function here
        val parsedData = parseJSON(stb)
        // Return the parsed data
        return parsedData
    }


    fun parseJSON(stb: StringBuilder): String {
        // this contains the full JSON returned by the Web Service
        val json = JSONObject(stb.toString())
        // Information about all the leagues extracted by this function
        var allLeagues = StringBuilder()
        var jsonArray: JSONArray = json.getJSONArray("teams")
        // extract all the leagues from the JSON array
        for (i in 0 until jsonArray.length()) {
            val league: JSONObject = jsonArray.getJSONObject(i)
            // extract the required fields
            val idTeam = league.getString("idTeam")
            val strTeam = league.getString("strTeam")
            val strTeamShort = league.getString("strTeamShort")
            val strAlternate = league.optString("strAlternate", "")
            val intFormedYear = league.getString("intFormedYear")

            val idLeague = league.getString("idLeague")
            val strLeague = league.getString("strLeague")
            val strStadium = league.getString("strStadium")
            val strKeywords = league.getString("strKeywords")
            val strStadiumThumb = league.getString("strStadiumThumb")

            val strStadiumLocation = league.getString("strStadiumLocation")
            val intStadiumCapacity = league.getString("intStadiumCapacity")
            val strWebsite = league.getString("strWebsite")
            val strTeamJersey = league.getString("strTeamJersey")
            val strTeamLogo = league.getString("strTeamLogo")


            allLeagues.append("${i + 1}---------------------\n " +
                    "idTeam: $idTeam\n" +
                    "strTeam: $strTeam\n" +
                    "strTeamShort: $strTeamShort\n"+
                    "strAlternate: $strAlternate\n"+
                    "intFormedYear: $intFormedYear\n"+

                    "strLeague: $strLeague\n"+
                    "idLeague: $idLeague\n" +
                    "strStadium: $strStadium\n"+
                    "strKeywords: $strKeywords\n"+
                    "strStadiumThumb: $strStadiumThumb\n"+

                    "strStadiumLocation: $strStadiumLocation\n"+
                    "intStadiumCapacity: $intStadiumCapacity\n"+
                    "strWebsite: $strWebsite\n"+
                    "strTeamJersey: $strTeamJersey\n"+
                    "strTeamLogo: $strTeamLogo\n"

            )

            allLeagues.append("\n\n")


            var l = Teams(idTeam = idTeam, Name = strTeam, strTeamShort = strTeamShort, strAlternate = strAlternate, intFormedYear = intFormedYear, strLeague = strLeague, idLeague = idLeague, strStadium = strStadium, strKeywords = strKeywords,strStadiumThumb = strStadiumThumb,strStadiumLocation = strStadiumLocation,   intStadiumCapacity = intStadiumCapacity,strWebsite = strWebsite, strTeamJersey = strTeamJersey, strTeamLogo = strTeamLogo )
            leagueList.add(l)
        }
        return allLeagues.toString()
    }

    suspend fun retrieveWebData(){

        val db = Room.databaseBuilder(applicationContext ,AppDatabase::class.java, "DB").build()

        val teamsDoa = db.teamsDoa()

        for(obj in leagueList){
            GlobalScope.launch(Dispatchers.IO){
                teamsDoa.insertTeam(obj)
            }
        }
        leagueList.clear()
    }

}

