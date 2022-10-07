package cl.mario.covid.ui.viewcomponent

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import cl.mario.covid.databinding.ErrorBinding

class ErrorViewComponent(context: Context, attributeSet: AttributeSet) :
    ConstraintLayout(context, attributeSet) {
    private val binding = ErrorBinding.inflate(LayoutInflater.from(context), this, false)

    init {
        addView(binding.root)
    }

    fun setText(text: String) {
        binding.errorText.text = text
    }

    fun loadError(text: String, retryCallback: () -> Unit) {
        setText(text)
        setSubscriptionRetryButton(retryCallback)
    }

    fun setSubscriptionRetryButton(retryCallback: (() -> Unit)? = null) {
        binding.btn.setOnClickListener {
            retryCallback?.invoke()
        }
    }

}