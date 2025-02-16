package com.luna.dollargrain.editor.dateTimeEdit

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.luna.dollargrain.R
import com.luna.dollargrain.base.RenderAdaptivePane
import com.luna.dollargrain.base.datePicker.DatePicker
import com.luna.dollargrain.base.datePicker.model.CalendarSelectionMode
import com.luna.dollargrain.base.datePicker.model.CalendarState
import com.luna.dollargrain.ui.DollargrainTheme
import com.luna.dollargrain.util.prettyDate
import com.luna.dollargrain.util.toDate
import java.time.LocalDate

@Composable
fun DatePickerDialog(
    initDate: LocalDate = LocalDate.now(),
    disableBeforeDate: LocalDate? = null,
    disableAfterDate: LocalDate? = null,
    onSelect: (date: LocalDate) -> Unit,
    onClose: () -> Unit,
) {
    val context = LocalContext.current

    val datePickerState = remember {
        CalendarState(
            context,
            CalendarSelectionMode.SINGLE,
            selectDate = initDate.toDate(),
            disableBeforeDate = disableBeforeDate?.toDate(),
            disableAfterDate = disableAfterDate?.toDate(),
        )
    }

    Dialog(
        onDismissRequest = { onClose() },
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
            decorFitsSystemWindows = false,
        )
    ) {
        RenderAdaptivePane {
            Card(
                shape = MaterialTheme.shapes.extraLarge,
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainerLowest,
                ),
                modifier = Modifier
                    .widthIn(max = 500.dp)
                    .padding(16.dp),
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = "change date",
                        style = MaterialTheme.typography.titleSmall,
                        modifier = Modifier
                            .padding(horizontal = 24.dp, vertical = 16.dp)
                            .fillMaxWidth(),
                    )
                    Text(
                        text = prettyDate(
                            date = datePickerState.calendarUiState.value.selectedStartDate!!.toDate(),
                            pattern = "EEEE, dd MMM",
                            simplifyIfToday = true,
                        ),
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier
                            .padding(bottom = 16.dp, start = 24.dp, end = 24.dp)
                            .fillMaxWidth(),
                    )

                    HorizontalDivider(
                        modifier = Modifier
                            .padding(start = 30.dp, end = 30.dp),
                        thickness = 2.dp
                    )

                    Box(
                        Modifier
                            .fillMaxWidth()
                            .weight(weight = 1f, fill = false),
                        contentAlignment = Alignment.Center,
                    ) {
                        DatePicker(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            calendarState = datePickerState,
                            onDayClicked = {
                                datePickerState.setSelectedDay(it)
                            },
                        )
                    }

                    HorizontalDivider(
                        modifier = Modifier
                            .padding(start = 30.dp, end = 30.dp),
                        thickness = 2.dp
                    )
                    Row(Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 8.dp)) {
                        Spacer(modifier = Modifier.weight(1f))

                        TextButton(onClick = { onClose() }) {
                            Text("cancel")
                        }

                        TextButton(
                            onClick = {
                                onSelect(
                                    datePickerState.calendarUiState.value.selectedStartDate!!
                                )
                            }
                        ) {
                            Text(text = "apply")
                        }
                    }
                }
            }
        }
    }
}


@Preview(name = "Default", widthDp = 540)
@Composable
private fun PreviewDefault(){
    DollargrainTheme {
        DatePickerDialog(
            initDate = LocalDate.now(),
            disableBeforeDate = LocalDate.now().minusDays(3),
            disableAfterDate = LocalDate.now().plusDays(10),
            onSelect = {},
            onClose = {},
        )
    }
}

@Preview(name = "Night mode", widthDp = 540, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun PreviewNightMode(){
    DollargrainTheme {
        DatePickerDialog(
            initDate = LocalDate.now(),
            onSelect = {},
            onClose = {},
        )
    }
}