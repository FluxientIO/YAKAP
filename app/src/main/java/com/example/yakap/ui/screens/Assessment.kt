package com.example.yakap.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.yakap.ui.viewmodels.AssessmentViewModel

@Composable
fun AssessmentScreen(viewModel: AssessmentViewModel, onFinished: () -> Unit) {
    val uiState = viewModel.uiState.value
    val questions = viewModel.questions

    if (uiState.isFinished) {
        AssessmentResultContent(
            score = uiState.totalScore,
            interpretation = uiState.resultInterpretation ?: "",
            onBack = {
                viewModel.reset()
                onFinished()
            }
        )
    } else {
        val currentQuestion = questions[uiState.currentQuestionIndex]
        AssessmentQuizContent(
            questionText = currentQuestion.text,
            options = currentQuestion.options.map { it.text to it.score },
            progress = (uiState.currentQuestionIndex + 1).toFloat() / questions.size,
            onOptionSelected = { viewModel.selectOption(it) }
        )
    }
}

@Composable
fun AssessmentQuizContent(
    questionText: String,
    options: List<Pair<String, Int>>,
    progress: Float,
    onOptionSelected: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Self-Assessment",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(16.dp))
        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(48.dp))
        Text(
            text = questionText,
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(bottom = 48.dp)
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            options.forEach { (text, score) ->
                Button(
                    onClick = { onOptionSelected(score) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer,
                        contentColor = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                ) {
                    Text(text = text, modifier = Modifier.padding(8.dp))
                }
            }
        }
    }
}

@Composable
fun AssessmentResultContent(score: Int, interpretation: String, onBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Your Result",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = interpretation,
            fontSize = 32.sp,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.ExtraBold,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Total Score: $score",
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.height(48.dp))
        Text(
            text = "This result is for information only and does not replace professional diagnosis. Please consult a specialist for a formal evaluation.",
            textAlign = TextAlign.Center,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.height(64.dp))
        Button(onClick = onBack, modifier = Modifier.fillMaxWidth()) {
            Text("Done")
        }
    }
}
