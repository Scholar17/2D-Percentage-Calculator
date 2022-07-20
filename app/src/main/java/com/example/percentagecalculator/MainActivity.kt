package com.example.percentagecalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.percentagecalculator.ui.theme.PercentageCalculatorTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PercentageCalculatorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("A Shal Gyi")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting(name: String) {
    var text by remember { mutableStateOf("10") }
    var percentOfTotal by remember {
        mutableStateOf("0")
    }

    var commissionFee by remember {
        mutableStateOf("0")
    }

    var totalLeft by remember {
        mutableStateOf("0")
    }

    var winNumberAmount by remember {
        mutableStateOf("0")
    }

    var totalReturnAmount by remember {
        mutableStateOf("0")
    }

    var mustReturnAmount by remember {
        mutableStateOf("0")
    }

    var ourTotalProfit by remember {
        mutableStateOf("0")
    }

    var profitForShareOwner by remember {
        mutableStateOf("0")
    }

    var profitForManager by remember {
        mutableStateOf("0")
    }

    percentOfTotal = if (text.isEmpty()) {
        0
    } else {
        (text.toInt() * 0.1).roundToInt()
    }.toString()

    totalReturnAmount = if (winNumberAmount.isEmpty()) {
        "0"
    } else {
        (winNumberAmount.toInt() * 80).toString()
    }

    mustReturnAmount = if (totalReturnAmount.isEmpty()) {
        "0"
    } else {
        (totalReturnAmount.toInt() * 0.1).roundToInt().toString()
    }
    ourTotalProfit = if (totalLeft.isEmpty()) {
        "0"
    } else {
        (totalLeft.toInt() - mustReturnAmount.toFloat()).roundToInt().toString()
    }
    profitForShareOwner = if (ourTotalProfit.isEmpty()) {
        "0"
    } else {
        (ourTotalProfit.toInt() * 0.25).roundToInt().toString()
    }

    profitForManager = if (ourTotalProfit.isEmpty()) {
        "0"
    } else {
        (ourTotalProfit.toInt() * 0.1).roundToInt().toString()
    }

    commissionFee = ((percentOfTotal.toInt() * 0.17).roundToInt()).toString()
    totalLeft = (percentOfTotal.toInt() - commissionFee.toInt()).toString()


    Scaffold(modifier = Modifier, topBar = {
        SmallTopAppBar(
            modifier = Modifier,
            title = { Text(text = "Calculator") },
            colors = TopAppBarDefaults.smallTopAppBarColors(MaterialTheme.colorScheme.primary)
        )
    }) {
        Column(modifier = Modifier.padding(10.dp)) {
            Text(text = "Hello $name!", color = MaterialTheme.colorScheme.primary)
            Row(modifier = Modifier.padding(top = 10.dp)) {
                CommonText(text = "Total Amount")
                CommonTextField(
                    text = text,
                    onValueChange = { value ->
                        text = value
                    },
                    colorScheme = darkColorScheme().copy(
                        primary = MaterialTheme.colorScheme.secondary.copy(0.2f)
                    )
                )
            }
            Row(modifier = Modifier.padding(top = 10.dp)) {
                CommonText(text = "10% of Total")
                CommonTextField(
                    text = percentOfTotal,
                    onValueChange = { value ->
                        percentOfTotal = value
                        text = (percentOfTotal.toInt() * 10).toString()
                    },
                    colorScheme = darkColorScheme().copy(
                        primary = MaterialTheme.colorScheme.secondary.copy(0.2f)
                    )
                )
            }
            Row(modifier = Modifier.padding(top = 10.dp)) {
                CommonText(text = "Commission Fee (17 %)")
                CommonTextField(
                    text = commissionFee,
                    onValueChange = { value ->
                        commissionFee = value
                    },
                    colorScheme = darkColorScheme().copy(primary = MaterialTheme.colorScheme.primaryContainer)
                )
            }
            Row(modifier = Modifier.padding(top = 10.dp)) {
                CommonText(text = "Our Total Asset(%နှုတ်ပြီးစုစုပေါင်းကျန်ငွေ)")
                CommonTextField(
                    text = totalLeft,
                    onValueChange = { value ->
                        totalLeft = value
                    },
                    colorScheme = darkColorScheme().copy(primary = MaterialTheme.colorScheme.primaryContainer)
                )
            }
            Row(modifier = Modifier.padding(top = 10.dp)) {
                CommonText(text = "Win number include?(ပေါက်ကြေးစုစုပေါင်း)")
                CommonTextField(
                    text = winNumberAmount,
                    onValueChange = { value ->
                        winNumberAmount = value
                    },
                    colorScheme = darkColorScheme().copy(
                        primary = MaterialTheme.colorScheme.secondary.copy(0.2f)
                    )
                )
            }
            Row(modifier = Modifier.padding(top = 10.dp)) {
                CommonText(text = "Total return amount(လျော်ကြေးစုစုပေါင်း)")
                CommonTextField(
                    text = totalReturnAmount,
                    onValueChange = { value ->
                        totalReturnAmount = value
                        winNumberAmount = if (totalReturnAmount.isNotEmpty()) {
                            (totalReturnAmount.toInt() / 80).toString()
                        } else {
                            "0"
                        }
                    },
                    colorScheme = darkColorScheme().copy(
                        primary = MaterialTheme.colorScheme.secondary.copy(0.2f)
                    )
                )
            }
            Row(modifier = Modifier.padding(top = 10.dp)) {
                CommonText(text = "Our return amount?(10%)")
                CommonTextField(
                    text = mustReturnAmount,
                    onValueChange = { value ->
                        mustReturnAmount = value
                        totalReturnAmount = if (mustReturnAmount.isNotEmpty()) {
                            ((mustReturnAmount.toInt() * 10.0)).toString()
                        } else {
                            "0"
                        }
                    },
                    colorScheme = darkColorScheme().copy(primary = MaterialTheme.colorScheme.primaryContainer)
                )
            }
            Row(modifier = Modifier.padding(top = 10.dp)) {
                CommonText(text = "Our total profit")
                CommonTextField(
                    text = ourTotalProfit,
                    onValueChange = { value ->
                        ourTotalProfit = value
                    },
                    colorScheme = darkColorScheme().copy(primary = MaterialTheme.colorScheme.primaryContainer)
                )
            }
            Row(modifier = Modifier.padding(top = 10.dp)) {
                CommonText(text = "Share Owner profit")
                CommonTextField(
                    text = profitForShareOwner,
                    onValueChange = { value ->
                        profitForShareOwner = value
                    },
                    colorScheme = darkColorScheme().copy(primary = MaterialTheme.colorScheme.primaryContainer)
                )
            }
            Row(modifier = Modifier.padding(top = 10.dp)) {
                CommonText(text = "Manager Profit")
                CommonTextField(
                    text = profitForManager,
                    onValueChange = { value ->
                        profitForManager = value
                    },
                    colorScheme = darkColorScheme().copy(primary = MaterialTheme.colorScheme.primaryContainer)
                )
            }
        }
    }

}

@Composable
fun CommonTextField(text: String, onValueChange: (String) -> Unit, colorScheme: ColorScheme) {
    BasicTextField(
        value = text,
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        maxLines = 1,
        textStyle = TextStyle(fontSize = 12.sp),
        modifier = Modifier.background(colorScheme.primary)
    )
}

@Composable
fun CommonText(text: String) {
    Text(
        text = text, modifier = Modifier
            .padding(end = 8.dp)
            .width(230.dp), color = MaterialTheme.colorScheme.primary, fontSize = 12.sp
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PercentageCalculatorTheme {
        Greeting("Android")
    }
}