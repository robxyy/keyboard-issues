import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Face
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.movableContentOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun App() {
  MaterialTheme {
    // Navigation
    var showScreen2 by remember { mutableStateOf(false) }
    var showScreen3 by remember { mutableStateOf(false) }
    val movableScreen1 =
      remember { movableContentOf { Screen1({ showScreen2 = true }, { showScreen3 = true }) } }
    val movableScreen2 = remember { movableContentOf { Screen2 { showScreen2 = false } } }
    val movableScreen3 = remember { movableContentOf { Screen3 { showScreen3 = false } } }

    movableScreen1()
    if (showScreen2) {
      movableScreen2()
    }
    if (showScreen3) {
      movableScreen3()
    }
  }
}

@Composable
fun Screen1(goToScreen2: () -> Unit, goToScreen3: () -> Unit) {
  Scaffold {
    Column(Modifier.fillMaxSize()) {
      TopAppBar(
        navigationIcon = {
          IconButton(
            onClick = {
            },
            enabled = false
          ) {
            Icon(
              imageVector = Icons.Default.Face,
              contentDescription = "",
            )
          }
        },
        title = {}
      )

      Spacer(Modifier.height(24.dp))
      Button(onClick = goToScreen2, Modifier.fillMaxWidth().height(48.dp)) {
        Text("goTo Screen2")
      }

      Spacer(Modifier.height(24.dp))
      Button(onClick = goToScreen3, Modifier.fillMaxWidth().height(48.dp)) {
        Text("goTo Screen3")
      }
    }
  }
}

@Composable
fun Screen2(onBack: () -> Unit) {
  val scrollState = rememberScrollState()
  var title by remember { mutableStateOf("Hello, World!") }
  var content by remember { mutableStateOf("Hello, World!Hello, World!Hello, World!Hello, World!Hello, World!Hello, World!Hello, World!Hello, World!Hello, World!Hello, World!Hello, World!Hello, World!Hello, World!Hello, World!Hello, World!Hello, World!Hello, World!Hello, World!Hello, World!") }

  Scaffold {
    Column(
      Modifier.fillMaxWidth()
        .windowInsetsPadding(
          WindowInsets.statusBars
        )
    ) {
      TopAppBar(
        navigationIcon = {
          IconButton(
            onClick = onBack,
          ) {
            Icon(
              imageVector = Icons.Default.ArrowBack,
              contentDescription = "",
            )
          }
        },
        title = {}
      )
      Spacer(Modifier.height(24.dp))
      Column(
        Modifier.fillMaxWidth()
          .windowInsetsPadding(
            WindowInsets.navigationBars
          ).windowInsetsPadding(
            WindowInsets.ime
          ).verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        TextField(
          title, {
            title = it
          },
          Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(24.dp))
        TextField(
          content,
          {
            content = it
          },
          Modifier.fillMaxWidth().defaultMinSize(minHeight = 200.dp),
          singleLine = false,
        )
      }
    }
  }
}

@Composable
fun Screen3(onBack: () -> Unit) {
  Scaffold {
    Column(
      Modifier.fillMaxWidth()
        .windowInsetsPadding(
          WindowInsets.statusBars
        )
    ) {
      TopAppBar(
        navigationIcon = {
          IconButton(
            onClick = onBack
          ) {
            Icon(
              imageVector = Icons.Default.ArrowBack,
              contentDescription = "",
            )
          }
        },
        title = {}
      )
      Spacer(Modifier.height(24.dp))
      Button(onClick = {}, Modifier.fillMaxWidth().height(48.dp)) {
        Text("Hello World")
      }
    }
  }
}

expect fun getPlatformName(): String