import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.zikrapp.data.Zikr
import com.example.zikrapp.ui.viewmodel.DataBaseViewModel
import kotlinx.coroutines.flow.Flow

@SuppressLint("UnrememberedMutableState")
@Composable
fun ZikrEditAddScreen(
    onDone: () -> Unit,
    onCancel: () -> Unit,
    databaseViewModel: DataBaseViewModel = viewModel(),
    zikrId: Int,
    notSaveCount:Int?

) {
    val context = LocalContext.current

    val dataBaseViewModel: DataBaseViewModel = viewModel()


    var zikrFlow by remember { mutableStateOf<Flow<Zikr?>?>(null) }
    LaunchedEffect(zikrId,) {

            zikrFlow = databaseViewModel.getZikrById(zikrId) // call suspend function inside coroutine


    }

    val zikr by zikrFlow?.collectAsState(initial = null) ?: mutableStateOf(null)


    var zikrName by rememberSaveable { mutableStateOf("") }
    var isErrorInName by rememberSaveable { mutableStateOf(false) }
    var zikrStart by rememberSaveable { mutableStateOf("") }
    var isErrorInStart by rememberSaveable { mutableStateOf(false) }
    var zikrEnd by rememberSaveable { mutableStateOf("") }
    var isErrorInEnd by rememberSaveable { mutableStateOf(false) }
    var zikrDescription by rememberSaveable { mutableStateOf("") }
    var isErrorInDescription by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(zikr,notSaveCount) {
        if(notSaveCount == null){
            zikrStart = 50.toString()
        }
        zikrName = zikr?.zikrName ?: ""
        zikrStart = if(notSaveCount != null) {
            notSaveCount.toString()
        } else {zikr?.zikrCountStart?.toString() ?: ""}
        zikrEnd = zikr?.zikrCountEnd?.toString() ?: ""
        zikrDescription = zikr?.zikrDescription ?: ""
    }
    val zikrNameLimit = 25
    val zikrStartLimit = 9
    val zikrEndLimit = 9
    val zikrDescriptionLimit = 60

    Surface(
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(36.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            // Banner/Header (reuse your composable here if you wish)
            Spacer(modifier = Modifier.height(20.dp))


            ZikrInputField(
                label = "Name",
                value = zikrName,
                onValueChange = {
                    zikrName = it
                    isErrorInName = false
                },
                isError = isErrorInName,
                maxLength = zikrNameLimit,
                onDone = {

                },
                isNumeric = false

            )
            Spacer(modifier = Modifier.height(10.dp))

            ZikrInputField(
                label = "Start",
                value = zikrStart,
                onValueChange = {
                    zikrStart = it
                    isErrorInStart = false
                    isErrorInEnd = false
                },
                isError = isErrorInStart,
                maxLength = zikrStartLimit,
                onDone = {},
                isNumeric = true
            )


            Spacer(modifier = Modifier.height(10.dp))

            ZikrInputField(
                label = "End",
                value = zikrEnd,
                onValueChange = {

                    isErrorInEnd = false
                    isErrorInStart = false
                    zikrEnd = it
                },
                isError = isErrorInEnd,
                maxLength = zikrEndLimit,
                onDone = {},
                isNumeric = true
            )



            Spacer(modifier = Modifier.height(10.dp))

            ZikrInputField(
                label = "Description:",
                value = zikrDescription,
                onValueChange = {
                    zikrDescription = it
                    isErrorInDescription = false
                },
                isError = isErrorInDescription,
                maxLength = zikrDescriptionLimit,
                onDone = {},
                isNumeric = false
            )

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {


                //Cancel Button

                IconButton(modifier = Modifier.size(75.dp),
                    onClick = { onCancel() },
                    colors = IconButtonDefaults.iconButtonColors(containerColor = MaterialTheme.colorScheme.error)
                ) {
                    Icon(imageVector = Icons.Default.Close,tint = Color.White, contentDescription = "Close", modifier = Modifier.size(75.dp))
                }


                IconButton(modifier = Modifier.size(75.dp),
                    onClick = { // Validate inputs
                        if (zikrName.isBlank()) {
                            Toast.makeText(context, "Please enter a name", Toast.LENGTH_SHORT)
                                .show()
                            isErrorInName = true
                            return@IconButton
                        }
                        val start = zikrStart.toIntOrNull() ?: 0

                        if (zikrStart.isBlank()) {
                            Toast.makeText(
                                context,
                                "Enter Any Number",
                                Toast.LENGTH_SHORT
                            ).show()
                            isErrorInStart = true
                            return@IconButton
                        }
                        val end = zikrEnd.toIntOrNull() ?: 0
                        if (zikrEnd.isBlank()) {
                            Toast.makeText(
                                context,
                                "Enter valid numbers for End",
                                Toast.LENGTH_SHORT
                            ).show()
                            isErrorInEnd = true

                            return@IconButton
                        }
                        if(start > end) {
                            Toast.makeText(
                                context,
                                "Start Can't be greater than End",
                                Toast.LENGTH_SHORT
                            ).show()
                            isErrorInEnd = true
                            isErrorInStart = true
                            return@IconButton
                        }

                        if (end ==0) {
                            Toast.makeText(
                                context,
                                "End Can't be 0",
                                Toast.LENGTH_SHORT
                            ).show()
                            isErrorInEnd = true
                            return@IconButton
                        }
                        if (zikrDescription.isBlank()) {
                            Toast.makeText(
                                context,
                                "Please enter a description",
                                Toast.LENGTH_SHORT
                            ).show()
                            isErrorInDescription = true
                            return@IconButton
                        }
                        if(zikrId != 0) {
                            dataBaseViewModel.updateZikr(
                                zikrId,
                                zikrName,
                                start,
                                end,
                                zikrDescription
                            )
                            Toast.makeText(context, "Zikr updated!", Toast.LENGTH_SHORT).show()
                        }

                        if (zikrId == 0) {
                            dataBaseViewModel.addZikr(
                                Zikr(
                                    zikrId,
                                    zikrName,
                                    zikrDescription,
                                    start,
                                    end,
                                )
                            )
                            Toast.makeText(context, "Zikr added!", Toast.LENGTH_SHORT).show()
                            dataBaseViewModel.updateNotSavedCount(0)
                        }

                        onDone()
                    },
                    colors = IconButtonDefaults.iconButtonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Icon(imageVector = Icons.Default.Done, tint = Color.White, contentDescription = "Done",modifier = Modifier.size(75.dp),)
                }
            }

        }
    }
}

