package ru.showcaseview.library

import android.app.Activity
import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewTreeObserver
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import java.lang.ref.WeakReference
import java.util.ArrayList

/**
 * Created by jcampos on 04/09/2018.
 * Modified by chumbam on 06/06/2023
 */
class BubbleShowCaseBuilderV2 {

    internal var mActivity: WeakReference<Activity>? = null
    internal var mImage: Drawable? = null
    internal var mTitle: String? = null
    internal var mSubtitle: String? = null
    internal var mNextButtonTitle: String? = null
    internal var mNextButtonColor: Int? = null
    internal var mActionButtonTitle: String? = null
    internal var mActionButtonColor: Int? = null
    internal var mCloseAction: Drawable? = null
    internal var mBackgroundColor: Int? = null
    internal var mTextColor: Int? = null
    internal var mTitleTextSize: Int? = null
    internal var mSubtitleTextColor: Int? = null
    internal var mSubtitleTextSize: Int? = null
    internal var mIndicatorCount: Int? = null
    internal var mIndicatorSelected: Int? = null
    internal var mHighlightMode: BubbleShowCaseV2.HighlightMode? = null
    internal var mDisableTargetClick: Boolean = false
    internal var mDisableCloseAction: Boolean = false
    internal var mShowOnce: String? = null
    internal var mIsFirstOfSequence: Boolean? = null
    internal var mIsLastOfSequence: Boolean? = null
    internal val mArrowPositionList = ArrayList<BubbleShowCaseV2.ArrowPosition>()
    internal var mTargetView: WeakReference<View>? = null
    internal var mBubbleShowCaseListener: BubbleShowCaseListenerV2? = null
    internal var mSequenceShowCaseListener: SequenceShowCaseListener? = null

    private var onGlobalLayoutListenerTargetView: ViewTreeObserver.OnGlobalLayoutListener? = null

    /**
     * Builder constructor. It needs an instance of the current activity to convert it to a weak reference in order to avoid memory leaks
     */
    constructor(activity: Activity) {
        mActivity = WeakReference(activity)
    }

    /**
     * Title of the BubbleShowCase. This text is bolded in the view.
     */
    fun title(title: String): BubbleShowCaseBuilderV2 {
        mTitle = title
        return this
    }

    /**
     * Additional description of the BubbleShowCase. This text has a regular format
     */
    fun description(subtitle: String): BubbleShowCaseBuilderV2 {
        mSubtitle = subtitle
        return this
    }

    fun nextButtonTextTitle(btnTitle: String): BubbleShowCaseBuilderV2 {
        mNextButtonTitle = btnTitle
        return this
    }

    fun nextButtonColor(color: Int): BubbleShowCaseBuilderV2 {
        mNextButtonColor = color
        return this
    }

    fun nextButtonColorResourceId(@ColorRes colorResId: Int): BubbleShowCaseBuilderV2 {
        mNextButtonColor =
            ContextCompat.getColor(mActivity!!.get()?.applicationContext!!, colorResId)
        return this
    }

    fun actionButtonTextTitle(btnTitle: String): BubbleShowCaseBuilderV2 {
        mActionButtonTitle = btnTitle
        return this
    }

    fun actionButtonColor(color: Int): BubbleShowCaseBuilderV2 {
        mActionButtonColor = color
        return this
    }

    fun actionButtonColorResourceId(@ColorRes colorResId: Int): BubbleShowCaseBuilderV2 {
        mActionButtonColor =
            ContextCompat.getColor(mActivity!!.get()?.applicationContext!!, colorResId)
        return this
    }

    /**
     * Image drawable to inserted as main image in the BubbleShowCase
     *  - If this param is not passed, the BubbleShowCase will not have main image
     */
    fun image(image: Drawable): BubbleShowCaseBuilderV2 {
        mImage = image
        return this
    }

    /**
     * Image resource id to insert the corresponding drawable as main image in the BubbleShowCase
     *  - If this param is not passed, the BubbleShowCase will not have main image
     */
    fun imageResourceId(resId: Int): BubbleShowCaseBuilderV2 {
        mImage = ContextCompat.getDrawable(mActivity!!.get()?.applicationContext!!, resId)
        return this
    }

