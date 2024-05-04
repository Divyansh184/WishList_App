package com.example.wishlistapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.launch

@Composable
fun AddEditDetailView(
    id: Int,
    viewModel: WishViewModel,
    navController: NavController
){
    val snackMessage = remember{
        mutableStateOf("")
    }
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    if (id != 0){
        val wish = viewModel.getAWishById(id).collectAsState(initial = Wish(0, "", ""))
        viewModel.titleState = wish.value.title
        viewModel.descState = wish.value.desc
    }else{
        viewModel.titleState = ""
        viewModel.descState = ""
    }

    Scaffold(scaffoldState=scaffoldState,
        topBar = {AppBarView(title =
    if(id != 0) "Update Wish"
    else "Add Wish"
    ) {navController.navigateUp()}
    },

        ) {
        Column(modifier = Modifier
            .padding(it)
            .wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Spacer(modifier = Modifier.height(10.dp))
            WishTextField(label ="Title" , value = viewModel.titleState , onValueChanged = {
                viewModel.titleChange(it)
            })
            Spacer(modifier = Modifier.height(10.dp))

            WishTextField(label = "Description",
                value = viewModel.descState,
                onValueChanged = {
                    viewModel.descChange(it)
                } )

            Spacer(modifier = Modifier.height(10.dp))

            Button(onClick={
                if(viewModel.titleState.isNotEmpty() &&
                    viewModel.descState.isNotEmpty()){
                    if(id!=0){
                        viewModel.updateWish(
                            Wish(
                                id = id,
                                title = viewModel.titleState.trim(),
                                desc = viewModel.descState.trim()
                            )
                        )

                    }else{
                        viewModel.addWish(
                            Wish(
                                title=viewModel.titleState.trim(),
                                desc = viewModel.descState.trim()
                            )

                        )
                    }
                }else{
                    snackMessage.value= "Enter fields to create a wish"
                }
                scope.launch {
                    scaffoldState.snackbarHostState.showSnackbar(snackMessage.value)
                    navController.navigateUp()
                }

            }){
                Text(
                    text = if (id != 0) "Update Wish"
                    else "Add Wish",
                    style = TextStyle(
                        fontSize = 18.sp
                    )
                )
            }


        }
    }

}


@Composable
fun WishTextField(
    label: String,
    value: String,
    onValueChanged: (String) -> Unit
){
    OutlinedTextField(
        value = value,
        onValueChange = onValueChanged,
        label = { Text(text = label, color = Color.Black) },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            // using predefined Color
            textColor = Color.Black,
            // using our own colors in Res.Values.Color
            focusedBorderColor = colorResource(id = R.color.black),
            unfocusedBorderColor = colorResource(id = R.color.black),
            cursorColor = colorResource(id = R.color.black),
            focusedLabelColor = colorResource(id = R.color.black),
            unfocusedLabelColor = colorResource(id = R.color.black),
        )


    )
}

