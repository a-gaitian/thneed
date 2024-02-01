package io.gaitian.thneed

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.google.android.play.core.splitcompat.SplitCompat
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus
import io.gaitian.thneed.ui.theme.ThneedTheme

class MainActivity: ComponentActivity() {
    
    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
        SplitCompat.installActivity(this)
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        FeatureManager.manager = SplitInstallManagerFactory.create(this)
        
        if(FeatureManager.isInstalled("home")) {
            startActivity(
                Intent()
                    .setClassName(packageName, "$packageName.home.HomeActivity")
                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            )
        }
        
        setContent {
            ThneedTheme {
                Text("MainActivity")
            }
        }
    }
}
