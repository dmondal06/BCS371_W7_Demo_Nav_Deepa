package edu.farmingdale.bcs371_w7_demo_nav

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import edu.farmingdale.bcs371_w7_demo_nav.ui.theme.BCS371_W7_Demo_NavTheme
import androidx.navigation.NavController

class MainActivity2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BCS371_W7_Demo_NavTheme {
                val navController = rememberNavController() // Initialize NavController
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    BasicOperations(
                        navController = navController, // Pass NavController
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun BasicOperations(navController: NavController, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var checkswitch by remember { mutableStateOf(true) }

    Column {
        Spacer(modifier = Modifier.padding(50.dp))
        Button(
            onClick = {
                val newInt = Intent(Intent.ACTION_VIEW)
                newInt.setData(Uri.parse("geo:0,0?q=Farmingdale State College, NY"))
                context.startActivity(newInt)
            },
            modifier = Modifier.padding(start = 40.dp, end = 40.dp), enabled = checkswitch
        ) {
            Icon(imageVector = Icons.Default.LocationOn, contentDescription = "Location")
            Text("Show me Farmingdale", Modifier.padding(start = 10.dp))
        }
        HorizontalDivider(thickness = DividerDefaults.Thickness)

        Button(
            onClick = {
                val newInt = Intent(Intent.ACTION_VIEW)
                // ToDo 1: Done create implicit intent to open a web page or call a phone number
                newInt.setData(Uri.parse("tel:+9999999999"))
                context.startActivity(newInt)
            },
            modifier = Modifier.padding(start = 40.dp, end = 40.dp), enabled = checkswitch
        ) {
            Icon(imageVector = Icons.Default.Phone, contentDescription = "Phone")
            Text("Call Me", Modifier.padding(start = 10.dp))
        }

        HorizontalDivider(thickness = DividerDefaults.Thickness)

        Button(
            onClick = {
                // ToDo 2: Done create explicit intent to open a new activity
                context.startActivity(Intent(context, MainActivity2::class.java))
            },
            modifier = Modifier.padding(start = 40.dp, end = 40.dp), enabled = checkswitch
        ) {
            Icon(imageVector = Icons.Default.Info, contentDescription = "Phone")
            Text("Go To activity 2", Modifier.padding(start = 10.dp))
        }

        HorizontalDivider(thickness = DividerDefaults.Thickness)

        // Navigate to GPA Calculator screen
        Button(
            onClick = {

                navController.navigate("GpaCalculatorScreen")
            },
            modifier = Modifier.padding(start = 40.dp, end = 40.dp), enabled = checkswitch
        ) {
            Text("Go to GPA Calculator", Modifier.padding(start = 10.dp))
        }

        HorizontalDivider(thickness = DividerDefaults.Thickness)

        // Navigate to Pizza Party screen
        Button(
            onClick = {
                navController.navigate("PizzaPartyScreen")
            },
            modifier = Modifier.padding(start = 40.dp, end = 40.dp), enabled = checkswitch
        ) {
            Text("Go to Pizza Party", Modifier.padding(start = 10.dp))
        }

        HorizontalDivider(thickness = DividerDefaults.Thickness)

        // ToDo 5: Done This switch is not working fix it
        Switch(
            checked = checkswitch,
            onCheckedChange = { checkswitch = it },
            modifier = Modifier.padding(10.dp),
        )
        // ToDo 6: Done when the switch is off, disable the buttons
    }
}

@Preview(showBackground = true)
@Composable
fun BasicOperationsPreview() {
    val navController = rememberNavController() // For preview, initialize NavController
    BCS371_W7_Demo_NavTheme {
        BasicOperations(navController = navController)
    }
}
