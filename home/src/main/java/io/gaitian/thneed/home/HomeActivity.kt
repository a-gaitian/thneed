package io.gaitian.thneed.home

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.google.android.play.core.splitcompat.SplitCompat
import io.gaitian.thneed.ui.theme.ThneedTheme

class HomeActivity: ComponentActivity() {
    
    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
        SplitCompat.installActivity(this)
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        setContent {
            ThneedTheme {
                HomeScreen()
            }
        }
    }
}