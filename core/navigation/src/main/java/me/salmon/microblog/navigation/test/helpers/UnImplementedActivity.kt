package me.salmon.microblog.navigation.test.helpers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import me.salmon.microblog.navigation.Navigator

class UnImplementedActivity : AppCompatActivity() {
    var navigator = Navigator(this)
}