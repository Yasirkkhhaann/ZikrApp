import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.zikrapp.ui.viewmodel.ZikrViewModel

import com.example.zikrapp.R
import com.example.zikrapp.ui.screens.ZikrScreen

@Composable
fun ZikrEditAddScreen(
    navController: NavController,
    zikrViewModel: ZikrViewModel = viewModel(),
    zikrId: Int
) {
    val context = LocalContext.current

    // Load zikr by ID from ViewModel
    val isNew = zikrId == 0

    val zikr = if (!isNew) {
        zikrViewModel.zikrs.find { it.zikrId == zikrId }
    } else null

    var zikrName by remember { mutableStateOf(zikr?.zikrName ?: "") }
    var zikrStart by remember { mutableStateOf(zikr?.zikrCountStart?.toString() ?: "") }
    var zikrEnd by remember { mutableStateOf(zikr?.zikrCountEnd?.toString() ?: "") }
    var zikrDescription by remember { mutableStateOf(zikr?.zikrDescription ?: "") }

    val zikrNameLimit = 25
    val zikrStartLimit = 9
    val zikrEndLimit = 9
    val zikrDescriptionLimit = 60

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0XFF1A1A1A))
            .padding(horizontal = 36.dp)
    ) {
        // Banner/Header (reuse your composable here if you wish)
        Spacer(modifier = Modifier.height(20.dp))

        Text("Name:", style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Thin), color = Color.White)
        TextField(
            value = zikrName,
            onValueChange = {
                if (it.length <= zikrNameLimit) zikrName = it else Toast.makeText(context, "Max $zikrNameLimit chars", Toast.LENGTH_SHORT).show()
            },
            textStyle = TextStyle(fontSize = 14.sp, color = Color.White),
            modifier = Modifier.height(50.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0XFF262626),
                unfocusedContainerColor = Color(0XFF262626),
                cursorColor = Color.White,
            ),
            shape = RoundedCornerShape(10.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text("Start:", style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Thin), color = Color.White)
        TextField(
            value = zikrStart,
            onValueChange = {
                if (it.length <= zikrStartLimit) zikrStart = it else Toast.makeText(context, "Max $zikrStartLimit digits", Toast.LENGTH_SHORT).show()
            },
            textStyle = TextStyle(fontSize = 14.sp, color = Color.White),
            modifier = Modifier.height(50.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0XFF262626),
                unfocusedContainerColor = Color(0XFF262626),
                cursorColor = Color.White,
            ),
            shape = RoundedCornerShape(10.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text("End:", style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Thin), color = Color.White)
        TextField(
            value = zikrEnd,
            onValueChange = {
                if (it.length <= zikrEndLimit) zikrEnd = it else Toast.makeText(context, "Max $zikrEndLimit digits", Toast.LENGTH_SHORT).show()
            },
            textStyle = TextStyle(fontSize = 14.sp, color = Color.White),
            modifier = Modifier.height(50.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0XFF262626),
                unfocusedContainerColor = Color(0XFF262626),
                cursorColor = Color.White,
            ),
            shape = RoundedCornerShape(10.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text("Description:", style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Thin), color = Color.White)
        TextField(
            value = zikrDescription,
            onValueChange = {
                if (it.length <= zikrDescriptionLimit) zikrDescription = it else Toast.makeText(context, "Max $zikrDescriptionLimit chars", Toast.LENGTH_SHORT).show()
            },
            textStyle = TextStyle(fontSize = 14.sp, color = Color.White),
            modifier = Modifier.height(90.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0XFF262626),
                unfocusedContainerColor = Color(0XFF262626),
                cursorColor = Color.White,
            ),
            shape = RoundedCornerShape(10.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Cancel button
            Image(
                painter = painterResource(id = R.drawable.cancel),
                contentDescription = "Cancel",
                modifier = Modifier.size(50.dp).clickable {
                    navController.navigate(ZikrScreen.ZikrList.name)
                }
            )
            // Done button
            Image(
                painter = painterResource(id = R.drawable.done2),
                contentDescription = "Done",
                modifier = Modifier.size(50.dp).clickable {
                    // Validate inputs
                    if (zikrName.isBlank()) {
                        Toast.makeText(context, "Please enter a name", Toast.LENGTH_SHORT).show()
                        return@clickable
                    }
                    val start = zikrStart.toIntOrNull() ?: 0
                    val end = zikrEnd.toIntOrNull() ?: 0
                    if (zikrStart.isBlank() || zikrEnd.isBlank()) {
                        Toast.makeText(context, "Enter valid numbers for Start/End", Toast.LENGTH_SHORT).show()
                        return@clickable
                    }
                    if (zikrDescription.isBlank()) {
                        Toast.makeText(context, "Please enter a description", Toast.LENGTH_SHORT).show()
                        return@clickable
                    }

                    if(isNew){
                        zikrViewModel.addZikr(zikrName, start, end, zikrDescription)
                        Toast.makeText(context, "Zikr added!", Toast.LENGTH_SHORT).show()
                    } else {
                        zikrViewModel.updateZikr(zikrId, zikrName, start, end, zikrDescription)
                        Toast.makeText(context, "Zikr updated!", Toast.LENGTH_SHORT).show()
                    }

                    navController.navigate(ZikrScreen.ZikrList.name)
                }
            )
        }
    }
}



@Composable
fun ZikrEditAddScreenBanner() {
    ElevatedCard(
        shape = RoundedCornerShape(bottomStart = 75.dp, bottomEnd = 75.dp),
        colors = CardDefaults.cardColors(Color(0XFF262626)),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .size(150.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 20.dp)
                .padding(top = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            Text("EDIT", style = TextStyle(fontSize = 32.sp), color = Color(0XFF0675C4))
            Text(" / ", style = TextStyle(fontSize = 32.sp), color = Color.White)
            Text("ADD", style = TextStyle(fontSize = 32.sp), color = Color(0XFFFC6C38))
            Text(" ZIKR", style = TextStyle(fontSize = 32.sp), color = Color(0XFF1C6615))
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 40.dp, top = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            Row(modifier = Modifier.weight(1f)) {
                Text(
                    "Here you can edit or add you new Zikr ",
                    style = TextStyle(fontSize = 14.sp),
                    color = Color.White,
                    fontWeight = FontWeight.Thin
                )

            }
            Row(modifier = Modifier.weight(0.4f)) {
                Image(
                    painter = painterResource(id = R.drawable.editaddicon),
                    contentDescription = "editaddicon",
                    modifier = Modifier.padding(start = 10.dp)
                )
            }
        }
    }
}

//@Preview
//@Composable
//fun ZikrEditAddScreenPreview() {
//
//    ZikrEditAddScreen(navController = NavController(LocalContext))
//}