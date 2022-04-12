package code.with.cal.moviesapp

import android.view.View
import android.widget.FrameLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView


class ScrollHandler : CoordinatorLayout.Behavior<BottomNavigationView?>() {
    fun layoutDependsOn(
        parent: CoordinatorLayout?,
        child: BottomNavigationView?,
        dependency: View?
    ): Boolean {
        return dependency is FrameLayout
    }

    fun onStartNestedScroll(
        coordinatorLayout: CoordinatorLayout?, child: BottomNavigationView?,
        directTargetChild: View?, target: View?, nestedScrollAxes: Int
    ): Boolean {
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL
    }

    fun onNestedPreScroll(
        coordinatorLayout: CoordinatorLayout?, child: BottomNavigationView,
        target: View?, dx: Int, dy: Int, consumed: IntArray?
    ) {
        if (dy < 0) {
            showBottomNavigationView(child)
        } else if (dy > 0) {
            hideBottomNavigationView(child)
        }
    }

    private fun hideBottomNavigationView(view: BottomNavigationView) {
        view.animate().translationY(view.height.toFloat())
    }

    private fun showBottomNavigationView(view: BottomNavigationView) {
        view.animate().translationY(0F)
    }
}