    /**
     * Image drawable to be inserted as close icon in the BubbleShowCase.
     *  - If this param is not defined, a default close icon is displayed
     */
    fun closeActionImage(image: Drawable?): BubbleShowCaseBuilderV2 {
        mCloseAction = image
        return this
    }

    /**
     * Image resource id to insert the corresponding drawable as close icon in the BubbleShowCase.
     *  - If this param is not defined, a default close icon is displayed
     */
    fun closeActionImageResourceId(resId: Int): BubbleShowCaseBuilderV2 {
        mCloseAction = ContextCompat.getDrawable(mActivity!!.get()?.applicationContext!!, resId)
        return this
    }


    /**
     * Background color of the BubbleShowCase.
     *  - #3F51B5 color will be set if this param is not defined
     */
    fun backgroundColor(color: Int): BubbleShowCaseBuilderV2 {
        mBackgroundColor = color
        return this
    }

    /**
     * Background color of the BubbleShowCase.
     *  - #3F51B5 color will be set if this param is not defined
     */
    fun backgroundColorResourceId(colorResId: Int): BubbleShowCaseBuilderV2 {
        mBackgroundColor =
            ContextCompat.getColor(mActivity!!.get()?.applicationContext!!, colorResId)
        return this
    }

    /**
     * Text color of the BubbleShowCase.
     *  - White color will be set if this param is not defined
     */
    fun textColor(color: Int): BubbleShowCaseBuilderV2 {
        mTextColor = color
        return this
    }

    /**
     * Text color of the BubbleShowCase.
     *  - White color will be set if this param is not defined
     */
    fun textColorResourceId(colorResId: Int): BubbleShowCaseBuilderV2 {
        mTextColor = ContextCompat.getColor(mActivity!!.get()?.applicationContext!!, colorResId)
        return this
    }

    /**
     * Title text size in SP.
     * - Default value -> 16 sp
     */
    fun titleTextSize(textSize: Int): BubbleShowCaseBuilderV2 {
        mTitleTextSize = textSize
        return this
    }

    fun descriptionTextColor(color: Int): BubbleShowCaseBuilderV2 {
        mTextColor = color
        return this
    }

    fun descriptionTextColorResourceId(@ColorRes colorResId: Int): BubbleShowCaseBuilderV2 {
        mSubtitleTextColor =
            ContextCompat.getColor(mActivity!!.get()?.applicationContext!!, colorResId)
        return this
    }

    /**
     * Description text size in SP.
     * - Default value -> 14 sp
     */
    fun descriptionTextSize(textSize: Int): BubbleShowCaseBuilderV2 {
        mSubtitleTextSize = textSize
        return this
    }

    fun addIndicator(itemsCount: Int, itemSelected: Int): BubbleShowCaseBuilderV2 {
        mIndicatorCount = itemsCount
        mIndicatorSelected = itemSelected
        return this
    }

    /**
     * If an unique id is passed in this function, this BubbleShowCase will only be showed once
     * - ID to identify the BubbleShowCase
     */
    fun showOnce(id: String): BubbleShowCaseBuilderV2 {
        mShowOnce = id
        return this
    }

    /**
     * Target view to be highlighted. Set a TargetView is essential to figure out BubbleShowCase position
     * - If a target view is not defined, the BubbleShowCase final position will be the center of the screen
     */
    fun targetView(targetView: View): BubbleShowCaseBuilderV2 {
        mTargetView = WeakReference(targetView)
        return this
    }

    /**
     * If this variable is true, when user clicks on the target, the showcase will not be dismissed
     *  Default value -> false
     */
    fun disableTargetClick(isDisabled: Boolean): BubbleShowCaseBuilderV2 {
        mDisableTargetClick = isDisabled
        return this
    }

    /**
     * If this variable is true, close action button will be gone
     *  Default value -> false
     */
    fun disableCloseAction(isDisabled: Boolean): BubbleShowCaseBuilderV2 {
        mDisableCloseAction = isDisabled
        return this
    }

