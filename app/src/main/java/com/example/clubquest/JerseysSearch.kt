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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.clubquest.ui.theme.ClubQuestTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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

    var teamIDNameMap = mutableMapOf<String, String>()

    var allLeaguesName = mutableListOf<String>()

    var teamsDateID = mutableMapOf<String,String>()

    var jerseyMap_name_linkMap = mutableMapOf<String,Map<String,String>>()

    var orientation = false



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        // Save the jerseyMap_name_linkMap
        val serializableMap = HashMap(jerseyMap_name_linkMap)
        outState.putSerializable("jerseyMap_name_linkMap", serializableMap)

        // Save the orientation flag
        outState.putBoolean("orientation", true)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        // Restore the orientation flag
        orientation = savedInstanceState.getBoolean("orientation", false)

        // Restore jerseyMap_name_linkMap
        jerseyMap_name_linkMap = savedInstanceState.getSerializable("jerseyMap_name_linkMap") as? MutableMap<String, Map<String, String>> ?: mutableMapOf()
    }


    @SuppressLint("CoroutineCreationDuringComposition")
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun JerseyPageContent(name: String, modifier: Modifier = Modifier) {

        var jerseyName by remember { mutableStateOf("") }

        var done by remember { mutableStateOf(false) }

        var searchPressed by remember { mutableStateOf(false) }



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

                        Box(
                            modifier = Modifier
                                .fillMaxWidth(),
                            contentAlignment = Alignment.Center

                        ) {
                            Column(
                            ) {
                                OutlinedTextField(
                                    modifier = Modifier
                                        .height(63.dp)
                                        .fillMaxWidth(0.95f)
                                    ,
                                    value = jerseyName,
                                    onValueChange = {jerseyName = it},
                                    enabled = true,
                                    label = {
                                        Text(
                                            text = "Enter jersey name here"
                                        )
                                    }
                                )
//                                jerseyName = jersey


                                Spacer(modifier = Modifier.height(10.dp))
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth(0.95f)
                                ) {

                                    Button(
                                        onClick = {
                                            Log.i("","jersey name : ${jerseyName}")
                                            jersey = jerseyName
                                            searchPressed = true
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
                                            text = "Search",
                                            fontSize = 18.sp,
                                            modifier = Modifier
                                                .padding(10.dp)
                                        )
                                    }
                                }
                            }
                        }

                        if(orientation == true){
                            searchPressed = true
                        }


                        if(searchPressed == true){
                            if(done == false && orientation == false){
                                progressIndicator()
                            }

                            if (orientation == false){
                                val coroutineScope = rememberCoroutineScope()

                                LaunchedEffect(Unit) {
                                    coroutineScope.launch {
                                        allLeaguesName = fetchLeagues()
                                        Log.i("","all leagues list: ${allLeaguesName}")

                                        for (i in allLeaguesName) {
                                            fetchTeams("$i")
                                        }

                                        Log.i("","${teamIDNameMap.size}")

                                        for ((id,name) in teamIDNameMap) {
                                            var jMap = fetchJerseys("$id")
                                            jerseyMap_name_linkMap["$name"] = jMap
                                        }

                                        Log.i("","JerseyMapList size = ${jerseyMap_name_linkMap.size}")

                                        done = true
                                    }
                                }
                            }else{
                                done = true
//                            jerseyMap_name_linkMap_after = jerseyMap_name_linkMap
                            }
                        }


//                        Log.i("","after ${jerseyMap_name_linkMap_after.size}")


                        if (done) {

                            for ((name,linkList) in jerseyMap_name_linkMap){


                                Box(
                                    modifier = Modifier
                                        .padding(10.dp)
                                        .fillMaxWidth()

                                ) {
                                    Text(
                                        text = "${name}",
                                        fontSize = 30.sp,
                                        modifier = Modifier
                                            .padding(10.dp)
                                    )
                                }

                                if(linkList.isEmpty()){
                                    Box(
                                        modifier = Modifier
                                            .height(100.dp)
                                            .fillMaxWidth()
                                            .background(Color.Black),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = "Not Available",
                                            textAlign = TextAlign.Center,
                                            color = Color.White,
                                        )
                                    }
                                }else{

                                    for ((date,link) in linkList){
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
                                }

                                Spacer(modifier = Modifier.height(10.dp))
                            }
                        }
                    }
                }
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
            val strSport = league.getString("strSport")

            if(strSport == "Soccer"){
                allLeague.add("${strLeague}")
            }

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

    fun parseJSONteams(stb: StringBuilder) {
        try {
            val json = JSONObject(stb.toString())

            if (json.has("teams")) {
                val jsonArray: JSONArray = json.getJSONArray("teams")

                for (i in 0 until jsonArray.length()) {
                    val league: JSONObject = jsonArray.getJSONObject(i)

                    val strTeam = league.optString("strTeam", "")
                    val idTeam = league.optString("idTeam", "")

                    if (strTeam.lowercase().contains("${jersey.lowercase()}")) {
                        teamIDNameMap["$idTeam"] = strTeam
                        Log.i("", "$idTeam $strTeam")
                    }
                }
            } else {
                Log.e("JerseysSearch", "JSON object does not contain 'teams' key")
            }
        } catch (e: JSONException) {
            e.printStackTrace()
            Log.e("JerseysSearch", "Error parsing JSON teams: ${e.message}")
        }
    }

    suspend fun fetchJerseys(keyword: String): MutableMap<String, String> {

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
        // Call parseJSON function here
        val parsedData = parseJSONjerseys(stb)
        // Return the parsed data
        return parsedData
    }

    fun parseJSONjerseys(stb: StringBuilder): MutableMap<String, String> {
        val json = JSONObject(stb.toString())

        // Check if the key "equipment" exists in the JSON object
        if (json.has("equipment") && "equipment" != null) {
            val jsonArray: JSONArray? = json.optJSONArray("equipment")

            // Check if the value associated with the key "equipment" is not null and is a JSON array
            if (jsonArray != null) {
                val leagueMap = mutableMapOf<String, String>()

                for (i in 0 until jsonArray.length()) {
                    val league: JSONObject = jsonArray.getJSONObject(i)

                    val idLeague = league.getString("strSeason")
                    val strTeam = league.getString("strEquipment")

                    leagueMap[idLeague] = strTeam
                }

                return leagueMap
            } else {
                Log.e("JerseysSearch", "Key 'equipment' exists but is not a valid JSON array")
            }
        } else {
            Log.e("JerseysSearch", "Key 'equipment' does not exist in the JSON object")
        }

        // Return an empty map if there was an error or the key "equipment" does not exist
        return mutableMapOf()
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

    @Composable
    fun progressIndicator(){
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            CircularProgressIndicator(
                modifier = Modifier.size(100.dp),
                color = Color.Red,
                strokeWidth = 10.dp,
            )
            Spacer(modifier = Modifier.height(40.dp))
            Text(text = "Loading!!", fontSize = 28.sp)
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "This will take few minutes to load jerseys")
        }
    }



}


