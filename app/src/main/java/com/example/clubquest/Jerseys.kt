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
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class Jerseys : ComponentActivity() {

    var jersey = ""

//    val idList = mutableListOf<String>()

    val idList = mutableMapOf<String, String>()

    var result =  mutableMapOf<String, String>()

    var fMap = mutableMapOf<String,Map<String,String>>()

    var fMap2 = mapOf<String,Map<String,String>>()



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



        val db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "DB-final").build()
        val teamsDoa = db.teamsDoa()
        var searchResults by remember { mutableStateOf<List<Teams>>(emptyList()) }


        searchResults = searchLeagues(teamsDoa, jersey.lowercase())

        for (obj in searchResults){
            idList["${obj.Name}"] = "${obj.idTeam}"
        }


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

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState()),

                    ) {
//                        jerseyImages(links)
//                        for(i:String in links){
//                            imageHandling("$i")
//                        }

                        var done by remember { mutableStateOf(false) }

                        kkk { result ->
                            tt(result)
                            done = true
                        }

                        if(done == true){
                            display()
                        }

                    }


                }
            }
        }
    }

    fun searchLeagues(TeamsDAO: TeamsDAO, keyword: String): List<Teams> {
        return runBlocking {
            TeamsDAO.search("%$keyword%")
        }
    }

    suspend fun fetchBooks(keyword: String): MutableMap<String, String> {


        val url_string = "https://www.thesportsdb.com/api/v1/json/3/lookupequipment.php?id=$keyword"
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


    fun parseJSON(stb: StringBuilder): MutableMap<String, String> {
        val json = JSONObject(stb.toString())
        val jsonArray: JSONArray = json.getJSONArray("equipment")

        val leagueMap = mutableMapOf<String, String>()

        for (i in 0 until jsonArray.length()) {
            val league: JSONObject = jsonArray.getJSONObject(i)

            val idLeague = league.getString("strSeason")
            val strTeam = league.getString("strEquipment")

            leagueMap[idLeague] = strTeam
        }

        return leagueMap
    }

    @SuppressLint("CoroutineCreationDuringComposition")
    @Composable
    fun kkk(onResultReady: (Map<String, String>) -> Unit){

        val coroutineScope = rememberCoroutineScope()
        for(obj in idList){

            var temMap =  mutableMapOf<String, String>()

            coroutineScope.launch {
                result = fetchBooks(obj.value)

                Log.i("","result size : ${result.size}")
                Log.i("","name : ${obj.key}")
                Log.i("","team id : ${obj.value}")

                for ((key, value) in result) {
                    Log.i("","Key: $key, Value: $value")
                    temMap["${key}"] = "${value}"
                }

                fMap["${obj.key}"] = temMap

//                temMap.add(result)

                // Once result is ready, trigger the callback
                onResultReady(result)
            }

        }
    }

    fun tt(result: Map<String, String>){
        Log.i("","tt ${result.size}")
        fMap2 = fMap
    }

    @Composable
    fun display(){

        for((key, value ) in fMap2){


            Box(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()

            ) {
                Text(
                    text = "${key}",
                    fontSize = 30.sp,
                    modifier = Modifier
                        .padding(10.dp)
                )
            }

            for((date,link) in value){
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(207, 92, 54)),
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    Column(
                        modifier = Modifier
                            .weight(1f),
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Text(
                            text = "${date}",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(20.dp)
                        )
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1.3f)
                            .background(Color(5, 5, 23))
                    ){
                        imageHandling(url = "${link}")
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
    }

    @Composable
    fun imageHandling(url: String){
        Box(
            modifier = Modifier
                .padding(bottom = 10.dp, top = 20.dp)
                .height(150.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ){
            val painter = rememberImagePainter(data = url,
                builder = {}
            )
            Image(painter = painter, contentDescription = "LOGO")
        }
    }
}


