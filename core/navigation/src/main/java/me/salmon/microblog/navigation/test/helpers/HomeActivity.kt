package me.salmon.microblog.navigation.test.helpers

import androidx.appcompat.app.AppCompatActivity
import me.salmon.microblog.navigation.Constants
import me.salmon.microblog.navigation.Navigator
import me.salmon.microblog.navigation.components.HomeNavigation

class HomeActivity : AppCompatActivity(), HomeNavigation {

    var navigator = Navigator(this)

    override fun homeActivityClass() = Constants.homeComponent

}