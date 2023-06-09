package ru.showcaseview.library

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import me.relex.circleindicator.CircleIndicator
import java.lang.ref.WeakReference

/**
 * Created by chumbam on 06/06/2023
 */

class BubbleMessageViewV2 : ConstraintLayout {

    private val WIDTH_ARROW = 20

    private var itemView: View? = null

    private var textViewTitle: TextView? = null
    private var textViewSubtitle: TextView? = null
    private var showCaseMessageViewLayout: ConstraintLayout? = null
    private var nextButton: TextView? = null
    private var actionButton: TextView? = null
    private var progressIndicator: CircleIndicator? = null
    private var targetViewScreenLocation: RectF? = null
    private var mBackgroundColor: Int = ContextCompat.getColor(context, R.color.blue_default)
    private var arrowPositionList = ArrayList<BubbleShowCaseV2.ArrowPosition>()

    private var paint: Paint? = null

    constructor(context: Context) : super(context) {
        initView()
    }

    constructor(context: Context, builder: Builder) : super(context) {
        initView()
        setAttributes(builder)
        setBubbleListener(builder)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initView()
    }

    private fun initView() {
        setWillNotDraw(false)

        inflateXML()
        bindViews()
    }

    private fun inflateXML() {
        itemView = inflate(context, R.layout.view_bubble_message_v2, this)
    }

    private fun bindViews() {
        textViewTitle = findViewById(R.id.textViewShowCaseTitle)
        textViewSubtitle = findViewById(R.id.textViewShowCaseText)
        showCaseMessageViewLayout = findViewById(R.id.showCaseMessageViewLayout)
        nextButton = findViewById(R.id.nextButton)
        actionButton = findViewById(R.id.actionButton)
        progressIndicator = findViewById(R.id.indicator)
    }

    private fun setAttributes(builder: Builder) {
        builder.mTitle?.let {
            textViewTitle?.visibility = View.VISIBLE
            textViewTitle?.text = builder.mTitle
        }
        builder.mSubtitle?.let {
            textViewSubtitle?.visibility = View.VISIBLE
            textViewSubtitle?.text = builder.mSubtitle
        }
        builder.mNextButtonTitle?.let {
            nextButton?.text = builder.mNextButtonTitle
        }
        builder.mNextButtonColor?.let {
            nextButton?.setTextColor(builder.mNextButtonColor!!)
        }
        builder.mActionButtonTitle?.let {
            actionButton?.visibility = View.VISIBLE
            actionButton?.text = builder.mActionButtonTitle
        }
        builder.mActionButtonColor?.let {
            actionButton?.setTextColor(builder.mActionButtonColor!!)
        }
        builder.mTextColor?.let {
            textViewTitle?.setTextColor(builder.mTextColor!!)

        }
        builder.mSubtitleTextColor?.let {
            textViewSubtitle?.setTextColor(builder.mSubtitleTextColor!!)
        }
        builder.mTitleTextSize?.let {
            textViewTitle?.setTextSize(
                TypedValue.COMPLEX_UNIT_SP,
                builder.mTitleTextSize!!.toFloat()
            )
        }
        builder.mIndicatorCount?.let { indicatorCount ->
            builder.mSelectedIndicator?.let { selectedIndicator ->
                progressIndicator?.visibility = View.VISIBLE
                progressIndicator?.createIndicators(indicatorCount, selectedIndicator)
            }
        }
        builder.mSubtitleTextSize?.let {
            textViewSubtitle?.setTextSize(
                TypedValue.COMPLEX_UNIT_SP,
                builder.mSubtitleTextSize!!.toFloat()
            )
        }
        builder.mBackgroundColor?.let { mBackgroundColor = builder.mBackgroundColor!! }
        arrowPositionList = builder.mArrowPosition
        targetViewScreenLocation = builder.mTargetViewScreenLocation
    }

    private fun setBubbleListener(builder: Builder) {
        nextButton?.setOnClickListener { builder.mListener?.onCloseActionImageClick() }
        actionButton?.setOnClickListener { builder.mListener?.onActionClick() }
        itemView?.setOnClickListener { builder.mListener?.onBubbleClick() }
    }


    //END REGION

    //REGION AUX FUNCTIONS

    private fun getViewWidth(): Int = width

    private fun getMargin(): Int = ScreenUtils.dpToPx(20)

    private fun getSecurityArrowMargin(): Int =
        getMargin() + ScreenUtils.dpToPx(2 * WIDTH_ARROW / 3)

    //END REGION

