package ru.jerold.showcaseview_v2.bubbleshowcase

import ru.jerold.showcaseview_v2.bubbleshowcase.BubbleShowCase

/**
 * Created by jcampos on 04/09/2018.
 *
 * Listener of user actions in a BubbleShowCase
 * Modified by chumbam on 06/06/2023
 */
interface BubbleShowCaseListener {
    /**
     * It is called when the user clicks on the targetView
     */
    fun onTargetClick(bubbleShowCase: BubbleShowCase)

    /**
     * It is called when the user clicks on the close icon
     */
    fun onCloseActionImageClick(bubbleShowCase: BubbleShowCase)

    /**
     * It is called when the user clicks on the background dim
     */
    fun onBackgroundDimClick(bubbleShowCase: BubbleShowCase)

    /**
     * It is called when the user clicks on the bubble
     */
    fun onBubbleClick(bubbleShowCase: BubbleShowCase)
}