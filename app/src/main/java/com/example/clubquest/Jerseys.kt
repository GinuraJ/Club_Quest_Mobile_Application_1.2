package com.example.clubquest

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.FontScaling
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.clubquest.ui.theme.ClubQuestTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class Jerseys : ComponentActivity() {

    var jersey = ""

//    val teamNames: MutableList<String> = mutableListOf()
//
//    val jerseysLink: MutableList<List<String>> = mutableListOf()

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

        var teamNames by remember { mutableStateOf(emptyList<String>()) }
        var jerseysLink by remember { mutableStateOf(emptyList<List<String>>()) }

        // Only fetch data if it hasn't been fetched before
        if (teamNames.isEmpty() || jerseysLink.isEmpty()) {
            val (names, jerseys) = searchClubsByName("$jersey")
            teamNames = names
            jerseysLink = jerseys
        }

        Column {
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
                            val goBack = Intent(this@Jerseys, MainActivity::class.java)
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



//                    searchClubsByName("$jersey")



                    Box(
                        modifier = Modifier
                            .padding(20.dp)
                    ) {
                        Text(
                            text = "$jersey",
                            modifier = Modifier
//                                .background(Color.Yellow)
                                .fillMaxWidth(),
                        )
                    }


//                    Text(text = "${jerseysLink.get(0).get(0)}")

                    val links = jerseysLink.get(0)


                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Yellow)
//                            .verticalScroll(rememberScrollState()),

                    ) {
                        jerseyImages(links)
//                        for(i:String in links){
//                            imageHandling("$i")
//                        }

                    }


                }
            }
        }
    }

    private fun searchClubsByName1(searchString: String) {
        GlobalScope.launch(Dispatchers.IO) {
            val url = URL("https://www.thesportsdb.com/api/v1/json/3/searchteams.php?t=${searchString}")
            val connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            val responseCode = connection.responseCode
            if (responseCode == HttpURLConnection.HTTP_OK) {
                val response = StringBuilder()
                val reader = BufferedReader(InputStreamReader(connection.inputStream))
                var line: String?
                while (reader.readLine().also { line = it } != null) {
                    response.append(line)
                }
                reader.close()
                val jsonArray = JSONObject(response.toString()).getJSONArray("teams")
                for (i in 0 until jsonArray.length()) {
                    val club = jsonArray.getJSONObject(i)
                    val clubName = club.getString("strTeam")
                    val clubId = club.getString("idTeam")
                    val jerseys = lookupJerseysForClub(clubId)
                    // Display jerseys to the user
                    displayJerseys(clubName, jerseys)
                }
            }
            connection.disconnect()
        }
    }


    private fun searchClubsByName(searchString: String): Pair<List<String>, List<List<String>>> {
        val names = mutableListOf<String>()
        val links = mutableListOf<List<String>>()

        GlobalScope.launch(Dispatchers.IO) {
            val url = URL("https://www.thesportsdb.com/api/v1/json/3/searchteams.php?t=${searchString}")
            val connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            val responseCode = connection.responseCode
            if (responseCode == HttpURLConnection.HTTP_OK) {
                val response = StringBuilder()
                val reader = BufferedReader(InputStreamReader(connection.inputStream))
                var line: String?
                while (reader.readLine().also { line = it } != null) {
                    response.append(line)
                }
                reader.close()
                val jsonArray = JSONObject(response.toString()).getJSONArray("teams")
                for (i in 0 until jsonArray.length()) {
                    val club = jsonArray.getJSONObject(i)
                    val clubName = club.getString("strTeam")
                    val clubId = club.getString("idTeam")
                    val jerseys = lookupJerseysForClub(clubId)
                    names.add(clubName)
                    links.add(jerseys)
                }
            }
            connection.disconnect()
        }


        Thread.sleep(2000)

        return Pair(names, links)
    }


    private fun lookupJerseysForClub(clubId: String): List<String> {
        val url = URL("https://www.thesportsdb.com/api/v1/json/3/lookupequipment.php?id=${clubId}")
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "GET"
        val responseCode = connection.responseCode
        val jerseys = mutableListOf<String>()
        if (responseCode == HttpURLConnection.HTTP_OK) {
            val response = StringBuilder()
            val reader = BufferedReader(InputStreamReader(connection.inputStream))
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                response.append(line)
            }
            reader.close()
            val jsonArray = JSONObject(response.toString()).getJSONArray("equipment")
            for (i in 0 until jsonArray.length()) {
                val equipment = jsonArray.getJSONObject(i)
                val jersey = equipment.getString("strEquipment")
                jerseys.add(jersey)
            }
        }
        connection.disconnect()
        return jerseys
    }

    private fun displayJerseys(clubName: String, jerseys: List<String>) {

//        teamNames.add("$clubName")
//        jerseysLink.add(jerseys)

        Log.i("","hello $clubName")
        Log.i("","hello ${jerseys.get(0)}")
        Log.i("","hello ${jerseys.get(1)}")



    }

    @Composable
    fun jerseyImages(listItems: List<String>) {
        LazyColumn {
            items(listItems) { item ->
                // Each item in the list will be displayed here
                imageHandling("$item")
                Spacer(modifier = Modifier.height(5.dp))
            }
        }

    }

    @Composable
    fun imageHandling(url: String){

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

//    @Composable
//    fun ScrollingColumn(links: List<String>) {
//        // Create a scroll state
//        val scrollState = rememberScrollbarAdapter(scrollState = androidx.compose.foundation.lazy.rememberLazyListState())
//
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .background(Color.Yellow)
//                .verticalScroll(scrollState) // Enable vertical scrolling
//        ) {
//            jerseyImages(links)
//        }
//    }




}


