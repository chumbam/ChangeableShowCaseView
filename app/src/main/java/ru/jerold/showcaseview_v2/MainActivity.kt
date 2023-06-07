package ru.jerold.showcaseview_v2

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import ru.jerold.showcaseview_v2.databinding.ActivityMainBinding
import ru.showcaseview.library.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        setUpListeners()
    }

    private fun setUpListeners() {
        binding.apply {
            buttonSimpleShowCase.setOnClickListener { getSimpleShowCaseBuilder().show() }
            buttonColorShowCase.setOnClickListener { getCustomColorShowCaseBuilder().show() }
            buttonTextSizeShowCase.setOnClickListener { getTextSizeShowCaseBuilder().show() }
            buttonArrowLeftShowCase.setOnClickListener { getArrowLeftShowCaseBuilder().show() }
            buttonArrowRightShowCase.setOnClickListener { getArrowRightShowCaseBuilder().show() }
            buttonEventListener.setOnClickListener { getListenerShowCaseBuilder().show() }
            buttonSequence.setOnClickListener { getSequence().show() }
        }
    }

    //SHOW CASES GETTERS

    private fun getSimpleShowCaseBuilder(): BubbleShowCaseBuilderV2 {
        return BubbleShowCaseBuilderV2(this)
            .title("Welcome!!!")
            .description("This is a simple BubbleShowCase with default values.")
            .addIndicator(3, 0)
            .targetView(binding.buttonSimpleShowCase)

    }

    private fun getCustomColorShowCaseBuilder(): BubbleShowCaseBuilderV2 {
        return BubbleShowCaseBuilderV2(this)
            .title("Custom your bubble style!")
            .description("It is possible to change the text color, background ... and you can even add an image into your bubble.")
            .backgroundColor(ContextCompat.getColor(this, R.color.colorSurfaceContainerHigh))
            .closeActionImage(
                ContextCompat.getDrawable(
                    this,
                    com.google.android.material.R.drawable.ic_clock_black_24dp
                )!!
            )
            .addIndicator(3, 1)
            .textColorResourceId(R.color.colorOnSurface)
            .descriptionTextColorResourceId(R.color.colorOnSurfaceVariant)
            .nextButtonTextTitle("Привет")
            .targetView(binding.buttonColorShowCase)
    }

    private fun getTextSizeShowCaseBuilder(): BubbleShowCaseBuilderV2 {
        return BubbleShowCaseBuilderV2(this)
            .title("Change text sizes!")
            .description("You can also choose the best text size for you.")
            .backgroundColor(ContextCompat.getColor(this, R.color.colorSurfaceContainerHigh))
            .titleTextSize(18)
            .textColorResourceId(R.color.colorOnSurface)
            .nextButtonColorResourceId(R.color.red_C02E2E)
            .descriptionTextColorResourceId(R.color.colorOnSurfaceVariant)
            .descriptionTextSize(16)
            .closeActionImage(null)
            .targetView(binding.buttonTextSizeShowCase)
    }

    private fun getArrowLeftShowCaseBuilder(): BubbleShowCaseBuilderV2 {
        return BubbleShowCaseBuilderV2(this)
            .title("Force the position of the bubble!")
            .description("You only have to specify in which side you want the arrow, and the bubble will be located depending on it.")
            .arrowPosition(BubbleShowCaseV2.ArrowPosition.LEFT)
            .backgroundColor(ContextCompat.getColor(this, R.color.colorSurfaceContainerHigh))
            .textColorResourceId(R.color.colorOnSurface)
            .descriptionTextColorResourceId(R.color.colorOnSurfaceVariant)
            .targetView(binding.buttonArrowLeftShowCase)
    }

    private fun getArrowRightShowCaseBuilder(): BubbleShowCaseBuilderV2 {
        return BubbleShowCaseBuilderV2(this)
            .title("Arrow set on right side this time :)")
            .arrowPosition(BubbleShowCaseV2.ArrowPosition.RIGHT)
            .backgroundColor(ContextCompat.getColor(this, R.color.colorSurfaceContainerHigh))
            .textColorResourceId(R.color.colorOnSurface)
            .descriptionTextColorResourceId(R.color.colorOnSurfaceVariant)
            .targetView(binding.buttonArrowRightShowCase)
    }


    private fun getListenerShowCaseBuilder(): BubbleShowCaseBuilderV2 {
        return BubbleShowCaseBuilderV2(this)
            .title("Listen user actions!")
            .description("You can detect when the user interacts with the different view elements to act consequently.")
            .backgroundColor(ContextCompat.getColor(this, R.color.green_01A47E))
            .actionButtonTextTitle("иди отсюда")
            .actionButtonColor(R.color.colorPrimary)
            .listener(object : BubbleShowCaseListenerV2 {
                override fun onBubbleClick(bubbleShowCase: BubbleShowCaseV2) {
                    Toast.makeText(this@MainActivity, "OnBubbleClick", Toast.LENGTH_SHORT).show()
                }

                override fun onActionClick(bubbleShowCase: BubbleShowCaseV2) {
                    Toast.makeText(this@MainActivity, "OnActionClick", Toast.LENGTH_SHORT)
                        .show()
                }

                override fun onBackgroundDimClick(bubbleShowCase: BubbleShowCaseV2) {
                    Toast.makeText(this@MainActivity, "OnBackgroundDimClick", Toast.LENGTH_SHORT)
                        .show()
                }

                override fun onTargetClick(bubbleShowCase: BubbleShowCaseV2) {
                    Toast.makeText(this@MainActivity, "OnTargetClick", Toast.LENGTH_SHORT).show()
                }

                override fun onCloseActionImageClick(bubbleShowCase: BubbleShowCaseV2) {
                    Toast.makeText(this@MainActivity, "OnClose", Toast.LENGTH_SHORT).show()
                }
            })
            .targetView(binding.buttonEventListener)
    }

    private fun getSequence(): BubbleShowCaseSequenceV2 {
        return BubbleShowCaseSequenceV2().addShowCases(
            listOf<BubbleShowCaseBuilderV2>(
                getSimpleShowCaseBuilder(),
                getCustomColorShowCaseBuilder(),
                getTextSizeShowCaseBuilder(),
                getArrowLeftShowCaseBuilder(),
                getArrowRightShowCaseBuilder(),
                getListenerShowCaseBuilder()
            )
        )
    }
}
