package ru.easycode.zerotoheroandroidtdd.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import ru.easycode.zerotoheroandroidtdd.FilterUi
import ru.easycode.zerotoheroandroidtdd.OrderUi
import ru.easycode.zerotoheroandroidtdd.ProductListUi
import ru.easycode.zerotoheroandroidtdd.ProductsViewModel

@Composable
fun ProductScreen(viewModel: ProductsViewModel) {

    val state = viewModel.productsUiListStateFlow.collectAsState()
    val orders = viewModel.ordersUiListStateFlow.collectAsState()
    val filters = viewModel.filtersUiListStateFlow.collectAsState()

    var showOrderDialog by remember { mutableStateOf(false) }
    var showFilterDialog by remember { mutableStateOf(false) }

    Box(Modifier.fillMaxSize()) {
        Column(Modifier.align(Alignment.TopCenter)) {
            Button(
                onClick = { showFilterDialog = true },
                modifier = Modifier
                    .testTag("filters button")
                    .align(Alignment.End)
                    .padding(end = 16.dp)
            ) {
                Text("filters")
            }

            LazyColumn(Modifier.testTag("ProductsLazyColumn")) {
                itemsIndexed(state.value) { index, item ->
                    when (item) {
                        is ProductListUi.Base -> ProductItem(item, index)
                        is ProductListUi.Empty -> NothingFound()
                    }
                }
            }
        }

        Button(
            onClick = { showOrderDialog = true },
            modifier = Modifier
                .testTag("order button")
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp)
        ) {
            Text("order")
        }
    }

    if (showOrderDialog) {
        OrderSettingsDialog(
            orders = orders.value,
            onChoose = { viewModel.chooseOrder(it) },
            onDismiss = { showOrderDialog = false }
        )
    }

    if (showFilterDialog) {
        FilterSettingsDialog(
            filters = filters.value,
            onChoose = { viewModel.chooseFilter(it) },
            onSave = { showFilterDialog = false }
        )
    }
}

@Composable
fun ProductItem(state: ProductListUi.Base, index: Int) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("Product at $index")
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = state.name,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.testTag("Product name at $index")
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = state.price,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.testTag("Product price at $index")
            )

            Text(
                text = state.os,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.testTag("Product os at $index")
            )

            Text(
                text = state.ram.toString(),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.testTag("Product ram at $index")
            )
        }
    }
}

@Composable
fun NothingFound() {
    Text(
        text = "Nothing found",
        modifier = Modifier.testTag("nothing found")
    )
}

@Composable
fun OrderSettingsDialog(
    orders: List<OrderUi>,
    onChoose: (String) -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {},
        title = { Text("Order by") },
        text = {
            Column {
                orders.forEach { order ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("Order option ${order.name}")
                            .semantics { selected = order.chosen }
                            .clickable {
                                onChoose(order.name)
                                onDismiss()
                            }
                    ) {
                        RadioButton(
                            selected = order.chosen,
                            onClick = null
                        )
                        Text(
                            text = order.name,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }
            }
        }
    )
}

@Composable
fun FilterSettingsDialog(
    filters: List<FilterUi>,
    onChoose: (Int) -> Unit,
    onSave: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onSave,
        confirmButton = {
            TextButton(
                onClick = onSave,
                modifier = Modifier.testTag("save button")
            ) {
                Text("save")
            }
        },
        title = { Text("Filters") },
        text = {
            Column {
                filters.forEach { filter ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("filter ${filter.category} ${filter.value}")
                            .semantics { selected = filter.chosen }
                            .clickable { onChoose(filter.id) }
                    ) {
                        Text(
                            text = "${filter.category}: ${filter.value}",
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }
            }
        }
    )
}
