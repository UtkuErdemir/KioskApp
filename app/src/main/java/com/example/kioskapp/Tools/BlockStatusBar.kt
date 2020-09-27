package com.example.kioskapp.Tools

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Handler
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method

class BlockStatusBar(context: Context, isPaused:Boolean) {
    internal var context:Context
    // To keep track of activity's window focus
    internal var currentFocus:Boolean = false
    // To keep track of activity's foreground/background status
    internal var isPaused:Boolean = false
    internal lateinit var collapseStatusBar: Method
    init{
        this.context = context
        this.isPaused = isPaused
        collapseNow()
    }
    fun collapseNow() {
        // Initialize 'collapseNotificationHandler'
        if (collapseNotificationHandler ==null)
        {
            collapseNotificationHandler = Handler()
        }
        // If window focus has been lost && activity is not in a paused state
        // Its a valid check because showing of notification panel
        // steals the focus from current activity's window, but does not
        // 'pause' the activity
        if (!currentFocus && !isPaused)
        {
            // Post a Runnable with some delay - currently set to 300 ms
            collapseNotificationHandler!!.postDelayed(object:Runnable {
                @SuppressLint("WrongConstant")
                public override fun run() {
                    // Use reflection to trigger a method from 'StatusBarManager'
                    val statusBarService = context.getSystemService("statusbar")
                   lateinit var statusBarManager:Class<*>
                    try
                    {
                        statusBarManager = Class.forName("android.app.StatusBarManager")
                    }
                    catch (e:ClassNotFoundException) {
                        e.printStackTrace()
                    }
                    try
                    {
                        // Prior to API 17, the method to call is 'collapse()'
                        // API 17 onwards, the method to call is `collapsePanels()`
                        if (Build.VERSION.SDK_INT > 16)
                        {
                            collapseStatusBar = statusBarManager.getMethod("collapsePanels")
                        }
                        else
                        {
                            collapseStatusBar = statusBarManager.getMethod("collapse")
                        }
                    }
                    catch (e:NoSuchMethodException) {
                        e.printStackTrace()
                    }
                    collapseStatusBar.setAccessible(true)
                    try
                    {
                        collapseStatusBar.invoke(statusBarService)
                    }
                    catch (e:IllegalArgumentException) {
                        e.printStackTrace()
                    }
                    catch (e:IllegalAccessException) {
                        e.printStackTrace()
                    }
                    catch (e: InvocationTargetException) {
                        e.printStackTrace()
                    }
                    // Check if the window focus has been returned
                    // If it hasn't been returned, post this Runnable again
                    // Currently, the delay is 100 ms. You can change this
                    // value to suit your needs.
                    if (!currentFocus && !isPaused)
                    {
                        collapseNotificationHandler!!.postDelayed(this, 100L)
                    }
                    if (!currentFocus && isPaused)
                    {
                        collapseNotificationHandler!!.removeCallbacksAndMessages(null)
                    }
                }
            }, 1L)
        }
    }
    companion object {
        var collapseNotificationHandler: Handler ? = null
    }
}
