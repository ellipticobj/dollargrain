package com.luna.dollargrain.wallet

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.luna.dollargrain.data.SpendsViewModel
import com.luna.dollargrain.analytics.RestAndSpentBudgetCard
import com.luna.dollargrain.analytics.WholeBudgetCard
import com.luna.dollargrain.data.ExtendCurrency

@Composable
fun BudgetSummary(
    spendsViewModel: SpendsViewModel = hiltViewModel(),
    onEdit: () -> Unit = {},
) {
    val currency by spendsViewModel.currency.observeAsState(ExtendCurrency.none())
    val wholeBudget = spendsViewModel.budget.value!!

    Column(Modifier.padding(start = 16.dp, top = 0.dp, end = 16.dp, bottom = 16.dp)) {
        RestAndSpentBudgetCard(
            modifier = Modifier,
            bigVariant = true
        )
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
        ) {
            WholeBudgetCard(
                modifier = Modifier.weight(1f),
                bigVariant = false,
                budget = wholeBudget,
                currency = currency,
                startDate = spendsViewModel.startPeriodDate.value!!,
                finishDate = spendsViewModel.finishPeriodDate.value!!,
            )
            DaysLeftCard(
                startDate = spendsViewModel.startPeriodDate.value!!,
                finishDate = spendsViewModel.finishPeriodDate.value!!,
            )
        }
        EditButton(onClick = { onEdit() })
    }
}
