import android.view.animation.OvershootInterpolator
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView


class BounceItemAnimator : DefaultItemAnimator() {

    override fun animateMove(
        holder: RecyclerView.ViewHolder,
        fromX: Int, fromY: Int,
        toX: Int, toY: Int
    ): Boolean {
        val view = holder.itemView

        val deltaX = toX - fromX
        val deltaY = toY - fromY

        view.animate()
            .translationXBy(deltaX.toFloat())
            .translationYBy(deltaY.toFloat())
            .setDuration(400)
            .setInterpolator(OvershootInterpolator(1.5f)) // Bounce effect
            .withEndAction {
                view.translationX = 0f
                view.translationY = 0f
            }
            .start()

        return super.animateMove(holder, fromX, fromY, toX, toY)
    }
}
