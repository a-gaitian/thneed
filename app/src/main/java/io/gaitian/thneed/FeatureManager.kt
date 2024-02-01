package io.gaitian.thneed

import android.util.Log
import com.google.android.play.core.ktx.sessionId
import com.google.android.play.core.splitinstall.SplitInstallManager
import com.google.android.play.core.splitinstall.SplitInstallRequest
import com.google.android.play.core.splitinstall.SplitInstallSessionState
import com.google.android.play.core.splitinstall.SplitInstallStateUpdatedListener

object FeatureManager {
    
    lateinit var manager: SplitInstallManager // TODO
    
    fun isInstalled(feature: String): Boolean =
        manager.installedModules.contains(feature)
            .also { Log.d("FeatureManager", "isInstalled: $feature -> $it") }
    
    fun install(
        feature: String,
        onFailure: () -> Unit = {},
        onUpdate: (SplitInstallSessionState) -> Unit = {}
    ) {
        val request = SplitInstallRequest
            .newBuilder()
            .addModule(feature)
            .build()
        
        var sessionId: Int? = null
        
        val listener = SplitInstallStateUpdatedListener {
            Log.d("FeatureManager", "Update: $it")
            if(it.sessionId == sessionId)
                onUpdate(it)
        }
        
        manager.registerListener(listener)
        
        manager
            .startInstall(request)
            .addOnSuccessListener {
                Log.d("FeatureManager", "Success: $it")
                sessionId = it
            }
            .addOnFailureListener {
                Log.d("FeatureManager", "Failure", it)
                onFailure()
            }
            .addOnCompleteListener {
                Log.d("FeatureManager", "Complete: $it")
                manager.unregisterListener(listener)
            }
    }
}