    /**
     * Insert an arrowPosition to force the position of the BubbleShowCase.
     * - ArrowPosition enum values: LEFT, RIGHT, TOP and DOWN
     * - If an arrow position is not defined, the BubbleShowCase will be set in a default position depending on the targetView
     */
    fun arrowPosition(arrowPosition: BubbleShowCaseV2.ArrowPosition): BubbleShowCaseBuilderV2 {
        mArrowPositionList.clear()
        mArrowPositionList.add(arrowPosition)
        return this
    }

    /**
     * Insert a set of arrowPosition to force the position of the BubbleShowCase.
     * - ArrowPosition enum values: LEFT, RIGHT, TOP and DOWN
     * - If the number of arrow positions is 0 or more than 1, BubbleShowCase will be set on the center of the screen
     */
    fun arrowPosition(arrowPosition: List<BubbleShowCaseV2.ArrowPosition>): BubbleShowCaseBuilderV2 {
        mArrowPositionList.clear()
        mArrowPositionList.addAll(arrowPosition)
        return this
    }

    /**
     * Highlight mode. It represents the way that the target view will be highlighted
     * - VIEW_LAYOUT: Default value. All the view box is highlighted (the rectangle where the view is contained). Example: For a TextView, all the element is highlighted (characters and background)
     * - VIEW_SURFACE: Only the view surface is highlighted, but not the background. Example: For a TextView, only the characters will be highlighted
     */
    fun highlightMode(highlightMode: BubbleShowCaseV2.HighlightMode): BubbleShowCaseBuilderV2 {
        mHighlightMode = highlightMode
        return this
    }

    /**
     * Add a BubbleShowCaseListener in order to listen the user actions:
     * - onTargetClick -> It is triggered when the user clicks on the target view
     * - onCloseClick -> It is triggered when the user clicks on the close icon
     */
    fun listener(bubbleShowCaseListener: BubbleShowCaseListenerV2): BubbleShowCaseBuilderV2 {
        mBubbleShowCaseListener = bubbleShowCaseListener
        return this
    }

    /**
     * Add a sequence listener in order to know when a BubbleShowCase has been dismissed to show another one
     */
    internal fun sequenceListener(sequenceShowCaseListener: SequenceShowCaseListener): BubbleShowCaseBuilderV2 {
        mSequenceShowCaseListener = sequenceShowCaseListener
        return this
    }

    internal fun isFirstOfSequence(isFirst: Boolean): BubbleShowCaseBuilderV2 {
        mIsFirstOfSequence = isFirst
        return this
    }

    internal fun isLastOfSequence(isLast: Boolean): BubbleShowCaseBuilderV2 {
        mIsLastOfSequence = isLast
        return this
    }

    /**
     * Build the BubbleShowCase object from the builder one
     */
    private fun build(): BubbleShowCaseV2 {
        if (mIsFirstOfSequence == null)
            mIsFirstOfSequence = true
        if (mIsLastOfSequence == null)
            mIsLastOfSequence = true

        return BubbleShowCaseV2(this)
    }

    /**
     * Show the BubbleShowCase using the params added previously
     */
    fun show(): BubbleShowCaseV2 {
        val bubbleShowCase = build()
        if (mTargetView != null) {
            val targetView = mTargetView!!.get()
            if (targetView!!.height == 0 || targetView.width == 0) {
                //If the view is not already painted, we wait for it waiting for view changes using OnGlobalLayoutListener
                onGlobalLayoutListenerTargetView = ViewTreeObserver.OnGlobalLayoutListener {
                    bubbleShowCase.show()
                    targetView.viewTreeObserver.removeOnGlobalLayoutListener(
                        onGlobalLayoutListenerTargetView
                    )
                }
                targetView.viewTreeObserver.addOnGlobalLayoutListener(
                    onGlobalLayoutListenerTargetView
                )
            } else {
                bubbleShowCase.show()
            }
        } else {
            bubbleShowCase.show()
        }
        return bubbleShowCase
    }

}