    //REGION SHOW ITEM

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        prepareToDraw()
        drawRectangle(canvas)

        for (arrowPosition in arrowPositionList) {
            drawArrow(canvas, arrowPosition, targetViewScreenLocation)
        }
    }

    private fun prepareToDraw() {
        paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint!!.color = mBackgroundColor
        paint!!.style = Paint.Style.FILL
        paint!!.strokeWidth = 4.0f
    }

    private fun drawRectangle(canvas: Canvas) {
        val rect = RectF(
            getMargin().toFloat() * 2,
            getMargin().toFloat(),
            getViewWidth() - getMargin().toFloat(),
            height.toFloat() - getMargin().toFloat()
        )
        canvas.drawRoundRect(rect, 16f, 16f, paint!!)
    }

    private fun drawArrow(
        canvas: Canvas,
        arrowPosition: BubbleShowCaseV2.ArrowPosition,
        targetViewLocationOnScreen: RectF?
    ) {
        val xPosition: Int
        val yPosition: Int

        when (arrowPosition) {
            BubbleShowCaseV2.ArrowPosition.LEFT -> {
                xPosition = getMargin() * 2
                yPosition =
                    if (targetViewLocationOnScreen != null) getArrowVerticalPositionDependingOnTarget(
                        targetViewLocationOnScreen
                    ) else height / 2
            }
            BubbleShowCaseV2.ArrowPosition.RIGHT -> {
                xPosition = getViewWidth() - getMargin()
                yPosition =
                    if (targetViewLocationOnScreen != null) getArrowVerticalPositionDependingOnTarget(
                        targetViewLocationOnScreen
                    ) else height / 2
            }
            BubbleShowCaseV2.ArrowPosition.TOP -> {
                xPosition =
                    if (targetViewLocationOnScreen != null) getArrowHorizontalPositionDependingOnTarget(
                        targetViewLocationOnScreen
                    ) else width / 2
                yPosition = getMargin()
            }
            BubbleShowCaseV2.ArrowPosition.BOTTOM -> {
                xPosition =
                    if (targetViewLocationOnScreen != null) getArrowHorizontalPositionDependingOnTarget(
                        targetViewLocationOnScreen
                    ) else width / 2
                yPosition = height - getMargin()
            }
        }

        drawRhombus(canvas, paint, xPosition, yPosition, ScreenUtils.dpToPx(WIDTH_ARROW))
    }

    private fun getArrowHorizontalPositionDependingOnTarget(targetViewLocationOnScreen: RectF?): Int {
        val xPosition: Int
        when {
            isOutOfRightBound(targetViewLocationOnScreen) -> xPosition =
                width - getSecurityArrowMargin()
            isOutOfLeftBound(targetViewLocationOnScreen) -> xPosition = getSecurityArrowMargin()
            else -> xPosition = Math.round(
                targetViewLocationOnScreen!!.centerX() - ScreenUtils.getAxisXpositionOfViewOnScreen(
                    this
                )
            )
        }
        return xPosition
    }

    private fun getArrowVerticalPositionDependingOnTarget(targetViewLocationOnScreen: RectF?): Int {
        val yPosition: Int
        when {
            isOutOfBottomBound(targetViewLocationOnScreen) -> yPosition =
                height - getSecurityArrowMargin()
            isOutOfTopBound(targetViewLocationOnScreen) -> yPosition = getSecurityArrowMargin()
            else -> yPosition = Math.round(
                targetViewLocationOnScreen!!.centerY() + ScreenUtils.getStatusBarHeight(
                    context
                ) - ScreenUtils.getAxisYpositionOfViewOnScreen(this)
            )
        }
        return yPosition
    }

    private fun isOutOfRightBound(targetViewLocationOnScreen: RectF?): Boolean {
        return targetViewLocationOnScreen!!.centerX() > ScreenUtils.getAxisXpositionOfViewOnScreen(
            this
        ) + width - getSecurityArrowMargin()
    }

    private fun isOutOfLeftBound(targetViewLocationOnScreen: RectF?): Boolean {
        return targetViewLocationOnScreen!!.centerX() < ScreenUtils.getAxisXpositionOfViewOnScreen(
            this
        ) + getSecurityArrowMargin()
    }

    private fun isOutOfBottomBound(targetViewLocationOnScreen: RectF?): Boolean {
        return targetViewLocationOnScreen!!.centerY() > ScreenUtils.getAxisYpositionOfViewOnScreen(
            this
        ) + height - getSecurityArrowMargin() - ScreenUtils.getStatusBarHeight(context)
    }

    private fun isOutOfTopBound(targetViewLocationOnScreen: RectF?): Boolean {
        return targetViewLocationOnScreen!!.centerY() < ScreenUtils.getAxisYpositionOfViewOnScreen(
            this
        ) + getSecurityArrowMargin() - ScreenUtils.getStatusBarHeight(context)
    }


    private fun drawRhombus(canvas: Canvas, paint: Paint?, x: Int, y: Int, width: Int) {
        val halfRhombusWidth = width / 2

        val path = Path()
        path.moveTo(x.toFloat(), (y + halfRhombusWidth).toFloat()) // Top
        path.lineTo((x - halfRhombusWidth).toFloat(), y.toFloat()) // Left
        path.lineTo(x.toFloat(), (y - halfRhombusWidth).toFloat()) // Bottom
        path.lineTo((x + halfRhombusWidth).toFloat(), y.toFloat()) // Right
        path.lineTo(x.toFloat(), (y + halfRhombusWidth).toFloat()) // Back to Top
        path.close()

        canvas.drawPath(path, paint!!)
    }


    //END REGION

    /**
     * Builder for BubbleMessageView class
     */
    class Builder {
        lateinit var mContext: WeakReference<Context>
        var mTargetViewScreenLocation: RectF? = null
        var mImage: Drawable? = null
        var mDisableCloseAction: Boolean? = null
        var mTitle: String? = null
        var mSubtitle: String? = null
        var mNextButtonTitle: String? = null
        var mNextButtonColor: Int? = null
        var mActionButtonTitle: String? = null
        var mActionButtonColor: Int? = null
        var mCloseAction: Drawable? = null
        var mBackgroundColor: Int? = null
        var mTextColor: Int? = null
        var mSubtitleTextColor: Int? = null
        var mTitleTextSize: Int? = null
        var mSubtitleTextSize: Int? = null
        var mIndicatorCount: Int? = null
        var mSelectedIndicator: Int? = null
        var mArrowPosition = ArrayList<BubbleShowCaseV2.ArrowPosition>()
        var mListener: OnBubbleMessageViewListener? = null

        fun from(context: Context): Builder {
            mContext = WeakReference(context)
            return this
        }

        fun title(title: String?): Builder {
            mTitle = title
            return this
        }

        fun subtitle(subtitle: String?): Builder {
            mSubtitle = subtitle
            return this
        }

        fun nextButtonTitle(btnTitle: String?): Builder {
            mNextButtonTitle = btnTitle
            return this
        }

        fun setNextButtonColor(color: Int?): Builder {
            mNextButtonColor = color
            return this
        }

        fun actionButtonTitle(btnTitle: String?): Builder {
            mActionButtonTitle = btnTitle
            return this
        }

        fun setActionButtonColor(color: Int?): Builder {
            mActionButtonColor = color
            return this
        }

        fun image(image: Drawable?): Builder {
            mImage = image
            return this
        }

        fun closeActionImage(image: Drawable?): Builder {
            mCloseAction = image
            return this
        }

        fun disableCloseAction(isDisabled: Boolean): Builder {
            mDisableCloseAction = isDisabled
            return this
        }

        fun targetViewScreenLocation(targetViewLocationOnScreen: RectF): Builder {
            mTargetViewScreenLocation = targetViewLocationOnScreen
            return this
        }

        fun backgroundColor(backgroundColor: Int?): Builder {
            mBackgroundColor = backgroundColor
            return this
        }

        fun textColor(textColor: Int?): Builder {
            mTextColor = textColor
            return this
        }

        fun subtitleTexteColor(textColor: Int?): Builder {
            mSubtitleTextColor = textColor
            return this
        }

        fun titleTextSize(textSize: Int?): Builder {
            mTitleTextSize = textSize
            return this
        }

        fun subtitleTextSize(textSize: Int?): Builder {
            mSubtitleTextSize = textSize
            return this
        }

        fun setIndicatorCountAndSelectedItem(indicatorCount: Int?, selectedItem: Int?): Builder {
            mIndicatorCount = indicatorCount
            mSelectedIndicator = selectedItem
            return this
        }

        fun arrowPosition(arrowPosition: List<BubbleShowCaseV2.ArrowPosition>): Builder {
            mArrowPosition.clear()
            mArrowPosition.addAll(arrowPosition)
            return this
        }

        fun listener(listener: OnBubbleMessageViewListener?): Builder {
            mListener = listener
            return this
        }

        fun build(): BubbleMessageViewV2 {
            return BubbleMessageViewV2(mContext.get()!!, this)
        }
    }
}