//
//@Preview
//@Composable
//fun ZikrEditAddScreenPreview() {
//
//    ZikrEditAddScreen(onDone = {}, onCancel = {}, zikrId = 0, databaseViewModel = DataBaseViewModel())
//}


@Composable
fun ZikrInputField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    isError: Boolean,
    onDone: () -> Unit = {},
    maxLength: Int,
    isNumeric: Boolean = false
) {
    val context = LocalContext.current
    val errorColor = MaterialTheme.colorScheme.error

    Column {
        Text(
            label,
            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Thin),
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = value,
            onValueChange = {
                val isValid = if (isNumeric) it.all { char -> char.isDigit() } else true
                if (it.length <= maxLength && isValid) {
                    onValueChange(it)
                } else {
                    isError
                    Toast.makeText(

                        context,
                        if (!isValid) "Enter numbers only" else "Max $maxLength chars",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            },
            isError = isError,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = if (isError) errorColor else MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = if (isError) errorColor else MaterialTheme.colorScheme.onSurface,
                cursorColor = if (isError) errorColor else MaterialTheme.colorScheme.primary
            ),
            textStyle = TextStyle(fontSize = 18.sp),
            modifier = Modifier.fillMaxWidth().height(if (label == "Description:") 120.dp else 70.dp),
            shape = RoundedCornerShape(10.dp),
            singleLine = label != "Description:",
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { onDone() })
        )
    }
}
