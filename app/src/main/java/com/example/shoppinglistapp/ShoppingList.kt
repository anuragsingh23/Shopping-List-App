package com.example.shoppinglistapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class ShoppingListData class ShoppingItems(
    val id: Int,
    var item: String,
    var isEditing: Boolean = false,
    var quantity: Int
)

@Composable
fun ShoppingListApp(){
    var sItems by remember {
        mutableStateOf(listOf<ShoppingItems>())
    }
    var showAlert by remember {
        mutableStateOf(false)
    }

    var itemName by remember {
        mutableStateOf("")
    }

    var itemQuantity by remember {
        mutableStateOf("")
    }


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        // add Item Button
        Button(
            onClick = { showAlert = true },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Add Item")
        }
        // lazy column
        LazyColumn (
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ){
            items(sItems){

            }
        }
    }

    if (showAlert){
       AlertDialog(onDismissRequest = { showAlert = false },
           confirmButton = { 
                           Row (
                               modifier = Modifier
                                   .fillMaxWidth()
                                   .padding(8.dp),
                               horizontalArrangement = Arrangement.SpaceAround
                           ){
                               Button(onClick = {
                                   if (itemName.isNotBlank()){
                                       val newItem = ShoppingItems(
                                           id = sItems.size + 1 ,
                                           item = itemName,
                                           quantity = itemQuantity.toInt()
                                       )
                                       sItems = sItems + newItem
                                       showAlert = false
                                       itemName = ""
                                   }
                               }) {
                                   Text(text = "Add")
                               }
                               Button(onClick = { showAlert = false }) {
                                   Text(text = "Cancel")
                               }
                           }
           },
           title = { Text(text = "Add Shopping Item")},
           text = {
               Column {
                   OutlinedTextField(value = itemName, onValueChange = {
                       itemName = it
                   },
                       singleLine = true,
                       modifier = Modifier
                           .fillMaxWidth()
                           .padding(8.dp),
                       label = { Text(text = "Item Name")}
                   )
                   OutlinedTextField(value = itemQuantity, onValueChange = {
                       itemQuantity = it
                   },
                       singleLine = true,
                       modifier = Modifier
                           .fillMaxWidth()
                           .padding(8.dp),
                       label = { Text(text = "Item Quantity")}
                   )
               }
           }
       )
    }
}
