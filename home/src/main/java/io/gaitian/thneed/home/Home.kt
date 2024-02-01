package io.gaitian.thneed.home

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus
import io.gaitian.thneed.FeatureManager

@Composable
fun HomeScreen() {
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val context = LocalContext.current
        Text("Home")
        Button({ onClick(context) }) {
            Text("To Feature 1")
        }
    }
}

private fun onClick(context: Context) {
    val startFeature1 = {
        ActivityCompat.startActivity(
            context,
            Intent()
                .setClassName(context.packageName, "${context.packageName}.feature1.Feature1Activity"),
            null
        )
    }
    
    if(FeatureManager.isInstalled("feature1"))
        startFeature1()
    else
        FeatureManager.install("feature1") {
            if(it.status() == SplitInstallSessionStatus.INSTALLED)
                startFeature1()
        }
}
