package ru.showcaseview.library

/**
 * Created by jcampos on 04/09/2018.
 *
 * Listener of user actions in a BubbleShowCase
 * Modified by chumbam on 06/06/2023
 */
interface BubbleShowCaseListenerV2 {
    /**
     * It is called when the user clicks on the targetView
     */
    fun onTargetClick(bubbleShowCase: BubbleShowCaseV2)

    /**
     * It is called when the user clicks on the close icon
     */
    fun onCloseActionImageClick(bubbleShowCase: BubbleShowCaseV2)

    /**
     * It is called when the user clicks on the background dim
     */
    fun onBackgroundDimClick(bubbleShowCase: BubbleShowCaseV2)

    /**
     * It is called when the user clicks on the bubble
     */
    fun onBubbleClick(bubbleShowCase: BubbleShowCaseV2)
}